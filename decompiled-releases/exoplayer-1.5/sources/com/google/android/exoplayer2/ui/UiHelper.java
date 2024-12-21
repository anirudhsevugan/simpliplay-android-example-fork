package com.google.android.exoplayer2.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.google.appinventor.components.runtime.Form;
import java.io.FileInputStream;
import java.io.InputStream;

public class UiHelper {
    public static final int BOTTOM_BAR_COLOR = Color.parseColor("#b0000000");
    public static final float BUTTON_DISABLED_ALPHA = 0.33f;
    public static final float BUTTON_ENABLED_ALPHA = 1.0f;
    public static final int CONTROLS_COLOR = Color.parseColor("#98000000");
    public static final int DIM_WHITE = Color.parseColor("#FFBEBEBE");
    public static final String IC_AUDIO_TRACK = "ic_audio_track.png";
    public static final String IC_CHECK = "ic_check.png";
    public static final String IC_FAST_FORWARD = "ic_fast_forward.png";
    public static final String IC_FULLSCREEN_ENTER = "ic_fullscreen_enter.png";
    public static final String IC_FULLSCREEN_EXIT = "ic_fullscreen_exit.png";
    public static final String IC_NEXT = "ic_next.png";
    public static final String IC_PAUSE = "ic_pause.png";
    public static final String IC_PLAY = "ic_play.png";
    public static final String IC_PREVIOUS = "ic_previous.png";
    public static final String IC_REPEAT_ALL = "ic_repeat_all.png";
    public static final String IC_REPEAT_OFF = "ic_repeat_off.png";
    public static final String IC_REPEAT_ONE = "ic_repeat_one.png";
    public static final String IC_REWIND = "ic_rewind.png";
    public static final String IC_SETTINGS = "ic_settings.png";
    public static final String IC_SHUFFLE_OFF = "ic_shuffle_off.png";
    public static final String IC_SHUFFLE_ON = "ic_shuffle_on.png";
    public static final String IC_SPEED = "ic_speed.png";
    public static final String IC_SUBTITLE_OFF = "ic_subtitle_off.png";
    public static final String IC_SUBTITLE_ON = "ic_subtitle_on.png";
    public static final String IC_VIDEO_SETTINGS = "ic_video_settings.png";
    public static final String INITIAL_DURATION = "00:00";
    public static final String LOG_TAG = "ExoplayerUi";
    public static final int TRANSPARENT_BLACK = Color.parseColor("#CC000000");

    public static int convertToDp(float f) {
        return (int) TypedValue.applyDimension(1, f, Resources.getSystem().getDisplayMetrics());
    }

    public static ImageButton createBottomImageButton(Context context, Drawable drawable) {
        ImageButton imageButton = new ImageButton(context);
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(convertToDp(48.0f), convertToDp(48.0f));
        int convertToDp = convertToDp(2.0f);
        marginLayoutParams.setMargins(convertToDp, 0, convertToDp, 0);
        imageButton.setLayoutParams(marginLayoutParams);
        imageButton.setImageDrawable(drawable);
        imageButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
        int convertToDp2 = convertToDp(12.0f);
        imageButton.setPadding(convertToDp2, convertToDp2, convertToDp2, convertToDp2);
        setSelectableBackground(imageButton, true);
        return imageButton;
    }

    public static ImageButton createCenterImageButton(Context context, Drawable drawable) {
        ImageButton imageButton = new ImageButton(context);
        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(convertToDp(50.0f), convertToDp(50.0f));
        int convertToDp = convertToDp(4.0f);
        marginLayoutParams.setMargins(convertToDp, 0, convertToDp, 0);
        imageButton.setLayoutParams(marginLayoutParams);
        imageButton.setImageDrawable(drawable);
        imageButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
        int convertToDp2 = convertToDp(6.0f);
        imageButton.setPadding(convertToDp2, convertToDp2, convertToDp2, convertToDp2);
        setSelectableBackground(imageButton, true);
        return imageButton;
    }

    public static InputStream getAsset(Context context, String str, boolean z) {
        String str2;
        StringBuilder append;
        if (!z) {
            return context.getAssets().open(str);
        }
        try {
            if (!context.getClass().getName().contains("makeroid")) {
                append = context.getClass().getName().contains(Form.APPINVENTOR_URL_SCHEME) ? Build.VERSION.SDK_INT >= 29 ? new StringBuilder().append(context.getExternalFilesDir((String) null).toString()).append("/assets/").append(str) : new StringBuilder().append(context.getExternalFilesDir((String) null).toString()).append("/AppInventor/assets/").append(str) : new StringBuilder().append(context.getExternalFilesDir((String) null).toString()).append("/assets/").append(str);
            } else if (Build.VERSION.SDK_INT >= 29) {
                append = new StringBuilder().append(context.getExternalFilesDir((String) null).toString()).append("/assets/").append(str);
            } else {
                str2 = "/storage/emulated/0/Kodular/assets/".concat(String.valueOf(str));
                Log.v(LOG_TAG, "getAsset | Filepath = ".concat(String.valueOf(str2)));
                return new FileInputStream(str2);
            }
            str2 = append.toString();
            Log.v(LOG_TAG, "getAsset | Filepath = ".concat(String.valueOf(str2)));
            return new FileInputStream(str2);
        } catch (Exception e) {
            Log.e(LOG_TAG, "getAsset | Debug Mode : " + z + " | Error : " + e);
            return null;
        }
    }

    public static Drawable getDrawable(Context context, String str, boolean z) {
        try {
            InputStream asset = getAsset(context, str, z);
            if (asset == null) {
                return null;
            }
            Drawable createFromStream = Drawable.createFromStream(asset, (String) null);
            asset.close();
            return createFromStream;
        } catch (Exception e) {
            Log.v(LOG_TAG, "getDrawable : Error = ".concat(String.valueOf(e)));
            return null;
        }
    }

    public static void setSelectableBackground(View view, boolean z) {
        TypedValue typedValue = new TypedValue();
        view.getContext().getTheme().resolveAttribute(z ? 16843868 : 16843534, typedValue, true);
        view.setBackgroundResource(typedValue.resourceId);
    }
}
