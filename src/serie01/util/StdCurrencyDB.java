package serie01.util;

public class StdCurrencyDB  implements CurrencyDB{
	
	
	
	protected final Currency[] currencies  = new Currency[176];
	
	 StdCurrencyDB() {
		//initialiser la base de données ici 
	}
	

	@Override
	public Currency getCurrency(CurrencyId id) {
		Currency c = null ;
		for (Currency cs : currencies) {
			if (cs.getId()  == id) {
				c = cs;
			}
		}
		return c;
	}

	@Override
	public void sync() throws DBAccessException {
		// TODO Auto-generated method stub
		
	}

}
