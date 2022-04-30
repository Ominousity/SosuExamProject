package BE;

public class SubCategory
{
    private String SubCatID;
    private String SubCatName;
    private String SubCatContents;
    private String CategoryID;

    public SubCategory(String SubCatID, String SubCatName, String SubCatContents,String CategoryID){
        setSubCatID(SubCatID);
        setSubCatName(SubCatName);
        setSubCatContents(SubCatContents);
        setCategoryID(CategoryID);
    }

    public String getSubCatID() {
        return SubCatID;
    }

    public void setSubCatID(String subCatID) {
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

    public String getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(String categoryID) {
        CategoryID = categoryID;
    }
}
