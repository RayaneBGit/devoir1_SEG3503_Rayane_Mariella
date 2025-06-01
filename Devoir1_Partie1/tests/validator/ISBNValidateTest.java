package validator;

import org.junit.Test;
import org.junit.Assert;

import static org.junit.Assert.fail;

/**
 * Cas de test T1 à T10 derivés de la table des classes d’équivalence.(voir document joint)
 * pour ISBNValidate.appendCheckDigitToISBN12
 */
public class ISBNValidateTest {

    @Test
    public void testT1_Valide12Chiffres() {
        // T1 : 12 chiffres (valide)
        String input = "978030640615";
        String attendu = "9780306406157";
        Assert.assertEquals(attendu, ISBNValidate.appendCheckDigitToISBN12(input));
    }

    @Test
    public void testT2_LettreDansISBN() {
        // Cas pas correctment gerés par la methode.. Le ISB devrait uniquement prendre des chiffres selon sa signature
        String input = "979123A56789";
        try {
            String result = ISBNValidate.appendCheckDigitToISBN12(input);
            fail("Expected IllegalArgumentException, mais la methode a accepté une entrée invalide: " + result);
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            fail("Expected IllegalArgumentException, but got: " + e.getClass().getSimpleName());
        }
    }
    @Test
    public void testT3_SymboleDansISBN() {
        // Cas pas correctment gerés par la methode.. Le ISB devrait uniquement prendre des chiffres selon sa signature
        String input = "979123&56789";
        try {
            String result = ISBNValidate.appendCheckDigitToISBN12(input);
            fail("Expected IllegalArgumentException, mais la methode a accepté une entrée invalide: " + result);
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            fail("Expected IllegalArgumentException, but got: " + e.getClass().getSimpleName());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testT4_TiretDansISBN() {
        // T4 : Contient un tiret (invalide)
        String input = "979-123456789";
        ISBNValidate.appendCheckDigitToISBN12(input);
    }
    @Test(expected = NullPointerException.class)
    public void testT5_Null() {
        // T6 : Entrée null (la méthode crash avec NullPointerException (mais ce cas pas géré explicitement))
        ISBNValidate.appendCheckDigitToISBN12(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testT6_EspaceDansISBN() {
        // T5 : Contient un espace à la fin (invalide)
        String input = "9771234567896 ";
        ISBNValidate.appendCheckDigitToISBN12(input);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testT7_TropCourt() {
        // T7 : Moins de 12 chiffres (invalide)
        String input = "97803064061";
        ISBNValidate.appendCheckDigitToISBN12(input);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testT8_TropLong() {
        // T8 : Plus de 12 chiffres (invalide)
        String input = "97803064061599";
        ISBNValidate.appendCheckDigitToISBN12(input);
    }
    @Test public void testT9_TousZero() {
        // T9 : Valeurs aux bornes/extremes (0)
        Assert.assertEquals("0000000000000", ISBNValidate.appendCheckDigitToISBN12("000000000000"));
    }

    @Test public void testT10_TousNeuf() {
        // T10 : Valeurs aux bornes/extremes (9)
        Assert.assertEquals("9999999999994", ISBNValidate.appendCheckDigitToISBN12("999999999999"));
    }
}
