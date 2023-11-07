package serie07;

import java.awt.Dimension;
import java.util.Observable;

import util.Contract;

public class StdSwellingModel extends Observable implements SwellingModel {
	
	private Dimension dimension ;
	private Dimension maxDimension ;
	private Dimension minDimension ;
	
	public StdSwellingModel(){
		dimension = new Dimension(0, 0);
		maxDimension = new Dimension(0, 0);
		minDimension = new Dimension(0, 0);
	}

	@Override
	public Dimension current() {
		
		return new Dimension((int) dimension.getWidth(),(int) dimension.getHeight());
	}

	@Override
	public boolean isSurroundedBy(Dimension d) {
		
		Contract.checkCondition(d != null);
		return this.dimension.getWidth() <= d.getWidth() && this.dimension.getHeight() <= d.getHeight();
	}

	@Override
	public boolean isSurrounding(Dimension d) {
		
		Contract.checkCondition(d != null);
		return this.dimension.getWidth() >= d.getWidth() && this.dimension.getHeight() >= d.getHeight();
	}

	@Override
	public boolean isValidScaleFactor(double f) {
		
		if (f <= MIN_FACTOR) {
			return false;
		}
		int newW = (int) (this.dimension.getWidth() * (1 + f / 100));
		int newH = (int) (this.dimension.getHeight() * 1 + (f / 100));
		
		if (newH < this.min().getHeight() || newW < this.min().getWidth() ) {
			return false;
		}
		if (newH > this.max().getHeight() || newW > this.max().getWidth() ) {
			return false;
		}
		return true;
	}

	@Override
	public Dimension max() {
		
		return new Dimension((int) maxDimension.getWidth(),(int) maxDimension.getHeight());
	}

	@Override
	public Dimension min() {
		
		return new Dimension((int) minDimension.getWidth(),(int) minDimension.getHeight());
	}

	@Override
	public void scaleCurrent(double f) {
		Contract.checkCondition(this.isValidScaleFactor(f));
		this.dimension.setSize(this.dimension.getWidth()* (1 + f / 100), this.dimension.getHeight()* (1 + f / 100));
		notifyObservers();
	}

	@Override
	public void setCurrent(Dimension d) {
		
		Contract.checkCondition(d != null);
		Contract.checkCondition(this.dimComparator(d, this.maxDimension) <= 0 );
		Contract.checkCondition(this.dimComparator(this.minDimension, d) <= 0 );
		this.dimension.setSize(d.getWidth(), d.getHeight()); 
		notifyObservers();
	}

	@Override
	public void setMax(Dimension d) {
		// TODO Auto-generated method stub
		Contract.checkCondition(d != null);
		Contract.checkCondition(this.dimComparator(new Dimension(0, 0), d) <= 0 );
		
		this.maxDimension.setSize(d.getWidth(), d.getHeight());
		if ( (this.dimComparator(d, this.minDimension) <= 0)) {
			this.minDimension.setSize(d.getWidth(), d.getHeight());
		}
		if ( !(this.dimComparator(this.dimension, this.maxDimension) <= 0)) {
			this.dimension.setSize(d.getWidth(), d.getHeight()); 
		}
		notifyObservers();
	}

	@Override
	public void setMin(Dimension d) {
		Contract.checkCondition(d != null);
		Contract.checkCondition(this.dimComparator(new Dimension(0, 0), d) <= 0 );
		
		this.minDimension.setSize(d.getWidth(), d.getHeight());
		if ( (this.dimComparator(this.dimension, d) <= 0)) {
			this.dimension.setSize(d.getWidth(), d.getHeight());
		}
		if (this.dimComparator(this.maxDimension, d) <= 0) {
			this.maxDimension.setSize(d.getWidth(), d.getHeight());
		}
		notifyObservers();

	}
	
	private int dimComparator(Dimension d1, Dimension d2) {
		if (d1.getHeight() < d2.getHeight() && d1.getHeight() < d2.getWidth()) {
			return -1;
		}
		if (d1.getHeight() > d2.getHeight() && d1.getHeight() > d2.getWidth()) {
			return 1;
		}
		if (d1.getHeight() == d2.getHeight() && d1.getHeight() == d2.getWidth()) {
			return 0;
		}
		return 2;
	}
}
