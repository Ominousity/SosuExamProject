package Test;

import UI.MVC.Controller.CreateCitizenController;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;

public class UnitTest {

    public UnitTest() throws IOException {
    }

    /**
     * Her tester vi vores string replacer i createCitizenController
     * vi tester om den kan fjerne semicolon fra hovedkategorier korrekt
     * (metoden kan lave fejl pga instancieret fxml elementer)
     * @throws IOException
     */
    @Test
    public void testCreateCategories() throws IOException {
        CreateCitizenController createCitizenController = new CreateCitizenController();
        String line = "Hello;There;";
        String actual = createCitizenController.removeBannedChars(line);
        String expected = "HelloThere";
        Assertions.assertEquals(expected, actual);
    }
}
