package serie07;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class Swelling {
	
	private JFrame frame;
	private SwellingModel model;
	private JTextField factorTextField;
	private JButton editButton;
	private JButton reinitButton;

    public Swelling(){
        frame = this.createFrame();
        model = new StdSwellingModel();
        initModel();
        factorTextField = creaJTextField();
        editButton = new JButton("Modifier");
        reinitButton = new JButton("Réinitialiser");
      
        placeComponents();
        connectControllers();
    }
	
    JFrame createFrame() {
        JFrame f = new JFrame("Slot Machine");
        f.setLayout(new BorderLayout());
        return f;
    }

    private JTextField creaJTextField(){
        JTextField t =  new JTextField();
        t.setColumns(7);
        return t;
    }

    private void initModel(){
        model.setMax(new Dimension(2000, 2000));
        model.setCurrent(new Dimension(300, 100));
    }

    private void placeComponents(){
        JPanel p = new JPanel();
        {//--
            p.add(new JLabel("Factueur : "));
            p.add(this.factorTextField);
            p.add(new JLabel("%"));
            p.add(this.editButton);
        }//--
        frame.add(p, BorderLayout.NORTH);
        p = new JPanel();
        {//--
            p.add(this.reinitButton);
        }//--
        frame.add(p, BorderLayout.SOUTH);
    }
    
    
    private void connectControllers() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ((Observable) model).addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                refresh();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String factorText = factorTextField.getText();
                Double factor =  (!factorText.matches("[\\d]+")) ? 0 :  Double.parseDouble(factorText)  ;
                if(factor == 0){
                    JOptionPane.showMessageDialog(
                        null, 
                        "le facteur est invalide ",
                        "Erreur !",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
                if (model.isValidScaleFactor(factor)){
                    model.scaleCurrent(factor);
                    System.out.println(model.current());
                }else{
                    JOptionPane.showMessageDialog(
                        null, 
                        "la facteur est impossible à appliqué",
                        "Erreur !",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        reinitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                initModel();
            }
        });
    }

    public void display() {
        refresh();
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

	private void refresh(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println("refreshing");
        frame.setSize(model.current());
	}
    

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Swelling().display();
            }
        });
    }
}


