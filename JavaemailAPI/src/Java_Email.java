import java.net.URL;
import java.security.GeneralSecurityException;
import java.time.temporal.ChronoUnit;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.FetchProfile;
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
import com.sun.mail.util.MailSSLSocketFactory;
import com.sun.mail.imap.IMAPFolder.FetchProfileItem;

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

		final String password="elfried147258369";

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
	public static ArrayList stats(String host,String user,String password) throws MessagingException, GeneralSecurityException {
		Properties properties = new Properties();
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
		sf.setTrustAllHosts(true); 
		properties.put("mail.imap.ssl.trust", "*");
		properties.put("mail.imap.ssl.socketFactory", sf);
		properties.put("mail.store.protocol", "imaps");
		properties.put("mail.imap.partialfetch",false);
		properties.put("mail.imap.fetchsize",10000000);

		ArrayList age=new ArrayList();

		Session emailSession = Session.getDefaultInstance(properties);
		Store store = emailSession.getStore("imaps");
		store.connect(host, user, password);

		Folder emailFolder = store.getFolder("INBOX");
		emailFolder.open(Folder.READ_ONLY);
		Message [] messages=emailFolder.getMessages();

		FetchProfile fp = new FetchProfile();
		fp.add(FetchProfile.Item.ENVELOPE);
		fp.add("X-mailer");
		emailFolder.fetch(messages, fp); // Load the profile of the messages in 1 fetch.
		System.out.println("pret a afficher les dates");

		for (int i=0;i<messages.length;i++) {
			LocalDate date=convertToLocalDate(messages[i].getReceivedDate());
			age.add(i,ChronoUnit.MONTHS.between(date,LocalDate.now()));

			System.out.println(ChronoUnit.MONTHS.between(date,LocalDate.now())/2);
		}

		return age;
	}

	public  static ArrayList frequence(ArrayList age) {
		ArrayList frequence= new ArrayList();
		ArrayList distinct=new ArrayList();
		for (int i=0;i<age.size();i++) {
			if (!distinct.contains(age.get(i))) {
				distinct.add(age.get(i));

			}
		}
		for (int j=0;j<distinct.size();j++) {
			long coordonnees[]=new long[2];
			coordonnees[0]=(long) distinct.get(j);
			coordonnees[1]=Collections.frequency(age, coordonnees[0]);
			frequence.add(coordonnees);
		}

		return frequence;
	}
	public static void options(String host,String user,String password,int time,boolean spam) throws MessagingException {
		Properties properties = new Properties();
		properties.put("mail.store.protocol", "imaps");
		properties.put("mail.imap.partialfetch",false);
		properties.put("mail.imap.fetchsize",100000000);
		Session emailSession = Session.getDefaultInstance(properties);
		Store store = emailSession.getStore("imaps");
		store.connect(host, user, password);

		Folder emailFolder = store.getFolder("INBOX");
		Folder spamFolder=store.getFolder("[Gmail]/Spam");
		emailFolder.open(Folder.READ_WRITE);
		spamFolder.open(Folder.READ_WRITE);
		Message [] messages=emailFolder.getMessages();
		Message [] msgs=spamFolder.getMessages();

		FetchProfile fp = new FetchProfile();
		fp.add(FetchProfile.Item.ENVELOPE);
		fp.add(FetchProfile.Item.FLAGS);
		fp.add("X-mailer");
		emailFolder.fetch(messages, fp); // Load the profile of the messages in 1 fetch.
		spamFolder.fetch(msgs, fp);
		System.out.println("pret");

		for (int i=0;i<messages.length;i++) {
			LocalDate date=convertToLocalDate(messages[i].getReceivedDate());
			if(ChronoUnit.MONTHS.between(date,LocalDate.now())>=time) {
				messages[i].setFlag(Flags.Flag.DELETED, true);
				//messages[i].saveChanges();
				System.out.println("ok");
			}
			else {
				break;
			}

		}
		if(spam==true) {
			for(Message message: msgs) {
				message.setFlag(Flags.Flag.SEEN, true);
			}
		}

		emailFolder.close(true);
		spamFolder.close(true);
	}

public static  ArrayList Entropie(ArrayList<long[]> frequence,int index) {
	ArrayList proba=new ArrayList();
	double total=0;
	for (int i=0;i<index;i++) {
		total=total+frequence.get(i)[1];
	}	
	double entropie=0;
	for (int i=0;i<index;i++) {
		proba.add(frequence.get(i)[1]/total);
		entropie=entropie+(frequence.get(i)[1]/total)*Math.log(frequence.get(i)[1]/total)/Math.log(2);
		
	}
	proba.add(total);
	proba.add(Math.log(total)/Math.log(2));
	proba.add(-entropie);
	return proba;
	
}
}
