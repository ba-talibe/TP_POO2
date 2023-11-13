package serie07;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;


public class SlotMachine {

	
	private JFrame frame;
	private SlotModel model;
	private JButton gambleButton;
	private JLabel resultLabel;
	private JLabel loseLabel;

	private JLabel gainLabel;
    private JLabel payoutLabel;

    public SlotMachine() {
    	//Modele
    	int[]credits = new int[] {0, 5, 30};
    	model = new StdSlotModel(credits);
    	frame = createFrame();
    	gambleButton = new JButton("Tentez votre chance");
		resultLabel = new JLabel("");
		loseLabel = new JLabel("");
		gainLabel = new JLabel("");
		payoutLabel = new JLabel("");
    	//lostCreditLabel
		placeComponents();
		connectControllers();
    }

	
    JFrame createFrame() {
        JFrame f = new JFrame("Slot Machine");
        f.setLayout(new BorderLayout());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return f;
    }
    
	  private void placeComponents() {
        JPanel p =new JPanel();
		{//--
			p.add(this.gambleButton);
			p.add(this.resultLabel);
		}//--
		frame.add(p, BorderLayout.NORTH);
		p = new JPanel();
		{//--
			p.add(new JLabel("Pertes : "));
			p.add(this.loseLabel);
			p.add(new JLabel("Gains : "));
			p.add(this.gainLabel);
		}//--
		frame.add(p, BorderLayout.CENTER);
		p = new JPanel();
		{//--
			p.add(new JLabel("Vous venez de gagner : "));
			p.add(this.payoutLabel);
		}//--
		frame.add(p, BorderLayout.SOUTH);
    }

	public void display() {
        refresh();
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

	private void connectControllers() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ((Observable) model).addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                refresh();
            }
        });

        gambleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.gamble();
            }
        });
    }

	private void refresh(){

        
    

		resultLabel.setText(model.result());
		loseLabel.setText("" +  model.moneyLost());
		gainLabel.setText("" + model.moneyWon());
		payoutLabel.setText("" + model.lastPayout());
	}
	public static void main(String[] args) {
		 SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SlotMachine().display();
            }
        });
    }
	
}

