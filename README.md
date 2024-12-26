# ExoPlayer Creator
An Android app that can create ExoPlayer 2 video players, HTML5 video players, and video players that play back what you recorded (called Camcorder players in-app) instantly, developed in MIT App Inventor.

ExoPlayer Creator primarily uses the [ExoPlayer for App Inventor](https://github.com/zainulhassan815/exoplayer-appinventor) extension to create ExoPlayer initializations, and is a modified version of the 1.0.4 AIA version of the [ExoPlayer for App Inventor demo](https://github.com/zainulhassan815/exoplayer-appinventor/blob/v2.0/aia/exoplayer_v1.0.4.aia).

| Extensions | First Used |
| -------- | ------- |
| ExoPlayer for App Inventor  | v1 and beyond    |
| UpdateMyApp | v1.4.1  |

## Compatibility
ExoPlayer Creator has been tested on the following devices with the following results:

| Device | Compatible? | Real Device/VM |
| -------- | ------- |  ------- |
| Android 15  | Yes   |  VM     |
| Android 14 | Yes  |   Real Device   |
| Android 8 | Yes  |   VM   |
| Android 5 | **No**  |   VM   |


Compatibility-wise, **most versions of Android work just fine with the app**, but if you're using Android 7 or below, note that I either haven't tested the app on those devices or the app failed to function as intended.

Make sure you **have a WebView installed**, or else the HTML5 player won't work because it relies on a WebView to display the player. This is important in VMs since unlike commercial devices they are meant more for developer purposes and thus lack some important tools, like the Play Store (some Android SDKs do not include it), WebViews, etc.

### Issues
On Android 8, there are some input issues, like not being able to press the play button. This is **most likely an issue with the VM and shouldn't happen on a real device**.

On Android 5, a severe codec issue happens when loading any video, in both ExoPlayer *and* the HTML5 player. This is most likely due to the fact that **Android 5 does not support the latest video codecs**.

And on any Android version, URLs that are signed using self-signed certificates return a source error in ExoPlayer, **this is normal behavior in Android** and it ***cannot* be fixed because Android does not allow bypassing this check**.

## Fun Facts!
The VMs were actually [Android Virtual Devices](https://developer.android.com/studio/run/managing-avds) that were created in Android Studio.



