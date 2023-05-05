package br.com.ifsc.mymediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    AudioManager audioManager;
    MediaPlayer mediaPlayer;
    ImageButton bntPlay, bntStop, bntPause;
    SeekBar seekBarTime, seekBarVolume;
    TextView textViewTime;
    private Timer timerCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this, R.raw.seminovos);
        audioManager  = (AudioManager) getSystemService(AUDIO_SERVICE);
        mediaPlayer=mediaPlayer.create(this, R.raw.seminovos);

        bntPlay = findViewById(R.id.btnPlay);
        bntStop = findViewById(R.id.btnStop);
        bntPause = findViewById(R.id.btnPause);

        bntPlay.setOnClickListener(this);
        bntStop.setOnClickListener(this);
        bntPause.setOnClickListener(this);

        seekBarTime = findViewById(R.id.seekBarTime);
        seekBarVolume = findViewById(R.id.seekBarVolume);
        textViewTime = findViewById(R.id.textViewTime);

        textViewTime.setText(convertDurationMillis(mediaPlayer.getDuration()));
        mediaPlayer.getCurrentPosition();
        timerCounter();

        seekBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    seekBar.setMax(mediaPlayer.getDuration());
                    mediaPlayer.seekTo(progress);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        startSeekBar();
    }

    public void startSeekBar(){
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        seekBarVolume.setMax(maxVolume);
        seekBarVolume.setProgress(currentVolume);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bntPlay:
                mediaPlayer.start();
                break;
            case R.id.btnPause:
                mediaPlayer.pause();
                break;
            case R.id.btnStop:
                mediaPlayer.stop();
                break;
        }
    }
    public String convertDurationMillis(Integer getDurationInMillis){

        int getDurationMillis = getDurationInMillis;
        String convertHours = String.format("%02d", TimeUnit.MILLISECONDS.toHours(getDurationMillis));
        String convertMinutes = String.format("%02d", TimeUnit.MILLISECONDS.toMinutes(getDurationMillis));
        String convertSeconds = String.format("%02d", TimeUnit.MILLISECONDS.toSeconds(getDurationMillis) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(getDurationMillis)));

        String getDuration = convertHours + ":" + convertMinutes + ":" + convertSeconds;

        return getDuration;
    }

    public void updateTimer(){
        String strTimeDuration=convertDurationMillis(mediaPlayer.getDuration());
        strTimeDuration+="/"+convertDurationMillis(mediaPlayer.getCurrentPosition());
        textViewTime.setText(strTimeDuration);
    }

    private void timerCounter(){
        timerCounter = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateTimer();
//                        seekBarTime.setProgress();
                    }
                });
            }
        };
        timerCounter.schedule(task, 0, 1000);
    }
}