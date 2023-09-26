package serie01.model;

import serie01.util.Currency;
import util.Contract;


public class StdMultiConverter implements MultiConverter {
	private final Currency refCur ;
	private final Currency[] currencies;
	private final int currenciesNb;
	private double commonAmount = 0;
	
	StdMultiConverter(Currency refCur, int n){
		Contract.checkCondition(refCur != null && n >= 2);
		this.refCur = refCur;
		this.currenciesNb = n;
		currencies = new Currency[this.currenciesNb];
		
	}

	@Override
	public double getAmount(int index) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Currency getCurrency(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCurrencyNb() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getExchangeRate(int index1, int index2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setAmount(int index, double amount) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCurrency(int index, Currency c) {
		// TODO Auto-generated method stub

	}

}
