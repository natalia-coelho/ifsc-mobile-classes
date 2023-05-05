package br.com.ifsc.mymediaplayer;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    MediaPlayer mediaPlayer;
    ImageButton bntPlay, bntStop, bntPause;
    SeekBar seekBar;
    TextView textViewTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer=mediaPlayer.create(this, R.raw.seminovos);
        bntPlay = findViewById(R.id.btnPlay);
        bntStop = findViewById(R.id.btnStop);
        bntPause = findViewById(R.id.btnPause);


        bntPlay.setOnClickListener(this);
        bntStop.setOnClickListener(this);
        bntPause.setOnClickListener(this);

        seekBar = findViewById(R.id.seekBarTime);
        textViewTime = findViewById(R.id.textViewTime);

        textViewTime.setText(convertDurationMillis(mediaPlayer.getDuration()));
        mediaPlayer.getCurrentPosition();
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
}