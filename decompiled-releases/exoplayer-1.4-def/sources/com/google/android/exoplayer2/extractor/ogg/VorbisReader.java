package com.google.android.exoplayer2.extractor.ogg;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.VorbisUtil;
import com.google.android.exoplayer2.extractor.ogg.StreamReader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.ArrayList;
import java.util.Arrays;

final class VorbisReader extends StreamReader {
    private VorbisSetup j;
    private int k;
    private boolean l;
    private VorbisUtil.VorbisIdHeader m;
    private VorbisUtil.CommentHeader n;

    final class VorbisSetup {
        public final VorbisUtil.VorbisIdHeader a;
        public final byte[] b;
        public final VorbisUtil.Mode[] c;
        public final int d;

        public VorbisSetup(VorbisUtil.VorbisIdHeader vorbisIdHeader, byte[] bArr, VorbisUtil.Mode[] modeArr, int i) {
            this.a = vorbisIdHeader;
            this.b = bArr;
            this.c = modeArr;
            this.d = i;
        }
    }

    VorbisReader() {
    }

    public static boolean a(ParsableByteArray parsableByteArray) {
        try {
            return VorbisUtil.a(1, parsableByteArray, true);
        } catch (ParserException e) {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public final void a(boolean z) {
        super.a(z);
        if (z) {
            this.j = null;
            this.m = null;
            this.n = null;
        }
        this.k = 0;
        this.l = false;
    }

    /* access modifiers changed from: protected */
    public final boolean a(ParsableByteArray parsableByteArray, long j2, StreamReader.SetupData setupData) {
        if (this.j != null) {
            Assertions.b((Object) setupData.a);
            return false;
        }
        VorbisUtil.VorbisIdHeader vorbisIdHeader = this.m;
        VorbisSetup vorbisSetup = null;
        if (vorbisIdHeader == null) {
            this.m = VorbisUtil.a(parsableByteArray);
        } else if (this.n == null) {
            this.n = VorbisUtil.b(parsableByteArray);
        } else {
            byte[] bArr = new byte[parsableByteArray.c];
            System.arraycopy(parsableByteArray.a, 0, bArr, 0, parsableByteArray.c);
            VorbisUtil.Mode[] a = VorbisUtil.a(parsableByteArray, vorbisIdHeader.a);
            vorbisSetup = new VorbisSetup(vorbisIdHeader, bArr, a, VorbisUtil.a(a.length - 1));
        }
        this.j = vorbisSetup;
        if (vorbisSetup == null) {
            return true;
        }
        VorbisUtil.VorbisIdHeader vorbisIdHeader2 = vorbisSetup.a;
        ArrayList arrayList = new ArrayList();
        arrayList.add(vorbisIdHeader2.g);
        arrayList.add(vorbisSetup.b);
        Format.Builder builder = new Format.Builder();
        builder.k = "audio/vorbis";
        builder.f = vorbisIdHeader2.d;
        builder.g = vorbisIdHeader2.c;
        builder.x = vorbisIdHeader2.a;
        builder.y = vorbisIdHeader2.b;
        builder.m = arrayList;
        setupData.a = builder.a();
        return true;
    }

    /* access modifiers changed from: protected */
    public final long b(ParsableByteArray parsableByteArray) {
        int i = 0;
        if ((parsableByteArray.a[0] & 1) == 1) {
            return -1;
        }
        byte b = parsableByteArray.a[0];
        VorbisSetup vorbisSetup = (VorbisSetup) Assertions.a((Object) this.j);
        int i2 = !vorbisSetup.c[(b >> 1) & (255 >>> (8 - vorbisSetup.d))].a ? vorbisSetup.a.e : vorbisSetup.a.f;
        if (this.l) {
            i = (this.k + i2) / 4;
        }
        long j2 = (long) i;
        if (parsableByteArray.a.length < parsableByteArray.c + 4) {
            byte[] copyOf = Arrays.copyOf(parsableByteArray.a, parsableByteArray.c + 4);
            parsableByteArray.a(copyOf, copyOf.length);
        } else {
            parsableByteArray.c(parsableByteArray.c + 4);
        }
        byte[] bArr = parsableByteArray.a;
        bArr[parsableByteArray.c - 4] = (byte) ((int) (j2 & 255));
        bArr[parsableByteArray.c - 3] = (byte) ((int) ((j2 >>> 8) & 255));
        bArr[parsableByteArray.c - 2] = (byte) ((int) ((j2 >>> 16) & 255));
        bArr[parsableByteArray.c - 1] = (byte) ((int) ((j2 >>> 24) & 255));
        this.l = true;
        this.k = i2;
        return j2;
    }

    /* access modifiers changed from: protected */
    public final void c(long j2) {
        super.c(j2);
        int i = 0;
        this.l = j2 != 0;
        VorbisUtil.VorbisIdHeader vorbisIdHeader = this.m;
        if (vorbisIdHeader != null) {
            i = vorbisIdHeader.e;
        }
        this.k = i;
    }
}
