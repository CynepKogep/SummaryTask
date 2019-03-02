package ua.kharkov.khpi.web.commands.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.kharkov.khpi.constants.Path;
import ua.kharkov.khpi.web.commands.general.Command;

/**
 * View settings command.
 * 
 * @author D.Belozerov
 * 
 */
public class ViewSettingsCommand extends Command {
	
	private static final long serialVersionUID = -3071536593627692473L;
	private static final Logger log = Logger.getLogger(ViewSettingsCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)	throws IOException, ServletException {		
		log.debug("Command \"ViewSettingsCommand\" starts");
		
		log.debug("Command \"ViewSettingsCommand\" finished");
		return Path.PAGE__SETTINGS;
	}

}