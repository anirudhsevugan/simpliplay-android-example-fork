package com.google.android.exoplayer2.util;

import android.os.SystemClock;
import android.text.TextUtils;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.analytics.AnalyticsListener$$CC;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.source.MediaLoadData;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.video.VideoSize;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class EventLogger implements AnalyticsListener {
    private static final NumberFormat a;
    private final MappingTrackSelector b;
    private final String c;
    private final Timeline.Window d;
    private final Timeline.Period e;
    private final long f;

    static {
        NumberFormat instance = NumberFormat.getInstance(Locale.US);
        a = instance;
        instance.setMinimumFractionDigits(2);
        instance.setMaximumFractionDigits(2);
        instance.setGroupingUsed(false);
    }

    public EventLogger(MappingTrackSelector mappingTrackSelector) {
        this(mappingTrackSelector, "EventLogger");
    }

    private EventLogger(MappingTrackSelector mappingTrackSelector, String str) {
        this.b = mappingTrackSelector;
        this.c = str;
        this.d = new Timeline.Window();
        this.e = new Timeline.Period();
        this.f = SystemClock.elapsedRealtime();
    }

    private static String a(long j) {
        return j == -9223372036854775807L ? "?" : a.format((double) (((float) j) / 1000.0f));
    }

    private static String a(boolean z) {
        return z ? "[X]" : "[ ]";
    }

    private void a(AnalyticsListener.EventTime eventTime, String str, Exception exc) {
        a(eventTime, "internalError", str, (Throwable) exc);
    }

    private void a(AnalyticsListener.EventTime eventTime, String str, String str2) {
        a(b(eventTime, str, str2, (Throwable) null));
    }

    private void a(AnalyticsListener.EventTime eventTime, String str, String str2, Throwable th) {
        b(b(eventTime, str, str2, th));
    }

    private void a(Metadata metadata, String str) {
        for (Metadata.Entry valueOf : metadata.a) {
            String valueOf2 = String.valueOf(valueOf);
            a(new StringBuilder(String.valueOf(str).length() + String.valueOf(valueOf2).length()).append(str).append(valueOf2).toString());
        }
    }

    private void a(String str) {
        Log.a(this.c, str);
    }

    private String b(AnalyticsListener.EventTime eventTime, String str, String str2, Throwable th) {
        String h = h(eventTime);
        String sb = new StringBuilder(String.valueOf(str).length() + 2 + String.valueOf(h).length()).append(str).append(" [").append(h).toString();
        if (str2 != null) {
            String valueOf = String.valueOf(sb);
            sb = new StringBuilder(String.valueOf(valueOf).length() + 2 + String.valueOf(str2).length()).append(valueOf).append(", ").append(str2).toString();
        }
        String a2 = Log.a(th);
        if (!TextUtils.isEmpty(a2)) {
            String valueOf2 = String.valueOf(sb);
            String replace = a2.replace("\n", "\n  ");
            sb = new StringBuilder(String.valueOf(valueOf2).length() + 4 + String.valueOf(replace).length()).append(valueOf2).append("\n  ").append(replace).append(10).toString();
        }
        return String.valueOf(sb).concat("]");
    }

    private void b(String str) {
        Log.d(this.c, str);
    }

    private void e(AnalyticsListener.EventTime eventTime, String str) {
        a(b(eventTime, str, (String) null, (Throwable) null));
    }

    private String h(AnalyticsListener.EventTime eventTime) {
        String sb = new StringBuilder(18).append("window=").append(eventTime.c).toString();
        if (eventTime.d != null) {
            String valueOf = String.valueOf(sb);
            sb = new StringBuilder(String.valueOf(valueOf).length() + 20).append(valueOf).append(", period=").append(eventTime.b.c(eventTime.d.a)).toString();
            if (eventTime.d.a()) {
                String valueOf2 = String.valueOf(sb);
                String valueOf3 = String.valueOf(new StringBuilder(String.valueOf(valueOf2).length() + 21).append(valueOf2).append(", adGroup=").append(eventTime.d.b).toString());
                sb = new StringBuilder(String.valueOf(valueOf3).length() + 16).append(valueOf3).append(", ad=").append(eventTime.d.c).toString();
            }
        }
        String a2 = a(eventTime.a - this.f);
        String a3 = a(eventTime.e);
        return new StringBuilder(String.valueOf(a2).length() + 23 + String.valueOf(a3).length() + String.valueOf(sb).length()).append("eventTime=").append(a2).append(", mediaPos=").append(a3).append(", ").append(sb).toString();
    }

    public final void a() {
        AnalyticsListener$$CC.a();
    }

    public final void a(AnalyticsListener.EventTime eventTime) {
        e(eventTime, "audioEnabled");
    }

    public final void a(AnalyticsListener.EventTime eventTime, int i) {
        String str;
        switch (i) {
            case 1:
                str = "IDLE";
                break;
            case 2:
                str = "BUFFERING";
                break;
            case 3:
                str = "READY";
                break;
            case 4:
                str = "ENDED";
                break;
            default:
                str = "?";
                break;
        }
        a(eventTime, "state", str);
    }

    public final void a(AnalyticsListener.EventTime eventTime, int i, int i2) {
        a(eventTime, "surfaceSize", new StringBuilder(24).append(i).append(", ").append(i2).toString());
    }

    public final void a(AnalyticsListener.EventTime eventTime, int i, long j, long j2) {
        a(eventTime, "audioTrackUnderrun", new StringBuilder(55).append(i).append(", ").append(j).append(", ").append(j2).toString(), (Throwable) null);
    }

    public final void a(AnalyticsListener.EventTime eventTime, ExoPlaybackException exoPlaybackException) {
        b(b(eventTime, "playerFailed", (String) null, exoPlaybackException));
    }

    public final void a(AnalyticsListener.EventTime eventTime, Format format) {
        a(eventTime, "audioInputFormat", Format.c(format));
    }

    public final void a(AnalyticsListener.EventTime eventTime, PlaybackParameters playbackParameters) {
        a(eventTime, "playbackParameters", playbackParameters.toString());
    }

    public final void a(AnalyticsListener.EventTime eventTime, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i) {
        String str;
        StringBuilder sb = new StringBuilder();
        StringBuilder append = sb.append("reason=");
        switch (i) {
            case 0:
                str = "AUTO_TRANSITION";
                break;
            case 1:
                str = "SEEK";
                break;
            case 2:
                str = "SEEK_ADJUSTMENT";
                break;
            case 3:
                str = "SKIP";
                break;
            case 4:
                str = "REMOVE";
                break;
            case 5:
                str = "INTERNAL";
                break;
            default:
                str = "?";
                break;
        }
        append.append(str).append(", PositionInfo:old [window=").append(positionInfo.a).append(", period=").append(positionInfo.b).append(", pos=").append(positionInfo.c);
        if (positionInfo.e != -1) {
            sb.append(", contentPos=").append(positionInfo.d).append(", adGroup=").append(positionInfo.e).append(", ad=").append(positionInfo.f);
        }
        sb.append("], PositionInfo:new [window=").append(positionInfo2.a).append(", period=").append(positionInfo2.b).append(", pos=").append(positionInfo2.c);
        if (positionInfo2.e != -1) {
            sb.append(", contentPos=").append(positionInfo2.d).append(", adGroup=").append(positionInfo2.e).append(", ad=").append(positionInfo2.f);
        }
        sb.append("]");
        a(eventTime, "positionDiscontinuity", sb.toString());
    }

    public final void a(AnalyticsListener.EventTime eventTime, Metadata metadata) {
        String valueOf = String.valueOf(h(eventTime));
        a(valueOf.length() != 0 ? "metadata [".concat(valueOf) : new String("metadata ["));
        a(metadata, "  ");
        a("]");
    }

    public final void a(AnalyticsListener.EventTime eventTime, MediaLoadData mediaLoadData) {
        a(eventTime, "downstreamFormat", Format.c(mediaLoadData.c));
    }

    public final void a(AnalyticsListener.EventTime eventTime, TrackSelectionArray trackSelectionArray) {
        String str;
        String str2;
        MappingTrackSelector mappingTrackSelector = this.b;
        MappingTrackSelector.MappedTrackInfo currentMappedTrackInfo = mappingTrackSelector != null ? mappingTrackSelector.getCurrentMappedTrackInfo() : null;
        if (currentMappedTrackInfo == null) {
            a(eventTime, "tracks", "[]");
            return;
        }
        AnalyticsListener.EventTime eventTime2 = eventTime;
        String valueOf = String.valueOf(h(eventTime));
        a(valueOf.length() != 0 ? "tracks [".concat(valueOf) : new String("tracks ["));
        int rendererCount = currentMappedTrackInfo.getRendererCount();
        int i = 0;
        while (true) {
            String str3 = "    Group:";
            String str4 = " [";
            if (i < rendererCount) {
                TrackGroupArray trackGroups = currentMappedTrackInfo.getTrackGroups(i);
                TrackSelection trackSelection = trackSelectionArray.b[i];
                int i2 = rendererCount;
                if (trackGroups.length == 0) {
                    String rendererName = currentMappedTrackInfo.getRendererName(i);
                    str = new StringBuilder(String.valueOf(rendererName).length() + 5).append("  ").append(rendererName).append(" []").toString();
                } else {
                    String rendererName2 = currentMappedTrackInfo.getRendererName(i);
                    String str5 = "  ]";
                    a(new StringBuilder(String.valueOf(rendererName2).length() + 4).append("  ").append(rendererName2).append(str4).toString());
                    int i3 = 0;
                    while (i3 < trackGroups.length) {
                        TrackGroup trackGroup = trackGroups.b[i3];
                        int i4 = trackGroup.a;
                        int adaptiveSupport = currentMappedTrackInfo.getAdaptiveSupport(i, i3, false);
                        if (i4 < 2) {
                            str2 = "N/A";
                        } else {
                            switch (adaptiveSupport) {
                                case 0:
                                    str2 = "NO";
                                    break;
                                case 8:
                                    str2 = "YES_NOT_SEAMLESS";
                                    break;
                                case 16:
                                    str2 = "YES";
                                    break;
                                default:
                                    throw new IllegalStateException();
                            }
                        }
                        TrackGroupArray trackGroupArray = trackGroups;
                        a(new StringBuilder(String.valueOf(str2).length() + 44).append(str3).append(i3).append(", adaptive_supported=").append(str2).append(str4).toString());
                        int i5 = 0;
                        while (i5 < trackGroup.a) {
                            String a2 = a((trackSelection == null || trackSelection.e() != trackGroup || trackSelection.c(i5) == -1) ? false : true);
                            String a3 = C.a(currentMappedTrackInfo.getTrackSupport(i, i3, i5));
                            String str6 = str4;
                            String c2 = Format.c(trackGroup.b[i5]);
                            a(new StringBuilder(String.valueOf(a2).length() + 38 + String.valueOf(c2).length() + String.valueOf(a3).length()).append("      ").append(a2).append(" Track:").append(i5).append(", ").append(c2).append(", supported=").append(a3).toString());
                            i5++;
                            str4 = str6;
                            str3 = str3;
                            trackGroup = trackGroup;
                        }
                        String str7 = str3;
                        String str8 = str4;
                        a("    ]");
                        i3++;
                        trackGroups = trackGroupArray;
                    }
                    if (trackSelection != null) {
                        int i6 = 0;
                        while (true) {
                            if (i6 < trackSelection.f()) {
                                Metadata metadata = trackSelection.a(i6).j;
                                if (metadata != null) {
                                    a("    Metadata [");
                                    a(metadata, "      ");
                                    a("    ]");
                                } else {
                                    i6++;
                                }
                            }
                        }
                    }
                    str = str5;
                }
                a(str);
                i++;
                rendererCount = i2;
            } else {
                String str9 = str3;
                String str10 = "  ]";
                String str11 = str4;
                TrackGroupArray unmappedTrackGroups = currentMappedTrackInfo.getUnmappedTrackGroups();
                if (unmappedTrackGroups.length > 0) {
                    a("  Unmapped [");
                    int i7 = 0;
                    while (i7 < unmappedTrackGroups.length) {
                        String str12 = str9;
                        String str13 = str11;
                        a(new StringBuilder(23).append(str12).append(i7).append(str13).toString());
                        int i8 = 0;
                        for (TrackGroup trackGroup2 = unmappedTrackGroups.b[i7]; i8 < trackGroup2.a; trackGroup2 = trackGroup2) {
                            String a4 = a(false);
                            String a5 = C.a(0);
                            String c3 = Format.c(trackGroup2.b[i8]);
                            a(new StringBuilder(String.valueOf(a4).length() + 38 + String.valueOf(c3).length() + String.valueOf(a5).length()).append("      ").append(a4).append(" Track:").append(i8).append(", ").append(c3).append(", supported=").append(a5).toString());
                            i8++;
                            unmappedTrackGroups = unmappedTrackGroups;
                        }
                        TrackGroupArray trackGroupArray2 = unmappedTrackGroups;
                        a("    ]");
                        i7++;
                        str9 = str12;
                        str11 = str13;
                    }
                    a(str10);
                }
                a("]");
                return;
            }
        }
    }

    public final void a(AnalyticsListener.EventTime eventTime, VideoSize videoSize) {
        int i = videoSize.b;
        a(eventTime, "videoSize", new StringBuilder(24).append(i).append(", ").append(videoSize.c).toString());
    }

    public final void a(AnalyticsListener.EventTime eventTime, IOException iOException) {
        a(eventTime, "loadError", (Exception) iOException);
    }

    public final void a(AnalyticsListener.EventTime eventTime, Exception exc) {
        a(eventTime, "drmSessionManagerError", exc);
    }

    public final void a(AnalyticsListener.EventTime eventTime, Object obj) {
        a(eventTime, "renderedFirstFrame", String.valueOf(obj));
    }

    public final void a(AnalyticsListener.EventTime eventTime, String str) {
        a(eventTime, "audioDecoderInitialized", str);
    }

    public final void a(AnalyticsListener.EventTime eventTime, List list) {
        String valueOf = String.valueOf(h(eventTime));
        a(valueOf.length() != 0 ? "staticMetadata [".concat(valueOf) : new String("staticMetadata ["));
        for (int i = 0; i < list.size(); i++) {
            Metadata metadata = (Metadata) list.get(i);
            if (metadata.a.length != 0) {
                a(new StringBuilder(24).append("  Metadata:").append(i).append(" [").toString());
                a(metadata, "    ");
                a("  ]");
            }
        }
        a("]");
    }

    public final void a(AnalyticsListener.EventTime eventTime, boolean z) {
        a(eventTime, "isPlaying", Boolean.toString(z));
    }

    public final void a(AnalyticsListener.EventTime eventTime, boolean z, int i) {
        String str;
        switch (i) {
            case 1:
                str = "USER_REQUEST";
                break;
            case 2:
                str = "AUDIO_FOCUS_LOSS";
                break;
            case 3:
                str = "AUDIO_BECOMING_NOISY";
                break;
            case 4:
                str = "REMOTE";
                break;
            case 5:
                str = "END_OF_MEDIA_ITEM";
                break;
            default:
                str = "?";
                break;
        }
        a(eventTime, "playWhenReady", new StringBuilder(String.valueOf(str).length() + 7).append(z).append(", ").append(str).toString());
    }

    public final void b() {
        AnalyticsListener$$CC.b();
    }

    public final void b(AnalyticsListener.EventTime eventTime) {
        e(eventTime, "audioDisabled");
    }

    public final void b(AnalyticsListener.EventTime eventTime, int i) {
        String str;
        switch (i) {
            case 0:
                str = "NONE";
                break;
            case 1:
                str = "TRANSIENT_AUDIO_FOCUS_LOSS";
                break;
            default:
                str = "?";
                break;
        }
        a(eventTime, "playbackSuppressionReason", str);
    }

    public final void b(AnalyticsListener.EventTime eventTime, Format format) {
        a(eventTime, "videoInputFormat", Format.c(format));
    }

    public final void b(AnalyticsListener.EventTime eventTime, MediaLoadData mediaLoadData) {
        a(eventTime, "upstreamDiscarded", Format.c(mediaLoadData.c));
    }

    public final void b(AnalyticsListener.EventTime eventTime, String str) {
        a(eventTime, "audioDecoderReleased", str);
    }

    public final void b(AnalyticsListener.EventTime eventTime, boolean z) {
        a(eventTime, "shuffleModeEnabled", Boolean.toString(z));
    }

    public final void c() {
        AnalyticsListener$$CC.c();
    }

    public final void c(AnalyticsListener.EventTime eventTime) {
        e(eventTime, "videoEnabled");
    }

    public final void c(AnalyticsListener.EventTime eventTime, int i) {
        String str;
        int b2 = eventTime.b.b();
        int a2 = eventTime.b.a();
        String h = h(eventTime);
        switch (i) {
            case 0:
                str = "PLAYLIST_CHANGED";
                break;
            case 1:
                str = "SOURCE_UPDATE";
                break;
            default:
                str = "?";
                break;
        }
        a(new StringBuilder(String.valueOf(h).length() + 69 + String.valueOf(str).length()).append("timeline [").append(h).append(", periodCount=").append(b2).append(", windowCount=").append(a2).append(", reason=").append(str).toString());
        for (int i2 = 0; i2 < Math.min(b2, 3); i2++) {
            eventTime.b.a(i2, this.e, false);
            String a3 = a(C.a(this.e.d));
            a(new StringBuilder(String.valueOf(a3).length() + 11).append("  period [").append(a3).append("]").toString());
        }
        if (b2 > 3) {
            a("  ...");
        }
        for (int i3 = 0; i3 < Math.min(a2, 3); i3++) {
            eventTime.b.a(i3, this.d, 0);
            String a4 = a(C.a(this.d.m));
            boolean z = this.d.h;
            a(new StringBuilder(String.valueOf(a4).length() + 42).append("  window [").append(a4).append(", seekable=").append(z).append(", dynamic=").append(this.d.i).append("]").toString());
        }
        if (a2 > 3) {
            a("  ...");
        }
        a("]");
    }

    public final void c(AnalyticsListener.EventTime eventTime, String str) {
        a(eventTime, "videoDecoderInitialized", str);
    }

    public final void c(AnalyticsListener.EventTime eventTime, boolean z) {
        a(eventTime, "loading", Boolean.toString(z));
    }

    public final void d() {
        AnalyticsListener$$CC.d();
    }

    public final void d(AnalyticsListener.EventTime eventTime) {
        e(eventTime, "videoDisabled");
    }

    public final void d(AnalyticsListener.EventTime eventTime, int i) {
        String str;
        String h = h(eventTime);
        switch (i) {
            case 0:
                str = "REPEAT";
                break;
            case 1:
                str = "AUTO";
                break;
            case 2:
                str = "SEEK";
                break;
            case 3:
                str = "PLAYLIST_CHANGED";
                break;
            default:
                str = "?";
                break;
        }
        a(new StringBuilder(String.valueOf(h).length() + 21 + String.valueOf(str).length()).append("mediaItem [").append(h).append(", reason=").append(str).append("]").toString());
    }

    public final void d(AnalyticsListener.EventTime eventTime, String str) {
        a(eventTime, "videoDecoderReleased", str);
    }

    public final void d(AnalyticsListener.EventTime eventTime, boolean z) {
        a(eventTime, "skipSilenceEnabled", Boolean.toString(z));
    }

    public final void e() {
        AnalyticsListener$$CC.e();
    }

    public final void e(AnalyticsListener.EventTime eventTime) {
        e(eventTime, "drmKeysLoaded");
    }

    public final void e(AnalyticsListener.EventTime eventTime, int i) {
        String str;
        switch (i) {
            case 0:
                str = "OFF";
                break;
            case 1:
                str = "ONE";
                break;
            case 2:
                str = "ALL";
                break;
            default:
                str = "?";
                break;
        }
        a(eventTime, "repeatMode", str);
    }

    public final void f() {
        AnalyticsListener$$CC.f();
    }

    public final void f(AnalyticsListener.EventTime eventTime) {
        e(eventTime, "drmKeysRestored");
    }

    public final void f(AnalyticsListener.EventTime eventTime, int i) {
        a(eventTime, "droppedFrames", Integer.toString(i));
    }

    public final void g() {
        AnalyticsListener$$CC.g();
    }

    public final void g(AnalyticsListener.EventTime eventTime) {
        e(eventTime, "drmSessionReleased");
    }

    public final void g(AnalyticsListener.EventTime eventTime, int i) {
        a(eventTime, "drmSessionAcquired", new StringBuilder(17).append("state=").append(i).toString());
    }

    public final void h() {
        AnalyticsListener$$CC.h();
    }

    public final void i() {
        AnalyticsListener$$CC.i();
    }

    public final void j() {
        AnalyticsListener$$CC.j();
    }

    public final void k() {
        AnalyticsListener$$CC.k();
    }

    public final void l() {
        AnalyticsListener$$CC.l();
    }

    public final void m() {
        AnalyticsListener$$CC.m();
    }

    public final void n() {
        AnalyticsListener$$CC.n();
    }

    public final void o() {
        AnalyticsListener$$CC.o();
    }

    public final void p() {
        AnalyticsListener$$CC.p();
    }

    public final void q() {
        AnalyticsListener$$CC.q();
    }

    public final void r() {
        AnalyticsListener$$CC.r();
    }

    public final void s() {
        AnalyticsListener$$CC.s();
    }

    public final void t() {
        AnalyticsListener$$CC.t();
    }

    public final void u() {
        AnalyticsListener$$CC.u();
    }

    public final void v() {
        AnalyticsListener$$CC.v();
    }

    public final void w() {
        AnalyticsListener$$CC.w();
    }
}
