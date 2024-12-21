package com.google.android.exoplayer2.source.dash;

import android.os.Handler;
import android.os.Message;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.TrackOutput$$CC;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.MetadataInputBuffer;
import com.google.android.exoplayer2.metadata.emsg.EventMessage;
import com.google.android.exoplayer2.metadata.emsg.EventMessageDecoder;
import com.google.android.exoplayer2.source.SampleQueue;
import com.google.android.exoplayer2.source.dash.manifest.DashManifest;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DataReader;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.google.appinventor.components.runtime.Component;
import java.util.TreeMap;

public final class PlayerEmsgHandler implements Handler.Callback {
    final Allocator a;
    final PlayerEmsgCallback b;
    /* access modifiers changed from: package-private */
    public final Handler c = Util.a((Handler.Callback) this);
    final TreeMap d = new TreeMap();
    DashManifest e;
    long f;
    boolean g;
    boolean h;
    boolean i;
    /* access modifiers changed from: private */
    public final EventMessageDecoder j = new EventMessageDecoder();

    final class ManifestExpiryEventInfo {
        public final long a;
        public final long b;

        public ManifestExpiryEventInfo(long j, long j2) {
            this.a = j;
            this.b = j2;
        }
    }

    public interface PlayerEmsgCallback {
        void a();

        void a(long j);
    }

    public final class PlayerTrackEmsgHandler implements TrackOutput {
        final SampleQueue a;
        long b = -9223372036854775807L;
        private final FormatHolder d = new FormatHolder();
        private final MetadataInputBuffer e = new MetadataInputBuffer();

        PlayerTrackEmsgHandler(Allocator allocator) {
            this.a = SampleQueue.a(allocator);
        }

        public final int a(DataReader dataReader, int i, boolean z) {
            return TrackOutput$$CC.a(this.a, dataReader, i, z);
        }

        public final void a(long j, int i, int i2, int i3, TrackOutput.CryptoData cryptoData) {
            MetadataInputBuffer metadataInputBuffer;
            this.a.a(j, i, i2, i3, cryptoData);
            while (this.a.b(false)) {
                this.e.a();
                if (this.a.a(this.d, (DecoderInputBuffer) this.e, 0, false) == -4) {
                    this.e.h();
                    metadataInputBuffer = this.e;
                } else {
                    metadataInputBuffer = null;
                }
                if (metadataInputBuffer != null) {
                    long j2 = metadataInputBuffer.e;
                    Metadata a2 = PlayerEmsgHandler.this.j.a(metadataInputBuffer);
                    if (a2 != null) {
                        EventMessage eventMessage = (EventMessage) a2.a[0];
                        if (PlayerEmsgHandler.a(eventMessage.a, eventMessage.b)) {
                            long a3 = PlayerEmsgHandler.b(eventMessage);
                            if (a3 != -9223372036854775807L) {
                                PlayerEmsgHandler.this.c.sendMessage(PlayerEmsgHandler.this.c.obtainMessage(1, new ManifestExpiryEventInfo(j2, a3)));
                            }
                        }
                    }
                }
            }
            SampleQueue sampleQueue = this.a;
            sampleQueue.a.a(sampleQueue.m());
        }

        public final void a(Format format) {
            this.a.a(format);
        }

        public final void a(ParsableByteArray parsableByteArray, int i) {
            TrackOutput$$CC.a(this.a, parsableByteArray, i);
        }

        public final int b(DataReader dataReader, int i, boolean z) {
            return TrackOutput$$CC.a(this, dataReader, i, z);
        }

        public final void b(ParsableByteArray parsableByteArray, int i) {
            TrackOutput$$CC.a(this, parsableByteArray, i);
        }
    }

    public PlayerEmsgHandler(DashManifest dashManifest, PlayerEmsgCallback playerEmsgCallback, Allocator allocator) {
        this.e = dashManifest;
        this.b = playerEmsgCallback;
        this.a = allocator;
    }

    static /* synthetic */ boolean a(String str, String str2) {
        if ("urn:mpeg:dash:event:2012".equals(str)) {
            return Component.TYPEFACE_SANSSERIF.equals(str2) || Component.TYPEFACE_SERIF.equals(str2) || Component.TYPEFACE_MONOSPACE.equals(str2);
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static long b(EventMessage eventMessage) {
        try {
            return Util.e(Util.a(eventMessage.e));
        } catch (ParserException e2) {
            return -9223372036854775807L;
        }
    }

    /* access modifiers changed from: package-private */
    public final void a() {
        if (this.g) {
            this.h = true;
            this.g = false;
            this.b.a();
        }
    }

    public final boolean handleMessage(Message message) {
        if (this.i) {
            return true;
        }
        switch (message.what) {
            case 1:
                ManifestExpiryEventInfo manifestExpiryEventInfo = (ManifestExpiryEventInfo) message.obj;
                long j2 = manifestExpiryEventInfo.a;
                long j3 = manifestExpiryEventInfo.b;
                Long l = (Long) this.d.get(Long.valueOf(j3));
                if (l == null || l.longValue() > j2) {
                    this.d.put(Long.valueOf(j3), Long.valueOf(j2));
                }
                return true;
            default:
                return false;
        }
    }
}
