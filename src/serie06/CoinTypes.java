package serie06;

public enum CoinTypes {
	ONE(1, "1 ct"),
	TWO(2, "2 cts"),
	FIVE(5, "5 cts"),
	TEN(10, "10 cts"), 
	TWENTY(20, "20 cts"),
	FIFTY(50, "50 cts"),
	ONE_HUNDRED(100, "100 cts"), 
	TWO_HUNDRED(200, "200 cts");
	
	private int price;
	private String description;
	
	CoinTypes(int price, String description) {
		this.price = price;
		this.description = description;
	}
	
	public int getFaceValue() {
		return price;
	}
	
	@Override
	public String toString() {
		return description;
	}
	
	public static CoinTypes getCoinType(int val) {
		
		for(CoinTypes coin : CoinTypes.values()) {
			if(coin.getFaceValue() == val) return coin;
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(CoinTypes.ONE.getFaceValue());
		System.out.println(CoinTypes.getCoinType(40));
	}
}
