package com.nguyencongthuan.coiboitinhyeu;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.nguyencongthuan.coiboitinhyeu.Api.ApiService;
import com.nguyencongthuan.coiboitinhyeu.Model.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class sign_up extends AppCompatActivity {

    private TextInputEditText etDateOfBirth;
    private TextInputEditText etUserName;
    private TextInputEditText etFullName;
    private TextInputEditText etPassword;
    private Spinner spGender;
    private Button btnSignUp;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //set status bar
        setColorStatusBar();

        // mapping
        etDateOfBirth = (TextInputEditText) findViewById(R.id.signup_dateOfBirth);
        etUserName = (TextInputEditText) findViewById(R.id.signup_userName);
        etFullName = (TextInputEditText) findViewById(R.id.signup_fullName);
        etPassword = (TextInputEditText) findViewById(R.id.signup_password);
        spGender =  findViewById(R.id.signup_gender);
        btnSignUp = findViewById(R.id.sign_up);

        //set error
        etUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==0){
                    etUserName.setError("Không được để trống");
                }
                else{
                    etUserName.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etFullName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==0){
                    etFullName.setError("Không được để trống");
                }
                else{
                    etFullName.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==0){
                    etPassword.setError("Không được để trống");
                }
                else{
                    etPassword.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etDateOfBirth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==0){
                    etDateOfBirth.setError("Không được để trống");
                }
                else{
                    etDateOfBirth.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // init object
        DatePicker datePicker = new DatePicker();

        // set date of birth
        etDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.openDatePicker(sign_up.this, etDateOfBirth);
            }
        });

        //signup
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = String.valueOf(etUserName.getText());
                String password = String.valueOf(etPassword.getText());
                String fullname = String.valueOf(etFullName.getText());
                String dob = String.valueOf(etDateOfBirth.getText());
                String gender = spGender.getSelectedItem().toString();
                if(username!="" && password!="" && fullname!="" && gender!="" && dob!=""){
                    int gt = 0;
                    if(gender=="Nữ"){
                        gt=0;
                    }
                    if(gender=="Nam"){
                        gt=1;
                    }
                    User user = new User(
                            String.valueOf(etUserName.getText()),
                            String.valueOf(etPassword.getText()),
                            String.valueOf(etFullName.getText()),
                            String.valueOf(etDateOfBirth.getText()),
                            gt
                    );
                    callApiCreate(user);
                }
                else{
                    if (username==""){
                        etUserName.setError("Không được để trống");
                    }
                    else{
                        if(password==""){
                            etPassword.setError("Không được để trống");
                        }
                        else{
                            if(fullname==""){
                                etFullName.setError("Không được để trống");
                            }
                            else{
                                if(dob==""){
                                    etDateOfBirth.setError("Không được để trống");
                                }
                            }
                        }
                    }
                }

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setColorStatusBar() {
        getWindow().setStatusBarColor(ContextCompat.getColor(sign_up.this,R.color.pinkStatusBar));
    }

    public void callApiCreate(User user){
        ApiService.apiService.createUser(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Toast.makeText(sign_up.this, "tc", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(sign_up.this, "loi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}