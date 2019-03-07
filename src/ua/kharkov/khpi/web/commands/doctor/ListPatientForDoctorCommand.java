package ua.kharkov.khpi.web.commands.doctor;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.kharkov.khpi.constants.Path;
import ua.kharkov.khpi.database.beans.MedicalUser;
import ua.kharkov.khpi.database.beans.Patient;
import ua.kharkov.khpi.database.dao.MedicalUserDao;
import ua.kharkov.khpi.database.dao.PatientDao;
import ua.kharkov.khpi.web.commands.admin.ListMedicalUserCommand;
import ua.kharkov.khpi.web.commands.general.Command;

public class ListPatientForDoctorCommand extends Command {
	private static final long serialVersionUID = -5361970576847952484L;
	private static final Logger log = Logger.getLogger(ListPatientForDoctorCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

//		String errorMessage = null;
//		String forward = Path.PAGE__ERROR_PAGE;
//		System.out.println(request.getSession(false));
//		// check the session
//		if (request.getSession(false) == null) {
//			errorMessage = "You are not register";
//			request.setAttribute("errorMessage", errorMessage);
//			return forward;
//		}
//		// check the role
//		if (request.getSession(false).getAttribute("medRole") == null
//				|| !request.getSession(false).getAttribute("medRole").equals(Role.ADMIN)) {
//			errorMessage = "Wrong priviliges";
//			request.setAttribute("errorMessage", errorMessage);
//
//			return forward;
//		}

		HttpSession session = request.getSession();
		String login = (String)session.getAttribute("login");
		MedicalUser user = new MedicalUserDao().findMedicalUserByLogin(login);
		
		log.debug("session: " + session);
		log.debug("login --> " + login);
		log.debug("user.getId() --> " + user.getId());
		
		
		List<Patient> patientList = new PatientDao().getPatientsByDoctorId(user.getId());
		
		if (request.getParameter("sorting_order") == null
				|| request.getParameter("sorting_order").equals("sort_by_id")) {
			Collections.sort(patientList, (Patient o1, Patient o2) -> (int) (o1.getId() - o2.getId()));
		} else if (request.getParameter("sorting_order").equals("sort_by_lastname")) {
			Collections.sort(patientList, (Patient o1, Patient o2) -> o1.getLastName().compareTo(o2.getLastName()));
		}else if (request.getParameter("sorting_order").equals("sort_by_lastname_ru")) {
			Collections.sort(patientList, (Patient o1, Patient o2) -> o1.getLastNameRu().compareTo(o2.getLastNameRu()));
		}else if (request.getParameter("sorting_order").equals("sort_by_email")) {
			Collections.sort(patientList, (Patient o1, Patient o2) -> o1.getEmail().compareTo(o2.getEmail()));
		} else if (request.getParameter("sorting_order").equals("sort_by_date_of_birth")) {
			Collections.sort(patientList, (Patient o1, Patient o2) -> o1.getDateOfBirth().compareTo(o2.getDateOfBirth()));
		}
		request.setAttribute("patient_list", patientList);
		return Path.PAGE__DOCTOR_PATIENT;
	}

}
