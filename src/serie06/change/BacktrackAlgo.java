package serie06.change;

import serie06.CoinTypes;
import serie06.MoneyAmount;
import serie06.StdMoneyAmount;
import util.Contract;

public class BacktrackAlgo extends AbstractAlgo {

	 public BacktrackAlgo(MoneyAmount s) {
		super(s);
	}

	// COMMANDES
    
    
    public void computeChange(int amount) {
    	Contract.checkCondition(amount >= 0, 
				"Le montant doit être supérieur ou égal à zéro");
    	found = bt(amount, K - 1);
    	
    }
    
    // OUTILS
    
    private boolean bt(int amount, int i) {
    	Contract.checkCondition(amount >= 0, 
				"Le montant doit être supérieur ou égal à zéro");
    	
    	if(amount == 0) return true;
    	if(i < 0) return false;
    	
    	for (int q = (int) Math.min(coinCounts[i], Math.floor(amount / P[i])); q >= 0; q--) {
    		if(bt(amount - q * P[i], i - 1)) {
    			solution[i] = q;
    			return true;
    		}
    	}
    	
    	return false;
    }
    
	public static void main(String[] args) {
		
		MoneyAmount mAmount = new StdMoneyAmount();
		
		
		
		mAmount.addElement(CoinTypes.ONE, 100);
		mAmount.addElement(CoinTypes.TWO, 50);
		mAmount.addElement(CoinTypes.FIVE, 50);
		mAmount.addElement(CoinTypes.TEN, 51);
		mAmount.addElement(CoinTypes.TWENTY, 52);
		mAmount.addElement(CoinTypes.FIFTY, 50);
		mAmount.addElement(CoinTypes.ONE_HUNDRED, 10);
		mAmount.addElement(CoinTypes.TWO_HUNDRED, 5);
		
    	AbstractAlgo a = new BacktrackAlgo(mAmount);
    	
    	a.computeChange(2);
    	
    	MoneyAmount result = a.getChange();
    	
    	System.out.println(a.found);
    	System.out.println(result.getTotalValue());
    	
    	
    	
    }
    	
}
