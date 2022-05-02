package BE;

public class School
{
    private int SchoolID;
    private String SchoolName;

    public School (int SchoolID,String SchoolName){
        setSchoolID(SchoolID);
        setSchoolName(SchoolName);
    }


    public int getSchoolID() {
        return SchoolID;
    }

    public void setSchoolID(int schoolID) {
        SchoolID = schoolID;
    }

    public String getSchoolName() {
        return SchoolName;
    }

    public void setSchoolName(String schoolName) {
        SchoolName = schoolName;
    }
}

