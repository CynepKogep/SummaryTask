package ua.kharkov.khpi.web.commands.doctor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.kharkov.khpi.constants.Path;
import ua.kharkov.khpi.database.dao.PatientDao;
import ua.kharkov.khpi.database.enums.Role;
import ua.kharkov.khpi.web.commands.general.Command;


public class SetDiagnosisDoctorCommand extends Command{

	private static final long serialVersionUID = 6645534777806269511L;
	private static final Logger log = Logger.getLogger(SetDiagnosisDoctorCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		log.debug("Commands \"SetDiagnosisDoctorCommand\" starts");
		
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
		
		int patient_id = Integer.parseInt(request.getParameter("patient_id"));
		int diagnosis_id = Integer.parseInt(request.getParameter("diagnosis_name"));
		
		new PatientDao().setDiagnosis(diagnosis_id, patient_id);
		
		log.debug("Commands \"SetDiagnosisDoctorCommand\" starts");
		
		return new PatientCardDoctorCommand().execute(request, response);
	}
}
