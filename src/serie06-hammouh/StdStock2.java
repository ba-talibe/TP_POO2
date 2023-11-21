package serie06;

import java.util.HashMap;
import java.util.Map;

import util.Contract;

public class StdStock2<E> implements Stock<E> {
	
	// ATTRIBUTS
	
	private Map<E, Integer> items;
	
	// CONSTRUCTEUR
	
	public StdStock2() {
		items = new HashMap<E, Integer>();
	}
	
	// REQUETES
    
    @Override
    public int getNumber(E e) {
    	Contract.checkCondition(e != null,
    			"L'item que vous avez donnez est null");
    	if(!items.containsKey(e)) return 0;
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
    	if(items.get(e) == null) items.put(e, qty);
    	else items.put(e, getNumber(e) + qty);
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
    	if(getNumber(e) == qty) items.remove(e);
    	else items.put(e, getNumber(e) - qty);
    }
    
    @Override
    public void reset() {
    	items.clear();
    }
}