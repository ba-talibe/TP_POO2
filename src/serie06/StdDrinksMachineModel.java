package serie06;

import util.Contract;
import java.util.Observable;
import java.util.Observer;

public class StdDrinksMachineModel extends Observable implements DrinksMachineModel {
	
	// ATTRIBUTS
	
	private Stock<DrinkTypes> drinksStock;
	private DrinkTypes lastDrink;
	private MoneyAmount cashbox;
	private MoneyAmount changebox;
	private MoneyAmount creditbox;
	
	// CONSTRUCTEUR
	
	public StdDrinksMachineModel() {
		drinksStock = new StdStock<DrinkTypes>(DrinkTypes.class);
		for (DrinkTypes elem : DrinkTypes.values()) {
	           drinksStock.addElement(elem, MAX_DRINK);
	       }
		cashbox = new StdMoneyAmount();
		changebox = new StdMoneyAmount();
		creditbox = new StdMoneyAmount();
	}
	
	public StdDrinksMachineModel(int max) {
		drinksStock = new StdStock<DrinkTypes>(DrinkTypes.class);
		for (DrinkTypes elem : DrinkTypes.values()) {
	           drinksStock.addElement(elem, max);
	       }
		cashbox = new StdMoneyAmount();
		changebox = new StdMoneyAmount();
		creditbox = new StdMoneyAmount();
	}
	
	// REQUETES
    
    @Override
    public int getDrinkNb(DrinkTypes d) {
    	Contract.checkCondition(d != null,
				"Vous n'avez pas entrez un type de boisson");
    	return drinksStock.getNumber(d);
    }
    
    @Override
    public DrinkTypes getLastDrink() {
    	DrinkTypes lastDrinkcopie = lastDrink;
    	return lastDrinkcopie;
    }
    
    @Override
    public int getCreditAmount() {
    	return creditbox.getTotalValue();
    }
    
    @Override
    public int getCreditNb(CoinTypes c) {
    	Contract.checkCondition(c != null,
				"Vous n'avez pas entrez un type de pièce");
    	return creditbox.getNumber(c);
    }
    
    @Override
    public int getCashAmount() {
    	return cashbox.getTotalValue();
    }
    
    @Override
    public int getCashNb(CoinTypes c) {
    	Contract.checkCondition(c != null,
				"Vous n'avez pas entrez un type de pièce");
    	return cashbox.getNumber(c);
    }
    
    @Override
    public int getChangeAmount() {
    	return changebox.getTotalValue();
    }
    
    @Override
    public int getChangeNb(CoinTypes c) {
    	Contract.checkCondition(c != null,
				"Vous n'avez pas entrez un type de pièce");
    	return changebox.getNumber(c);
    }
    
    @Override
    public boolean canGetChange() {
    	CoinTypes[] c = CoinTypes.values();
    	int s = c[c.length - 1].getFaceValue();
    	for(int x = 1; x < s; x++) {
    		if(cashbox.computeChange(x) == null) return false;
    	}
    	return true;
    }
    
    // COMMANDES
    
    @Override
    public void selectDrink(DrinkTypes d) {
    	Contract.checkCondition(d != null,
				"Vous n'avez pas entrez un type de boisson");
    	Contract.checkCondition(getDrinkNb(d) >= 1 ,
				"Le nombre de cette boisson commandé doit être sup à zero");
    	Contract.checkCondition(getCreditAmount() >= d.getPrice(),
				"Vous n'avez pas entrez un suffisamment d'argent");
    	Contract.checkCondition(getLastDrink() == null,
				"La machine a délivré de boisson mais elle n'a pas encore été prise");
    	
    	cashbox.addAmount(creditbox);
    	drinksStock.removeElement(d);
    	
    	int value = getCreditAmount();
    	
    	if(value > 0 && canGetChange()) {
    		
    		if (value - d.getPrice() > 0) {
    			MoneyAmount montantbox = cashbox.computeChange(value - d.getPrice());
    			changebox.addAmount(montantbox);
        		cashbox.removeAmount(montantbox);
    		}
    		
    		

    	}
    	
    	creditbox.reset();
    	lastDrink = d;
    	
    	setChanged();
    	notifyObservers();
    }
    
    @Override
    public void fillStock(DrinkTypes d, int q) {
    	Contract.checkCondition(d != null,
    			"Vous n'avez pas entrez un type de boisson");
    	Contract.checkCondition(q > 0,
				"Vous devriez ajouter au minimun une seule boisson");
    	Contract.checkCondition(getDrinkNb(d) + q <= MAX_DRINK,
				"Vous avez depasser la quantité maximum reservé pour cette boisson dans le stock");
    	
    	drinksStock.addElement(d, q);
    	
    	setChanged();
    	notifyObservers();
    }
    
    @Override
    public void fillCash(CoinTypes c, int q) {
    	Contract.checkCondition(c != null,
				"Vous n'avez pas entrez un type de pièce");
    	Contract.checkCondition(q > 0,
				"Vous devriez ajouter au minimun une seule pièce");
    	Contract.checkCondition(getCashNb(c) + getCreditNb(c) + q <= MAX_COIN,
				"Vous avez depasser la quantité maximum reservé pour cette pièce dans le stock");
    	
    	cashbox.addElement(c, q);
    	
    	setChanged();
    	notifyObservers();
    }
    
    @Override
    public void insertCoin(CoinTypes c) {
    	Contract.checkCondition(c != null,
    			"Vous n'avez pas entrez un type de pièce");
    	if(getCashNb(c) + getCreditNb(c) == MAX_COIN) changebox.addElement(c);
    	else creditbox.addElement(c);
    	
    	setChanged();
    	notifyObservers();
    }
    
    @Override
    public void cancelCredit() {
    	changebox.addAmount(creditbox);
    	creditbox.reset();
    	
    	setChanged();
    	notifyObservers();
    }
    
    @Override
    public void takeDrink() {
    	lastDrink = null;
    	
    	setChanged();
    	notifyObservers();
    }
    
    @Override
    public void takeChange() {
    	changebox.reset();
    	
    	setChanged();
    	notifyObservers();
    }
    
    @Override
    public void reset() {
    	cashbox.reset();
    	changebox.reset();
    	creditbox.reset();
    	lastDrink = null;
    	drinksStock.reset();
    	
    	setChanged();
    	notifyObservers();
    }
    
    
}
