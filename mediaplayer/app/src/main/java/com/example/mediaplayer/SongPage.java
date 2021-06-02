package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;

public class SongPage extends AppCompatActivity {

    final static int maxVolume = 100;
    TextView songname;
    ImageButton playpause,next,previous,reset,volumeup,volumedown;
    boolean play;
    SeekBar s;
    Handler h ;
    private MediaPlayer mediaPlayer;
    private AssetFileDescriptor  afd;
    double startTime,finalTime;
    String [] songlist;
    String song;
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_page);
        h = new Handler();
        AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        songname = findViewById(R.id.songname);
        song = getIntent().getStringExtra("song");
        songlist = getIntent().getStringArrayExtra("songlist");
        pos = getsongpos();
        songname.setText(song);

        playpause = findViewById(R.id.playpause);
        next = findViewById(R.id.nextsong);
        previous = findViewById(R.id.previoussong);
        reset = findViewById(R.id.resetsong);
        s = findViewById(R.id.seekBar);
        volumedown = findViewById(R.id.volumedown);
        volumeup = findViewById(R.id.volumeup);
        String dir = "songs/"+song;
        System.out.println(dir);

      playfunction(dir);
      next.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if(pos+1 != songlist.length) {


                  pos+=1;

              }
              else{
                  pos = 0;
              }

              mediaPlayer.stop();
              mediaPlayer = null;
              song = songlist[pos];
              songname.setText(song);
              playfunction("songs/"+song);

          }
      });
      previous.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if(pos-1 != -1) {

                  pos-=1;

              }
              else{
                  pos = songlist.length-1;

              }
              mediaPlayer.stop();
              mediaPlayer = null;
              song = songlist[pos];
              songname.setText(song);
              playfunction("songs/"+song);
          }
      });
        playpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(play){
                    // pause this now
                    playpause.setImageResource(R.drawable.play);
                    mediaPlayer.pause();


                }
                else{
                    //play now
                    playpause.setImageResource(R.drawable.pause);

                    mediaPlayer.start();


                }
                play = !play;



            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.seekTo(0);
                mediaPlayer.start();
            }
        });
        volumedown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
            }
        });
        volumeup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
            }
        });

    }
    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();

            s.setProgress((int) startTime);
            h.postDelayed(this, 1000);
        }
    };
    private void playfunction(String dir){
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setVolume(0.8f,0.8f);

        try {
            afd = getAssets().openFd(dir);
            mediaPlayer.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            afd.close();
            mediaPlayer.prepare();
            mediaPlayer.start();
            finalTime = mediaPlayer.getDuration();
            startTime = mediaPlayer.getCurrentPosition();
            s.setMax((int)finalTime);
            s.setProgress((int)startTime);




        }
        catch (IOException e){
            e.printStackTrace();
        }
        h.postDelayed(UpdateSongTime,100);
        s.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(mediaPlayer!=null && fromUser)
                    mediaPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        play = true;
    }

    private int getsongpos(){
        for(int i = 0;i<songlist.length;i++)
        {
            if(songlist[i].equals(song)) {
                System.out.println("Song pos"+i);
                return i;
            }// return pos
        }
        return -1;

    }
}