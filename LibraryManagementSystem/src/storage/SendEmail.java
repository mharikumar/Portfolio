/*
Project Team J (COEN 275)
Name : Meenakshy Harikumar	SCU ID : 1001341
Name: Nidhi Singh			SCU ID : 1027035

Date of submission: 9/05/2013
*/
package storage;


import java.util.Properties;
 
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class SendEmail
{

	final static String username = "bookmarkerslib@gmail.com";
	final static String password = "admin!@#";
	static Session session;
	static{
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.starttls.required", "true"); // added this line
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		
		  session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		
	}
	
public  boolean sendEmailNotification(String receipientname, String itemName, String receipentemailId)
{    
	 
	
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("bookmarkerslib@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(receipentemailId));
			message.setSubject("Book Available");
			message.setText("Dear " + receipientname +","
				+ "\n\n The item " + itemName + " you reserved is available!"
				+ "\n \nThanks,"
				+ "\n BookMarkers Library"); 
			 
			Transport.send(message);
 
			System.out.println("Done");
			// TODO(Nidhi): create a joptionpane and display successfully sent
		
		} catch (MessagingException e) {
			return false;
		}
		return true;
	}


}