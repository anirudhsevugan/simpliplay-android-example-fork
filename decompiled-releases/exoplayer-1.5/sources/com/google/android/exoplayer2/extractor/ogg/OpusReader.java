package com.google.android.exoplayer2.extractor.ogg;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.audio.OpusUtil;
import com.google.android.exoplayer2.extractor.ogg.StreamReader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.util.Arrays;
import java.util.List;

final class OpusReader extends StreamReader {
    private static final byte[] j = {Ev3Constants.Opcode.CP_EQF, Ev3Constants.Opcode.JR_NEQ8, Ev3Constants.Opcode.JR_LTEQ16, Ev3Constants.Opcode.JR_NEQF, Ev3Constants.Opcode.CP_GT8, Ev3Constants.Opcode.JR_LT16, Ev3Constants.Opcode.PORT_CNV_OUTPUT, Ev3Constants.Opcode.JR_LT8};
    private boolean k;

    OpusReader() {
    }

    public static boolean a(ParsableByteArray parsableByteArray) {
        if (parsableByteArray.a() < 8) {
            return false;
        }
        byte[] bArr = new byte[8];
        parsableByteArray.a(bArr, 0, 8);
        return Arrays.equals(bArr, j);
    }

    /* access modifiers changed from: protected */
    public final void a(boolean z) {
        super.a(z);
        if (z) {
            this.k = false;
        }
    }

    /* access modifiers changed from: protected */
    public final boolean a(ParsableByteArray parsableByteArray, long j2, StreamReader.SetupData setupData) {
        boolean z = true;
        if (!this.k) {
            byte[] copyOf = Arrays.copyOf(parsableByteArray.a, parsableByteArray.c);
            int a = OpusUtil.a(copyOf);
            List b = OpusUtil.b(copyOf);
            Format.Builder builder = new Format.Builder();
            builder.k = "audio/opus";
            builder.x = a;
            builder.y = 48000;
            builder.m = b;
            setupData.a = builder.a();
            this.k = true;
            return true;
        }
        Assertions.b((Object) setupData.a);
        if (parsableByteArray.j() != 1332770163) {
            z = false;
        }
        parsableByteArray.d(0);
        return z;
    }

    /* access modifiers changed from: protected */
    public final long b(ParsableByteArray parsableByteArray) {
        byte b;
        byte[] bArr = parsableByteArray.a;
        byte b2 = bArr[0] & Ev3Constants.Opcode.TST;
        switch (b2 & 3) {
            case 0:
                b = 1;
                break;
            case 1:
            case 2:
                b = 2;
                break;
            default:
                b = bArr[1] & Ev3Constants.Opcode.MOVEF_F;
                break;
        }
        int i = b2 >> 3;
        int i2 = i & 3;
        return b(((long) b) * ((long) (i >= 16 ? 2500 << i2 : i >= 12 ? 10000 << (i2 & 1) : i2 == 3 ? 60000 : 10000 << i2)));
    }
}
