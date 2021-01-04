import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.mail.MessagingException;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Options extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Options frame = new Options();
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
	public Options() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 582, 416);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JCheckBox chckbxSupprimerLesMessages = new JCheckBox("Supprimer les messages contenus dans le dossier spam");
		chckbxSupprimerLesMessages.setVerticalAlignment(SwingConstants.TOP);
		chckbxSupprimerLesMessages.setToolTipText("");
		chckbxSupprimerLesMessages.setBounds(6, 46, 412, 23);
		panel.add(chckbxSupprimerLesMessages);
		
		JCheckBox chckbxSuprimerLesMessagesagesages = new JCheckBox("Suprimer les messages les plus ag\u00E9s");
		chckbxSuprimerLesMessagesagesages.setBounds(6, 95, 289, 23);
		panel.add(chckbxSuprimerLesMessagesagesages);
		

		JTextArea textPane = new JTextArea();
		textPane.setEditable(false);
		textPane.setBounds(10, 206, 536, 150);
		panel.add(textPane);
		
		textField = new JTextField();
		textField.setBounds(334, 96, 70, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxSuprimerLesMessagesagesages.isSelected()==true) {
					try {
						Java_Email.options("imap.gmail.com", Interface_gestion_mail.email.getText(), Interface_gestion_mail.password.getText(), Integer.parseInt(textField.getText()), false);
					} catch (NumberFormatException | MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(chckbxSupprimerLesMessages.isSelected()==true) {
					try {
						Java_Email.options("imap.gmail.com", Interface_gestion_mail.email.getText(), Interface_gestion_mail.password.getText(), Integer.MAX_VALUE, true);
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				textPane.setText("");
				try {
					int[] valeurs=Java_Email.fetchMessages("imap.gmail.com",Interface_gestion_mail.email.getText(),Interface_gestion_mail.password.getText(), false);
					String s="\n"+"\n"+"  -le nombre total de messages présent dans la boite de réception est "+valeurs[0]+"\n"+
							" -le nombre de messages suprimés présent dans la corbeille  est "+valeurs[1]+"\n"+
							" -le nombre de messages présent dans le spam  est "+valeurs[2]+"\n"+
							" -le nombre total de messages non lus dans la boite de réception est "+valeurs[3]+"\n"+
							" -le nombre total de messages lu dans la boite de réception est "+valeurs[4]+"\n"+
							" "+"-"+Java_Email.Quotas(Interface_gestion_mail.email.getText(),Interface_gestion_mail.password.getText());
					textPane.append(s);
				} catch (MessagingException e1) {
					String s="\n"+"\n"+"  problème de connexion.veuillez vérifier que votre connexion est bien active"+"\n";
					textPane.append(s);
					;
				}//affiche les informations importantes de la boite mail
			}
		});
		btnEnregistrer.setBounds(224, 154, 105, 30);
		panel.add(btnEnregistrer);
		
		
		
	}
}
