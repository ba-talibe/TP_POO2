package serie06.change;


import serie06.ChangeAlgorithm;
import serie06.CoinTypes;
import serie06.MoneyAmount;
import serie06.StdMoneyAmount;
import util.Contract;

public abstract class AbstractAlgo implements ChangeAlgorithm {
	
		// ATTRIBUTS
	
		protected final int[] P = new int[] { 1, 2, 5, 10, 20, 50, 100, 200 };
		protected final int K = P.length;
		protected int[] coinCounts;
		protected int[] solution;
		protected boolean found;
		
		// CONSTRUCTEUR
		
		public AbstractAlgo(MoneyAmount s) {
			Contract.checkCondition(s != null, 
					"");
			coinCounts = new int[K];
			solution = new int[K];
			for(int i = 0; i < K; i++) {
				coinCounts[i] = s.getNumber(CoinTypes.getCoinType(P[i]));
			}
		}
		
		// REQUETES
	    
		@Override
	    public MoneyAmount getCash() {
	        return createMoneyAmount(coinCounts);
	    }
	    
		@Override
	    public MoneyAmount getChange() {
	    	Contract.checkCondition(solutionFound(), 
	    			"Aucune solution trouvée");
	        return createMoneyAmount(solution);
	    }
	    
		@Override
	    public boolean solutionFound() {
	    	return found;
	    }
	    
	    // COMMANDES
	   
	    abstract public void computeChange(int amount);
	    
	    // OUTILS
	    
	    private MoneyAmount createMoneyAmount(int[] solution) {
	        MoneyAmount s = new StdMoneyAmount();
	        for (int i = 0; i < K; i++) {
	            if(solution[i] > 0) s.addElement(CoinTypes.getCoinType(P[i]), solution[i]);
	        }
	        return s;
	    }

}
