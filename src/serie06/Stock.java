package serie06;

/**
 * Modélise les stocks d'éléments.
 * Un stock est composé du type des éléments qu'il contient et du nombre
 *  d'éléments pour chacun d'eux.
 * @inv <pre>
 *     forall e in E : getNumber(e) >= 0
 *     getTotalNumber() == sum(e in E, getNumber(e)) </pre>
 * @cons <pre>
 *     $ARGS$
 *     $POST$
 *         getTotalNumber() == 0 </pre>
 */  
public interface Stock<E> {
    
    // REQUETES
    
    /**
     * Le nombre d'éléments de type <code>e</code> dans ce stock.
     * @pre <pre>
     *     e != null </pre>
     */
    int getNumber(E e);
    
    /**
     * Le nombre total d'éléments dans ce stock.
     */
    int getTotalNumber();

    // COMMANDES
   
    /**
     * Ajoute une nouvelle fois l'élément <code>e</code>.
     * @pre <pre>
     *     e != null </pre>
     * @post <pre>
     *     getNumber(e) == old getNumber(e) + 1 </pre>
     */
    void addElement(E e);
    
    /**
     * Ajoute <code>qty</code> fois l'élément <code>e</code>.
     * @pre <pre>
     *     e != null
     *     qty > 0 </pre>
     * @post <pre>
     *     getNumber(e) == old getNumber(e) + qty </pre>
     */
    void addElement(E e, int qty);
    
    /**
     * Retire une fois l'élément <code>e</code>.
     * @pre <pre>
     *     e != null
     *     getNumber(e) >= 1 </pre>
     * @post <pre>
     *     getNumber(e) == old getNumber(e) - 1 </pre>
     */
    void removeElement(E e);
    
    /**
     * Retire <code>qty</code> fois l'élément <code>e</code>.
     * @pre <pre>
     *     e != null
     *     qty > 0
     *     getNumber(e) >= qty </pre>
     * @post <pre>
     *     getNumber(e) == old getNumber(e) - qty </pre>
     */
    void removeElement(E e, int qty);
    
    /**
     * Vide ce stock en mettant à zéro toutes les quantités.
     * @post <pre>
     *     getTotalNumber() == 0 </pre>
     */
    void reset();
}
