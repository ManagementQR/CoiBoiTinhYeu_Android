package com.nguyencongthuan.coiboitinhyeu;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class home extends AppCompatActivity {

    private LinearLayout lnlDivinationName;
    private LinearLayout lnlDivinationDoB;
    private LinearLayout lnlProfile;
    private LinearLayout lnlHistory;

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
        lnlProfile = (LinearLayout) findViewById(R.id.home_profile);
        lnlHistory = (LinearLayout) findViewById(R.id.home_history);

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
        // move to profile
        lnlProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, profile.class);
                startActivity(intent);
            }
        });
        // move to love history
        lnlHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, history_love.class);
                startActivity(intent);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setColorStatusBar() {
        getWindow().setStatusBarColor(ContextCompat.getColor(home.this,R.color.pinkStatusBar));
    }
}