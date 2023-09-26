package serie01.util;

public class StdCurrency implements Currency{
	private final CurrencyId id;
	private  double rate;
	public StdCurrency(CurrencyId id, double r){
		assert r > 0 && id != null;
		this.id = id;
		this.rate = r;
	}

	@Override
	public CurrencyId getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public double getExchangeRate() {
		// TODO Auto-generated method stub
		return rate;
	}

	@Override
	public String getIsoCode() {
		// TODO Auto-generated method stub
		return id.name();
	}

	@Override
	public String getCountry() {
		// TODO Auto-generated method stub
		return id.location();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return id.denomination();
	}

	@Override
	public void setExchangeRate(double r) {
		// TODO Auto-generated method stub
		assert r > 0 && this.id != CurrencyId.EUR;
		this.rate = r;
	}
}
