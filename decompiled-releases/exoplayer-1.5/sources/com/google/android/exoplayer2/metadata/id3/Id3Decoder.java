package com.google.android.exoplayer2.metadata.id3;

import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.MetadataInputBuffer;
import com.google.android.exoplayer2.metadata.SimpleMetadataDecoder;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public final class Id3Decoder extends SimpleMetadataDecoder {
    public static final FramePredicate a = Id3Decoder$$Lambda$0.a;
    private final FramePredicate b;

    public interface FramePredicate {
        boolean a(int i, int i2, int i3, int i4, int i5);
    }

    final class Id3Header {
        /* access modifiers changed from: private */
        public final int a;
        /* access modifiers changed from: private */
        public final boolean b;
        /* access modifiers changed from: private */
        public final int c;

        public Id3Header(int i, boolean z, int i2) {
            this.a = i;
            this.b = z;
            this.c = i2;
        }
    }

    public Id3Decoder() {
        this((FramePredicate) null);
    }

    public Id3Decoder(FramePredicate framePredicate) {
        this.b = framePredicate;
    }

    private static int a(byte[] bArr, int i, int i2) {
        int b2 = b(bArr, i);
        if (i2 == 0 || i2 == 3) {
            return b2;
        }
        while (b2 < bArr.length - 1) {
            if (b2 % 2 == 0 && bArr[b2 + 1] == 0) {
                return b2;
            }
            b2 = b(bArr, b2 + 1);
        }
        return bArr.length;
    }

    private static ChapterFrame a(ParsableByteArray parsableByteArray, int i, int i2, boolean z, int i3, FramePredicate framePredicate) {
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        int i4 = parsableByteArray2.b;
        int b2 = b(parsableByteArray2.a, i4);
        String str = new String(parsableByteArray2.a, i4, b2 - i4, "ISO-8859-1");
        parsableByteArray.d(b2 + 1);
        int j = parsableByteArray.j();
        int j2 = parsableByteArray.j();
        long h = parsableByteArray.h();
        long j3 = h == 4294967295L ? -1 : h;
        long h2 = parsableByteArray.h();
        long j4 = h2 == 4294967295L ? -1 : h2;
        ArrayList arrayList = new ArrayList();
        int i5 = i4 + i;
        while (parsableByteArray2.b < i5) {
            Id3Frame a2 = a(i2, parsableByteArray, z, i3, framePredicate);
            if (a2 != null) {
                arrayList.add(a2);
            }
        }
        return new ChapterFrame(str, j, j2, j3, j4, (Id3Frame[]) arrayList.toArray(new Id3Frame[0]));
    }

    private static Id3Header a(ParsableByteArray parsableByteArray) {
        String sb;
        if (parsableByteArray.a() < 10) {
            sb = "Data too short to be an ID3 tag";
        } else {
            int g = parsableByteArray.g();
            boolean z = false;
            if (g != 4801587) {
                String valueOf = String.valueOf(String.format("%06X", new Object[]{Integer.valueOf(g)}));
                Log.c("Id3Decoder", valueOf.length() != 0 ? "Unexpected first three bytes of ID3 tag header: 0x".concat(valueOf) : new String("Unexpected first three bytes of ID3 tag header: 0x"));
                return null;
            }
            int c = parsableByteArray.c();
            parsableByteArray.e(1);
            int c2 = parsableByteArray.c();
            int n = parsableByteArray.n();
            if (c == 2) {
                if ((c2 & 64) != 0) {
                    sb = "Skipped ID3 tag with majorVersion=2 and undefined compression scheme";
                }
            } else if (c == 3) {
                if ((c2 & 64) != 0) {
                    int j = parsableByteArray.j();
                    parsableByteArray.e(j);
                    n -= j + 4;
                }
            } else if (c == 4) {
                if ((c2 & 64) != 0) {
                    int n2 = parsableByteArray.n();
                    parsableByteArray.e(n2 - 4);
                    n -= n2;
                }
                if ((c2 & 16) != 0) {
                    n -= 10;
                }
            } else {
                sb = new StringBuilder(57).append("Skipped ID3 tag with unsupported majorVersion=").append(c).toString();
            }
            if (c < 4 && (c2 & 128) != 0) {
                z = true;
            }
            return new Id3Header(c, z, n);
        }
        Log.c("Id3Decoder", sb);
        return null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v2, resolved type: com.google.android.exoplayer2.metadata.id3.ApicFrame} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: com.google.android.exoplayer2.metadata.id3.ApicFrame} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v15, resolved type: com.google.android.exoplayer2.metadata.id3.ApicFrame} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v21, resolved type: com.google.android.exoplayer2.metadata.id3.ApicFrame} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v27, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v38, resolved type: com.google.android.exoplayer2.metadata.id3.ApicFrame} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v39, resolved type: com.google.android.exoplayer2.metadata.id3.BinaryFrame} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v40, resolved type: com.google.android.exoplayer2.metadata.id3.MlltFrame} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v41, resolved type: com.google.android.exoplayer2.metadata.id3.ChapterTocFrame} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v42, resolved type: com.google.android.exoplayer2.metadata.id3.ChapterFrame} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v43, resolved type: com.google.android.exoplayer2.metadata.id3.CommentFrame} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v44, resolved type: com.google.android.exoplayer2.metadata.id3.ApicFrame} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v45, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v46, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v47, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v48, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r3v2, types: [com.google.android.exoplayer2.metadata.id3.Id3Frame] */
    /* JADX WARNING: type inference failed for: r5v19, types: [com.google.android.exoplayer2.metadata.id3.GeobFrame] */
    /* JADX WARNING: type inference failed for: r2v21, types: [com.google.android.exoplayer2.metadata.id3.PrivFrame] */
    /* JADX WARNING: type inference failed for: r2v22 */
    /* JADX WARNING: type inference failed for: r2v24, types: [com.google.android.exoplayer2.metadata.id3.UrlLinkFrame] */
    /* JADX WARNING: type inference failed for: r5v24, types: [com.google.android.exoplayer2.metadata.id3.UrlLinkFrame] */
    /* JADX WARNING: type inference failed for: r5v26, types: [com.google.android.exoplayer2.metadata.id3.TextInformationFrame] */
    /* JADX WARNING: type inference failed for: r5v30, types: [com.google.android.exoplayer2.metadata.id3.TextInformationFrame] */
    /* JADX WARNING: Code restructure failed: missing block: B:217:0x03e1, code lost:
        r0 = th;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:212:0x03ae A[Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:217:0x03e1 A[ExcHandler: all (th java.lang.Throwable), PHI: r7 r18 
      PHI: (r7v5 com.google.android.exoplayer2.util.ParsableByteArray) = (r7v6 com.google.android.exoplayer2.util.ParsableByteArray), (r7v6 com.google.android.exoplayer2.util.ParsableByteArray), (r7v12 com.google.android.exoplayer2.util.ParsableByteArray), (r7v0 com.google.android.exoplayer2.util.ParsableByteArray), (r7v0 com.google.android.exoplayer2.util.ParsableByteArray), (r7v0 com.google.android.exoplayer2.util.ParsableByteArray) binds: [B:214:0x03db, B:215:?, B:184:0x0320, B:155:0x02a0, B:160:0x02b8, B:145:0x0275] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r18v2 int) = (r18v3 int), (r18v3 int), (r18v7 int), (r18v11 int), (r18v11 int), (r18v12 int) binds: [B:214:0x03db, B:215:?, B:184:0x0320, B:155:0x02a0, B:160:0x02b8, B:145:0x0275] A[DONT_GENERATE, DONT_INLINE], Splitter:B:145:0x0275] */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.exoplayer2.metadata.id3.Id3Frame a(int r19, com.google.android.exoplayer2.util.ParsableByteArray r20, boolean r21, int r22, com.google.android.exoplayer2.metadata.id3.Id3Decoder.FramePredicate r23) {
        /*
            r0 = r19
            r7 = r20
            int r8 = r20.c()
            int r9 = r20.c()
            int r10 = r20.c()
            r12 = 3
            if (r0 < r12) goto L_0x0019
            int r1 = r20.c()
            r13 = r1
            goto L_0x001a
        L_0x0019:
            r13 = 0
        L_0x001a:
            r14 = 4
            if (r0 != r14) goto L_0x003a
            int r1 = r20.o()
            if (r21 != 0) goto L_0x0038
            r2 = r1 & 255(0xff, float:3.57E-43)
            int r3 = r1 >> 8
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r3 = r3 << 7
            r2 = r2 | r3
            int r3 = r1 >> 16
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r3 = r3 << 14
            r2 = r2 | r3
            int r1 = r1 >>> 24
            int r1 = r1 << 21
            r1 = r1 | r2
        L_0x0038:
            r15 = r1
            goto L_0x0046
        L_0x003a:
            if (r0 != r12) goto L_0x0041
            int r1 = r20.o()
            goto L_0x0038
        L_0x0041:
            int r1 = r20.g()
            goto L_0x0038
        L_0x0046:
            if (r0 < r12) goto L_0x004e
            int r1 = r20.d()
            r6 = r1
            goto L_0x004f
        L_0x004e:
            r6 = 0
        L_0x004f:
            r5 = 0
            if (r8 != 0) goto L_0x0062
            if (r9 != 0) goto L_0x0062
            if (r10 != 0) goto L_0x0062
            if (r13 != 0) goto L_0x0062
            if (r15 != 0) goto L_0x0062
            if (r6 != 0) goto L_0x0062
        L_0x005c:
            int r0 = r7.c
            r7.d(r0)
            return r5
        L_0x0062:
            int r1 = r7.b
            int r4 = r1 + r15
            int r1 = r7.c
            java.lang.String r3 = "Id3Decoder"
            if (r4 <= r1) goto L_0x0072
            java.lang.String r0 = "Frame size exceeds remaining tag data"
            com.google.android.exoplayer2.util.Log.c(r3, r0)
            goto L_0x005c
        L_0x0072:
            if (r23 == 0) goto L_0x008c
            r1 = r23
            r2 = r19
            r16 = r3
            r3 = r8
            r11 = r4
            r4 = r9
            r14 = r5
            r5 = r10
            r17 = r6
            r6 = r13
            boolean r1 = r1.a(r2, r3, r4, r5, r6)
            if (r1 != 0) goto L_0x0092
            r7.d(r11)
            return r14
        L_0x008c:
            r16 = r3
            r11 = r4
            r14 = r5
            r17 = r6
        L_0x0092:
            r1 = 1
            if (r0 != r12) goto L_0x00b0
            r2 = r17
            r3 = r2 & 128(0x80, float:1.794E-43)
            if (r3 == 0) goto L_0x009d
            r3 = 1
            goto L_0x009e
        L_0x009d:
            r3 = 0
        L_0x009e:
            r4 = r2 & 64
            if (r4 == 0) goto L_0x00a4
            r4 = 1
            goto L_0x00a5
        L_0x00a4:
            r4 = 0
        L_0x00a5:
            r2 = r2 & 32
            if (r2 == 0) goto L_0x00ab
            r2 = 1
            goto L_0x00ac
        L_0x00ab:
            r2 = 0
        L_0x00ac:
            r5 = r4
            r6 = 0
            r4 = r3
            goto L_0x00df
        L_0x00b0:
            r2 = r17
            r3 = 4
            if (r0 != r3) goto L_0x00da
            r3 = r2 & 64
            if (r3 == 0) goto L_0x00bb
            r3 = 1
            goto L_0x00bc
        L_0x00bb:
            r3 = 0
        L_0x00bc:
            r4 = r2 & 8
            if (r4 == 0) goto L_0x00c2
            r4 = 1
            goto L_0x00c3
        L_0x00c2:
            r4 = 0
        L_0x00c3:
            r5 = r2 & 4
            if (r5 == 0) goto L_0x00c9
            r5 = 1
            goto L_0x00ca
        L_0x00c9:
            r5 = 0
        L_0x00ca:
            r6 = r2 & 2
            if (r6 == 0) goto L_0x00d0
            r6 = 1
            goto L_0x00d1
        L_0x00d0:
            r6 = 0
        L_0x00d1:
            r2 = r2 & r1
            if (r2 == 0) goto L_0x00d7
            r2 = r3
            r3 = 1
            goto L_0x00df
        L_0x00d7:
            r2 = r3
            r3 = 0
            goto L_0x00df
        L_0x00da:
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
        L_0x00df:
            if (r4 != 0) goto L_0x0400
            if (r5 == 0) goto L_0x00e5
            goto L_0x0400
        L_0x00e5:
            if (r2 == 0) goto L_0x00ec
            int r15 = r15 + -1
            r7.e(r1)
        L_0x00ec:
            if (r3 == 0) goto L_0x00f4
            int r15 = r15 + -4
            r2 = 4
            r7.e(r2)
        L_0x00f4:
            if (r6 == 0) goto L_0x00fa
            int r15 = b((com.google.android.exoplayer2.util.ParsableByteArray) r7, (int) r15)
        L_0x00fa:
            r2 = 84
            r3 = 88
            r4 = 2
            if (r8 != r2) goto L_0x013b
            if (r9 != r3) goto L_0x013b
            if (r10 != r3) goto L_0x013b
            if (r0 == r4) goto L_0x0109
            if (r13 != r3) goto L_0x013b
        L_0x0109:
            if (r15 > 0) goto L_0x010d
        L_0x010b:
            r5 = r14
            goto L_0x0162
        L_0x010d:
            int r1 = r20.c()     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            java.lang.String r2 = a((int) r1)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            int r3 = r15 + -1
            byte[] r4 = new byte[r3]     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            r5 = 0
            r7.a(r4, r5, r3)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            int r3 = a(r4, r5, r1)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            java.lang.String r6 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            r6.<init>(r4, r5, r3, r2)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            int r5 = b(r1)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            int r3 = r3 + r5
            int r1 = a(r4, r3, r1)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            java.lang.String r1 = a((byte[]) r4, (int) r3, (int) r1, (java.lang.String) r2)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            com.google.android.exoplayer2.metadata.id3.TextInformationFrame r5 = new com.google.android.exoplayer2.metadata.id3.TextInformationFrame     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            java.lang.String r2 = "TXXX"
            r5.<init>(r2, r6, r1)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            goto L_0x0162
        L_0x013b:
            if (r8 != r2) goto L_0x0170
            java.lang.String r1 = a((int) r0, (int) r8, (int) r9, (int) r10, (int) r13)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            if (r15 > 0) goto L_0x0144
            goto L_0x010b
        L_0x0144:
            int r2 = r20.c()     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            java.lang.String r3 = a((int) r2)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            int r4 = r15 + -1
            byte[] r5 = new byte[r4]     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            r6 = 0
            r7.a(r5, r6, r4)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            int r2 = a(r5, r6, r2)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            java.lang.String r4 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            r4.<init>(r5, r6, r2, r3)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            com.google.android.exoplayer2.metadata.id3.TextInformationFrame r5 = new com.google.android.exoplayer2.metadata.id3.TextInformationFrame     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            r5.<init>(r1, r14, r4)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
        L_0x0162:
            r18 = r11
            goto L_0x03ac
        L_0x0166:
            r0 = move-exception
        L_0x0167:
            r2 = r11
            goto L_0x03fc
        L_0x016a:
            r0 = move-exception
        L_0x016b:
            r2 = r11
            r1 = r16
            goto L_0x03f1
        L_0x0170:
            r5 = 87
            java.lang.String r6 = "ISO-8859-1"
            if (r8 != r5) goto L_0x01af
            if (r9 != r3) goto L_0x01af
            if (r10 != r3) goto L_0x01af
            if (r0 == r4) goto L_0x017e
            if (r13 != r3) goto L_0x01af
        L_0x017e:
            if (r15 > 0) goto L_0x0181
            goto L_0x010b
        L_0x0181:
            int r1 = r20.c()     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            java.lang.String r2 = a((int) r1)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            int r3 = r15 + -1
            byte[] r4 = new byte[r3]     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            r5 = 0
            r7.a(r4, r5, r3)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            int r3 = a(r4, r5, r1)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            java.lang.String r12 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            r12.<init>(r4, r5, r3, r2)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            int r1 = b(r1)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            int r3 = r3 + r1
            int r1 = b((byte[]) r4, (int) r3)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            java.lang.String r1 = a((byte[]) r4, (int) r3, (int) r1, (java.lang.String) r6)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            com.google.android.exoplayer2.metadata.id3.UrlLinkFrame r5 = new com.google.android.exoplayer2.metadata.id3.UrlLinkFrame     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            java.lang.String r2 = "WXXX"
            r5.<init>(r2, r12, r1)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            goto L_0x0162
        L_0x01af:
            if (r8 != r5) goto L_0x01cb
            java.lang.String r1 = a((int) r0, (int) r8, (int) r9, (int) r10, (int) r13)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            byte[] r2 = new byte[r15]     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            r3 = 0
            r7.a(r2, r3, r15)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            int r4 = b((byte[]) r2, (int) r3)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            java.lang.String r5 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            r5.<init>(r2, r3, r4, r6)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            com.google.android.exoplayer2.metadata.id3.UrlLinkFrame r2 = new com.google.android.exoplayer2.metadata.id3.UrlLinkFrame     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            r2.<init>(r1, r14, r5)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
        L_0x01c9:
            r5 = r2
            goto L_0x0162
        L_0x01cb:
            r3 = 73
            r5 = 80
            if (r8 != r5) goto L_0x01f5
            r14 = 82
            if (r9 != r14) goto L_0x01f5
            if (r10 != r3) goto L_0x01f5
            r14 = 86
            if (r13 != r14) goto L_0x01f5
            byte[] r2 = new byte[r15]     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            r3 = 0
            r7.a(r2, r3, r15)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            int r4 = b((byte[]) r2, (int) r3)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            java.lang.String r5 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            r5.<init>(r2, r3, r4, r6)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            int r4 = r4 + r1
            byte[] r1 = b(r2, r4, r15)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            com.google.android.exoplayer2.metadata.id3.PrivFrame r2 = new com.google.android.exoplayer2.metadata.id3.PrivFrame     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            r2.<init>(r5, r1)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            goto L_0x01c9
        L_0x01f5:
            r14 = 71
            r2 = 79
            if (r8 != r14) goto L_0x0247
            r14 = 69
            if (r9 != r14) goto L_0x0247
            if (r10 != r2) goto L_0x0247
            r14 = 66
            if (r13 == r14) goto L_0x0207
            if (r0 != r4) goto L_0x0247
        L_0x0207:
            int r2 = r20.c()     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            java.lang.String r3 = a((int) r2)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            int r4 = r15 + -1
            byte[] r5 = new byte[r4]     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            r12 = 0
            r7.a(r5, r12, r4)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            int r14 = b((byte[]) r5, (int) r12)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            java.lang.String r1 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            r1.<init>(r5, r12, r14, r6)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            r6 = 1
            int r14 = r14 + r6
            int r6 = a(r5, r14, r2)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            java.lang.String r12 = a((byte[]) r5, (int) r14, (int) r6, (java.lang.String) r3)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            int r14 = b(r2)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            int r6 = r6 + r14
            int r14 = a(r5, r6, r2)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            java.lang.String r3 = a((byte[]) r5, (int) r6, (int) r14, (java.lang.String) r3)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            int r2 = b(r2)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            int r14 = r14 + r2
            byte[] r2 = b(r5, r14, r4)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            com.google.android.exoplayer2.metadata.id3.GeobFrame r5 = new com.google.android.exoplayer2.metadata.id3.GeobFrame     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            r5.<init>(r1, r12, r3, r2)     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            goto L_0x0162
        L_0x0247:
            r1 = 65
            r14 = 67
            if (r0 != r4) goto L_0x0254
            if (r8 != r5) goto L_0x02fb
            if (r9 != r3) goto L_0x02fb
            if (r10 != r14) goto L_0x02fb
            goto L_0x025c
        L_0x0254:
            if (r8 != r1) goto L_0x02fb
            if (r9 != r5) goto L_0x02fb
            if (r10 != r3) goto L_0x02fb
            if (r13 != r14) goto L_0x02fb
        L_0x025c:
            int r1 = r20.c()     // Catch:{ UnsupportedEncodingException -> 0x02f6, all -> 0x02f1 }
            java.lang.String r2 = a((int) r1)     // Catch:{ UnsupportedEncodingException -> 0x02f6, all -> 0x02f1 }
            int r3 = r15 + -1
            byte[] r5 = new byte[r3]     // Catch:{ UnsupportedEncodingException -> 0x02f6, all -> 0x02f1 }
            r14 = 0
            r7.a(r5, r14, r3)     // Catch:{ UnsupportedEncodingException -> 0x02f6, all -> 0x02f1 }
            if (r0 != r4) goto L_0x029d
            java.lang.String r4 = "image/"
            java.lang.String r12 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x016a, all -> 0x0166 }
            r18 = r11
            r11 = 3
            r12.<init>(r5, r14, r11, r6)     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            java.lang.String r6 = com.dreamers.exoplayercore.repack.C0000a.a((java.lang.String) r12)     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            int r11 = r6.length()     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            if (r11 == 0) goto L_0x028b
            java.lang.String r4 = r4.concat(r6)     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            goto L_0x0291
        L_0x028b:
            java.lang.String r6 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            r6.<init>(r4)     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            r4 = r6
        L_0x0291:
            java.lang.String r6 = "image/jpg"
            boolean r6 = r6.equals(r4)     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            if (r6 == 0) goto L_0x029b
            java.lang.String r4 = "image/jpeg"
        L_0x029b:
            r11 = 2
            goto L_0x02cc
        L_0x029d:
            r18 = r11
            r4 = 0
            int r11 = b((byte[]) r5, (int) r4)     // Catch:{ UnsupportedEncodingException -> 0x0351, all -> 0x034c }
            java.lang.String r12 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x0351, all -> 0x034c }
            r12.<init>(r5, r4, r11, r6)     // Catch:{ UnsupportedEncodingException -> 0x0351, all -> 0x034c }
            java.lang.String r4 = com.dreamers.exoplayercore.repack.C0000a.a((java.lang.String) r12)     // Catch:{ UnsupportedEncodingException -> 0x0351, all -> 0x034c }
            r6 = 47
            int r6 = r4.indexOf(r6)     // Catch:{ UnsupportedEncodingException -> 0x0351, all -> 0x034c }
            r12 = -1
            if (r6 != r12) goto L_0x02cc
            java.lang.String r6 = "image/"
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            int r12 = r4.length()     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            if (r12 == 0) goto L_0x02c7
            java.lang.String r4 = r6.concat(r4)     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            goto L_0x02cc
        L_0x02c7:
            java.lang.String r4 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            r4.<init>(r6)     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
        L_0x02cc:
            int r6 = r11 + 1
            byte r6 = r5[r6]     // Catch:{ UnsupportedEncodingException -> 0x0351, all -> 0x034c }
            r6 = r6 & 255(0xff, float:3.57E-43)
            r12 = 2
            int r11 = r11 + r12
            int r12 = a(r5, r11, r1)     // Catch:{ UnsupportedEncodingException -> 0x0351, all -> 0x034c }
            java.lang.String r14 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x0351, all -> 0x034c }
            int r7 = r12 - r11
            r14.<init>(r5, r11, r7, r2)     // Catch:{ UnsupportedEncodingException -> 0x0351, all -> 0x034c }
            int r1 = b(r1)     // Catch:{ UnsupportedEncodingException -> 0x0351, all -> 0x034c }
            int r12 = r12 + r1
            byte[] r1 = b(r5, r12, r3)     // Catch:{ UnsupportedEncodingException -> 0x0351, all -> 0x034c }
            com.google.android.exoplayer2.metadata.id3.ApicFrame r5 = new com.google.android.exoplayer2.metadata.id3.ApicFrame     // Catch:{ UnsupportedEncodingException -> 0x0351, all -> 0x034c }
            r5.<init>(r4, r14, r6, r1)     // Catch:{ UnsupportedEncodingException -> 0x0351, all -> 0x034c }
            r7 = r20
            goto L_0x03ac
        L_0x02f1:
            r0 = move-exception
            r7 = r20
            goto L_0x0167
        L_0x02f6:
            r0 = move-exception
            r7 = r20
            goto L_0x016b
        L_0x02fb:
            r18 = r11
            r3 = 77
            if (r8 != r14) goto L_0x0356
            if (r9 != r2) goto L_0x0356
            if (r10 != r3) goto L_0x0356
            if (r13 == r3) goto L_0x030a
            r4 = 2
            if (r0 != r4) goto L_0x0356
        L_0x030a:
            r1 = 4
            if (r15 >= r1) goto L_0x0312
            r7 = r20
            r5 = 0
            goto L_0x03ac
        L_0x0312:
            int r1 = r20.c()     // Catch:{ UnsupportedEncodingException -> 0x0351, all -> 0x034c }
            java.lang.String r2 = a((int) r1)     // Catch:{ UnsupportedEncodingException -> 0x0351, all -> 0x034c }
            r3 = 3
            byte[] r4 = new byte[r3]     // Catch:{ UnsupportedEncodingException -> 0x0351, all -> 0x034c }
            r7 = r20
            r5 = 0
            r7.a(r4, r5, r3)     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            java.lang.String r6 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            r6.<init>(r4, r5, r3)     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            int r3 = r15 + -4
            byte[] r4 = new byte[r3]     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            r7.a(r4, r5, r3)     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            int r3 = a(r4, r5, r1)     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            java.lang.String r11 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            r11.<init>(r4, r5, r3, r2)     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            int r5 = b(r1)     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            int r3 = r3 + r5
            int r1 = a(r4, r3, r1)     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            java.lang.String r1 = a((byte[]) r4, (int) r3, (int) r1, (java.lang.String) r2)     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            com.google.android.exoplayer2.metadata.id3.CommentFrame r5 = new com.google.android.exoplayer2.metadata.id3.CommentFrame     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            r5.<init>(r6, r11, r1)     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            goto L_0x03ac
        L_0x034c:
            r0 = move-exception
            r7 = r20
            goto L_0x03e2
        L_0x0351:
            r0 = move-exception
            r7 = r20
            goto L_0x03e6
        L_0x0356:
            r7 = r20
            if (r8 != r14) goto L_0x0372
            r4 = 72
            if (r9 != r4) goto L_0x0372
            if (r10 != r1) goto L_0x0372
            if (r13 != r5) goto L_0x0372
            r1 = r20
            r2 = r15
            r3 = r19
            r4 = r21
            r5 = r22
            r6 = r23
            com.google.android.exoplayer2.metadata.id3.ChapterFrame r5 = a(r1, r2, r3, r4, r5, r6)     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            goto L_0x03ac
        L_0x0372:
            if (r8 != r14) goto L_0x038c
            r1 = 84
            if (r9 != r1) goto L_0x038c
            if (r10 != r2) goto L_0x038c
            if (r13 != r14) goto L_0x038c
            r1 = r20
            r2 = r15
            r3 = r19
            r4 = r21
            r5 = r22
            r6 = r23
            com.google.android.exoplayer2.metadata.id3.ChapterTocFrame r5 = b(r1, r2, r3, r4, r5, r6)     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            goto L_0x03ac
        L_0x038c:
            if (r8 != r3) goto L_0x039d
            r1 = 76
            if (r9 != r1) goto L_0x039d
            if (r10 != r1) goto L_0x039d
            r1 = 84
            if (r13 != r1) goto L_0x039d
            com.google.android.exoplayer2.metadata.id3.MlltFrame r5 = a((com.google.android.exoplayer2.util.ParsableByteArray) r7, (int) r15)     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            goto L_0x03ac
        L_0x039d:
            java.lang.String r1 = a((int) r0, (int) r8, (int) r9, (int) r10, (int) r13)     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            byte[] r2 = new byte[r15]     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            r3 = 0
            r7.a(r2, r3, r15)     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            com.google.android.exoplayer2.metadata.id3.BinaryFrame r5 = new com.google.android.exoplayer2.metadata.id3.BinaryFrame     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            r5.<init>(r1, r2)     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
        L_0x03ac:
            if (r5 != 0) goto L_0x03eb
            java.lang.String r0 = a((int) r0, (int) r8, (int) r9, (int) r10, (int) r13)     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            java.lang.String r1 = java.lang.String.valueOf(r0)     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            int r1 = r1.length()     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            int r1 = r1 + 50
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            r2.<init>(r1)     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            java.lang.String r1 = "Failed to decode frame: id="
            java.lang.StringBuilder r1 = r2.append(r1)     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            java.lang.StringBuilder r0 = r1.append(r0)     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            java.lang.String r1 = ", frameSize="
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            java.lang.StringBuilder r0 = r0.append(r15)     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            java.lang.String r0 = r0.toString()     // Catch:{ UnsupportedEncodingException -> 0x03e5, all -> 0x03e1 }
            r1 = r16
            com.google.android.exoplayer2.util.Log.c(r1, r0)     // Catch:{ UnsupportedEncodingException -> 0x03df, all -> 0x03e1 }
            goto L_0x03eb
        L_0x03df:
            r0 = move-exception
            goto L_0x03e8
        L_0x03e1:
            r0 = move-exception
        L_0x03e2:
            r2 = r18
            goto L_0x03fc
        L_0x03e5:
            r0 = move-exception
        L_0x03e6:
            r1 = r16
        L_0x03e8:
            r2 = r18
            goto L_0x03f1
        L_0x03eb:
            r2 = r18
            r7.d(r2)
            return r5
        L_0x03f1:
            java.lang.String r0 = "Unsupported character encoding"
            com.google.android.exoplayer2.util.Log.c(r1, r0)     // Catch:{ all -> 0x03fb }
            r7.d(r2)
            r3 = 0
            return r3
        L_0x03fb:
            r0 = move-exception
        L_0x03fc:
            r7.d(r2)
            throw r0
        L_0x0400:
            r2 = r11
            r3 = r14
            r1 = r16
            java.lang.String r0 = "Skipping unsupported compressed or encrypted frame"
            com.google.android.exoplayer2.util.Log.c(r1, r0)
            r7.d(r2)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.metadata.id3.Id3Decoder.a(int, com.google.android.exoplayer2.util.ParsableByteArray, boolean, int, com.google.android.exoplayer2.metadata.id3.Id3Decoder$FramePredicate):com.google.android.exoplayer2.metadata.id3.Id3Frame");
    }

    private static MlltFrame a(ParsableByteArray parsableByteArray, int i) {
        int d = parsableByteArray.d();
        int g = parsableByteArray.g();
        int g2 = parsableByteArray.g();
        int c = parsableByteArray.c();
        int c2 = parsableByteArray.c();
        ParsableBitArray parsableBitArray = new ParsableBitArray();
        parsableBitArray.a(parsableByteArray.a, parsableByteArray.c);
        parsableBitArray.a(parsableByteArray.b << 3);
        int i2 = ((i - 10) * 8) / (c + c2);
        int[] iArr = new int[i2];
        int[] iArr2 = new int[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            int c3 = parsableBitArray.c(c);
            int c4 = parsableBitArray.c(c2);
            iArr[i3] = c3;
            iArr2[i3] = c4;
        }
        return new MlltFrame(d, g, g2, iArr, iArr2);
    }

    private static String a(int i) {
        switch (i) {
            case 1:
                return "UTF-16";
            case 2:
                return "UTF-16BE";
            case 3:
                return "UTF-8";
            default:
                return "ISO-8859-1";
        }
    }

    private static String a(int i, int i2, int i3, int i4, int i5) {
        if (i == 2) {
            return String.format(Locale.US, "%c%c%c", new Object[]{Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)});
        }
        return String.format(Locale.US, "%c%c%c%c", new Object[]{Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5)});
    }

    private static String a(byte[] bArr, int i, int i2, String str) {
        return (i2 <= i || i2 > bArr.length) ? "" : new String(bArr, i, i2 - i, str);
    }

    static final /* synthetic */ boolean a() {
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0074, code lost:
        if ((r10 & 1) != 0) goto L_0x0089;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0084, code lost:
        if ((r10 & 128) != 0) goto L_0x0089;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean a(com.google.android.exoplayer2.util.ParsableByteArray r18, int r19, int r20, boolean r21) {
        /*
            r1 = r18
            r0 = r19
            int r2 = r1.b
        L_0x0006:
            int r3 = r18.a()     // Catch:{ all -> 0x00ad }
            r4 = 1
            r5 = r20
            if (r3 < r5) goto L_0x00a9
            r3 = 3
            r6 = 0
            if (r0 < r3) goto L_0x0020
            int r7 = r18.j()     // Catch:{ all -> 0x00ad }
            long r8 = r18.h()     // Catch:{ all -> 0x00ad }
            int r10 = r18.d()     // Catch:{ all -> 0x00ad }
            goto L_0x002a
        L_0x0020:
            int r7 = r18.g()     // Catch:{ all -> 0x00ad }
            int r8 = r18.g()     // Catch:{ all -> 0x00ad }
            long r8 = (long) r8
            r10 = 0
        L_0x002a:
            r11 = 0
            if (r7 != 0) goto L_0x0038
            int r7 = (r8 > r11 ? 1 : (r8 == r11 ? 0 : -1))
            if (r7 != 0) goto L_0x0038
            if (r10 != 0) goto L_0x0038
            r1.d(r2)
            return r4
        L_0x0038:
            r7 = 4
            if (r0 != r7) goto L_0x0069
            if (r21 != 0) goto L_0x0069
            r13 = 8421504(0x808080, double:4.160776E-317)
            long r13 = r13 & r8
            int r15 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
            if (r15 == 0) goto L_0x0049
            r1.d(r2)
            return r6
        L_0x0049:
            r11 = 255(0xff, double:1.26E-321)
            long r13 = r8 & r11
            r15 = 8
            long r15 = r8 >> r15
            long r15 = r15 & r11
            r17 = 7
            long r15 = r15 << r17
            long r13 = r13 | r15
            r15 = 16
            long r15 = r8 >> r15
            long r15 = r15 & r11
            r17 = 14
            long r15 = r15 << r17
            long r13 = r13 | r15
            r15 = 24
            long r8 = r8 >> r15
            long r8 = r8 & r11
            r11 = 21
            long r8 = r8 << r11
            long r8 = r8 | r13
        L_0x0069:
            if (r0 != r7) goto L_0x0079
            r3 = r10 & 64
            if (r3 == 0) goto L_0x0071
            r3 = 1
            goto L_0x0072
        L_0x0071:
            r3 = 0
        L_0x0072:
            r7 = r10 & 1
            if (r7 == 0) goto L_0x0077
            goto L_0x0089
        L_0x0077:
            r4 = 0
            goto L_0x0089
        L_0x0079:
            if (r0 != r3) goto L_0x0087
            r3 = r10 & 32
            if (r3 == 0) goto L_0x0081
            r3 = 1
            goto L_0x0082
        L_0x0081:
            r3 = 0
        L_0x0082:
            r7 = r10 & 128(0x80, float:1.794E-43)
            if (r7 == 0) goto L_0x0077
            goto L_0x0089
        L_0x0087:
            r3 = 0
            goto L_0x0077
        L_0x0089:
            if (r4 == 0) goto L_0x008d
            int r3 = r3 + 4
        L_0x008d:
            long r3 = (long) r3
            int r7 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r7 >= 0) goto L_0x0096
            r1.d(r2)
            return r6
        L_0x0096:
            int r3 = r18.a()     // Catch:{ all -> 0x00ad }
            long r3 = (long) r3
            int r7 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r7 >= 0) goto L_0x00a3
            r1.d(r2)
            return r6
        L_0x00a3:
            int r3 = (int) r8
            r1.e(r3)     // Catch:{ all -> 0x00ad }
            goto L_0x0006
        L_0x00a9:
            r1.d(r2)
            return r4
        L_0x00ad:
            r0 = move-exception
            r1.d(r2)
            goto L_0x00b3
        L_0x00b2:
            throw r0
        L_0x00b3:
            goto L_0x00b2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.metadata.id3.Id3Decoder.a(com.google.android.exoplayer2.util.ParsableByteArray, int, int, boolean):boolean");
    }

    private static int b(int i) {
        return (i == 0 || i == 3) ? 1 : 2;
    }

    private static int b(ParsableByteArray parsableByteArray, int i) {
        byte[] bArr = parsableByteArray.a;
        int i2 = parsableByteArray.b;
        int i3 = i2;
        while (true) {
            int i4 = i3 + 1;
            if (i4 >= i2 + i) {
                return i;
            }
            if ((bArr[i3] & Ev3Constants.Opcode.TST) == 255 && bArr[i4] == 0) {
                System.arraycopy(bArr, i3 + 2, bArr, i4, (i - (i3 - i2)) - 2);
                i--;
            }
            i3 = i4;
        }
    }

    private static int b(byte[] bArr, int i) {
        while (i < bArr.length) {
            if (bArr[i] == 0) {
                return i;
            }
            i++;
        }
        return bArr.length;
    }

    private static ChapterTocFrame b(ParsableByteArray parsableByteArray, int i, int i2, boolean z, int i3, FramePredicate framePredicate) {
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        int i4 = parsableByteArray2.b;
        int b2 = b(parsableByteArray2.a, i4);
        String str = new String(parsableByteArray2.a, i4, b2 - i4, "ISO-8859-1");
        parsableByteArray2.d(b2 + 1);
        int c = parsableByteArray.c();
        boolean z2 = (c & 2) != 0;
        boolean z3 = (c & 1) != 0;
        int c2 = parsableByteArray.c();
        String[] strArr = new String[c2];
        for (int i5 = 0; i5 < c2; i5++) {
            int i6 = parsableByteArray2.b;
            int b3 = b(parsableByteArray2.a, i6);
            strArr[i5] = new String(parsableByteArray2.a, i6, b3 - i6, "ISO-8859-1");
            parsableByteArray2.d(b3 + 1);
        }
        ArrayList arrayList = new ArrayList();
        int i7 = i4 + i;
        while (parsableByteArray2.b < i7) {
            Id3Frame a2 = a(i2, parsableByteArray2, z, i3, framePredicate);
            if (a2 != null) {
                arrayList.add(a2);
            }
        }
        return new ChapterTocFrame(str, z2, z3, strArr, (Id3Frame[]) arrayList.toArray(new Id3Frame[0]));
    }

    private static byte[] b(byte[] bArr, int i, int i2) {
        return i2 <= i ? Util.f : Arrays.copyOfRange(bArr, i, i2);
    }

    public final Metadata a(MetadataInputBuffer metadataInputBuffer, ByteBuffer byteBuffer) {
        return a(byteBuffer.array(), byteBuffer.limit());
    }

    public final Metadata a(byte[] bArr, int i) {
        ArrayList arrayList = new ArrayList();
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr, i);
        Id3Header a2 = a(parsableByteArray);
        if (a2 == null) {
            return null;
        }
        int i2 = parsableByteArray.b;
        int i3 = a2.a == 2 ? 6 : 10;
        int b2 = a2.c;
        if (a2.b) {
            b2 = b(parsableByteArray, a2.c);
        }
        parsableByteArray.c(i2 + b2);
        boolean z = false;
        if (!a(parsableByteArray, a2.a, i3, false)) {
            if (a2.a != 4 || !a(parsableByteArray, 4, i3, true)) {
                Log.c("Id3Decoder", new StringBuilder(56).append("Failed to validate ID3 tag with majorVersion=").append(a2.a).toString());
                return null;
            }
            z = true;
        }
        while (parsableByteArray.a() >= i3) {
            Id3Frame a3 = a(a2.a, parsableByteArray, z, i3, this.b);
            if (a3 != null) {
                arrayList.add(a3);
            }
        }
        return new Metadata((List) arrayList);
    }
}
