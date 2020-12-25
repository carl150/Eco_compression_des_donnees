import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class Javamail {

	public static void main(String[] args) throws AddressException, MessagingException {
		//		Java_Email.sendEmail("guehi102@gmail.com");//permet d'envoyer des emails
		//int[] valeurs=Java_Email.fetchMessages("imap.gmail.com","guatrobin@gmail.com","elfried147258369", false);//affiche les informations importantes de la boite mail
		//	//System.out.println(message[1]);
		//String result=Java_Email.Quotas("guatrobin@gmail.com","elfried147258369");//affiche le stockage actuel(utlisé ou pas utilisé);
		ArrayList age= Java_Email.stats("imap.gmail.com","xxxxxxxxxxxxxxx","xxxxxxx");
		//	Collections.sort(age,Collections.reverseOrder());
		//	System.out.println("la taille de age est "+age.size()+" ,"+age.get(0));










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
		

	}
}
