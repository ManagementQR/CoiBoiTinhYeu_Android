package com.nguyencongthuan.coiboitinhyeu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class percent extends AppCompatActivity {

    private Context mContext;

    private TextView mTextViewInfo;
    private TextView mTextViewPercentage;
    private ProgressBar mProgressBar;

    private int mProgressStatus = 0;

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            mTextViewInfo.setText("Battery Scale : " + scale);
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            mTextViewInfo.setText(mTextViewInfo.getText() + "\nBattery Level : " + level);
            float percentage = level / (float) scale;
            mProgressStatus = (int) ((percentage) * 100);
            mTextViewPercentage.setText("" + mProgressStatus + "%");
            mTextViewInfo.setText(mTextViewInfo.getText() +
                    "\nPercentage : " + mProgressStatus + "%");
            mProgressBar.setProgress(mProgressStatus);

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percent);

        mContext = getApplicationContext();
        IntentFilter iFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        mContext.registerReceiver(mBroadcastReceiver,iFilter);
        mTextViewInfo = (TextView) findViewById(R.id.tv_info);
        mTextViewPercentage = (TextView) findViewById(R.id.tv_percentage);
        mProgressBar = (ProgressBar) findViewById(R.id.pb);

    }
}