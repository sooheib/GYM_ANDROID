package selmibenromdhane.sparta_v1.manager;

import java.io.Serializable;

public class Friend_photos implements Serializable {
    private long id;
    private String album_name;
    private int photo;
    private int photo_count;

    public Friend_photos(long id, String album_name, int photo, int photo_count) {
        this.id = id;
        this.album_name = album_name;
        this.photo = photo;
        this.photo_count = photo_count;
    }

    public long getId() {
        return id;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public int getPhoto() {
        return photo;
    }

    public int getPhoto_count() {
        return photo_count;
    }

    public String getSnippets() {
        return getPhoto_count()+" Photos";
    }
}


