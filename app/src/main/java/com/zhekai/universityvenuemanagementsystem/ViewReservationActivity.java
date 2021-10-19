package com.zhekai.universityvenuemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

public class ViewReservationActivity extends AppCompatActivity {

    Button refreshbtn;
    ListView lv_reservationList;
    ArrayAdapter workArrayAdapter;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reservation);

        refreshbtn = (Button) findViewById(R.id.btnRefresh);
        lv_reservationList = (ListView) findViewById(R.id.lvReservationView);

        databaseHelper = new DatabaseHelper(ViewReservationActivity.this);

        ShowReservationOnListView(databaseHelper);

        refreshbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper = new DatabaseHelper(ViewReservationActivity.this);


                ShowReservationOnListView(databaseHelper);
            }
        });

        lv_reservationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Work clickedReservation = (Work) parent.getItemAtPosition(position);
                databaseHelper.deleteReservation(clickedReservation);
                ShowReservationOnListView(databaseHelper);
                Toast.makeText(ViewReservationActivity.this, "Deleted " + clickedReservation.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ShowReservationOnListView(DatabaseHelper databaseHelper1) {
        workArrayAdapter = new ArrayAdapter<Work>(ViewReservationActivity.this, android.R.layout.simple_list_item_1, databaseHelper1.getEveryone());
        lv_reservationList.setAdapter(workArrayAdapter);
    }


}