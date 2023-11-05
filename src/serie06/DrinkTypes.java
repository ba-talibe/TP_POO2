package serie06;

public enum DrinkTypes {
    COFFEE(30), 
    CHOCOLATE(45),
    ORANGE_JUICE(110);

    private final int price ;
    private DrinkTypes(int price){
    this.price = price;
    }

    public int getPrice(){
        return price;
    }

    @Override
    public String toString(){
        return super.toString().toLowerCase().replaceAll("_", " ");
    }
}
