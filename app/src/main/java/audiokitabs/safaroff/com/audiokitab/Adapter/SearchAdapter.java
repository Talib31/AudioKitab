package audiokitabs.safaroff.com.audiokitab.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import audiokitabs.safaroff.com.audiokitab.Interface.OnClickListener;
import audiokitabs.safaroff.com.audiokitab.Model.Book;
import audiokitabs.safaroff.com.audiokitab.Model.Results;
import audiokitabs.safaroff.com.audiokitab.R;
import audiokitabs.safaroff.com.audiokitab.ViewHolder.BooksViewHolder;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {

    Context context;
    List<Results> books;

    public SearchAdapter(Context context, List<Results> books) {
        this.context = context;
        this.books = books;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Results book = books.get(position);
        final MyViewHolder viewHolder = (MyViewHolder) holder;
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
        //viewHolder.text_mb.setText(book);

        Picasso.get().load(book.getMain_photo().getThumbnail()).into(viewHolder.book_image, new Callback() {
            @Override
            public void onSuccess() {
                viewHolder.book_progress.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(context, "Could not load the image", Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.setOnClickListener(new OnClickListener() {
            @Override
            public void onClickEvent(View view, int position, boolean isLongClick) {
                Intent intent = new Intent(context, BookDetailActivity.class);
                intent.putExtra("image",books.get(position).getMain_photo().getFull_size());
                intent.putExtra("name",books.get(position).getName());
                intent.putExtra("annotation",books.get(position).getName());
                intent.putExtra("size",books.get(position).getPrice());

                intent.putExtra("id", books.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        public ImageView book_image,threedots;
        public TextView book_name,book_authors,text_mb,listen;
        public ProgressBar book_progress;
        private OnClickListener onClickListener;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            book_image = (ImageView)itemView.findViewById(R.id.book_image);
            threedots = (ImageView)itemView.findViewById(R.id.threedots);
            book_name = (TextView)itemView.findViewById(R.id.book_name);
            book_authors = (TextView)itemView.findViewById(R.id.book_author);
            text_mb = (TextView)itemView.findViewById(R.id.text_mb);
            listen = (TextView)itemView.findViewById(R.id.listen);
            book_progress = (ProgressBar)itemView.findViewById(R.id.book_progress);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }

        public void setOnClickListener(OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
        }

        @Override
        public void onClick(View view) {
            onClickListener.onClickEvent(view,getAdapterPosition(),true);
        }

        @Override
        public boolean onLongClick(View view) {
            onClickListener.onClickEvent(view,getAdapterPosition(),false);
            return true;
        }
    }
}
