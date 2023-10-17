package serie05;

import java.io.File;
import java.io.IOException;

import serie04.Civ;
import serie04.Contact;
import serie04.PhoneBook;

/**
 * Modélise un annuaire téléphonique permettant d'associer une liste de
 *  numéros de téléphone à un <code>Contact</code> et de charger à partir de,
 *  ou de sauvegarder dans, un fichier de texte.
 * Une entrée au plus par contact.
 * Les fichiers textes associés aux annuaires doivent impérativement
 *  respecter le format suivant :
 * <ul>
 *    <li>Une entrée par ligne</li>
 *    <li>Une ligne est constituée, dans cet ordre,
 *       <ol>
 *          <li>d'un entier entre 0 et le nombre de civilités moins un
 *              suivi du caractère ':'</li>
 *          <li>d'un nom suivi du caractère ':'</li>
 *          <li>d'un prénom suivi du caractère ':'</li>
 *          <li>d'un numéro de téléphone</li>
 *          <li>[d'une virgule suivie d'un numéro de téléphone]*</li>
 *       </ol>
 *    </li>
 * </ul>
 * 
 * @cons <pre>
 * $DESC$
 *     Un annuaire éventuellement associé à un fichier de chemin 'file' et doté
 *     d'une fabrique 'df'.
 * $ARGS$
 *     DataFactory<C, N> df, File file
 * $PRE$
 *     df != null
 * $POST$
 *     getFile() == file
 *     isEmpty() </pre>
 *     
 * @cons <pre>
 * $DESC$
 *     Un annuaire sans fichier associé, doté d'une fabrique 'df'.
 * $ARGS$
 *     DataFactory<C, N> df
 * $PRE$
 *     df != null
 * $POST$
 *     getFile() == null
 *     isEmpty() </pre>
 */
public interface PersistentPhoneBook<
            C extends Contact & Comparable<C>,
            N extends PhoneNumber>
        extends PhoneBook<C, N> {
    
    // CONSTANTES
    
    /**
     * Séparateur de champs (civilité, nom, prénom, liste de numéros).
     */
    String FSEP = ":";
    
    /**
     * Séparateur de numéros de téléphones.
     */
    String NSEP = ",";
    
    /**
     * Le motif qui permet de tester la validité d'une ligne du fichier.
     */
    String LINE_PATTERN =
            // <début de ligne> <civilité> <:>
            "^\\s*[0-" + (Civ.values().length - 1) + "]\\s*" + FSEP
            // <nom> <:> <prénom> <:>
            + "([^" + FSEP + "]*" + FSEP + "[^" + FSEP + "]+" + FSEP
            + "|" + "[^" + FSEP + "]+" + FSEP + "[^" + FSEP + "]*" + FSEP + ")"
            // <numéro de téléphone>
            + PhoneNumber.NUMBER_PATTERN
            // (<,> <numéro de téléphone>)* <fin de ligne>
            + "(" + NSEP + PhoneNumber.NUMBER_PATTERN + ")*$";

    // REQUETES
    
    /**
     * Retourne le chemin du fichier de sauvegarde associé à cet annuaire.
     * Retourne null s'il n'y a pas de fichier associé.
     */
    File getFile();
    
    // COMMANDES
    
    /**
     * Remplace le contenu de cet annuaire par celui du fichier texte.
     * Si le chargement est impossible, une BadSyntaxException est levée et
     *  l'annuaire est alors vide.
     * @pre
     *     getFile() != null
     * @post
     *     L'annuaire a été réinitialisé avec le contenu du fichier associé
     *      si tout s'est bien passé
     *     L'annuaire est vide si une exception a été levée
     * @throws BadSyntaxException
     *     si le fichier associé est mal formé
     * @throws java.io.FileNotFoundException
     *     si le fichier associé n'existe pas ou n'est pas accessible en lecture
     * @throws IOException
     *     s'il se produit une erreur d'entrée/sortie lors de la lecture
     *      du fichier associé
     * @throws Error
     *     s'il est impossible de créer un contact ou un numéro de téléphone
     */
    void load() throws IOException, BadSyntaxException;
    
    /**
     * Sauvegarde le contenu de l'annuaire dans son fichier associé.
     * @pre
     *     getFile() != null
     * @post
     *     Le fichier associé est un fichier de texte dont le contenu
     *      (correctement formé) est celui de l'annuaire si tout
     *      s'est bien passé.
     *     Les numéros de téléphone sont sauvegardés au format national.
     *     Il ne doit y avoir aucun blanc superflu.
     * @throws java.io.FileNotFoundException
     *     si le fichier associé ne peut pas être créé ou n'est pas accessible
     *      en écriture
     * @throws IOException
     *     s'il se produit une erreur d'entrée/sortie lors de l'écriture
     *      dans le fichier associé
     */
    void save() throws IOException;
    
    /**
     * Change de chemin de fichier de sauvegarde associé à cet annuaire.
     * @pre
     *     file != null
     * @post
     *     getFile() == file
     */
    void setFile(File file);
}
