package com.google.android.exoplayer2.extractor.mkv;

import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.util.Assertions;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.util.ArrayDeque;

final class DefaultEbmlReader implements EbmlReader {
    private final byte[] a = new byte[8];
    private final ArrayDeque b = new ArrayDeque();
    private final VarintReader c = new VarintReader();
    private EbmlProcessor d;
    private int e;
    private int f;
    private long g;

    final class MasterElement {
        /* access modifiers changed from: private */
        public final int a;
        /* access modifiers changed from: private */
        public final long b;

        private MasterElement(int i, long j) {
            this.a = i;
            this.b = j;
        }

        /* synthetic */ MasterElement(int i, long j, byte b2) {
            this(i, j);
        }
    }

    private long a(ExtractorInput extractorInput, int i) {
        extractorInput.b(this.a, 0, i);
        long j = 0;
        for (int i2 = 0; i2 < i; i2++) {
            j = (j << 8) | ((long) (this.a[i2] & Ev3Constants.Opcode.TST));
        }
        return j;
    }

    private static String b(ExtractorInput extractorInput, int i) {
        if (i == 0) {
            return "";
        }
        byte[] bArr = new byte[i];
        extractorInput.b(bArr, 0, i);
        while (i > 0 && bArr[i - 1] == 0) {
            i--;
        }
        return new String(bArr, 0, i);
    }

    public final void a() {
        this.e = 0;
        this.b.clear();
        this.c.a();
    }

    public final void a(EbmlProcessor ebmlProcessor) {
        this.d = ebmlProcessor;
    }

    public final boolean a(ExtractorInput extractorInput) {
        Assertions.a((Object) this.d);
        while (true) {
            MasterElement masterElement = (MasterElement) this.b.peek();
            if (masterElement == null || extractorInput.c() < masterElement.b) {
                if (this.e == 0) {
                    long a2 = this.c.a(extractorInput, true, false, 4);
                    if (a2 == -2) {
                        extractorInput.a();
                        while (true) {
                            extractorInput.d(this.a, 0, 4);
                            int a3 = VarintReader.a(this.a[0]);
                            if (a3 != -1 && a3 <= 4) {
                                int a4 = (int) VarintReader.a(this.a, a3, false);
                                if (this.d.b(a4)) {
                                    extractorInput.b(a3);
                                    a2 = (long) a4;
                                }
                            }
                            extractorInput.b(1);
                        }
                    }
                    if (a2 == -1) {
                        return false;
                    }
                    this.f = (int) a2;
                    this.e = 1;
                }
                if (this.e == 1) {
                    this.g = this.c.a(extractorInput, false, true, 8);
                    this.e = 2;
                }
                int a5 = this.d.a(this.f);
                switch (a5) {
                    case 0:
                        extractorInput.b((int) this.g);
                        this.e = 0;
                    case 1:
                        long c2 = extractorInput.c();
                        this.b.push(new MasterElement(this.f, this.g + c2, (byte) 0));
                        this.d.a(this.f, c2, this.g);
                        this.e = 0;
                        return true;
                    case 2:
                        long j = this.g;
                        if (j <= 8) {
                            this.d.a(this.f, a(extractorInput, (int) j));
                            this.e = 0;
                            return true;
                        }
                        throw new ParserException(new StringBuilder(42).append("Invalid integer size: ").append(this.g).toString());
                    case 3:
                        long j2 = this.g;
                        if (j2 <= 2147483647L) {
                            this.d.a(this.f, b(extractorInput, (int) j2));
                            this.e = 0;
                            return true;
                        }
                        throw new ParserException(new StringBuilder(41).append("String element size: ").append(this.g).toString());
                    case 4:
                        this.d.a(this.f, (int) this.g, extractorInput);
                        this.e = 0;
                        return true;
                    case 5:
                        long j3 = this.g;
                        if (j3 == 4 || j3 == 8) {
                            EbmlProcessor ebmlProcessor = this.d;
                            int i = this.f;
                            int i2 = (int) j3;
                            long a6 = a(extractorInput, i2);
                            ebmlProcessor.a(i, i2 == 4 ? (double) Float.intBitsToFloat((int) a6) : Double.longBitsToDouble(a6));
                            this.e = 0;
                            return true;
                        }
                        throw new ParserException(new StringBuilder(40).append("Invalid float size: ").append(this.g).toString());
                    default:
                        throw new ParserException(new StringBuilder(32).append("Invalid element type ").append(a5).toString());
                }
            } else {
                this.d.c(((MasterElement) this.b.pop()).a);
                return true;
            }
        }
    }
}
