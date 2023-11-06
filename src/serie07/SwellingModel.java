package serie07;

import java.awt.Dimension;

/**
 * Notre modèle représente une taille courante, bornée par une taille min
 *  et une taille max.
 * On peut faire croître et décroître la taille courante à volonté entre les
 *  bornes min et max.
 * On récupère la taille du modèle dans une Dimension par current() et on peut
 *  la modifier de deux manières différentes.
 *  
 * Remarque 1 : dans toute cette spécification, on définit les notations
 *     forall n in int ; d, e in Dimension
 *         (d <= e) <==> (d.width <= e.width && d.height <= e.height)
 *         (n <= d) <==> (n <= d.width && n <= d.height)
 * Remarque 2 : il faut résister à la notation « d > e » pour « !(d <= e) »
 *     forall d, e in Dimension
 *         !(d <= e) <==> (d.width > e.width || d.height > e.height)
 *     en particulier, pour une Dimension d donnée il est possible que
 *         !isSurrounding(d) && !isSurroundedBy(d)
 *  
 * @inv
 *     Let s(f) ::= new Dimension(
 *                      current().width * (1 + f / 100),
 *                      current().height * (1 + f / 100))
 *     min() != null && current() != null && max() != null
 *     min() != current() && current() != max() && min() != max()
 *     0 <= min() <= current() <= max()
 *     forall d in Dimension :
 *         isSurroundedBy(d) <==> current() <= d
 *         isSurrounding(d) <==> d <= current()
 *     forall f >= 0 : isValidScaleFactor(f) <==> (s(f) <= max())
 *     forall MIN_FACTOR <= f < 0 : isValidScaleFactor(f) <==> (min() <= s(f))
 *     forall f < MIN_FACTOR : !isValidScaleFactor(f)
 * @cons
 *     $DESC$ un modèle de min et max égaux à 0
 *     $ARGS$ -
 *     $PRE$ -
 *     $POST$
 *         min().equals(new Dimension(0, 0))
 *         max().equals(new Dimension(0, 0))
 */
public interface SwellingModel {
    
    // CONSTANTES
    
    /**
     * Plus grand facteur de décroissance possible pour une Dimension.
     * Si d est une Dimension, retailler d de MIN_FACTOR pourcents conduira à
     *  d.width == 0 et d.height == 0.
     */
    int MIN_FACTOR = -100;
    
    // REQUETES
    
    Dimension current();
    
    /**
     * Indique si la dimension courante du modèle est contenue dans d.
     * @pre
     *     d != null
     */
    boolean isSurroundedBy(Dimension d);
    
    /**
     * Indique si la dimension courante du modèle contient d.
     * @pre
     *     d != null
     */
    boolean isSurrounding(Dimension d);
    
    /**
     * Détermine si l'on a le droit de retailler la dimension courante du
     *  modèle d'un facteur f, c'est-à-dire si à la fois :
     *  <ul>
     *  <li> f >= MIN_FACTOR, et</li>
     *  <li> new Dimension(w * (1 + f / 100), h * (1 + f / 100)) est comprise
     *  entre min() et max().</li>
     *  </ul>
     *  (où w et h représentent la largeur et la hauteur courantes).
     */
    boolean isValidScaleFactor(double f);

    /**
     * La dimension maximale autorisée.
     */
    Dimension max();
    
    /**
     * La dimension minimale autorisée.
     */
    Dimension min();
    
    // COMMANDES
    
    /**
     * Retaille la dimension courante de f/100 pourcents (f > 0 : augmentation,
     *  f < 0 : réduction).
     * @pre
     *     isValidScaleFactor(f)
     * @post
     *     current().width == old current().width * (1 + f / 100)
     *     current().height == old current().height * (1 + f / 100)
     *     min() == old min()
     *     max() == old max()
     */
    void scaleCurrent(double f);
    
    /**
     * Fixe la taille courante.
     * @pre
     *     d != null
     *     min() <= d <= max()
     * @post
     *     current().equals(d)
     *     min() == old min()
     *     max() == old max()
     */
    void setCurrent(Dimension d);
    
    /**
     * Fixe la taille maximale autorisée.
     * Fixe min() ou current() si nécessaire.
     * @pre
     *     d != null
     *     new Dimension(0, 0) <= d
     * @post
     *     max().equals(d)
     *     (old min() <= d) ==> min() == old min()
     *     !(old min() <= d) ==> min().equals(d)
     *     (old current() <= d) ==> current() == old current()
     *     !(old current() <= d) ==> current().equals(d)
     */
    void setMax(Dimension d);
    
    /**
     * Fixe la taille minimale autorisée.
     * Fixe max() ou current() si nécessaire.
     * @pre
     *     d != null
     *     new Dimension(0, 0) <= d
     * @post
     *     min().equals(d)
     *     (d <= old current()) ==> current() == old current()
     *     !(d <= old current()) ==> current().equals(d)
     *     (d <= old max()) ==> max() == old max()
     *     !(d <= old max()) ==> max().equals(d)
     */
    void setMin(Dimension d);
}
