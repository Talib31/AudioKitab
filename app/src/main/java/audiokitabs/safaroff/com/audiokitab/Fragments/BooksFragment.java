package audiokitabs.safaroff.com.audiokitab.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import audiokitabs.safaroff.com.audiokitab.Adapter.BooksAdapter;
import audiokitabs.safaroff.com.audiokitab.Interface.BooksClient;
import audiokitabs.safaroff.com.audiokitab.Interface.ILoadMore;
import audiokitabs.safaroff.com.audiokitab.Interface.OnClickListener;
import audiokitabs.safaroff.com.audiokitab.Model.Book;
import audiokitabs.safaroff.com.audiokitab.Model.Example;
import audiokitabs.safaroff.com.audiokitab.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class BooksFragment extends Fragment {

    View bFragment;
    Toolbar toolbar;
    TextView mTitle;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    List<Book> items = new ArrayList<>();
    BooksAdapter adapter;
    ProgressBar searchProgressMain;
    public int number = 1;

    public static BooksFragment newInstance() {
        BooksFragment booksFragment = new BooksFragment();
        return booksFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bFragment = inflater.inflate(R.layout.fragment_books, container, false);

        toolbar = (Toolbar) bFragment.findViewById(R.id.toolbar_top);
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        searchProgressMain = (ProgressBar)bFragment.findViewById(R.id.searchProgressMain);
        swipeRefreshLayout = (SwipeRefreshLayout) bFragment.findViewById(R.id.swipe_refresh);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                loadFirst10Book();
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                items.clear();
                loadFirst10Book();
                setUpAdapter();
            }
        });

        recyclerView = (RecyclerView) bFragment.findViewById(R.id.books);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        setUpAdapter();

        return bFragment;
    }

    private void setUpAdapter() {
        adapter = new BooksAdapter(recyclerView, getActivity(), items);
        recyclerView.setAdapter(adapter);
        adapter.setiLoadMore(new ILoadMore() {
            @Override
            public void onLoadMore() {
                int count = number;
                if (items.size() <= 239) {
                    items.add(null);
                    adapter.notifyItemInserted(items.size() - 1);
                    loadNext10Book(count);
                    number++;
                } else {
                    Toast.makeText(getContext(), "Max book is 239", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void loadNext10Book(int index) {
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("https://api.audiokitab.com")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        swipeRefreshLayout.setRefreshing(true);
        BooksClient client = retrofit.create(BooksClient.class);
        Call<Example> call = client.getAllList(index);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Example example = response.body();
                final List<Book> books = example.getBooks();
                items.remove(items.size() - 1);
                adapter.notifyItemRemoved(items.size());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        searchProgressMain.setVisibility(View.GONE);
                        items.addAll(books);
                        adapter.setLoaded();
                        adapter.updateData(items);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(getContext(), "Went something wrong ! ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadFirst10Book() {
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("https://api.audiokitab.com")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        swipeRefreshLayout.setRefreshing(true);
        BooksClient client = retrofit.create(BooksClient.class);
        Call<Example> call = client.getBooks();
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Example example = response.body();
                final List<Book> books = example.getBooks();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        searchProgressMain.setVisibility(View.GONE);
                        adapter.updateData(books);

                    }
                });
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(getContext(), "Went something wrong ! ", Toast.LENGTH_SHORT).show();
            }
        });
        if (swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }
    }



}
