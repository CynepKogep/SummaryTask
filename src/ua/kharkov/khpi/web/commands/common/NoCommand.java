package ua.kharkov.khpi.web.commands.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.kharkov.khpi.constants.Path;
import ua.kharkov.khpi.web.commands.general.Command;

/**
 * No command.
 * 
 * @author D.Belozerov
 * 
 */
public class NoCommand extends Command {

	private static final long serialVersionUID = -2785976616686657267L;
	private static final Logger log = Logger.getLogger(NoCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	
		log.debug("Command \"NoCommand\" starts");
		
		String errorMessage = "No such command";
		request.setAttribute("errorMessage", errorMessage);
		log.error("Set the request attribute: errorMessage --> " + errorMessage);

		log.debug("Command \"NoCommand\" finished");
		return Path.PAGE__ERROR_PAGE;
	}
}