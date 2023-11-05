package serie06;

import java.util.EnumMap;
import util.Contract;

public class StdMoneyAmount  extends StdStock<CoinTypes>  implements MoneyAmount/*, Cloneable */ {

    //private final EnumMap<CoinTypes, Integer> stock;

    public StdMoneyAmount(){
        super(CoinTypes.class);
        //super<();
        //stock = new EnumMap<CoinTypes, Integer>(CoinTypes.class);
    }

    @Override
    public int getNumber(CoinTypes c) {
        Contract.checkCondition(c != null);
		if (!stock.containsKey(c)){
			return 0;
		}
		return stock.get(c);
    }

    @Override
    public int getTotalNumber() {
        int sum = 0;
		for (CoinTypes ele : stock.keySet()) {
			sum += stock.get(ele);
		}
		return sum;
    }

    @Override
    public void addElement(CoinTypes c) {
        Contract.checkCondition(c != null);
		stock.put(c, 1);
    }

    @Override
    public void addElement(CoinTypes c, int qty) {
        Contract.checkCondition(c != null);
		Contract.checkCondition(qty > 0);
		
		stock.put(c,  qty + getNumber(c));
    }

    @Override
    public void removeElement(CoinTypes c) {
        Contract.checkCondition(c != null);
		Contract.checkCondition(getNumber(c) >= 1);
		
		stock.replace(c, stock.get(c) -1);
    }

    @Override
    public void removeElement(CoinTypes c, int qty) {
        Contract.checkCondition(qty > 0);
		Contract.checkCondition(getNumber(c) >= qty);

		stock.replace(c, stock.get(c) -qty);
    }

    @Override
    public void reset() {
        for (CoinTypes c : stock.keySet()) {
			stock.replace(c, 0);
		}
    }

    @Override
    public int getValue(CoinTypes c) {
        Contract.checkCondition(c != null);
        return c.getFaceValue() * getNumber(c);
    }

    @Override
    public int getTotalValue() {
        int sum = 0;
        for (CoinTypes c : stock.keySet()){
            sum += getValue(c);
        }
        return sum;
    }
        

    @Override
    public void addAmount(MoneyAmount amount) {
        Contract.checkCondition(amount != null);
        for (CoinTypes c : CoinTypes.values()){
            if (amount.getNumber(c) > 0){
                this.addElement(c, amount.getNumber(c));
            }
        }
    }

    @Override
    public MoneyAmount computeChange(int s) {
        Contract.checkCondition(s > 0);
        try {
            MoneyAmount initAmount = new StdMoneyAmount();
            initAmount.addAmount(this);
            ChangeAlgorithm changeComputer = new GreedyAlgo(initAmount);
            changeComputer.computeChange(s);
            if (changeComputer.solutionFound()){
                return changeComputer.getChange();
            }
        return null;
        } catch (Exception e) {
            return null;
        }
        
    }


    @Override
    public void removeAmount(MoneyAmount amount) {
        Contract.checkCondition(amount != null);
        for (CoinTypes c : stock.keySet()){
            Contract.checkCondition(this.getNumber(c) >= amount.getNumber(c));
        }

        for (CoinTypes c : stock.keySet()){
            removeElement(c, amount.getNumber(c));
        }
    }
    
}
