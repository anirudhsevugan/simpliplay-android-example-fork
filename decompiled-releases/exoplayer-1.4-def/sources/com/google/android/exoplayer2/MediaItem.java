package com.google.android.exoplayer2;

import android.net.Uri;
import com.google.android.exoplayer2.Bundleable;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class MediaItem implements Bundleable {
    public final String a;
    public final PlaybackProperties b;
    public final LiveConfiguration c;
    public final MediaMetadata d;
    public final ClippingProperties e;

    public final class Builder {
        private Object A;
        private MediaMetadata B;
        public Uri a;
        public Object b;
        public long c;
        public long d;
        public long e;
        public float f;
        public float g;
        private String h;
        private String i;
        private long j;
        private long k;
        private boolean l;
        private boolean m;
        private boolean n;
        private Uri o;
        private Map p;
        private UUID q;
        private boolean r;
        private boolean s;
        private boolean t;
        private List u;
        private byte[] v;
        private List w;
        private String x;
        private List y;
        private Uri z;

        public Builder() {
            this.k = Long.MIN_VALUE;
            this.u = Collections.emptyList();
            this.p = Collections.emptyMap();
            this.w = Collections.emptyList();
            this.y = Collections.emptyList();
            this.c = -9223372036854775807L;
            this.d = -9223372036854775807L;
            this.e = -9223372036854775807L;
            this.f = -3.4028235E38f;
            this.g = -3.4028235E38f;
        }

        private Builder(MediaItem mediaItem) {
            this();
            this.k = mediaItem.e.b;
            this.l = mediaItem.e.c;
            this.m = mediaItem.e.d;
            this.j = mediaItem.e.a;
            this.n = mediaItem.e.e;
            this.h = mediaItem.a;
            this.B = mediaItem.d;
            this.c = mediaItem.c.a;
            this.d = mediaItem.c.b;
            this.e = mediaItem.c.c;
            this.f = mediaItem.c.d;
            this.g = mediaItem.c.e;
            PlaybackProperties playbackProperties = mediaItem.b;
            if (playbackProperties != null) {
                this.x = playbackProperties.f;
                this.i = playbackProperties.b;
                this.a = playbackProperties.a;
                this.w = playbackProperties.e;
                this.y = playbackProperties.g;
                this.b = playbackProperties.h;
                DrmConfiguration drmConfiguration = playbackProperties.c;
                if (drmConfiguration != null) {
                    this.o = drmConfiguration.b;
                    this.p = drmConfiguration.c;
                    this.r = drmConfiguration.d;
                    this.t = drmConfiguration.f;
                    this.s = drmConfiguration.e;
                    this.u = drmConfiguration.g;
                    this.q = drmConfiguration.a;
                    this.v = drmConfiguration.a();
                }
                AdsConfiguration adsConfiguration = playbackProperties.d;
                if (adsConfiguration != null) {
                    this.z = adsConfiguration.a;
                    this.A = adsConfiguration.b;
                }
            }
        }

        /* synthetic */ Builder(MediaItem mediaItem, byte b2) {
            this(mediaItem);
        }

        public final Builder a(String str) {
            this.h = (String) Assertions.b((Object) str);
            return this;
        }

        public final Builder a(List list) {
            this.w = (list == null || list.isEmpty()) ? Collections.emptyList() : Collections.unmodifiableList(new ArrayList(list));
            return this;
        }

        public final MediaItem a() {
            PlaybackProperties playbackProperties;
            Assertions.b(this.o == null || this.q != null);
            Uri uri = this.a;
            AdsConfiguration adsConfiguration = null;
            if (uri != null) {
                String str = this.i;
                UUID uuid = this.q;
                DrmConfiguration drmConfiguration = uuid != null ? new DrmConfiguration(uuid, this.o, this.p, this.r, this.t, this.s, this.u, this.v, (byte) 0) : null;
                Uri uri2 = this.z;
                if (uri2 != null) {
                    adsConfiguration = new AdsConfiguration(uri2, this.A, (byte) 0);
                }
                playbackProperties = new PlaybackProperties(uri, str, drmConfiguration, adsConfiguration, this.w, this.x, this.y, this.b, (byte) 0);
            } else {
                playbackProperties = null;
            }
            String str2 = this.h;
            if (str2 == null) {
                str2 = "";
            }
            String str3 = str2;
            ClippingProperties clippingProperties = new ClippingProperties(this.j, this.k, this.l, this.m, this.n, (byte) 0);
            LiveConfiguration liveConfiguration = new LiveConfiguration(this.c, this.d, this.e, this.f, this.g);
            MediaMetadata mediaMetadata = this.B;
            if (mediaMetadata == null) {
                mediaMetadata = MediaMetadata.a;
            }
            return new MediaItem(str3, clippingProperties, playbackProperties, liveConfiguration, mediaMetadata, (byte) 0);
        }

        public final Builder b(List list) {
            this.y = (list == null || list.isEmpty()) ? Collections.emptyList() : Collections.unmodifiableList(new ArrayList(list));
            return this;
        }
    }

    public final class Subtitle {
        public final Uri a;
        public final String b;
        public final String c;
        public final int d;
        public final String e;

        public Subtitle(Uri uri, String str, String str2, int i, String str3) {
            this.a = uri;
            this.b = str;
            this.c = str2;
            this.d = i;
            this.e = str3;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Subtitle)) {
                return false;
            }
            Subtitle subtitle = (Subtitle) obj;
            return this.a.equals(subtitle.a) && this.b.equals(subtitle.b) && Util.a((Object) this.c, (Object) subtitle.c) && this.d == subtitle.d && Util.a((Object) this.e, (Object) subtitle.e);
        }

        public final int hashCode() {
            int hashCode = ((this.a.hashCode() * 31) + this.b.hashCode()) * 31;
            String str = this.c;
            int i = 0;
            int hashCode2 = (((hashCode + (str == null ? 0 : str.hashCode())) * 31) + this.d) * 31 * 31;
            String str2 = this.e;
            if (str2 != null) {
                i = str2.hashCode();
            }
            return hashCode2 + i;
        }
    }

    public final class AdsConfiguration {
        public final Uri a;
        public final Object b;

        private AdsConfiguration(Uri uri, Object obj) {
            this.a = uri;
            this.b = obj;
        }

        /* synthetic */ AdsConfiguration(Uri uri, Object obj, byte b2) {
            this(uri, obj);
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AdsConfiguration)) {
                return false;
            }
            AdsConfiguration adsConfiguration = (AdsConfiguration) obj;
            return this.a.equals(adsConfiguration.a) && Util.a(this.b, adsConfiguration.b);
        }

        public final int hashCode() {
            int hashCode = this.a.hashCode() * 31;
            Object obj = this.b;
            return hashCode + (obj != null ? obj.hashCode() : 0);
        }
    }

    public final class ClippingProperties implements Bundleable {
        public final long a;
        public final long b;
        public final boolean c;
        public final boolean d;
        public final boolean e;

        static {
            Bundleable.Creator creator = MediaItem$ClippingProperties$$Lambda$0.a;
        }

        private ClippingProperties(long j, long j2, boolean z, boolean z2, boolean z3) {
            this.a = j;
            this.b = j2;
            this.c = z;
            this.d = z2;
            this.e = z3;
        }

        /* synthetic */ ClippingProperties(long j, long j2, boolean z, boolean z2, boolean z3, byte b2) {
            this(j, j2, z, z2, z3);
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ClippingProperties)) {
                return false;
            }
            ClippingProperties clippingProperties = (ClippingProperties) obj;
            return this.a == clippingProperties.a && this.b == clippingProperties.b && this.c == clippingProperties.c && this.d == clippingProperties.d && this.e == clippingProperties.e;
        }

        public final int hashCode() {
            long j = this.a;
            long j2 = this.b;
            return (((((((((int) (j ^ (j >>> 32))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + (this.c ? 1 : 0)) * 31) + (this.d ? 1 : 0)) * 31) + (this.e ? 1 : 0);
        }
    }

    public final class DrmConfiguration {
        public final UUID a;
        public final Uri b;
        public final Map c;
        public final boolean d;
        public final boolean e;
        public final boolean f;
        public final List g;
        private final byte[] h;

        private DrmConfiguration(UUID uuid, Uri uri, Map map, boolean z, boolean z2, boolean z3, List list, byte[] bArr) {
            Assertions.a(!z2 || uri != null);
            this.a = uuid;
            this.b = uri;
            this.c = map;
            this.d = z;
            this.f = z2;
            this.e = z3;
            this.g = list;
            this.h = bArr != null ? Arrays.copyOf(bArr, bArr.length) : null;
        }

        /* synthetic */ DrmConfiguration(UUID uuid, Uri uri, Map map, boolean z, boolean z2, boolean z3, List list, byte[] bArr, byte b2) {
            this(uuid, uri, map, z, z2, z3, list, bArr);
        }

        public final byte[] a() {
            byte[] bArr = this.h;
            if (bArr != null) {
                return Arrays.copyOf(bArr, bArr.length);
            }
            return null;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DrmConfiguration)) {
                return false;
            }
            DrmConfiguration drmConfiguration = (DrmConfiguration) obj;
            return this.a.equals(drmConfiguration.a) && Util.a((Object) this.b, (Object) drmConfiguration.b) && Util.a((Object) this.c, (Object) drmConfiguration.c) && this.d == drmConfiguration.d && this.f == drmConfiguration.f && this.e == drmConfiguration.e && this.g.equals(drmConfiguration.g) && Arrays.equals(this.h, drmConfiguration.h);
        }

        public final int hashCode() {
            int hashCode = this.a.hashCode() * 31;
            Uri uri = this.b;
            return ((((((((((((hashCode + (uri != null ? uri.hashCode() : 0)) * 31) + this.c.hashCode()) * 31) + (this.d ? 1 : 0)) * 31) + (this.f ? 1 : 0)) * 31) + (this.e ? 1 : 0)) * 31) + this.g.hashCode()) * 31) + Arrays.hashCode(this.h);
        }
    }

    public final class LiveConfiguration implements Bundleable {
        public final long a;
        public final long b;
        public final long c;
        public final float d;
        public final float e;

        static {
            new LiveConfiguration(-9223372036854775807L, -9223372036854775807L, -9223372036854775807L, -3.4028235E38f, -3.4028235E38f);
            Bundleable.Creator creator = MediaItem$LiveConfiguration$$Lambda$0.a;
        }

        public LiveConfiguration(long j, long j2, long j3, float f, float f2) {
            this.a = j;
            this.b = j2;
            this.c = j3;
            this.d = f;
            this.e = f2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof LiveConfiguration)) {
                return false;
            }
            LiveConfiguration liveConfiguration = (LiveConfiguration) obj;
            return this.a == liveConfiguration.a && this.b == liveConfiguration.b && this.c == liveConfiguration.c && this.d == liveConfiguration.d && this.e == liveConfiguration.e;
        }

        public final int hashCode() {
            long j = this.a;
            long j2 = this.b;
            long j3 = this.c;
            int i = ((((((int) (j ^ (j >>> 32))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + ((int) (j3 ^ (j3 >>> 32)))) * 31;
            float f = this.d;
            int i2 = 0;
            int floatToIntBits = (i + (f != 0.0f ? Float.floatToIntBits(f) : 0)) * 31;
            float f2 = this.e;
            if (f2 != 0.0f) {
                i2 = Float.floatToIntBits(f2);
            }
            return floatToIntBits + i2;
        }
    }

    public final class PlaybackProperties {
        public final Uri a;
        public final String b;
        public final DrmConfiguration c;
        public final AdsConfiguration d;
        public final List e;
        public final String f;
        public final List g;
        public final Object h;

        private PlaybackProperties(Uri uri, String str, DrmConfiguration drmConfiguration, AdsConfiguration adsConfiguration, List list, String str2, List list2, Object obj) {
            this.a = uri;
            this.b = str;
            this.c = drmConfiguration;
            this.d = adsConfiguration;
            this.e = list;
            this.f = str2;
            this.g = list2;
            this.h = obj;
        }

        /* synthetic */ PlaybackProperties(Uri uri, String str, DrmConfiguration drmConfiguration, AdsConfiguration adsConfiguration, List list, String str2, List list2, Object obj, byte b2) {
            this(uri, str, drmConfiguration, adsConfiguration, list, str2, list2, obj);
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof PlaybackProperties)) {
                return false;
            }
            PlaybackProperties playbackProperties = (PlaybackProperties) obj;
            return this.a.equals(playbackProperties.a) && Util.a((Object) this.b, (Object) playbackProperties.b) && Util.a((Object) this.c, (Object) playbackProperties.c) && Util.a((Object) this.d, (Object) playbackProperties.d) && this.e.equals(playbackProperties.e) && Util.a((Object) this.f, (Object) playbackProperties.f) && this.g.equals(playbackProperties.g) && Util.a(this.h, playbackProperties.h);
        }

        public final int hashCode() {
            int hashCode = this.a.hashCode() * 31;
            String str = this.b;
            int i = 0;
            int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
            DrmConfiguration drmConfiguration = this.c;
            int hashCode3 = (hashCode2 + (drmConfiguration == null ? 0 : drmConfiguration.hashCode())) * 31;
            AdsConfiguration adsConfiguration = this.d;
            int hashCode4 = (((hashCode3 + (adsConfiguration == null ? 0 : adsConfiguration.hashCode())) * 31) + this.e.hashCode()) * 31;
            String str2 = this.f;
            int hashCode5 = (((hashCode4 + (str2 == null ? 0 : str2.hashCode())) * 31) + this.g.hashCode()) * 31;
            Object obj = this.h;
            if (obj != null) {
                i = obj.hashCode();
            }
            return hashCode5 + i;
        }
    }

    static {
        Bundleable.Creator creator = MediaItem$$Lambda$0.a;
    }

    private MediaItem(String str, ClippingProperties clippingProperties, PlaybackProperties playbackProperties, LiveConfiguration liveConfiguration, MediaMetadata mediaMetadata) {
        this.a = str;
        this.b = playbackProperties;
        this.c = liveConfiguration;
        this.d = mediaMetadata;
        this.e = clippingProperties;
    }

    /* synthetic */ MediaItem(String str, ClippingProperties clippingProperties, PlaybackProperties playbackProperties, LiveConfiguration liveConfiguration, MediaMetadata mediaMetadata, byte b2) {
        this(str, clippingProperties, playbackProperties, liveConfiguration, mediaMetadata);
    }

    public final Builder a() {
        return new Builder(this, (byte) 0);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaItem)) {
            return false;
        }
        MediaItem mediaItem = (MediaItem) obj;
        return Util.a((Object) this.a, (Object) mediaItem.a) && this.e.equals(mediaItem.e) && Util.a((Object) this.b, (Object) mediaItem.b) && Util.a((Object) this.c, (Object) mediaItem.c) && Util.a((Object) this.d, (Object) mediaItem.d);
    }

    public final int hashCode() {
        int hashCode = this.a.hashCode() * 31;
        PlaybackProperties playbackProperties = this.b;
        return ((((((hashCode + (playbackProperties != null ? playbackProperties.hashCode() : 0)) * 31) + this.c.hashCode()) * 31) + this.e.hashCode()) * 31) + this.d.hashCode();
    }
}
