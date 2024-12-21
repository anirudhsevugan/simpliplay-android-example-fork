package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.decoder.CryptoInfo;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.source.SampleQueue;
import com.google.android.exoplayer2.upstream.Allocation;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class SampleDataQueue {
    final int a = 65536;
    AllocationNode b;
    AllocationNode c;
    AllocationNode d;
    long e;
    private final Allocator f;
    private final ParsableByteArray g = new ParsableByteArray(32);

    final class AllocationNode {
        public final long a;
        public final long b;
        public boolean c;
        public Allocation d;
        public AllocationNode e;

        public AllocationNode(long j, int i) {
            this.a = j;
            this.b = j + ((long) i);
        }

        public final AllocationNode a() {
            this.d = null;
            AllocationNode allocationNode = this.e;
            this.e = null;
            return allocationNode;
        }
    }

    public SampleDataQueue(Allocator allocator) {
        this.f = allocator;
        AllocationNode allocationNode = new AllocationNode(0, 65536);
        this.b = allocationNode;
        this.c = allocationNode;
        this.d = allocationNode;
    }

    private static AllocationNode a(AllocationNode allocationNode, long j) {
        while (j >= allocationNode.b) {
            allocationNode = allocationNode.e;
        }
        return allocationNode;
    }

    private static AllocationNode a(AllocationNode allocationNode, long j, ByteBuffer byteBuffer, int i) {
        AllocationNode a2 = a(allocationNode, j);
        while (i > 0) {
            int min = Math.min(i, (int) (a2.b - j));
            byteBuffer.put(a2.d.a, (int) (j - a2.a), min);
            i -= min;
            j += (long) min;
            if (j == a2.b) {
                a2 = a2.e;
            }
        }
        return a2;
    }

    private static AllocationNode a(AllocationNode allocationNode, long j, byte[] bArr, int i) {
        AllocationNode a2 = a(allocationNode, j);
        int i2 = i;
        while (i2 > 0) {
            int min = Math.min(i2, (int) (a2.b - j));
            System.arraycopy(a2.d.a, (int) (j - a2.a), bArr, i - i2, min);
            i2 -= min;
            j += (long) min;
            if (j == a2.b) {
                a2 = a2.e;
            }
        }
        return a2;
    }

    private static AllocationNode a(AllocationNode allocationNode, DecoderInputBuffer decoderInputBuffer, SampleQueue.SampleExtrasHolder sampleExtrasHolder, ParsableByteArray parsableByteArray) {
        ByteBuffer byteBuffer;
        long j;
        if (decoderInputBuffer.g()) {
            allocationNode = b(allocationNode, decoderInputBuffer, sampleExtrasHolder, parsableByteArray);
        }
        if (decoderInputBuffer.e()) {
            parsableByteArray.a(4);
            AllocationNode a2 = a(allocationNode, sampleExtrasHolder.b, parsableByteArray.a, 4);
            int o = parsableByteArray.o();
            sampleExtrasHolder.b += 4;
            sampleExtrasHolder.a -= 4;
            decoderInputBuffer.d(o);
            allocationNode = a(a2, sampleExtrasHolder.b, decoderInputBuffer.c, o);
            sampleExtrasHolder.b += (long) o;
            sampleExtrasHolder.a -= o;
            decoderInputBuffer.c(sampleExtrasHolder.a);
            j = sampleExtrasHolder.b;
            byteBuffer = decoderInputBuffer.f;
        } else {
            decoderInputBuffer.d(sampleExtrasHolder.a);
            j = sampleExtrasHolder.b;
            byteBuffer = decoderInputBuffer.c;
        }
        return a(allocationNode, j, byteBuffer, sampleExtrasHolder.a);
    }

    private static AllocationNode b(AllocationNode allocationNode, DecoderInputBuffer decoderInputBuffer, SampleQueue.SampleExtrasHolder sampleExtrasHolder, ParsableByteArray parsableByteArray) {
        int i;
        SampleQueue.SampleExtrasHolder sampleExtrasHolder2 = sampleExtrasHolder;
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        long j = sampleExtrasHolder2.b;
        parsableByteArray2.a(1);
        AllocationNode a2 = a(allocationNode, j, parsableByteArray2.a, 1);
        long j2 = j + 1;
        byte b2 = parsableByteArray2.a[0];
        boolean z = (b2 & 128) != 0;
        byte b3 = b2 & Ev3Constants.Opcode.MEMORY_READ;
        CryptoInfo cryptoInfo = decoderInputBuffer.b;
        if (cryptoInfo.a == null) {
            cryptoInfo.a = new byte[16];
        } else {
            Arrays.fill(cryptoInfo.a, (byte) 0);
        }
        AllocationNode a3 = a(a2, j2, cryptoInfo.a, (int) b3);
        long j3 = j2 + ((long) b3);
        if (z) {
            parsableByteArray2.a(2);
            a3 = a(a3, j3, parsableByteArray2.a, 2);
            j3 += 2;
            i = parsableByteArray.d();
        } else {
            i = 1;
        }
        int[] iArr = cryptoInfo.b;
        if (iArr == null || iArr.length < i) {
            iArr = new int[i];
        }
        int[] iArr2 = iArr;
        int[] iArr3 = cryptoInfo.c;
        if (iArr3 == null || iArr3.length < i) {
            iArr3 = new int[i];
        }
        int[] iArr4 = iArr3;
        if (z) {
            int i2 = i * 6;
            parsableByteArray2.a(i2);
            a3 = a(a3, j3, parsableByteArray2.a, i2);
            j3 += (long) i2;
            parsableByteArray2.d(0);
            for (int i3 = 0; i3 < i; i3++) {
                iArr2[i3] = parsableByteArray.d();
                iArr4[i3] = parsableByteArray.o();
            }
        } else {
            iArr2[0] = 0;
            iArr4[0] = sampleExtrasHolder2.a - ((int) (j3 - sampleExtrasHolder2.b));
        }
        TrackOutput.CryptoData cryptoData = (TrackOutput.CryptoData) Util.a((Object) sampleExtrasHolder2.c);
        cryptoInfo.a(i, iArr2, iArr4, cryptoData.b, cryptoInfo.a, cryptoData.a, cryptoData.c, cryptoData.d);
        int i4 = (int) (j3 - sampleExtrasHolder2.b);
        sampleExtrasHolder2.b += (long) i4;
        sampleExtrasHolder2.a -= i4;
        return a3;
    }

    /* access modifiers changed from: package-private */
    public final int a(int i) {
        if (!this.d.c) {
            AllocationNode allocationNode = this.d;
            Allocation a2 = this.f.a();
            AllocationNode allocationNode2 = new AllocationNode(this.d.b, this.a);
            allocationNode.d = a2;
            allocationNode.e = allocationNode2;
            allocationNode.c = true;
        }
        return Math.min(i, (int) (this.d.b - this.e));
    }

    public final void a() {
        a(this.b);
        AllocationNode allocationNode = new AllocationNode(0, this.a);
        this.b = allocationNode;
        this.c = allocationNode;
        this.d = allocationNode;
        this.e = 0;
        this.f.b();
    }

    public final void a(long j) {
        if (j != -1) {
            while (j >= this.b.b) {
                this.f.a(this.b.d);
                this.b = this.b.a();
            }
            if (this.c.a < this.b.a) {
                this.c = this.b;
            }
        }
    }

    public final void a(DecoderInputBuffer decoderInputBuffer, SampleQueue.SampleExtrasHolder sampleExtrasHolder) {
        this.c = a(this.c, decoderInputBuffer, sampleExtrasHolder, this.g);
    }

    /* access modifiers changed from: package-private */
    public final void a(AllocationNode allocationNode) {
        if (allocationNode.c) {
            int i = (this.d.c ? 1 : 0) + (((int) (this.d.a - allocationNode.a)) / this.a);
            Allocation[] allocationArr = new Allocation[i];
            for (int i2 = 0; i2 < i; i2++) {
                allocationArr[i2] = allocationNode.d;
                allocationNode = allocationNode.a();
            }
            this.f.a(allocationArr);
        }
    }

    /* access modifiers changed from: package-private */
    public final void b(int i) {
        long j = this.e + ((long) i);
        this.e = j;
        if (j == this.d.b) {
            this.d = this.d.e;
        }
    }

    public final void b(DecoderInputBuffer decoderInputBuffer, SampleQueue.SampleExtrasHolder sampleExtrasHolder) {
        a(this.c, decoderInputBuffer, sampleExtrasHolder, this.g);
    }
}
