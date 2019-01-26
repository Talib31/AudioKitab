package audiokitabs.safaroff.com.audiokitab;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import audiokitabs.safaroff.com.audiokitab.Adapter.SearchAdapter;
import audiokitabs.safaroff.com.audiokitab.Fragments.MyPojo;
import audiokitabs.safaroff.com.audiokitab.Interface.BooksClient;
import audiokitabs.safaroff.com.audiokitab.Model.Book;
import audiokitabs.safaroff.com.audiokitab.Model.Books;
import audiokitabs.safaroff.com.audiokitab.Model.Results;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Results> searchList;
    SearchAdapter adapter;
    BooksClient mService;
    ProgressBar searchProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchProgress = (ProgressBar)findViewById(R.id.searchProgressing);
        recyclerView = (RecyclerView)findViewById(R.id.searchList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        fetchSearch("");
    }
    private void fetchSearch(String key) {
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("https://api.audiokitab.com")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        BooksClient client = retrofit.create(BooksClient.class);
        Call<MyPojo> call = client.search(key);
        call.enqueue(new Callback<MyPojo>() {
            @Override
            public void onResponse(Call<MyPojo> call, Response<MyPojo> response) {
                searchProgress.setVisibility(View.INVISIBLE);
                MyPojo books = response.body();
                searchList = books.getBooks().getResults();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new SearchAdapter(getApplicationContext(), searchList);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                });

            }

            @Override
            public void onFailure(Call<MyPojo> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);

        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView)menu.findItem(R.id.action_search).getActionView();
        //searchView.setQueryHint("Axtar");

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                fetchSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                fetchSearch(s);
                return false;
            }
        });
        return true;

    }
}
