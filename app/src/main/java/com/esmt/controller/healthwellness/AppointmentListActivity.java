package com.esmt.controller.healthwellness;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class AppointmentListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_list);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        DatabaseHealth db = new DatabaseHealth(this);
        ArrayList<HashMap<String, String>> aptList = db.GetAppoint();
        ListView lv = (ListView) findViewById(R.id.appoint_list);
        ListAdapter adapter = new SimpleAdapter(AppointmentListActivity.this, aptList, R.layout.list_appoint_row,new String[]{"date_appoint","time_appoint"}, new int[]{ R.id.DateApt,R.id.TimeApt});
        lv.setAdapter(adapter);
    }
}
