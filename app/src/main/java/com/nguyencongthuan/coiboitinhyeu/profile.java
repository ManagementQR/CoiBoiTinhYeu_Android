package com.nguyencongthuan.coiboitinhyeu;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import com.nguyencongthuan.coiboitinhyeu.Api.ApiService;
import com.nguyencongthuan.coiboitinhyeu.Model.RealPathUtil;
import com.nguyencongthuan.coiboitinhyeu.Model.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;

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
    private Toolbar toolbar;
    private Button btnDialogSuccessOk;
    private ImageView imgAva;
    private int REQUEST_IMAGE = 123;
    private int MY_REQUEST = 111;
    private Uri mUri;
    private ImageView ava;

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
        ava = findViewById(R.id.profile_image);

        //set ava từ server
        Glide.with(profile.this).load(ApiService.url+"user-content/"+user.getAva()).into(ava);



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
            profile_dateOfBirth.setText(user.getDoB());

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

        //trở về trang home
        toolbar = findViewById(R.id.profile_toolbar);
        //getUser(user.getUsername());
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setDoB(String.valueOf(tvChangeDoB.getText()));
                callApoUpdate(user);
                Intent intent = new Intent(profile.this,home.class);
                if(user != null){
                    intent.putExtra("user", (Serializable) user);
                }
                startActivity(intent);
            }
        });

        //set ava
        imgAva = findViewById(R.id.imgAva);
        imgAva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M )
                {
                    return;
                }
                if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent,REQUEST_IMAGE);
                }
                else
                {
                    String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
                    requestPermissions(permission, MY_REQUEST);
                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_IMAGE && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            mUri = uri;
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                ava.setImageBitmap(bitmap);
                callApiImage();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void callApiImage(){
        RequestBody requestBodyUsername = RequestBody.create(MediaType.parse("multipart/form-data"),user.getUsername());

        String realFile = RealPathUtil.getRealPath(this,mUri);
        Log.e("d",realFile);
        File file = new File(realFile);
        RequestBody requestBodyAva = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part multipartAva = MultipartBody.Part.createFormData("image",file.getName(),requestBodyAva);
        ApiService.apiService.uploadImage(requestBodyUsername,multipartAva).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user = response.body();
                if(user!=null){
                    Glide.with(profile.this).load(ApiService.url+"user-content/"+user.getAva()).into(ava);                }
                Toast.makeText(profile.this, "tc", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(profile.this, "loi", Toast.LENGTH_SHORT).show();
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
                    titlefullname.setText(newName.getText());
                    dialog.dismiss();
                    openDialogSuccess(Gravity.CENTER);
                }
            });
        }

//        // hide dialog success ok
//        if(view == R.layout.dialog_success){
//            Button submit = dialog.findViewById(R.id.dialog_success_ok);
//            submit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dialog.dismiss();
//                }
//            });
//        }

        if(view == R.layout.dialog_change_gender){
            Spinner spinner = dialog.findViewById(R.id.spinner);
            Button submit = dialog.findViewById(R.id.submitGender);

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String newGender="";
                    newGender = spinner.getSelectedItem().toString();
                    if(!newGender.equals("Chọn giới tính")){
                        profile_gender.setText(newGender);
                        if(newGender.equals("Nữ")){
                            user.setGender(0);
                        }
                        else {
                            user.setGender(1);
                        }
                        callApoUpdate(user);
                        dialog.dismiss();
                        openDialogSuccess(Gravity.CENTER);
                    }


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
                            openDialogSuccess(Gravity.CENTER);
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
                    //openDialog(Gravity.CENTER, R.layout.dialog_success);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                //Toast.makeText(profile.this, "loi", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getUser(String username){
        ApiService.apiService.getUser(username).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user = response.body();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

    }

    // open dialog
    public void openDialogSuccess(int gravity){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_success);

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

        Button ok = dialog.findViewById(R.id.dialog_success_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


}