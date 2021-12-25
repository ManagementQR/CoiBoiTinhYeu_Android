package com.nguyencongthuan.coiboitinhyeu;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


import com.nguyencongthuan.coiboitinhyeu.Api.ApiService;
import com.nguyencongthuan.coiboitinhyeu.Model.History;
import com.nguyencongthuan.coiboitinhyeu.Model.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class history_love extends AppCompatActivity {

    private ListView lvHistory;
    private ArrayList<History> arrayHistory;
    private HistoryAdapter adapter;
    private Toolbar toolbar;
    private EditText find;

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

        //tìm kiếm
        find.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                find(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        //trở về trang home
        toolbar = findViewById(R.id.history_toolbar);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(history_love.this,home.class);
                if(user != null){
                    intent.putExtra("user", (Serializable) user);
                }
                startActivity(intent);
            }
        });

        //xóa item
        lvHistory.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                int idH = arrayHistory.get(position).getId();
                arrayHistory.remove(position);
                adapter.notifyDataSetChanged();

                deleteApi(idH);
                return false;
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setColorStatusBar() {
        getWindow().setStatusBarColor(ContextCompat.getColor(history_love.this,R.color.pinkStatusBar));
    }

    private void dataMapping(){
        lvHistory = (ListView) findViewById(R.id.history_listview);
        arrayHistory = new ArrayList<History>();
        find = findViewById(R.id.find);
    }

    private void callApiGetHistory(String username) {
        ApiService.apiService.getHistory(username).enqueue(new Callback<ArrayList<History>>() {
            @Override
            public void onResponse(Call<ArrayList<History>> call, Response<ArrayList<History>> response) {
                arrayHistory = response.body();
                if(arrayHistory!=null){
                    //Toast.makeText(history_love.this, "tc", Toast.LENGTH_SHORT).show();
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

    private void find(CharSequence key){
        ArrayList<History> arr = new ArrayList<>();
        for (History history: arrayHistory) {
            if (history.getFullname() !=null && history.getInfor() != null){
                if(history.getFullname().contains(key) || history.getInfor().contains(key)){
                    arr.add(history);
                }
            }

        }
        adapter = new HistoryAdapter(history_love.this,R.layout.row_history,arr);
        lvHistory.setAdapter(adapter);
    }

    private void deleteApi(int id){
        ApiService.apiService.delete(id).enqueue(new Callback<History>() {
            @Override
            public void onResponse(Call<History> call, Response<History> response) {
                if(response.isSuccessful()){
                    Toast.makeText(history_love.this, "tc", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<History> call, Throwable t) {

            }
        });
    }

}