package serie01.util;
/**
 * Une devise permet d'accéder à un enregistrement dans la base de données.
 * C'est par le biais d'une devise que les classes externes au paquetage
 *  <code>util</code> peuvent consulter ou modifier la base de données.
 * @inv <pre>
 *     getId() != null
 *     getExchangeRate() > 0
 *     getIsoCode() != null && getIsoCode().length() == 3
 *     getCountry() != null
 *     getName() != null </pre>
 * @cons <pre>
 * $DESC$ Une devise d'identificateur id, de taux de change initial r.
 * $ARGS$ CurrencyId id, double r
 * $PRE$
 *     id != null && r > 0
 * $POST$
 *     getId() == id
 *     getExchangeRate() == r </pre>
 */
public interface Currency {

    // REQUETES
    
    /**
     * L'identificateur de la devise.
     */
    CurrencyId getId();

    /**
     * Le taux de change de la devise.
     */
    double getExchangeRate();

    /**
     * Le code ISO de la devise.
     */
    String getIsoCode();

    /**
     * Le pays où a cours la devise.
     */
    String getCountry();

    /**
     * Le nom de la devise.
     */
    String getName();
    
    // COMMANDES

    /**
     * Modifie le taux de change de la devise.
     * @pre <pre>
     *     r > 0
     *     getId() != CurrencyId.EUR </pre>
     * @post <pre>
     *     getExchangeRate() == r </pre>
     */
    void setExchangeRate(double r);
}
