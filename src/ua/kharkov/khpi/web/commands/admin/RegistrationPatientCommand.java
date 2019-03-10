package ua.kharkov.khpi.web.commands.admin;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.kharkov.khpi.constants.Path;
import ua.kharkov.khpi.database.beans.MedicalUser;
import ua.kharkov.khpi.database.beans.Patient;
import ua.kharkov.khpi.database.beans.Profession;
import ua.kharkov.khpi.database.dao.MedicalUserDao;
import ua.kharkov.khpi.database.dao.PatientDao;
import ua.kharkov.khpi.database.dao.ProfessionDao;
import ua.kharkov.khpi.database.enums.Role;
import ua.kharkov.khpi.web.commands.general.Command;

public class RegistrationPatientCommand extends Command{

	private static final long serialVersionUID = 1863978254689587888L;
	private static final Logger log = Logger.getLogger(RegistrationPatientCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		log.debug("Command \"RegistrationPatientCommand\" starts");
		
		String errorMessage = null;
		String forward = Path.PAGE__ERROR_PAGE;
		log.debug("request.getSession(false):" + request.getSession(false));
		
		// check the session
		if (request.getSession(false) == null) {
			errorMessage = "You are not register";
			request.setAttribute("errorMessage", errorMessage);
			return forward;
		}
		// check the role
		if (request.getSession(false).getAttribute("userRole") == null
				|| !request.getSession(false).getAttribute("userRole").equals(Role.ADMIN)) {
			errorMessage = "Wrong priviliges";
			request.setAttribute("errorMessage", errorMessage);
			return forward;
		}
		
		String command = request.getParameter("command");
		
		String first_name_registration = request.getParameter("first_name_registration");
		String last_name_registration = request.getParameter("last_name_registration");
		String first_name_ru_registration = request.getParameter("first_name_ru_registration");
		String last_name_ru_registration = request.getParameter("last_name_ru_registration");
		String doctor_id_registration = request.getParameter("doctor_id_registration");
		int doctor_id_registration_int = -1;
		if (doctor_id_registration != null)
		    doctor_id_registration_int = Integer.parseInt(doctor_id_registration);
		// -----------------------------------------------------------------------
		// Had problem with to input field "date_of_birth" - validation input date  
		// -----------------------------------------------------------------------
		String error_date_of_birth = "zero";
		String date_of_birth_registration = request.getParameter("date_of_birth_registration");
		Date date_of_birth_registration_date = null;
		if (date_of_birth_registration != null) {
			log.debug("date_of_birth_registration:" + date_of_birth_registration);
			log.debug("date_of_birth_registration_data:" + date_of_birth_registration_date);
			try {
				date_of_birth_registration_date = Date.valueOf(date_of_birth_registration);
		    } catch (IllegalArgumentException e) {
		        System.out.println(e.getMessage());
		        log.debug(e.getMessage());
				log.debug("date_of_birth_registration:" + date_of_birth_registration);
				log.debug("date_of_birth_registration_data:" + date_of_birth_registration_date);
		        log.debug("Command \"RegistrationPatientCommand\" with IllegalArgumentException finished");
				error_date_of_birth = "Error input date of birth. Input date again!";
				request.setAttribute("error_date_of_birth", error_date_of_birth);
				return Path.PAGE__ADMIN_ADD_PATIENT;
		    }
		}
		request.setAttribute("error_date_of_birth", error_date_of_birth);
		// ----------------------------------------------------------------------- 
		String telephon_number_registration = request.getParameter("telephon_number_registration");
		String email_registration = request.getParameter("email_registration");
		String name_button = request.getParameter("name_button");

		log.debug("command:" + command);
		log.debug("first_name_registration:" + first_name_registration);
		log.debug("last_name_registration:" + last_name_registration);
		log.debug("first_name_ru_registration:" + first_name_ru_registration);
		log.debug("last_name_ru_registration:" + last_name_ru_registration);
		log.debug("doctor_id_registration:" + doctor_id_registration);
		log.debug("doctor_id_registration_int:" + doctor_id_registration_int);
		log.debug("date_of_birth_registration:" + date_of_birth_registration);
		log.debug("date_of_birth_registration_date:" + date_of_birth_registration_date);
		log.debug("telephon_number_registration:" + telephon_number_registration);
		log.debug("email_registration:" + email_registration);
		
		log.debug("name_button:" + name_button);
		
		List<Patient> patientList = new PatientDao().getPatients();
		log.debug("Set the request attribute: usersList --> " + patientList);
		int size_patientList = patientList.size();
		
		List<MedicalUser> usersList = new MedicalUserDao().getMedicalUsers();
		log.debug("Set the request attribute: usersList --> " + usersList);
		int size_usersList = usersList.size();
		log.debug("Set the request attribute: size_usersList --> " + size_usersList);
		
		request.setAttribute("usersList", usersList);
		
		String is_user = null;
		if (first_name_registration != null && last_name_registration != null && 
			first_name_ru_registration != null && last_name_ru_registration != null && 
			doctor_id_registration != null && date_of_birth_registration != null &&
			telephon_number_registration != null && email_registration != null &&
			name_button != null){
			
			log.debug("if  #1");
			if (name_button.equals("button_registration") && usersList != null)
			{
				log.debug("if  #2");
				is_user = "is_not";
				for (int i = 0; i < patientList.size(); i++)
				{
					String patientLastName = patientList.get(i).getLastName();
					String patientFirstName = patientList.get(i).getFirstName();
					String patientLastNameRu = patientList.get(i).getLastNameRu();
					String patientFirstNameRu = patientList.get(i).getFirstNameRu();
					if (patientLastName.equals(last_name_registration)&&patientFirstName.equals(first_name_registration)||
						patientLastNameRu.equals(last_name_ru_registration)&&patientFirstNameRu.equals(first_name_registration))
					{
						log.debug("if(is_user) == is");
						is_user = "is";
						break;
					}
				}
				if(is_user.equals("is_not"))
				{
					log.debug("if(is_user) == is_not");
					Patient patient = new Patient();
					patient.setFirstName(first_name_registration);
					patient.setLastName(last_name_registration);
					patient.setFirstNameRu(first_name_ru_registration);
					patient.setLastNameRu(last_name_ru_registration);
					patient.setTelephoneNumber(telephon_number_registration);
					patient.setEmail(email_registration);	
					patient.setDateOfBirth(date_of_birth_registration_date);
					patient.setDoctor_id(doctor_id_registration_int);

					new PatientDao().addNewPatient(patient);
					
					patientList = new PatientDao().getPatients();
					usersList = new MedicalUserDao().getMedicalUsers();
				}
			}
		}
		
		request.setAttribute("is_user", is_user);
		request.setAttribute("usersList", usersList);
		request.setAttribute("patientList", patientList);
		
		log.debug("Command \"RegistrationPatientCommand\" finished");
		return Path.PAGE__ADMIN_ADD_PATIENT;
	}

}
