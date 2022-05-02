package BE;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Category
{
    public String CatName;
    public int CitizenId;
    public int ID;

    public Category(String catName, int CitizenID, int Id){
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

    public int getCitizenID() {
        return CitizenId;
    }

    public void setCitizenId(int citizenId) {
        this.CitizenId = CitizenId;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
