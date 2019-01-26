package audiokitabs.safaroff.com.audiokitab.Model;

import java.util.List;

public class Narrators {
    private String book_count;
    private String name;
    private String photo;
    private String description;
    private String id;
    private List<String> categories = null;
    private String url;

    public Narrators() {
    }

    public Narrators(String book_count, String name, String photo, String description, String id, List<String> categories, String url) {
        this.book_count = book_count;
        this.name = name;
        this.photo = photo;
        this.description = description;
        this.id = id;
        this.categories = categories;
        this.url = url;
    }

    public String getBook_count() {
        return book_count;
    }

    public void setBook_count(String book_count) {
        this.book_count = book_count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
