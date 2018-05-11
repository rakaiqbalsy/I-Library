package bso.android.simplemovieapps.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import bso.android.simplemovieapps.R;
import bso.android.simplemovieapps.adapter.DownloadAdapter;

/**
 * Created by rakaiqbalsy on 5/11/18.
 */

public class DownloadActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_list_row);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String fileName = dataSnapshot.getKey();
                String url = dataSnapshot.getValue(String.class);

                ((DownloadAdapter)recyclerView.getAdapter()).update(fileName, url);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);


        recyclerView.setLayoutManager(new LinearLayoutManager(DownloadActivity.this));
        DownloadAdapter downloadAdapter = new DownloadAdapter(recyclerView, DownloadActivity.this, new ArrayList<String>(), new ArrayList<String>());
        recyclerView.setAdapter(downloadAdapter);
    }

    }
