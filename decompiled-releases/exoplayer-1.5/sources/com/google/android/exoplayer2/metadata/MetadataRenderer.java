package com.google.android.exoplayer2.metadata;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.exoplayer2.BaseRenderer;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.RendererCapabilities$$CC;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public final class MetadataRenderer extends BaseRenderer implements Handler.Callback {
    private final MetadataDecoderFactory a;
    private final MetadataOutput b;
    private final Handler c;
    private final MetadataInputBuffer d;
    private MetadataDecoder e;
    private boolean f;
    private boolean g;
    private long h;
    private long i;
    private Metadata j;

    public MetadataRenderer(MetadataOutput metadataOutput, Looper looper) {
        this(metadataOutput, looper, MetadataDecoderFactory.a);
    }

    private MetadataRenderer(MetadataOutput metadataOutput, Looper looper, MetadataDecoderFactory metadataDecoderFactory) {
        super(5);
        this.b = (MetadataOutput) Assertions.b((Object) metadataOutput);
        this.c = looper == null ? null : Util.a(looper, (Handler.Callback) this);
        this.a = (MetadataDecoderFactory) Assertions.b((Object) metadataDecoderFactory);
        this.d = new MetadataInputBuffer();
        this.i = -9223372036854775807L;
    }

    private void a(Metadata metadata) {
        this.b.onMetadata(metadata);
    }

    private void a(Metadata metadata, List list) {
        for (int i2 = 0; i2 < metadata.a.length; i2++) {
            Format a2 = metadata.a[i2].a();
            if (a2 == null || !this.a.a(a2)) {
                list.add(metadata.a[i2]);
            } else {
                MetadataDecoder b2 = this.a.b(a2);
                byte[] bArr = (byte[]) Assertions.b((Object) metadata.a[i2].b());
                this.d.a();
                this.d.d(bArr.length);
                ((ByteBuffer) Util.a((Object) this.d.c)).put(bArr);
                this.d.h();
                Metadata a3 = b2.a(this.d);
                if (a3 != null) {
                    a(a3, list);
                }
            }
        }
    }

    public final int a(Format format) {
        if (!this.a.a(format)) {
            return RendererCapabilities$$CC.a(0);
        }
        return RendererCapabilities$$CC.a(format.C == null ? 4 : 2);
    }

    public final void a(long j2, long j3) {
        boolean z = true;
        while (z) {
            if (!this.f && this.j == null) {
                this.d.a();
                FormatHolder t = t();
                int a2 = a(t, (DecoderInputBuffer) this.d, 0);
                if (a2 == -4) {
                    if (this.d.c()) {
                        this.f = true;
                    } else {
                        this.d.g = this.h;
                        this.d.h();
                        Metadata a3 = ((MetadataDecoder) Util.a((Object) this.e)).a(this.d);
                        if (a3 != null) {
                            ArrayList arrayList = new ArrayList(a3.a.length);
                            a(a3, (List) arrayList);
                            if (!arrayList.isEmpty()) {
                                this.j = new Metadata((List) arrayList);
                                this.i = this.d.e;
                            }
                        }
                    }
                } else if (a2 == -5) {
                    this.h = ((Format) Assertions.b((Object) t.b)).p;
                }
            }
            Metadata metadata = this.j;
            if (metadata == null || this.i > j2) {
                z = false;
            } else {
                Handler handler = this.c;
                if (handler != null) {
                    handler.obtainMessage(0, metadata).sendToTarget();
                } else {
                    a(metadata);
                }
                this.j = null;
                this.i = -9223372036854775807L;
                z = true;
            }
            if (this.f && this.j == null) {
                this.g = true;
            }
        }
    }

    public final void a(long j2, boolean z) {
        this.j = null;
        this.i = -9223372036854775807L;
        this.f = false;
        this.g = false;
    }

    public final void a(Format[] formatArr, long j2, long j3) {
        this.e = this.a.b(formatArr[0]);
    }

    public final boolean handleMessage(Message message) {
        switch (message.what) {
            case 0:
                a((Metadata) message.obj);
                return true;
            default:
                throw new IllegalStateException();
        }
    }

    public final void r() {
        this.j = null;
        this.i = -9223372036854775807L;
        this.e = null;
    }

    public final String x() {
        return "MetadataRenderer";
    }

    public final boolean y() {
        return true;
    }

    public final boolean z() {
        return this.g;
    }
}
