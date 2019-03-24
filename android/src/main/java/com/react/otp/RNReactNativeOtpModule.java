
package com.react.otp;

import android.support.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class RNReactNativeOtpModule extends ReactContextBaseJavaModule {


  public RNReactNativeOtpModule(ReactApplicationContext reactContext) {
    super(reactContext);

  }

  @Override
  public String getName() {
    return "RNReactNativeOtp";
  }



  @ReactMethod
  public void requestSMS(final Promise promise){

      // Get an instance of SmsRetrieverClient, used to start listening for a matching
    // SMS message.
      SmsRetrieverClient client = SmsRetriever.getClient(getReactApplicationContext());

        // Starts SmsRetriever, which waits for ONE matching SMS message until timeout
        // (5 minutes). The matching SMS message will be sent via a Broadcast Intent with
        // action SmsRetriever#SMS_RETRIEVED_ACTION.
      Task<Void> task = client.startSmsRetriever();

        // Listen for success/failure of the start Task. If in a background thread, this
        // can be made blocking using Tasks.await(task, [timeout]);
      task.addOnSuccessListener(new OnSuccessListener<Void>() {
          @Override
          public void onSuccess(Void aVoid) {
          promise.resolve(aVoid);
          }
      });

      task.addOnFailureListener(new OnFailureListener() {
          @Override
          public void onFailure(@NonNull Exception e) {
              // Failed to start retriever, inspect Exception for more details
              // ...

              promise.reject(e);
          }
      });
  }

}