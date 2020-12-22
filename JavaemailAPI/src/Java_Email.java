import java.net.URL;
import java.time.temporal.ChronoUnit;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.search.FlagTerm;

import com.sun.mail.imap.IMAPStore;

import javax.mail.Quota;

public class Java_Email
{

	public static void sendEmail(String recipient) throws AddressException, MessagingException {
		System.out.println("preparing to send email");

		Properties properties=new Properties();

		properties.put("mail.smtp.auth","true");

		properties.put("mail.smtp.starttls.enable","true");

		properties.put("mail.smtp.host","smtp.gmail.com");

		properties.put("mail.smtp.port","587");

		//		properties.setProperty("mail.store.protocol", "pop3");
		//		properties.setProperty("mail.pop3.host", "pop.gmail.com");
		//		properties.setProperty("mail.pop3.port","995");
		//		properties.setProperty("mail.pop3.auth", "true");
		//		properties.setProperty( "mail.pop3.socketFactory.class",
		//				"javax.net.ssl.SSLSocketFactory" ); 
		//
		//
		final String myAccountEmail="guatrobin@gmail.com";

		final String password="xxxxxxxxxxxx";

		//URLName rep_Serveur = new URLName("pop3://guatrobin@pop.gmail.com/Inbox");
		Session session= Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccountEmail, password);
			}

		});
		Message message=prepareMessage(session,myAccountEmail,recipient);
		System.out.println("message sent successfully");

	}

	public static  Message prepareMessage(Session session,String username,String recepient) throws AddressException, MessagingException {

		Message message=new MimeMessage(session);

		message.setFrom(new InternetAddress(username));
		message.setRecipient(Message.RecipientType.TO,new InternetAddress(recepient));
		message.setSubject("my first email from javamail");
		message.setText("hey i am carl nice to meet you");
		return message;


	}
	public static int[] fetchMessages(String host, String user, String password, boolean read) throws MessagingException {
		int [] valeurs =new int[5];
		Properties properties = new Properties();
		properties.put("mail.store.protocol", "imaps");

		Session emailSession = Session.getDefaultInstance(properties);
		Store store = emailSession.getStore("imaps");
		store.connect(host, user, password);

		Folder emailFolder = store.getFolder("INBOX");
		emailFolder.open(Folder.READ_ONLY);
		LocalDate date=convertToLocalDate(emailFolder.getMessages()[1].getReceivedDate());
		System.out.println(ChronoUnit.DAYS.between(date,LocalDate.now()));
		valeurs[0]=emailFolder.getMessageCount();
		Folder corbeille = store.getFolder("[Gmail]/Corbeille");
		valeurs[1]=corbeille.getMessageCount();
		//System.out.println("le nombre de messages suprimés présent dans la corbeille  est "+corbeille.getMessageCount());

		Folder spams = store.getFolder("[Gmail]/Spam");
		valeurs[2]=spams.getMessageCount();
		//		System.out.println("le nombre de messages présent dans le spam  est "+spams.getMessageCount());
		//		
		//		System.out.println("le nombre total de messages présent dans la boite de réception est "+emailFolder.getMessageCount());
		//		// use READ_ONLY if you don't wish the messages
		//		// to be marked as read after retrieving its content
		//		System.out.println("le nombre total de messages non lus dans la boite de réception est "+emailFolder.getUnreadMessageCount());
		valeurs[3]=emailFolder.getUnreadMessageCount();
		int nb=emailFolder.getMessageCount()-emailFolder.getUnreadMessageCount();
		valeurs[4]=nb;
		//System.out.println("le nombre total de messages lu dans la boite de réception est "+nb);


		return valeurs;

	}
	public static float cacul_entropie(int[]valeurs) {
		float H=(float) (valeurs[1]*(valeurs[1]/valeurs[0])*Math.log(valeurs[1]/valeurs[0])+valeurs[2]*(valeurs[2]/0));
		return H;

	}
	public static String Quotas(String myAccountEmail,String password) throws MessagingException {
		String result ="";
		Properties properties = new Properties();
		properties.put("mail.store.protocol", "imaps");
		properties.put("mail.imaps.port", "993");
		properties.put("mail.imaps.starttls.enable", "true");
		Session emailSession = Session.getDefaultInstance(properties);
		// emailSession.setDebug(true);

		// create the IMAP3 store object and connect with the pop server
		Store store = emailSession.getStore("imaps");

		//change the user and password accordingly
		store.connect("imap.gmail.com", myAccountEmail,password);
		IMAPStore imapStore = (IMAPStore) store;
		//System.out.println("imapStore ---" + imapStore);

		//get quota
		Quota[] quotas = imapStore.getQuota("INBOX");
		NumberFormat format=NumberFormat.getInstance();
		format.setMaximumFractionDigits(2);
		//Iterate through the Quotas
		for (Quota quota : quotas) {
			System.out.println(String.format("quotaRoot:'%s'",
					quota.quotaRoot));
			//Iterate through the Quota Resource
			for (Quota.Resource resource : quota.resources) {
				result=String.format(
						"Stockage , espace total:'%s', espace utilisé:'%s'",
						format.format((double)resource.limit/1000000)+" Go", format.format((double)resource.usage/1000000)+" Go");
			}
		}
		return result;
	}
	public static LocalDate convertToLocalDate(Date dateToConvert) {
		return dateToConvert.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDate();
	}
	public static ArrayList stats(String host,String user,String password) throws MessagingException {
		Properties properties = new Properties();
		properties.put("mail.store.protocol", "imaps");

		ArrayList age=new ArrayList();

		Session emailSession = Session.getDefaultInstance(properties);
		Store store = emailSession.getStore("imaps");
		store.connect(host, user, password);

		Folder emailFolder = store.getFolder("INBOX");
		emailFolder.open(Folder.READ_ONLY);
		Message [] messages=emailFolder.getMessages();
		for (int i=0;i<messages.length;i++) {
			LocalDate date=convertToLocalDate(messages[i].getReceivedDate());
			age.add(i,ChronoUnit.MONTHS.between(date,LocalDate.now()));
		}
		return age;
	}
}
