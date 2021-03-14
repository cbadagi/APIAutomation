package ResponseModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.testng.annotations.Test;

public class AddBookResponse
{
    @JsonProperty("Msg")
    private String Msg;
    @JsonProperty("ID")
    private String ID;
    @JsonProperty("msg")
    private String Duplicatemsg;

    public String getDuplicatemsg() {
        return Duplicatemsg;
    }

    public void setDuplicatemsg(String duplicatemsg) {
        Duplicatemsg = duplicatemsg;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }


}
