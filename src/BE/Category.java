package BE;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Category
{
    public String CatName;
    public String CitizenId;
    public int ID;

    public Category(String catName, String CitizenID, int Id){
        setCatName(CatName);
        setCitizenId(CitizenID);
        setID(ID);
    }



    public String getCatName() {
       return CatName;
    }

    public void setCatName(String catName) {
        CatName = catName;
    }

    public String getCitizenID() {
        return CitizenId;
    }

    public void setCitizenId(String citizenId) {
        this.CitizenId = CitizenId;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
