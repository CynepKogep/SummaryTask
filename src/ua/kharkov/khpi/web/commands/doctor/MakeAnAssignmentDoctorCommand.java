package ua.kharkov.khpi.web.commands.doctor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.kharkov.khpi.constants.Path;
import ua.kharkov.khpi.database.dao.AssignmentDao;
import ua.kharkov.khpi.database.enums.Assignment;
import ua.kharkov.khpi.database.enums.Role;
import ua.kharkov.khpi.web.commands.general.Command;

public class MakeAnAssignmentDoctorCommand  extends Command{
	
	private static final long serialVersionUID = -6075347981523918546L;
	private static final Logger log = Logger.getLogger(MakeAnAssignmentDoctorCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		log.debug("Commands \"MakeAnAssignmentDoctorCommand\" starts");
		
		String errorMessage = null;
		String forward = Path.PAGE__ERROR_PAGE;
		
		//check the session
		if (request.getSession(false) == null) {
			errorMessage = "You are not register";
			request.setAttribute("errorMessage", errorMessage);
			return forward;
		}
		
		//check the role
		if (request.getSession(false).getAttribute("userRole") == null ||
				!request.getSession(false).getAttribute("userRole").equals(Role.DOCTOR)) {
			errorMessage = "Wrong priviliges";
			request.setAttribute("errorMessage", errorMessage);

			return forward;
		}
		
		String assignment_name = request.getParameter("assignment_name");
		int assignment_id = Assignment.getIndex(assignment_name);
		Long patient_id = Long.parseLong(request.getParameter("patient_id"));
		new AssignmentDao().AddAssignment(patient_id, assignment_id);
		
		log.debug("Commands \"ListPatientForDoctorCommand\" finished");
		
		return new PatientCardDoctorCommand().execute(request, response);
	}
}
