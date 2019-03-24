
# react-native-react-native-otp

## Getting started

`$ npm install react-native-react-native-otp --save`

### Mostly automatic installation

`$ react-native link react-native-react-native-otp`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-react-native-otp` and add `RNReactNativeOtp.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNReactNativeOtp.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

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
import RNReactNativeOtp from 'react-native-react-native-otp';

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
const subscription = eventEmitter.addListener('onVerifySMS', onReceiveOTP);
const timeoutSubscription = eventEmitter.addListener('onVerifySMSTIMEOUT', onOTPTimeout);

}

componentWillUnmout(){
	subscription.remove();
	timeoutSubscription.remove();
}
}


```
  



