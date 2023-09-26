package serie01.util;

/**
 * Modélise les bases de données de monnaies du monde.
 * @inv <pre>
 *     forall id in CurrencyId : getCurrency(id) != null
 *     les devises d'une base de données sont uniques :
 *         tout appel à getCurrency(id) retourne invariablement le même objet
 * </pre>
 */
public interface CurrencyDB {
    
    // REQUETES
    
    /**
     * La devise identifiée par id.
     * Rq : la valeur retournée est toujours la même pour un id donné.
     * @pre <pre> id != null </pre>
     */
	
    Currency getCurrency(CurrencyId id);

    // COMMANDES
    
    /**
     * Synchronise cette base de données.
     */
    void sync() throws DBAccessException;
}
