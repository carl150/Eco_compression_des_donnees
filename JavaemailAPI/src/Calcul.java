import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;

import javax.mail.MessagingException;

public class Calcul {

	public static void main(String[] args) throws MessagingException, GeneralSecurityException {
		// TODO Auto-generated method stub
		ArrayList age= Java_Email.stats("imap.gmail.com","guatrobin@gmail.com","elfried147258369");
		Collections.sort(age,Collections.reverseOrder());
		ArrayList<long[]> frequence=Java_Email.frequence(age);
		ArrayList proba=Java_Email.Entropie(frequence,8);
		System.out.println("l'entropie est :"+proba.get(proba.size()-1)+"\n"+"l'entropie maximale est: "+proba.get(proba.size()-2));
	}

}
