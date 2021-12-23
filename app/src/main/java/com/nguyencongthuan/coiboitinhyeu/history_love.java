package com.nguyencongthuan.coiboitinhyeu;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.nguyencongthuan.coiboitinhyeu.Api.ApiService;
import com.nguyencongthuan.coiboitinhyeu.Model.History;
import com.nguyencongthuan.coiboitinhyeu.Model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class history_love extends AppCompatActivity {

    private ListView lvHistory;
    private ArrayList<History> arrayHistory;
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

        //get intent
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user");

        // add to listview and save data to arraylist
        callApiGetHistory(user.getUsername());

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setColorStatusBar() {
        getWindow().setStatusBarColor(ContextCompat.getColor(history_love.this,R.color.pinkStatusBar));
    }

    private void dataMapping(){
        lvHistory = (ListView) findViewById(R.id.history_listview);
        arrayHistory = new ArrayList<History>();
    }

    private void callApiGetHistory(String username) {
        ApiService.apiService.getHistory(username).enqueue(new Callback<ArrayList<History>>() {
            @Override
            public void onResponse(Call<ArrayList<History>> call, Response<ArrayList<History>> response) {
                arrayHistory = response.body();
                if(arrayHistory!=null){
                    Toast.makeText(history_love.this, "tc", Toast.LENGTH_SHORT).show();
                    adapter = new HistoryAdapter(history_love.this,R.layout.row_history,arrayHistory);
                    lvHistory.setAdapter(adapter);
                }
                else{
                    Toast.makeText(history_love.this, "null", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ArrayList<History>> call, Throwable t) {
                Toast.makeText(history_love.this, "loi", Toast.LENGTH_SHORT).show();
            }
        });

    }
}