package com.nguyencongthuan.coiboitinhyeu;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

public class divination_date_of_birth extends AppCompatActivity {

    private TextInputEditText etMyDoB;
    private TextInputEditText etYourDoB;

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

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setColorStatusBar() {
        getWindow().setStatusBarColor(ContextCompat.getColor(divination_date_of_birth.this,R.color.pinkStatusBar));
    }
}