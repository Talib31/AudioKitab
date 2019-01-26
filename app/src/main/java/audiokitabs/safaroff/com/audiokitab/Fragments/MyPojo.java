package audiokitabs.safaroff.com.audiokitab.Fragments;

import java.util.List;

import audiokitabs.safaroff.com.audiokitab.Model.Author;
import audiokitabs.safaroff.com.audiokitab.Model.Books;
import audiokitabs.safaroff.com.audiokitab.Model.Narrators;

public class MyPojo {
    private Books books;
    private List<Author> authors = null;
    private List<Narrators> narrators = null;

    public MyPojo() {
    }

    public MyPojo(Books books, List<Author> authors, List<Narrators> narrators) {
        this.books = books;
        this.authors = authors;
        this.narrators = narrators;
    }

    public Books getBooks() {
        return books;
    }

    public void setBooks(Books books) {
        this.books = books;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Narrators> getNarrators() {
        return narrators;
    }

    public void setNarrators(List<Narrators> narrators) {
        this.narrators = narrators;
    }
}
