package serie06;

import util.Contract;

public class GreedyAlgo  extends AbstractAlgo{

    public GreedyAlgo(MoneyAmount amount){
        super(amount);

    }
    @Override
    public void computeChange(int amount) {
       /*
         * TantQue i > 0 et s > 0 Faire
         * q =  min(ni, s/pi)
         * s = s - q*pi
         * xi = q
         * i = i - 1
         */
        Contract.checkCondition(amount >= 0);
        int i = CoinTypes.values().length;
        int s = amount;
        while (i > 0 && s > 0) {
        	if (cash.getNumber(getCoinI(i)) > 0) {
        		int q = Math.min(cash.getNumber(getCoinI(i)), (int) Math.round(s/getCoinI(i).getFaceValue()));
                s -= q * getCoinI(i).getFaceValue();
                solution.addElement(getCoinI(i), q);
        	}
            i--;
        }
        if (s == 0){
            solutionFound = true;
        }else{
            solution.reset();
        }
    }



    
}
