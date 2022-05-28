package BE;

public class SubCategory
{
    private int subCatID;
    private String subCatName;
    private String subCatContents;

    public SubCategory(int subCatID, String subCatName, String subCatContents){
        this.subCatID = subCatID;
        setSubCatName(subCatName);
        setSubCatContents(subCatContents);
    }
    public int getSubCatID() {
        return subCatID;
    }
    public String getSubCatName() {
        return subCatName;
    }
    public void setSubCatName(String subCatName) {
        this.subCatName = subCatName;
    }
    public String getSubCatContents() {
        return subCatContents;
    }
    public void setSubCatContents(String subCatContents) {
        this.subCatContents = subCatContents;
    }
    @Override
    public String toString() {
        return subCatName;
    }
}
