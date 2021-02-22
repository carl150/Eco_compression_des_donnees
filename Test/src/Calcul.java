import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;

import javax.mail.MessagingException;

public class Calcul {

	public static void main(String[] args) throws MessagingException, GeneralSecurityException {
		// TODO Auto-generated method stub
		ArrayList age= Java_Email.stats("imap.gmail.com","guehi102@gmail.com","elfried147258369","all");
		Collections.sort(age,Collections.reverseOrder());
		ArrayList<long[]> frequence=Java_Email.frequence(age);
		ArrayList <Double> proba=Java_Email.Entropie(frequence,frequence.size());
		System.out.println("l'entropie est :"+proba.get(proba.size()-1)+"\n"+"l'entropie maximale est: "+proba.get(proba.size()-3));
		double NDC=0;
		for (int i=0;i<frequence.size();i++){
			NDC=NDC+Math.pow((frequence.get(i)[1]/proba.get(proba.size()-3)),2);
		}
		System.out.println(NDC);
		System.out.println();
		NDC=Math.log(Math.pow((proba.get(proba.size()-3)),2)/NDC);
		System.out.println("la valeur de NDC est "+NDC);
	}

}
