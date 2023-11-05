package serie06;

/**
 * Modélise les sommes d'argent.
 * Une telle somme est constituée uniquement d'un certain nombre de cents
 *  et d'euros.
 * @inv <pre>
 *     forall c in CoinTypes : getValue(c) == getNumber(c) * c.getFaceValue()
 *     getTotalValue() == sum(c in CoinTypes, getValue(c)) </pre>
 * @cons <pre>
 * $POST$ getTotalValue() == 0 </pre>
 */

public interface MoneyAmount extends Stock<CoinTypes> {

    // REQUETES
    
    /**
     * Le montant constitué par l'ensemble des pièces de type 
     *  <code>c</code>.
     * @pre <pre>
     *     c != null </pre>
     */
    
    int getValue(CoinTypes c);
    
    /**
     * Le montant total de cette somme, tous types de pièce confondus.
     */
    int getTotalValue();
    
    // COMMANDES
    
    /**
     * Ajoute la somme <code>amount</code> à la somme courante.
     * @pre <pre>
     *     amount != null </pre>
     * @post <pre>
     *     forall c in CoinTypes :
     *         getNumber(c) == old getNumber(c) + amount.getNumber(c) </pre>
     */
    void addAmount(MoneyAmount amount);
    
    /**
     * Une somme d'argent dont le montant vaut <code>s</code>
     *  et qui peut être prélevée de la somme courante.
     * Retourne null s'il n'est pas possible de faire la monnaie.
     * @pre <pre>
     *     s > 0 </pre>
     * @post <pre>
     *     result != this
     *     result == null <==> il n'est pas possible de faire la monnaie
     *     result != null
     *         ==> result.getTotalValue() == s
     *             forall c in CoinTypes :
     *                 result.getNumber(c) <= getNumber(c) </pre>
     */
    MoneyAmount computeChange(int s);
    
    /**
     * Retire la somme <code>amount</code> de la somme courante.
     * @pre <pre>
     *     amount != null
     *     forall c in CoinTypes :
     *         getNumber(c) >= amount.getNumber(c) </pre>
     * @post <pre>
     *     forall c in CoinTypes :
     *         getNumber(c) == old getNumber(c) - amount.getNumber(c) </pre>
     */
    void removeAmount(MoneyAmount amount);
}
