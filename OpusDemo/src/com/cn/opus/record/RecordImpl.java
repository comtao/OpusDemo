package com.cn.opus.record;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

import com.cn.opus.tool.OpusTool;
import com.cn.opus.util.Utils;

public class RecordImpl implements IRecord{
	private boolean isRecording;  
    protected static int FREQUENCY = 16000;  
    private static int CHANNEL = AudioFormat.CHANNEL_IN_MONO;  
    private static int ENCODING = AudioFormat.ENCODING_PCM_16BIT;  
    private final String FILEPATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/demo.wav";  
    private  AudioRecord audioRecord;  
  
    @Override  
    public boolean open(int bufferSize) {  
        audioRecord = new AudioRecord(MediaRecorder.AudioSource.DEFAULT,  
                FREQUENCY, CHANNEL, ENCODING, bufferSize);  
        if (audioRecord.getState() == AudioRecord.STATE_INITIALIZED) {  
            audioRecord.startRecording();  
            return true;  
        }  
        return false;  
    }  
  
    @Override  
    public void close() {  
        if (audioRecord != null) {  
            if (audioRecord.getState() == AudioRecord.STATE_INITIALIZED) {  
                audioRecord.stop();  
            }  
            if (audioRecord != null) {  
                audioRecord.release();  
                audioRecord = null;  
            }  
        }  
    }  
  
    @Override  
    public void start() {  
        File file = creatFile();  
        int bufferSize = AudioRecord.getMinBufferSize(FREQUENCY, CHANNEL, ENCODING);  
        if(!open(bufferSize)){  
            return ;  
        }  
  
        try {  
            OutputStream os = new FileOutputStream(file);  
            BufferedOutputStream bos = new BufferedOutputStream(os);  
            DataOutputStream dos = new DataOutputStream(bos);  
  
            short[] buffer = new short[bufferSize];  
            audioRecord.startRecording();  
  
            isRecording = true;  
            while (isRecording) {  
                int bufferReadResult = audioRecord.read(buffer, 0, bufferSize);  
                for (int i = 0; i < bufferReadResult; i++)  
                    dos.writeShort(buffer[i]);  
            }  
            
            //opus±àÂë
           /* OpusTool opusTool = new OpusTool();
            String opusFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/demo.opus";
System.out.println("inPath="+FILEPATH+"...outPath"+opusFile);
            opusTool.opusEncode(FILEPATH,opusFile);*/
  
            audioRecord.stop();  
            dos.close();  
        } catch (Throwable t) {  
            Log.e("AudioRecord ", "Recording Failed");  
        }
    }
  
    @Override  
    public void stop() {
        isRecording = false;  
    }

    private File creatFile() {
System.out.println("Sd prepare = "+Utils.isSdPrepare());
        File file = new File(FILEPATH);  
System.out.println("createFile = "+FILEPATH);
        if (file.exists())  
            file.delete();  
        try {
            file.createNewFile();  
        } catch (IOException e) {
            throw new IllegalStateException("Failed to create " + file.toString());
        }
        return file;  
    }
}
