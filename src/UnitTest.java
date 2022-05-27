import UI.MVC.Controller.CreateCitizenController;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;

public class UnitTest {

    CreateCitizenController createCitizenController = new CreateCitizenController();

    public UnitTest() throws IOException {
    }

    @Test
    public void testCreateCategories(){

        String actual = null;
        String expected = null;
        Assertions.assertEquals(expected, actual);
    }
}
