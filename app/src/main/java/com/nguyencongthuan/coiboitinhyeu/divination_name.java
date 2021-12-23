package com.nguyencongthuan.coiboitinhyeu;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;




import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

public class divination_name extends AppCompatActivity {

    private TextInputEditText myName, yourName;
    private String strName, strSo;
    private int count_l, count_o, count_v , count_e, count_s, percent;
    private Button btnCheckName;
    private TextView txtResult;
    private ProgressBar progressBar;
    private int current;
    private Handler handler;
    private AtomicBoolean isrunning = new AtomicBoolean(false);


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divination_name);

        // no action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        // set status bar
        setColorStatusBar();

        myName = findViewById(R.id.divinationName_myName);
        yourName = findViewById(R.id.divinationName_yourName);

        myName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==0){
                    myName.setError("Không được để trống");
                }
                else{
                    myName.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        yourName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==0){
                    yourName.setError("Không được để trống");
                }
                else{
                    yourName.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        progressBar = findViewById(R.id.ProgressBar);



        btnCheckName = findViewById(R.id.btnCheckName);
        txtResult = findViewById(R.id.result);
        btnCheckName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m = String.valueOf(myName.getText());
                String y = String.valueOf(yourName.getText());
                if(m.length()!=0&&y.length()!=0){
                    strName =  m + "loves" + y;

                    count_l = soKiTu(strName,"l");
                    count_o = soKiTu(strName,"o");
                    count_v = soKiTu(strName,"v");
                    count_e = soKiTu(strName,"e");
                    count_s = soKiTu(strName,"s");

                    strSo = ""+count_l + count_o + count_v + count_e + count_s;
                    percent = Percent(strSo);

                    process(percent);
                    doStart(percent);

                }
                else{
                    if(m.length()==0){
                        myName.setError("Không được để trống");
                    }
                    else{
                        yourName.setError("Không được để trống");
                    }
                }


            }
        });

        handler = new Handler(){
            public void handleMessage(Message msg){
                super.handleMessage(msg);
                txtResult.setText(msg.arg1+"%");
            }
        };



    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setColorStatusBar() {
        getWindow().setStatusBarColor(ContextCompat.getColor(divination_name.this,R.color.pinkStatusBar));
    }

    public int soKiTu(String str, String c){
        int dem = 0;
        for(int i = 0; i<str.length(); i++){
            if(str.charAt(i) == c.charAt(0)){
                dem++;
            }
        }
        return dem;
    }

    private int Percent(String s){
        if(Integer.parseInt(s)<=100){
            return Integer.parseInt(s);
        }
        else{
            String newString = "";
            int soDau;
            soDau = Integer.parseInt(s.charAt(0)+"");
            for(int i=1; i<s.length();i++){
                newString+= (soDau+ Integer.parseInt(s.charAt(i)+"")) + "";
            }
            return Percent(newString);
        }
    }

    private void process(int percent){
        progressBar.setProgress(0);
        current = progressBar.getProgress();
        final Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                current++;
                progressBar.setProgress(current);
                if(current==percent*88/100){
                    timer.cancel();
                }
            }
        };
        timer.schedule(timerTask, 0, 100);
    }

    public void doStart(int percent){
        isrunning.set(false);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1; i<=percent && isrunning.get(); i++){
                    SystemClock.sleep(85);
                    Message msg = handler.obtainMessage();
                    msg.arg1 = i;
                    handler.sendMessage(msg);
                }
            }
        });
        isrunning.set(true);
        thread.start();
    }

}