package com.cn.opus;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.cn.opus.record.PlayImpl;
import com.cn.opus.record.RecordImpl;
import com.cn.opus.tool.OpusTool;

public class MainActivity extends Activity {
	private static RecordImpl mRecord;  
    private static PlayImpl mPlay;  
  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main);  
        initView();  
    }  
  
  
    private void initView() {  
        mRecord = new RecordImpl();  
        mPlay = new PlayImpl();  
        findViewById(R.id.record_start).setOnClickListener(new View.OnClickListener() {  
            @Override  
            public void onClick(View v) {
            OpusTool opusTool = new OpusTool();
System.out.println("opus test sjdsfjldkj "+opusTool.getNativeGetString());
                Thread thread = new Thread(new Runnable() {
                    public void run() {
                        mRecord.start();  
                    }
                });  
                thread.start();  
  
            }  
        });  
  
        findViewById(R.id.record_stop).setOnClickListener(new View.OnClickListener() {  
            @Override  
            public void onClick(View v) {  
                mRecord.stop();  
                mRecord.close();  
            }  
        });  
  
        findViewById(R.id.play_start).setOnClickListener(new View.OnClickListener() {
            @Override  
            public void onClick(View v) {
                mPlay.play();  
            }  
        });  
  
        findViewById(R.id.record_stop).setOnClickListener(new View.OnClickListener() {  
            @Override  
            public void onClick(View v) {  
                mRecord.stop();  
                mRecord.close();  
            }  
        });  
  
        findViewById(R.id.play_start).setOnClickListener(new View.OnClickListener() {  
            @Override  
            public void onClick(View v) {  
                mPlay.play();  
            }  
        }); 
    } 
}
