package BE;

public class CitizenCase
{
    private int id;
    private String citizenCaseContent;
    private String status;
    public CitizenCase(int id, String citizenCaseContent){
        this.id = id;
        setCitizenCaseContent(citizenCaseContent);
        setStatus(status);
    }

    public int getId()
    {
        return id;
    }

    public String getStatus(){return status;}

    public void setStatus(String status) {this.status = status;}

    public String getCitizenCaseContent()
    {
        return citizenCaseContent;
    }

    public void setCitizenCaseContent(String citizenCaseContent)
    {
        this.citizenCaseContent = citizenCaseContent;
    }
}
