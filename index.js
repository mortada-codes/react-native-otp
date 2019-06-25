
import { NativeModules,NativeEventEmitter,Platform } from 'react-native';
let module = {
    requestSMS:new Promise(function(resolve,reject){
        if(__DEV__){
            console.warn('detect OTP avialable only for android')
        }
        resolve()
    }),
    detectorEmitter:function(){}
}
if(Platform.OS === 'android'){
module = NativeModules.RNReactNativeOtp;
const OTP_EVENT = 'onVerifySMS';

module.detectorEmitter  = function(callback){


const detectorEmitter =  new NativeEventEmitter(module)
const subscription = detectorEmitter.addListener(
    OTP_EVENT,
    callback);
  return subscription
}  


}


export default module
