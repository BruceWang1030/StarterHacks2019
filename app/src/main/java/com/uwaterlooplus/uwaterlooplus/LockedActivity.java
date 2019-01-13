package com.uwaterlooplus.uwaterlooplus;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.uwaterlooplus.uwaterlooplus.Fragment.NewAdventureFragment;

public class LockedActivity extends AppCompatActivity {


    private TextView mTvTimerText;
//    private Button mBtnStart;
    private CountDownTimer countDownTimer;
    private static long timeLeftinMilliconds; //10 mins
    private long mEndTime = System.currentTimeMillis() + timeLeftinMilliconds;
    private boolean timerRunning;

    private NotificationManager notificationManager;
    private NotificationChannel notificationChannel;
    private NotificationCompat.Builder mBuilder;
    private int notificationId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locked);

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        String channelId = "some_channel_id";
        CharSequence channelName = "Some Channel";
        int importance = NotificationManager.IMPORTANCE_LOW;
        notificationChannel = new NotificationChannel(channelId, channelName, importance);

        String textTitle = "Warning!";
        String textContent = "Your mission will fail if you leave.";

        mBuilder = new NotificationCompat.Builder(this, channelId)
                .setContentTitle(textTitle)
                .setContentText(textContent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        mTvTimerText = findViewById(R.id.textView_countdown);
        timerRunning = true;
//        mBtnStart = findViewById(R.id.button_start);
//
//        mBtnStart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startStop();
//            }
//        });

        updateTimer();
        startTimer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        notificationManager.notify(notificationId++, mBuilder.build());
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(countDownTimer != null){
            stopTimer();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        notificationManager.notify(notificationId++, mBuilder.build());
    }

//    public void startStop(){
//        if(timerRunning){
//            stopTimer();
//        }else {
//            startTimer();
//        }
//    }

    public void startTimer(){
        countDownTimer = new CountDownTimer(timeLeftinMilliconds,100) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftinMilliconds = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();
//        mBtnStart.setText("Pause");
//        timerRunning = true;
    }

    public void stopTimer(){
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

//        mBtnStart.setText("Start");
//
//        timerRunning = false;
    }

    public void updateTimer(){
        int minutes = (int) timeLeftinMilliconds / 60000;
        int seconds = (int) timeLeftinMilliconds % 60000 / 1000;

        if(minutes < 100) {
            String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);
            mTvTimerText.setText(timeLeftFormatted);
        }else {
            String timeLeftFormatted = String.format("%03d:%02d", minutes, seconds);
            mTvTimerText.setText(timeLeftFormatted);
        }

    }


    //To make sure every time is saved

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("millisLeft",timeLeftinMilliconds);
        outState.putBoolean("timerRunning", timerRunning);
        outState.putLong("endTime",mEndTime);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState != null) {
            timeLeftinMilliconds = savedInstanceState.getLong("millisLeft");
            timerRunning = savedInstanceState.getBoolean("timerRunning");
            updateTimer();

            if (timerRunning) {
                mEndTime = savedInstanceState.getLong("endTime");
                timeLeftinMilliconds = mEndTime - System.currentTimeMillis();
                startTimer();
            }
        }
    }

    public static void setTimeLeftMilliconds (long inputMillisSeconds){
        timeLeftinMilliconds = inputMillisSeconds;
    }



}
