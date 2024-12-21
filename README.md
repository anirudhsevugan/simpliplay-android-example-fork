# ExoPlayer Creator
An Android app that can create ExoPlayer video players and HTML5 video players instantly, developed in MIT App Inventor.

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

### Issues
On Android 8, there are some input issues, like not being able to press the play button. This is **most likely an issue with the VM and shouldn't happen on a real device**.

On Android 5, any HTTPS video returns a source error, even with the correct URL typed in. This is **most likely an issue with TLS support, as Android 5 does not support modern TLS cipher suites that most websites use these days**.

On any other Android version, URLs that are signed using self-signed certificates return a source error, this is **normal behavior in Android to keep you safe, so it isn't an issue with the OS**.

