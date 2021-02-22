import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.mail.MessagingException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;

public class Compression_courbe extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Compression_courbe frame = new Compression_courbe();
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
	public Compression_courbe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 512, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 466, 273);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("Show");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				XYSeries courbe=new XYSeries("evolution de la compression");
				try {
					ArrayList age = Java_Email.stats("imap.gmail.com",Interface_gestion_mail.email.getText(),Interface_gestion_mail.password.getText(),(String)Histogramm.name.getSelectedItem());
					Collections.sort(age,Collections.reverseOrder());
					ArrayList<long[]> f=Java_Email.frequence(age);
					for(int i=0;i<f.size();i++) {
						
					}
				} catch (MessagingException | GeneralSecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(192, 295, 89, 23);
		panel.add(btnNewButton);
	}
}
