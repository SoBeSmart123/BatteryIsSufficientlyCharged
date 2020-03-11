package com.sobesmart.batteryissufficientlycharged;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.preference.PreferenceManager;

// Handle the callback on the Intent.
public class MyFenceReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {


        if (intent.getAction().equalsIgnoreCase("android.intent.action.ACTION_POWER_CONNECTED")) {
            if(shouldVibrateOnChargingConnected(context)) {
              vibrate(context);
            }
        }

        if (intent.getAction().equalsIgnoreCase("android.intent.action.ACTION_POWER_DISCONNECTED")) {
            if(shouldVibrateOnChargingDisconnected(context)) {
                vibrate(context);
            }
        }
    }

    private void vibrate(Context context) {
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
           v.vibrate(VibrationEffect.createOneShot(500,50));
        } else {
            // Vibrate for 500 milliseconds
            v.vibrate(500);
        }


    }

    private boolean shouldVibrateOnChargingConnected(Context context) {
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(context);
        boolean shouldVibrateOnChargingConnected = SP.getBoolean("vibrateChargingConnected",true);
        return shouldVibrateOnChargingConnected;
    }

    private boolean shouldVibrateOnChargingDisconnected(Context context) {
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(context);
        boolean shouldVibrateOnChargingDisconnected = SP.getBoolean("vibrateChargingDisconnected",true);
        return shouldVibrateOnChargingDisconnected;
    }
}
