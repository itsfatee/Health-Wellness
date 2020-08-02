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

public class HomeMainActivity extends AppCompatActivity {

    private Button btnLogin,btnSignIn;
    private EditText txtMail, txtPwd;
    String mail,password;
    String TempPassword = "NOT_FOUND" ;
    private DatabaseHealth db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);



        txtMail= (EditText)findViewById(R.id.txtMail);
        txtPwd= (EditText)findViewById(R.id.txtPwd);
        db = new DatabaseHealth(this);

        btnLogin= (Button)findViewById(R.id.btnLogin);
        btnSignIn= (Button)findViewById(R.id.btnSignIn);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDataEntered();
                mail = txtMail.getText().toString().trim();
                password = txtPwd.getText().toString().trim();

                if(mail.equals("manager@admin.com") && password.equals("adminroot")){

                    Intent intent = new Intent(HomeMainActivity.this,HomeManagerActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    txtMail.setText("");
                    txtPwd.setText("");
                    //finish();
                }else if (mail.equals("user@test.com") && password.equals("usertest")){
                    Intent intent = new Intent(HomeMainActivity.this,HomeUserActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    txtMail.setText("");
                    txtPwd.setText("");

                }else if (db.checkUser(mail,password)){

                    Intent intent = new Intent(HomeMainActivity.this,HomeUserActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    txtMail.setText("");
                    txtPwd.setText("");

                }
                else {

                    Toast.makeText(HomeMainActivity.this,getString(R.string.error_connexion),Toast.LENGTH_SHORT).show();
                }
            }

        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMainActivity.this,SignInActivity.class);
                startActivity(intent);
            }
        });


    }

    boolean isEmail(EditText text){
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    void checkDataEntered() {
        if (isEmpty(txtMail)) {
            Toast t = Toast.makeText(this, "Email is required", Toast.LENGTH_SHORT);
            t.show();
        }

        if (isEmpty(txtPwd)) {
            txtPwd.setError("Password is required!");
        }

        if (isEmail(txtMail) == false) {
            txtMail.setError("Enter valid email!");
        }


    }

    // Checking entered password from SQLite database email associated password.
    public void CheckFinalResult(){

        if(TempPassword.equalsIgnoreCase(password))
        {

            Toast.makeText(HomeMainActivity.this,"Login Successfully",Toast.LENGTH_LONG).show();

            // Going to Dashboard activity after login success message.
            Intent intent = new Intent(HomeMainActivity.this, HomeUserActivity.class);
            // Sending Email to Dashboard Activity using intent.
           // intent.putExtra(mail, password);
            startActivity(intent);

        }
        else {

            Toast.makeText(HomeMainActivity.this,"Email or Password is Wrong, Please Try Again.",Toast.LENGTH_LONG).show();

        }
        TempPassword = "NOT_FOUND" ;

    }
}
