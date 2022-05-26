package BE;

public class SubCategory
{
    private int subCatID;
    private String subCatName;
    private String subCatContents;
    private int currentState;
    private int citizensOwnAssesment;
    private int goal;

    public SubCategory(int subCatID, String subCatName, String subCatContents, int currentState, int citizensOwnAssesment, int goal){
        this.subCatID = subCatID;
        setSubCatName(subCatName);
        setSubCatContents(subCatContents);
        setCurrentState(currentState);
        setCitizensOwnAssesment(citizensOwnAssesment);
        setGoal(goal);
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

    public int getCurrentState() {
        return currentState;
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

    public int getCitizensOwnAssesment() {
        return citizensOwnAssesment;
    }

    public void setCitizensOwnAssesment(int citizensOwnAssesment) {
        this.citizensOwnAssesment = citizensOwnAssesment;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }
    @Override
    public String toString() {
        return subCatName;
    }
}
