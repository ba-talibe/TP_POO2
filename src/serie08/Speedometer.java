package serie08;

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
import javax.swing.JRadioButton;

public class Speedometer {
	
	private SpeedometerModel model;
	private JFrame frame;
	private JRadioButton kmhButton, mihButton;
	private JButton speedDownButton, speedUpButton;
	private JButton turnOffButton;
	
	public Speedometer() {
		//modele
		model = new StdSpeedometerModel(10, 60);
		
		//composants majeurs
		frame = createFrame();
		kmhButton = new JRadioButton("km / h");
		mihButton = new JRadioButton("mi / h");
		speedDownButton = new JButton("-");
		speedUpButton = new JButton("+");
		turnOffButton = new JButton("Turn " + ((model.isOn()) ? "ON" : "OFF"));
		connectControllers();
		
		
	}
	
	private void placeComponents() {
		+
	}
	
	private void connectControllers() {
		
	}
	
	private JFrame createFrame() {
        final int frameWidth = 300;
        final int frameHeight = 300;
        
        JFrame f = new JFrame("Velocimetre");
        f.setPreferredSize(new Dimension(frameWidth, frameHeight));
        return f;
	}
}
