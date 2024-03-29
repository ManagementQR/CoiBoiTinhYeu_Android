package com.nguyencongthuan.coiboitinhyeu;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nguyencongthuan.coiboitinhyeu.Model.User;

import java.io.Serializable;

public class home extends AppCompatActivity {

    private LinearLayout lnlDivinationName;
    private LinearLayout lnlDivinationDoB;
    private LinearLayout lnlProfile;
    private LinearLayout lnlHistory;
    private TextView txthome_userName;
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

        //get Intent
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user");


        // mapping
        lnlDivinationName = (LinearLayout) findViewById(R.id.home_BoiTen);
        lnlDivinationDoB = (LinearLayout)findViewById(R.id.home_BoiNgaySinh);
        lnlProfile = (LinearLayout) findViewById(R.id.home_profile);
        lnlHistory = (LinearLayout) findViewById(R.id.home_history);
        txthome_userName = findViewById(R.id.home_userName);

        if(user != null){
            txthome_userName.setText(user.getFullname());
        }



        // move to divination name
        lnlDivinationName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(home.this, divination_name.class);
                if(user != null){
                    intent.putExtra("user", (Serializable) user);
                }
                startActivity(intent);
            }
        });
        // move to divination date of birth
        lnlDivinationDoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, divination_date_of_birth.class);
                if(user != null){
                    intent.putExtra("user", (Serializable) user);
                }
                startActivity(intent);
            }
        });
        // move to profile
        lnlProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user!=null){
                    Intent intent = new Intent(home.this, profile.class);
                    intent.putExtra("user", (Serializable) user);
                    startActivity(intent);
                }
                else{
                    openDialog(Gravity.CENTER);
                }
                
            }
        });
        // move to love history
        lnlHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user!=null){
                    Intent intent = new Intent(home.this, history_love.class);
                    intent.putExtra("user", (Serializable) user);
                    startActivity(intent);
                }
                else{
                    openDialog(Gravity.CENTER);
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setColorStatusBar() {
        getWindow().setStatusBarColor(ContextCompat.getColor(home.this,R.color.pinkStatusBar));
    }

    // open dialog
    public void openDialog(int gravity){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_notify);

        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);
        // if click outside area, dialog will hide
        if(Gravity.CENTER == gravity){
            dialog.setCancelable(true);
        } else{
            dialog.setCancelable(false);
        }

        Button btnCancel = dialog.findViewById(R.id.cancel);
        Button btnSubmit = dialog.findViewById(R.id.submit);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, MainActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}