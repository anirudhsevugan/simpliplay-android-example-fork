package com.dreamers.exoplayercore;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.net.Uri;
import android.util.Log;
import androidx.appcompat.widget.ActivityChooserView;
import com.dreamers.exoplayercore.repack.at;
import com.dreamers.exoplayercore.repack.da;
import com.dreamers.exoplayercore.repack.db;
import com.dreamers.exoplayercore.repack.dc;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Player$EventListener$$CC;
import com.google.android.exoplayer2.Player$Listener$$CC;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.StreamVolumeManager;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.audio.AudioListener$$CC;
import com.google.android.exoplayer2.device.DeviceInfo;
import com.google.android.exoplayer2.device.DeviceListener$$CC;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.util.EventLogger;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoListener$$CC;
import com.google.android.exoplayer2.video.VideoSize;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.OnDestroyListener;
import com.google.appinventor.components.runtime.OnPauseListener;
import com.google.appinventor.components.runtime.OnResumeListener;
import com.google.appinventor.components.runtime.OnStopListener;
import com.google.appinventor.components.runtime.util.JsonUtil;
import com.google.appinventor.components.runtime.util.YailDictionary;
import com.google.appinventor.components.runtime.util.YailList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public final class ExoplayerCore extends AndroidNonvisibleComponent implements Player.Listener, Component, OnDestroyListener, OnPauseListener, OnResumeListener, OnStopListener {
    public static final at Companion = new at((byte) 0);
    private final Context a;
    private SimpleExoPlayer b;
    private DefaultTrackSelector c;
    private final AudioManager d;
    private int e;
    private long f;
    private boolean g;
    private boolean h;
    private final ArrayList i;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExoplayerCore(ComponentContainer componentContainer) {
        super(componentContainer.$form());
        db.b(componentContainer, "container");
        Activity $context = componentContainer.$context();
        db.a((Object) $context, "container.`$context`()");
        Context context = $context;
        this.a = context;
        Object systemService = context.getSystemService("audio");
        if (systemService != null) {
            this.d = (AudioManager) systemService;
            Log.v("DreamersExoPlayer", "Registering for Activity Changes");
            this.form.registerForOnPause(this);
            this.form.registerForOnStop(this);
            this.form.registerForOnResume(this);
            this.form.registerForOnDestroy(this);
            this.i = new ArrayList();
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.media.AudioManager");
    }

    private final MediaItem.Subtitle a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("path");
            db.a((Object) string, "jsonObject.getString(\"path\")");
            String string2 = jSONObject.getString("mime_type");
            db.a((Object) string2, "jsonObject.getString(\"mime_type\")");
            String a2 = a(jSONObject, "label");
            String a3 = a(jSONObject, "language");
            Integer b2 = b(jSONObject, "selection_flags");
            return new MediaItem.Subtitle(Uri.parse(string), string2, a3, b2 == null ? 0 : b2.intValue(), a2);
        } catch (JSONException e2) {
            Log.e("DreamersExoPlayer", "parseSubtitleData | Failed to parse data : " + str + " with error : " + e2.getMessage());
            OnError("Failed to parse data : " + str + " with error : " + e2.getMessage());
            return null;
        }
    }

    private static String a(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getString(str);
        } catch (Exception e2) {
            return null;
        }
    }

    private final void a() {
        SimpleExoPlayer simpleExoPlayer = this.b;
        if (simpleExoPlayer != null) {
            this.f = simpleExoPlayer.t();
            this.e = simpleExoPlayer.r();
            this.g = simpleExoPlayer.l();
            simpleExoPlayer.b((Player.Listener) this);
            simpleExoPlayer.p();
        }
        this.b = null;
        this.c = null;
        Log.v("DreamersExoPlayer", db.a("releasePlayer : Released = ", (Object) true));
    }

    private static Integer b(JSONObject jSONObject, String str) {
        try {
            return Integer.valueOf(jSONObject.getInt(str));
        } catch (Exception e2) {
            return null;
        }
    }

    private final void b() {
        Log.v("DreamersExoPlayer", "Setting up player");
        this.c = new DefaultTrackSelector(this.a);
        SimpleExoPlayer.Builder builder = new SimpleExoPlayer.Builder(this.a);
        DefaultTrackSelector defaultTrackSelector = this.c;
        db.a((Object) defaultTrackSelector);
        SimpleExoPlayer a2 = builder.a((TrackSelector) defaultTrackSelector).a();
        a2.a(this.e, this.f);
        a2.a(this.g);
        a2.k();
        boolean z = true;
        if (!this.i.isEmpty()) {
            Log.v("DreamersExoPlayer", "setupPlayer : Using previously added media items.");
            a2.a((int) ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, (List) this.i);
        }
        a2.a((Player.Listener) this);
        DefaultTrackSelector defaultTrackSelector2 = this.c;
        db.a((Object) defaultTrackSelector2);
        a2.a((AnalyticsListener) new EventLogger(defaultTrackSelector2));
        da daVar = da.a;
        this.b = a2;
        if (a2 == null) {
            z = false;
        }
        this.h = z;
    }

    public final void AddMedia(String str, YailList yailList) {
        db.b(str, "path");
        db.b(yailList, "subtitles");
        try {
            if (str.length() > 0) {
                MediaItem.Builder builder = new MediaItem.Builder();
                builder.a = str == null ? null : Uri.parse(str);
                db.a((Object) builder, "Builder().setUri(path)");
                ArrayList arrayList = new ArrayList();
                String[] stringArray = yailList.toStringArray();
                db.a((Object) stringArray, "subtitles.toStringArray()");
                for (Object obj : (Object[]) stringArray) {
                    String str2 = (String) obj;
                    db.a((Object) str2, "subtitleData");
                    MediaItem.Subtitle a2 = a(str2);
                    if (a2 != null) {
                        arrayList.add(a2);
                    }
                }
                builder.b(arrayList);
                builder.a(str);
                MediaItem a3 = builder.a();
                db.a((Object) a3, "builder.build()");
                this.i.add(a3);
                SimpleExoPlayer simpleExoPlayer = this.b;
                if (simpleExoPlayer != null) {
                    simpleExoPlayer.a((int) ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, Collections.singletonList(a3));
                    return;
                }
                return;
            }
            throw new Exception("Path is null or empty");
        } catch (Exception e2) {
            Log.e("DreamersExoPlayer", db.a("AddMedia : Error = ", (Object) e2.getMessage()));
            OnError(db.a("AddMedia : Error = ", (Object) e2.getMessage()));
        }
    }

    public final long BufferedLocation() {
        SimpleExoPlayer simpleExoPlayer = this.b;
        if (simpleExoPlayer == null) {
            return 0;
        }
        return simpleExoPlayer.u();
    }

    public final int BufferedPercentage() {
        SimpleExoPlayer simpleExoPlayer = this.b;
        if (simpleExoPlayer == null) {
            return 0;
        }
        long u = simpleExoPlayer.u();
        long s = simpleExoPlayer.s();
        if (u == -9223372036854775807L || s == -9223372036854775807L) {
            return 0;
        }
        if (s == 0) {
            return 100;
        }
        return Util.a((int) ((u * 100) / s), 0, 100);
    }

    public final void ClearMediaItems() {
        SimpleExoPlayer simpleExoPlayer = this.b;
        if (simpleExoPlayer != null) {
            simpleExoPlayer.a(0, (int) ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
        }
        this.i.clear();
    }

    public final void CreatePlayer() {
        b();
    }

    public final long CurrentPosition() {
        SimpleExoPlayer simpleExoPlayer = this.b;
        if (simpleExoPlayer == null) {
            return 0;
        }
        return simpleExoPlayer.t();
    }

    public final void DecreaseVolume() {
        SimpleExoPlayer simpleExoPlayer = this.b;
        if (simpleExoPlayer != null) {
            simpleExoPlayer.G();
            StreamVolumeManager streamVolumeManager = simpleExoPlayer.c;
            if (streamVolumeManager.d > streamVolumeManager.a()) {
                streamVolumeManager.b.adjustStreamVolume(streamVolumeManager.c, -1, 1);
                streamVolumeManager.d();
            }
        }
    }

    public final void DeviceMuted(boolean z) {
        SimpleExoPlayer simpleExoPlayer = this.b;
        if (simpleExoPlayer != null) {
            simpleExoPlayer.G();
            StreamVolumeManager streamVolumeManager = simpleExoPlayer.c;
            if (Util.a >= 23) {
                streamVolumeManager.b.adjustStreamVolume(streamVolumeManager.c, z ? -100 : 100, 1);
            } else {
                streamVolumeManager.b.setStreamMute(streamVolumeManager.c, z);
            }
            streamVolumeManager.d();
        }
    }

    public final boolean DeviceMuted() {
        SimpleExoPlayer simpleExoPlayer = this.b;
        if (simpleExoPlayer == null) {
            return false;
        }
        simpleExoPlayer.G();
        return simpleExoPlayer.c.e;
    }

    public final int DeviceVolume() {
        SimpleExoPlayer simpleExoPlayer = this.b;
        if (simpleExoPlayer == null) {
            return 0;
        }
        simpleExoPlayer.G();
        return simpleExoPlayer.c.d;
    }

    public final void DeviceVolume(int i2) {
        SimpleExoPlayer simpleExoPlayer = this.b;
        if (simpleExoPlayer != null) {
            simpleExoPlayer.G();
            StreamVolumeManager streamVolumeManager = simpleExoPlayer.c;
            if (i2 >= streamVolumeManager.a() && i2 <= streamVolumeManager.b()) {
                streamVolumeManager.b.setStreamVolume(streamVolumeManager.c, i2, 1);
                streamVolumeManager.d();
            }
        }
    }

    public final long Duration() {
        SimpleExoPlayer simpleExoPlayer = this.b;
        if (simpleExoPlayer == null) {
            return 0;
        }
        return simpleExoPlayer.s();
    }

    public final String Format(int i2) {
        long j = ((long) i2) / 1000;
        dc dcVar = dc.a;
        String format = String.format(Locale.US, "%02d:%02d:%02d", Arrays.copyOf(new Object[]{Long.valueOf(j / 3600), Long.valueOf((j % 3600) / 60), Long.valueOf(j % 60)}, 3));
        db.a((Object) format, "java.lang.String.format(locale, format, *args)");
        return format;
    }

    public final void IncreaseVolume() {
        SimpleExoPlayer simpleExoPlayer = this.b;
        if (simpleExoPlayer != null) {
            simpleExoPlayer.G();
            StreamVolumeManager streamVolumeManager = simpleExoPlayer.c;
            if (streamVolumeManager.d < streamVolumeManager.b()) {
                streamVolumeManager.b.adjustStreamVolume(streamVolumeManager.c, 1, 1);
                streamVolumeManager.d();
            }
        }
    }

    public final boolean IsLoading() {
        SimpleExoPlayer simpleExoPlayer = this.b;
        if (simpleExoPlayer == null) {
            return false;
        }
        simpleExoPlayer.G();
        return simpleExoPlayer.b.j.g;
    }

    public final boolean IsPlaying() {
        SimpleExoPlayer simpleExoPlayer = this.b;
        if (simpleExoPlayer == null) {
            return false;
        }
        return simpleExoPlayer.a();
    }

    public final int MaxVolume() {
        return this.d.getStreamMaxVolume(3);
    }

    public final int MinVolume() {
        return this.d.getStreamMinVolume(3);
    }

    public final void OnAppResume() {
        EventDispatcher.dispatchEvent(this, "OnAppResume", new Object[0]);
    }

    public final void OnError(String str) {
        db.b(str, "error");
        EventDispatcher.dispatchEvent(this, "OnError", str);
    }

    public final void OnIsPlayingChanged(boolean z) {
        EventDispatcher.dispatchEvent(this, "OnIsPlayingChanged", Boolean.valueOf(z));
    }

    public final void OnLoadingChanged(boolean z) {
        EventDispatcher.dispatchEvent(this, "OnLoadingChanged", Boolean.valueOf(z));
    }

    public final void OnMediaItemTransition(String str, int i2) {
        db.b(str, "mediaUrl");
        EventDispatcher.dispatchEvent(this, "OnMediaItemTransition", str, Integer.valueOf(i2));
    }

    public final void OnMetadataChanged(Object obj) {
        db.b(obj, "data");
        EventDispatcher.dispatchEvent(this, "OnMetadataChanged", obj);
    }

    public final void OnRenderFirstFrame() {
        EventDispatcher.dispatchEvent(this, "OnRenderFirstFrame", new Object[0]);
    }

    public final void OnRepeatModeChanged(int i2) {
        EventDispatcher.dispatchEvent(this, "OnRepeatModeChanged", Integer.valueOf(i2));
    }

    public final void OnShuffleModeEnabledChanged(boolean z) {
        EventDispatcher.dispatchEvent(this, "OnShuffleModeEnabledChanged", Boolean.valueOf(z));
    }

    public final void OnStateChanged(int i2) {
        EventDispatcher.dispatchEvent(this, "OnStateChanged", Integer.valueOf(i2));
    }

    public final void OnVideoSizeChanged(int i2, int i3, float f2, int i4) {
        EventDispatcher.dispatchEvent(this, "OnVideoSizeChanged", Integer.valueOf(i2), Integer.valueOf(i3), Float.valueOf(f2), Integer.valueOf(i4));
    }

    public final void OnVolumeChanged(int i2, boolean z) {
        EventDispatcher.dispatchEvent(this, "OnVolumeChanged", Integer.valueOf(i2), Boolean.valueOf(z));
    }

    public final void Pause() {
        SimpleExoPlayer simpleExoPlayer = this.b;
        if (simpleExoPlayer != null) {
            simpleExoPlayer.a(false);
        }
    }

    public final void Play() {
        SimpleExoPlayer simpleExoPlayer = this.b;
        if (simpleExoPlayer != null) {
            simpleExoPlayer.a(true);
        }
    }

    public final void PlayWhenReady(boolean z) {
        SimpleExoPlayer simpleExoPlayer = this.b;
        if (simpleExoPlayer != null) {
            simpleExoPlayer.a(z);
        }
        this.g = z;
    }

    public final int PlaybackState() {
        SimpleExoPlayer simpleExoPlayer = this.b;
        if (simpleExoPlayer == null) {
            return 0;
        }
        return simpleExoPlayer.h();
    }

    public final Object Player() {
        return this.b;
    }

    public final void RemoveMedia(int i2) {
        SimpleExoPlayer simpleExoPlayer = this.b;
        if (simpleExoPlayer != null) {
            simpleExoPlayer.a(i2, i2 + 1);
        }
        this.i.remove(i2);
    }

    public final int RepeatModeAll() {
        return 2;
    }

    public final int RepeatModeOff() {
        return 0;
    }

    public final int RepeatModeOne() {
        return 1;
    }

    public final void RepeatModes(int i2) {
        SimpleExoPlayer simpleExoPlayer = this.b;
        if (simpleExoPlayer != null) {
            simpleExoPlayer.b(i2);
        }
    }

    public final void SeekTo(long j) {
        SimpleExoPlayer simpleExoPlayer = this.b;
        if (simpleExoPlayer != null) {
            simpleExoPlayer.a(simpleExoPlayer.r(), j);
        }
    }

    public final int SelectionFlagAutoSelect() {
        return 4;
    }

    public final int SelectionFlagDefault() {
        return 1;
    }

    public final int SelectionFlagForced() {
        return 2;
    }

    public final int StateBuffering() {
        return 2;
    }

    public final int StateEnded() {
        return 4;
    }

    public final int StateIdle() {
        return 1;
    }

    public final int StateReady() {
        return 3;
    }

    public final void Stop() {
        SimpleExoPlayer simpleExoPlayer = this.b;
        if (simpleExoPlayer != null) {
            simpleExoPlayer.c(false);
        }
    }

    public final int TrackTypeAudio() {
        return 1;
    }

    public final int TrackTypeText() {
        return 3;
    }

    public final int TrackTypeVideo() {
        return 2;
    }

    public final int TransitionReasonAuto() {
        return 1;
    }

    public final int TransitionReasonPlaylistChanged() {
        return 3;
    }

    public final int TransitionReasonRepeat() {
        return 0;
    }

    public final int TransitionReasonSeek() {
        return 2;
    }

    public final void onAudioAttributesChanged(AudioAttributes audioAttributes) {
        AudioListener$$CC.b();
    }

    public final void onAudioSessionIdChanged(int i2) {
        AudioListener$$CC.a();
    }

    public final void onAvailableCommandsChanged(Player.Commands commands) {
        Player$EventListener$$CC.i();
    }

    public final void onCues(List list) {
        Player$Listener$$CC.a();
    }

    public final void onDestroy() {
        Log.v("DreamersExoPlayer", "onDestroy");
        a();
    }

    public final void onDeviceInfoChanged(DeviceInfo deviceInfo) {
        DeviceListener$$CC.a();
    }

    public final void onDeviceVolumeChanged(int i2, boolean z) {
        DeviceListener$$CC.b();
        Log.v("DreamersExoPlayer", "onDeviceVolumeChanged : Volume = " + i2 + " | Muted : " + z);
        OnVolumeChanged(i2, z);
    }

    public final void onEvents(Player player, Player.Events events) {
        Player$EventListener$$CC.v();
    }

    public final void onIsLoadingChanged(boolean z) {
        Player$EventListener$$CC.g();
        Log.v("DreamersExoPlayer", db.a("onIsLoadingChanged : ", (Object) Boolean.valueOf(z)));
        OnLoadingChanged(z);
    }

    public final void onIsPlayingChanged(boolean z) {
        Player$EventListener$$CC.n();
        Log.v("DreamersExoPlayer", db.a("onIsPlayingChanged : IsPlaying = ", (Object) Boolean.valueOf(z)));
        OnIsPlayingChanged(z);
    }

    public final void onLoadingChanged(boolean z) {
        Player$EventListener$$CC.h();
    }

    public final void onMediaItemTransition(MediaItem mediaItem, int i2) {
        Player$EventListener$$CC.c();
        String str = null;
        Log.v("DreamersExoPlayer", "onMediaItemTransition : MediaUrl = " + (mediaItem == null ? null : mediaItem.a) + " | Reason = " + i2);
        if (mediaItem != null) {
            str = mediaItem.a;
        }
        OnMediaItemTransition(String.valueOf(str), i2);
    }

    public final void onMediaMetadataChanged(MediaMetadata mediaMetadata) {
        db.b(mediaMetadata, "mediaMetadata");
        Player$EventListener$$CC.f();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("title", String.valueOf(mediaMetadata.b));
        jSONObject.put("artist", String.valueOf(mediaMetadata.c));
        jSONObject.put("albumTitle", String.valueOf(mediaMetadata.d));
        jSONObject.put("albumArtist", String.valueOf(mediaMetadata.e));
        jSONObject.put("displayTitle", String.valueOf(mediaMetadata.f));
        jSONObject.put("subtitle", String.valueOf(mediaMetadata.g));
        jSONObject.put("description", String.valueOf(mediaMetadata.h));
        jSONObject.put("media_uri", String.valueOf(mediaMetadata.i));
        jSONObject.put("artwork_uri", String.valueOf(mediaMetadata.m));
        jSONObject.put("track_number", mediaMetadata.n);
        jSONObject.put("total_tracks", mediaMetadata.o);
        jSONObject.put("year", mediaMetadata.r);
        jSONObject.put("playable", mediaMetadata.q);
        YailDictionary dictionaryFromJsonObject = JsonUtil.getDictionaryFromJsonObject(jSONObject);
        db.a((Object) dictionaryFromJsonObject, "getDictionaryFromJsonObject(data)");
        Log.v("DreamersExoPlayer", db.a("onMediaMetadataChanged : MetaData = ", (Object) dictionaryFromJsonObject));
        OnMetadataChanged(dictionaryFromJsonObject);
    }

    public final void onMetadata(Metadata metadata) {
        Player$Listener$$CC.b();
    }

    public final void onPause() {
        Log.v("DreamersExoPlayer", "onPause");
        if (Util.a < 24) {
            a();
        }
    }

    public final void onPlayWhenReadyChanged(boolean z, int i2) {
        Player$EventListener$$CC.l();
    }

    public final void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        Player$EventListener$$CC.t();
    }

    public final void onPlaybackStateChanged(int i2) {
        Player$EventListener$$CC.k();
        Log.v("DreamersExoPlayer", db.a("onPlaybackStateChanged : ", (Object) Integer.valueOf(i2)));
        OnStateChanged(i2);
    }

    public final void onPlaybackSuppressionReasonChanged(int i2) {
        Player$EventListener$$CC.m();
    }

    public final void onPlayerError(ExoPlaybackException exoPlaybackException) {
        db.b(exoPlaybackException, "e");
        Player$EventListener$$CC.q();
        Log.e("DreamersExoPlayer", db.a("onPlayerError : ", (Object) exoPlaybackException.getMessage()));
        OnError(exoPlaybackException.toString());
    }

    public final void onPlayerStateChanged(boolean z, int i2) {
        Player$EventListener$$CC.j();
    }

    public final void onPositionDiscontinuity(int i2) {
        Player$EventListener$$CC.r();
    }

    public final void onPositionDiscontinuity(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i2) {
        Player$EventListener$$CC.s();
    }

    public final void onRenderedFirstFrame() {
        VideoListener$$CC.d();
        Log.v("DreamersExoPlayer", "onRenderedFirstFrame");
        OnRenderFirstFrame();
    }

    public final void onRepeatModeChanged(int i2) {
        Player$EventListener$$CC.o();
        Log.v("DreamersExoPlayer", db.a("onRepeatModeChanged : RepeatMode = ", (Object) Integer.valueOf(i2)));
        OnRepeatModeChanged(i2);
    }

    public final void onResume() {
        Log.v("DreamersExoPlayer", "onResume");
        if ((Util.a < 24 || this.b == null) && this.b == null && this.h) {
            b();
            Log.v("DreamersExoPlayer", "OnAppResume");
            OnAppResume();
        }
    }

    public final void onSeekProcessed() {
        Player$EventListener$$CC.u();
    }

    public final void onShuffleModeEnabledChanged(boolean z) {
        Player$EventListener$$CC.p();
        Log.v("DreamersExoPlayer", db.a("onShuffleModeEnabledChanged : ShuffleModeEnabled = ", (Object) Boolean.valueOf(z)));
        OnShuffleModeEnabledChanged(z);
    }

    public final void onSkipSilenceEnabledChanged(boolean z) {
        AudioListener$$CC.d();
    }

    public final void onStaticMetadataChanged(List list) {
        Player$EventListener$$CC.e();
    }

    public final void onStop() {
        Log.v("DreamersExoPlayer", "onStop");
        if (Util.a >= 24) {
            a();
        }
    }

    public final void onSurfaceSizeChanged(int i2, int i3) {
        VideoListener$$CC.c();
    }

    public final void onTimelineChanged(Timeline timeline, int i2) {
        Player$EventListener$$CC.a();
    }

    public final void onTimelineChanged(Timeline timeline, Object obj, int i2) {
        Player$EventListener$$CC.b();
    }

    public final void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
        Player$EventListener$$CC.d();
    }

    public final void onVideoSizeChanged(int i2, int i3, int i4, float f2) {
        VideoListener$$CC.b();
    }

    public final void onVideoSizeChanged(VideoSize videoSize) {
        db.b(videoSize, "videoSize");
        VideoListener$$CC.a();
        Log.i("DreamersExoPlayer", db.a("onVideoSizeChanged : ", (Object) videoSize));
        OnVideoSizeChanged(videoSize.b, videoSize.c, videoSize.e, videoSize.d);
    }

    public final void onVolumeChanged(float f2) {
        AudioListener$$CC.c();
    }
}
