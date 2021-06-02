package com.example.myapplication;

import androidx.annotation.RawRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    SeekBar seekBar;
    Handler handler;
    Runnable runnable;
    ListView myListView;
    TextView timeTV;
    String[] names;
    int[] song_ID={R.raw.song1,R.raw.lovely,R.raw.mortals,R.raw.invincible};
    ItemAdapter ia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myListView=(ListView) findViewById(R.id.songList);
        names=getResources().getStringArray(R.array.SongNames);
        ia=new ItemAdapter(this,names,song_ID);
        myListView.setAdapter(ia);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                stop(view);
                play(view,song_ID[position]);
            }
        });
        seekBar=(SeekBar) findViewById(R.id.seekBar);
        handler=new Handler();
        timeTV=(TextView) findViewById(R.id.timeTV);
        AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        Button volumeUpBTN=(Button) findViewById(R.id.volumeUpBTN);
        Button volumeLowBTN=(Button) findViewById(R.id.volumDownBTN);
        Button startBTN=(Button) findViewById(R.id.playBTN);
        Button pauseBTN=(Button) findViewById(R.id.pauseBTN);
        Button stopBTN=(Button) findViewById(R.id.stopBTN);
        Button forwardBTN=(Button) findViewById(R.id.forwardBTN);
        Button rewindBTN=(Button) findViewById(R.id.rewindBTN);
        startBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play(v,R.raw.song1);
            }
        });
        pauseBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pause(v);
            }
        });
        stopBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop(v);
            }
        });
        rewindBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currTime=(int) mediaPlayer.getCurrentPosition();
                if(currTime-5000>=0){
                    currTime-=5000;
                    mediaPlayer.seekTo(currTime);
                }
            }
        });
        forwardBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currTime=(int) mediaPlayer.getCurrentPosition();
                int finalTime=(int) mediaPlayer.getDuration();
                if(currTime+5000<=finalTime){
                    currTime+=5000;
                    mediaPlayer.seekTo(currTime);
                }
            }
        });
        volumeUpBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
            }
        });
        volumeLowBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean input) {
                if(input){
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
    }
    public void playCycle() {
        seekBar.setProgress(mediaPlayer.getCurrentPosition());
        if(mediaPlayer.isPlaying()){
            runnable=new Runnable() {
                @Override
                public void run() {
                    int currentDuration=mediaPlayer.getCurrentPosition();
                    timeTV.setText("" + milliSecondsToTimer((long) currentDuration)+"/"+milliSecondsToTimer(mediaPlayer.getDuration()));
                    playCycle();
                }
            };
            handler.postDelayed(runnable,1000);
        }
    }
    private void updatePlayer(int currentDuration){
        timeTV.setText("" + milliSecondsToTimer((long) currentDuration)+"/"+milliSecondsToTimer(mediaPlayer.getDuration()));
    }
    public  String milliSecondsToTimer(long milliseconds) {
        String finalTimerString = "";
        String secondsString = "";

        // Convert total duration into time
        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
        // Add hours if there
        if (hours > 0) {
            finalTimerString = hours + ":";
        }

        // Prepending 0 to seconds if it is one digit
        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = "" + seconds;
        }

        finalTimerString = finalTimerString + minutes + ":" + secondsString;

        // return timer string
        return finalTimerString;
    }

    public void play(View v,@RawRes int sound){
        if(mediaPlayer==null){
            mediaPlayer=MediaPlayer.create(this,sound);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });
        }
        seekBar.setMax(mediaPlayer.getDuration());
        mediaPlayer.start();
        playCycle();
    }
    public void pause(View v){
        if(mediaPlayer!=null){
            mediaPlayer.pause();
        }
    }
    public void stop(View v){
        stopPlayer();
    }
    private void stopPlayer(){
        if(mediaPlayer!=null){
            mediaPlayer.release();
            timeTV.setText("");
            seekBar.setProgress(0);
            handler.removeCallbacks(runnable);
            mediaPlayer=null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }

}