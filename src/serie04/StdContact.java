package serie04;

import util.Contract;

public class StdContact implements Contact, Comparable<StdContact> {
		
	private String lastName;
	private String firstName;
	private Civ civility;
	
	public StdContact(Civ c, String n, String p) {
		Contract.checkCondition(c != null && n!= null && p != null, "La civilité, le nom et le prénom"
				+ "doivent être définies");
		Contract.checkCondition(!n.equals("") || !p.equals(""),  "le nom et le prénom"
				+ "ne peuvent pas être simultanément vides");
		
		this.lastName = n;
		this.firstName = p;
		this.civility = c;		
	}
	
	public StdContact(String n, String p) {	
		this(Civ.UKN, n, p);
	}
	
	@Override
	public boolean equals(Object other) {

		boolean result = false;

		if(other instanceof StdContact) {

			StdContact that = (StdContact) other;

			result = that.canEquals(this) && (this.civility == that.civility)

					&& (this.firstName == that.firstName) && (this.lastName == that.lastName);

		}
		return result;

	}

    public boolean canEquals(Object other) {

    	return other instanceof StdContact;

    }
	
	@Override
	public Civ getCivility() {
		// TODO Auto-generated method stub
		
		return this.civility;
	}

	@Override
	public String getFirstName() {
		// TODO Auto-generated method stub
		return this.firstName;
	}

	@Override
	public int hashCode() {

	    return civility.hashCode() + firstName.hashCode() +  lastName.hashCode();
	}
	
	@Override
	public String getLastName() {
		// TODO Auto-generated method stub
		return this.lastName;
	}

	@Override
	public Contact evolve(Civ civility) {
		// TODO Auto-generated method stub
		Contract.checkCondition(civility != null && getCivility().canEvolveTo(civility), " civility ne peux pas etre null"
				+ "et civility ne peux pas etre changé");
		Contact local_contact = new StdContact(civility, this.lastName, this.firstName);

		return local_contact;
	}
	
	@Override
	public String toString() {
		return this.getCivility().toString() + " " + this.getFirstName() + " " + this.getLastName();
	}
	
	@Override
	public int compareTo(StdContact c) {
		if(c == null) {	
			throw new NullPointerException();	
		}	
		int result = getLastName().compareTo(c.getLastName());	
		if(result == 0) {	
			result = getFirstName().compareTo(c.getFirstName());	
		}	
		return result == 0 ? getCivility().compareTo(c.getCivility()) : result;
	}
}