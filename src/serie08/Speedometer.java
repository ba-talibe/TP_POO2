package serie08;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;


import serie08.SpeedometerModel.SpeedUnit;

public class Speedometer {
	
	private SpeedometerModel model;
	private JFrame frame;
	private JRadioButton kmhButton, mihButton;
	private JButton speedDownButton, speedUpButton;
	private JButton turnOffButton;
	private GraphicSpeedometer graphic; 
	
	public Speedometer() {
		//modele
		model = new StdSpeedometerModel(5, 60);
		
		//composants majeurs
		frame = createFrame();
		kmhButton = new JRadioButton("km / h", true);
		mihButton = new JRadioButton("mi / h", false);
		speedDownButton = new JButton("-");
		speedUpButton = new JButton("+");
		turnOffButton = new JButton("Turn " + ((model.isOn()) ? "OFF" : "ON"));
		graphic = new GraphicSpeedometer(model);
		placeComponents();
		connectControllers();
	}
	
	private void placeComponents() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(0 ,1));;
		{//--
			JPanel q = new JPanel();
			{//--
				JPanel r = new JPanel();
				r.setLayout(new GridLayout(0, 1));
				{//--
					ButtonGroup bg = new ButtonGroup();
					bg.add(kmhButton);
					bg.add(mihButton);
					r.add(kmhButton);
					r.add(mihButton);
				}//--
				q.add(r);
			}//--
			p.add(q);
			q = new JPanel();
			{//--
				JPanel r = new JPanel();
				r.setLayout(new GridLayout(1, 0));
				{//--
					r.add(speedUpButton);
					r.add(speedDownButton);
				}//--
				q.add(r);
			}//--
			p.add(q);
			q = new JPanel();
			{//--
				
				q.add(turnOffButton);
			}//--
			p.add(q);
		}//--
		frame.add(p, BorderLayout.WEST);
		frame.add(graphic, BorderLayout.CENTER);
	}
	
	private void connectControllers() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ((Observable) model).addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                refresh();
            }
        });

		turnOffButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				if (model.isOn()){
					model.turnOff();
				}else{
					model.turnOn();
				}
			}
		});

		speedDownButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				model.slowDown();
			}
		});
		speedUpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				model.speedUp();
			}
		});

		kmhButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				model.setUnit(SpeedUnit.KMH);
				
			}
		});

		mihButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				model.setUnit(SpeedUnit.MIH);
			}
		});

	}
	
    public void display() {
        refresh();
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    private void refresh() {
    	if (!model.isOn()){
			speedUpButton.setEnabled(false);
			speedDownButton.setEnabled(false);
		}else{
			speedUpButton.setEnabled(true);
			speedDownButton.setEnabled(true);
		}

		turnOffButton.setText("Turn " + ((model.isOn()) ? "OFF" : "ON"));

		graphic.repaint();
    }
    
	private JFrame createFrame() {
        final int frameWidth = 500;
        final int frameHeight = 300;
        
        JFrame f = new JFrame("Velocimetre");
        f.setLayout(new BorderLayout());
        f.setPreferredSize(new Dimension(frameWidth, frameHeight));
        return f;
	}

	    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Speedometer().display();
            }
        });
    }
}
