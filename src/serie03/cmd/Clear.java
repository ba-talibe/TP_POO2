package serie03.cmd;

import java.util.ArrayList;
import java.util.List;

import serie03.Text;

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
    Clear(Text text) {
        // ...
    	super(text);
    }
    
    // REQUETES
    
    /**
     * La liste des lignes composant le texte juste avant d'exécuter undoIt.
     */
    List<String> getBackup() {
		return null;
        
    }
    
    @Override
    public boolean isRelevantForText() {
		return false;
        
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
        // ...
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
        // ...
    }
}
