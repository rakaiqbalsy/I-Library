package bso.android.simplemovieapps.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import bso.android.simplemovieapps.R;

public class DetailMovieActivity extends AppCompatActivity {
    TextView title,genre,year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        initialized();
        Intent intent = getIntent();
        String mTitle = intent.getStringExtra("title");
        String mGenre = intent.getStringExtra("genre");
        String mYear = intent.getStringExtra("year");

        title.setText(mTitle);
        genre.setText(mGenre);
        year.setText(mYear);
    }
    public void initialized(){
        title =(TextView) findViewById(R.id.tv_title);
        genre = (TextView) findViewById(R.id.tv_genre);
        year = (TextView) findViewById(R.id.tv_year);
    }
}
