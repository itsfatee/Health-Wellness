package com.esmt.controller.healthwellness;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class ConsultationListActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation_list);


        DatabaseHealth db = new DatabaseHealth(this);
        ArrayList<HashMap<String, String>> consList = db.GetConsult();
        ListView lv = (ListView) findViewById(R.id.consult_list);
        ListAdapter adapter = new SimpleAdapter(ConsultationListActivity.this, consList, R.layout.consult_row,new String[]{"doc_name"}, new int[]{R.id.Docname});
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(ConsultationListActivity.this,ConsultDetailsActivity.class);
                startActivity(intent);
            }
        });

    }



}
