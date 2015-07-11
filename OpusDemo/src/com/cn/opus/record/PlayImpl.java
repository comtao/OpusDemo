package com.cn.opus.record;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Environment;
import android.util.Log;

public class PlayImpl implements IPlay{
	protected static int FREQUENCY = 16000;  
    //private static int CHANNEL = AudioFormat.CHANNEL_IN_MONO;  
    //private static int ENCODING = AudioFormat.ENCODING_PCM_16BIT;  
    private final String FILEPATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/demo.wav";  
    private  AudioTrack audioTrack;  
    @Override  
    public void play() {  
        File file = new File(FILEPATH);  
        int musicLength = (int) (file.length() / 2);  
        short[] music = new short[musicLength];  
  
        try {  
            InputStream is = new FileInputStream(file);  
            BufferedInputStream bis = new BufferedInputStream(is);  
            DataInputStream dis = new DataInputStream(bis);  
  
            int i = 0;  
            while (dis.available() > 0) {  
                music[i] = dis.readShort();  
                i++;  
            }  
  
            dis.close();  
  
            open(musicLength);  
            audioTrack.play();  
            audioTrack.write(music, 0, musicLength);  
            audioTrack.stop();  
        } catch (Throwable t) {  
            Log.e("AudioTrack", "Playback Failed");  
        }  
    }  
  
    @Override  
    public void open(int musicLength){  
         audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,  
                FREQUENCY,  
                AudioFormat.CHANNEL_OUT_MONO,  
                AudioFormat.ENCODING_PCM_16BIT,  
                musicLength * 2,  
                AudioTrack.MODE_STREAM);  
    }  
    @Override  
    public void stop() {  
        audioTrack.stop();  
    } 

}
