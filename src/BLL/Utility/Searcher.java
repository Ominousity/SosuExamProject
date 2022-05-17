package BLL.Utility;

import BE.User;
import BLL.UserManager;
import javafx.collections.transformation.FilteredList;

import java.sql.SQLException;
import java.util.Locale;

public class Searcher
{
    UserManager userManager;
    User user;

    private String observable1;
    private String oldValue1;
    private String newValue1;


   //FilteredList<> filteredList = new FilteredList<>(userManager.getAllUsers(), b -> true);

   /* public Searcher() throws SQLException {
        txtFieldSearch.textProperty().addListener((observable1, oldValue1, newValue1) -> {
            filteredList.setPredicate(user -> {

                if (newValue1 == null || newValue1.isEmpty())
                {
                    return true;
                }

                String lowerCaseFilter = newValue1.toLowerCase();

                if(user.getFName.toLowerCase.indexof(lowerCaseFilter) != -1), user.getLName.toLowerCase.indexof(lowerCaseFilter) != -1)
                {
                    return true;
                }
                {
                    return false;
                }
            });
        });
    }*/
}
