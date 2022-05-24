package BE;

public class Category
{
    public int ID;
    public String catName;
    public boolean isFuncHealth;
    private String catColor;

    public Category(int ID, String catName, boolean isFuncHealth){
        this.ID = ID;
        setCatName(catName);
        setIsFuncHealth(isFuncHealth);
    }

    public int getID() {
        return ID;
    }

    public String getCatName() {
       return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public boolean getIsFuncHealth(){
        return isFuncHealth;
    }

    public void setIsFuncHealth(boolean isFuncHealth){
        this.isFuncHealth = isFuncHealth;
    }

}
