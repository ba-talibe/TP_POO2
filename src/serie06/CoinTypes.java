package serie06;

import java.util.HashMap;


public enum CoinTypes {
	ONE(1), 
	TWO(2), 
	FIVE(5), 
	TEN(10), 
	TWENTY(20), 
	FIFTY(50), 
	ONE_HUNDRED(100),
	TWO_HUNDRED(200);
	
	private final int faceValue;
	
	private static HashMap<Integer, CoinTypes> coinMap = new HashMap<Integer, CoinTypes>();
	private CoinTypes (int value) {
		faceValue = value;
	}
	
	public int getFaceValue() {
		return faceValue;
	}
	
	public String toString() {
		return faceValue + " ct" + ((faceValue == 1) ? "" : "s");
	}
	
	public static CoinTypes getCoinType(int value) {
		if (!coinMap.containsKey(value)){
			return null;
		}
		return coinMap.get(value);
	}

	static {
		coinMap.put(1, ONE);
		coinMap.put(2, TWO);
		coinMap.put(5, FIVE);
		coinMap.put(10, TEN);
		coinMap.put(20, TWENTY);
		coinMap.put(50, FIFTY);
		coinMap.put(100, ONE_HUNDRED);
		coinMap.put(200, TWO_HUNDRED);
	}

}
