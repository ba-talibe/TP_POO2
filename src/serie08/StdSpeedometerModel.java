package serie08;

import java.util.Observable;

import util.Contract;

public class StdSpeedometerModel extends Observable implements SpeedometerModel {
	
	private double step, max, speed;
	private SpeedUnit currentUnit;
	private boolean isOn;
	
	public StdSpeedometerModel(double step, double max) {
		Contract.checkCondition(1 <= step && step <= max);
		this.step = step;
		this.max = max;
		this.currentUnit = SpeedUnit.KMH;
		this.isOn = false;
		this.speed = 0;
	}

	@Override
	public double getMaxSpeed() {
		// TODO Auto-generated method stub
		return this.max;
	}

	@Override
	public double getSpeed() {
		// TODO Auto-generated method stub
		return this.speed;
	}

	@Override
	public double getStep() {
		// TODO Auto-generated method stub
		return this.step;
	}

	@Override
	public SpeedUnit getUnit() {
		// TODO Auto-generated method stub
		
		return this.currentUnit;
	}

	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		return this.isOn; 
	}

	@Override
	public void setUnit(SpeedUnit unit) {
		// TODO Auto-generated method stub
		Contract.checkCondition(unit != null);
		if (this.currentUnit != unit) {
			this.max = this.max * (unit.getUnitPerKm()/this.currentUnit.getUnitPerKm());
			this.speed = this.speed * (unit.getUnitPerKm()/this.currentUnit.getUnitPerKm());
			this.step = this.step * (unit.getUnitPerKm()/this.currentUnit.getUnitPerKm());
		}
		
		this.currentUnit =  unit;
		
	}

	@Override
	public void slowDown() {
		// TODO Auto-generated method stub
		Contract.checkCondition(isOn());
		this.speed = Math.max(0, this.speed - this.step);
		
		setChanged();
		notifyObservers();
	}

	@Override
	public void speedUp() {
		// TODO Auto-generated method stub
		Contract.checkCondition(isOn());
		this.speed = Math.min(this.getMaxSpeed(), this.speed + this.step);

		setChanged();
		notifyObservers();
	}

	@Override
	public void turnOff() {
		// TODO Auto-generated method stub
		Contract.checkCondition(isOn());
		this.speed =0;
		this.isOn = false;
		

		setChanged();
		notifyObservers();
	}

	@Override
	public void turnOn() {
		// TODO Auto-generated method stub
		Contract.checkCondition(!isOn());
		this.isOn = true;
		
		setChanged();
		notifyObservers();
	}

}
