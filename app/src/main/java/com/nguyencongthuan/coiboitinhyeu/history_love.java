package com.nguyencongthuan.coiboitinhyeu;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class history_love extends AppCompatActivity {

    private ListView lvHistory;
    private ArrayList<Attribute_history> arrayHistory;
    private HistoryAdapter adapter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_love);

        // actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //set status bar
        setColorStatusBar();

        // data mapping
        dataMapping();

        // add to listview and save data to arraylist
        adapter = new HistoryAdapter(history_love.this,R.layout.row_history,arrayHistory);
        lvHistory.setAdapter(adapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setColorStatusBar() {
        getWindow().setStatusBarColor(ContextCompat.getColor(history_love.this,R.color.pinkStatusBar));
    }

    private void dataMapping(){
        lvHistory = (ListView) findViewById(R.id.history_listview);
        arrayHistory = new ArrayList<Attribute_history>();

        arrayHistory.add(new Attribute_history("Thuận Nguyễn","Bánh Kem",  "100%"));
        arrayHistory.add(new Attribute_history("Thịnh Nguyễn","Bánh Ngọt",  "90%"));
        arrayHistory.add(new Attribute_history("Huy Nguyễn","Bánh Mỳ",  "80%"));
        arrayHistory.add(new Attribute_history("Chương Nguyễn","Bánh Trái Cây",  "53%"));
    }
}