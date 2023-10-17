package serie05;

/**
 * Modélisation des numéros de téléphone.
 * 
 * Rq : dans l'invariant ci-dessous, les parties entre crochets indiquent un
 *  élément optionnel si l'extension est vide.
 * 
 * @inv
 *     extension() != null
 *     international() est de la forme "+33 123456789~123" :
 *         "+33" + " " + digit(1) + ... + digit(9)[ + "~" + extension()]
 *     national() est de la forme "01 23 45 67 89 (123)" :
 *         "0" + digit(1) + " " + digit(2) + digit(3) + " "
 *          + ... + digit(8) + digit(9)[ + " (" + extension() + ")"]
 *     toString() est de la forme "1,2,3,4,5,6,7,8,9;123" :
 *          digit(1) + "," + digit(2) + "," + digit(3) + ","
 *          + ... + digit(8) + "," + digit(9)[ + ";" + extension()]
 * @cons
 *     $DESC$ Un numéro de téléphone qui correspond à n
 *     $ARGS$ String n
 *     $PRE$
 *         n != null
 *         n est reconnu par NUMBER_PATTERN
 *     $POST$
 *         digit(i) est le i-ème chiffre du numéro reconnu en n
 *         extension() est le numéro de poste reconnu en n
 */
public interface PhoneNumber {
    
    // CONSTANTES

    /**
     * Préfixe national.
     */
    String NATIONAL_PREFIX = "0";
    
    /**
     * Préfixe international.
     */
    String INTERNATIONAL_PREFIX = "+33";
    
    /**
     * Séparateurs autorisés : .-()~/ et espace, avec répétition.
     */
    String SEPARATORS = "[-.()~/ ]*";
    
    /**
     *  Nombre de chiffres entre le préfixe et le poste dans un numéro
     *   de téléphone.
     */
    int DIGITS_NB = 9;
    
    /**
     * Motif reconnaissant un numéro de téléphone (aucun groupe de capture).
     */
    String NUMBER_PATTERN =
            SEPARATORS
            + "(" + NATIONAL_PREFIX + "|\\" + INTERNATIONAL_PREFIX + ")?"
            + "(" + SEPARATORS + "\\d){" + DIGITS_NB + "}"
            + SEPARATORS + "\\d*"
            + SEPARATORS;
    
    // REQUETES

    /**
     * Le i-ème chiffre du numéro (hors préfixe).
     * @pre
     *     1 <= i <= DIGITS_NB
     */
    char digit(int i);

    /**
     * Le numéro de poste s'il y en a un, sinon la chaîne vide.
     */
    String extension();
    
    /**
     * Le numéro normalisé pour une communication internationale.
     */
    String international();

    /**
     * Le numéro normalisé pour une communication nationale.
     */
    String national();
    
    /**
     * Une représentation du numéro sous forme d'une chaîne de caractères.
     */
    @Override
    String toString();
}
