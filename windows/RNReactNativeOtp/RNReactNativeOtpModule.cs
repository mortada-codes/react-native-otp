using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace React.Native.Otp.RNReactNativeOtp
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNReactNativeOtpModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNReactNativeOtpModule"/>.
        /// </summary>
        internal RNReactNativeOtpModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNReactNativeOtp";
            }
        }
    }
}
