package selmibenromdhane.sparta_v1.manager;

import java.io.Serializable;

/**
 * Created by sooheib on 11/20/16.
 */

public class Client implements Serializable {

    String client_id;
    String client_name;
    String client_email;
    String client_password;
    String client_photo;

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getClient_email() {
        return client_email;
    }

    public void setClient_email(String client_email) {
        this.client_email = client_email;
    }

    public String getClient_password() {
        return client_password;
    }

    public void setClient_password(String client_password) {
        this.client_password = client_password;
    }

    public String getClient_photo() {
        return client_photo;
    }

    public void setClient_photo(String client_photo) {
        this.client_photo = client_photo;
    }
}