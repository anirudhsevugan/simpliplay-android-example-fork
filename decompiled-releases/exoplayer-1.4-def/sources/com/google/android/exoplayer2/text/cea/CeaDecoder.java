package com.google.android.exoplayer2.text.cea;

import androidx.core.location.LocationRequestCompat;
import com.google.android.exoplayer2.decoder.OutputBuffer;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.text.SubtitleDecoder;
import com.google.android.exoplayer2.text.SubtitleInputBuffer;
import com.google.android.exoplayer2.text.SubtitleOutputBuffer;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayDeque;
import java.util.PriorityQueue;

abstract class CeaDecoder implements SubtitleDecoder {
    final ArrayDeque a;
    private final ArrayDeque b = new ArrayDeque();
    private final PriorityQueue c;
    private CeaInputBuffer d;
    private long e;
    private long f;

    final class CeaInputBuffer extends SubtitleInputBuffer implements Comparable {
        /* access modifiers changed from: private */
        public long h;

        private CeaInputBuffer() {
        }

        /* synthetic */ CeaInputBuffer(byte b) {
            this();
        }

        public final /* synthetic */ int compareTo(Object obj) {
            CeaInputBuffer ceaInputBuffer = (CeaInputBuffer) obj;
            if (c() != ceaInputBuffer.c()) {
                return c() ? 1 : -1;
            }
            long j = this.e - ceaInputBuffer.e;
            if (j == 0) {
                j = this.h - ceaInputBuffer.h;
                if (j == 0) {
                    return 0;
                }
            }
            return j > 0 ? 1 : -1;
        }
    }

    final class CeaOutputBuffer extends SubtitleOutputBuffer {
        private OutputBuffer.Owner c;

        public CeaOutputBuffer(OutputBuffer.Owner owner) {
            this.c = owner;
        }

        public final void f() {
            this.c.a(this);
        }
    }

    public CeaDecoder() {
        for (int i = 0; i < 10; i++) {
            this.b.add(new CeaInputBuffer((byte) 0));
        }
        this.a = new ArrayDeque();
        for (int i2 = 0; i2 < 2; i2++) {
            this.a.add(new CeaOutputBuffer(new CeaDecoder$$Lambda$0(this)));
        }
        this.c = new PriorityQueue();
    }

    private void a(CeaInputBuffer ceaInputBuffer) {
        ceaInputBuffer.a();
        this.b.add(ceaInputBuffer);
    }

    public void a(long j) {
        this.e = j;
    }

    /* access modifiers changed from: protected */
    public abstract void a(SubtitleInputBuffer subtitleInputBuffer);

    /* renamed from: b */
    public void a(SubtitleInputBuffer subtitleInputBuffer) {
        Assertions.a(subtitleInputBuffer == this.d);
        CeaInputBuffer ceaInputBuffer = (CeaInputBuffer) subtitleInputBuffer;
        if (ceaInputBuffer.d_()) {
            a(ceaInputBuffer);
        } else {
            long j = this.f;
            this.f = 1 + j;
            long unused = ceaInputBuffer.h = j;
            this.c.add(ceaInputBuffer);
        }
        this.d = null;
    }

    public void c() {
        this.f = 0;
        this.e = 0;
        while (!this.c.isEmpty()) {
            a((CeaInputBuffer) Util.a((Object) (CeaInputBuffer) this.c.poll()));
        }
        CeaInputBuffer ceaInputBuffer = this.d;
        if (ceaInputBuffer != null) {
            a(ceaInputBuffer);
            this.d = null;
        }
    }

    public void d() {
    }

    /* renamed from: e */
    public SubtitleOutputBuffer b() {
        SubtitleOutputBuffer subtitleOutputBuffer;
        if (this.a.isEmpty()) {
            return null;
        }
        while (!this.c.isEmpty() && ((CeaInputBuffer) Util.a((Object) (CeaInputBuffer) this.c.peek())).e <= this.e) {
            CeaInputBuffer ceaInputBuffer = (CeaInputBuffer) Util.a((Object) (CeaInputBuffer) this.c.poll());
            if (ceaInputBuffer.c()) {
                subtitleOutputBuffer = (SubtitleOutputBuffer) Util.a((Object) (SubtitleOutputBuffer) this.a.pollFirst());
                subtitleOutputBuffer.a_(4);
            } else {
                a((SubtitleInputBuffer) ceaInputBuffer);
                if (f()) {
                    Subtitle g = g();
                    subtitleOutputBuffer = (SubtitleOutputBuffer) Util.a((Object) (SubtitleOutputBuffer) this.a.pollFirst());
                    subtitleOutputBuffer.a(ceaInputBuffer.e, g, LocationRequestCompat.PASSIVE_INTERVAL);
                } else {
                    a(ceaInputBuffer);
                }
            }
            a(ceaInputBuffer);
            return subtitleOutputBuffer;
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract boolean f();

    /* access modifiers changed from: protected */
    public abstract Subtitle g();

    /* renamed from: h */
    public SubtitleInputBuffer a() {
        Assertions.b(this.d == null);
        if (this.b.isEmpty()) {
            return null;
        }
        CeaInputBuffer ceaInputBuffer = (CeaInputBuffer) this.b.pollFirst();
        this.d = ceaInputBuffer;
        return ceaInputBuffer;
    }

    /* access modifiers changed from: protected */
    public final long j() {
        return this.e;
    }
}
