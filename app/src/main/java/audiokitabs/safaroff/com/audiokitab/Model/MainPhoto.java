package audiokitabs.safaroff.com.audiokitab.Model;

public class MainPhoto {
    private String full_size;
    private String thumbnail;

    public MainPhoto() {
    }

    public MainPhoto(String full_size, String thumbnail) {
        this.full_size = full_size;
        this.thumbnail = thumbnail;
    }

    public String getFull_size() {
        return full_size;
    }

    public void setFull_size(String full_size) {
        this.full_size = full_size;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
