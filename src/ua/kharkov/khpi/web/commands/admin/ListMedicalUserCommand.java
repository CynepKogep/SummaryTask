package ua.kharkov.khpi.web.commands.admin;

import java.io.IOException;
import java.io.Serializable;
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
				
		String id_user = request.getParameter("id_user");
		String accesses_users = request.getParameter("accesses_users");
		log.debug("------------------");
		log.debug("id_user:" + id_user);
		log.debug("accesses_users:" + accesses_users);
		log.debug("------------------");
		String localeToSet = request.getParameter("localeToSet");
		log.debug("localeToSet:" + localeToSet);
		
//		if ((id_user != null && accesses_users != null))
//		{
//			
///*		}
//		else
//		{
//*/			if (accesses_users.equals("unlocked"))
//			{
//				accesses_users = "1";
//			}
//			else if (accesses_users.equals("blocked"))
//			{
//				accesses_users = "0";
//			}
//			new UserFullInfoDao().updateUserAccessesUsers(id_user, accesses_users);
//		}
//		
		
/*		
		// List<UserA> usersAList = new UserDaoA().findUserAll();
		List<UserFull> usersList = new UserFullInfoDao().getUsers();
		// log.trace("Found in DB: usersAList --> " + usersAList);
		log.trace("Found in DB: usersAList --> " + usersList);
		
		int size_UserFull = usersList.size();
		log.debug("Set the request attribute: size_UserFull --> " + size_UserFull);
		
		List<Integer> countBlockCard = new ArrayList<Integer>();
		for (int i = 0; i < size_UserFull;  i++)
		{
			countBlockCard.add(new UserFullInfoDao().getBlockCard(i + 1));
		}
		for (int i = 0; i < size_UserFull;  i++)
		{
			log.debug("countBlockCard ["+ i + "]: " + countBlockCard.get(i));
			UserFull oneUserFull = usersList.get(i);
			oneUserFull.setCountBlockCard(countBlockCard.get(i));
			usersList.set(i, oneUserFull);
		}
		
		// Collections.sort(userOrderBeanList, compareById);
		
		// put user order beans list to request
		// request.setAttribute("usersAList", usersAList);		
		request.setAttribute("usersList", usersList);
		log.trace("Set the request attribute: usersList --> " + usersList);
*/		
//		List<UserFullWithBlockCard> usersWithBlockCardList = new UserFullInfoDao().getUsersWithBlockCard();
//		request.setAttribute("usersList", usersWithBlockCardList);
//		log.trace("Set the request attribute: usersList --> " + usersWithBlockCardList);
		
		List<MedicalUser> usersList = new MedicalUserDao().getMedicalUsers();
		for (MedicalUser user : usersList) {
			user.setProfessionName(new ProfessionDao().getCategoryById(user.getProfessionId()).getProfessionName());
		}
		request.setAttribute("usersList", usersList);
		log.trace("Set the request attribute: usersList --> " + usersList);
		
		log.debug("Commands \"ListMedicalUserCommand\" finished");
		
		// request.setAttribute("usersList", usersList);
		
		
		return Path.PAGE__ADMIN_MEDICAL_USER;
		
		// return Path.PAGE__ADMIN_ONE; //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// return Path.PAGE__LIST_ORDERS;
	}


}