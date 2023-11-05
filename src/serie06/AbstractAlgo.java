package serie06;

import util.Contract;

public abstract class AbstractAlgo implements ChangeAlgorithm{

    protected final MoneyAmount cash;
    protected boolean solutionFound = false;
    protected MoneyAmount solution ;

    public AbstractAlgo(MoneyAmount amount){
        Contract.checkCondition(amount != null);
        cash = amount;
        solution = new StdMoneyAmount();
    }

    @Override
    public MoneyAmount getCash() {
        
       return cash;
    }

    @Override
    public MoneyAmount getChange() {
        Contract.checkCondition(solutionFound());

        return solution;
    }

    @Override
    public boolean solutionFound() {
        return solutionFound;
    }

    //outils
    protected CoinTypes getCoinI(int i){
        Contract.checkCondition(i < CoinTypes.values().length);
        return CoinTypes.values()[i];
    }
}
