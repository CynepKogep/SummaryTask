package ua.kharkov.khpi.web.commands.doctor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.kharkov.khpi.constants.Path;
import ua.kharkov.khpi.database.beans.Patient;
import ua.kharkov.khpi.database.beans.PatientAssignment;
import ua.kharkov.khpi.database.dao.AssignmentDao;
import ua.kharkov.khpi.database.dao.PatientDao;
import ua.kharkov.khpi.database.enums.Role;
import ua.kharkov.khpi.web.commands.general.Command;


public class DischargePatientDoctorCommand extends Command{
	private static final long serialVersionUID = 7052213159842679224L;
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String errorMessage = null;
		String forward = Path.PAGE__ERROR_PAGE;
		
		//check the session
		if (request.getSession(false) == null) {
			errorMessage = "You are not register";
			request.setAttribute("errorMessage", errorMessage);

			return forward;
		}
//		//check the role
//		if (request.getSession(false).getAttribute("medRole") == null ||
//				!request.getSession(false).getAttribute("medRole").equals(Role.DOCTOR)) {
//			errorMessage = "Wrong priviliges";
//			request.setAttribute("errorMessage", errorMessage);
//
//			return forward;
//		}
		
		
		
		long id = Long.parseLong(request.getParameter("patient_id"));
		new PatientDao().DischargedPatient(id);
		
		
		Patient patient = new PatientDao().getPatientById(id);
		List<PatientAssignment> patient_assignment = new AssignmentDao().getPatientAssignments(id);
		StringBuilder sb = new StringBuilder();
		sb.append("Patient with id:" + patient.getId() + "\n"
				+ patient.getFirstName() + " " + patient.getLastName()+"\n");
		sb.append("Patient assignment information:"+ "\n");
		for (PatientAssignment patientAssignment : patient_assignment) {
			sb.append("ID: " + patientAssignment.getId() +
			", Assignment name: " + patientAssignment.getAssignmentName() +
			", Assinment status: " + patientAssignment.getAssignmentStatusName()+ "\n");
		}
		sb.append("This patient was discharged!");
		File f = new File("F:\\" + patient.getId()
							+"_"+patient.getFirstName()+".txt");
		System.out.println(f.getAbsolutePath());
		PrintWriter pr = new PrintWriter(new FileOutputStream(f));
		pr.write(sb.toString());
		pr.close();
		
		// SendMailSSL.SendMailToUser(patient.getEmail(), sb.toString());
		// return new ListPatientForDoctorCommand().execute(request, response);
		String information = new String(sb);		
		request.setAttribute("information", information);
		
		return Path.PAGE__DOCTOR_INFORMATION_DISCHARGED_PATIENT;
		
	}
}
