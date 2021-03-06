package ua.kharkov.khpi.web.commands.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import org.apache.log4j.Logger;

import ua.kharkov.khpi.constants.NameOfCommand;
import ua.kharkov.khpi.constants.Path;
import ua.kharkov.khpi.database.beans.MedicalUser;
import ua.kharkov.khpi.database.dao.MedicalUserDao;
import ua.kharkov.khpi.database.enums.Role;
import ua.kharkov.khpi.web.commands.general.Command;

/**
 * Login command.
 * 
 */
public class LoginCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;
	private static final Logger log = Logger.getLogger(LoginCommand.class);
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		log.debug("Command \"LoginCommand\" starts");
		
		HttpSession session = request.getSession();
		
		// obtain login and password from the request
		String login = request.getParameter("login");
		log.trace("Request parameter: loging --> " + login);
		
		String password = request.getParameter("password");
		
		// error handler
		String errorMessage = null;		
		String forward = Path.PAGE__ERROR_PAGE;
		
		if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
			errorMessage = "Login/password cannot be empty";
			request.setAttribute("errorMessage", errorMessage);
			log.error("errorMessage --> " + errorMessage);
			return forward;
		}
		
		MedicalUser user = new MedicalUserDao().findMedicalUserByLogin(login);
		log.trace("Found in DB: user --> " + user);
			
		if (user == null || !password.equals(user.getPassword())) {
			errorMessage = "Cannot find user with such login/password";
			request.setAttribute("errorMessage", errorMessage);
			log.error("errorMessage --> " + errorMessage);
			return forward;
		} else {
			Role userRole = Role.getRole(user);
			log.debug("userRole --> " + userRole);
			log.debug("Role.ADMIN --> " + Role.ADMIN); 
			log.debug("Role.DOCTOR --> " + Role.DOCTOR); 
			log.debug("Role.NURSE --> " + Role.NURSE); 
			
			if (userRole == Role.ADMIN) {
				log.debug("userRole == Role.ADMIN");
				forward = NameOfCommand.COMMAND__LIST_MEDICAL_USER;
			}
		
			if (userRole == Role.DOCTOR) {
				log.debug("userRole == Role.DOCTOR");
				forward = NameOfCommand.COMMAND__LIST_PATIENT_FOR_DOCTOR;
			}

			if (userRole == Role.NURSE) {
				log.debug("userRole == Role.NURSE");
				forward = NameOfCommand.COMMAND__LIST_PATIENT_FOR_NURSE;
			}

			session.setAttribute("user", user);
			log.trace("Set the session attribute: user --> " + user);
				
			session.setAttribute("userRole", userRole);				
			log.trace("Set the session attribute: userRole --> " + userRole);
			
			session.setAttribute("login", login);
			log.trace("Set the session attribute: login --> " + login);
			
			log.info("User " + user + " logged as " + userRole.toString().toLowerCase());

			// work with i18n
			// String userLocaleName = user.getLocaleName();
			String userLocaleName = null;
			log.trace("userLocalName --> " + userLocaleName);
			
			if (userLocaleName != null && !userLocaleName.isEmpty()) {
				Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", userLocaleName);
				
				session.setAttribute("defaultLocale", userLocaleName);
				log.trace("Set the session attribute: defaultLocaleName --> " + userLocaleName);
				log.info("Locale for user: defaultLocale --> " + userLocaleName);
			}
		}
		
		log.debug("Command \"LoginCommand\" finished");
		return forward;
	}

}