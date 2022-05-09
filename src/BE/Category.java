package BE;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Category
{
    public String CatName;
    public int ID;

    public Category(String catName, int ID){
        setCatName(catName);
        this.ID = ID;
    }



    public String getCatName() {
       return CatName;
    }

    public void setCatName(String catName) {
        CatName = catName;
    }

    public int getID() {
        return ID;
    }

}
