package BE;

public class SubCategory
{
    private int SubCatID;
    private String SubCatName;
    private String SubCatContents;
    private int CategoryID;

    public SubCategory(int SubCatID, String SubCatName, String SubCatContents, int CategoryID){
        setSubCatID(SubCatID);
        setSubCatName(SubCatName);
        setSubCatContents(SubCatContents);
        setCategoryID(CategoryID);
    }

    public int getSubCatID() {
        return SubCatID;
    }

    public void setSubCatID(int subCatID) {
        SubCatID = subCatID;
    }

    public String getSubCatName() {
        return SubCatName;
    }

    public void setSubCatName(String subCatName) {
        SubCatName = subCatName;
    }

    public String getSubCatContents() {
        return SubCatContents;
    }

    public void setSubCatContents(String subCatContents) {
        SubCatContents = subCatContents;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int categoryID) {
        CategoryID = categoryID;
    }
}
