package BE;

public class School
{
    private String SchoolID;
    private String SchoolName;

    public School (String SchoolID,String SchoolName){
        setSchoolID(SchoolID);
        setSchoolName(SchoolName);
    }


    public String getSchoolID() {
        return SchoolID;
    }

    public void setSchoolID(String schoolID) {
        SchoolID = schoolID;
    }

    public String getSchoolName() {
        return SchoolName;
    }

    public void setSchoolName(String schoolName) {
        SchoolName = schoolName;
    }
}

