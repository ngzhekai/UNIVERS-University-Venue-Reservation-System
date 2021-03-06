package com.zhekai.universityvenuemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CardView reservation, btnViewReservation, btnSignOut, btnSearchReservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reservation = (CardView) findViewById(R.id.reservationButton);
        btnViewReservation = (CardView) findViewById(R.id.viewReservationBtn);
        btnSignOut = (CardView) findViewById(R.id.signOutBtn);
        btnSearchReservation = (CardView) findViewById(R.id.searchReservationBtn);


        reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ReservationActivity.class);
                startActivity(intent);
            }
        });

        btnViewReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ViewReservationActivity.class);
                startActivity(intent);
            }
        });

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Signing Out...", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "Sign Out Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), LoginActivity.class);
                startActivity(intent);
                finish(); // finish is to make sure it ends the current activity to prevent
                // user from clicking the return button.
            }
        });

        btnSearchReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }
}