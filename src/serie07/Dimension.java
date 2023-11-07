package serie07;

public class Dimension implements Comparable{
	
	private int width;
	private int height;
	
	public Dimension(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
    @Override
    public int compareTo(Object o) {
    	return 0;
    }

    public boolean canEquals(Object other) {
    	 return other instanceof Dimension;
    }
       
    
}
