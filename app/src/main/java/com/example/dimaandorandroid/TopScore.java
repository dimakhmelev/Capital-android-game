package com.example.dimaandorandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TopScore extends AppCompatActivity {

    ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_score);
        this.listView = findViewById(R.id.listview);
        List<AppUser> allAppUsers = MainActivity.db.getAllAppUsers();
        Collections.sort(allAppUsers);
        Collections.reverse(allAppUsers);
        List<String> topScoreListToPrint = new ArrayList<>();
        int index = 1;
        for (AppUser user : allAppUsers) {
            String stringToAdd = (index++) +". Score: " + user.getScore() + " Level: " + user.getLevel();
            topScoreListToPrint.add(stringToAdd);

        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, topScoreListToPrint);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                                            @Override
                                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                            }
                                        }
        );
    }
}