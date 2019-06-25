
# react-native-react-native-otp

## Getting started

`$ npm install react-native-autodetect-otp --save`

###  follow play servies docs to generate hash
 https://developers.google.com/identity/sms-retriever/verify
### Manual installation


#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNReactNativeOtpPackage;` to the imports at the top of the file
  - Add `new RNReactNativeOtpPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-react-native-otp'
  	project(':react-native-react-native-otp').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-react-native-otp/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-react-native-otp')
  	```
4. add this code to manifest		
```
		<receiver android:name=".SmsBroadcast" android:exported="true">
  	  <intent-filter>
        <action android:name="com.google.android.gms.auth.api.phone.SMS_RETRIEVED"/>
    	</intent-filter>
		</receiver>
```

## Usage
```javascript
import { NativeEventEmitter, NativeModules } from 'react-native';
import RNReactNativeOtp from 'react-native-autodetect-otp';

const { ModuleWithEmitter } = NativeModules;

const eventEmitter = new NativeEventEmitter(ModuleWithEmitter);

const onReceiveOTP = ({otp}) => {
  console.log(otp);
}

const onOTPTimeout = (event) => {
  console.log(event);
}

class VerifyOTP extends Component{

	async componentDidMount(){
	const result = await 	RNReactNativeOtp.requestSMS();
this.unsubscribe =    ReactNativeOtp.detectorEmitter((result)=>{
            // you will get the sms message here
          })    
          })
}

componentWillUnmout(){
	this.unsubscribe()
	timeoutSubscription.remove();
}
}


```
  
  ```
  result object properties:
  result.otp : the sms message
  result.status : fail || success
  ```



