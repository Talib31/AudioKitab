package audiokitabs.safaroff.com.audiokitab.Fragments;


import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import audiokitabs.safaroff.com.audiokitab.Adapter.SearchAdapter;
import audiokitabs.safaroff.com.audiokitab.Constants;
import audiokitabs.safaroff.com.audiokitab.Interface.BooksClient;
import audiokitabs.safaroff.com.audiokitab.Model.Book;
import audiokitabs.safaroff.com.audiokitab.Model.Example;
import audiokitabs.safaroff.com.audiokitab.Model.Results;
import audiokitabs.safaroff.com.audiokitab.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SearchFragment extends Fragment {

    View sFragment;
    RecyclerView recyclerView;
    List<Results> searchList;
    SearchAdapter adapter;
    BooksClient mService;
    ProgressBar searchProgress;

    public static SearchFragment newInstance(){
        SearchFragment searchFragment = new SearchFragment();
        return searchFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sFragment = inflater.inflate(R.layout.fragment_search,container,false);

        searchProgress = (ProgressBar)sFragment.findViewById(R.id.searchProgress);
        recyclerView = (RecyclerView)sFragment.findViewById(R.id.searchList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        fetchSearch("pir");

        return sFragment;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu,menu);

        SearchManager searchManager = (SearchManager)getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView)menu.findItem(R.id.action_search).getActionView();
        searchView.setQueryHint("Type something");

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
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
        return;

    }
    private void fetchSearch(String key) {
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("https://api.audiokitab.com")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        BooksClient client = retrofit.create(BooksClient.class);
//        Call<MyPojo> call = client.search(key);
//        call.enqueue(new Callback<MyPojo>() {
//            @Override
//            public void onResponse(Call<MyPojo> call, Response<MyPojo> response) {
//                searchProgress.setVisibility(View.GONE);
//                MyPojo book = response.body();
//                searchList = book.getBooks().getResults();
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        adapter = new SearchAdapter(getContext(), searchList);
//                        recyclerView.setAdapter(adapter);
//                        adapter.notifyDataSetChanged();
//                    }
//                });
//            }
//
//            @Override
//            public void onFailure(Call<MyPojo> call, Throwable t) {
//                Toast.makeText(getContext(), "error" + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
