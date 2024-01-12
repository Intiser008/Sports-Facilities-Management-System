package authentication;
import java.util.UUID;
import user.*;

public class Session {
    User user;
    UUID sessionId;
    Boolean isValid;

    public Session(User user){
        this.user=user;
        this.isValid=true;
        this.sessionId=generateSessionId();
    }

    private UUID generateSessionId(){
        return UUID.randomUUID();
    }

    public Boolean getIsValid(){
        return this.isValid;
    }

    public void setIsValid(Boolean bool){
        this.isValid=bool;
    }

    public User getUser(){
        return this.user;
    }
}


