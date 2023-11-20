package serie06;

import java.util.Observable;

import util.Contract;

public class StdDrinksMachineModel extends Observable implements DrinksMachineModel{

    private MoneyAmount cashBox = new StdMoneyAmount();
    private MoneyAmount creditBox = new StdMoneyAmount();
    private MoneyAmount changeBox = new StdMoneyAmount();
    private Stock<DrinkTypes> drinksStock  = new StdStock<DrinkTypes>(DrinkTypes.class);
    private DrinkTypes lastDrink = null;

    public StdDrinksMachineModel(){
        for (DrinkTypes c : DrinkTypes.values()){
            drinksStock.addElement(c, MAX_DRINK);
        }
    }

    public StdDrinksMachineModel(int max){
        if(max < 5) max =5;
        for (DrinkTypes c : DrinkTypes.values()){
            drinksStock.addElement(c, max);
        }
    }

    @Override
    public int getDrinkNb(DrinkTypes d) {
        Contract.checkCondition(d != null);
        return drinksStock.getNumber(d);
    }

    @Override
    public DrinkTypes getLastDrink() {
       return lastDrink;
    }

    @Override
    public int getCreditAmount() {
        return creditBox.getTotalValue();
    }

    @Override
    public int getCreditNb(CoinTypes c) {
        Contract.checkCondition(c != null);
        return creditBox.getNumber(c);
    }

    @Override
    public int getCashAmount() {
        return cashBox.getTotalValue();
    }

    @Override
    public int getCashNb(CoinTypes c) {
        Contract.checkCondition(c != null);
        return cashBox.getNumber(c);
    }

    @Override
    public int getChangeAmount() {
       return changeBox.getTotalValue();
    }

    @Override
    public int getChangeNb(CoinTypes c) {
        Contract.checkCondition(c != null);
        return changeBox.getNumber(c);
    }

    @Override
    public boolean canGetChange() {
        for (int i =1; i<= 199; i++){
            if (cashBox.computeChange(i) == null){
                return false;
            }
        }
        return true; // pas encore
    }

    @Override
    public void selectDrink(DrinkTypes d) {
        Contract.checkCondition(d != null);
		Contract.checkCondition(getDrinkNb(d) >= 1);
		Contract.checkCondition(getCreditAmount() >= d.getPrice());
		Contract.checkCondition(getLastDrink() == null);
		drinksStock.removeElement(d);
        if(canGetChange()){
        	changeBox=cashBox.computeChange(getChangeAmount()+getCreditAmount()-d.getPrice());
        	changeBox.addAmount(toMoneyAmount(d.getPrice()));
        }else {
        	cashBox.addAmount(creditBox);
        }
        lastDrink=d;
        creditBox.reset();
        
        setChanged();
        notifyObservers();
    }

    @Override
    public void fillStock(DrinkTypes d, int q) {
        Contract.checkCondition(d != null);
        Contract.checkCondition(q > 0);
        Contract.checkCondition(getDrinkNb(d) + q <= MAX_DRINK);
        drinksStock.addElement(d, q);

        setChanged();
        notifyObservers();
    }

    @Override
    public void fillCash(CoinTypes c, int q) {
        Contract.checkCondition(c != null);
        Contract.checkCondition(q > 0);
        Contract.checkCondition(getCashNb(c) + getCreditNb(c) + q <= MAX_COIN);
        cashBox.addElement(c, q);

        setChanged();
        notifyObservers();
    }

    @Override
    public void insertCoin(CoinTypes c) {
        Contract.checkCondition(c != null);
        if (getCashNb(c) + getCreditNb(c) == MAX_COIN){
            changeBox.addElement(c);
        }else{
            creditBox.addElement(c);
        }

        setChanged();
        notifyObservers();
    }

    @Override
    public void cancelCredit() {
       changeBox.addAmount(creditBox);
       creditBox.reset();

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
        creditBox.reset();
        changeBox.reset();

        setChanged();
        notifyObservers();
    }

    @Override
    public void reset() {
        changeBox.reset();
        creditBox.reset();
        cashBox.reset();
        lastDrink = null;
        drinksStock.reset();


        setChanged();
        notifyObservers();
    }

    //outils 
    private MoneyAmount toMoneyAmount(int amount) {
        CoinTypes[] x=CoinTypes.values();
        MoneyAmount money=new StdMoneyAmount();
        int i=x.length-1;
        int q=0;
        while(i>=0 && amount>0) {
            q=(int) Math.floor(amount/x[i].getFaceValue());
            money.addElement(x[i], q);
            amount-=q*x[i].getFaceValue();
            i--;
        }
        return money;
}
    
}
