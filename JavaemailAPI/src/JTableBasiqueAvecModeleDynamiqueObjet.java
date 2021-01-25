import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class JTableBasiqueAvecModeleDynamiqueObjet extends JFrame {
    public ModeleDynamiqueObjet modele = new ModeleDynamiqueObjet();
    public JTable tableau;
    public JTableBasiqueAvecModeleDynamiqueObjet() {
        super();
 
        setTitle("JTable avec modèle dynamique");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
 
        tableau = new JTable(modele);
 
        JScrollPane scrollPane = new JScrollPane(tableau);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        pack();
    }
 
    public static void main(String[] args) {
        new JTableBasiqueAvecModeleDynamiqueObjet().setVisible(true);
    }
}