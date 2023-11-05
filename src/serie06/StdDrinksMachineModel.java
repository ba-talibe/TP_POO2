package serie06;

import util.Contract;

public class StdDrinksMachineModel implements DrinksMachineModel{

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
        return changeBox != null;
    }

    @Override
    public void selectDrink(DrinkTypes d) {
        Contract.checkCondition(d != null);
        lastDrink = DrinkTypes.values()[d.ordinal()];
    }

    @Override
    public void fillStock(DrinkTypes d, int q) {
        Contract.checkCondition(d != null);
        Contract.checkCondition(q > 0);
        Contract.checkCondition(getDrinkNb(d) + q >= MAX_DRINK);

        drinksStock.addElement(d, q);
    }

    @Override
    public void fillCash(CoinTypes c, int q) {
        Contract.checkCondition(c != null);
        Contract.checkCondition(q > 0);
        Contract.checkCondition(getCashNb(c) + getCreditNb(c) + q >= MAX_COIN);

        cashBox.addElement(c, q);
    }

    @Override
    public void insertCoin(CoinTypes c) {
        Contract.checkCondition(c != null);
        if (getCashNb(c) + getCreditNb(c) == MAX_COIN){
            changeBox.addElement(c);
        }else{
            creditBox.addElement(c);
        }
    }

    @Override
    public void cancelCredit() {
       changeBox.addAmount(creditBox);
       creditBox.reset();
    }

    @Override
    public void takeDrink() {
        lastDrink = null;
    }

    @Override
    public void takeChange() {
        changeBox.reset();
    }

    @Override
    public void reset() {
       changeBox.reset();
       creditBox.reset();
       cashBox.reset();
       lastDrink = null;
       drinksStock.reset();
    }
    
}
