package com.react.otp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Status;

public class SmsBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        ReactContext rContext = (ReactContext) context;
        // Create map for params
        WritableMap payload = Arguments.createMap();
        // Put data to map


        if (SmsRetriever.SMS_RETRIEVED_ACTION.equals(intent.getAction())) {
            Bundle extras = intent.getExtras();
            Status status = (Status) extras.get(SmsRetriever.EXTRA_STATUS);

            switch(status.getStatusCode()) {
                case CommonStatusCodes.SUCCESS:
                    String message = (String) extras.get(SmsRetriever.EXTRA_SMS_MESSAGE);

                    payload.putString("otp",message);
                    rContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit("onVerifySMS",payload);

                    break;
                case CommonStatusCodes.TIMEOUT:
                    rContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit("onVerifySMSTIMEOUT",payload);

                    break;
            }
        }

    }
}
