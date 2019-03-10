package ua.kharkov.khpi.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
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
	
//	// TEST TLS
//    public static void SendMailToPatientTLS(){
//	    
//    	final String fromEmail = "testsummarytask@gmail.com"; //requires valid gmail id
//	    final String password = "cfknjdrf1977"; // correct password for gmail id
//	    final String toEmail = "testsummarytask@gmail.com"; // can be any email id 
//	
//	    System.out.println("TLSEmail Start");
//	    Properties props = new Properties();
//	    props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
//	    props.put("mail.smtp.port", "587"); //TLS Port
//	    props.put("mail.smtp.auth", "true"); //enable authentication
//	    props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
//	
//        //create Authenticator object to pass in Session.getInstance argument
//	    Authenticator auth = new Authenticator() {
//		    //override the getPasswordAuthentication method
//		    protected PasswordAuthentication getPasswordAuthentication() {
//			    return new PasswordAuthentication(fromEmail, password);
//		    }
//	   };
//	   Session session = Session.getInstance(props, auth);
//       sendEmail(session, toEmail,"TLSEmail Testing Subject", "TLSEmail Testing Body");
//    }
//	
//	public static void sendEmail(Session session, String toEmail, String subject, String body){
//		System.out.println("Start!");
//		try
//	    {
//	      MimeMessage msg = new MimeMessage(session);
//	      //set message headers
//	      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
//	      msg.addHeader("format", "flowed");
//	      msg.addHeader("Content-Transfer-Encoding", "8bit");
//
//	      msg.setFrom(new InternetAddress("no_reply@example.com", "NoReply-JD"));
//	      msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));
//	      msg.setSubject(subject, "UTF-8");
//	      msg.setText(body, "UTF-8");
//	      msg.setSentDate(new Date());
//	      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
//	      System.out.println("Message is ready");
//    	  Transport.send(msg);  
//	      System.out.println("EMail Sent Successfully!!");
//	    }
//	    catch (Exception e) {
//	      e.printStackTrace();
//	    }
//	}

}
    
    
    
	
