package serie07;

/**
 * @inv
 *     moneyLost() >= 0
 *     moneyWon() >= 0
 *     result() != null
 *     size() == result().length()
 *     size() >= MIN_RESULT_SIZE
 *     forall i, 1 <= i <= size() : credit(i) >= 0
 * @cons
 *     $DESC$ un modèle dont les gains sont spécifiés par credit
 *            credit[0] == le gain pour des lettres toutes distinctes
 *            forall i, 1 <= i < size() :
 *                credit[i] == le gain pour i + 1 lettres identiques
 *     $ARGS$ int[] credits
 *     $PRE$
 *         credits != null
 *         |credits| >= MIN_RESULT_SIZE
 *         forall i, 0 <= i < |credits| : credits[i] >= 0
 *     $POST$
 *         size() == |credits|
 *         forall i, 1 <= i <= size() : credit(i) == credits[i - 1]
 *         lastPayout() == 0
 *         moneyLost() == 0
 *         moneyWon() == 0
 *         forall i, 0 <= i < size() : result().charAt(i) == ' '
 */
public interface SlotModel {
    
    // CONSTANTES
    
    int MIN_RESULT_SIZE = 3;
    
    // REQUETES
    
    /**
     * Ce que rapporte le fait que result() contienne n fois une même lettre,
     *  n étant maximal.
     * @pre
     *     1 <= n <= size()
     */
    int credit(int n);

    /**
     * Le dernier gain obtenu.
     */
    int lastPayout();
    
    /**
     * Le montant des pertes.
     */
    int moneyLost();
    
    /**
     * Le montant des gains.
     */
    int moneyWon();
    
    /**
     * Le résultat du dernier coup joué.
     */
    String result();
    
    /**
     * Le nombre total de lettres dans result().
     */
    int size();
    
    // COMMANDES
    
    /**
     * Joue un coup.
     * @post
     *     moneyLost() == old moneyLost() + 1
     *     size() == old size()
     *     result() est une nouvelle chaîne reconnue par "[A-Z]{" + size() + "}"
     *     lastPayout() == credit(<nombre_de_lettres_identiques_obtenues>)
     *     moneyWon() == (old moneyWon()) + lastPayout()
     *     forall i, 1 <= i <= size() : credit(i) == old credit(i)
     */
    void gamble();
}
