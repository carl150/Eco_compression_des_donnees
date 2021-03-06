import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.mail.MessagingException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class Interface_gestion_mail extends JFrame {

	private JPanel contentPane;
	public static JTextField email;
	private JTextArea infos;
	public static JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface_gestion_mail frame = new Interface_gestion_mail();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Interface_gestion_mail() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 739, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane txtpnEmail = new JTextPane();
		txtpnEmail.setEditable(false);
		txtpnEmail.setBackground(UIManager.getColor("control"));
		txtpnEmail.setText("Email");
		txtpnEmail.setBounds(28, 37, 44, 20);
		contentPane.add(txtpnEmail);
		
		email = new JTextField();
		email.setBounds(123, 37, 282, 25);
		email.setName("email");
		contentPane.add(email);
		email.setColumns(10);
		
		JTextPane txtpnMotDePasse = new JTextPane();
		txtpnMotDePasse.setEditable(false);
		txtpnMotDePasse.setBackground(UIManager.getColor("control"));
		txtpnMotDePasse.setText("Mot de passe");
		txtpnMotDePasse.setBounds(28, 96, 85, 20);
		contentPane.add(txtpnMotDePasse);
		
		infos = new JTextArea();
		infos.setEditable(false);
		infos.setBounds(28, 188, 669, 192);
		contentPane.add(infos);
		
		password = new JPasswordField();
		password.setBounds(123, 96, 282, 25);
		contentPane.add(password);
		
		
		JButton btnInfos = new JButton("Infos");
		btnInfos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				infos.setText("");
				try {
					int[] valeurs=Java_Email.fetchMessages("imap.gmail.com",email.getText(),password.getText(), false);
					String s="\n"+"\n"+"  -le nombre total de messages pr�sent dans la boite de r�ception est "+valeurs[0]+"\n"+
							" -le nombre de messages suprim�s pr�sent dans la corbeille  est "+valeurs[1]+"\n"+
							" -le nombre de messages pr�sent dans le spam  est "+valeurs[2]+"\n"+
							" -le nombre total de messages non lus dans la boite de r�ception est "+valeurs[3]+"\n"+
							" -le nombre total de messages lu dans la boite de r�ception est "+valeurs[4]+"\n"+
							" "+"-"+Java_Email.Quotas(email.getText(),password.getText());
					infos.append(s);
				} catch (MessagingException e1) {
					String s="\n"+"\n"+"  probl�me de connexion.veuillez v�rifier que votre connexion est bien active ou que vous avez mis le bon mot de "+"\n"+" passe ou email";
					infos.append(s);
					;
				}//affiche les informations importantes de la boite mail
			}
		});
		btnInfos.setBounds(290, 144, 89, 33);
		contentPane.add(btnInfos);
		
		JButton Histogramme = new JButton("Histogramme");
		Histogramme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Histogramm frame1 = new Histogramm();
				frame1.setVisible(true);
			}
		});
		Histogramme.setBounds(290, 391, 115, 33);
		contentPane.add(Histogramme);
		
		JButton btnOptions = new JButton("Options");
		btnOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Options frame = new Options();
				frame.setVisible(true);
			}
		});
		btnOptions.setBounds(290, 457, 115, 33);
		contentPane.add(btnOptions);
		
		JButton btnNewButton = new JButton("Entropie");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new JTableBasiqueAvecModeleDynamiqueObjet().setVisible(true);
			}
		});
		btnNewButton.setBounds(290, 527, 115, 33);
		contentPane.add(btnNewButton);
		
		
		
	}
}
