package serie07;

import util.Contract;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Observable;

public class StdSlotModel  extends Observable implements   SlotModel {
	
	private String result = "";
	private int [] credit;
	private int lastPayout = 0;
	private int moneyLost = 0;
	private int moneyWon = 0;
	
	public StdSlotModel(int[] credit) {
		Contract.checkCondition(credit != null);
		Contract.checkCondition(credit.length >= MIN_RESULT_SIZE);
		for (int i=0; i< credit.length;i++) {
			Contract.checkCondition(credit[i] >= 0);
		}
		this.credit = credit;
		for (int i=0; i<this.credit.length; i++) {
			result +=' ';
		}
	}

	@Override
	public int credit(int n) {
		Contract.checkCondition(1 <= n && n <= result.length());
		/*if (n==0) {
			return 0;
		}
		return 295*n - 585;*/
		return this.credit[n-1];
	}

	@Override
	public int lastPayout() {
		// TODO Auto-generated method stub
		return this.lastPayout;
	}

	@Override
	public int moneyLost() {
		// TODO Auto-generated method stub
		return this.moneyLost;
	}

	@Override
	public int moneyWon() {
		// TODO Auto-generated method stub
		return this.moneyWon;
	}

	@Override
	public String result() {
		// TODO Auto-generated method stub
		return this.result;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.result.length();
	}

	@Override
	public void gamble() {
		// TODO Auto-generated method stub
		Random r = new Random();
		result = "";
		for (int i=0; i<this.credit.length; i++) {
			result += (char) (r.nextInt(26) + (int) 'A');
		}
		Pattern pattern;
		Matcher matcher;
		int maxNbOcc = 0;
		for (int i=0; i<this.size(); i++) {
			pattern = Pattern.compile("[^" + result.charAt(i) + "]*" + result.charAt(i));
			matcher = pattern.matcher(result);
			int count = 0;
			while (matcher.find()) {
			    count++;
			}
			if (count > maxNbOcc) {
				maxNbOcc = count;
			}
		} 
		this.lastPayout = credit(maxNbOcc);
		this.moneyWon += this.lastPayout;
		this.moneyLost += 1;
		
		setChanged();
		notifyObservers();
	}
	

}
