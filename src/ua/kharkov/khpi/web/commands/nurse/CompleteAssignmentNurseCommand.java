package ua.kharkov.khpi.web.commands.nurse;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.kharkov.khpi.constants.Path;
import ua.kharkov.khpi.database.dao.AssignmentDao;
import ua.kharkov.khpi.database.enums.Role;
import ua.kharkov.khpi.web.commands.general.Command;

public class CompleteAssignmentNurseCommand extends Command{
	
	private static final long serialVersionUID = 6825322230912344147L;
	private static final Logger log = Logger.getLogger(CompleteAssignmentNurseCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		log.debug("Commands \"CompleteAssignmentNurseCommand\" starts");
		
		String errorMessage = null;
		String forward = Path.PAGE__ERROR_PAGE;
		log.debug("request.getSession(false):" + request.getSession(false));
		
		//check the session
		if (request.getSession(false) == null) {
			errorMessage = "You are not register";
			request.setAttribute("errorMessage", errorMessage);
			return forward;
		}
		//check the role
		if (request.getSession(false).getAttribute("userRole") == null ||
				!request.getSession(false).getAttribute("userRole").equals(Role.NURSE)) {
			errorMessage = "Wrong priviliges";
			request.setAttribute("errorMessage", errorMessage);
			return forward;
		}
		
		int assignment_id = Integer.parseInt(request.getParameter("assignment_id"));
		new AssignmentDao().CompleteAssignmentById(assignment_id);	
		
		log.debug("Commands \"CompleteAssignmentNurseCommand\" finished");
		
		return new PatientCardNurseCommand().execute(request, response);
	}

}
