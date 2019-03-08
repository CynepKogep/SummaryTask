package ua.kharkov.khpi.web.commands.general;

import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import ua.kharkov.khpi.web.commands.admin.ListMedicalUserCommand;
import ua.kharkov.khpi.web.commands.admin.ListPatientCommand;
import ua.kharkov.khpi.web.commands.admin.RegistrationMedicalUserCommand;
import ua.kharkov.khpi.web.commands.admin.RegistrationPatientCommand;
import ua.kharkov.khpi.web.commands.common.LoginCommand;
import ua.kharkov.khpi.web.commands.common.LogoutCommand;
import ua.kharkov.khpi.web.commands.common.NoCommand;
import ua.kharkov.khpi.web.commands.common.UpdateSettingsCommand;
import ua.kharkov.khpi.web.commands.common.ViewSettingsCommand;
import ua.kharkov.khpi.web.commands.doctor.CompleteAssignmentDoctorCommand;
import ua.kharkov.khpi.web.commands.doctor.DischargePatientDoctorCommand;
import ua.kharkov.khpi.web.commands.doctor.ListPatientForDoctorCommand;
import ua.kharkov.khpi.web.commands.doctor.MakeAnAssignmentDoctorCommand;
import ua.kharkov.khpi.web.commands.doctor.PatientCardDoctorCommand;
import ua.kharkov.khpi.web.commands.doctor.SetDiagnosisDoctorCommand;
import ua.kharkov.khpi.web.commands.nurse.CompleteAssignmentNurseCommand;
import ua.kharkov.khpi.web.commands.nurse.ListPatientForNurseCommand;
import ua.kharkov.khpi.web.commands.nurse.MakeAnAssignmentNurseCommand;
import ua.kharkov.khpi.web.commands.nurse.PatientCardNurseCommand;

/**
 * Holder for all commands.
 * 
 */
public class CommandContainer {
	
	private static final Logger log = Logger.getLogger(CommandContainer.class);
	private static Map<String, Command> commands = new TreeMap<String, Command>();
	
	static {
		// common commands
		commands.put("login", new LoginCommand());
		commands.put("logout", new LogoutCommand()); 
		commands.put("noCommand", new NoCommand());
		commands.put("viewSettings", new ViewSettingsCommand());
		commands.put("updateSettings", new UpdateSettingsCommand()); 
		// admin commands
		commands.put("listMedicalUser", new ListMedicalUserCommand());
		commands.put("listPatient", new ListPatientCommand());
		commands.put("RegistrationMedicalUser", new RegistrationMedicalUserCommand());
		commands.put("RegistrationPatient", new RegistrationPatientCommand());
		// doctor commands
		commands.put("listPatientForDoctor", new ListPatientForDoctorCommand());
		commands.put("patientCardDoctor", new PatientCardDoctorCommand());
		commands.put("setDiagnosisDoctor", new SetDiagnosisDoctorCommand());
		commands.put("makeAssignmentDoctor", new MakeAnAssignmentDoctorCommand()); // назначение
		commands.put("completeAssignmentDoctor", new CompleteAssignmentDoctorCommand());
		commands.put("dischargedPatientDoctor", new DischargePatientDoctorCommand());
		// nurse commands
		commands.put("listPatientForNurse", new ListPatientForNurseCommand());
		commands.put("patientCardNurse", new PatientCardNurseCommand());
		commands.put("makeAssignmentNurse", new MakeAnAssignmentNurseCommand());
		commands.put("completeAssignmentNurse", new CompleteAssignmentNurseCommand());
		
		log.debug("Command container was successfully initialized");
		log.debug("Number of commands --> " + commands.size());
	}

	/**
	 * Returns command object with the given name.
	 * @param commandName - Name of the command. 
	 * @return Command object. 
	 */
	public static Command get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			log.trace("Command not found, name --> " + commandName);
			return commands.get("noCommand"); 
		}
		
		log.debug("commandName: " + commandName);
		return commands.get(commandName);
	}
	
}