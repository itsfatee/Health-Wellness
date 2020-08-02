package com.esmt.controller.healthwellness;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {

    private Button btnRegister;
    private EditText txtLastName,txtFirstName,txtEmail,txtPassword,txtPhoneNumber;
    String lastname,firstname,email,password,phone;
    private DatabaseHealth db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);



        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        btnRegister = (Button)findViewById(R.id.btnRegister);
        txtLastName = (EditText)findViewById(R.id.txtLastName);
        txtFirstName = (EditText)findViewById(R.id.txtFirstName);
        txtEmail = (EditText)findViewById(R.id.txtEmail);
        txtPassword = (EditText)findViewById(R.id.txtPassword);
        txtPhoneNumber = (EditText)findViewById(R.id.txtPhone);
        db = new DatabaseHealth(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lastname = txtLastName.getText().toString().trim();
                firstname = txtFirstName.getText().toString().trim();
                email = txtEmail.getText().toString().trim();
                password = txtPassword.getText().toString().trim();
                phone = txtPhoneNumber.getText().toString().trim();

               if(lastname.isEmpty()|| firstname.isEmpty() || email.isEmpty() || password.isEmpty() || password.isEmpty() || phone.isEmpty()){

                   Toast.makeText(SignInActivity.this,getString(R.string.error_fields),Toast.LENGTH_SHORT).show();

               }

               if (db.addUser(lastname,firstname,email,password,phone)){

                    Toast.makeText(SignInActivity.this,getString(R.string.success_save),Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(SignInActivity.this,HomeMainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();

                }
                else{

                    Toast.makeText(SignInActivity.this,getString(R.string.error_save),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    /*boolean isEmail(EditText text){
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean checkDataEntered() {
        if (isEmpty(txtEmail)) {
            Toast t = Toast.makeText(this, getString(R.string.error_fields), Toast.LENGTH_SHORT);
            t.show();
        }

       /* if (isEmpty(txtPassword)) {
            txtEmail.setError("Password is required");
        }

        if (isEmpty(txtFirstName)) {
            txtEmail.setError("First name is required");
        }

        if (isEmpty(txtLastName)) {
            txtEmail.setError("Last name is required");
        }

        if (isEmpty(txtPhoneNumber)) {
            txtEmail.setError("Phone number is required");
        }

        if (isEmail(txtEmail) == false) {
            txtEmail.setError("Enter valid email!");
        }


        return checkDataEntered();
    }*/


}
