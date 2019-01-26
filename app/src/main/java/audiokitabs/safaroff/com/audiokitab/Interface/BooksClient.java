package audiokitabs.safaroff.com.audiokitab.Interface;

import java.util.List;

import audiokitabs.safaroff.com.audiokitab.Fragments.MyPojo;
import audiokitabs.safaroff.com.audiokitab.Model.Book;
import audiokitabs.safaroff.com.audiokitab.Model.Books;
import audiokitabs.safaroff.com.audiokitab.Model.Detail;
import audiokitabs.safaroff.com.audiokitab.Model.Example;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BooksClient {
    @GET("/core/api/books/")
    Call<Example> getBooks();

    @GET("/core/api/search/")
    Call<MyPojo> search(@Query("query") String query);

    @GET("/core/api/books/{id}/")
    Call<Detail> getDetail(@Path("id") String id);

    @GET("/core/api/books/")
    Call<Example> getAllList(@Query("page") int page);

}
