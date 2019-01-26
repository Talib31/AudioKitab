package audiokitabs.safaroff.com.audiokitab.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import audiokitabs.safaroff.com.audiokitab.Interface.OnClickListener;
import audiokitabs.safaroff.com.audiokitab.R;

public class BooksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{

    public ImageView book_image,threedots;
    public TextView book_name,book_authors,text_mb,listen;
    public ProgressBar book_progress;
    private OnClickListener onClickListener;

    public BooksViewHolder(@NonNull View itemView ) {
        super(itemView);
        book_image = (ImageView)itemView.findViewById(R.id.book_image);
        threedots = (ImageView)itemView.findViewById(R.id.threedots);
        book_name = (TextView)itemView.findViewById(R.id.book_name);
        book_authors = (TextView)itemView.findViewById(R.id.book_author);
        text_mb = (TextView)itemView.findViewById(R.id.text_mb);
        listen = (TextView)itemView.findViewById(R.id.listen);
        book_progress = (ProgressBar)itemView.findViewById(R.id.book_progress);
        this.onClickListener = onClickListener;

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public boolean onLongClick(View view) {
        onClickListener.onClickEvent(view,getAdapterPosition(),true);
        return true;
    }

    @Override
    public void onClick(View view) {
        onClickListener.onClickEvent(view,getAdapterPosition(),false);
    }
}
