package serie05;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import serie04.Civ;
import serie04.Contact;

/**
 * Fabrique de contacts et de numéros de téléphones respectant les
 *  spécifications de constructeurs données dans les interfaces Contact et
 *  PhoneNumber.
 */
class DataFactory<C extends Contact & Comparable<C>, N extends PhoneNumber> {

    private final Class<C> contactType;
    private final Class<N> phoneNumberType;
    
    /**
     * Une fabrique pour les types contactType et phoneNumberType.
     */
    DataFactory(Class<C> contactType, Class<N> phoneNumberType) {
        this.contactType = contactType;
        this.phoneNumberType = phoneNumberType;
    }

    /**
     * Retourne une instance de C si possible, sinon lève une erreur.
     */
    C createContact(Civ civ, String lastName, String firstName) {
        C result = null;
        try {
            Constructor<C> cons = contactType.getConstructor(Civ.class,
                    String.class, String.class);
            result = cons.newInstance(civ, lastName, firstName);
        } catch (SecurityException e) {
            throw new Error(
                    "Le constructeur StdContact n'est probablement pas public",
                    e);
        } catch (NoSuchMethodException e) {
            throw new Error(
                    "Le constructeur StdContact(Civ, String, String)"
                    + " n'existe pas",
                    e);
        } catch (IllegalArgumentException e) {
            throw new Error(
                    "Le constructeur StdContact(Civ, String, String)"
                    + " n'existe pas",
                    e);
        } catch (InstantiationException e) {
            throw new Error("Impossible de créer un StdContact", e);
        } catch (IllegalAccessException e) {
            throw new Error("La classe StdContact n'est pas concrète", e);
        } catch (InvocationTargetException e) {
//            throw new Error("Le constructeur de StdContact a levé"
//                    + " une exception",
//                    e);
        }
        return result;
    }

    /**
     * Retourne une instance de N si possible, sinon lève une erreur.
     */
    N createPhoneNumber(String num) {
        N result = null;
        try {
            Constructor<N> cons = phoneNumberType.getConstructor(String.class);
            result = cons.newInstance(num);
        } catch (SecurityException e) {
            throw new Error(
                    "Le constructeur StdPhoneNUmber n'est probablement"
                    + " pas public",
                    e);
        } catch (NoSuchMethodException e) {
            throw new Error(
                    "Le constructeur StdPhoneNumber(String) n'existe pas", e);
        } catch (IllegalArgumentException e) {
            throw new Error(
                    "Le constructeur StdPhoneNumber(String) n'existe pas", e);
        } catch (InstantiationException e) {
            throw new Error("Impossible de créer un StdPhoneNumber", e);
        } catch (IllegalAccessException e) {
            throw new Error("La classe StdPhoneNumber n'est pas concrète", e);
        } catch (InvocationTargetException e) {
            throw new Error("Le constructeur de StdhoneNumber a levé"
                    + " une exception",
                    e);
        }
        return result;
    }
}
