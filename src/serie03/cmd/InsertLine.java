package serie03.cmd;

import serie03.Text;

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
    InsertLine(Text text, int numLine, String line) {
        // ...
    	super(text);
    }
    
    // REQUETES
    
    @Override
    public boolean isRelevantForText() {
		return false;
        // ...
    }
    
    /**
     * Le rang où l'on doit insérer la ligne dans le texte.
     */
    int getIndex() {
		return 0;
        // ...
    }
    
    /**
     * La ligne à insérer.
     */
    String getLine() {
		return null;
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
        // ...
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
        // ...
    }
}
