package serie08;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JComponent;

import util.Contract;

public class GraphicSpeedometer extends JComponent {
	
    // marge horizontale interne de part et d'autre du composant
    private static final int MARGIN = 40;
    // épaisseur de la ligne horizontale graduée
    private static final int THICK = 3;
    // demi-hauteur d'une graduation
    private static final int MARK = 5;
    // largeur de la base du triangle pour la tête de la flèche
    private static final int ARROW_BASE = 20;
    // épaisseur du corps de la flèche
    private static final int ARROW_THICK = 4;
    // hauteur du corps de la flèche
    private static final int ARROW_HEIGHT = 20;
    // hauteur préférée d'un GraphicSpeedometer
    private static final int PREFERRED_HEIGHT = 3 * (3 * MARK + ARROW_BASE / 2 + ARROW_HEIGHT);
    // facteur d'échelle pour l'espacement entre deux graduations
    private static final double ASPECT_RATIO = 1.25;
    // couleur bleu franc lorsque le moteur est allumé
    private static final Color BLUE = Color.BLUE;
    // couleur rouge franc lorsque le moteur est allumé
    private static final Color RED = Color.RED;
    // couleur bleu grisé lorsque le moteur est éteint
    private static final Color GRAYED_BLUE = new Color(0, 0, 255, 50);
    // couleur rouge grisé lorsque le moteur est éteint
    private static final Color GRAYED_RED = new Color(255, 0, 0, 50);
    // les vitesses affichées sont celles, entre 0 et model.getMaxSpeed(), qui sont les multiples de SPLIT_SIZE
    private static final int SPLIT_SIZE = 10;
    
    private int width ;
    private int height ;
    private Graphics graph; 

	private SpeedometerModel model;

	public GraphicSpeedometer(SpeedometerModel m) {

        super();
		Contract.checkCondition(m != null);
		model = m;
        configureSize();
        
	}
	

    private void configureSize(){
        width = (int) (ASPECT_RATIO * ARROW_BASE * (model.getMaxSpeed() / SPLIT_SIZE) + 2 * MARGIN);
        height = 3 * (3 * MARK + ARROW_BASE / 2 + ARROW_HEIGHT); 
        setPreferredSize(new Dimension(width, PREFERRED_HEIGHT));
        setMinimumSize(new Dimension(width, PREFERRED_HEIGHT));
        System.out.println("" + width +":"+ height);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        graph = g;
        // dessiner la tete de fleche 
        int xA = (int) (MARGIN + (width * model.getSpeed()) / model.getMaxSpeed() - ARROW_BASE / 2);
        int yA = (int) (height/3 + THICK + 2 * MARK + ARROW_BASE / 2);
        int xC = xA + ARROW_BASE;
        int yC = yA;
        int xB = xA + ARROW_BASE/2;
        int yB = yA - ARROW_BASE/2;
        int xP = xA + (ARROW_BASE-ARROW_THICK)/2;
        int yP = yA;

        int[] abs = {xA, xB, xC};
        int[] ord = {yA, yB, yC};
        g.setColor((model.isOn() ? RED : GRAYED_RED));
        g.fillPolygon(abs, ord, 3);
        // dessin du rectangle de la fleche
        g.fillRect(xP, yP, ARROW_THICK, ARROW_HEIGHT);
        g.setColor((model.isOn() ? RED : GRAYED_RED));
        
        // dessin de numeroté
        int xQ, yQ;
        int grad = MARGIN;
        for (int i=0; i <= model.getMaxSpeed()/SPLIT_SIZE ;i++){
            FontMetrics fm = g.getFontMetrics();
            String s = String.valueOf(i * SPLIT_SIZE);
            int sWidth = fm.stringWidth(s);
            xQ = (int) (MARGIN + i * width * SPLIT_SIZE / model.getMaxSpeed() - sWidth / 2);
            yQ = (height / 3) - 2*MARK;
            g.setColor((model.isOn() ? BLUE : GRAYED_BLUE));
            g.drawString(s, xQ, yQ);
            g.drawLine(grad, (height/3) - MARK, grad, (height/3) + MARK);
            grad += width *  SPLIT_SIZE/ model.getMaxSpeed();
        }
        // dessin de la graduation
        int xG, yG;
        xG = MARGIN;
        yG = height /3;
        g.fillRect(xG, yG, width-2, THICK);
        g.setColor((model.isOn() ? BLUE : GRAYED_BLUE));
        System.out.println("" + width +":"+ height);
        System.out.println("done");
    }
}
