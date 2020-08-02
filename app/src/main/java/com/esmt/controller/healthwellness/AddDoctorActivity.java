package com.esmt.controller.healthwellness;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddDoctorActivity extends AppCompatActivity {

    private EditText txtName,txtEMail,txtNumber,txtSpe;
    private Button btnRegDoc;
    String name,mail,number,specialty;
    private DatabaseHealth db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        txtName= (EditText)findViewById(R.id.txtName);
        txtEMail= (EditText)findViewById(R.id.txtEMail);
        txtNumber= (EditText)findViewById(R.id.txtNumber);
        txtSpe= (EditText)findViewById(R.id.txtSpe);
        btnRegDoc= (Button)findViewById(R.id.btnRegDoc);
        db = new DatabaseHealth(this);

        btnRegDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = txtName.getText().toString().trim();
                mail = txtEMail.getText().toString().trim();
                number = txtNumber.getText().toString().trim();
                specialty = txtSpe.getText().toString().trim();

                if(name.isEmpty() || mail.isEmpty()||number.isEmpty()||specialty.isEmpty()){

                    Toast.makeText(AddDoctorActivity.this,getString(R.string.error_fields),Toast.LENGTH_SHORT).show();

                }
                if(db.addDoctor(name,mail,number,specialty)){

                    Toast.makeText(AddDoctorActivity.this,"Doctor successfully added",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(AddDoctorActivity.this,DoctorListActivity.class);
                    startActivity(intent);

                    txtName.setText("");
                    txtEMail.setText("");
                    txtNumber.setText("");
                    txtSpe.setText("");


                }else{

                    Toast.makeText(AddDoctorActivity.this,"Doctor unsuccessfully added",Toast.LENGTH_SHORT).show();

                }


            }
        });
    }
}
