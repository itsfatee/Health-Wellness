package com.esmt.controller.healthwellness;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AddAppointActivity extends AppCompatActivity {

    private DatePickerDialog picker;
    private TimePickerDialog pick;
    private EditText txtDate, txtTime, txtlName, txtfName, txtmail,txtDocName;
    String last_name,first_name,email,date_appoint,time_appoint,doctor;
    private Button btnBook;
    private DatabaseHealth db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appoint);

        txtDate = (EditText) findViewById(R.id.txtDate);
        txtDate.setInputType(InputType.TYPE_NULL);
        txtTime = (EditText) findViewById(R.id.txtTime);
        txtTime.setInputType(InputType.TYPE_NULL);
        txtlName = (EditText) findViewById(R.id.txtlName);
        txtfName = (EditText) findViewById(R.id.txtfName);
        txtmail = (EditText) findViewById(R.id.txtmail);
        txtDocName = (EditText) findViewById(R.id.txtDocName);
        btnBook = (Button) findViewById(R.id.btnBook);
        db = new DatabaseHealth(this);

        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                picker = new DatePickerDialog(AddAppointActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                txtDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, year, month, day);
                picker.show();

            }
        });

        txtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                pick = new TimePickerDialog(AddAppointActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                txtTime.setText(sHour + ":" + sMinute);
                            }
                        }, hour, minutes, true);
                pick.show();

            }
        });


        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                last_name = txtlName.getText().toString().trim();
                first_name = txtfName.getText().toString().trim();
                email = txtmail.getText().toString().trim();
                date_appoint = txtDate.getText().toString().trim();
                time_appoint = txtTime.getText().toString().trim();
                doctor = txtDocName.getText().toString().trim();


                checkData();
                if(db.addAppoint(last_name,first_name,email,date_appoint,time_appoint,doctor)){

                    AlertDialog.Builder builder = new AlertDialog.Builder(AddAppointActivity.this);
                    builder.setTitle(R.string.app_name);
                    builder.setIcon(R.mipmap.ic_launcher);
                    builder.setMessage("Successful booking, you're Doctor will send you an email to confirm")

                            .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();

                                    Intent intent = new Intent(AddAppointActivity.this, AppointmentListActivity.class);
                                    startActivity(intent);
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();

                    txtlName.setText("");
                    txtfName.setText("");
                    txtmail.setText("");
                    txtDate.setText("");
                    txtTime.setText("");
                    txtDocName.setText("");

                }else{

                }



            }
        });
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    void checkData() {

        if (isEmpty(txtlName)) {
            txtlName.setError("Last name is required!");
        }

        if (isEmpty(txtfName)) {
            txtfName.setError("First name is required!");
        }
        if (isEmpty(txtmail)) {
            txtmail.setError("Email is required!");
        }

        if (isEmpty(txtDate)) {
            txtDate.setError("Date is required!");
        }
        if (isEmpty(txtTime)) {
            txtTime.setError("Time is required!");
        }if (isEmpty(txtDocName)) {
            txtDocName.setError("Doctor's name is required!");
        }

    }
}

