package ua.kharkov.khpi.web.commands.admin;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.kharkov.khpi.constants.Path;
import ua.kharkov.khpi.database.beans.MedicalUser;
import ua.kharkov.khpi.database.dao.MedicalUserDao;
import ua.kharkov.khpi.database.dao.ProfessionDao;
import ua.kharkov.khpi.database.enums.Role;
import ua.kharkov.khpi.web.commands.general.Command;

public class ListMedicalUserCommand extends Command{
	
	private static final long serialVersionUID = 1863978254689586513L;
	private static final Logger log = Logger.getLogger(ListMedicalUserCommand.class);
	
	/**
	 * Serializable comparator used with TreeMap container. When the servlet
	 * container tries to serialize the session it may fail because the session
	 * can contain TreeMap object with not serializable comparator.
	 * 
	 */
	private static class CompareById implements Comparator<MedicalUser>, Serializable {
		private static final long serialVersionUID = -1573481565177573283L;
		public int compare(MedicalUser bean1, MedicalUser bean2) {
			if (bean1.getId() > bean2.getId())
				return 1;
			else return -1;
		}
	}
	private static Comparator<MedicalUser> compareById = new CompareById();
			
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		log.debug("Commands \"ListMedicalUserCommand\" starts");
		
		String errorMessage = null;
		String forward = Path.PAGE__ERROR_PAGE;
		log.debug("request.getSession(false):" + request.getSession(false));
		
		// check the session
		if (request.getSession(false) == null) {
			errorMessage = "You are not register";
			request.setAttribute("errorMessage", errorMessage);
			return forward;
		}
		// check the role
		if (request.getSession(false).getAttribute("userRole") == null
				|| !request.getSession(false).getAttribute("userRole").equals(Role.ADMIN)) {
			errorMessage = "Wrong priviliges";
			request.setAttribute("errorMessage", errorMessage);
			return forward;
		}
	
		String localeToSet = request.getParameter("localeToSet");
		log.debug("localeToSet:" + localeToSet);
		
		// Fill in the list of a medical user bean <<MedicalUse>> at the request 
		// related to the table <<MEDICAL_USER>> in the database. 
		// Some fields of a bean <<MedicalUse>> are empty.
		List<MedicalUser> usersList = new MedicalUserDao().getMedicalUsers();
		// Refill in empty fields of a bean <<MedicalUse>> with the following cycle, 
		// using methods of a bean <<MedicalUse>> and Dao <<ProfessionDao>>. 
		for (MedicalUser user : usersList) {
			user.setProfessionName(new ProfessionDao().getProfessionById(user.getProfessionId()).getProfessionName());
			user.setProfessionNameRu(new ProfessionDao().getProfessionById(user.getProfessionId()).getProfessionNameRu());
			
			user.setNumberOfPatients(new MedicalUserDao().getCountOfPatientsForDoctorId(user.getId()));
		}
		// Sort
		if(request.getParameter("sorting_order") == null || request.getParameter("sorting_order").equals("sort_by_id")) {
			Collections.sort(usersList, new Comparator<MedicalUser>() {
				public int compare(MedicalUser o1, MedicalUser o2) {
					return (int)(o1.getId() - o2.getId());
				}
			});
		} else if(request.getParameter("sorting_order").equals("sort_by_login")) {
			Collections.sort(usersList, (MedicalUser o1, MedicalUser o2) -> (o1.getLogin().compareTo(o2.getLogin())));
		} else if(request.getParameter("sorting_order").equals("sort_by_lastname")) {
			Collections.sort(usersList, (MedicalUser o1, MedicalUser o2) -> (o1.getLastName().compareTo(o2.getLastName())));
		} else if(request.getParameter("sorting_order").equals("sort_by_lastname_ru")) {
			Collections.sort(usersList, (MedicalUser o1, MedicalUser o2) -> (o1.getLastNameRu().compareTo(o2.getLastNameRu())));
		} else if(request.getParameter("sorting_order").equals("sort_by_profession")) {
			Collections.sort(usersList, (MedicalUser o1, MedicalUser o2) -> (o1.getProfessionName().compareTo(o2.getProfessionName())));
		}else if(request.getParameter("sorting_order").equals("sort_by_profession_ru")) {
			Collections.sort(usersList, (MedicalUser o1, MedicalUser o2) -> (o1.getProfessionNameRu().compareTo(o2.getProfessionNameRu())));
		}else if(request.getParameter("sorting_order").equals("sort_by_number_of_pations")) {
			Collections.sort(usersList, (MedicalUser o1, MedicalUser o2) -> (int)(o1.getNumberOfPatients() - o2.getNumberOfPatients()));
		}

		request.setAttribute("usersList", usersList);
		log.trace("Set the request attribute: usersList --> " + usersList);

		log.debug("Commands \"ListMedicalUserCommand\" finished");
		
		return Path.PAGE__ADMIN_MEDICAL_USER;
	}
}
