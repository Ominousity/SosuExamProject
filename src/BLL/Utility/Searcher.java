package BLL.Utility;

import BE.Citizen;
import BE.User;
import BLL.UserManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

public class Searcher
{
    UserManager userManager;
    User user;
    Citizen citizen;

    private String observable1;
    private String oldValue1;
    private String newValue1;


    public ObservableList<User> search(List<User> searchBase, String query) {
        ObservableList<User> searchResult = FXCollections.observableArrayList();

        for (User user : searchBase) {
            if(compareToUserFName(user, query) || compareToCitizen(user, query))
            {
                searchResult.add(user);
            }
        }
        return searchResult;
    }

    public boolean compareToUserFName(User user, String query) {
        return user.getFName().toLowerCase().contains(query.toLowerCase());
    }

    public boolean compareToCitizen(User user, String query) {
        return citizen.getFName().toLowerCase().contains(query.toLowerCase());
    }


    
  /** FilteredList<> filteredList = new FilteredList<>(userManager.getAllUsers(), b -> true);

    public Searcher() throws SQLException {
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
    } */
}
