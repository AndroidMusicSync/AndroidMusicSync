package de.sync.androidmusicsync;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaPlayer;

public class MasterActivity extends AppCompatActivity {

    public static final int DURATION = 2; // seconds
    public static final int SAMPLE_RATE = 48000;
    public static final double FREQUENCE = 18000; // hz
    public static final int WAIT_TIME = 1000;
    public static final int REPETITION = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);
    }
    public void masterEvent(View view){
        try {
            for(int i = 0; i < REPETITION; i++){
                generateSound();
                Thread.sleep(WAIT_TIME+DURATION*1000);
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        playSong();
    }

    public void generateSound (){
        int numSamples = DURATION * SAMPLE_RATE;
        double sample[] = new double[numSamples];
        byte generatedSnd[] = new byte[2 * numSamples];


        for (int i = 0; i < numSamples; ++i) {
            sample[i] = Math.sin(2 * Math.PI * i / (SAMPLE_RATE/FREQUENCE));
        }

        int idx = 0;
        for (double dVal : sample) {
            short val = (short) (dVal * 32767);
            generatedSnd[idx++] = (byte) (val & 0x00ff);
            generatedSnd[idx++] = (byte) ((val & 0xff00) >>> 8);
        }

        AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                SAMPLE_RATE, AudioFormat.CHANNEL_CONFIGURATION_MONO,
                AudioFormat.ENCODING_PCM_16BIT, numSamples,
                AudioTrack.MODE_STATIC);
        audioTrack.write(generatedSnd, 0, numSamples);
        audioTrack.play();


    }

    public void playSong(){
        MediaPlayer player = MediaPlayer.create(app, R.raw.roccowbreakaleg);
        player.start();
    }
}
