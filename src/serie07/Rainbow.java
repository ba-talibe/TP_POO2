package serie07;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Rainbow {

    // ATTRIBUTS D'INSTANCE
    
    private final JFrame frame;
    private final JButton changeColorButton;
    private final RainbowModel model;

    // CONSTRUCTEURS
    
    public Rainbow() {
        // MODELE
        model = new StdRainbowModel();
        // VUE
        frame = createFrame();
        changeColorButton = new JButton("Modifier");
        placeComponents();
        // CONTROLEUR
        connectControllers();
    }
    
    // COMMANDES
    
    /**
     * Rend l'application visible au centre de l'écran.
     */
    public void display() {
        refresh();
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // OUTILS
    
    private JFrame createFrame() {
        final int frameWidth = 300;
        final int frameHeight = 300;
        
        JFrame f = new JFrame("Arc-en-ciel");
        f.setPreferredSize(new Dimension(frameWidth, frameHeight));
        return f;
    }
    
    private void placeComponents() {
        JPanel p = new JPanel();
        { //--
            p.add(changeColorButton);
        } //--
        frame.add(p, BorderLayout.NORTH);
    }
    
    private void connectControllers() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ((Observable) model).addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                refresh();
            }
        });

        changeColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.changeColor();
            }
        });
    }
    
    private void refresh() {
        Container contentPane = frame.getContentPane();
        contentPane.setBackground(model.getColor());
    }

    // POINT D'ENTREE
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Rainbow().display();
            }
        });
    }
}
