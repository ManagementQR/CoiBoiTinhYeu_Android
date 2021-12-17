package com.nguyencongthuan.coiboitinhyeu;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class home extends AppCompatActivity {

    private LinearLayout lnlDivinationName;
    private LinearLayout lnlDivinationDoB;
    private LinearLayout LnlDivinationZodiac;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // no action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        // set status bar
        setColorStatusBar();

        // mapping
        lnlDivinationName = (LinearLayout) findViewById(R.id.home_BoiTen);
        lnlDivinationDoB = (LinearLayout)findViewById(R.id.home_BoiNgaySinh);
        LnlDivinationZodiac = (LinearLayout) findViewById(R.id.home_BoiCungHoangDao);

        // move to divination name
        lnlDivinationName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, divination_name.class);
                startActivity(intent);
            }
        });
        // move to divination date of birth
        lnlDivinationDoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, divination_date_of_birth.class);
                startActivity(intent);
            }
        });
        // move to divination zodiac
        LnlDivinationZodiac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, divination_zodiac.class);
                startActivity(intent);
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setColorStatusBar() {
        getWindow().setStatusBarColor(ContextCompat.getColor(home.this,R.color.pinkStatusBar));
    }
}