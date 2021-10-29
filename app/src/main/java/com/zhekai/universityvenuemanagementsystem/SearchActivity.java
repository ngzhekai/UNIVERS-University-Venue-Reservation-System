package com.zhekai.universityvenuemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

public class SearchActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    ListView lv_searchList;
    //Spinner venuelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        lv_searchList = (ListView) findViewById(R.id.lvSearchView);
        Spinner venuelist = (Spinner) findViewById(R.id.searchvenuelist);
        Button refreshBtn = (Button) findViewById(R.id.refreshBtn);

        ArrayAdapter<String> myadapter = new ArrayAdapter<String>(SearchActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.venues));
        venuelist.setAdapter(myadapter);

        databaseHelper = new DatabaseHelper(SearchActivity.this);

        ShowSearchOnListView(databaseHelper, venuelist.getSelectedItem().toString());

        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper = new DatabaseHelper(SearchActivity.this);
                ShowSearchOnListView(databaseHelper, venuelist.getSelectedItem().toString());
            }
        });

    }


    private void ShowSearchOnListView(DatabaseHelper databaseHelper2, String venuelist) {
        ArrayAdapter searchArrayAdapter = new ArrayAdapter<Work>(SearchActivity.this,
                android.R.layout.simple_list_item_1,
                databaseHelper2.searchEveryone(venuelist));
        lv_searchList.setAdapter(searchArrayAdapter);
    }
}