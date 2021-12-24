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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.textfield.TextInputEditText;
import com.nguyencongthuan.coiboitinhyeu.Api.ApiService;
import com.nguyencongthuan.coiboitinhyeu.Model.History;
import com.nguyencongthuan.coiboitinhyeu.Model.User;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class divination_date_of_birth extends AppCompatActivity {

    private TextInputEditText etMyDoB;
    private TextInputEditText etYourDoB;
    private TextInputEditText myDob, yourDob;
    private Button btnCheckName;
    private String txtMyDob, txtYourDob;
    private TextView txtResult;
    private long soMyDob, soYourDob;
    private History history;
    private Toolbar toolbar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divination_date_of_birth);
        // actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //set status bar
        setColorStatusBar();

        // mapping
        etMyDoB = (TextInputEditText) findViewById(R.id.divinationDoB_myDoB);
        etYourDoB = (TextInputEditText)findViewById(R.id.divinationDoB_yourDoB);
        toolbar = findViewById(R.id.divinationDoB_toolbar);


        //get intent
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user");

        // init object
        DatePicker datePicker = new DatePicker();

        // set date
        etMyDoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.openDatePicker(divination_date_of_birth.this, etMyDoB);
            }
        });
        etYourDoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.openDatePicker(divination_date_of_birth.this, etYourDoB);
            }
        });

        myDob = findViewById(R.id.divinationDoB_myDoB);
        yourDob = findViewById(R.id.divinationDoB_yourDoB);

        myDob.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==0){
                    myDob.setError("Không được để trống");
                }
                else{
                    myDob.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        yourDob.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==0){
                    yourDob.setError("Không được để trống");
                }
                else{
                    yourDob.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //kiểm tra kết quả
        btnCheckName = findViewById(R.id.btnCheckName);
        txtResult = findViewById(R.id.result);
        btnCheckName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtMyDob = String.valueOf(myDob.getText());
                txtYourDob = String.valueOf(yourDob.getText());

                if(txtMyDob.length()!=0 && txtYourDob.length()!=0){
                    soMyDob = Integer.parseInt(txtMyDob.replace("/",""));
                    soYourDob = Integer.parseInt(txtYourDob.replace("/",""));

                    long percent = Percent(soMyDob+soYourDob);
                    String rs = "";
                    if(percent==10)
                        rs = "Tình bạn";
                    if(percent==11)
                        rs = "Thầm lặng";
                    if(percent==12)
                        rs = "Bạn bè";
                    if(percent==13)
                        rs = "Có cảm tình";
                    if(percent==14)
                        rs = "Tình yêu";
                    if(percent==15)
                        rs = "Đơn phương";
                    if(percent==16)
                        rs = "Ghét";
                    if(percent==17)
                        rs = "Chờ đợi";
                    if(percent==18)
                        rs = "Vợ chồng";
                    if(percent==19)
                        rs = "Bình thường";

                    txtResult.setText(rs);

                    //insert history
                    if(user != null){
                        history = new History(user.getUsername(),soMyDob+"",soYourDob+"",rs);
                        createHistory(history);
                    }
                }
                else{
                    if(txtMyDob.length()==0){
                        myDob.setError("Không được để trống");
                    }
                    else{
                        yourDob.setError("Không được để trống");
                    }
                }

            }
        });

        //trở về trang home
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(divination_date_of_birth.this,home.class);
                if(user != null){
                    intent.putExtra("user", (Serializable) user);
                }
                startActivity(intent);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setColorStatusBar() {
        getWindow().setStatusBarColor(ContextCompat.getColor(divination_date_of_birth.this,R.color.pinkStatusBar));
    }

    private long Percent(long tong){
        while(tong>19){
            if(tong%2==0){
                tong = tong/2;
            }
            else {
                tong = (tong+1)/2;
            }
        }
        return tong;
    }

    public void createHistory(History history){
        ApiService.apiService.createHistory(history).enqueue(new Callback<History>() {
            @Override
            public void onResponse(Call<History> call, Response<History> response) {
                if(response.isSuccessful()){
                    //Toast.makeText(divination_date_of_birth.this, "tc", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<History> call, Throwable t) {
                Toast.makeText(divination_date_of_birth.this, "loi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}