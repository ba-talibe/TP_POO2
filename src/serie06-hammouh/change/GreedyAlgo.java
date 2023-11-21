package serie06.change;

import serie06.CoinTypes;
import serie06.MoneyAmount;
import serie06.StdMoneyAmount;
import util.Contract;

public class GreedyAlgo extends AbstractAlgo {
	
	public GreedyAlgo(MoneyAmount s) {
		super(s);
	}
	
	 // COMMANDES
    
	public void computeChange(int amount) {
    	Contract.checkCondition(amount >= 0, 
				"Le montant doit être supérieur ou égal à zéro");
    	int i = K - 1 ;
    	int q;
    	while(amount > 0 && i >= 0) {
    		q = (int) Math.min(coinCounts[i], Math.floor(amount / P[i]));
    		amount -= q * P[i];
    		solution[i] = q;
    		i -= 1;
    	}
    	found = (amount == 0);
    }
	
	public static void main(String[] args) {
		
		MoneyAmount mAmount = new StdMoneyAmount();
		
		mAmount.addElement(CoinTypes.getCoinType(200), 10);
		mAmount.addElement(CoinTypes.getCoinType(100), 1);
		mAmount.addElement(CoinTypes.getCoinType(50), 2);
		mAmount.addElement(CoinTypes.getCoinType(20), 4);
		mAmount.addElement(CoinTypes.getCoinType(10), 2);
		mAmount.addElement(CoinTypes.getCoinType(5), 1);
		mAmount.addElement(CoinTypes.getCoinType(2), 1);
		mAmount.addElement(CoinTypes.getCoinType(1), 2);
		
    	AbstractAlgo a = new GreedyAlgo(mAmount);
    	
    	a.computeChange(200);
    	
    	MoneyAmount result = a.getChange();
    	
    	
    	System.out.println(result.getNumber(CoinTypes.getCoinType(200)));
    	
    	
    	
    	
    	
    }
}
