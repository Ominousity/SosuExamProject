package BE;

public class CitizenCase
{
    private int id;
    private String name;
    private String caseContent;
    private String status;

    public CitizenCase(int id, String name, String caseContent, String status){
        this.id = id;
        setName(name);
        setCaseContent(caseContent);
        setStatus(status);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCaseContent() {
        return caseContent;
    }

    public void setCaseContent(String citizenCaseContent) {
        this.caseContent = citizenCaseContent;
    }
}
