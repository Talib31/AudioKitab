package audiokitabs.safaroff.com.audiokitab.Model;

import java.util.List;

public class Book {
    private int id;
    private String url;
    private String name;
    private MainPhoto main_photo;
    private List<String> authors = null;
    private double price;
    private String price_currency;
    private String annotation;
    private String size_str;

    public Book() {
    }

    public Book(int id, String url, String name, MainPhoto main_photo, List<String> authors, double price, String price_currency, String annotation, String size_str) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.main_photo = main_photo;
        this.authors = authors;
        this.price = price;
        this.price_currency = price_currency;
        this.annotation = annotation;
        this.size_str = size_str;
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

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPrice_currency() {
        return price_currency;
    }

    public void setPrice_currency(String price_currency) {
        this.price_currency = price_currency;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public String getSize_str() {
        return size_str;
    }

    public void setSize_str(String size_str) {
        this.size_str = size_str;
    }
}
