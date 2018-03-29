package bso.android.simplemovieapps.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import bso.android.simplemovieapps.R;

public class DashboardActivity extends AppCompatActivity {
    Button tdata,ldata,edata,hdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        tdata = (Button) findViewById(R.id.tdata);
        ldata = (Button) findViewById(R.id.ldata);
        hdata = (Button) findViewById(R.id.hdata);
        edata = (Button) findViewById(R.id.edata);

        tdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashboardActivity.this,TambahDataActivity.class);
                startActivity(i);
            }
        });

        edata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(DashboardActivity.this,EditDataActivity.class);
                startActivity(i);
            }
        });

        ldata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(DashboardActivity.this,LihatDataActivity.class);
                startActivity(i);
            }
        });

        hdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(DashboardActivity.this,HapusDataActivity.class);
                startActivity(i);
            }
        });

    }
}
