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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.nguyencongthuan.coiboitinhyeu.Api.ApiService;
import com.nguyencongthuan.coiboitinhyeu.Model.User;

import java.util.Arrays;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class profile extends AppCompatActivity {

    private TextView tvChangeFullName;
    private TextView tvChangeGender;
    private TextView tvChangeDoB;
    private TextView tvChangePassword;
    private TextView titlefullname;
    private TextView profile_username;
    private TextView profile_fullname;
    private TextView profile_gender;
    private TextView profile_dateOfBirth;
    private User user;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //get Intent
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");



        // actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        //set status bar
        setColorStatusBar();
        // mapping
        tvChangeFullName = (TextView) findViewById(R.id.profile_fullname);
        tvChangeGender = (TextView) findViewById(R.id.profile_gender);
        tvChangeDoB = (TextView) findViewById(R.id.profile_dateOfBirth);
        tvChangePassword = (TextView)findViewById(R.id.profile_password);
        titlefullname = (TextView) findViewById(R.id.titlefullname);
        profile_username = findViewById(R.id.profile_username);
        profile_fullname = findViewById(R.id.profile_fullname);
        profile_gender = findViewById(R.id.profile_gender);
        profile_dateOfBirth = findViewById(R.id.profile_dateOfBirth);

        //set text for profile
        if(user != null){
            titlefullname.setText(user.getFullname());
            profile_username.setText(user.getUsername());
            profile_fullname.setText(user.getFullname());
            if(user.getGender()==0){
                profile_gender.setText("Nữ");
            }
            else{
                profile_gender.setText("Nam");
            }

            //set ngày
            String[] arrays = user.getDoB().split("-");

            profile_dateOfBirth.setText(arrays[2]+"/"+arrays[1]+"/"+arrays[0]);
        }


        // move to dialog change full name
        tvChangeFullName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(Gravity.BOTTOM, R.layout.dialog_change_fullname);
                //xuliDialog(Gravity.BOTTOM);
            }
        });
        // move to dialog change gender
        tvChangeGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(Gravity.BOTTOM, R.layout.dialog_change_gender);
            }
        });
        // init object
        DatePicker datePicker = new DatePicker();

        // set change date of birth
        tvChangeDoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.openDatePicker_TextView(profile.this, tvChangeDoB);
            }
        });
        // move to dialog change password
        tvChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(Gravity.BOTTOM, R.layout.dialog_change_password);
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setColorStatusBar() {
        getWindow().setStatusBarColor(ContextCompat.getColor(profile.this,R.color.pinkStatusBar));
    }

    // open dialog
    public void openDialog(int gravity, int view){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);

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
        if(Gravity.BOTTOM == gravity){
            dialog.setCancelable(true);
        } else{
            dialog.setCancelable(false);
        }

        if(view == R.layout.dialog_change_fullname){
            EditText newName = dialog.findViewById(R.id.newFullname);
            Button submit = dialog.findViewById(R.id.submitFullname);
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    profile_fullname.setText(newName.getText());
                    user.setFullname(String.valueOf(newName.getText()));
                    callApoUpdate(user);
                    dialog.dismiss();
                }
            });
        }

        if(view == R.layout.dialog_change_gender){
            Spinner spinner = dialog.findViewById(R.id.spinner);
            Button submit = dialog.findViewById(R.id.submitGender);

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String newGender="";
                    newGender = spinner.getSelectedItem().toString();
                    if(newGender!="Chọn giới tính"){
                        profile_gender.setText(newGender);
                        if(newGender=="Nữ"){
                            user.setGender(0);
                        }
                        else {
                            user.setGender(1);
                        }
                        callApoUpdate(user);
                    }

                    dialog.dismiss();
                }
            });
        }

        if(view == R.layout.dialog_change_password){
            TextInputEditText oldPassword = (TextInputEditText) dialog.findViewById(R.id.oldPassword);
            TextInputEditText newPassword = (TextInputEditText) dialog.findViewById(R.id.newPassword);
            Button submit = (Button) dialog.findViewById(R.id.submitPassword);

            oldPassword.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(s.length()==0){
                        oldPassword.setError("Không được để trống");
                    }
                    else{
                        oldPassword.setError(null);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            newPassword.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(s.length()==0){
                        newPassword.setError("Không được để trống");
                    }
                    else{
                        newPassword.setError(null);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String oldPass = String.valueOf(oldPassword.getText());
                    String newPass = String.valueOf(newPassword.getText());

                    if(oldPass.length()!=0 && newPass.length()!=0){
                        if(oldPass.equals(user.getPassword())){
                            oldPassword.setError(null);
                            user.setPassword(newPass);
                            callApoUpdate(user);
                            dialog.dismiss();
                        }
                        else{
                            oldPassword.setError("Mật khẩu không đúng!");
                        }
                    }
                    else{
                        if(oldPass.length()==0){
                            oldPassword.setError("Không được để trống");
                        }
                        else{
                            newPassword.setError("Không được để trống");
                        }
                    }
                }
            });
        }

        dialog.show();
    }


    private void callApoUpdate(User user){
        ApiService.apiService.updateUser(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    //Toast.makeText(profile.this, "tc", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                //Toast.makeText(profile.this, "loi", Toast.LENGTH_SHORT).show();
            }
        });
    }


}