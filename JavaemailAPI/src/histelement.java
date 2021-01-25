
public class histelement {
public String mois;
public String nb_emails;
public double entropie;
public double entropie_max;
public histelement(String mois, String nb_emails, double entropie, double entropie_max) {
	super();
	this.mois = mois;
	this.nb_emails = nb_emails;
	this.entropie = entropie;
	this.entropie_max = entropie_max;
}
public String getMois() {
	return mois;
}
public void setMois(String mois) {
	this.mois = mois;
}
public String getNb_emails() {
	return nb_emails;
}
public void setNb_emails(String nb_emails) {
	this.nb_emails = nb_emails;
}
public double getEntropie() {
	return entropie;
}
public void setEntropie(double entropie) {
	this.entropie = entropie;
}
public double getEntropie_max() {
	return entropie_max;
}
public void setEntropie_max(double entropie_max) {
	this.entropie_max = entropie_max;
}

}
