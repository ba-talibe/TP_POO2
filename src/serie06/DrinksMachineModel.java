package serie06;

/**
 * Modélise un distributeur automatique de boissons.
 * Un distributeur contient :
 *  - un stock de boissons de différents types ;
 *  - une caisse (cash) contenant une certaine quantité de chaque type de
 *     pièces utilisables ;
 *  - une réserve permettant de stocker le credit inséré par le client (credit).
 *  - une réserve permettant de stocker la monnaie à rendre au client
 *     (change).
 * Lorsque la caisse est insuffisamment remplie, la machine signale 
 *  qu'elle ne rendra pas la monnaie (canGetChange).
 * Le client désirant obtenir une boisson introduit des pièces (insertCoin).
 * Lorsqu'il sélectionne une boisson (selectDrink), son crédit est reversé dans
 *  la caisse, sa boisson est débitée du stock et la monnaie lui est 
 *  éventuellement reversée.
 * @inv <pre>
 *     forall d in DrinkTypes : 0 <= getDrinkNb(d) <= MAX_DRINK
 *     forall c in CoinTypes : 0 <= getCashNb(c) <= MAX_COIN
 *     forall c in CoinTypes : 0 <= getCreditNb(c) <= MAX_COIN
 *     forall c in CoinTypes : 0 <= getChangeNb(c)
 *     getCashAmount() == sum(c in CoinTypes,
 *             getCashNb(c) * c.getFaceValue())
 *     getCreditAmount() == sum(c in CoinTypes,
 *             getCreditNb(c) * c.getFaceValue())
 *     getChangeAmount() == sum(c in CoinTypes,
 *             getChangeNb(c) * c.getFaceValue())
 *     canGetChange()
 *         <==> la machine peut rendre la monnaie pour tout
 *              crédit fourni et pour toute boisson demandée. </pre>
 * @cons <pre>
 * $POST$
 *     forall d in DrinkTypes : getDrinkNb(d) == MAX_DRINK
 *     forall c in CoinTypes : getCashNb(c) == 0
 *     forall c in CoinTypes : getCreditNb(c) == 0
 *     forall c in CoinTypes : getChangeNb(c) == 0
 *     getLastDrink() == null </pre>
 */
public interface DrinksMachineModel {
    
    // CONSTANTES
    
    /**
     * Nombre maximal de boissons de chaque type.
     */
    int MAX_DRINK = 50;
    
    /**
     * Nombre maximal de pièces de chaque type.
     */
    int MAX_COIN = 100;

    // REQUETES
    
    /**
     * Le nombre de boissons du type d actuellement stockées dans la machine.
     * @pre <pre>
     *     d != null </pre>
     */
    int getDrinkNb(DrinkTypes d);
    
    /**
     * Indique le type de la dernière boisson obtenue du modèle.
     * Retourne null si la boisson a été prise, ou si le modèle n'a pas encore
     *  délivré de boisson.
     */
    DrinkTypes getLastDrink();
    
    /**
     * Le montant total du crédit du client actuellement stocké dans la machine.
     * On peut annuler ce crédit en faisant cancelCredit(), ce qui a pour effet
     *  de remplir d'autant getChange().
     */
    int getCreditAmount();
    
    /**
     * Le nombre de pièces de type c paticipant au credit actuellement stocké
     *  dans la machine.
     * @pre <pre>
     *     c != null </pre>
     */
    int getCreditNb(CoinTypes c);
    
    /**
     * Le montant total actuellement stocké dans la caisse de la machine.
     */
    int getCashAmount();
    
    /**
     * Le nombre de pièces de type c présentes dans la caisse de la machine.
     * @pre <pre>
     *     c != null </pre>
     */
    int getCashNb(CoinTypes c);
    
    /**
     * La monnaie actuellement rendue par la machine.
     * On peut prendre cette monnaie en faisant takeChange().
     */
    int getChangeAmount();
    
    /**
     * Le nombre de pièces de type c présentes dans la monnaie rendue par
     *  la machine.
     * @pre <pre>
     *     c != null </pre>
     */
    int getChangeNb(CoinTypes c);
    
    /**
     * La machine pourra-t-elle rendre la monnaie quels que soient la boisson
     *  commandée et le montant crédité ?
     */
    boolean canGetChange();
    
    // COMMANDES
    
    /**
     * Simule l'action de la machine lorsqu'elle fournit une boisson de type d.
     * Cette simulation ne peut se produire qu'à condition que le crédit soit
     *  suffisant et qu'il y ait au moins une boisson de type d dans le stock
     *  de boissons de la machine.
     * Dans ce cas, le crédit est mis dans la caisse et la monnaie est rendue
     *  si la machine est en mesure de le faire.
     * @pre <pre>
     *     d != null
     *     getDrinkNb(d) >= 1
     *     getCreditAmount() >= d.getPrice()
     *     getLastDrink() == null </pre>
     * @post <pre>
     *     getDrinkNb(d) == old getDrinkNb(d) - 1
     *     getLastDrink() == d
     *     getCreditAmount() == 0
     *     old canGetChange() ==> 
     *         getCashAmount() == old getCashAmount() + d.getPrice()
     *         getChangeAmount()  ==
     *             old (getChangeAmount() + getCreditAmount()) - d.getPrice()
     *     !old canGetChange() ==> 
     *         getCashAmount() == old (getCashAmount() + getCreditAmount())
     * </pre>
     */
    void selectDrink(DrinkTypes d);
    
    /**
     * Remplit le stock de boissons de type d dans la machine.
     * @pre <pre>
     *     d != null
     *     q > 0 && getDrinkNb(d) + q <= MAX_DRINK </pre>
     * @post <pre>
     *     getDrinkNb(d) == old getDrinkNb(d) + q </pre>
     */
    void fillStock(DrinkTypes d, int q);
    
    /**
     * Remplit la caisse de monnaie de la machine.
     * @pre <pre>
     *     c != null
     *     q > 0 && getCashNb(c) + getCreditNb(c) + q <= MAX_COIN </pre>
     * @post <pre>
     *     getCashNb(c) == old getCashNb(c) + q </pre>
     */
    void fillCash(CoinTypes c, int q);
    
    /**
     * Simule l'entrée d'une pièce de type c dans la machine par le client,
     *  augmentant si possible le crédit d'une pièce de type c.
     * @pre <pre>
     *     c != null </pre>
     * @post <pre>
     *     old getCashNb(c) + getCreditNb(c) == MAX_COIN
     *         ==> getChangeNb(c) == old getChangeNb(c) + 1
     *     old getCashNb(c) + getCreditNb(c) < MAX_COIN
     *         ==> getCreditNb(c) == old getCreditNb(c) + 1 </pre>
     */
    void insertCoin(CoinTypes c);
    
    /**
     * Annule le crédit, qui se retrouve dans la monnaie rendue.
     * @post <pre>
     *     getCreditAmount() == 0
     *     getChangeAmount() == old (getChangeAmount() + getCreditAmount())
     * </pre>
     */
    void cancelCredit();
    
    /**
     * Simule la prise de la boisson délivrée.
     * @post <pre>
     *     getLastDrink() == null </pre>
     */
    void takeDrink();
    
    /**
     * Simule la prise de la monnaie rendue.
     * @post <pre>
     *     getChangeAmount() == 0 </pre>
     */
    void takeChange();
    
    /**
     * Vide totalement la machine de son contenu (boissons et pièces).
     * @post <pre>
     *     getCashAmount() == 0
     *     getCreditAmount() == 0
     *     getChangeAmount() == 0
     *     getLastDrink() == null
     *     forall d in DrinkTypes : getDrinkNb(d) == 0 </pre>
     */
    void reset();
}
