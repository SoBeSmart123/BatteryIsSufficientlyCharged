package com.sobesmart.batteryissufficientlycharged;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

public class TTSService extends Service {
    private TextToSpeech ttobj = null;

    public TTSService() {
    }

    @Override
    public void onCreate(){
        super.onCreate();
        ttobj=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
            }
    });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getApplicationContext(), intent.getStringExtra(this.getString(R.string.READ_TEXT)), Toast.LENGTH_SHORT).show();
            ttobj.speak(intent.getStringExtra(this.getString(R.string.READ_TEXT)), TextToSpeech.QUEUE_ADD, null);



        return Service.START_NOT_STICKY;
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (ttobj != null) {
            ttobj.stop();
            ttobj.shutdown();
        }
    }
}
