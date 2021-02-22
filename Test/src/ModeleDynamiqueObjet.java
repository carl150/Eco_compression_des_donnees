import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.mail.MessagingException;
import javax.swing.table.AbstractTableModel;

public class  ModeleDynamiqueObjet extends AbstractTableModel {
	public List<histelement> histelements = new ArrayList<histelement>();
	 
 
    public String[] entetes = {"Mois", "Nb_emails", "Entropie", "Entropie_max"};
 
  
 
    public ModeleDynamiqueObjet() {
		super();
		        ArrayList age = null; 
				try {
					age = Java_Email.stats("imap.gmail.com",Interface_gestion_mail.email.getText(),Interface_gestion_mail.password.getText(),(String)Histogramm.name.getSelectedItem());
				} catch (MessagingException | GeneralSecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Collections.sort(age,Collections.reverseOrder());
				ArrayList<long[]> frequence=Java_Email.frequence(age);
				for(int i=0;i<=frequence.size()-1;i++) {
					ArrayList proba=Java_Email.Entropie(frequence,i+1);
					histelement hist= new histelement(""+frequence.get(i)[0],""+proba.get(proba.size()-3),(double)proba.get(proba.size()-1),(double)proba.get(proba.size()-2));
					histelements.add(hist);
				}
		 
		        
		    }

	public int getRowCount() {
        return histelements.size();
    }
 
    public int getColumnCount() {
        return entetes.length;
    }
 
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }
 
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return histelements.get(rowIndex).getMois();
            case 1:
                return histelements.get(rowIndex).getNb_emails();
            case 2:
                return histelements.get(rowIndex).getEntropie();
            case 3:
                return histelements.get(rowIndex).getEntropie_max();
            default:
                return null; //Ne devrait jamais arriver
        }
    }
}