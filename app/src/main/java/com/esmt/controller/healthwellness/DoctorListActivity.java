package com.esmt.controller.healthwellness;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorListActivity extends AppCompatActivity {
    private DatabaseHealth bd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        DatabaseHealth db = new DatabaseHealth(this);
        ArrayList<HashMap<String, String>> docList = db.GetDoctors();
         ListView lv = (ListView) findViewById(R.id.doctor_list);
         ListAdapter adapter = new SimpleAdapter(DoctorListActivity.this, docList, R.layout.list_row,new String[]{"name","specialty","phone"}, new int[]{R.id.name, R.id.specialty, R.id.phone});
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(DoctorListActivity.this,DoctorDetailsActivity.class);
                startActivity(intent);
            }
        });







    }
}
