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
		
		
//		commands.put("AddMedicalUser", new AddMedicalUserCommand());
		
//		commands.put("login", new LoginCommand());
//		commands.put("loginA", new LoginCommandA());
//		commands.put("logout", new LogoutCommand());
//		commands.put("noCommand", new NoCommand());
//		commands.put("viewSettings", new ViewSettingsCommand());
//		commands.put("updateSettings", new UpdateSettingsCommand());
//		commands.put("registration", new RegistrationCommand());
		// client commands
//		commands.put("listMenu", new ListMenuCommand());
//		commands.put("listClientCards", new CardsClientCommand());
//		commands.put("listClientPays", new PaysClientCommand());
//		commands.put("ClientCreatePay", new ClientCreatePayCommand());
		// admin commands
//		commands.put("listOrders", new ListOrdersCommand());
//		commands.put("listOrdersA", new ListOrdersCommandA());
//		commands.put("cardsAdminCommand", new CardsAdminCommand());
//		commands.put("AdminCreateCard", new AdminCreateCardCommand());
		
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