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
import javax.swing.JOptionPane;
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

	private ActionListener selectDrinkListener;
	private ActionListener insertCoinListener;

	public DrinksMachine(){
		createModel();
		frame = createFrame();
		drinks = new EnumMap<DrinkTypes, JButton>(DrinkTypes.class);
		createView();
		placeComponents();
		createController();
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
		DrinkTypes lastDrink = model.getLastDrink();
		if (lastDrink != null){
			drinkOutput.setText(lastDrink.toString());
			changeOutput.setText(Integer.toString(model.getChangeAmount()));
		}

		changeInfo.setText(model.canGetChange() ? "Cet machine rend la monnaie" : "Cet machine ne rend pas la monnaie");
		creditInfo.setText("Vous disposer d'un credit de " + model.getCreditAmount() + " cents");
		// verifons le stock de drink
		for (DrinkTypes drink : DrinkTypes.values()){
			if (model.getDrinkNb(drink) == 0){
				drinks.get(drink).setEnabled(false);
			}else{
				drinks.get(drink).setEnabled(true);
			}
		}
	}
	private void createController(){
		selectDrinkListener = new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e){
				JButton drinkButton = (JButton) e.getSource();
				String drink_name = drinkButton.getText();
				DrinkTypes selectedDrink = null;
				for (DrinkTypes drink : DrinkTypes.values()){
					if(drink.toString() == drink_name){
						selectedDrink = drink;
						break;
					}
				}
				if (selectedDrink != null  && model.getCreditAmount() >= selectedDrink.getPrice()){
					if (model.getLastDrink() != null){
						JOptionPane.showMessageDialog(
							null, 
							"Veillez prendre la boisson commandée",
							"Erreur !",
							JOptionPane.ERROR_MESSAGE
                    	);
					}else{
						model.selectDrink(selectedDrink);
					}
					
				}
			}
		};

		insertCoinListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				String cointText = coinInput.getText();
				
				if (cointText == null  || !cointText.matches("[\\d]")){
					JOptionPane.showMessageDialog(
							null, 
							"Veillez inserer un piece existante correct",
							"Erreur !",
							JOptionPane.ERROR_MESSAGE
                    	);
				}else{
					CoinTypes coinInserted = CoinTypes.getCoinType(Integer.parseInt(cointText));
					if (coinInserted == null){

					}
				}
	
			}
		};
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

		// creation des drinks buttons
		for (DrinkTypes drink : DrinkTypes.values()){
			drinks.put(drink, new JButton(drink.toString()));
		}

		// creation des Jbutton insert, cancel et consume
		insertBtn = new JButton("Insérer");
		cancelBtn = new JButton("Annuler");
		consumeBtn = new JButton("Prendre votre boisson et/ou votre monnaie");

		//creation des JtextFiel
		coinInput = new JTextField();
		coinInput.setEnabled(true);
		coinInput.setColumns(5);
		drinkOutput = new JTextField();
		drinkOutput.setEnabled(false); 
		drinkOutput.setColumns(15);
		changeOutput= new JTextField();
		changeOutput.setEnabled(false);
		changeOutput.setColumns(5);

		// creation des info label
		changeInfo = new JLabel( model.canGetChange() ? "Cet machine rend la monnaie" : "Cet machine ne rend pas la monnaie");
		creditInfo = new JLabel("Vous disposer d'un credit de " + model.getCreditAmount() + " cents");

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

