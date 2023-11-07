package serie07;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;


public class SlotMachine {

	
	private final JFrame frame;
	private final JButton gambleButton;
    private final SlotModel model;
    private final JLabel model;

    public SlotMachine() {
    	//Modele
    	int[]credits = new int[] {0, 5, 30};
    	model = new StdSlotModel(credits);
    	frame = createFrame();
    	gambleButton = new JButton("Tentez votre chance");
    	lostCreditLabel
    }
    
    JFrame createFrame() {
    	return new JFrame();
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
