package com.esmt.controller.healthwellness;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ConsultationActivity extends AppCompatActivity {

    private EditText UserLName, UserFName, DocName, UserDesc;
    String last_name,first_name,doc_name,description;
    private Button btnConsult;
    private DatabaseHealth db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        UserFName = (EditText) findViewById(R.id.UserFName);
        UserLName = (EditText) findViewById(R.id.UserLName);
        DocName = (EditText) findViewById(R.id.DocName);
        UserDesc = (EditText) findViewById(R.id.UserDesc);
        btnConsult= (Button) findViewById(R.id.btnConsult);
        db = new DatabaseHealth(this);

        btnConsult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                last_name = UserLName.getText().toString().trim();
                first_name = UserFName.getText().toString().trim();
                doc_name = DocName.getText().toString().trim();
                description = UserDesc.getText().toString().trim();


                checkData();
                if(db.addConsult(last_name,first_name,doc_name,description)){

                    AlertDialog.Builder builder = new AlertDialog.Builder(ConsultationActivity.this);
                    builder.setTitle(R.string.app_name);
                    builder.setIcon(R.mipmap.ic_launcher);
                    builder.setMessage("Consultation successfully added")

                            .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();

                                    Intent intent = new Intent(ConsultationActivity.this, ConsultationListActivity.class);
                                    startActivity(intent);
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();

                    UserLName.setText("");
                    UserFName.setText("");
                    DocName.setText("");
                    UserDesc.setText("");

                }


            }
        });


    }


    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }


    void checkData() {

        if (isEmpty(UserLName)) {
            UserLName.setError("Last name is required!");
        }

        if (isEmpty(UserFName)) {
            UserFName.setError("First name is required!");
        }
        if (isEmpty(DocName)) {
            DocName.setError("Doctor name is required!");
        }

        if (isEmpty(UserDesc)) {
            UserDesc.setError("Description is required!");
        }


    }
}
