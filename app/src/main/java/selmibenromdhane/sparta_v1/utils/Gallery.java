package selmibenromdhane.sparta_v1.utils;

/**
 * Created by mounir on 30/12/2016.
 */

public class Gallery {

    private String nbLike;
    private String photo;
    private String photoUser;
    private String posted;
    private String user_name;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Gallery(String nbLike, String photo, String photoUser, String posted,String user_name) {
        this.nbLike = nbLike;
        this.photo = photo;
        this.photoUser = photoUser;
        this.posted = posted;
        this.user_name=user_name;
    }

    public Gallery() {
    }

    public String getNbLike() {
        return nbLike;
    }

    public void setNbLike(String nbLike) {
        this.nbLike = nbLike;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhotoUser() {
        return photoUser;
    }

    public void setPhotoUser(String photoUser) {
        this.photoUser = photoUser;
    }

    public String getPosted() {
        return posted;
    }

    public void setPosted(String posted) {
        this.posted = posted;
    }
}
