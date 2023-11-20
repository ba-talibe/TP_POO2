package serie09;
import serie06.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

public class DrinksMachine {
	private StdDrinksMachineModel model;
	private JFrame frame;
	private EnumMap<DrinkTypes, JButton> drinks ;
	private JButton insertBtn, cancelBtn, consumeBtn;
	private JTextField coinInput, drinkOutput, changeOutput;
	private JLabel changeInfo, creditInfo;

	public DrinksMachine(){
		createModel();
		frame = createFrame();
		drinks = new EnumMap<DrinkTypes, JButton>(DrinkTypes.class);
		createView();
		placeComponents();
		connectControllers();
	}

	private void placeComponents(){
		frame.setLayout(new BorderLayout());
		JPanel p = new JPanel(new GridLayout(2, 1));
		{//--
			JPanel q = new JPanel();
			{//--
				q.add(changeInfo);
			}//--
			p.add(q);
			q = new JPanel();
			{//--
				q.add(creditInfo);
			}//--
			p.add(q);
		}//--
		frame.add(p, BorderLayout.NORTH);
		p = new JPanel();
		{//--
			JPanel q = new JPanel(new GridLayout(2, 2));
			{//--
				q.add(insertBtn);
				JPanel r = new JPanel();
				{//--
					r.add(coinInput);
					r.add(new JLabel("cents"));
				}//--
				q.add(r);
				q.add(cancelBtn);

			}//--
			p.add(q);
		}//--
		frame.add(p, BorderLayout.EAST);
		p = new JPanel(new GridLayout(3, 2));
		{//--
			for (DrinkTypes drink : drinks.keySet()){
				p.add(drinks.get(drink));
				p.add(new JLabel( Integer.toString(drink.getPrice()) + " cents"));
			}
		}//--
		frame.add(p, BorderLayout.WEST);
		p = new JPanel(new GridLayout(2, 1));
		{//--
			JPanel q = new JPanel(new GridLayout(1, 2));
			{//--
				JPanel r = new JPanel();
				{//--
					r.add(new JLabel("Boisson : "));
					r.add(drinkOutput);
				}//--
				q.add(r);
				r = new JPanel();
				{//--
					r.add(new JLabel("Monnaie : "));
					r.add(changeOutput);
					r.add(new JLabel("cents"));
				}//--
				q.add(r);
			}//--
			p.add(q);
			q = new JPanel();
			{//--
				q.add(consumeBtn);
			}//--
			p.add(q);
		}//--
		frame.add(p, BorderLayout.SOUTH);
	}

	public void display() {
        refresh();
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


	private void connectControllers(){

	}

	private void refresh(){

	}

	private void createDrinkButton(){
		for (DrinkTypes drink : DrinkTypes.values()){
			drinks.put(drink, new JButton(drink.toString()));
		}
	}

	private void createJButtons(){
		insertBtn = new JButton("Insérer");
		cancelBtn = new JButton("Annuler");
		consumeBtn = new JButton("Prendre votre boisson et/ou votre monnaie");

	}

	private void createJTextFields(){
		coinInput = new JTextField();
		coinInput.setEnabled(false);
		coinInput.setColumns(5);
		drinkOutput = new JTextField();
		drinkOutput.setEnabled(false); 
		drinkOutput.setColumns(15);
		changeOutput= new JTextField();
		changeOutput.setEnabled(false);
		changeOutput.setColumns(5);
	}

	private void createJLabels(){
		changeInfo = new JLabel((String) (model.canGetChange() ? "Cet machine rend la monnaie" : "Cet machine ne rend pas la monnaie"));
		creditInfo = new JLabel("Vous disposer d'un credit de " + model.getChangeAmount() + " cents");
	}

	private JFrame createFrame() {
        final int frameWidth = 600;
        final int frameHeight = 300;
        
        JFrame f = new JFrame("Distributeur de boissons");
        f.setLayout(new BorderLayout());
        f.setPreferredSize(new Dimension(frameWidth, frameHeight));
        return f;
	}

	private void createModel(){
		model = new StdDrinksMachineModel(10);
		// for (CoinTypes c : CoinTypes.values()){
		// 	model.fillCash(c, 5);
		// }
		System.out.println(model.canGetChange());
	}

	private void createView(){
		createDrinkButton();
		createJButtons();
		createJTextFields();
		createJLabels();

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DrinksMachine().display();
            }
        });
    }
}

