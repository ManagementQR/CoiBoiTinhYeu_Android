package com.nguyencongthuan.coiboitinhyeu;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class divination_date_of_birth extends AppCompatActivity {

    private TextInputEditText etMyDoB;
    private TextInputEditText etYourDoB;
    private TextInputEditText myDob, yourDob;
    private Button btnCheckName;
    private String txtMyDob, txtYourDob;
    private TextView txtResult;
    private long soMyDob, soYourDob;

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
        btnCheckName = findViewById(R.id.btnCheckName);
        txtResult = findViewById(R.id.result);
        btnCheckName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtMyDob = String.valueOf(myDob.getText());
                txtYourDob = String.valueOf(yourDob.getText());
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
}