package audiokitabs.safaroff.com.audiokitab.Model;

import java.util.List;

public class Results {
    private double price;
    private String name;
    private int id;
    private String url;
    private String price_currency;
    private MainPhoto main_photo;
    private List<String> authors = null;

    public Results() {
    }

    public Results(double price, String name, int id, String url, String price_currency, MainPhoto main_photo, List<String> authors) {
        this.price = price;
        this.name = name;
        this.id = id;
        this.url = url;
        this.price_currency = price_currency;
        this.main_photo = main_photo;
        this.authors = authors;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPrice_currency() {
        return price_currency;
    }

    public void setPrice_currency(String price_currency) {
        this.price_currency = price_currency;
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
}
