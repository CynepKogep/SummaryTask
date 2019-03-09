package ua.kharkov.khpi.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public class SendMail {
	
	private static final Logger log = Logger.getLogger(SendMail.class);

	
	public static void SendMailToPatient(String email, String messageToUser) {
		log.trace("Command start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,	new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("testsummarytask@gmail.com","cfknjdrf1977");
				}
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("testsummarytask@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,	InternetAddress.parse(email));
			message.setSubject("Discharging information");
			message.setText(messageToUser);

			Transport.send(message);
			log.debug("Message send to patient: " + email);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		log.trace("Commands end");
	}
	
//    public static void SendMailTo(String email, String messageToUser){
//    	log.trace("Command start");
//    	
//        String to   = "testsummarytask@gmail.com";         // sender email
//        String from = "testsummarytask@gmail.com";         // receiver email
//        String host = "127.0.0.1";            // mail server host
//
//        Properties properties = System.getProperties();
//        properties.setProperty("mail.smtp.host", host);
//
//        Session session = Session.getDefaultInstance(properties); // default session
//
//        try {
//             MimeMessage message = new MimeMessage(session); // email message
//             message.setFrom(new InternetAddress(from));     // setting header fields
//             message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//             message.setSubject("Test Mail from Java Program"); // subject line
//
//             // actual mail body
//             message.setText(messageToUser);
//
//             // Send message
//             Transport.send(message); 
//             System.out.println("Email Sent successfully....");
//            } catch (MessagingException mex){ 
//            	mex.printStackTrace(); 
//            }
//    }

}
    
    
    
	
