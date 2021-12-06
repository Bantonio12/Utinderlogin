package com.example.myapplication.me;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.concurrent.TimeUnit;
import com.example.myapplication.R;
import com.example.myapplication.homepage.Homepage;


public class Pomodoro extends AppCompatActivity {

    private CountDownTimer countDownTimer;

    private TextView timerText;
    private TextView mode_txt;
    private Button btn_play_pause;
    private Button btn_reset;
    private Button btn_settings;
    private LinearLayout settings_layout;

    // Random Testing Variables Creation
    String mode = "first_work"; // first_work, short_break, second_work, long_break
    boolean isTimerStarted;

    // Good one
    long work_duration = TimeUnit.MINUTES.toMillis(25);
    long short_duration = TimeUnit.MINUTES.toMillis(5);
    long long_duration = TimeUnit.MINUTES.toMillis(15);

    String button_mode = "Start";
    int milli_val;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomodoro);

        final Button backbutton = findViewById(R.id.backbutton2);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backbutton_intent = new Intent(Pomodoro.this, Homepage.class);
                startActivity(backbutton_intent);
                finish();
            }
        });

        // Time Manipulation
        Timer timer = new Timer();

        mode_txt = findViewById(R.id.mode_txt);
        timerText = findViewById(R.id.time);

        String freeze_time = (work_duration / 1000) / 60 + ":00";
        timerText.setText(freeze_time);

        btn_play_pause = findViewById(R.id.play_btn);
        btn_reset = findViewById(R.id.reset_btn);
        btn_settings = findViewById(R.id.p_settings_btn);

        // Changing the Pomodoro Timings
        final SeekBar work_time_sb = findViewById(R.id.work_seekbar);
        final TextView work_time_txt = findViewById(R.id.work_timer_txt);
        work_time_sb.setProgress(25);

        final SeekBar long_time_sb = findViewById(R.id.long_seekbar);
        final TextView long_time_txt = findViewById(R.id.long_timer_txt);
        long_time_sb.setProgress(5);

        final SeekBar short_time_sb = findViewById(R.id.short_seekbar);
        final TextView short_time_txt = findViewById(R.id.short_timer_txt);
        short_time_sb.setProgress(15);

        settings_layout = findViewById(R.id.pomodoro_settings_layout);
        settings_layout.setVisibility(View.INVISIBLE);

        //SOUNDS
//        final MediaPlayer sound_effect = MediaPlayer.create(this, );

        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (settings_layout.getVisibility() == View.VISIBLE) {
                    settings_layout.setVisibility(View.INVISIBLE);
                } else settings_layout.setVisibility(View.VISIBLE);
            }
        });

        work_time_sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                work_time_txt.setText(String.valueOf(progress));

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                work_duration = TimeUnit.MINUTES.toMillis(seekBar.getProgress());
                reset_pompom();
            }
        });
        long_time_sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                long_time_txt.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                long_duration = TimeUnit.MINUTES.toMillis(seekBar.getProgress());
                reset_pompom();
            }
        });
        short_time_sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                short_time_txt.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                short_duration = TimeUnit.MINUTES.toMillis(seekBar.getProgress());
                reset_pompom();
            }
        });


        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset_pompom();
            }
        });

        btn_play_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button_mode.equals("Start")) {
//                    timerText.setText("you're into start");
                    if (isTimerStarted) {
                        start_session(milli_val);
                    } else {
                        switch (mode) {
                            case "first_work":
                                mode_txt.setText("Work");
                                start_session(work_duration);
                                break;
                            case "short_break":
                                mode_txt.setText("Short Break");
                                start_session(short_duration);
                                break;
                            case "second_work":
                                mode_txt.setText("Work Break");
                                start_session(work_duration);
                                break;
                            case "long_break":
                                mode_txt.setText("Long Break");
                                start_session(long_duration);
                                break;
                        }
                        isTimerStarted = true;
                    }
                    showStopButton();

                } else if (button_mode.equals("Stop")) {
//                    timerText.setText("you're into stop");
                    showStartButton();

                    countDownTimer.cancel();
                }
            }
        });
    }

    private void showStartButton() {
        btn_play_pause.setBackgroundResource(R.drawable.play_icon);
        button_mode = "Start";
        btn_reset.setVisibility(View.VISIBLE);
        btn_settings.setVisibility(View.VISIBLE);
        settings_layout.setVisibility(View.INVISIBLE);

    }

    private void showStopButton() {
        btn_play_pause.setBackgroundResource(R.drawable.pause_icon);
        button_mode = "Stop";
        btn_reset.setVisibility(View.INVISIBLE);
        btn_settings.setVisibility(View.INVISIBLE);
        settings_layout.setVisibility(View.INVISIBLE);

    }

    private void reset_pompom() {
        isTimerStarted = false;
        mode = "first_work";
        String freeze_time = (work_duration / 1000) / 60 + ":00";
        timerText.setText(freeze_time);
        mode_txt.setText("Ready to Work?");
    }

    private void start_session(long duration) {
        countDownTimer = new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int counter = (int) millisUntilFinished / 1000;
                milli_val = (int) millisUntilFinished;
                String display_time;
                if ((counter % 60) >= 10) {
                    display_time = counter / 60 + ":" + counter % 60;
                } else display_time = counter / 60 + ":0" + counter % 60;
                timerText.setText(display_time);
                // 125 seconds,
            }

            @Override
            public void onFinish() {
                switch (mode) {
                    case "first_work":
                        mode_txt.setText("Start Short Break?");
                        showStartButton();
//                        btn_play_pause.setBackgroundResource(R.drawable.play_icon);
//                        button_mode = "Start";
//                        btn_play_pause.setText("Start");
                        String freeze_time = (short_duration / 1000) / 60 + ":00";
                        timerText.setText(freeze_time);
                        isTimerStarted = false;
                        mode = "short_break";
                        break;
                    case "short_break":
                        mode_txt.setText("Start Work Period?");
                        showStartButton();
//                        btn_play_pause.setBackgroundResource(R.drawable.play_icon);
//                        button_mode = "Start";
//                        btn_play_pause.setText("Start");
                        freeze_time = (work_duration / 1000) / 60 + ":00";
                        timerText.setText(freeze_time);
                        isTimerStarted = false;
                        mode = "second_work";
                        break;
                    case "second_work":
                        mode_txt.setText("Start Long Break?");
                        showStartButton();
//                        btn_play_pause.setBackgroundResource(R.drawable.play_icon);
//                        button_mode = "Start";
//                        btn_play_pause.setText("Start");
                        freeze_time = (long_duration / 1000) / 60 + ":00";
                        timerText.setText(freeze_time);
                        isTimerStarted = false;
                        mode = "long_break";
                        break;
                    case "long_break":
                        mode_txt.setText("Start Work Period?");
                        showStartButton();
//                        btn_play_pause.setBackgroundResource(R.drawable.play_icon);
//                        button_mode = "Start";
//                        btn_play_pause.setText("Start");
                        freeze_time = (work_duration / 1000) / 60 + ":00";
                        timerText.setText(freeze_time);
                        isTimerStarted = false;
                        mode = "first_work";
                        break;
                }
            }
        }.start();
    }


}