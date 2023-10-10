package serie04;

import java.util.List;

//import java.util.Set;
//import java.util.HashSet;
import java.util.ArrayList;
import java.util.NavigableMap;
import java.util.TreeMap;

import util.Contract;

import java.util.NavigableSet;


public class StdPhoneBook<C extends Contact & Comparable<C>, N> implements PhoneBook<C, N> {
	
	private final NavigableMap<C, List<N>> contacts;
	
	public StdPhoneBook() {
		contacts =  new TreeMap<C, List<N>>();
	}

	@Override
	public NavigableSet<C> contacts() {
		return this.contacts.navigableKeySet();
	}

	@Override
	public boolean contains(C p) {
		Contract.checkCondition(p != null);
		return this.contacts.containsKey(p);
	}

	@Override
	public boolean isEmpty() {
		
		return this.contacts.isEmpty();
	}

	@Override
	public List<N> phoneNumbers(C p) {
		Contract.checkCondition(p != null);
		return this.contacts.get(p);
	}

	@Override
	public void addEntry(C p, N n) {
		Contract.checkCondition(p != null);
		Contract.checkCondition(!this.contains(p));
		Contract.checkCondition(n != null);
		List<N> nums = new ArrayList<N>();
		nums.add(n);
		this.contacts.put(p, nums);
		
	}

	@Override
	public void addEntry(C p, List<N> nums) {
		Contract.checkCondition(p != null);
		Contract.checkCondition(!this.contains(p));
		Contract.checkCondition(nums != null);
		Contract.checkCondition(nums.size() > 0);
		Contract.checkCondition(notNullValue(nums)); // We could use the contains method of List 
		
		List<N> numList = new ArrayList<N>();
		for (N n : nums) {
			if (!numList.contains(n)) {
				numList.add(n);
			}
		}
		
		//Set<N> numSet = new HashSet<N>(nums);
		//List<N> numList = new ArrayList<N>(numSet);
		this.contacts.put(p, numList);
	}

	@Override
	public void addPhoneNumber(C p, N n) {
		Contract.checkCondition(p != null);
		Contract.checkCondition(this.contains(p));
		
		List<N> nums = this.contacts.get(p);
		if (!nums.contains(n)) {
			nums.add(n);
		}
	}

	@Override
	public void clear() {
		this.contacts.clear();
		
	}

	@Override
	public void deletePhoneNumber(C p, N n) {
		Contract.checkCondition(p != null);
		Contract.checkCondition(this.contains(p));
		
		List<N> nums = this.contacts.get(p);
		nums.remove(n);
		if (nums.isEmpty()) {
			this.removeEntry(p);
		}
		
	}

	@Override
	public void removeEntry(C p) {
		// TODO Auto-generated method stub
		Contract.checkCondition(p != null);
		
		this.contacts.remove(p);
	}
	
	//Outils
	private boolean notNullValue(List <N> nums) {
		for (N n : nums) {
			if (n == null) {
				return false;
			}
		}
		return true;
	}

}
