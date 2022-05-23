package BE;



public class Category
{
    public int ID;
    public String catName;
    public boolean isFuncHealth;
    private String catColor;

    public Category(int ID, String catName, boolean isFuncHealth, String catColor){
        this.ID = ID;
        setCatName(catName);
        setIsFuncHealth(isFuncHealth);
        setCatColor(catColor);
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

    public String getCatColor() {
        return catColor;
    }

    public void setCatColor(String catColor) {
        this.catColor = catColor;
    }
}
