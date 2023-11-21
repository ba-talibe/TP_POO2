package serie06;

import serie06.change.AbstractAlgo;
import serie06.change.BacktrackAlgo;
import serie06.change.GreedyAlgo;
import util.Contract;



public class StdMoneyAmount extends StdStock<CoinTypes> implements MoneyAmount {

	// REQUETES

	public StdMoneyAmount() {
		super(CoinTypes.class);
	}

	@Override
	public int getValue(CoinTypes c) {
		Contract.checkCondition(c != null,
				"La pièce entré est null");
		return getNumber(c) * c.getFaceValue();
	}

	@Override
	public int getTotalValue() {
		int sum = 0;
		for(CoinTypes c : CoinTypes.values()) {
			sum += getValue(c);
		}
		return sum;
	}

	// COMMANDES

	@Override
	public void addAmount(MoneyAmount amount) {
		Contract.checkCondition(amount != null,
				"Le montant ajouté est null");
		for(CoinTypes c : CoinTypes.values()) {
			int val = amount.getNumber(c);
			if(val > 0) addElement(c, val);
		}
	} 

	@Override
	public MoneyAmount computeChange(int s) {
		Contract.checkCondition(s > 0,
				"La somme d'argent à rendre doit être superieur à 0");
		//AbstractAlgo g = new GreedyAlgo(this);
		AbstractAlgo g = new BacktrackAlgo(this);
		g.computeChange(s);
		if(!g.solutionFound()) return null;
		return g.getChange();
	}

	@Override
	public void removeAmount(MoneyAmount amount) {
		Contract.checkCondition(amount != null,
				"Le montant ajouté est null");
		Contract.checkCondition(allGreaterOrEqual(amount),
				"le montant retiré est superieur à ce qu'on a");
		
		for(CoinTypes c : CoinTypes.values()) {
			int val = amount.getNumber(c);
			if(val > 0) removeElement(c, amount.getNumber(c));
		}
	}

	// OUTILS

	private boolean allGreaterOrEqual(MoneyAmount amount) {
		for(CoinTypes c : CoinTypes.values()) {
			if(getNumber(c) < amount.getNumber(c)) return false;
		}
		return true;
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
		
		System.out.println(mAmount.getTotalValue());
		MoneyAmount resultAmount ;
		
		for(int i = 1; i < 200; i++) {
			resultAmount = mAmount.computeChange(i);
			System.out.println(resultAmount.getTotalValue());
		}
		
		
		
		
	}


}

/*
public class StdMoneyAmount extends StdStock1<CoinTypes> implements MoneyAmount {

	// REQUETES

	@Override
	public int getValue(CoinTypes c) {
		Contract.checkCondition(c != null,
				"La pièce entré est null");
		return getNumber(c) * c.getFaceValue();
	}

	@Override
	public int getTotalValue() {
		int sum = 0;
		for(CoinTypes c : CoinTypes.values()) {
			sum += getValue(c);
		}
		return sum;
	}

	// COMMANDES

	@Override
	public void addAmount(MoneyAmount amount) {
		Contract.checkCondition(amount != null,
				"Le montant ajouté est null");
		for(CoinTypes c : CoinTypes.values()) {
			int coinNumbers = amount.getNumber(c);
			if(coinNumbers != 0) addElement(c, coinNumbers);
		}
	} 

	/**
     * Une somme d'argent dont le montant vaut <code>s</code>
     *  et qui peut être prélevée de la somme courante.
     * Retourne null s'il n'est pas possible de faire la monnaie.
     * @pre <pre>
     *     s > 0 </pre>
     * @post <pre>
     *     result != this
     *     result == null <==> il n'est pas possible de faire la monnaie
     *     result != null
     *         ==> result.getTotalValue() == s
     *             forall c in CoinTypes :
     *                 result.getNumber(c) <= getNumber(c) </pre>
     *
	public MoneyAmount computeChange(int s) {
		Contract.checkCondition(s > 0,
				"La somme d'argent à rendre doit etre superieur à 0");
		//AbstractAlgo g = new GreedyAlgo(this);
		AbstractAlgo g = new BacktrackAlgo(this);
		g.computeChange(s);
		if(!g.solutionFound()) return null;
		return g.getChange();
	}

	@Override
	public void removeAmount(MoneyAmount amount) {
		Contract.checkCondition(amount != null,
				"Le montant ajouté est null");
		Contract.checkCondition(allGreaterOrEqual(amount),
				"le montant retiré est superieur à ce qu'on a");
		
		for(CoinTypes c : CoinTypes.values()) {
			int coinNumbers = amount.getNumber(c);
			if(coinNumbers != 0) removeElement(c, coinNumbers);
		}
	}

	// OUTILS

	private boolean allGreaterOrEqual(MoneyAmount amount) {
		for(CoinTypes c : CoinTypes.values()) {
			if(getNumber(c) < amount.getNumber(c)) return false;
		}
		return true;
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
		

		System.out.println(mAmount.getTotalValue());
		
		MoneyAmount resultAmount = mAmount.computeChange(200);
		
		System.out.println(resultAmount.getNumber(CoinTypes.getCoinType(200)));
		
		
	}
	
	
}*/