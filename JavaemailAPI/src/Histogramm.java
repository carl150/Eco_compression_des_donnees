import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.mail.MessagingException;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.keypoint.PngEncoder;

import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;

public class Histogramm extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Histogramm frame = new Histogramm();
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
	public Histogramm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JComboBox Choix = new JComboBox();
		Choix.setBounds(164, 517, 89, 22);
		panel.add(Choix);

		JButton btnShow = new JButton("    Show ");
		JPanel pnhist = new JPanel();
		pnhist.setBackground(Color.WHITE);
		pnhist.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), new EtchedBorder(EtchedBorder.LOWERED, null, null)));
		pnhist.setBounds(23, 36, 1119, 462);
		panel.add(pnhist);
		pnhist.setLayout(new BoxLayout(pnhist, BoxLayout.X_AXIS));
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultCategoryDataset dcd=new DefaultCategoryDataset();
				ArrayList age = new ArrayList();
				try {
					age = Java_Email.stats("imap.gmail.com",Interface_gestion_mail.email.getText(),Interface_gestion_mail.password.getText());
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					System.out.println("problème de connexion");
				}
				Collections.sort(age,Collections.reverseOrder());
				ArrayList<long[]> f=Java_Email.frequence(age);
				for(int i=0;i<f.size();i++) {
					dcd.setValue(f.get(i)[1], "Nombre de mails",f.get(i)[0]+" mois");
				}
				JFreeChart Jchart=ChartFactory.createBarChart3D("histogramme des ages", "age des mails", "nombre de mails",dcd, PlotOrientation.VERTICAL, true,true, false);
				CategoryPlot plot=Jchart.getCategoryPlot();
				plot.setRangeGridlinePaint(Color.black);
				
				ChartFrame chartframe=new ChartFrame("Histogramme des ages", Jchart, true);
				//chartframe.setVisible(true);
				chartframe.setSize(800, 500);
				
				ChartPanel chartPanel =new ChartPanel(Jchart);
				
				pnhist.removeAll();
				pnhist.add(chartPanel);
				pnhist.updateUI();
				
			}
		});
		btnShow.setForeground(Color.DARK_GRAY);
		btnShow.setBounds(24, 517, 89, 23);
		panel.add(btnShow);
		
		

		
	}
}
