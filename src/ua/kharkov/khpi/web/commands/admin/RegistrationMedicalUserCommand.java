package ua.kharkov.khpi.web.commands.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.kharkov.khpi.constants.Path;
import ua.kharkov.khpi.database.beans.MedicalUser;
import ua.kharkov.khpi.database.beans.Profession;
import ua.kharkov.khpi.database.dao.MedicalUserDao;
import ua.kharkov.khpi.database.dao.ProfessionDao;
import ua.kharkov.khpi.database.enums.Role;
import ua.kharkov.khpi.web.commands.general.Command;



public class RegistrationMedicalUserCommand extends Command{

	private static final long serialVersionUID = 1863978254689587888L;
	private static final Logger log = Logger.getLogger(RegistrationMedicalUserCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		log.debug("Command \"RegistrationMedicalUserCommand\" starts");
		
		String command = request.getParameter("command");
		String login_registration = request.getParameter("login_registration");
		String password_registration = request.getParameter("password_registration");
		String first_name_registration = request.getParameter("first_name_registration");
		String last_name_registration = request.getParameter("last_name_registration");
		String first_name_ru_registration = request.getParameter("first_name_ru_registration");
		String last_name_ru_registration = request.getParameter("last_name_ru_registration");
		String profession_registration = request.getParameter("profession_registration");
		
		String role = request.getParameter("role");
		int role_id = -1;
		if (role != null) {
			if (role.toLowerCase().equals(Role.DOCTOR.getName().toLowerCase())) {
				role_id = 1;
			} else {
				role_id = 2;
			}
		}

		String name_button = request.getParameter("name_button");

		log.debug("command:" + command);
		log.debug("login_registration:" + login_registration);
		log.debug("password_registration:" + password_registration);
		log.debug("first_name_registration:" + first_name_registration);
		log.debug("last_name_registration:" + last_name_registration);
		log.debug("first_name_ru_registration:" + first_name_ru_registration);
		log.debug("last_name_ru_registration:" + last_name_ru_registration);
		log.debug("profession_registration:" + role);
		log.debug("role:" + profession_registration);
		log.debug("name_button:" + name_button);
		
		List<MedicalUser> usersList = new MedicalUserDao().getMedicalUsers();
		log.debug("Set the request attribute: usersList --> " + usersList);
		int size_usersList = usersList.size();
		log.debug("Set the request attribute: size_UserFull --> " + size_usersList);
		
		List<Profession> professionList = new ProfessionDao().getProfession();
		log.debug("Set the request attribute: professionList --> " + usersList);
		int size_professionList = professionList.size();
		log.debug("Set the request attribute: size_professionList --> " + size_professionList);
				
		List<Role> roleList = new ArrayList<>();
		roleList.add(Role.DOCTOR);
		roleList.add(Role.NURSE);
		request.setAttribute("role", roleList);
		
		
		String is_user = null;
		if (login_registration != null && password_registration != null && 
			first_name_registration != null && last_name_registration != null && 
			first_name_ru_registration != null && last_name_ru_registration != null && 
			profession_registration != null && name_button != null){
			
			log.debug("if  #1");
			if (name_button.equals("button_registration") && usersList != null && professionList != null)
			{
				log.debug("if  #2");
				is_user = "is_not";
				for (int i = 0; i < usersList.size(); i++)
				{
					String userLogin = usersList.get(i).getLogin();
					String userLastName = usersList.get(i).getLastName();
					String userFirstName = usersList.get(i).getFirstName();
					String userLastNameRu = usersList.get(i).getLastNameRu();
					String userFirstNameRu = usersList.get(i).getFirstNameRu();
					if (userLogin.equals(login_registration)|| 
						userLastName.equals(last_name_registration)&&userFirstName.equals(first_name_registration)||
						userLastNameRu.equals(last_name_ru_registration)&&userFirstNameRu.equals(first_name_registration))
					{
						log.debug("if(is_user) == is");
						is_user = "is";
						break;
					}
				}
				if(is_user.equals("is_not"))
				{
					log.debug("if(is_user) == is_not");
					MedicalUser user = new MedicalUser();
					user.setLogin(login_registration);
					user.setPassword(password_registration);
					user.setFirstName(first_name_registration);
					user.setLastName(last_name_registration);
					user.setFirstNameRu(first_name_ru_registration);
					user.setLastNameRu(last_name_ru_registration);
					int profession_registration_int = Integer.parseInt(profession_registration);
					user.setProfessionId(profession_registration_int);
					
					user.setRoleId(role_id);
					
					new MedicalUserDao().addMedicalUser(user);
					
					usersList = new MedicalUserDao().getMedicalUsers();
				}
			}
		}
		request.setAttribute("is_user", is_user);
		request.setAttribute("usersList", usersList);
		request.setAttribute("professionList", professionList);
		
		log.debug("Command \"RegistrationMedicalUserCommand\" finished");
		return Path.PAGE__ADMIN_ADD_MEDICAL_USER;
	}

}
