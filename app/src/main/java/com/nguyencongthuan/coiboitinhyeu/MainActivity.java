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
import com.nguyencongthuan.coiboitinhyeu.Model.User;


import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView tvSignUp;
    private TextView tvSignIn_goHome;
    private Button btnSignIn;
    private TextInputEditText tIELogin_userName, tIELogin_password;
    String username;
    String password;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        // set color statusbar
        setColorStatusBar();

        // mapping
        tvSignUp = (TextView) findViewById(R.id.sign_up);
        tvSignIn_goHome = (TextView) findViewById(R.id.signIn_goHome);

        // move to sign up
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, sign_up.class);
                startActivity(intent);
            }
        });

        // move to home page, No need to log in
        tvSignIn_goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, home.class);
                startActivity(intent);
            }
        });

        btnSignIn = findViewById(R.id.signIn);
        tIELogin_userName = findViewById(R.id.login_userName);
        tIELogin_password = findViewById(R.id.login_password);

        tIELogin_userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==0){
                    tIELogin_userName.setError("Không được để trống");
                }
                else{
                    tIELogin_userName.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tIELogin_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==0){
                    tIELogin_password.setError("Không được để trống");
                }
                else{
                    tIELogin_password.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = String.valueOf(tIELogin_userName.getText());
                password = String.valueOf(tIELogin_password.getText());

                if(username.length()!=0&&password.length()!=0){
                    ApiService.apiService.getUser(username).enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            User user = response.body();
                            if(user!=null){
                                if(password.equals(user.getPassword())){
                                    tIELogin_password.setError(null);
                                    Intent intent = new Intent(MainActivity.this, home.class);

                                    intent.putExtra("user", (Serializable) user);
                                    startActivity(intent);
                                }
                                else{
                                    tIELogin_password.setError("Mật khẩu không đúng");
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "loi", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{
                    if(username.length()==0){
                        tIELogin_userName.setError("Không được để trống");
                    }
                    else{
                        tIELogin_password.setError("Không được để trống");
                    }
                }

            }
        });
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setColorStatusBar() {
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.pinkStatusBar));
    }
}