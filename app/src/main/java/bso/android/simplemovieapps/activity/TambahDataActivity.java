package bso.android.simplemovieapps.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bso.android.simplemovieapps.R;
import bso.android.simplemovieapps.koneksi.KoneksiDatabase;

public class TambahDataActivity extends AppCompatActivity {
    KoneksiDatabase moviedb;
    EditText Id, Title, Genre, Year;
    String id,title,genre,year;
    Button insert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);

        moviedb =new KoneksiDatabase(this);
        Id = (EditText) findViewById(R.id.mId);
        Title = (EditText) findViewById(R.id.mTitle);
        Genre = (EditText) findViewById(R.id.mGenre);
        Year = (EditText) findViewById(R.id.mYear);
        insert = (Button) findViewById(R.id.inser);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                masukanData();
            }
        });
    }
    public void masukanData(){
        id = Id.getText().toString();
        title = Title.getText().toString();
        genre = Genre.getText().toString();
        year = Year.getText().toString();

        Boolean hasil = moviedb.MasukanData(id,title,genre,year);

        if (hasil== true){
            Toast.makeText(this,"Data berhasil di input",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(TambahDataActivity.this,LihatDataActivity.class);
            startActivity(i);
        }
        else{
            Toast.makeText(this,"Data gagal di input",Toast.LENGTH_SHORT).show();
        }
    }
}
