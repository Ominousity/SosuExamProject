package BE;

public class CitizenCase
{
    private int id;
    private String citizenCaseContent;
    public CitizenCase(int id, String citizenCaseContent){
        this.id = id;
        setCitizenCaseContent(citizenCaseContent);
    }

    public int getId()
    {
        return id;
    }

    public String getCitizenCaseContent()
    {
        return citizenCaseContent;
    }

    public void setCitizenCaseContent(String citizenCaseContent)
    {
        this.citizenCaseContent = citizenCaseContent;
    }
}
