package audiokitabs.safaroff.com.audiokitab.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import audiokitabs.safaroff.com.audiokitab.Interface.ILoadMore;
import audiokitabs.safaroff.com.audiokitab.Interface.OnClickListener;
import audiokitabs.safaroff.com.audiokitab.Model.Book;
import audiokitabs.safaroff.com.audiokitab.R;
import audiokitabs.safaroff.com.audiokitab.ViewHolder.BooksViewHolder;
import audiokitabs.safaroff.com.audiokitab.ViewHolder.LoadingViewHolder;

public class BooksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final int VIEW_TYPE_ITEM=0,VIEW_TYPE_LOADING=1;
    private boolean isLoading;
    Activity activity;
    private List<Book> books;
    ILoadMore iLoadMore;
    private OnClickListener onClickListener;
    private int visibleThreshold = 5,lastVisibleItem,totalItemCount;

    public BooksAdapter(RecyclerView recyclerView,Activity activity,List<Book> books) {
        this.activity = activity;
        this.books = books;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem+visibleThreshold)){
                    if (iLoadMore != null){
                        iLoadMore.onLoadMore();
                        isLoading = true;
                    }
                }
            }
        });

    }


    @Override
    public int getItemViewType(int position) {
        return books.get(position) ==null?VIEW_TYPE_LOADING:VIEW_TYPE_ITEM;
    }

    public void setiLoadMore(ILoadMore iLoadMore) {
        this.iLoadMore = iLoadMore;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         if (viewType == VIEW_TYPE_ITEM){
             View view = LayoutInflater.from(activity).inflate(R.layout.recycler_item,parent,false);
             return new BooksViewHolder(view);
         }else if (viewType == VIEW_TYPE_LOADING){
             View view = LayoutInflater.from(activity).inflate(R.layout.books_loading,parent,false);
             return new LoadingViewHolder(view);
         }
         return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BooksViewHolder){
            final Book book = books.get(position);
            final BooksViewHolder viewHolder = (BooksViewHolder)holder;
                    String book_author = null;

                    if (book.getAuthors().size() > 0) {
                        StringBuilder nameBuilder = new StringBuilder();

                        for (String n : book.getAuthors()) {
                            nameBuilder.append("").append(n.replace("'", "\\'")).append(",");
                            // can also do the following
                            // nameBuilder.append("'").append(n.replace("'", "''")).append("',");
                        }

                        nameBuilder.deleteCharAt(nameBuilder.length() - 1);

                        book_author = nameBuilder.toString();
                    } else {
                        book_author = "";
                    }


            viewHolder.book_name.setText(book.getName());
            viewHolder.book_authors.setText(book_author);
            viewHolder.text_mb.setText(book.getSize_str());

                    Picasso.get().load(book.getMain_photo().getThumbnail()).into(viewHolder.book_image, new Callback() {
                        @Override
                        public void onSuccess() {
                            viewHolder.book_progress.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onError(Exception e) {
                            Toast.makeText(activity, "Could not load the image", Toast.LENGTH_SHORT).show();
                        }
                    });
                    viewHolder.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClickEvent(View view, int position, boolean isLongClick) {
                            Intent intent = new Intent(activity, BookDetailActivity.class);
                            intent.putExtra("image",books.get(position).getMain_photo().getFull_size());
                            intent.putExtra("name",books.get(position).getName());
                            intent.putExtra("annotation",books.get(position).getAnnotation());
                            intent.putExtra("size",books.get(position).getSize_str());

                            intent.putExtra("id", books.get(position).getId());
                            activity.startActivity(intent);
                            activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        }
                    });
            }else if (holder instanceof LoadingViewHolder){
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder)holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }

            }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setLoaded() {
        isLoading = false;
    }
    public void updateData(List<Book> books){
        this.books = books;
        notifyDataSetChanged();
    }
}
