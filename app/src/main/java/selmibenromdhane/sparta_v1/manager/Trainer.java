package selmibenromdhane.sparta_v1.manager;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sooheib on 11/12/16.
 */

public class Trainer implements Parcelable {

    public int employee_id;
    public String first_name;
    public String last_name;
    public String username;
    public String password;
    public String photo;
    public int admin;
    public String course_name;
    private List<String> interests = new ArrayList<>();
    private ArrayList<Trainer> items;


    public Trainer() {
    }

    public Trainer(String name, String photo ){
        this.last_name = name;
        this.photo = photo;

    }

    public Trainer(Parcel in){
        last_name=in.readString();
        photo=in.readString();
        employee_id=in.readInt();
        first_name=in.readString();
        username=in.readString();
        password=in.readString();
        admin=in.readInt();
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


    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public ArrayList<Trainer> getItems() {
        return items;
    }

    public void setItems(ArrayList<Trainer> items) {
        this.items = items;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(last_name);
        dest.writeString(photo);
        dest.writeString(first_name);
        dest.writeInt(employee_id);
        dest.writeInt(admin);
        dest.writeString(username);
        dest.writeString(password);

//        Bundle b = new Bundle();
//        b.putParcelableArrayList("items", items);
//        dest.writeBundle(b);

    }

    public static final Parcelable.Creator CREATOR=new Parcelable.Creator(){

        @Override
        public Trainer createFromParcel(Parcel source) {
//            Trainer category = new Trainer();
//            category.last_name = source.readString();
//            category.photo = source.readString();
//            Bundle b = source.readBundle(Trainer.class.getClassLoader());
//            category.items = b.getParcelableArrayList("items");

            return new Trainer(source);
        }

        @Override
        public Trainer[] newArray(int size) {
            return new Trainer[size];
        }
    };
}

