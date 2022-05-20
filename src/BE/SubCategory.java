package BE;

public class SubCategory
{
    private int SubCatID;
    private String SubCatName;
    private String SubCatContents;

    public SubCategory(int SubCatID, String SubCatName, String SubCatContents){
        this.SubCatID = SubCatID;
        setSubCatName(SubCatName);
        setSubCatContents(SubCatContents);
    }

    public int getSubCatID() {
        return SubCatID;
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

    @Override
    public String toString() {
        return SubCatName;
    }
}
