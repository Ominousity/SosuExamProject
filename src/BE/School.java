package BE;

public class School
{
    private int schoolID;
    private String schoolName;

    public School (int schoolID,String schoolName){
        setSchoolID(schoolID);
        setSchoolName(schoolName);
    }


    public int getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Override
    public String toString() {
        return schoolName;
    }
}

