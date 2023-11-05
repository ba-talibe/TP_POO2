package serie06;

import java.util.EnumMap;

import util.Contract;

public class StdStock<E extends Enum<E>> implements Stock<E> {

	protected final EnumMap<E, Integer> stock;
	private Class<E> c ;
	public StdStock(Class<E> ct){
		Contract.checkCondition(ct != null);
		c = ct;
		stock = new EnumMap<E, Integer>(ct);
	}


    @Override
	public int getNumber(E e) {
		Contract.checkCondition(e != null);
		if (! stock.containsKey(e)){
			return 0;
		}
		return stock.get(e);
	}

	@Override
	public int getTotalNumber() {
		int sum = 0;
		for (E ele : stock.keySet()) {
			sum += stock.get(ele);
		}
		return sum;
	}

	@Override
	public void addElement(E e) {
		Contract.checkCondition(e != null);
		stock.put(e, 1);
	}

	@Override
	public void addElement(E e, int qty) {

		Contract.checkCondition(e != null);
		Contract.checkCondition(qty > 0);
		stock.put(e,  qty + getNumber(e));
	}

	@Override
	public void removeElement(E e) {
		Contract.checkCondition(e != null);
		Contract.checkCondition(getNumber(e) >= 1);
		
		stock.replace(e, stock.get(e) -1);
	}

	@Override
	public void removeElement(E e, int qty) {
		Contract.checkCondition(e != null);
		Contract.checkCondition(qty > 0);
		Contract.checkCondition(getNumber(e) >= qty);

		stock.replace(e, stock.get(e) -qty);
	}

	@Override
	public void reset() {
		for (E e : stock.keySet()) {
			stock.replace(e, 0);
		}
	}

}
