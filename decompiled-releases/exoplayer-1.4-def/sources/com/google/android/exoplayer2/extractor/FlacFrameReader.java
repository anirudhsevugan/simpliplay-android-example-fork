package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import com.google.appinventor.components.runtime.util.FullScreenVideoUtil;

public final class FlacFrameReader {

    public final class SampleNumberHolder {
        public long a;
    }

    public static int a(ParsableByteArray parsableByteArray, int i) {
        switch (i) {
            case 1:
                return FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PAUSE;
            case 2:
            case 3:
            case 4:
            case 5:
                return 576 << (i - 2);
            case 6:
                return parsableByteArray.c() + 1;
            case 7:
                return parsableByteArray.d() + 1;
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                return 256 << (i - 8);
            default:
                return -1;
        }
    }

    public static long a(ExtractorInput extractorInput, FlacStreamMetadata flacStreamMetadata) {
        extractorInput.a();
        boolean z = true;
        extractorInput.c(1);
        byte[] bArr = new byte[1];
        extractorInput.d(bArr, 0, 1);
        if ((bArr[0] & 1) != 1) {
            z = false;
        }
        extractorInput.c(2);
        int i = z ? 7 : 6;
        ParsableByteArray parsableByteArray = new ParsableByteArray(i);
        parsableByteArray.c(ExtractorUtil.a(extractorInput, parsableByteArray.a, 0, i));
        extractorInput.a();
        SampleNumberHolder sampleNumberHolder = new SampleNumberHolder();
        if (a(parsableByteArray, flacStreamMetadata, z, sampleNumberHolder)) {
            return sampleNumberHolder.a;
        }
        throw new ParserException();
    }

    public static boolean a(ExtractorInput extractorInput, FlacStreamMetadata flacStreamMetadata, int i, SampleNumberHolder sampleNumberHolder) {
        long b = extractorInput.b();
        byte[] bArr = new byte[2];
        extractorInput.d(bArr, 0, 2);
        if ((((bArr[0] & Ev3Constants.Opcode.TST) << 8) | (bArr[1] & Ev3Constants.Opcode.TST)) != i) {
            extractorInput.a();
            extractorInput.c((int) (b - extractorInput.c()));
            return false;
        }
        ParsableByteArray parsableByteArray = new ParsableByteArray(16);
        System.arraycopy(bArr, 0, parsableByteArray.a, 0, 2);
        parsableByteArray.c(ExtractorUtil.a(extractorInput, parsableByteArray.a, 2, 14));
        extractorInput.a();
        extractorInput.c((int) (b - extractorInput.c()));
        return a(parsableByteArray, flacStreamMetadata, i, sampleNumberHolder);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:44:0x008f, code lost:
        if (r7 == r1.f) goto L_0x0087;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x009e, code lost:
        if ((r17.c() * 1000) == r3) goto L_0x0087;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00ad, code lost:
        if (r4 == r3) goto L_0x0087;
     */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00b2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(com.google.android.exoplayer2.util.ParsableByteArray r17, com.google.android.exoplayer2.extractor.FlacStreamMetadata r18, int r19, com.google.android.exoplayer2.extractor.FlacFrameReader.SampleNumberHolder r20) {
        /*
            r0 = r17
            r1 = r18
            int r2 = r0.b
            long r3 = r17.h()
            r5 = 16
            long r5 = r3 >>> r5
            r7 = r19
            long r7 = (long) r7
            r9 = 0
            int r10 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r10 == 0) goto L_0x0017
            return r9
        L_0x0017:
            r7 = 1
            long r5 = r5 & r7
            r10 = 1
            int r11 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r11 != 0) goto L_0x0021
            r5 = 1
            goto L_0x0022
        L_0x0021:
            r5 = 0
        L_0x0022:
            r6 = 12
            long r11 = r3 >> r6
            r13 = 15
            long r11 = r11 & r13
            int r12 = (int) r11
            r11 = 8
            long r15 = r3 >> r11
            long r6 = r15 & r13
            int r7 = (int) r6
            r6 = 4
            long r15 = r3 >> r6
            long r13 = r13 & r15
            int r6 = (int) r13
            long r13 = r3 >> r10
            r15 = 7
            long r13 = r13 & r15
            int r8 = (int) r13
            r13 = 1
            long r3 = r3 & r13
            int r11 = (r3 > r13 ? 1 : (r3 == r13 ? 0 : -1))
            if (r11 != 0) goto L_0x0045
            r3 = 1
            goto L_0x0046
        L_0x0045:
            r3 = 0
        L_0x0046:
            r4 = 7
            if (r6 > r4) goto L_0x0050
            int r4 = r1.g
            int r4 = r4 - r10
            if (r6 != r4) goto L_0x005a
        L_0x004e:
            r4 = 1
            goto L_0x005b
        L_0x0050:
            r4 = 10
            if (r6 > r4) goto L_0x005a
            int r4 = r1.g
            r6 = 2
            if (r4 != r6) goto L_0x005a
            goto L_0x004e
        L_0x005a:
            r4 = 0
        L_0x005b:
            if (r4 == 0) goto L_0x00c7
            if (r8 != 0) goto L_0x0061
        L_0x005f:
            r4 = 1
            goto L_0x0067
        L_0x0061:
            int r4 = r1.i
            if (r8 != r4) goto L_0x0066
            goto L_0x005f
        L_0x0066:
            r4 = 0
        L_0x0067:
            if (r4 == 0) goto L_0x00c7
            if (r3 != 0) goto L_0x00c7
            r3 = r20
            boolean r3 = a((com.google.android.exoplayer2.util.ParsableByteArray) r0, (com.google.android.exoplayer2.extractor.FlacStreamMetadata) r1, (boolean) r5, (com.google.android.exoplayer2.extractor.FlacFrameReader.SampleNumberHolder) r3)
            if (r3 == 0) goto L_0x00c7
            int r3 = a((com.google.android.exoplayer2.util.ParsableByteArray) r0, (int) r12)
            r4 = -1
            if (r3 == r4) goto L_0x0080
            int r4 = r1.b
            if (r3 > r4) goto L_0x0080
            r3 = 1
            goto L_0x0081
        L_0x0080:
            r3 = 0
        L_0x0081:
            if (r3 == 0) goto L_0x00c7
            int r3 = r1.e
            if (r7 != 0) goto L_0x0089
        L_0x0087:
            r1 = 1
            goto L_0x00b0
        L_0x0089:
            r4 = 11
            if (r7 > r4) goto L_0x0094
            int r1 = r1.f
            if (r7 != r1) goto L_0x0092
            goto L_0x0087
        L_0x0092:
            r1 = 0
            goto L_0x00b0
        L_0x0094:
            r1 = 12
            if (r7 != r1) goto L_0x00a1
            int r1 = r17.c()
            int r1 = r1 * 1000
            if (r1 != r3) goto L_0x0092
            goto L_0x0087
        L_0x00a1:
            r1 = 14
            if (r7 > r1) goto L_0x0092
            int r4 = r17.d()
            if (r7 != r1) goto L_0x00ad
            int r4 = r4 * 10
        L_0x00ad:
            if (r4 != r3) goto L_0x0092
            goto L_0x0087
        L_0x00b0:
            if (r1 == 0) goto L_0x00c7
            int r1 = r17.c()
            int r3 = r0.b
            byte[] r0 = r0.a
            int r3 = r3 - r10
            int r0 = com.google.android.exoplayer2.util.Util.a((byte[]) r0, (int) r2, (int) r3, (int) r9)
            if (r1 != r0) goto L_0x00c3
            r0 = 1
            goto L_0x00c4
        L_0x00c3:
            r0 = 0
        L_0x00c4:
            if (r0 == 0) goto L_0x00c7
            return r10
        L_0x00c7:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.FlacFrameReader.a(com.google.android.exoplayer2.util.ParsableByteArray, com.google.android.exoplayer2.extractor.FlacStreamMetadata, int, com.google.android.exoplayer2.extractor.FlacFrameReader$SampleNumberHolder):boolean");
    }

    private static boolean a(ParsableByteArray parsableByteArray, FlacStreamMetadata flacStreamMetadata, boolean z, SampleNumberHolder sampleNumberHolder) {
        try {
            long t = parsableByteArray.t();
            if (!z) {
                t *= (long) flacStreamMetadata.b;
            }
            sampleNumberHolder.a = t;
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
