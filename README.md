# ExoPlayer Creator
An Android tool, that can create ExoPlayer initializations instantaneously.
The **new** version utilizes **Media3**, and the App Inventor version utilizes **Media2**.

ExoPlayer Creator primarily uses the [ExoPlayer for App Inventor](https://github.com/zainulhassan815/exoplayer-appinventor) extension to create ExoPlayer initializations, and is a modified version of the 1.0.4 AIA version of the [ExoPlayer for App Inventor demo](https://github.com/zainulhassan815/exoplayer-appinventor/blob/v2.0/aia/exoplayer_v1.0.4.aia).

| Extensions | First Used |
| -------- | ------- |
| ExoPlayer for App Inventor  | v1 |
| RelativeView | v1    |
| ComponentTools | v1   |
| ~~UpdateMyApp~~ | v1.4.1 - v3.3 |
| UrsAI2SideBar | v2.0  |
| OriginalToast | v2.2  |
| Network | v2.4  |
| UrsAI2KeepAwake | v2.5  |
| GetMetrics | v2.5  |
| CustomSpotlight | v3.1  |
| ImageNotifier | v3.2.2 |
| CustomDownloader | v3.4 |
| AltNotifier | v3.4 |
| NotificationStyle | v3.4 |
| Mp3Tags | v3.9 |


## App Inventor Ver. Compatibility
ExoPlayer Creator has been tested on the following devices with the following results:

| Device | Compatible? | Real Device/VM |
| -------- | ------- |  ------- |
| Android 15  | Yes   |  VM     |
| Android 14 | Yes  |   Real Device   |
| Android 9 | Yes  |   VM   |
| Android 8 | Yes  |   VM   |
| Android 5 | **Mostly Broken**  |   VM   |


Compatibility-wise, **most versions of Android work just fine with the app**, but if you're using Android 7 or below, note that I either haven't tested the app on those devices or the app failed to function as intended.

Make sure you **have a WebView installed**, or else the HTML5 player won't work because it relies on a WebView to display the player. This is important in VMs since unlike commercial devices they are meant more for developer purposes and thus lack some important tools, like the Play Store (some Android SDKs do not include it), WebViews, etc.

### Issues (that can't really be fixed)
On Android 5, a severe codec issue happens when loading most videos (unless recorded with device camera), in both ExoPlayer *and* the HTML5 player. This is most likely due to the fact that **Android 5 does not support the latest video codecs**.

And on any Android version, URLs that are signed using self-signed certificates return a source error in any video player in the app that supports using video URLs, **this is normal behavior in Android** and it ***cannot* be fixed** because Android does not allow bypassing this check.

## Flutter Ver.
The Flutter version was built in Android Studio with the Flutter plugin, and primarily relies on the video_player and chewie plugins, uses the keep_screen_on plugin to keep screen active when playing a video, uses the file_picker plugin to pick a video and subtitle file on your device to play, and unlike the App Inventor version, uses a different UI standard, which is rendered with [Skia](https://skia.org/), Material UI.

It uses **Media 3 ExoPlayer**, something the App Inventor version would've been dreaming of, but has less controls, but is simpler to use and *much* more user-friendly, and is solely ExoPlayer (at least, for now). It does not support a quality selector, or advanced audio settings, but it supports everything else the App Inventor version does in the ExoPlayer point of view (P.O.V).

## ~~Media3 Demo (Very Early Demo)~~ (NOT RECOMMENDED)
The Media3 Demo was built in Android Studio using Kotlin, and uses the [Media3 Player](https://github.com/akhorasani/Media3Player) app as a base for all Media3 support, and uses the traditional INTENT methods for URL redirects in Kotlin.

It, unlike the other 2 versions, is in a **very early** state right now and does not support video URLs (believe me, I tried, and failed), as of now. However, the placeholder video is the [Big Buck Bunny](https://peach.blender.org/) short film, stored in a GCP (Google Cloud Protocol) storage bucket.





