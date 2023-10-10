package serie03.cmd;

import java.util.ArrayList;
import java.util.List;

import serie03.Text;
import util.Contract;

/**
 * @inv <pre>
 *     getBackup() != null
 *     isRelevantForText() </pre>
 */
class Clear extends AbstractCommand {
    
    // ATTRIBUTS D'INSTANCE
    
    // ...
    
    // CONSTRUCTEURS
    
    /**
     * Une commande de vidage du texte.
     * @pre <pre>
     *     text != null </pre>
     * @post <pre>
     *     getText() == text
     *     !done()
     *     getBackup().size() == 0 </pre>
     */
    private boolean done;
    
    private Text text;

    private List<String> backup = new ArrayList<String>();
    
    Clear(Text text) {
        // ...
      
    	super(text);
    	Contract.checkCondition(text != null);
    	this.text = text;
    	done = false;
    }
    
    // REQUETES
    
    /**
     * La liste des lignes composant le texte juste avant d'exécuter undoIt.
     */
    List<String> getBackup() {
      
      for (int lineNum = 1;lineNum <= this.text.getLinesNb(); lineNum++){
        backup.add(text.getLine(lineNum));
      }
	  return backup;
        
    }
    
    @Override
    public boolean isRelevantForText() {
    	if(!done()) {
    		return text.getLinesNb() > 0;
    	}
		return true;
    }
    
    // COMMANDES

    /**
     * Efface la totalité du texte.
     * @post <pre>
     *     getBackup().size() == old getText().getLinesNb()
     *     forall i, 0 <= i < getBackup().size() :
     *         getBackup().get(i).equals(old getText().getLine(i + 1))
     *     getText().getLinesNb() == 0 </pre>
     */
    @Override
    protected void doIt() {
        backup = getBackup();
        text.clear();
        done = true;
    }
    
    /**
     * Régénère la totalité du texte.
     * @post <pre>
     *     getText().getLinesNb() == old |getBackup()|
     *     forall i, 1 <= i <= getText().getLinesNb() :
     *         getText().getLine(i).equals(old getBackup().get(i - 1)) </pre>
     */
    @Override
    protected void undoIt() {
       for (int lineNum = 1;lineNum <= backup.size(); lineNum++){
        text.insertLine(lineNum, backup.get(lineNum-1));;
      }
      backup.clear();
      done = false;
    }
}
