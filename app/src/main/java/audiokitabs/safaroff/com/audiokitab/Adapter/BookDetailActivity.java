package audiokitabs.safaroff.com.audiokitab.Adapter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import audiokitabs.safaroff.com.audiokitab.Interface.BooksClient;
import audiokitabs.safaroff.com.audiokitab.Model.Author;
import audiokitabs.safaroff.com.audiokitab.Model.Detail;
import audiokitabs.safaroff.com.audiokitab.Model.MainPhoto;
import audiokitabs.safaroff.com.audiokitab.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookDetailActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageView full;
    ProgressBar progressBar;
    TextView detail_book_name,age_limit,detail_authors,detail_mb,detail_annotation,detail_mbs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        toolbar = (Toolbar) findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        full = (ImageView)findViewById(R.id.book_detail_image);
        progressBar = (ProgressBar)findViewById(R.id.book_detail_progress);
        detail_book_name = (TextView)findViewById(R.id.detail_book_name);
        age_limit = (TextView)findViewById(R.id.age_limit);
        detail_authors = (TextView)findViewById(R.id.detail_authors);
        detail_mb = (TextView)findViewById(R.id.detail_mb);
        detail_annotation = (TextView)findViewById(R.id.detail_annotation);
        detail_mbs = (TextView)findViewById(R.id.detail_hourss);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        String list = intent.getStringExtra("image");
        String name = intent.getStringExtra("name");
        //String hour = detail.getDuration_str();
        String mb = intent.getStringExtra("size");
        String annotation = intent.getStringExtra("annotation");
        String authors = null;
        StringBuilder nameBuilder = new StringBuilder();
//        if (detail.getAuthors().size() > 0) {
//            for (int i = 0;i<detail.getAuthors().size();i++){
//                    nameBuilder.append("").append(detail.getAuthors().
//                            get(i).getName().replace("'", "\\'")).append(",");
//
//                }
//                nameBuilder.deleteCharAt(nameBuilder.length() - 1);
//                    authors = nameBuilder.toString();
//                } else {
//                    authors = "";
//                }
                //detail_mbs.setText(hour);
                detail_annotation.setText(annotation);
                detail_mb.setText(mb);
               // age_limit.setText(String.valueOf(age));
                detail_authors.setText(authors);
                detail_book_name.setText(name);
                Picasso.get().load(list).into(full, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
            }



    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}
