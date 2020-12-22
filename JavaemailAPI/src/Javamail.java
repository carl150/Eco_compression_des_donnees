import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class Javamail {

	public static void main(String[] args) throws AddressException, MessagingException {
//		//Java_Email.sendEmail("guehi102@gmail.com");//permet d'envoyer des emails
		//int[] valeurs=Java_Email.fetchMessages("imap.gmail.com","guatrobin@gmail.com","xxxxxxxxxx", false);//affiche les informations importantes de la boite mail
//	//System.out.println(message[1]);
//		String result=Java_Email.Quotas("guatrobin@gmail.com","xxxxxxxxxx");//affiche le stockage actuel(utlisé ou pas utilisé);
	ArrayList age= Java_Email.stats("imap.gmail.com","guehi102@gmail.com","xxxxxxxx");
	Collections.sort(age);
	System.out.println("la taille de age est "+age.size()+" ,"+age.get(385));
		
		
}
}
