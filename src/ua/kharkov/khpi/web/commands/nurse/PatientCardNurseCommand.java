package ua.kharkov.khpi.web.commands.nurse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.kharkov.khpi.constants.Path;
import ua.kharkov.khpi.database.beans.Diagnosis;
import ua.kharkov.khpi.database.beans.Patient;
import ua.kharkov.khpi.database.beans.PatientAssignment;
import ua.kharkov.khpi.database.dao.AssignmentDao;
import ua.kharkov.khpi.database.dao.DiagnosisDao;
import ua.kharkov.khpi.database.dao.PatientDao;
import ua.kharkov.khpi.database.enums.Assignment;
import ua.kharkov.khpi.database.enums.Role;
import ua.kharkov.khpi.web.commands.general.Command;

public class PatientCardNurseCommand extends Command{
	
	private static final long serialVersionUID = 3340981522803068509L;
	private static final Logger log = Logger.getLogger(PatientCardNurseCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		log.debug("Commands \"PatientCardNurseCommand\" starts");
		
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
		
		String patient_id = request.getParameter("patient_id");
		log.debug("patient_id:" + patient_id);
		Patient patient = new PatientDao().getPatientById(Long.parseLong(request.getParameter("patient_id")));
		
		log.debug("patient.getId():" + patient.getId());
		log.debug("patient.getDiagnosisName():" + patient.getDiagnosisName());
		Diagnosis diagnosis_for_id = new DiagnosisDao().getDiagnosById(patient.getDiagnosis_id());
		patient.setDiagnosisName(diagnosis_for_id.getDiagnosisName());
		log.debug("patient.getDiagnosisName():" + patient.getDiagnosisName());
		
		List<PatientAssignment> patientAssignmentList = new AssignmentDao().getPatientAssignments(patient.getId());
		for (PatientAssignment patientAssignment: patientAssignmentList) {
			log.debug("patientAssignment:" + patientAssignment.toString());
		}
			
		List<Assignment> assignmentList = new ArrayList<>();
		assignmentList.add(Assignment.values()[0]);
		assignmentList.add(Assignment.values()[1]);
		
//		for (Assignment assignment : Assignment.values()) {
//			assignmentList.add(assignment);
//		}

		List<Diagnosis> diagnosisList =new  DiagnosisDao().GetDiagnoses();
		
		request.setAttribute("diagnosisList", diagnosisList);
		request.setAttribute("assignmentList", assignmentList);
		request.setAttribute("patientAssignmentList", patientAssignmentList);
		request.setAttribute("patient", patient);
		
		log.debug("Commands \"PatientCardNurseCommand\" finished");
		
		return Path.PAGE__NURSE_PATIENT_CARD;
	}
	
}