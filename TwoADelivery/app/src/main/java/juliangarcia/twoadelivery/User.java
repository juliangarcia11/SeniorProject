package juliangarcia.twoadelivery;

/**
 * Created by Julian on 2/19/2015.
 */
public class User {
    public long userId;
    public String username;
    public String password;

    public User(long userId, String username, String password){
        this.userId=userId;
        this.username=username;
        this.password=password;
    }
}
