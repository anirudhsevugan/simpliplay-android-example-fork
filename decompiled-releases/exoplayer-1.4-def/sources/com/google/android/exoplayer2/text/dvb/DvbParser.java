package com.google.android.exoplayer2.text.dvb;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.SparseArray;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.Util;
import com.google.appinventor.components.runtime.util.Ev3Constants;

final class DvbParser {
    private static final byte[] b = {0, 7, 8, 15};
    private static final byte[] c = {0, Ev3Constants.Opcode.JR_LTEQF, Ev3Constants.Opcode.BP0, -1};
    private static final byte[] d = {0, 17, Ev3Constants.Opcode.OR32, Ev3Constants.Opcode.MOVE8_F, Ev3Constants.Opcode.CP_LT8, Ev3Constants.Opcode.CP_LTEQ16, Ev3Constants.Opcode.JR_LT32, Ev3Constants.Opcode.JR_LTEQF, Ev3Constants.Opcode.BP0, -103, Ev3Constants.Opcode.OUTPUT_READY, -69, Ev3Constants.Opcode.WRITE8, Ev3Constants.Opcode.MAILBOX_CLOSE, -18, -1};
    final SubtitleService a;
    private final Paint e;
    private final Paint f;
    private final Canvas g = new Canvas();
    private final DisplayDefinition h = new DisplayDefinition(719, 575, 0, 719, 0, 575);
    private final ClutDefinition i = new ClutDefinition(0, a(), b(), c());
    private Bitmap j;

    final class ClutDefinition {
        public final int a;
        public final int[] b;
        public final int[] c;
        public final int[] d;

        public ClutDefinition(int i, int[] iArr, int[] iArr2, int[] iArr3) {
            this.a = i;
            this.b = iArr;
            this.c = iArr2;
            this.d = iArr3;
        }
    }

    final class DisplayDefinition {
        public final int a;
        public final int b;
        public final int c;
        public final int d;
        public final int e;
        public final int f;

        public DisplayDefinition(int i, int i2, int i3, int i4, int i5, int i6) {
            this.a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
            this.e = i5;
            this.f = i6;
        }
    }

    final class ObjectData {
        public final int a;
        public final boolean b;
        public final byte[] c;
        public final byte[] d;

        public ObjectData(int i, boolean z, byte[] bArr, byte[] bArr2) {
            this.a = i;
            this.b = z;
            this.c = bArr;
            this.d = bArr2;
        }
    }

    final class PageComposition {
        public final int a;
        public final int b;
        public final SparseArray c;

        public PageComposition(int i, int i2, SparseArray sparseArray) {
            this.a = i;
            this.b = i2;
            this.c = sparseArray;
        }
    }

    final class PageRegion {
        public final int a;
        public final int b;

        public PageRegion(int i, int i2) {
            this.a = i;
            this.b = i2;
        }
    }

    final class RegionComposition {
        public final int a;
        public final boolean b;
        public final int c;
        public final int d;
        public final int e;
        public final int f;
        public final int g;
        public final int h;
        public final int i;
        public final SparseArray j;

        public RegionComposition(int i2, boolean z, int i3, int i4, int i5, int i6, int i7, int i8, int i9, SparseArray sparseArray) {
            this.a = i2;
            this.b = z;
            this.c = i3;
            this.d = i4;
            this.e = i5;
            this.f = i6;
            this.g = i7;
            this.h = i8;
            this.i = i9;
            this.j = sparseArray;
        }

        public final void a(RegionComposition regionComposition) {
            SparseArray sparseArray = regionComposition.j;
            for (int i2 = 0; i2 < sparseArray.size(); i2++) {
                this.j.put(sparseArray.keyAt(i2), (RegionObject) sparseArray.valueAt(i2));
            }
        }
    }

    final class RegionObject {
        public final int a;
        public final int b;

        public RegionObject(int i, int i2) {
            this.a = i;
            this.b = i2;
        }
    }

    final class SubtitleService {
        public final int a;
        public final int b;
        public final SparseArray c = new SparseArray();
        public final SparseArray d = new SparseArray();
        public final SparseArray e = new SparseArray();
        public final SparseArray f = new SparseArray();
        public final SparseArray g = new SparseArray();
        public DisplayDefinition h;
        public PageComposition i;

        public SubtitleService(int i2, int i3) {
            this.a = i2;
            this.b = i3;
        }
    }

    public DvbParser(int i2, int i3) {
        Paint paint = new Paint();
        this.e = paint;
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        paint.setPathEffect((PathEffect) null);
        Paint paint2 = new Paint();
        this.f = paint2;
        paint2.setStyle(Paint.Style.FILL);
        paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
        paint2.setPathEffect((PathEffect) null);
        this.a = new SubtitleService(i2, i3);
    }

    private static int a(int i2, int i3, int i4, int i5) {
        return (i2 << 24) | (i3 << 16) | (i4 << 8) | i5;
    }

    private static ObjectData a(ParsableBitArray parsableBitArray) {
        int c2 = parsableBitArray.c(16);
        parsableBitArray.b(4);
        int c3 = parsableBitArray.c(2);
        boolean e2 = parsableBitArray.e();
        parsableBitArray.b(1);
        byte[] bArr = Util.f;
        byte[] bArr2 = Util.f;
        if (c3 == 1) {
            parsableBitArray.b(parsableBitArray.c(8) << 4);
        } else if (c3 == 0) {
            int c4 = parsableBitArray.c(16);
            int c5 = parsableBitArray.c(16);
            if (c4 > 0) {
                bArr = new byte[c4];
                parsableBitArray.c(bArr, c4);
            }
            if (c5 > 0) {
                bArr2 = new byte[c5];
                parsableBitArray.c(bArr2, c5);
            } else {
                bArr2 = bArr;
            }
        }
        return new ObjectData(c2, e2, bArr, bArr2);
    }

    private static RegionComposition a(ParsableBitArray parsableBitArray, int i2) {
        ParsableBitArray parsableBitArray2 = parsableBitArray;
        int c2 = parsableBitArray2.c(8);
        int i3 = 4;
        parsableBitArray2.b(4);
        boolean e2 = parsableBitArray.e();
        parsableBitArray2.b(3);
        int i4 = 16;
        int c3 = parsableBitArray2.c(16);
        int c4 = parsableBitArray2.c(16);
        parsableBitArray2.c(3);
        int c5 = parsableBitArray2.c(3);
        int i5 = 2;
        parsableBitArray2.b(2);
        int c6 = parsableBitArray2.c(8);
        int c7 = parsableBitArray2.c(8);
        int c8 = parsableBitArray2.c(4);
        int c9 = parsableBitArray2.c(2);
        parsableBitArray2.b(2);
        int i6 = i2 - 10;
        SparseArray sparseArray = new SparseArray();
        while (i6 > 0) {
            int c10 = parsableBitArray2.c(i4);
            int c11 = parsableBitArray2.c(i5);
            parsableBitArray2.c(i5);
            int i7 = c9;
            int c12 = parsableBitArray2.c(12);
            parsableBitArray2.b(i3);
            int c13 = parsableBitArray2.c(12);
            i6 -= 6;
            if (c11 != 1) {
                if (c11 != 2) {
                    sparseArray.put(c10, new RegionObject(c12, c13));
                    c9 = i7;
                    i3 = 4;
                    i5 = 2;
                    i4 = 16;
                }
            }
            parsableBitArray2.c(8);
            parsableBitArray2.c(8);
            i6 -= 2;
            sparseArray.put(c10, new RegionObject(c12, c13));
            c9 = i7;
            i3 = 4;
            i5 = 2;
            i4 = 16;
        }
        return new RegionComposition(c2, e2, c3, c4, c5, c6, c7, c8, c9, sparseArray);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v28, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v29, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v33, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v36, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v37, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v42, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v44, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v48, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v50, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v51, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v64, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v65, resolved type: byte} */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0186, code lost:
        r20 = 0;
     */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=int, for r2v31, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=int, for r2v6, types: [byte] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x01e0 A[LOOP:3: B:78:0x014f->B:106:0x01e0, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x012a A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x01da A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0101 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0130 A[LOOP:2: B:34:0x00a2->B:65:0x0130, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0155  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x015b  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x01af A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(byte[] r24, int[] r25, int r26, int r27, int r28, android.graphics.Paint r29, android.graphics.Canvas r30) {
        /*
            r0 = r26
            r7 = r29
            com.google.android.exoplayer2.util.ParsableBitArray r8 = new com.google.android.exoplayer2.util.ParsableBitArray
            r1 = r24
            r8.<init>(r1)
            r1 = r27
            r10 = r28
            r11 = 0
            r12 = 0
            r13 = 0
        L_0x0012:
            int r2 = r8.a()
            if (r2 == 0) goto L_0x01e4
            r14 = 8
            int r2 = r8.c(r14)
            r15 = 3
            r6 = 4
            r16 = 1
            r17 = 0
            r5 = 2
            switch(r2) {
                case 16: goto L_0x0136;
                case 17: goto L_0x0093;
                case 18: goto L_0x003f;
                case 32: goto L_0x003a;
                case 33: goto L_0x0035;
                case 34: goto L_0x002e;
                case 240: goto L_0x0029;
                default: goto L_0x0028;
            }
        L_0x0028:
            goto L_0x0012
        L_0x0029:
            int r10 = r10 + 2
            r1 = r27
            goto L_0x0012
        L_0x002e:
            r2 = 16
            byte[] r12 = a(r2, r14, r8)
            goto L_0x0012
        L_0x0035:
            byte[] r11 = a(r6, r14, r8)
            goto L_0x0012
        L_0x003a:
            byte[] r13 = a(r6, r6, r8)
            goto L_0x0012
        L_0x003f:
            r15 = r1
            r1 = 0
        L_0x0041:
            int r2 = r8.c(r14)
            if (r2 == 0) goto L_0x004c
            r18 = r1
            r19 = 1
            goto L_0x0072
        L_0x004c:
            boolean r2 = r8.e()
            r3 = 7
            if (r2 != 0) goto L_0x0065
            int r2 = r8.c(r3)
            if (r2 == 0) goto L_0x005f
            r18 = r1
            r19 = r2
            r2 = 0
            goto L_0x0072
        L_0x005f:
            r2 = 0
            r18 = 1
            r19 = 0
            goto L_0x0072
        L_0x0065:
            int r2 = r8.c(r3)
            int r3 = r8.c(r14)
            r18 = r1
            r19 = r2
            r2 = r3
        L_0x0072:
            if (r19 == 0) goto L_0x008a
            if (r7 == 0) goto L_0x008a
            r1 = r25[r2]
            r7.setColor(r1)
            float r2 = (float) r15
            float r3 = (float) r10
            int r1 = r15 + r19
            float r4 = (float) r1
            int r1 = r10 + 1
            float r5 = (float) r1
            r1 = r30
            r6 = r29
            r1.drawRect(r2, r3, r4, r5, r6)
        L_0x008a:
            int r15 = r15 + r19
            if (r18 == 0) goto L_0x0090
            r1 = r15
            goto L_0x0012
        L_0x0090:
            r1 = r18
            goto L_0x0041
        L_0x0093:
            if (r0 != r15) goto L_0x009e
            if (r12 != 0) goto L_0x009a
            byte[] r2 = d
            goto L_0x009b
        L_0x009a:
            r2 = r12
        L_0x009b:
            r18 = r2
            goto L_0x00a0
        L_0x009e:
            r18 = 0
        L_0x00a0:
            r4 = r1
            r1 = 0
        L_0x00a2:
            int r2 = r8.c(r6)
            if (r2 == 0) goto L_0x00ad
            r19 = r1
        L_0x00aa:
            r20 = 1
            goto L_0x00ff
        L_0x00ad:
            boolean r2 = r8.e()
            if (r2 != 0) goto L_0x00c5
            int r2 = r8.c(r15)
            if (r2 == 0) goto L_0x00c1
            int r2 = r2 + 2
            r19 = r1
            r20 = r2
            r2 = 0
            goto L_0x00ff
        L_0x00c1:
            r2 = 0
            r19 = 1
            goto L_0x00e4
        L_0x00c5:
            boolean r2 = r8.e()
            if (r2 != 0) goto L_0x00da
            int r2 = r8.c(r5)
            int r2 = r2 + r6
        L_0x00d0:
            int r3 = r8.c(r6)
            r19 = r1
            r20 = r2
            r2 = r3
            goto L_0x00ff
        L_0x00da:
            int r2 = r8.c(r5)
            switch(r2) {
                case 0: goto L_0x00fb;
                case 1: goto L_0x00f5;
                case 2: goto L_0x00ee;
                case 3: goto L_0x00e7;
                default: goto L_0x00e1;
            }
        L_0x00e1:
            r19 = r1
            r2 = 0
        L_0x00e4:
            r20 = 0
            goto L_0x00ff
        L_0x00e7:
            int r2 = r8.c(r14)
            int r2 = r2 + 25
            goto L_0x00d0
        L_0x00ee:
            int r2 = r8.c(r6)
            int r2 = r2 + 9
            goto L_0x00d0
        L_0x00f5:
            r19 = r1
            r2 = 0
            r20 = 2
            goto L_0x00ff
        L_0x00fb:
            r19 = r1
            r2 = 0
            goto L_0x00aa
        L_0x00ff:
            if (r20 == 0) goto L_0x0123
            if (r7 == 0) goto L_0x0123
            if (r18 == 0) goto L_0x0107
            byte r2 = r18[r2]
        L_0x0107:
            r1 = r25[r2]
            r7.setColor(r1)
            float r2 = (float) r4
            float r3 = (float) r10
            int r1 = r4 + r20
            float r1 = (float) r1
            int r5 = r10 + 1
            float r5 = (float) r5
            r21 = r1
            r1 = r30
            r22 = r4
            r4 = r21
            r9 = 2
            r6 = r29
            r1.drawRect(r2, r3, r4, r5, r6)
            goto L_0x0126
        L_0x0123:
            r22 = r4
            r9 = 2
        L_0x0126:
            int r4 = r22 + r20
            if (r19 == 0) goto L_0x0130
            r8.f()
            r1 = r4
            goto L_0x0012
        L_0x0130:
            r1 = r19
            r5 = 2
            r6 = 4
            goto L_0x00a2
        L_0x0136:
            r9 = 2
            if (r0 != r15) goto L_0x0142
            if (r11 != 0) goto L_0x013e
            byte[] r2 = c
            goto L_0x013f
        L_0x013e:
            r2 = r11
        L_0x013f:
            r18 = r2
            goto L_0x014d
        L_0x0142:
            if (r0 != r9) goto L_0x014b
            if (r13 != 0) goto L_0x0149
            byte[] r2 = b
            goto L_0x013f
        L_0x0149:
            r2 = r13
            goto L_0x013f
        L_0x014b:
            r18 = 0
        L_0x014d:
            r6 = r1
            r1 = 0
        L_0x014f:
            int r2 = r8.c(r9)
            if (r2 == 0) goto L_0x015b
            r19 = r1
        L_0x0157:
            r5 = 4
            r20 = 1
            goto L_0x01ad
        L_0x015b:
            boolean r2 = r8.e()
            if (r2 == 0) goto L_0x0171
            int r2 = r8.c(r15)
            int r5 = r2 + 3
        L_0x0167:
            int r2 = r8.c(r9)
            r19 = r1
            r20 = r5
            r5 = 4
            goto L_0x01ad
        L_0x0171:
            boolean r2 = r8.e()
            if (r2 == 0) goto L_0x017b
            r19 = r1
            r2 = 0
            goto L_0x0157
        L_0x017b:
            int r2 = r8.c(r9)
            switch(r2) {
                case 0: goto L_0x01a8;
                case 1: goto L_0x01a1;
                case 2: goto L_0x0190;
                case 3: goto L_0x0189;
                default: goto L_0x0182;
            }
        L_0x0182:
            r5 = 4
            r19 = r1
            r2 = 0
        L_0x0186:
            r20 = 0
            goto L_0x01ad
        L_0x0189:
            int r2 = r8.c(r14)
            int r5 = r2 + 29
            goto L_0x0167
        L_0x0190:
            r5 = 4
            int r2 = r8.c(r5)
            int r2 = r2 + 12
            int r3 = r8.c(r9)
            r19 = r1
            r20 = r2
            r2 = r3
            goto L_0x01ad
        L_0x01a1:
            r5 = 4
            r19 = r1
            r2 = 0
            r20 = 2
            goto L_0x01ad
        L_0x01a8:
            r5 = 4
            r2 = 0
            r19 = 1
            goto L_0x0186
        L_0x01ad:
            if (r20 == 0) goto L_0x01d2
            if (r7 == 0) goto L_0x01d2
            if (r18 == 0) goto L_0x01b5
            byte r2 = r18[r2]
        L_0x01b5:
            r1 = r25[r2]
            r7.setColor(r1)
            float r2 = (float) r6
            float r3 = (float) r10
            int r1 = r6 + r20
            float r4 = (float) r1
            int r1 = r10 + 1
            float r1 = (float) r1
            r22 = r1
            r1 = r30
            r23 = 4
            r5 = r22
            r22 = r6
            r6 = r29
            r1.drawRect(r2, r3, r4, r5, r6)
            goto L_0x01d6
        L_0x01d2:
            r22 = r6
            r23 = 4
        L_0x01d6:
            int r6 = r22 + r20
            if (r19 == 0) goto L_0x01e0
            r8.f()
            r1 = r6
            goto L_0x0012
        L_0x01e0:
            r1 = r19
            goto L_0x014f
        L_0x01e4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.dvb.DvbParser.a(byte[], int[], int, int, int, android.graphics.Paint, android.graphics.Canvas):void");
    }

    private static byte[] a(int i2, int i3, ParsableBitArray parsableBitArray) {
        byte[] bArr = new byte[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            bArr[i4] = (byte) parsableBitArray.c(i3);
        }
        return bArr;
    }

    private static int[] a() {
        return new int[]{0, -1, -16777216, -8421505};
    }

    private static ClutDefinition b(ParsableBitArray parsableBitArray, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        ParsableBitArray parsableBitArray2 = parsableBitArray;
        int i8 = 8;
        int c2 = parsableBitArray2.c(8);
        parsableBitArray2.b(8);
        int i9 = i2 - 2;
        int[] a2 = a();
        int[] b2 = b();
        int[] c3 = c();
        while (i9 > 0) {
            int c4 = parsableBitArray2.c(i8);
            int c5 = parsableBitArray2.c(i8);
            int i10 = i9 - 2;
            int[] iArr = (c5 & 128) != 0 ? a2 : (c5 & 64) != 0 ? b2 : c3;
            if ((c5 & 1) != 0) {
                i6 = parsableBitArray2.c(i8);
                i5 = parsableBitArray2.c(i8);
                i4 = parsableBitArray2.c(i8);
                i3 = parsableBitArray2.c(i8);
                i7 = i10 - 4;
            } else {
                int c6 = parsableBitArray2.c(2) << 6;
                i7 = i10 - 2;
                i4 = parsableBitArray2.c(4) << 4;
                i3 = c6;
                i6 = parsableBitArray2.c(6) << 2;
                i5 = parsableBitArray2.c(4) << 4;
            }
            if (i6 == 0) {
                i5 = 0;
                i4 = 0;
                i3 = 255;
            }
            double d2 = (double) i6;
            double d3 = (double) (i5 - 128);
            Double.isNaN(d3);
            Double.isNaN(d2);
            int i11 = i7;
            double d4 = (double) (i4 - 128);
            Double.isNaN(d4);
            Double.isNaN(d2);
            Double.isNaN(d3);
            Double.isNaN(d4);
            Double.isNaN(d2);
            iArr[c4] = a((byte) (255 - (i3 & 255)), Util.a((int) (d2 + (1.402d * d3)), 0, 255), Util.a((int) ((d2 - (0.34414d * d4)) - (d3 * 0.71414d)), 0, 255), Util.a((int) (d2 + (d4 * 1.772d)), 0, 255));
            a2 = a2;
            c2 = c2;
            i9 = i11;
            i8 = 8;
        }
        return new ClutDefinition(c2, a2, b2, c3);
    }

    private static int[] b() {
        int[] iArr = new int[16];
        iArr[0] = 0;
        for (int i2 = 1; i2 < 16; i2++) {
            if (i2 < 8) {
                iArr[i2] = a(255, (i2 & 1) != 0 ? 255 : 0, (i2 & 2) != 0 ? 255 : 0, (i2 & 4) != 0 ? 255 : 0);
            } else {
                int i3 = 127;
                int i4 = (i2 & 1) != 0 ? 127 : 0;
                int i5 = (i2 & 2) != 0 ? 127 : 0;
                if ((i2 & 4) == 0) {
                    i3 = 0;
                }
                iArr[i2] = a(255, i4, i5, i3);
            }
        }
        return iArr;
    }

    private static int[] c() {
        int[] iArr = new int[256];
        iArr[0] = 0;
        for (int i2 = 0; i2 < 256; i2++) {
            int i3 = 255;
            if (i2 >= 8) {
                int i4 = 170;
                int i5 = 43;
                int i6 = 85;
                switch (i2 & 136) {
                    case 0:
                        int i7 = ((i2 & 1) != 0 ? 85 : 0) + ((i2 & 16) != 0 ? 170 : 0);
                        int i8 = ((i2 & 2) != 0 ? 85 : 0) + ((i2 & 32) != 0 ? 170 : 0);
                        if ((i2 & 4) == 0) {
                            i6 = 0;
                        }
                        if ((i2 & 64) == 0) {
                            i4 = 0;
                        }
                        iArr[i2] = a(255, i7, i8, i6 + i4);
                        break;
                    case 8:
                        int i9 = ((i2 & 1) != 0 ? 85 : 0) + ((i2 & 16) != 0 ? 170 : 0);
                        int i10 = ((i2 & 2) != 0 ? 85 : 0) + ((i2 & 32) != 0 ? 170 : 0);
                        if ((i2 & 4) == 0) {
                            i6 = 0;
                        }
                        if ((i2 & 64) == 0) {
                            i4 = 0;
                        }
                        iArr[i2] = a(127, i9, i10, i6 + i4);
                        break;
                    case 128:
                        int i11 = ((i2 & 1) != 0 ? 43 : 0) + 127 + ((i2 & 16) != 0 ? 85 : 0);
                        int i12 = ((i2 & 2) != 0 ? 43 : 0) + 127 + ((i2 & 32) != 0 ? 85 : 0);
                        if ((i2 & 4) == 0) {
                            i5 = 0;
                        }
                        int i13 = i5 + 127;
                        if ((i2 & 64) == 0) {
                            i6 = 0;
                        }
                        iArr[i2] = a(255, i11, i12, i13 + i6);
                        break;
                    case 136:
                        int i14 = ((i2 & 1) != 0 ? 43 : 0) + ((i2 & 16) != 0 ? 85 : 0);
                        int i15 = ((i2 & 2) != 0 ? 43 : 0) + ((i2 & 32) != 0 ? 85 : 0);
                        if ((i2 & 4) == 0) {
                            i5 = 0;
                        }
                        if ((i2 & 64) == 0) {
                            i6 = 0;
                        }
                        iArr[i2] = a(255, i14, i15, i5 + i6);
                        break;
                }
            } else {
                int i16 = (i2 & 1) != 0 ? 255 : 0;
                int i17 = (i2 & 2) != 0 ? 255 : 0;
                if ((i2 & 4) == 0) {
                    i3 = 0;
                }
                iArr[i2] = a(63, i16, i17, i3);
            }
        }
        return iArr;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00b6, code lost:
        r3.put(r4, r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List a(byte[] r27, int r28) {
        /*
            r26 = this;
            r0 = r26
            com.google.android.exoplayer2.util.ParsableBitArray r1 = new com.google.android.exoplayer2.util.ParsableBitArray
            r2 = r27
            r3 = r28
            r1.<init>(r2, r3)
        L_0x000b:
            int r2 = r1.a()
            r3 = 48
            r4 = 2
            r5 = 3
            if (r2 < r3) goto L_0x0153
            r2 = 8
            int r3 = r1.c(r2)
            r7 = 15
            if (r3 != r7) goto L_0x0153
            com.google.android.exoplayer2.text.dvb.DvbParser$SubtitleService r3 = r0.a
            int r7 = r1.c(r2)
            r8 = 16
            int r9 = r1.c(r8)
            int r10 = r1.c(r8)
            int r11 = r1.c()
            int r11 = r11 + r10
            int r12 = r10 << 3
            int r13 = r1.a()
            if (r12 <= r13) goto L_0x004b
            java.lang.String r2 = "DvbParser"
            java.lang.String r3 = "Data field length exceeds limit"
            com.google.android.exoplayer2.util.Log.c(r2, r3)
            int r2 = r1.a()
            r1.b(r2)
            goto L_0x000b
        L_0x004b:
            r12 = 4
            switch(r7) {
                case 16: goto L_0x00ed;
                case 17: goto L_0x00c6;
                case 18: goto L_0x00aa;
                case 19: goto L_0x0092;
                case 20: goto L_0x0051;
                default: goto L_0x004f;
            }
        L_0x004f:
            goto L_0x0149
        L_0x0051:
            int r2 = r3.a
            if (r9 != r2) goto L_0x0149
            r1.b(r12)
            boolean r2 = r1.e()
            r1.b(r5)
            int r13 = r1.c(r8)
            int r14 = r1.c(r8)
            if (r2 == 0) goto L_0x0081
            int r6 = r1.c(r8)
            int r2 = r1.c(r8)
            int r4 = r1.c(r8)
            int r5 = r1.c(r8)
            r16 = r2
            r17 = r4
            r18 = r5
            r15 = r6
            goto L_0x0088
        L_0x0081:
            r16 = r13
            r18 = r14
            r15 = 0
            r17 = 0
        L_0x0088:
            com.google.android.exoplayer2.text.dvb.DvbParser$DisplayDefinition r2 = new com.google.android.exoplayer2.text.dvb.DvbParser$DisplayDefinition
            r12 = r2
            r12.<init>(r13, r14, r15, r16, r17, r18)
            r3.h = r2
            goto L_0x0149
        L_0x0092:
            int r2 = r3.a
            if (r9 != r2) goto L_0x009f
            com.google.android.exoplayer2.text.dvb.DvbParser$ObjectData r2 = a(r1)
            android.util.SparseArray r3 = r3.e
        L_0x009c:
            int r4 = r2.a
            goto L_0x00b6
        L_0x009f:
            int r2 = r3.b
            if (r9 != r2) goto L_0x0149
            com.google.android.exoplayer2.text.dvb.DvbParser$ObjectData r2 = a(r1)
            android.util.SparseArray r3 = r3.g
            goto L_0x009c
        L_0x00aa:
            int r2 = r3.a
            if (r9 != r2) goto L_0x00bb
            com.google.android.exoplayer2.text.dvb.DvbParser$ClutDefinition r2 = b(r1, r10)
            android.util.SparseArray r3 = r3.d
        L_0x00b4:
            int r4 = r2.a
        L_0x00b6:
            r3.put(r4, r2)
            goto L_0x0149
        L_0x00bb:
            int r2 = r3.b
            if (r9 != r2) goto L_0x0149
            com.google.android.exoplayer2.text.dvb.DvbParser$ClutDefinition r2 = b(r1, r10)
            android.util.SparseArray r3 = r3.f
            goto L_0x00b4
        L_0x00c6:
            com.google.android.exoplayer2.text.dvb.DvbParser$PageComposition r2 = r3.i
            int r4 = r3.a
            if (r9 != r4) goto L_0x0149
            if (r2 == 0) goto L_0x0149
            com.google.android.exoplayer2.text.dvb.DvbParser$RegionComposition r4 = a((com.google.android.exoplayer2.util.ParsableBitArray) r1, (int) r10)
            int r2 = r2.b
            if (r2 != 0) goto L_0x00e5
            android.util.SparseArray r2 = r3.c
            int r5 = r4.a
            java.lang.Object r2 = r2.get(r5)
            com.google.android.exoplayer2.text.dvb.DvbParser$RegionComposition r2 = (com.google.android.exoplayer2.text.dvb.DvbParser.RegionComposition) r2
            if (r2 == 0) goto L_0x00e5
            r4.a(r2)
        L_0x00e5:
            android.util.SparseArray r2 = r3.c
            int r3 = r4.a
            r2.put(r3, r4)
            goto L_0x0149
        L_0x00ed:
            int r5 = r3.a
            if (r9 != r5) goto L_0x0149
            com.google.android.exoplayer2.text.dvb.DvbParser$PageComposition r5 = r3.i
            r1.c(r2)
            int r6 = r1.c(r12)
            int r7 = r1.c(r4)
            r1.b(r4)
            int r10 = r10 + -2
            android.util.SparseArray r4 = new android.util.SparseArray
            r4.<init>()
        L_0x0108:
            if (r10 <= 0) goto L_0x0124
            int r9 = r1.c(r2)
            r1.b(r2)
            int r12 = r1.c(r8)
            int r13 = r1.c(r8)
            int r10 = r10 + -6
            com.google.android.exoplayer2.text.dvb.DvbParser$PageRegion r14 = new com.google.android.exoplayer2.text.dvb.DvbParser$PageRegion
            r14.<init>(r12, r13)
            r4.put(r9, r14)
            goto L_0x0108
        L_0x0124:
            com.google.android.exoplayer2.text.dvb.DvbParser$PageComposition r2 = new com.google.android.exoplayer2.text.dvb.DvbParser$PageComposition
            r2.<init>(r6, r7, r4)
            int r4 = r2.b
            if (r4 == 0) goto L_0x013f
            r3.i = r2
            android.util.SparseArray r2 = r3.c
            r2.clear()
            android.util.SparseArray r2 = r3.d
            r2.clear()
            android.util.SparseArray r2 = r3.e
            r2.clear()
            goto L_0x0149
        L_0x013f:
            if (r5 == 0) goto L_0x0149
            int r4 = r5.a
            int r5 = r2.a
            if (r4 == r5) goto L_0x0149
            r3.i = r2
        L_0x0149:
            int r2 = r1.c()
            int r11 = r11 - r2
            r1.d(r11)
            goto L_0x000b
        L_0x0153:
            com.google.android.exoplayer2.text.dvb.DvbParser$SubtitleService r1 = r0.a
            com.google.android.exoplayer2.text.dvb.DvbParser$PageComposition r1 = r1.i
            if (r1 != 0) goto L_0x015e
            java.util.List r1 = java.util.Collections.emptyList()
            return r1
        L_0x015e:
            com.google.android.exoplayer2.text.dvb.DvbParser$SubtitleService r2 = r0.a
            com.google.android.exoplayer2.text.dvb.DvbParser$DisplayDefinition r2 = r2.h
            if (r2 == 0) goto L_0x0169
            com.google.android.exoplayer2.text.dvb.DvbParser$SubtitleService r2 = r0.a
            com.google.android.exoplayer2.text.dvb.DvbParser$DisplayDefinition r2 = r2.h
            goto L_0x016b
        L_0x0169:
            com.google.android.exoplayer2.text.dvb.DvbParser$DisplayDefinition r2 = r0.h
        L_0x016b:
            android.graphics.Bitmap r3 = r0.j
            if (r3 == 0) goto L_0x0187
            int r3 = r2.a
            int r3 = r3 + 1
            android.graphics.Bitmap r7 = r0.j
            int r7 = r7.getWidth()
            if (r3 != r7) goto L_0x0187
            int r3 = r2.b
            int r3 = r3 + 1
            android.graphics.Bitmap r7 = r0.j
            int r7 = r7.getHeight()
            if (r3 == r7) goto L_0x019c
        L_0x0187:
            int r3 = r2.a
            int r3 = r3 + 1
            int r7 = r2.b
            int r7 = r7 + 1
            android.graphics.Bitmap$Config r8 = android.graphics.Bitmap.Config.ARGB_8888
            android.graphics.Bitmap r3 = android.graphics.Bitmap.createBitmap(r3, r7, r8)
            r0.j = r3
            android.graphics.Canvas r7 = r0.g
            r7.setBitmap(r3)
        L_0x019c:
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            android.util.SparseArray r1 = r1.c
            r7 = 0
        L_0x01a4:
            int r8 = r1.size()
            if (r7 >= r8) goto L_0x0319
            android.graphics.Canvas r8 = r0.g
            r8.save()
            java.lang.Object r8 = r1.valueAt(r7)
            com.google.android.exoplayer2.text.dvb.DvbParser$PageRegion r8 = (com.google.android.exoplayer2.text.dvb.DvbParser.PageRegion) r8
            int r9 = r1.keyAt(r7)
            com.google.android.exoplayer2.text.dvb.DvbParser$SubtitleService r10 = r0.a
            android.util.SparseArray r10 = r10.c
            java.lang.Object r9 = r10.get(r9)
            com.google.android.exoplayer2.text.dvb.DvbParser$RegionComposition r9 = (com.google.android.exoplayer2.text.dvb.DvbParser.RegionComposition) r9
            int r10 = r8.a
            int r11 = r2.c
            int r10 = r10 + r11
            int r8 = r8.b
            int r11 = r2.e
            int r8 = r8 + r11
            int r11 = r9.c
            int r11 = r11 + r10
            int r12 = r2.d
            int r11 = java.lang.Math.min(r11, r12)
            int r12 = r9.d
            int r12 = r12 + r8
            int r13 = r2.f
            int r12 = java.lang.Math.min(r12, r13)
            android.graphics.Canvas r13 = r0.g
            r13.clipRect(r10, r8, r11, r12)
            com.google.android.exoplayer2.text.dvb.DvbParser$SubtitleService r11 = r0.a
            android.util.SparseArray r11 = r11.d
            int r12 = r9.f
            java.lang.Object r11 = r11.get(r12)
            com.google.android.exoplayer2.text.dvb.DvbParser$ClutDefinition r11 = (com.google.android.exoplayer2.text.dvb.DvbParser.ClutDefinition) r11
            if (r11 != 0) goto L_0x0202
            com.google.android.exoplayer2.text.dvb.DvbParser$SubtitleService r11 = r0.a
            android.util.SparseArray r11 = r11.f
            int r12 = r9.f
            java.lang.Object r11 = r11.get(r12)
            com.google.android.exoplayer2.text.dvb.DvbParser$ClutDefinition r11 = (com.google.android.exoplayer2.text.dvb.DvbParser.ClutDefinition) r11
            if (r11 != 0) goto L_0x0202
            com.google.android.exoplayer2.text.dvb.DvbParser$ClutDefinition r11 = r0.i
        L_0x0202:
            android.util.SparseArray r12 = r9.j
            r13 = 0
        L_0x0205:
            int r14 = r12.size()
            if (r13 >= r14) goto L_0x0282
            int r14 = r12.keyAt(r13)
            java.lang.Object r15 = r12.valueAt(r13)
            com.google.android.exoplayer2.text.dvb.DvbParser$RegionObject r15 = (com.google.android.exoplayer2.text.dvb.DvbParser.RegionObject) r15
            com.google.android.exoplayer2.text.dvb.DvbParser$SubtitleService r6 = r0.a
            android.util.SparseArray r6 = r6.e
            java.lang.Object r6 = r6.get(r14)
            com.google.android.exoplayer2.text.dvb.DvbParser$ObjectData r6 = (com.google.android.exoplayer2.text.dvb.DvbParser.ObjectData) r6
            if (r6 != 0) goto L_0x022b
            com.google.android.exoplayer2.text.dvb.DvbParser$SubtitleService r6 = r0.a
            android.util.SparseArray r6 = r6.g
            java.lang.Object r6 = r6.get(r14)
            com.google.android.exoplayer2.text.dvb.DvbParser$ObjectData r6 = (com.google.android.exoplayer2.text.dvb.DvbParser.ObjectData) r6
        L_0x022b:
            if (r6 == 0) goto L_0x0271
            boolean r14 = r6.b
            if (r14 == 0) goto L_0x0233
            r14 = 0
            goto L_0x0235
        L_0x0233:
            android.graphics.Paint r14 = r0.e
        L_0x0235:
            int r4 = r9.e
            int r5 = r15.a
            int r5 = r5 + r10
            int r15 = r15.b
            int r15 = r15 + r8
            r23 = r1
            android.graphics.Canvas r1 = r0.g
            r24 = r12
            r12 = 3
            if (r4 != r12) goto L_0x024b
            int[] r12 = r11.d
        L_0x0248:
            r25 = r7
            goto L_0x0254
        L_0x024b:
            r12 = 2
            if (r4 != r12) goto L_0x0251
            int[] r12 = r11.c
            goto L_0x0248
        L_0x0251:
            int[] r12 = r11.b
            goto L_0x0248
        L_0x0254:
            byte[] r7 = r6.c
            r16 = r7
            r17 = r12
            r18 = r4
            r19 = r5
            r20 = r15
            r21 = r14
            r22 = r1
            a(r16, r17, r18, r19, r20, r21, r22)
            byte[] r6 = r6.d
            int r20 = r15 + 1
            r16 = r6
            a(r16, r17, r18, r19, r20, r21, r22)
            goto L_0x0277
        L_0x0271:
            r23 = r1
            r25 = r7
            r24 = r12
        L_0x0277:
            int r13 = r13 + 1
            r1 = r23
            r12 = r24
            r7 = r25
            r4 = 2
            r5 = 3
            goto L_0x0205
        L_0x0282:
            r23 = r1
            r25 = r7
            boolean r1 = r9.b
            if (r1 == 0) goto L_0x02c2
            int r1 = r9.e
            r4 = 3
            if (r1 != r4) goto L_0x0297
            int[] r1 = r11.d
            int r5 = r9.g
            r1 = r1[r5]
            r5 = 2
            goto L_0x02a9
        L_0x0297:
            int r1 = r9.e
            r5 = 2
            if (r1 != r5) goto L_0x02a3
            int[] r1 = r11.c
            int r6 = r9.h
            r1 = r1[r6]
            goto L_0x02a9
        L_0x02a3:
            int[] r1 = r11.b
            int r6 = r9.i
            r1 = r1[r6]
        L_0x02a9:
            android.graphics.Paint r6 = r0.f
            r6.setColor(r1)
            android.graphics.Canvas r11 = r0.g
            float r12 = (float) r10
            float r13 = (float) r8
            int r1 = r9.c
            int r1 = r1 + r10
            float r14 = (float) r1
            int r1 = r9.d
            int r1 = r1 + r8
            float r15 = (float) r1
            android.graphics.Paint r1 = r0.f
            r16 = r1
            r11.drawRect(r12, r13, r14, r15, r16)
            goto L_0x02c4
        L_0x02c2:
            r4 = 3
            r5 = 2
        L_0x02c4:
            com.google.android.exoplayer2.text.Cue$Builder r1 = new com.google.android.exoplayer2.text.Cue$Builder
            r1.<init>()
            android.graphics.Bitmap r6 = r0.j
            int r7 = r9.c
            int r11 = r9.d
            android.graphics.Bitmap r6 = android.graphics.Bitmap.createBitmap(r6, r10, r8, r7, r11)
            r1.b = r6
            float r6 = (float) r10
            int r7 = r2.a
            float r7 = (float) r7
            float r6 = r6 / r7
            r1.f = r6
            r6 = 0
            r1.g = r6
            float r7 = (float) r8
            int r8 = r2.b
            float r8 = (float) r8
            float r7 = r7 / r8
            com.google.android.exoplayer2.text.Cue$Builder r1 = r1.a(r7, r6)
            r1.e = r6
            int r6 = r9.c
            float r6 = (float) r6
            int r7 = r2.a
            float r7 = (float) r7
            float r6 = r6 / r7
            r1.h = r6
            int r6 = r9.d
            float r6 = (float) r6
            int r7 = r2.b
            float r7 = (float) r7
            float r6 = r6 / r7
            r1.i = r6
            com.google.android.exoplayer2.text.Cue r1 = r1.a()
            r3.add(r1)
            android.graphics.Canvas r1 = r0.g
            android.graphics.PorterDuff$Mode r6 = android.graphics.PorterDuff.Mode.CLEAR
            r7 = 0
            r1.drawColor(r7, r6)
            android.graphics.Canvas r1 = r0.g
            r1.restore()
            int r1 = r25 + 1
            r7 = r1
            r1 = r23
            r4 = 2
            r5 = 3
            goto L_0x01a4
        L_0x0319:
            java.util.List r1 = java.util.Collections.unmodifiableList(r3)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.dvb.DvbParser.a(byte[], int):java.util.List");
    }
}
