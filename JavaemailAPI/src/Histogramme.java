
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;



/**
 * <p>Classe représentant un histogramme.</p>
 * @author Sébastien ESTIENNE.
 */
public class Histogramme extends JFrame
{
   /**
    * <p>Serial version UID.</p>
    */
   private static final long serialVersionUID = 1L;

   /** Valeurs de l'histogramme. */
   private int[] valeurs;

   /** Valeur maximum. */
   private int max;

   /** Couleurs de barres de l'histogramme. */
   private Color[] couleurs;

   /** Décalage en X du graphique par rapport à la gauche de la fenêtre. */
   private static final int DEC_X = 40;
   
   /** Décalage en Y du graphique par rapport au bas de la fenêtre. */
   private static final int DEC_Y = 40;
   
   /** Décalage en X du texte au-dessus des barres de l'histogramme. */
   private static final int DEC_TX = DEC_X + 5;
   
   /** Décalage en Y du texte au-dessus des barres de l'histogramme. */
   private static final int DEC_TY = DEC_Y + 2;
   
   /** Décalage en hauteur de la ligne permettant de créer la flèche. */
   private static final int DEC_FH = 4;
   
   /** Décalage en longueur de la ligne permettant de créer la flèche. */
   private static final int DEC_FL = 8;
   
   /** Largeur d'une barre de l'histogramme. */
   private static final int LG_B = 30;
   
   /** Incrément pour calculer la hauteur des barres de l'histogramme en fonction de la valeur. */
   private static final int INCR = 10;

   /**
    * <p>Constructeur pour l'histogramme.</p>
    */
   public Histogramme()
   {
      super("Histogramme");

      // Initialisation des valeurs.
      this.valeurs = new int[6];
      this.valeurs[0] = 12;
      this.valeurs[1] = 10;
      this.valeurs[2] = 17;
      this.valeurs[3] = 5;
      this.valeurs[4] = 13;
      this.valeurs[5] = 8;
      this.max = 20;

      // Initialisation des couleurs.
      this.couleurs = new Color[6];
      this.couleurs[0] = Color.RED;
      this.couleurs[1] = Color.GREEN;
      this.couleurs[2] = Color.BLUE;
      this.couleurs[3] = Color.YELLOW;
      this.couleurs[4] = Color.MAGENTA;
      this.couleurs[5] = new Color(255, 102, 0);

      // Propriétés de la fenêtre.
      setLocation(50, 50);
      setSize(350, 300);
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }


   /**
    * <p>Dessine la fenêtre.</p>
    * @param g Le contexte graphique utilisé pour dessiner.
    */
   @Override
   public void paint(Graphics g)
   {
      // Déclaration des variables utiles pour les calculs de l'histogramme.
      int x, y, x1, y1, x2, y2, largeur, hauteur;
      
      // Affichage des barres.
      for(int i = 0; i < this.valeurs.length; i++)
      {
         // Barre.
         x = DEC_X + i * (LG_B + 1);
         y = getHeight() - DEC_Y - this.valeurs[i] * INCR;
         largeur = LG_B;
         hauteur = this.valeurs[i] * INCR;
         g.setColor(this.couleurs[i]);
         g.fillRect(x, y, largeur, hauteur);
         
         // Valeur.
         x = DEC_TX + i * (LG_B + 1);
         y = getHeight() - DEC_TY - this.valeurs[i] * INCR;
         g.setColor(Color.BLACK);
         g.drawString("" + this.valeurs[i], x, y);
      }

      // Affichage de l'axe X.
      g.setColor(Color.BLACK);
      x1 = DEC_X;
      y1 = getHeight() - DEC_Y;
      x2 = x1 + this.valeurs.length * LG_B + LG_B;
      y2 = y1;
      g.drawLine(x1, y1, x2, y2);
      g.drawLine(x2, y2, x2 - DEC_FL, y2 - DEC_FH);
      g.drawLine(x2, y2, x2 - DEC_FL, y2 + DEC_FH);
      
      // Affichage de l'axe Y.
      x1 = DEC_X;
      y1 = getHeight() - DEC_Y;
      x2 = x1;
      y2 = y1 - this.max * INCR;
      g.drawLine(x1, y1, x2, y2);
      g.drawLine(x2, y2, x2 - DEC_FH, y2 + DEC_FL);
      g.drawLine(x2, y2, x2 + DEC_FH, y2 + DEC_FL);
   }


   /**
    * <p>Commence l'exécution du programme.</p>
    * @param args Les paramètres de la ligne de commande.
    */
   public static void main(String[] args)
   {
      new Histogramme();
   }
}