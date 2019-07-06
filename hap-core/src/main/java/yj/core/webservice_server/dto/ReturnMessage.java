package yj.core.webservice_server.dto;

public class ReturnMessage
{
    private String syncdate;
    private String flag;
    private String message;

    public String getSyncdate()
    {
        return this.syncdate;
    }

    public void setSyncdate(String syncdate)
    {
        this.syncdate = syncdate;
    }

    public String getFlag()
    {
        return this.flag;
    }

    public void setFlag(String flag)
    {
        this.flag = flag;
    }

    public String getMessage()
    {
        return this.message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
