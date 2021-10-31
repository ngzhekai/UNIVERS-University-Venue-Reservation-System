package com.zhekai.universityvenuemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class ViewReservationActivity extends AppCompatActivity {

    Button refreshbtn, addbtn;
    ListView lv_reservationList;
    ArrayAdapter workArrayAdapter;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reservation);

        addbtn = (Button) findViewById(R.id.buttonAdd);
        refreshbtn = (Button) findViewById(R.id.btnRefresh);
        lv_reservationList = (ListView) findViewById(R.id.lvReservationView);
        //lv_reservationList = (RecyclerView) findViewById(R.id.lvReservationView);

        databaseHelper = new DatabaseHelper(ViewReservationActivity.this);

        ShowReservationOnListView(databaseHelper);

        refreshbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper = new DatabaseHelper(ViewReservationActivity.this);


                ShowReservationOnListView(databaseHelper);
            }
        });


        lv_reservationList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Work clickedReservation = (Work) parent.getItemAtPosition(position);

                new AlertDialog.Builder(ViewReservationActivity.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Delete this reservation ?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                boolean success = databaseHelper.deleteReservation(clickedReservation);
                                ShowReservationOnListView(databaseHelper);
                                //Toast.makeText(ViewReservationActivity.this, "Deleted " + clickedReservation.toString(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(ViewReservationActivity.this, "Success= " + success, Toast.LENGTH_SHORT).show();
                            }
                        }).setNegativeButton("No", null).show();
                return true;
            }
        });

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ReservationActivity.class);
                startActivity(intent);
            }
        });
    }

    private void ShowReservationOnListView(DatabaseHelper databaseHelper1) {
        workArrayAdapter = new ArrayAdapter<Work>(ViewReservationActivity.this, android.R.layout.simple_list_item_1, databaseHelper1.getEveryone());
        lv_reservationList.setAdapter(workArrayAdapter);
    }


}