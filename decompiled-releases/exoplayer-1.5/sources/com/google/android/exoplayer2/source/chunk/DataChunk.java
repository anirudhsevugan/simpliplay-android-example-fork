package com.google.android.exoplayer2.source.chunk;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;

public abstract class DataChunk extends Chunk {
    public byte[] a;
    private volatile boolean b;

    public DataChunk(DataSource dataSource, DataSpec dataSpec, Format format, int i, Object obj, byte[] bArr) {
        super(dataSource, dataSpec, 3, format, i, obj, -9223372036854775807L, -9223372036854775807L);
        DataChunk dataChunk;
        byte[] bArr2;
        if (bArr == null) {
            bArr2 = Util.f;
            dataChunk = this;
        } else {
            dataChunk = this;
            bArr2 = bArr;
        }
        dataChunk.a = bArr2;
    }

    public final void a() {
        this.b = true;
    }

    /* access modifiers changed from: protected */
    public abstract void a(byte[] bArr, int i);

    public final void b() {
        try {
            this.l.a(this.e);
            int i = 0;
            int i2 = 0;
            while (i != -1 && !this.b) {
                byte[] bArr = this.a;
                if (bArr.length < i2 + 16384) {
                    this.a = Arrays.copyOf(bArr, bArr.length + 16384);
                }
                i = this.l.a(this.a, i2, 16384);
                if (i != -1) {
                    i2 += i;
                }
            }
            if (!this.b) {
                a(this.a, i2);
            }
        } finally {
            Util.a((DataSource) this.l);
        }
    }
}
