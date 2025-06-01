package validator;

import static org.junit.Assert.*;
import org.junit.Test;


public class ISBNValidateTest {

    // 1. ISBN-13 avec bruit, somme valide
    @Test
    public void testISBN13WithNoiseAndValidSum() {
        String input = " ISBN: 978-0 13--468599 1 ";
        String expected = "978-0-13-468599-1";
        assertEquals(expected, ISBNValidate.tidyISBN10or13InsertingDashes(input));
    }

    // 2. ISBN-10 propre, somme valide
    @Test
    public void testISBN10CleanAndValid() {
        String input = "0134685997";
        String expected = "0-13-468599-7";
        assertEquals(expected, ISBNValidate.tidyISBN10or13InsertingDashes(input));
    }

    // 3. ISBN-13 propre, somme invalide
    @Test
    public void testISBN13InvalidChecksum() {
        String input = "9780134685990";
        String expected = "ISBN invalide : mauvaise somme de contrôle";
        assertEquals(expected, ISBNValidate.tidyISBN10or13InsertingDashes(input));
    }

    // 4. ISBN-10 avec caractères spéciaux, somme valide
    @Test
    public void testISBN10WithSpecialCharsValid() {
        String input = "ISBN = 013-4685-997";
        String expected = "0-13-468599-7";
        assertEquals(expected, ISBNValidate.tidyISBN10or13InsertingDashes(input));
    }

    // 5. Entrée trop courte avec texte autour
    @Test
    public void testTooShortWithText() {
        String input = "code: 123456789";
        String expected = "ISBN invalide : longueur incorrecte";
        assertEquals(expected, ISBNValidate.tidyISBN10or13InsertingDashes(input));
    }

    // 6. Entrée trop longue avec bruit
    @Test
    public void testTooLongWithNoise() {
        String input = "ISBN: 9780134685991123";
        String expected = "ISBN invalide : longueur incorrecte";
        assertEquals(expected, ISBNValidate.tidyISBN10or13InsertingDashes(input));
    }

    // 7. Chaîne vide
    @Test
    public void testEmptyString() {
        String input = "";
        String expected = "ISBN invalide : vide";
        assertEquals(expected, ISBNValidate.tidyISBN10or13InsertingDashes(input));
    }

    // 8. null
    @Test
    public void testNullInput() {
        String expected = "ISBN invalide : vide";
        assertEquals(expected, ISBNValidate.tidyISBN10or13InsertingDashes(null));
    }
}