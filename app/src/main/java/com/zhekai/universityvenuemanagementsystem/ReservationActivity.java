package com.zhekai.universityvenuemanagementsystem;

import static com.zhekai.universityvenuemanagementsystem.LoginActivity.UserId;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class ReservationActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnDatePicker, btnTimePicker1, btnTimePicker2, btnSubmit;
    EditText phoneNumber;
    TextView txtDate, txtTime1, txtTime2;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        Spinner venuelist = (Spinner) findViewById(R.id.venueIDList);
        btnDatePicker = (Button) findViewById(R.id.btn_date);
        btnTimePicker1 = (Button) findViewById(R.id.btn_time);
        btnTimePicker2 = (Button) findViewById(R.id.btn_time2);
        btnSubmit = (Button) findViewById(R.id.btn_Submit);
        txtDate = (TextView) findViewById(R.id.in_date);
        txtTime1 = (TextView) findViewById(R.id.start_time);
        txtTime2 = (TextView) findViewById(R.id.end_time);
        phoneNumber = (EditText) findViewById(R.id.contactNum);

        phoneNumber.addTextChangedListener(new PhoneNumberFormattingTextWatcher("MY"));

        btnDatePicker.setOnClickListener(this);
        btnTimePicker1.setOnClickListener(this);
        btnTimePicker2.setOnClickListener(this);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(ReservationActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.venues));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        venuelist.setAdapter(myAdapter);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Work work1 = new Work();

                try {
                    work1 = new Work(-1, phoneNumber.getText().toString(),
                            venuelist.getSelectedItem().toString(), txtDate.getText().toString(),
                            txtTime1.getText().toString(), txtTime2.getText().toString(), UserId);

                    //Toast.makeText(ReservationActivity.this, work1.toString(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(ReservationActivity.this,
                            "Error Creating Reservation! ", Toast.LENGTH_SHORT).show();

                }

                DatabaseHelper databaseHelper = new DatabaseHelper(ReservationActivity.this);

                boolean success = databaseHelper.addReservation(work1);

                Toast.makeText(ReservationActivity.this, "Success= " + success,
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
                finish(); //to prevent user from coming back to this activity when back button is clicked
            }
        });
    }


    @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            //this line below to ignore input for past date.
            datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
            datePickerDialog.show();
        }
        if (v == btnTimePicker1) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTime1.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if (v == btnTimePicker2) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTime2.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }

    }
}