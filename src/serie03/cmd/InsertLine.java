package serie03.cmd;

import serie03.StdText;
import serie03.Text;
import util.Contract;

/**
 * @inv <pre>
 *     1 <= getIndex()
 *     isRelevantForText()
 *         <==> getIndex() <= getText().getLinesNb() + (done() ? 0 : 1)
 *     getLine() != null </pre>
 */
class InsertLine extends AbstractCommand {

    // ATTRIBUTS D'INSTANCE
    
    // ...
    
    // CONSTRUCTEURS
    
    /**
     * Une commande d'insertion de la chaîne <code>line</code> en
     *  position <code>index</code> dans <code>text</code>.
     * @pre <pre>
     *     text != null
     *     line != null
     *     1 <= numLine <= text.getLinesNb() + 1 </pre>
     * @post <pre>
     *     getText() == text
     *     !done()
     *     getIndex() == numLine
     *     getLine().equals(line)
     *     isRelevantForText() </pre>
     */
	private final int index;
    private Text text;
    private Text oldText;
    private String line;
    private boolean done;

    InsertLine(Text text, int numLine, String line) {
        // ...
    	super(text);
        Contract.checkCondition(text != null);
        Contract.checkCondition(line != null);
        Contract.checkCondition(1 <= numLine && numLine <= text.getLinesNb() + 1);
        this.text = text;
        this.index = numLine;
        this.line = line;
    }
    
    // REQUETES
    
    @Override
    public boolean isRelevantForText() {
    	if (done()) {
    		return  index <= getText().getLinesNb() && line.equals(text.getLine(index));
    	}else {
    		return index <= getText().getLinesNb()+1;
      	}
    	
        
        // ...
    }
    
    /**
     * Le rang où l'on doit insérer la ligne dans le texte.
     */
    int getIndex() {
		return index;
        // ...
    }
    
    /**
     * La ligne à insérer.
     */
    String getLine() {
		return line;
        // ...
    }
    
    // COMMANDES

    /**
     * Exécute l'insertion de la chaîne dans le texte.
     * @post <pre>
     *     getIndex() == old getIndex()
     *     getLine() == old getLine()
     *     getText().getLinesNb() == old getText().getLinesNb() + 1
     *     forall i, 1 <= i < getIndex() :
     *         getText().getLine(i).equals(old getText().getLine(i))
     *     getText().getLine(getIndex()).equals(old getLine())
     *     forall i, getIndex() < i <= getText().getLinesNb() :
     *         getText().getLine(i).equals(old getText().getLine(i - 1)) </pre>
     */
    @Override
    protected void doIt() {
        Text newText = new StdText();
        for (int i=1; i< getIndex(); i++){
            newText.insertLine(i, text.getLine(i));
        }
        newText.insertLine(getIndex(), line);
        for (int i=getIndex() ;i<= text.getLinesNb();i++){
            newText.insertLine(i+1, text.getLine(i));
        }
        
        text = newText;
        done = true;
    }
    
    /**
     * Annule l'insertion de la chaîne dans le texte.
     * @post <pre>
     *     getIndex() == old getIndex()
     *     getLine() == old getLine()
     *     getText().getLinesNb() == old getText().getLinesNb() - 1
     *     forall i, 1 <= i < getIndex() :
     *         getText().getLine(i).equals(old getText().getLine(i))
     *     forall i, getIndex() <= i <= getText().getLinesNb() :
     *         getText().getLine(i).equals(old getText().getLine(i + 1)) </pre>
     */
    @Override
    protected void undoIt() {
        Text newText = new StdText();
        for (int i=1; i< getIndex(); i++){
            newText.insertLine(i, text.getLine(i));
        }
        for (int i=getIndex();i< text.getLinesNb();i++){
            newText.insertLine(i, text.getLine(i+1));
        }
        text = newText;
        done = false;
    }
}
