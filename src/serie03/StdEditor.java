package serie03;

import util.Contract;

import serie03.cmd.CommandFactory;

public class StdEditor implements Editor {

    private  History<Command> history;
    private Text text = new StdText();
    private int historySize = DEFAULT_HISTORY_SIZE;

    public StdEditor(){
        history = new StdHistory<Command>(historySize);
    }

    public StdEditor(int historySize){
        Contract.checkCondition(historySize > 0);
        this.historySize = historySize;
        history = new StdHistory<Command>(historySize);
    }

    @Override
    public int getTextLinesNb() {
        // TODO Auto-generated method stub
        return text.getLinesNb();
    }

    @Override
    public String getTextContent() {
        // TODO Auto-generated method stub
        return text.getContent();
    }

    @Override
    public int getHistorySize() {
        // TODO Auto-generated method stub
        return this.historySize;
    }

    @Override
    public int nbOfPossibleUndo() {
        // TODO Auto-generated method stub
        return history.getCurrentPosition();
    }

    @Override
    public int nbOfPossibleRedo() {
        // TODO Auto-generated method stub
        return history.getEndPosition() - history.getCurrentPosition();
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        Command clearCommand = CommandFactory.createClear(text);
        clearCommand.act();
        history.add(clearCommand);
        
        
    }

    @Override
    public void insertLine(int numLine, String s) {
        // TODO Auto-generated method stub
        // 
        Contract.checkCondition(s != null);
        Contract.checkCondition(1 <= numLine && numLine <= getTextLinesNb()+1);

        
        Command insertCommand = CommandFactory.createInsertLine(text, numLine, s);
        history.add(insertCommand);
        insertCommand.act();
    }

    @Override
    public void deleteLine(int numLine) {
        Contract.checkCondition(1 <= numLine && numLine <= text.getLinesNb());

        Command deleteCommand = CommandFactory.createDeleteLine(text, numLine);
        history.add(deleteCommand);
        deleteCommand.act();
    }

    @Override
    public void redo() {
        // TODO Auto-generated method stub
        Contract.checkCondition(nbOfPossibleRedo() > 0);

        history.goForward();
        Command currentCommand = history.getCurrentElement();
        currentCommand.act();

    }

    @Override
    public void undo() {
        Contract.checkCondition(nbOfPossibleUndo() > 0);

        Command currentCommand = history.getCurrentElement();
        history.goBackward();
        currentCommand.act();
    }
    
}
