package selmibenromdhane.sparta_v1.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sooheib on 11/12/16.
 */

public class Trainer {

    public int employee_id;
    public String first_name;
    public String last_name;
    public String username;
    public String password;
    public String photo;
    public int admin;
    private List<String> interests = new ArrayList<>();

    public Trainer() {
    }

    public Trainer(int employee_id, String last_name, String first_name, String username, String password, int admin) {
        this.employee_id = employee_id;
        this.last_name = last_name;
        this.first_name = first_name;
        this.username = username;
        this.password = password;
        this.admin = admin;
    }

    public Trainer(String last_name, String photo,String... interest) {
        this.last_name = last_name;
        this.photo = photo;
        interests.addAll(Arrays.asList(interest));
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getUsername() {
        return username;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPassword() {
        return password;
    }

    public int getAdmin() {
        return admin;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }
}
