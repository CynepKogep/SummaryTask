package ua.kharkov.khpi.web.commands.nurse;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.kharkov.khpi.constants.Path;
import ua.kharkov.khpi.database.dao.AssignmentDao;
import ua.kharkov.khpi.database.enums.Assignment;
import ua.kharkov.khpi.database.enums.Role;
import ua.kharkov.khpi.web.commands.doctor.PatientCardDoctorCommand;
import ua.kharkov.khpi.web.commands.general.Command;

public class MakeAnAssignmentNurseCommand extends Command{

	private static final long serialVersionUID = -6075347981523918546L;
	private static final Logger log = Logger.getLogger(MakeAnAssignmentNurseCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		log.debug("Commands \"MakeAnAssignmentNurseCommand\" starts");
		
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
		
		String assignment_name = request.getParameter("assignment_name");
		int assignment_id = Assignment.getIndex(assignment_name);
		Long patient_id = Long.parseLong(request.getParameter("patient_id"));
		new AssignmentDao().AddAssignment(patient_id, assignment_id);
		
		log.debug("Commands \"MakeAnAssignmentNurseCommand\" finished");
		
		return new PatientCardNurseCommand().execute(request, response);
	}
}
