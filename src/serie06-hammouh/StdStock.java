package serie06;

import java.util.EnumMap;
import java.util.Map;

import util.Contract;

public class StdStock<E extends Enum<E>> implements Stock<E>{
	
		// ATTRIBUTS
	
		private Map<E, Integer> items;
		
		// CONSTRUCTEUR
		
		public StdStock(Class<E> keyType) {
			items = new EnumMap<E, Integer>(keyType);
		       for (E elem : keyType.getEnumConstants()) {
		           items.put(elem, 0);
		       }
		}
		
		// REQUETES
	    
	    @Override
	    public int getNumber(E e) {
	    	Contract.checkCondition(e != null,
	    			"L'item que vous avez donnez est null");
	    	return items.get(e);
	    }
	    
	    @Override
	    public int getTotalNumber() {
	    	int result = 0;
	    	for(E item : items.keySet()) {
	    		result += getNumber(item);
	    	}
	    	return result;
	    }

	    // COMMANDES
	   
	    @Override
	    public void addElement(E e) {
	    	Contract.checkCondition(e != null,
	    			"L'item que vous avez donnez est null");
	    	addElement(e, 1);
	    }
	    
	    @Override
	    public void addElement(E e, int qty) {
	    	Contract.checkCondition(e != null,
	    			"L'item que vous avez donnez est null");
	    	Contract.checkCondition(qty > 0,
	    			"La quantité ajoutée doit etre superieur à zero");
	    	items.put(e, getNumber(e) + qty);
	    }
	    
	    @Override
	    public void removeElement(E e) {
	    	Contract.checkCondition(e != null,
	    			"L'item que vous avez donnez est null");
	    	Contract.checkCondition(getNumber(e) >= 1, 
	    			"Il n y a plus d'elements à enlever");
	    	removeElement(e,1);
	    }
	    
	    @Override
	    public void removeElement(E e, int qty) {
	    	Contract.checkCondition(e != null,
	    			"L'item que vous avez donnez est null");
	    	Contract.checkCondition(getNumber(e) >= 1, 
	    			"Il n y a plus d'elements à enlever");
	    	Contract.checkCondition(getNumber(e) >= qty, 
	    			"La quantité donné est superieure à la quantité du stock");
	    	items.put(e, getNumber(e) - qty);
	    }
	    
	    @Override
	    public void reset() {
	    	for (E elem : items.keySet()) {
		           items.put(elem, 0);
		       }
	    }
}
