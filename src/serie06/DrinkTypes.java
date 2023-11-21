package serie06;

public enum DrinkTypes {
	COFFEE(30),
	CHOCOLATE(45),
	ORANGE_JUICE(110);
	
	private int price;
	
	private DrinkTypes(int price) {
		this.price = price;
	}
	
	public int getPrice() {
		return price;
	}
	
	@Override
	public String toString() {
		return name().toLowerCase().replaceAll("_", " ");
	}
	
	public static void main(String[] args) {
		System.out.println(DrinkTypes.ORANGE_JUICE.toString());
	}
}
