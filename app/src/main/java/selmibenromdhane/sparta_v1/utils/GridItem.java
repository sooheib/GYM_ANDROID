package selmibenromdhane.sparta_v1.utils;

public class GridItem {
    private String image;
    private String title;
    private String imageUser;

    public GridItem() {
        super();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public GridItem(String title, String image) {
        this.title = title;
        this.image = image;
    }

    public String getImageUser() {
        return imageUser;
    }

    public void setImageUser(String imageUser) {
        this.imageUser = imageUser;
    }

    public GridItem(String image, String title, String imageUser) {

        this.image = image;
        this.title = title;
        this.imageUser = imageUser;
    }
}
