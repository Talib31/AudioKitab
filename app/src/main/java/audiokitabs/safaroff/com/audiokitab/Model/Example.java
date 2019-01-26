package audiokitabs.safaroff.com.audiokitab.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Example {
    private int count;
    private String next;
    private String previous;
    @SerializedName("books")
    private List<Book> books = null;

    public Example() {
    }

    public Example(int count, String next, String previous, List<Book> books) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.books = books;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
