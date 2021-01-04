import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

import javax.mail.*;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.AddressException;

import com.sun.mail.imap.IMAPFolder.FetchProfileItem;

public class Javamail {

	public static void main(String[] args) throws AddressException, MessagingException, IOException {
		//		Java_Email.sendEmail("guehi102@gmail.com");//permet d'envoyer des emails
		//int[] valeurs=Java_Email.fetchMessages("imap.gmail.com","guatrobin@gmail.com","elfried147258369", false);//affiche les informations importantes de la boite mail
		//	//System.out.println(message[1]);
		//String result=Java_Email.Quotas("guatrobin@gmail.com","elfried147258369");//affiche le stockage actuel(utlisé ou pas utilisé);
		//System.out.println(result);
		
		//ArrayList age= Java_Email.stats("imap.gmail.com","guehi102@gmail.com","elfried147258369");
		//Java_Email.options("imap.gmail.com","guatrobin@gmail.com","elfried147258369",9);
		System.out.println(Math.round(3/2+0.5));
		//	Collections.sort(age,Collections.reverseOrder());
		//System.out.println("la taille de age est "+age.size()+" ,"+age.get(0));










//		ArrayList<Integer> aList = new ArrayList<Integer>();
//
//		//remplir ArrayList avec des String
//		aList.add(1);
//		aList.add(1);
//		aList.add(3);
//		aList.add(3);
//		aList.add(4);
//		aList.add(5);
//		aList.add(10);
//		ArrayList<int[]> f=Java_Email.frequence(aList);
//		for( int i=0;i<f.size();i++) {
//			System.out.print(f.get(i)[0]+" "+f.get(i)[1]);
//			System.out.println();
//		}
//		Properties properties = new Properties();
//		properties.put("mail.store.protocol", "imaps");
//		Session emailSession = Session.getDefaultInstance(properties);
//		Store store = emailSession.getStore("imaps");
//		store.connect("imap.gmail.com","guatrobin@gmail.com","elfried147258369");
//
//		Folder emailFolder = store.getFolder("INBOX");
//		emailFolder.open(Folder.READ_ONLY);
//		Message [] messages=emailFolder.getMessages();
//		FetchProfile fp = new FetchProfile();
//	       fp.add(FetchProfile.Item.ENVELOPE);
//	       fp.add(FetchProfileItem.FLAGS);
//	       fp.add(FetchProfileItem.CONTENT_INFO);
//
//	       fp.add("X-mailer");
//	       emailFolder.fetch(messages, fp); // Load the profile of the messages in 1 fetch.
//	       for (Message message: messages) {
//	          syso; //Subject is already local, no additional fetch required
//	       }

	}
}
