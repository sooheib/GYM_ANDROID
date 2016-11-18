package selmibenromdhane.sparta_v1.manager;

/**
 * Created by sooheib on 11/7/16.
 */

public class Classes {

    int userID;
    String userName;
    String userProfession;


    public Classes() {
    }

    public Classes(int userID, String userName, String userProfession) {
        this.userID = userID;
        this.userName = userName;
        this.userProfession = userProfession;
    }

    public Classes(String userName, String userProfession) {
        this.userName = userName;
        this.userProfession = userProfession;
    }

    public int getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserProfession() {
        return userProfession;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserProfession(String userProfession) {
        this.userProfession = userProfession;
    }
}
