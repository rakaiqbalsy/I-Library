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

public class EditDataActivity extends AppCompatActivity {
    KoneksiDatabase moviedb;
    EditText Id, Title, Genre, Year;
    Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        moviedb = new KoneksiDatabase(this);
        Title = (EditText) findViewById(R.id.mTitle);
        Genre = (EditText) findViewById(R.id.mGenre);
        Year = (EditText) findViewById(R.id.mYear);
        Id = (EditText) findViewById(R.id.mId);
        edit = (Button) findViewById(R.id.edit);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateData();
            }
        });
    }

    public void UpdateData() {
        String id = Id.getText().toString();
        String title = Title.getText().toString();
        String genre = Genre.getText().toString();
        String years = Year.getText().toString();

        Boolean hasil = moviedb.EditData(id, title, genre, years);

        if (hasil == true) {
            Toast.makeText(this, "Data berhasil di Update", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(EditDataActivity.this,LihatDataActivity.class);
            startActivity(i);
        } else {
            Toast.makeText(this, "Data gagal di Update", Toast.LENGTH_SHORT).show();
        }
    }
}
