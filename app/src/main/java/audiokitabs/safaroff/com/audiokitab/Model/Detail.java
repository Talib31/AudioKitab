package audiokitabs.safaroff.com.audiokitab.Model;

import java.util.List;

public class Detail {
    private int id;
    private String url;
    private String name;
    private MainPhoto main_photo;
    private List<Author> authors = null;
    private List<Narrator> narrators = null;
    private List<Category> categories = null;
    private Double price;
    private String price_currency;
    private String size_str;
    private Integer age_limit;
    private String duration_str;
    private String created_at;
    private String annotation;

    public Detail() {
    }

    public Detail(int id, String url, String name, MainPhoto main_photo, List<Author> authors, List<Narrator> narrators, List<Category> categories, Double price, String price_currency, String size_str, Integer age_limit, String duration_str, String created_at, String annotation) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.main_photo = main_photo;
        this.authors = authors;
        this.narrators = narrators;
        this.categories = categories;
        this.price = price;
        this.price_currency = price_currency;
        this.size_str = size_str;
        this.age_limit = age_limit;
        this.duration_str = duration_str;
        this.created_at = created_at;
        this.annotation = annotation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MainPhoto getMain_photo() {
        return main_photo;
    }

    public void setMain_photo(MainPhoto main_photo) {
        this.main_photo = main_photo;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Narrator> getNarrators() {
        return narrators;
    }

    public void setNarrators(List<Narrator> narrators) {
        this.narrators = narrators;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPrice_currency() {
        return price_currency;
    }

    public void setPrice_currency(String price_currency) {
        this.price_currency = price_currency;
    }

    public String getSize_str() {
        return size_str;
    }

    public void setSize_str(String size_str) {
        this.size_str = size_str;
    }

    public Integer getAge_limit() {
        return age_limit;
    }

    public void setAge_limit(Integer age_limit) {
        this.age_limit = age_limit;
    }

    public String getDuration_str() {
        return duration_str;
    }

    public void setDuration_str(String duration_str) {
        this.duration_str = duration_str;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }
}
