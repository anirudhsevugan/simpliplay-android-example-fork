package com.google.android.exoplayer2.mediacodec;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaCryptoException;
import android.media.MediaFormat;
import android.os.Bundle;
import android.os.SystemClock;
import com.google.android.exoplayer2.BaseRenderer;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.audio.MpegAudioUtil;
import com.google.android.exoplayer2.decoder.CryptoInfo;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.decoder.DecoderReuseEvaluation;
import com.google.android.exoplayer2.drm.DrmSession;
import com.google.android.exoplayer2.drm.DrmSession$$CC;
import com.google.android.exoplayer2.drm.ExoMediaCrypto;
import com.google.android.exoplayer2.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer2.mediacodec.MediaCodecAdapter;
import com.google.android.exoplayer2.mediacodec.MediaCodecUtil;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.TimedValueQueue;
import com.google.android.exoplayer2.util.Util;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public abstract class MediaCodecRenderer extends BaseRenderer {
    private static final byte[] a = {0, 0, 1, Ev3Constants.Opcode.JR_LTF, Ev3Constants.Opcode.JR_TRUE, Ev3Constants.Opcode.FILE, 11, Ev3Constants.Opcode.MAILBOX_READ, Ev3Constants.Opcode.AND16, Ev3Constants.Opcode.KEEP_ALIVE, 0, 0, 1, Ev3Constants.Opcode.JR_GT8, Ev3Constants.Opcode.WRITE32, 15, 19, 32, 0, 0, 1, Ev3Constants.Opcode.JR_LT16, Ev3Constants.Opcode.BP0, Ev3Constants.Opcode.UI_DRAW, 13, Ev3Constants.Opcode.WRITE32, Ev3Constants.Opcode.JR_NEQ16, 24, -96, 0, Ev3Constants.Opcode.INIT_BYTES, -65, 28, Ev3Constants.Opcode.MOVE8_16, Ev3Constants.Opcode.ARRAY_READ, 39, Ev3Constants.Opcode.SELECT16, Ev3Constants.Opcode.JR_GTEQ8};
    private Format A;
    private MediaFormat B;
    private boolean C;
    private float D;
    private ArrayDeque E;
    private DecoderInitializationException F;
    private MediaCodecInfo G;
    private int H;
    private boolean I;
    private boolean J;
    private boolean K;
    private boolean L;
    private boolean M;
    private boolean N;
    private boolean O;
    private boolean P;
    private boolean Q;
    private boolean R;
    private C2Mp3TimestampTracker S;
    private long T;
    private int U;
    private int V;
    private ByteBuffer W;
    private boolean X;
    private boolean Y;
    private boolean Z;
    private boolean aa;
    private boolean ab;
    private boolean ac;
    private int ad;
    private int ae;
    private int af;
    private boolean ag;
    private boolean ah;
    private boolean ai;
    private long aj;
    private long ak;
    private boolean al;
    private boolean am;
    private boolean an;
    private boolean ao;
    private ExoPlaybackException ap;
    private long aq;
    private long ar;
    private int as;
    protected DecoderCounters b;
    private final MediaCodecAdapter.Factory c;
    private final MediaCodecSelector d;
    private final boolean e = false;
    private final float f;
    private final DecoderInputBuffer g;
    private final DecoderInputBuffer h;
    private final DecoderInputBuffer i;
    private final BatchBuffer j;
    private final TimedValueQueue k;
    private final ArrayList l;
    private final MediaCodec.BufferInfo m;
    private final long[] n;
    private final long[] o;
    private final long[] p;
    private Format q;
    private Format r;
    private DrmSession s;
    private DrmSession t;
    private MediaCrypto u;
    private boolean v;
    private long w;
    private float x;
    private float y;
    private MediaCodecAdapter z;

    public class DecoderInitializationException extends Exception {
        private String a;
        private boolean b;
        private MediaCodecInfo c;
        private String d;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public DecoderInitializationException(com.google.android.exoplayer2.Format r9, java.lang.Throwable r10, boolean r11, int r12) {
            /*
                r8 = this;
                java.lang.String r0 = java.lang.String.valueOf(r9)
                java.lang.String r1 = java.lang.String.valueOf(r0)
                int r1 = r1.length()
                int r1 = r1 + 36
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>(r1)
                java.lang.String r1 = "Decoder init failed: ["
                java.lang.StringBuilder r1 = r2.append(r1)
                java.lang.StringBuilder r1 = r1.append(r12)
                java.lang.String r2 = "], "
                java.lang.StringBuilder r1 = r1.append(r2)
                java.lang.StringBuilder r0 = r1.append(r0)
                java.lang.String r2 = r0.toString()
                java.lang.String r4 = r9.l
                r6 = 0
                if (r12 >= 0) goto L_0x0033
                java.lang.String r9 = "neg_"
                goto L_0x0035
            L_0x0033:
                java.lang.String r9 = ""
            L_0x0035:
                int r12 = java.lang.Math.abs(r12)
                java.lang.String r0 = java.lang.String.valueOf(r9)
                int r0 = r0.length()
                int r0 = r0 + 71
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>(r0)
                java.lang.String r0 = "com.google.android.exoplayer2.mediacodec.MediaCodecRenderer_"
                java.lang.StringBuilder r0 = r1.append(r0)
                java.lang.StringBuilder r9 = r0.append(r9)
                java.lang.StringBuilder r9 = r9.append(r12)
                java.lang.String r7 = r9.toString()
                r1 = r8
                r3 = r10
                r5 = r11
                r1.<init>(r2, r3, r4, r5, r6, r7)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.mediacodec.MediaCodecRenderer.DecoderInitializationException.<init>(com.google.android.exoplayer2.Format, java.lang.Throwable, boolean, int):void");
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public DecoderInitializationException(com.google.android.exoplayer2.Format r9, java.lang.Throwable r10, boolean r11, com.google.android.exoplayer2.mediacodec.MediaCodecInfo r12) {
            /*
                r8 = this;
                java.lang.String r0 = r12.a
                java.lang.String r1 = java.lang.String.valueOf(r9)
                java.lang.String r2 = java.lang.String.valueOf(r0)
                int r2 = r2.length()
                int r2 = r2 + 23
                java.lang.String r3 = java.lang.String.valueOf(r1)
                int r3 = r3.length()
                int r2 = r2 + r3
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>(r2)
                java.lang.String r2 = "Decoder init failed: "
                java.lang.StringBuilder r2 = r3.append(r2)
                java.lang.StringBuilder r0 = r2.append(r0)
                java.lang.String r2 = ", "
                java.lang.StringBuilder r0 = r0.append(r2)
                java.lang.StringBuilder r0 = r0.append(r1)
                java.lang.String r2 = r0.toString()
                java.lang.String r4 = r9.l
                int r9 = com.google.android.exoplayer2.util.Util.a
                r0 = 21
                r1 = 0
                if (r9 < r0) goto L_0x004c
                boolean r9 = r10 instanceof android.media.MediaCodec.CodecException
                if (r9 == 0) goto L_0x004c
                r9 = r10
                android.media.MediaCodec$CodecException r9 = (android.media.MediaCodec.CodecException) r9
                java.lang.String r9 = r9.getDiagnosticInfo()
                r7 = r9
                goto L_0x004d
            L_0x004c:
                r7 = r1
            L_0x004d:
                r1 = r8
                r3 = r10
                r5 = r11
                r6 = r12
                r1.<init>(r2, r3, r4, r5, r6, r7)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.mediacodec.MediaCodecRenderer.DecoderInitializationException.<init>(com.google.android.exoplayer2.Format, java.lang.Throwable, boolean, com.google.android.exoplayer2.mediacodec.MediaCodecInfo):void");
        }

        private DecoderInitializationException(String str, Throwable th, String str2, boolean z, MediaCodecInfo mediaCodecInfo, String str3) {
            super(str, th);
            this.a = str2;
            this.b = z;
            this.c = mediaCodecInfo;
            this.d = str3;
        }

        static /* synthetic */ DecoderInitializationException a(DecoderInitializationException decoderInitializationException) {
            return new DecoderInitializationException(decoderInitializationException.getMessage(), decoderInitializationException.getCause(), decoderInitializationException.a, decoderInitializationException.b, decoderInitializationException.c, decoderInitializationException.d);
        }
    }

    public MediaCodecRenderer(int i2, MediaCodecAdapter.Factory factory, MediaCodecSelector mediaCodecSelector, boolean z2, float f2) {
        super(i2);
        this.c = factory;
        this.d = (MediaCodecSelector) Assertions.b((Object) mediaCodecSelector);
        this.f = f2;
        this.g = DecoderInputBuffer.f();
        this.h = new DecoderInputBuffer(0);
        this.i = new DecoderInputBuffer(2);
        BatchBuffer batchBuffer = new BatchBuffer();
        this.j = batchBuffer;
        this.k = new TimedValueQueue();
        this.l = new ArrayList();
        this.m = new MediaCodec.BufferInfo();
        this.x = 1.0f;
        this.y = 1.0f;
        this.w = -9223372036854775807L;
        this.n = new long[10];
        this.o = new long[10];
        this.p = new long[10];
        this.aq = -9223372036854775807L;
        this.ar = -9223372036854775807L;
        batchBuffer.d(0);
        batchBuffer.c.order(ByteOrder.nativeOrder());
        this.D = -1.0f;
        this.H = 0;
        this.ad = 0;
        this.U = -1;
        this.V = -1;
        this.T = -9223372036854775807L;
        this.aj = -9223372036854775807L;
        this.ak = -9223372036854775807L;
        this.ae = 0;
        this.af = 0;
    }

    private void N() {
        this.ab = false;
        this.j.a();
        this.i.a();
        this.aa = false;
        this.Z = false;
    }

    private boolean O() {
        if (this.z == null) {
            return false;
        }
        if (this.af == 3 || this.J || ((this.K && !this.ai) || (this.L && this.ah))) {
            H();
            return true;
        }
        P();
        return false;
    }

    private void P() {
        try {
            this.z.c();
        } finally {
            J();
        }
    }

    private void Q() {
        J();
        this.ap = null;
        this.S = null;
        this.E = null;
        this.G = null;
        this.A = null;
        this.B = null;
        this.C = false;
        this.ai = false;
        this.D = -1.0f;
        this.H = 0;
        this.I = false;
        this.J = false;
        this.K = false;
        this.L = false;
        this.M = false;
        this.N = false;
        this.O = false;
        this.R = false;
        this.ac = false;
        this.ad = 0;
        this.v = false;
    }

    private boolean R() {
        return this.V >= 0;
    }

    private void S() {
        this.U = -1;
        this.h.c = null;
    }

    private void T() {
        this.V = -1;
        this.W = null;
    }

    private boolean U() {
        MediaCodecAdapter mediaCodecAdapter = this.z;
        if (mediaCodecAdapter == null || this.ae == 2 || this.al) {
            return false;
        }
        if (this.U < 0) {
            int a2 = mediaCodecAdapter.a();
            this.U = a2;
            if (a2 < 0) {
                return false;
            }
            this.h.c = this.z.a(a2);
            this.h.a();
        }
        if (this.ae == 1) {
            if (!this.R) {
                this.ah = true;
                this.z.a(this.U, 0, 0, 4);
                S();
            }
            this.ae = 2;
            return false;
        } else if (this.P) {
            this.P = false;
            this.h.c.put(a);
            this.z.a(this.U, 38, 0, 0);
            S();
            this.ag = true;
            return true;
        } else {
            if (this.ad == 1) {
                for (int i2 = 0; i2 < this.A.n.size(); i2++) {
                    this.h.c.put((byte[]) this.A.n.get(i2));
                }
                this.ad = 2;
            }
            int position = this.h.c.position();
            FormatHolder t2 = t();
            try {
                int a3 = a(t2, this.h, 0);
                if (g()) {
                    this.ak = this.aj;
                }
                if (a3 == -3) {
                    return false;
                }
                if (a3 == -5) {
                    if (this.ad == 2) {
                        this.h.a();
                        this.ad = 1;
                    }
                    a(t2);
                    return true;
                } else if (this.h.c()) {
                    if (this.ad == 2) {
                        this.h.a();
                        this.ad = 1;
                    }
                    this.al = true;
                    if (!this.ag) {
                        Y();
                        return false;
                    }
                    try {
                        if (!this.R) {
                            this.ah = true;
                            this.z.a(this.U, 0, 0, 4);
                            S();
                        }
                        return false;
                    } catch (MediaCodec.CryptoException e2) {
                        throw a((Throwable) e2, this.q);
                    }
                } else if (this.ag || this.h.d()) {
                    boolean g2 = this.h.g();
                    if (g2) {
                        CryptoInfo cryptoInfo = this.h.b;
                        if (position != 0) {
                            if (cryptoInfo.b == null) {
                                cryptoInfo.b = new int[1];
                                cryptoInfo.d.numBytesOfClearData = cryptoInfo.b;
                            }
                            int[] iArr = cryptoInfo.b;
                            iArr[0] = iArr[0] + position;
                        }
                    }
                    if (this.I && !g2) {
                        NalUnitUtil.a(this.h.c);
                        if (this.h.c.position() == 0) {
                            return true;
                        }
                        this.I = false;
                    }
                    long j2 = this.h.e;
                    C2Mp3TimestampTracker c2Mp3TimestampTracker = this.S;
                    if (c2Mp3TimestampTracker != null) {
                        Format format = this.q;
                        DecoderInputBuffer decoderInputBuffer = this.h;
                        if (!c2Mp3TimestampTracker.c) {
                            ByteBuffer byteBuffer = (ByteBuffer) Assertions.b((Object) decoderInputBuffer.c);
                            byte b2 = 0;
                            for (int i3 = 0; i3 < 4; i3++) {
                                b2 = (b2 << 8) | (byteBuffer.get(i3) & Ev3Constants.Opcode.TST);
                            }
                            int b3 = MpegAudioUtil.b(b2);
                            if (b3 == -1) {
                                c2Mp3TimestampTracker.c = true;
                                Log.c("C2Mp3TimestampTracker", "MPEG audio header is invalid.");
                            } else if (c2Mp3TimestampTracker.a == 0) {
                                c2Mp3TimestampTracker.b = decoderInputBuffer.e;
                                c2Mp3TimestampTracker.a = ((long) b3) - 529;
                                j2 = c2Mp3TimestampTracker.b;
                            } else {
                                long j3 = (c2Mp3TimestampTracker.a * 1000000) / ((long) format.x);
                                c2Mp3TimestampTracker.a += (long) b3;
                                j2 = c2Mp3TimestampTracker.b + j3;
                            }
                        }
                        j2 = decoderInputBuffer.e;
                    }
                    long j4 = j2;
                    if (this.h.d_()) {
                        this.l.add(Long.valueOf(j4));
                    }
                    if (this.an) {
                        this.k.a(j4, (Object) this.q);
                        this.an = false;
                    }
                    C2Mp3TimestampTracker c2Mp3TimestampTracker2 = this.S;
                    long j5 = this.aj;
                    this.aj = c2Mp3TimestampTracker2 != null ? Math.max(j5, this.h.e) : Math.max(j5, j4);
                    this.h.h();
                    if (this.h.e()) {
                        b(this.h);
                    }
                    a(this.h);
                    if (g2) {
                        try {
                            this.z.a(this.U, this.h.b, j4);
                        } catch (MediaCodec.CryptoException e3) {
                            throw a((Throwable) e3, this.q);
                        }
                    } else {
                        this.z.a(this.U, this.h.c.limit(), j4, 0);
                    }
                    S();
                    this.ag = true;
                    this.ad = 0;
                    this.b.c++;
                    return true;
                } else {
                    this.h.a();
                    if (this.ad == 2) {
                        this.ad = 1;
                    }
                    return true;
                }
            } catch (DecoderInputBuffer.InsufficientCapacityException e4) {
                a((Exception) e4);
                throw a((Throwable) a((Throwable) e4, this.G), this.q, false);
            }
        }
    }

    private boolean V() {
        if (!(Util.a < 23 || this.z == null || this.af == 3 || b_() == 0)) {
            float a2 = a(this.y, u());
            float f2 = this.D;
            if (f2 == a2) {
                return true;
            }
            if (a2 == -1.0f) {
                X();
                return false;
            } else if (f2 == -1.0f && a2 <= this.f) {
                return true;
            } else {
                Bundle bundle = new Bundle();
                bundle.putFloat("operating-rate", a2);
                this.z.a(bundle);
                this.D = a2;
            }
        }
        return true;
    }

    private boolean W() {
        if (this.ag) {
            this.ae = 1;
            if (this.J || this.L) {
                this.af = 3;
                return false;
            }
            this.af = 2;
        } else {
            aa();
        }
        return true;
    }

    private void X() {
        if (this.ag) {
            this.ae = 1;
            this.af = 3;
            return;
        }
        Z();
    }

    private void Y() {
        switch (this.af) {
            case 1:
                P();
                return;
            case 2:
                P();
                aa();
                return;
            case 3:
                Z();
                return;
            default:
                this.am = true;
                B();
                return;
        }
    }

    private void Z() {
        H();
        C();
    }

    /* JADX WARNING: Removed duplicated region for block: B:104:0x01f3 A[Catch:{ Exception -> 0x0341 }] */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x01f5 A[Catch:{ Exception -> 0x0341 }] */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x022c A[Catch:{ Exception -> 0x0341 }] */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x022e A[Catch:{ Exception -> 0x0341 }] */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x023d A[Catch:{ Exception -> 0x0341 }] */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x023f A[Catch:{ Exception -> 0x0341 }] */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x0294 A[Catch:{ Exception -> 0x0341 }] */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x0296 A[Catch:{ Exception -> 0x0341 }] */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x02ae A[Catch:{ Exception -> 0x0341 }] */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x02b0 A[Catch:{ Exception -> 0x0341 }] */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x02fd A[Catch:{ Exception -> 0x0341 }] */
    /* JADX WARNING: Removed duplicated region for block: B:177:0x02ff A[Catch:{ Exception -> 0x0341 }] */
    /* JADX WARNING: Removed duplicated region for block: B:179:0x0302 A[Catch:{ Exception -> 0x0341 }] */
    /* JADX WARNING: Removed duplicated region for block: B:186:0x0318 A[Catch:{ Exception -> 0x0341 }] */
    /* JADX WARNING: Removed duplicated region for block: B:189:0x0326 A[Catch:{ Exception -> 0x0341 }] */
    /* JADX WARNING: Removed duplicated region for block: B:196:0x037a  */
    /* JADX WARNING: Removed duplicated region for block: B:197:0x037d  */
    /* JADX WARNING: Removed duplicated region for block: B:207:0x038f A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:210:0x038b A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x01a2 A[Catch:{ Exception -> 0x0341 }] */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x01a4 A[Catch:{ Exception -> 0x0341 }] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x01d9 A[Catch:{ Exception -> 0x0341 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(android.media.MediaCrypto r17, boolean r18) {
        /*
            r16 = this;
            r7 = r16
            r8 = r18
            java.util.ArrayDeque r0 = r7.E
            java.lang.String r9 = "MediaCodecRenderer"
            r10 = 0
            r11 = 0
            if (r0 != 0) goto L_0x009a
            com.google.android.exoplayer2.mediacodec.MediaCodecSelector r0 = r7.d     // Catch:{ DecoderQueryException -> 0x008e }
            com.google.android.exoplayer2.Format r1 = r7.q     // Catch:{ DecoderQueryException -> 0x008e }
            java.util.List r0 = r7.a((com.google.android.exoplayer2.mediacodec.MediaCodecSelector) r0, (com.google.android.exoplayer2.Format) r1, (boolean) r8)     // Catch:{ DecoderQueryException -> 0x008e }
            boolean r1 = r0.isEmpty()     // Catch:{ DecoderQueryException -> 0x008e }
            if (r1 == 0) goto L_0x006b
            if (r8 == 0) goto L_0x006b
            com.google.android.exoplayer2.mediacodec.MediaCodecSelector r0 = r7.d     // Catch:{ DecoderQueryException -> 0x008e }
            com.google.android.exoplayer2.Format r1 = r7.q     // Catch:{ DecoderQueryException -> 0x008e }
            java.util.List r0 = r7.a((com.google.android.exoplayer2.mediacodec.MediaCodecSelector) r0, (com.google.android.exoplayer2.Format) r1, (boolean) r11)     // Catch:{ DecoderQueryException -> 0x008e }
            boolean r1 = r0.isEmpty()     // Catch:{ DecoderQueryException -> 0x008e }
            if (r1 != 0) goto L_0x006b
            com.google.android.exoplayer2.Format r1 = r7.q     // Catch:{ DecoderQueryException -> 0x008e }
            java.lang.String r1 = r1.l     // Catch:{ DecoderQueryException -> 0x008e }
            java.lang.String r2 = java.lang.String.valueOf(r0)     // Catch:{ DecoderQueryException -> 0x008e }
            java.lang.String r3 = java.lang.String.valueOf(r1)     // Catch:{ DecoderQueryException -> 0x008e }
            int r3 = r3.length()     // Catch:{ DecoderQueryException -> 0x008e }
            int r3 = r3 + 99
            java.lang.String r4 = java.lang.String.valueOf(r2)     // Catch:{ DecoderQueryException -> 0x008e }
            int r4 = r4.length()     // Catch:{ DecoderQueryException -> 0x008e }
            int r3 = r3 + r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ DecoderQueryException -> 0x008e }
            r4.<init>(r3)     // Catch:{ DecoderQueryException -> 0x008e }
            java.lang.String r3 = "Drm session requires secure decoder for "
            java.lang.StringBuilder r3 = r4.append(r3)     // Catch:{ DecoderQueryException -> 0x008e }
            java.lang.StringBuilder r1 = r3.append(r1)     // Catch:{ DecoderQueryException -> 0x008e }
            java.lang.String r3 = ", but no secure decoder available. Trying to proceed with "
            java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ DecoderQueryException -> 0x008e }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ DecoderQueryException -> 0x008e }
            java.lang.String r2 = "."
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ DecoderQueryException -> 0x008e }
            java.lang.String r1 = r1.toString()     // Catch:{ DecoderQueryException -> 0x008e }
            com.google.android.exoplayer2.util.Log.c(r9, r1)     // Catch:{ DecoderQueryException -> 0x008e }
        L_0x006b:
            java.util.ArrayDeque r1 = new java.util.ArrayDeque     // Catch:{ DecoderQueryException -> 0x008e }
            r1.<init>()     // Catch:{ DecoderQueryException -> 0x008e }
            r7.E = r1     // Catch:{ DecoderQueryException -> 0x008e }
            boolean r2 = r7.e     // Catch:{ DecoderQueryException -> 0x008e }
            if (r2 == 0) goto L_0x007a
            r1.addAll(r0)     // Catch:{ DecoderQueryException -> 0x008e }
            goto L_0x008b
        L_0x007a:
            boolean r1 = r0.isEmpty()     // Catch:{ DecoderQueryException -> 0x008e }
            if (r1 != 0) goto L_0x008b
            java.util.ArrayDeque r1 = r7.E     // Catch:{ DecoderQueryException -> 0x008e }
            java.lang.Object r0 = r0.get(r11)     // Catch:{ DecoderQueryException -> 0x008e }
            com.google.android.exoplayer2.mediacodec.MediaCodecInfo r0 = (com.google.android.exoplayer2.mediacodec.MediaCodecInfo) r0     // Catch:{ DecoderQueryException -> 0x008e }
            r1.add(r0)     // Catch:{ DecoderQueryException -> 0x008e }
        L_0x008b:
            r7.F = r10     // Catch:{ DecoderQueryException -> 0x008e }
            goto L_0x009a
        L_0x008e:
            r0 = move-exception
            com.google.android.exoplayer2.mediacodec.MediaCodecRenderer$DecoderInitializationException r1 = new com.google.android.exoplayer2.mediacodec.MediaCodecRenderer$DecoderInitializationException
            com.google.android.exoplayer2.Format r2 = r7.q
            r3 = -49998(0xffffffffffff3cb2, float:NaN)
            r1.<init>((com.google.android.exoplayer2.Format) r2, (java.lang.Throwable) r0, (boolean) r8, (int) r3)
            throw r1
        L_0x009a:
            java.util.ArrayDeque r0 = r7.E
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x0396
        L_0x00a2:
            com.google.android.exoplayer2.mediacodec.MediaCodecAdapter r0 = r7.z
            if (r0 != 0) goto L_0x0392
            java.util.ArrayDeque r0 = r7.E
            java.lang.Object r0 = r0.peekFirst()
            r12 = r0
            com.google.android.exoplayer2.mediacodec.MediaCodecInfo r12 = (com.google.android.exoplayer2.mediacodec.MediaCodecInfo) r12
            boolean r0 = r7.a((com.google.android.exoplayer2.mediacodec.MediaCodecInfo) r12)
            if (r0 != 0) goto L_0x00b6
            return
        L_0x00b6:
            java.lang.String r2 = r12.a     // Catch:{ Exception -> 0x0343 }
            int r0 = com.google.android.exoplayer2.util.Util.a     // Catch:{ Exception -> 0x0343 }
            r1 = -1082130432(0xffffffffbf800000, float:-1.0)
            r3 = 23
            if (r0 >= r3) goto L_0x00c3
            r0 = -1082130432(0xffffffffbf800000, float:-1.0)
            goto L_0x00cd
        L_0x00c3:
            float r0 = r7.y     // Catch:{ Exception -> 0x0343 }
            com.google.android.exoplayer2.Format[] r4 = r16.u()     // Catch:{ Exception -> 0x0343 }
            float r0 = r7.a((float) r0, (com.google.android.exoplayer2.Format[]) r4)     // Catch:{ Exception -> 0x0343 }
        L_0x00cd:
            float r4 = r7.f     // Catch:{ Exception -> 0x0343 }
            int r4 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r4 > 0) goto L_0x00d4
            goto L_0x00d5
        L_0x00d4:
            r1 = r0
        L_0x00d5:
            long r4 = android.os.SystemClock.elapsedRealtime()     // Catch:{ Exception -> 0x0343 }
            java.lang.String r0 = "createCodec:"
            java.lang.String r6 = java.lang.String.valueOf(r2)     // Catch:{ Exception -> 0x0343 }
            int r13 = r6.length()     // Catch:{ Exception -> 0x0343 }
            if (r13 == 0) goto L_0x00ea
            java.lang.String r0 = r0.concat(r6)     // Catch:{ Exception -> 0x0343 }
            goto L_0x00f0
        L_0x00ea:
            java.lang.String r6 = new java.lang.String     // Catch:{ Exception -> 0x0343 }
            r6.<init>(r0)     // Catch:{ Exception -> 0x0343 }
            r0 = r6
        L_0x00f0:
            com.google.android.exoplayer2.util.TraceUtil.a(r0)     // Catch:{ Exception -> 0x0343 }
            com.google.android.exoplayer2.Format r0 = r7.q     // Catch:{ Exception -> 0x0343 }
            r13 = r17
            com.google.android.exoplayer2.mediacodec.MediaCodecAdapter$Configuration r0 = r7.a(r12, r0, r13, r1)     // Catch:{ Exception -> 0x0341 }
            com.google.android.exoplayer2.mediacodec.MediaCodecAdapter$Factory r6 = r7.c     // Catch:{ Exception -> 0x0341 }
            com.google.android.exoplayer2.mediacodec.MediaCodecAdapter r0 = r6.a(r0)     // Catch:{ Exception -> 0x0341 }
            long r14 = android.os.SystemClock.elapsedRealtime()     // Catch:{ Exception -> 0x0341 }
            r7.z = r0     // Catch:{ Exception -> 0x0341 }
            r7.G = r12     // Catch:{ Exception -> 0x0341 }
            r7.D = r1     // Catch:{ Exception -> 0x0341 }
            com.google.android.exoplayer2.Format r0 = r7.q     // Catch:{ Exception -> 0x0341 }
            r7.A = r0     // Catch:{ Exception -> 0x0341 }
            int r0 = com.google.android.exoplayer2.util.Util.a     // Catch:{ Exception -> 0x0341 }
            java.lang.String r6 = "OMX.Exynos.avc.dec.secure"
            r11 = 25
            if (r0 > r11) goto L_0x0147
            boolean r0 = r6.equals(r2)     // Catch:{ Exception -> 0x0341 }
            if (r0 == 0) goto L_0x0147
            java.lang.String r0 = com.google.android.exoplayer2.util.Util.d     // Catch:{ Exception -> 0x0341 }
            java.lang.String r1 = "SM-T585"
            boolean r0 = r0.startsWith(r1)     // Catch:{ Exception -> 0x0341 }
            if (r0 != 0) goto L_0x0145
            java.lang.String r0 = com.google.android.exoplayer2.util.Util.d     // Catch:{ Exception -> 0x0341 }
            java.lang.String r1 = "SM-A510"
            boolean r0 = r0.startsWith(r1)     // Catch:{ Exception -> 0x0341 }
            if (r0 != 0) goto L_0x0145
            java.lang.String r0 = com.google.android.exoplayer2.util.Util.d     // Catch:{ Exception -> 0x0341 }
            java.lang.String r1 = "SM-A520"
            boolean r0 = r0.startsWith(r1)     // Catch:{ Exception -> 0x0341 }
            if (r0 != 0) goto L_0x0145
            java.lang.String r0 = com.google.android.exoplayer2.util.Util.d     // Catch:{ Exception -> 0x0341 }
            java.lang.String r1 = "SM-J700"
            boolean r0 = r0.startsWith(r1)     // Catch:{ Exception -> 0x0341 }
            if (r0 == 0) goto L_0x0147
        L_0x0145:
            r0 = 2
            goto L_0x0188
        L_0x0147:
            int r0 = com.google.android.exoplayer2.util.Util.a     // Catch:{ Exception -> 0x0341 }
            r1 = 24
            if (r0 >= r1) goto L_0x0187
            java.lang.String r0 = "OMX.Nvidia.h264.decode"
            boolean r0 = r0.equals(r2)     // Catch:{ Exception -> 0x0341 }
            if (r0 != 0) goto L_0x015d
            java.lang.String r0 = "OMX.Nvidia.h264.decode.secure"
            boolean r0 = r0.equals(r2)     // Catch:{ Exception -> 0x0341 }
            if (r0 == 0) goto L_0x0187
        L_0x015d:
            java.lang.String r0 = "flounder"
            java.lang.String r1 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0341 }
            boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x0341 }
            if (r0 != 0) goto L_0x0185
            java.lang.String r0 = "flounder_lte"
            java.lang.String r1 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0341 }
            boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x0341 }
            if (r0 != 0) goto L_0x0185
            java.lang.String r0 = "grouper"
            java.lang.String r1 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0341 }
            boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x0341 }
            if (r0 != 0) goto L_0x0185
            java.lang.String r0 = "tilapia"
            java.lang.String r1 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0341 }
            boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x0341 }
            if (r0 == 0) goto L_0x0187
        L_0x0185:
            r0 = 1
            goto L_0x0188
        L_0x0187:
            r0 = 0
        L_0x0188:
            r7.H = r0     // Catch:{ Exception -> 0x0341 }
            com.google.android.exoplayer2.Format r0 = r7.A     // Catch:{ Exception -> 0x0341 }
            int r1 = com.google.android.exoplayer2.util.Util.a     // Catch:{ Exception -> 0x0341 }
            r11 = 21
            if (r1 >= r11) goto L_0x01a4
            java.util.List r0 = r0.n     // Catch:{ Exception -> 0x0341 }
            boolean r0 = r0.isEmpty()     // Catch:{ Exception -> 0x0341 }
            if (r0 == 0) goto L_0x01a4
            java.lang.String r0 = "OMX.MTK.VIDEO.DECODER.AVC"
            boolean r0 = r0.equals(r2)     // Catch:{ Exception -> 0x0341 }
            if (r0 == 0) goto L_0x01a4
            r0 = 1
            goto L_0x01a5
        L_0x01a4:
            r0 = 0
        L_0x01a5:
            r7.I = r0     // Catch:{ Exception -> 0x0341 }
            int r0 = com.google.android.exoplayer2.util.Util.a     // Catch:{ Exception -> 0x0341 }
            r1 = 19
            r10 = 18
            if (r0 < r10) goto L_0x01e2
            int r0 = com.google.android.exoplayer2.util.Util.a     // Catch:{ Exception -> 0x0341 }
            if (r0 != r10) goto L_0x01c3
            java.lang.String r0 = "OMX.SEC.avc.dec"
            boolean r0 = r0.equals(r2)     // Catch:{ Exception -> 0x0341 }
            if (r0 != 0) goto L_0x01e2
            java.lang.String r0 = "OMX.SEC.avc.dec.secure"
            boolean r0 = r0.equals(r2)     // Catch:{ Exception -> 0x0341 }
            if (r0 != 0) goto L_0x01e2
        L_0x01c3:
            int r0 = com.google.android.exoplayer2.util.Util.a     // Catch:{ Exception -> 0x0341 }
            if (r0 != r1) goto L_0x01e0
            java.lang.String r0 = com.google.android.exoplayer2.util.Util.d     // Catch:{ Exception -> 0x0341 }
            java.lang.String r10 = "SM-G800"
            boolean r0 = r0.startsWith(r10)     // Catch:{ Exception -> 0x0341 }
            if (r0 == 0) goto L_0x01e0
            java.lang.String r0 = "OMX.Exynos.avc.dec"
            boolean r0 = r0.equals(r2)     // Catch:{ Exception -> 0x0341 }
            if (r0 != 0) goto L_0x01e2
            boolean r0 = r6.equals(r2)     // Catch:{ Exception -> 0x0341 }
            if (r0 == 0) goto L_0x01e0
            goto L_0x01e2
        L_0x01e0:
            r0 = 0
            goto L_0x01e3
        L_0x01e2:
            r0 = 1
        L_0x01e3:
            r7.J = r0     // Catch:{ Exception -> 0x0341 }
            int r0 = com.google.android.exoplayer2.util.Util.a     // Catch:{ Exception -> 0x0341 }
            r6 = 29
            if (r0 != r6) goto L_0x01f5
            java.lang.String r0 = "c2.android.aac.decoder"
            boolean r0 = r0.equals(r2)     // Catch:{ Exception -> 0x0341 }
            if (r0 == 0) goto L_0x01f5
            r0 = 1
            goto L_0x01f6
        L_0x01f5:
            r0 = 0
        L_0x01f6:
            r7.K = r0     // Catch:{ Exception -> 0x0341 }
            int r0 = com.google.android.exoplayer2.util.Util.a     // Catch:{ Exception -> 0x0341 }
            if (r0 > r3) goto L_0x0204
            java.lang.String r0 = "OMX.google.vorbis.decoder"
            boolean r0 = r0.equals(r2)     // Catch:{ Exception -> 0x0341 }
            if (r0 != 0) goto L_0x022c
        L_0x0204:
            int r0 = com.google.android.exoplayer2.util.Util.a     // Catch:{ Exception -> 0x0341 }
            if (r0 > r1) goto L_0x022e
            java.lang.String r0 = "hb2000"
            java.lang.String r1 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0341 }
            boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x0341 }
            if (r0 != 0) goto L_0x021c
            java.lang.String r0 = "stvm8"
            java.lang.String r1 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0341 }
            boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x0341 }
            if (r0 == 0) goto L_0x022e
        L_0x021c:
            java.lang.String r0 = "OMX.amlogic.avc.decoder.awesome"
            boolean r0 = r0.equals(r2)     // Catch:{ Exception -> 0x0341 }
            if (r0 != 0) goto L_0x022c
            java.lang.String r0 = "OMX.amlogic.avc.decoder.awesome.secure"
            boolean r0 = r0.equals(r2)     // Catch:{ Exception -> 0x0341 }
            if (r0 == 0) goto L_0x022e
        L_0x022c:
            r0 = 1
            goto L_0x022f
        L_0x022e:
            r0 = 0
        L_0x022f:
            r7.L = r0     // Catch:{ Exception -> 0x0341 }
            int r0 = com.google.android.exoplayer2.util.Util.a     // Catch:{ Exception -> 0x0341 }
            if (r0 != r11) goto L_0x023f
            java.lang.String r0 = "OMX.google.aac.decoder"
            boolean r0 = r0.equals(r2)     // Catch:{ Exception -> 0x0341 }
            if (r0 == 0) goto L_0x023f
            r0 = 1
            goto L_0x0240
        L_0x023f:
            r0 = 0
        L_0x0240:
            r7.M = r0     // Catch:{ Exception -> 0x0341 }
            int r0 = com.google.android.exoplayer2.util.Util.a     // Catch:{ Exception -> 0x0341 }
            if (r0 >= r11) goto L_0x0296
            java.lang.String r0 = "OMX.SEC.mp3.dec"
            boolean r0 = r0.equals(r2)     // Catch:{ Exception -> 0x0341 }
            if (r0 == 0) goto L_0x0296
            java.lang.String r0 = "samsung"
            java.lang.String r1 = com.google.android.exoplayer2.util.Util.c     // Catch:{ Exception -> 0x0341 }
            boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x0341 }
            if (r0 == 0) goto L_0x0296
            java.lang.String r0 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0341 }
            java.lang.String r1 = "baffin"
            boolean r0 = r0.startsWith(r1)     // Catch:{ Exception -> 0x0341 }
            if (r0 != 0) goto L_0x0294
            java.lang.String r0 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0341 }
            java.lang.String r1 = "grand"
            boolean r0 = r0.startsWith(r1)     // Catch:{ Exception -> 0x0341 }
            if (r0 != 0) goto L_0x0294
            java.lang.String r0 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0341 }
            java.lang.String r1 = "fortuna"
            boolean r0 = r0.startsWith(r1)     // Catch:{ Exception -> 0x0341 }
            if (r0 != 0) goto L_0x0294
            java.lang.String r0 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0341 }
            java.lang.String r1 = "gprimelte"
            boolean r0 = r0.startsWith(r1)     // Catch:{ Exception -> 0x0341 }
            if (r0 != 0) goto L_0x0294
            java.lang.String r0 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0341 }
            java.lang.String r1 = "j2y18lte"
            boolean r0 = r0.startsWith(r1)     // Catch:{ Exception -> 0x0341 }
            if (r0 != 0) goto L_0x0294
            java.lang.String r0 = com.google.android.exoplayer2.util.Util.b     // Catch:{ Exception -> 0x0341 }
            java.lang.String r1 = "ms01"
            boolean r0 = r0.startsWith(r1)     // Catch:{ Exception -> 0x0341 }
            if (r0 == 0) goto L_0x0296
        L_0x0294:
            r0 = 1
            goto L_0x0297
        L_0x0296:
            r0 = 0
        L_0x0297:
            r7.N = r0     // Catch:{ Exception -> 0x0341 }
            com.google.android.exoplayer2.Format r0 = r7.A     // Catch:{ Exception -> 0x0341 }
            int r1 = com.google.android.exoplayer2.util.Util.a     // Catch:{ Exception -> 0x0341 }
            r3 = 18
            if (r1 > r3) goto L_0x02b0
            int r0 = r0.w     // Catch:{ Exception -> 0x0341 }
            r1 = 1
            if (r0 != r1) goto L_0x02b0
            java.lang.String r0 = "OMX.MTK.AUDIO.DECODER.MP3"
            boolean r0 = r0.equals(r2)     // Catch:{ Exception -> 0x0341 }
            if (r0 == 0) goto L_0x02b0
            r1 = 1
            goto L_0x02b1
        L_0x02b0:
            r1 = 0
        L_0x02b1:
            r7.O = r1     // Catch:{ Exception -> 0x0341 }
            java.lang.String r0 = r12.a     // Catch:{ Exception -> 0x0341 }
            int r1 = com.google.android.exoplayer2.util.Util.a     // Catch:{ Exception -> 0x0341 }
            r3 = 25
            if (r1 > r3) goto L_0x02c3
            java.lang.String r1 = "OMX.rk.video_decoder.avc"
            boolean r1 = r1.equals(r0)     // Catch:{ Exception -> 0x0341 }
            if (r1 != 0) goto L_0x02fd
        L_0x02c3:
            int r1 = com.google.android.exoplayer2.util.Util.a     // Catch:{ Exception -> 0x0341 }
            r3 = 17
            if (r1 > r3) goto L_0x02d1
            java.lang.String r1 = "OMX.allwinner.video.decoder.avc"
            boolean r1 = r1.equals(r0)     // Catch:{ Exception -> 0x0341 }
            if (r1 != 0) goto L_0x02fd
        L_0x02d1:
            int r1 = com.google.android.exoplayer2.util.Util.a     // Catch:{ Exception -> 0x0341 }
            if (r1 > r6) goto L_0x02e5
            java.lang.String r1 = "OMX.broadcom.video_decoder.tunnel"
            boolean r1 = r1.equals(r0)     // Catch:{ Exception -> 0x0341 }
            if (r1 != 0) goto L_0x02fd
            java.lang.String r1 = "OMX.broadcom.video_decoder.tunnel.secure"
            boolean r0 = r1.equals(r0)     // Catch:{ Exception -> 0x0341 }
            if (r0 != 0) goto L_0x02fd
        L_0x02e5:
            java.lang.String r0 = "Amazon"
            java.lang.String r1 = com.google.android.exoplayer2.util.Util.c     // Catch:{ Exception -> 0x0341 }
            boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x0341 }
            if (r0 == 0) goto L_0x02ff
            java.lang.String r0 = "AFTS"
            java.lang.String r1 = com.google.android.exoplayer2.util.Util.d     // Catch:{ Exception -> 0x0341 }
            boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x0341 }
            if (r0 == 0) goto L_0x02ff
            boolean r0 = r12.e     // Catch:{ Exception -> 0x0341 }
            if (r0 == 0) goto L_0x02ff
        L_0x02fd:
            r1 = 1
            goto L_0x0300
        L_0x02ff:
            r1 = 0
        L_0x0300:
            if (r1 != 0) goto L_0x030b
            boolean r0 = r16.D()     // Catch:{ Exception -> 0x0341 }
            if (r0 == 0) goto L_0x0309
            goto L_0x030b
        L_0x0309:
            r1 = 0
            goto L_0x030c
        L_0x030b:
            r1 = 1
        L_0x030c:
            r7.R = r1     // Catch:{ Exception -> 0x0341 }
            java.lang.String r0 = "c2.android.mp3.decoder"
            java.lang.String r1 = r12.a     // Catch:{ Exception -> 0x0341 }
            boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x0341 }
            if (r0 == 0) goto L_0x031f
            com.google.android.exoplayer2.mediacodec.C2Mp3TimestampTracker r0 = new com.google.android.exoplayer2.mediacodec.C2Mp3TimestampTracker     // Catch:{ Exception -> 0x0341 }
            r0.<init>()     // Catch:{ Exception -> 0x0341 }
            r7.S = r0     // Catch:{ Exception -> 0x0341 }
        L_0x031f:
            int r0 = r16.b_()     // Catch:{ Exception -> 0x0341 }
            r1 = 2
            if (r0 != r1) goto L_0x032f
            long r0 = android.os.SystemClock.elapsedRealtime()     // Catch:{ Exception -> 0x0341 }
            r10 = 1000(0x3e8, double:4.94E-321)
            long r0 = r0 + r10
            r7.T = r0     // Catch:{ Exception -> 0x0341 }
        L_0x032f:
            com.google.android.exoplayer2.decoder.DecoderCounters r0 = r7.b     // Catch:{ Exception -> 0x0341 }
            int r1 = r0.a     // Catch:{ Exception -> 0x0341 }
            r3 = 1
            int r1 = r1 + r3
            r0.a = r1     // Catch:{ Exception -> 0x0341 }
            long r10 = r14 - r4
            r1 = r16
            r3 = r14
            r5 = r10
            r1.a((java.lang.String) r2, (long) r3, (long) r5)     // Catch:{ Exception -> 0x0341 }
            goto L_0x038b
        L_0x0341:
            r0 = move-exception
            goto L_0x0346
        L_0x0343:
            r0 = move-exception
            r13 = r17
        L_0x0346:
            java.lang.String r1 = java.lang.String.valueOf(r12)
            java.lang.String r2 = java.lang.String.valueOf(r1)
            int r2 = r2.length()
            int r2 = r2 + 30
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r2)
            java.lang.String r2 = "Failed to initialize decoder: "
            java.lang.StringBuilder r2 = r3.append(r2)
            java.lang.StringBuilder r1 = r2.append(r1)
            java.lang.String r1 = r1.toString()
            com.google.android.exoplayer2.util.Log.a(r9, r1, r0)
            java.util.ArrayDeque r1 = r7.E
            r1.removeFirst()
            com.google.android.exoplayer2.mediacodec.MediaCodecRenderer$DecoderInitializationException r1 = new com.google.android.exoplayer2.mediacodec.MediaCodecRenderer$DecoderInitializationException
            com.google.android.exoplayer2.Format r2 = r7.q
            r1.<init>((com.google.android.exoplayer2.Format) r2, (java.lang.Throwable) r0, (boolean) r8, (com.google.android.exoplayer2.mediacodec.MediaCodecInfo) r12)
            com.google.android.exoplayer2.mediacodec.MediaCodecRenderer$DecoderInitializationException r0 = r7.F
            if (r0 != 0) goto L_0x037d
            r7.F = r1
            goto L_0x0383
        L_0x037d:
            com.google.android.exoplayer2.mediacodec.MediaCodecRenderer$DecoderInitializationException r0 = com.google.android.exoplayer2.mediacodec.MediaCodecRenderer.DecoderInitializationException.a(r0)
            r7.F = r0
        L_0x0383:
            java.util.ArrayDeque r0 = r7.E
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x038f
        L_0x038b:
            r10 = 0
            r11 = 0
            goto L_0x00a2
        L_0x038f:
            com.google.android.exoplayer2.mediacodec.MediaCodecRenderer$DecoderInitializationException r0 = r7.F
            throw r0
        L_0x0392:
            r1 = r10
            r7.E = r1
            return
        L_0x0396:
            r1 = r10
            com.google.android.exoplayer2.mediacodec.MediaCodecRenderer$DecoderInitializationException r0 = new com.google.android.exoplayer2.mediacodec.MediaCodecRenderer$DecoderInitializationException
            com.google.android.exoplayer2.Format r2 = r7.q
            r3 = -49999(0xffffffffffff3cb1, float:NaN)
            r0.<init>((com.google.android.exoplayer2.Format) r2, (java.lang.Throwable) r1, (boolean) r8, (int) r3)
            goto L_0x03a3
        L_0x03a2:
            throw r0
        L_0x03a3:
            goto L_0x03a2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.mediacodec.MediaCodecRenderer.a(android.media.MediaCrypto, boolean):void");
    }

    private void a(DrmSession drmSession) {
        DrmSession$$CC.a(this.t, drmSession);
        this.t = drmSession;
    }

    private static boolean a(FrameworkMediaCrypto frameworkMediaCrypto, Format format) {
        if (frameworkMediaCrypto.d) {
            return false;
        }
        try {
            MediaCrypto mediaCrypto = new MediaCrypto(frameworkMediaCrypto.b, frameworkMediaCrypto.c);
            try {
                return mediaCrypto.requiresSecureDecoderComponent(format.l);
            } finally {
                mediaCrypto.release();
            }
        } catch (MediaCryptoException e2) {
            return true;
        }
    }

    private void aa() {
        try {
            this.u.setMediaDrmSession(c(this.t).c);
            b(this.t);
            this.ae = 0;
            this.af = 0;
        } catch (MediaCryptoException e2) {
            throw a((Throwable) e2, this.q);
        }
    }

    private void b(DrmSession drmSession) {
        DrmSession$$CC.a(this.s, drmSession);
        this.s = drmSession;
    }

    private boolean b(int i2) {
        FormatHolder t2 = t();
        this.g.a();
        int a2 = a(t2, this.g, i2 | 4);
        if (a2 == -5) {
            a(t2);
            return true;
        } else if (a2 != -4 || !this.g.c()) {
            return false;
        } else {
            this.al = true;
            Y();
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:85:0x015a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean b(long r20, long r22) {
        /*
            r19 = this;
            r15 = r19
            boolean r0 = r19.R()
            r14 = 1
            r13 = 0
            if (r0 != 0) goto L_0x0118
            boolean r0 = r15.M
            if (r0 == 0) goto L_0x0027
            boolean r0 = r15.ah
            if (r0 == 0) goto L_0x0027
            com.google.android.exoplayer2.mediacodec.MediaCodecAdapter r0 = r15.z     // Catch:{ IllegalStateException -> 0x001b }
            android.media.MediaCodec$BufferInfo r1 = r15.m     // Catch:{ IllegalStateException -> 0x001b }
            int r0 = r0.a((android.media.MediaCodec.BufferInfo) r1)     // Catch:{ IllegalStateException -> 0x001b }
            goto L_0x002f
        L_0x001b:
            r0 = move-exception
            r19.Y()
            boolean r0 = r15.am
            if (r0 == 0) goto L_0x0026
            r19.H()
        L_0x0026:
            return r13
        L_0x0027:
            com.google.android.exoplayer2.mediacodec.MediaCodecAdapter r0 = r15.z
            android.media.MediaCodec$BufferInfo r1 = r15.m
            int r0 = r0.a((android.media.MediaCodec.BufferInfo) r1)
        L_0x002f:
            if (r0 >= 0) goto L_0x0074
            r1 = -2
            if (r0 != r1) goto L_0x0063
            r15.ai = r14
            com.google.android.exoplayer2.mediacodec.MediaCodecAdapter r0 = r15.z
            android.media.MediaFormat r0 = r0.b()
            int r1 = r15.H
            if (r1 == 0) goto L_0x0055
            java.lang.String r1 = "width"
            int r1 = r0.getInteger(r1)
            r2 = 32
            if (r1 != r2) goto L_0x0055
            java.lang.String r1 = "height"
            int r1 = r0.getInteger(r1)
            if (r1 != r2) goto L_0x0055
            r15.Q = r14
            goto L_0x0062
        L_0x0055:
            boolean r1 = r15.O
            if (r1 == 0) goto L_0x005e
            java.lang.String r1 = "channel-count"
            r0.setInteger(r1, r14)
        L_0x005e:
            r15.B = r0
            r15.C = r14
        L_0x0062:
            return r14
        L_0x0063:
            boolean r0 = r15.R
            if (r0 == 0) goto L_0x0073
            boolean r0 = r15.al
            if (r0 != 0) goto L_0x0070
            int r0 = r15.ae
            r1 = 2
            if (r0 != r1) goto L_0x0073
        L_0x0070:
            r19.Y()
        L_0x0073:
            return r13
        L_0x0074:
            boolean r1 = r15.Q
            if (r1 == 0) goto L_0x0080
            r15.Q = r13
            com.google.android.exoplayer2.mediacodec.MediaCodecAdapter r1 = r15.z
            r1.a((int) r0, (boolean) r13)
            return r14
        L_0x0080:
            android.media.MediaCodec$BufferInfo r1 = r15.m
            int r1 = r1.size
            if (r1 != 0) goto L_0x0092
            android.media.MediaCodec$BufferInfo r1 = r15.m
            int r1 = r1.flags
            r1 = r1 & 4
            if (r1 == 0) goto L_0x0092
            r19.Y()
            return r13
        L_0x0092:
            r15.V = r0
            com.google.android.exoplayer2.mediacodec.MediaCodecAdapter r1 = r15.z
            java.nio.ByteBuffer r0 = r1.b(r0)
            r15.W = r0
            if (r0 == 0) goto L_0x00b3
            android.media.MediaCodec$BufferInfo r1 = r15.m
            int r1 = r1.offset
            r0.position(r1)
            java.nio.ByteBuffer r0 = r15.W
            android.media.MediaCodec$BufferInfo r1 = r15.m
            int r1 = r1.offset
            android.media.MediaCodec$BufferInfo r2 = r15.m
            int r2 = r2.size
            int r1 = r1 + r2
            r0.limit(r1)
        L_0x00b3:
            boolean r0 = r15.N
            if (r0 == 0) goto L_0x00d8
            android.media.MediaCodec$BufferInfo r0 = r15.m
            long r0 = r0.presentationTimeUs
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 != 0) goto L_0x00d8
            android.media.MediaCodec$BufferInfo r0 = r15.m
            int r0 = r0.flags
            r0 = r0 & 4
            if (r0 == 0) goto L_0x00d8
            long r0 = r15.aj
            r2 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x00d8
            android.media.MediaCodec$BufferInfo r2 = r15.m
            r2.presentationTimeUs = r0
        L_0x00d8:
            android.media.MediaCodec$BufferInfo r0 = r15.m
            long r0 = r0.presentationTimeUs
            java.util.ArrayList r2 = r15.l
            int r2 = r2.size()
            r3 = 0
        L_0x00e3:
            if (r3 >= r2) goto L_0x00ff
            java.util.ArrayList r4 = r15.l
            java.lang.Object r4 = r4.get(r3)
            java.lang.Long r4 = (java.lang.Long) r4
            long r4 = r4.longValue()
            int r6 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r6 != 0) goto L_0x00fc
            java.util.ArrayList r0 = r15.l
            r0.remove(r3)
            r0 = 1
            goto L_0x0100
        L_0x00fc:
            int r3 = r3 + 1
            goto L_0x00e3
        L_0x00ff:
            r0 = 0
        L_0x0100:
            r15.X = r0
            long r0 = r15.ak
            android.media.MediaCodec$BufferInfo r2 = r15.m
            long r2 = r2.presentationTimeUs
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 != 0) goto L_0x010e
            r0 = 1
            goto L_0x010f
        L_0x010e:
            r0 = 0
        L_0x010f:
            r15.Y = r0
            android.media.MediaCodec$BufferInfo r0 = r15.m
            long r0 = r0.presentationTimeUs
            r15.c((long) r0)
        L_0x0118:
            boolean r0 = r15.M
            if (r0 == 0) goto L_0x015e
            boolean r0 = r15.ah
            if (r0 == 0) goto L_0x015e
            com.google.android.exoplayer2.mediacodec.MediaCodecAdapter r6 = r15.z     // Catch:{ IllegalStateException -> 0x014e }
            java.nio.ByteBuffer r7 = r15.W     // Catch:{ IllegalStateException -> 0x014e }
            int r8 = r15.V     // Catch:{ IllegalStateException -> 0x014e }
            android.media.MediaCodec$BufferInfo r0 = r15.m     // Catch:{ IllegalStateException -> 0x014e }
            int r9 = r0.flags     // Catch:{ IllegalStateException -> 0x014e }
            r10 = 1
            android.media.MediaCodec$BufferInfo r0 = r15.m     // Catch:{ IllegalStateException -> 0x014e }
            long r11 = r0.presentationTimeUs     // Catch:{ IllegalStateException -> 0x014e }
            boolean r0 = r15.X     // Catch:{ IllegalStateException -> 0x014e }
            boolean r4 = r15.Y     // Catch:{ IllegalStateException -> 0x014e }
            com.google.android.exoplayer2.Format r5 = r15.r     // Catch:{ IllegalStateException -> 0x014e }
            r1 = r19
            r2 = r20
            r16 = r4
            r17 = r5
            r4 = r22
            r18 = 0
            r13 = r0
            r0 = 1
            r14 = r16
            r15 = r17
            boolean r1 = r1.a(r2, r4, r6, r7, r8, r9, r10, r11, r13, r14, r15)     // Catch:{ IllegalStateException -> 0x014c }
            goto L_0x0184
        L_0x014c:
            r0 = move-exception
            goto L_0x0151
        L_0x014e:
            r0 = move-exception
            r18 = 0
        L_0x0151:
            r19.Y()
            r15 = r19
            boolean r0 = r15.am
            if (r0 == 0) goto L_0x015d
            r19.H()
        L_0x015d:
            return r18
        L_0x015e:
            r0 = 1
            r18 = 0
            com.google.android.exoplayer2.mediacodec.MediaCodecAdapter r6 = r15.z
            java.nio.ByteBuffer r7 = r15.W
            int r8 = r15.V
            android.media.MediaCodec$BufferInfo r1 = r15.m
            int r9 = r1.flags
            r10 = 1
            android.media.MediaCodec$BufferInfo r1 = r15.m
            long r11 = r1.presentationTimeUs
            boolean r13 = r15.X
            boolean r14 = r15.Y
            com.google.android.exoplayer2.Format r4 = r15.r
            r1 = r19
            r2 = r20
            r16 = r4
            r4 = r22
            r15 = r16
            boolean r1 = r1.a(r2, r4, r6, r7, r8, r9, r10, r11, r13, r14, r15)
        L_0x0184:
            if (r1 == 0) goto L_0x01a4
            r1 = r19
            android.media.MediaCodec$BufferInfo r2 = r1.m
            long r2 = r2.presentationTimeUs
            r1.d(r2)
            android.media.MediaCodec$BufferInfo r2 = r1.m
            int r2 = r2.flags
            r2 = r2 & 4
            if (r2 == 0) goto L_0x0199
            r14 = 1
            goto L_0x019a
        L_0x0199:
            r14 = 0
        L_0x019a:
            r19.T()
            if (r14 != 0) goto L_0x01a0
            return r0
        L_0x01a0:
            r19.Y()
            goto L_0x01a6
        L_0x01a4:
            r1 = r19
        L_0x01a6:
            return r18
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.mediacodec.MediaCodecRenderer.b(long, long):boolean");
    }

    private FrameworkMediaCrypto c(DrmSession drmSession) {
        ExoMediaCrypto f2 = drmSession.f();
        if (f2 == null || (f2 instanceof FrameworkMediaCrypto)) {
            return (FrameworkMediaCrypto) f2;
        }
        String valueOf = String.valueOf(f2);
        throw a((Throwable) new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 42).append("Expecting FrameworkMediaCrypto but found: ").append(valueOf).toString()), this.q);
    }

    protected static boolean c(Format format) {
        return format.C == null || FrameworkMediaCrypto.class.equals(format.C);
    }

    private boolean e(long j2) {
        return this.w == -9223372036854775807L || SystemClock.elapsedRealtime() - j2 < this.w;
    }

    public void A() {
    }

    /* access modifiers changed from: protected */
    public void B() {
    }

    /* access modifiers changed from: protected */
    public final void C() {
        Format format;
        if (this.z == null && !this.Z && (format = this.q) != null) {
            if (this.t != null || !b(format)) {
                b(this.t);
                String str = this.q.l;
                DrmSession drmSession = this.s;
                if (drmSession != null) {
                    if (this.u == null) {
                        FrameworkMediaCrypto c2 = c(drmSession);
                        if (c2 != null) {
                            try {
                                this.u = new MediaCrypto(c2.b, c2.c);
                                this.v = !c2.d && this.u.requiresSecureDecoderComponent(str);
                            } catch (MediaCryptoException e2) {
                                throw a((Throwable) e2, this.q);
                            }
                        } else if (this.s.d() == null) {
                            return;
                        }
                    }
                    if (FrameworkMediaCrypto.a) {
                        int b2 = this.s.b();
                        if (b2 == 1) {
                            throw a((Throwable) this.s.d(), this.q);
                        } else if (b2 != 4) {
                            return;
                        }
                    }
                }
                try {
                    a(this.u, this.v);
                } catch (DecoderInitializationException e3) {
                    throw a((Throwable) e3, this.q);
                }
            } else {
                Format format2 = this.q;
                N();
                String str2 = format2.l;
                if ("audio/mp4a-latm".equals(str2) || "audio/mpeg".equals(str2) || "audio/opus".equals(str2)) {
                    this.j.e(32);
                } else {
                    this.j.e(1);
                }
                this.Z = true;
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean D() {
        return false;
    }

    /* access modifiers changed from: protected */
    public final MediaCodecAdapter E() {
        return this.z;
    }

    /* access modifiers changed from: protected */
    public final MediaFormat F() {
        return this.B;
    }

    /* access modifiers changed from: protected */
    public final MediaCodecInfo G() {
        return this.G;
    }

    /* access modifiers changed from: protected */
    public final void H() {
        try {
            MediaCodecAdapter mediaCodecAdapter = this.z;
            if (mediaCodecAdapter != null) {
                mediaCodecAdapter.d();
                this.b.b++;
                a(this.G.a);
            }
            this.z = null;
            try {
                MediaCrypto mediaCrypto = this.u;
                if (mediaCrypto != null) {
                    mediaCrypto.release();
                }
            } finally {
                this.u = null;
                b((DrmSession) null);
                Q();
            }
        } catch (Throwable th) {
            this.z = null;
            MediaCrypto mediaCrypto2 = this.u;
            if (mediaCrypto2 != null) {
                mediaCrypto2.release();
            }
            throw th;
        } finally {
            this.u = null;
            b((DrmSession) null);
            Q();
        }
    }

    /* access modifiers changed from: protected */
    public final boolean I() {
        boolean O2 = O();
        if (O2) {
            C();
        }
        return O2;
    }

    public void J() {
        S();
        T();
        this.T = -9223372036854775807L;
        this.ah = false;
        this.ag = false;
        this.P = false;
        this.Q = false;
        this.X = false;
        this.Y = false;
        this.l.clear();
        this.aj = -9223372036854775807L;
        this.ak = -9223372036854775807L;
        C2Mp3TimestampTracker c2Mp3TimestampTracker = this.S;
        if (c2Mp3TimestampTracker != null) {
            c2Mp3TimestampTracker.a = 0;
            c2Mp3TimestampTracker.b = 0;
            c2Mp3TimestampTracker.c = false;
        }
        this.ae = 0;
        this.af = 0;
        this.ad = this.ac ? 1 : 0;
    }

    /* access modifiers changed from: protected */
    public final float K() {
        return this.x;
    }

    public final void L() {
        this.ao = true;
    }

    /* access modifiers changed from: protected */
    public final long M() {
        return this.ar;
    }

    /* access modifiers changed from: protected */
    public float a(float f2, Format[] formatArr) {
        return -1.0f;
    }

    public final int a(Format format) {
        try {
            return a(this.d, format);
        } catch (MediaCodecUtil.DecoderQueryException e2) {
            throw a((Throwable) e2, format);
        }
    }

    /* access modifiers changed from: protected */
    public abstract int a(MediaCodecSelector mediaCodecSelector, Format format);

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0059, code lost:
        r2 = c(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00b6, code lost:
        if (W() == false) goto L_0x00f8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00e2, code lost:
        if (W() == false) goto L_0x00f8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00eb, code lost:
        r7 = 16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x00f6, code lost:
        if (W() == false) goto L_0x00f8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x00f8, code lost:
        r7 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x010f, code lost:
        if (r0 == false) goto L_0x00f8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0117, code lost:
        if (r3.a == 0) goto L_0x012b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x011b, code lost:
        if (r11.z != r12) goto L_0x0121;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x011f, code lost:
        if (r11.af != 3) goto L_0x012b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x012a, code lost:
        return new com.google.android.exoplayer2.decoder.DecoderReuseEvaluation(r1.a, r4, r5, 0, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x012b, code lost:
        return r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.exoplayer2.decoder.DecoderReuseEvaluation a(com.google.android.exoplayer2.FormatHolder r12) {
        /*
            r11 = this;
            r0 = 1
            r11.an = r0
            com.google.android.exoplayer2.Format r1 = r12.b
            java.lang.Object r1 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r1)
            r5 = r1
            com.google.android.exoplayer2.Format r5 = (com.google.android.exoplayer2.Format) r5
            java.lang.String r1 = r5.l
            if (r1 == 0) goto L_0x012c
            com.google.android.exoplayer2.drm.DrmSession r12 = r12.a
            r11.a((com.google.android.exoplayer2.drm.DrmSession) r12)
            r11.q = r5
            boolean r12 = r11.Z
            r1 = 0
            if (r12 == 0) goto L_0x001f
            r11.ab = r0
            return r1
        L_0x001f:
            com.google.android.exoplayer2.mediacodec.MediaCodecAdapter r12 = r11.z
            if (r12 != 0) goto L_0x0029
            r11.E = r1
            r11.C()
            return r1
        L_0x0029:
            com.google.android.exoplayer2.mediacodec.MediaCodecInfo r1 = r11.G
            com.google.android.exoplayer2.Format r4 = r11.A
            com.google.android.exoplayer2.drm.DrmSession r2 = r11.s
            com.google.android.exoplayer2.drm.DrmSession r3 = r11.t
            r6 = 23
            r7 = 0
            if (r2 == r3) goto L_0x006c
            if (r3 == 0) goto L_0x006a
            if (r2 != 0) goto L_0x003b
            goto L_0x006a
        L_0x003b:
            int r8 = com.google.android.exoplayer2.util.Util.a
            if (r8 >= r6) goto L_0x0040
            goto L_0x006a
        L_0x0040:
            java.util.UUID r8 = com.google.android.exoplayer2.C.e
            java.util.UUID r2 = r2.e()
            boolean r2 = r8.equals(r2)
            if (r2 != 0) goto L_0x006a
            java.util.UUID r2 = com.google.android.exoplayer2.C.e
            java.util.UUID r8 = r3.e()
            boolean r2 = r2.equals(r8)
            if (r2 == 0) goto L_0x0059
            goto L_0x006a
        L_0x0059:
            com.google.android.exoplayer2.drm.FrameworkMediaCrypto r2 = r11.c((com.google.android.exoplayer2.drm.DrmSession) r3)
            if (r2 != 0) goto L_0x0060
            goto L_0x006a
        L_0x0060:
            boolean r3 = r1.e
            if (r3 != 0) goto L_0x006c
            boolean r2 = a((com.google.android.exoplayer2.drm.FrameworkMediaCrypto) r2, (com.google.android.exoplayer2.Format) r5)
            if (r2 == 0) goto L_0x006c
        L_0x006a:
            r2 = 1
            goto L_0x006d
        L_0x006c:
            r2 = 0
        L_0x006d:
            if (r2 == 0) goto L_0x007e
            r11.X()
            com.google.android.exoplayer2.decoder.DecoderReuseEvaluation r12 = new com.google.android.exoplayer2.decoder.DecoderReuseEvaluation
            java.lang.String r3 = r1.a
            r6 = 0
            r7 = 128(0x80, float:1.794E-43)
            r2 = r12
            r2.<init>(r3, r4, r5, r6, r7)
            return r12
        L_0x007e:
            com.google.android.exoplayer2.drm.DrmSession r2 = r11.t
            com.google.android.exoplayer2.drm.DrmSession r3 = r11.s
            if (r2 == r3) goto L_0x0086
            r2 = 1
            goto L_0x0087
        L_0x0086:
            r2 = 0
        L_0x0087:
            if (r2 == 0) goto L_0x0090
            int r3 = com.google.android.exoplayer2.util.Util.a
            if (r3 < r6) goto L_0x008e
            goto L_0x0090
        L_0x008e:
            r3 = 0
            goto L_0x0091
        L_0x0090:
            r3 = 1
        L_0x0091:
            com.google.android.exoplayer2.util.Assertions.b((boolean) r3)
            com.google.android.exoplayer2.decoder.DecoderReuseEvaluation r3 = r11.a((com.google.android.exoplayer2.mediacodec.MediaCodecInfo) r1, (com.google.android.exoplayer2.Format) r4, (com.google.android.exoplayer2.Format) r5)
            int r6 = r3.a
            r8 = 3
            r9 = 16
            r10 = 2
            switch(r6) {
                case 0: goto L_0x0112;
                case 1: goto L_0x00e5;
                case 2: goto L_0x00b9;
                case 3: goto L_0x00a7;
                default: goto L_0x00a1;
            }
        L_0x00a1:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            r12.<init>()
            throw r12
        L_0x00a7:
            boolean r0 = r11.V()
            if (r0 != 0) goto L_0x00ae
            goto L_0x00eb
        L_0x00ae:
            r11.A = r5
            if (r2 == 0) goto L_0x0115
            boolean r0 = r11.W()
            if (r0 != 0) goto L_0x0115
            goto L_0x00f8
        L_0x00b9:
            boolean r6 = r11.V()
            if (r6 != 0) goto L_0x00c0
            goto L_0x00eb
        L_0x00c0:
            r11.ac = r0
            r11.ad = r0
            int r6 = r11.H
            if (r6 == r10) goto L_0x00d8
            if (r6 != r0) goto L_0x00d7
            int r6 = r5.width
            int r9 = r4.width
            if (r6 != r9) goto L_0x00d7
            int r6 = r5.height
            int r9 = r4.height
            if (r6 != r9) goto L_0x00d7
            goto L_0x00d8
        L_0x00d7:
            r0 = 0
        L_0x00d8:
            r11.P = r0
            r11.A = r5
            if (r2 == 0) goto L_0x0115
            boolean r0 = r11.W()
            if (r0 != 0) goto L_0x0115
            goto L_0x00f8
        L_0x00e5:
            boolean r6 = r11.V()
            if (r6 != 0) goto L_0x00ee
        L_0x00eb:
            r7 = 16
            goto L_0x0115
        L_0x00ee:
            r11.A = r5
            if (r2 == 0) goto L_0x00fa
            boolean r0 = r11.W()
            if (r0 != 0) goto L_0x0115
        L_0x00f8:
            r7 = 2
            goto L_0x0115
        L_0x00fa:
            boolean r2 = r11.ag
            if (r2 == 0) goto L_0x010f
            r11.ae = r0
            boolean r2 = r11.J
            if (r2 != 0) goto L_0x010c
            boolean r2 = r11.L
            if (r2 == 0) goto L_0x0109
            goto L_0x010c
        L_0x0109:
            r11.af = r0
            goto L_0x010f
        L_0x010c:
            r11.af = r8
            r0 = 0
        L_0x010f:
            if (r0 != 0) goto L_0x0115
            goto L_0x00f8
        L_0x0112:
            r11.X()
        L_0x0115:
            int r0 = r3.a
            if (r0 == 0) goto L_0x012b
            com.google.android.exoplayer2.mediacodec.MediaCodecAdapter r0 = r11.z
            if (r0 != r12) goto L_0x0121
            int r12 = r11.af
            if (r12 != r8) goto L_0x012b
        L_0x0121:
            com.google.android.exoplayer2.decoder.DecoderReuseEvaluation r12 = new com.google.android.exoplayer2.decoder.DecoderReuseEvaluation
            java.lang.String r3 = r1.a
            r6 = 0
            r2 = r12
            r2.<init>(r3, r4, r5, r6, r7)
            return r12
        L_0x012b:
            return r3
        L_0x012c:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            r12.<init>()
            com.google.android.exoplayer2.ExoPlaybackException r12 = r11.a((java.lang.Throwable) r12, (com.google.android.exoplayer2.Format) r5)
            goto L_0x0137
        L_0x0136:
            throw r12
        L_0x0137:
            goto L_0x0136
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.mediacodec.MediaCodecRenderer.a(com.google.android.exoplayer2.FormatHolder):com.google.android.exoplayer2.decoder.DecoderReuseEvaluation");
    }

    /* access modifiers changed from: protected */
    public DecoderReuseEvaluation a(MediaCodecInfo mediaCodecInfo, Format format, Format format2) {
        return new DecoderReuseEvaluation(mediaCodecInfo.a, format, format2, 0, 1);
    }

    /* access modifiers changed from: protected */
    public abstract MediaCodecAdapter.Configuration a(MediaCodecInfo mediaCodecInfo, Format format, MediaCrypto mediaCrypto, float f2);

    /* access modifiers changed from: protected */
    public MediaCodecDecoderException a(Throwable th, MediaCodecInfo mediaCodecInfo) {
        return new MediaCodecDecoderException(th, mediaCodecInfo);
    }

    /* access modifiers changed from: protected */
    public abstract List a(MediaCodecSelector mediaCodecSelector, Format format, boolean z2);

    public void a(float f2, float f3) {
        this.x = f2;
        this.y = f3;
        V();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00cc, code lost:
        if (r1.Z != false) goto L_0x00ce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00f2, code lost:
        r5 = null;
     */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x016f A[Catch:{ IllegalStateException -> 0x019a }, LOOP:2: B:102:0x016f->B:105:0x0179, LOOP_START] */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x017b A[Catch:{ IllegalStateException -> 0x019a }, LOOP:3: B:106:0x017b->B:109:0x0185, LOOP_START] */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x01c5  */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x01e0  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x01f0  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x014d A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x009a A[SYNTHETIC, Splitter:B:41:0x009a] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x009e A[Catch:{ IllegalStateException -> 0x015b }] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0151 A[Catch:{ IllegalStateException -> 0x019a }, LOOP:0: B:19:0x0032->B:92:0x0151, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(long r21, long r23) {
        /*
            r20 = this;
            r15 = r20
            boolean r0 = r15.ao
            r14 = 0
            if (r0 == 0) goto L_0x000c
            r15.ao = r14
            r20.Y()
        L_0x000c:
            com.google.android.exoplayer2.ExoPlaybackException r0 = r15.ap
            r13 = 0
            if (r0 != 0) goto L_0x01f1
            r11 = 1
            boolean r0 = r15.am     // Catch:{ IllegalStateException -> 0x019c }
            if (r0 == 0) goto L_0x001a
            r20.B()     // Catch:{ IllegalStateException -> 0x019c }
            return
        L_0x001a:
            com.google.android.exoplayer2.Format r0 = r15.q     // Catch:{ IllegalStateException -> 0x019c }
            if (r0 != 0) goto L_0x0026
            r0 = 2
            boolean r0 = r15.b((int) r0)     // Catch:{ IllegalStateException -> 0x019c }
            if (r0 != 0) goto L_0x0026
            return
        L_0x0026:
            r20.C()     // Catch:{ IllegalStateException -> 0x019c }
            boolean r0 = r15.Z     // Catch:{ IllegalStateException -> 0x019c }
            if (r0 == 0) goto L_0x015f
            java.lang.String r0 = "bypassRender"
            com.google.android.exoplayer2.util.TraceUtil.a(r0)     // Catch:{ IllegalStateException -> 0x019c }
        L_0x0032:
            boolean r0 = r15.am     // Catch:{ IllegalStateException -> 0x019c }
            if (r0 != 0) goto L_0x0038
            r0 = 1
            goto L_0x0039
        L_0x0038:
            r0 = 0
        L_0x0039:
            com.google.android.exoplayer2.util.Assertions.b((boolean) r0)     // Catch:{ IllegalStateException -> 0x019c }
            com.google.android.exoplayer2.mediacodec.BatchBuffer r0 = r15.j     // Catch:{ IllegalStateException -> 0x019c }
            boolean r0 = r0.i()     // Catch:{ IllegalStateException -> 0x019c }
            if (r0 == 0) goto L_0x0094
            r6 = 0
            com.google.android.exoplayer2.mediacodec.BatchBuffer r0 = r15.j     // Catch:{ IllegalStateException -> 0x019c }
            java.nio.ByteBuffer r7 = r0.c     // Catch:{ IllegalStateException -> 0x019c }
            int r8 = r15.V     // Catch:{ IllegalStateException -> 0x019c }
            r9 = 0
            com.google.android.exoplayer2.mediacodec.BatchBuffer r0 = r15.j     // Catch:{ IllegalStateException -> 0x019c }
            int r10 = r0.h     // Catch:{ IllegalStateException -> 0x019c }
            com.google.android.exoplayer2.mediacodec.BatchBuffer r0 = r15.j     // Catch:{ IllegalStateException -> 0x019c }
            long r4 = r0.e     // Catch:{ IllegalStateException -> 0x019c }
            com.google.android.exoplayer2.mediacodec.BatchBuffer r0 = r15.j     // Catch:{ IllegalStateException -> 0x019c }
            boolean r0 = r0.d_()     // Catch:{ IllegalStateException -> 0x019c }
            com.google.android.exoplayer2.mediacodec.BatchBuffer r1 = r15.j     // Catch:{ IllegalStateException -> 0x019c }
            boolean r16 = r1.c()     // Catch:{ IllegalStateException -> 0x019c }
            com.google.android.exoplayer2.Format r12 = r15.r     // Catch:{ IllegalStateException -> 0x019c }
            r1 = r20
            r2 = r21
            r17 = r4
            r4 = r23
            r19 = r12
            r11 = r17
            r13 = r0
            r14 = r16
            r15 = r19
            boolean r0 = r1.a(r2, r4, r6, r7, r8, r9, r10, r11, r13, r14, r15)     // Catch:{ IllegalStateException -> 0x008f }
            if (r0 == 0) goto L_0x0088
            r1 = r20
            com.google.android.exoplayer2.mediacodec.BatchBuffer r0 = r1.j     // Catch:{ IllegalStateException -> 0x015d }
            long r2 = r0.g     // Catch:{ IllegalStateException -> 0x015d }
            r1.d(r2)     // Catch:{ IllegalStateException -> 0x015d }
            com.google.android.exoplayer2.mediacodec.BatchBuffer r0 = r1.j     // Catch:{ IllegalStateException -> 0x015d }
            r0.a()     // Catch:{ IllegalStateException -> 0x015d }
            goto L_0x0095
        L_0x0088:
            r1 = r20
            r2 = 1
            r3 = 0
        L_0x008c:
            r5 = 0
            goto L_0x014a
        L_0x008f:
            r0 = move-exception
            r1 = r20
            goto L_0x019e
        L_0x0094:
            r1 = r15
        L_0x0095:
            boolean r0 = r1.al     // Catch:{ IllegalStateException -> 0x015d }
            r2 = 1
            if (r0 == 0) goto L_0x009e
            r1.am = r2     // Catch:{ IllegalStateException -> 0x015b }
            r3 = 0
            goto L_0x008c
        L_0x009e:
            boolean r0 = r1.aa     // Catch:{ IllegalStateException -> 0x015b }
            if (r0 == 0) goto L_0x00b1
            com.google.android.exoplayer2.mediacodec.BatchBuffer r0 = r1.j     // Catch:{ IllegalStateException -> 0x015b }
            com.google.android.exoplayer2.decoder.DecoderInputBuffer r3 = r1.i     // Catch:{ IllegalStateException -> 0x015b }
            boolean r0 = r0.a(r3)     // Catch:{ IllegalStateException -> 0x015b }
            com.google.android.exoplayer2.util.Assertions.b((boolean) r0)     // Catch:{ IllegalStateException -> 0x015b }
            r3 = 0
            r1.aa = r3     // Catch:{ IllegalStateException -> 0x019a }
            goto L_0x00b2
        L_0x00b1:
            r3 = 0
        L_0x00b2:
            boolean r0 = r1.ab     // Catch:{ IllegalStateException -> 0x019a }
            if (r0 == 0) goto L_0x00ce
            com.google.android.exoplayer2.mediacodec.BatchBuffer r0 = r1.j     // Catch:{ IllegalStateException -> 0x019a }
            boolean r0 = r0.i()     // Catch:{ IllegalStateException -> 0x019a }
            if (r0 == 0) goto L_0x00c2
            r5 = 0
        L_0x00bf:
            r14 = 1
            goto L_0x014b
        L_0x00c2:
            r20.N()     // Catch:{ IllegalStateException -> 0x019a }
            r1.ab = r3     // Catch:{ IllegalStateException -> 0x019a }
            r20.C()     // Catch:{ IllegalStateException -> 0x019a }
            boolean r0 = r1.Z     // Catch:{ IllegalStateException -> 0x019a }
            if (r0 == 0) goto L_0x008c
        L_0x00ce:
            boolean r0 = r1.al     // Catch:{ IllegalStateException -> 0x019a }
            if (r0 != 0) goto L_0x00d4
            r14 = 1
            goto L_0x00d5
        L_0x00d4:
            r14 = 0
        L_0x00d5:
            com.google.android.exoplayer2.util.Assertions.b((boolean) r14)     // Catch:{ IllegalStateException -> 0x019a }
            com.google.android.exoplayer2.FormatHolder r0 = r20.t()     // Catch:{ IllegalStateException -> 0x019a }
            com.google.android.exoplayer2.decoder.DecoderInputBuffer r4 = r1.i     // Catch:{ IllegalStateException -> 0x019a }
            r4.a()     // Catch:{ IllegalStateException -> 0x019a }
        L_0x00e1:
            com.google.android.exoplayer2.decoder.DecoderInputBuffer r4 = r1.i     // Catch:{ IllegalStateException -> 0x019a }
            r4.a()     // Catch:{ IllegalStateException -> 0x019a }
            com.google.android.exoplayer2.decoder.DecoderInputBuffer r4 = r1.i     // Catch:{ IllegalStateException -> 0x019a }
            int r4 = r1.a((com.google.android.exoplayer2.FormatHolder) r0, (com.google.android.exoplayer2.decoder.DecoderInputBuffer) r4, (int) r3)     // Catch:{ IllegalStateException -> 0x019a }
            switch(r4) {
                case -5: goto L_0x0127;
                case -4: goto L_0x00f4;
                case -3: goto L_0x00f2;
                default: goto L_0x00ef;
            }     // Catch:{ IllegalStateException -> 0x019a }
        L_0x00ef:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ IllegalStateException -> 0x019a }
            goto L_0x0157
        L_0x00f2:
            r5 = 0
            goto L_0x012b
        L_0x00f4:
            com.google.android.exoplayer2.decoder.DecoderInputBuffer r4 = r1.i     // Catch:{ IllegalStateException -> 0x019a }
            boolean r4 = r4.c()     // Catch:{ IllegalStateException -> 0x019a }
            if (r4 == 0) goto L_0x00ff
            r1.al = r2     // Catch:{ IllegalStateException -> 0x019a }
            goto L_0x00f2
        L_0x00ff:
            boolean r4 = r1.an     // Catch:{ IllegalStateException -> 0x019a }
            if (r4 == 0) goto L_0x0114
            com.google.android.exoplayer2.Format r4 = r1.q     // Catch:{ IllegalStateException -> 0x019a }
            java.lang.Object r4 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r4)     // Catch:{ IllegalStateException -> 0x019a }
            com.google.android.exoplayer2.Format r4 = (com.google.android.exoplayer2.Format) r4     // Catch:{ IllegalStateException -> 0x019a }
            r1.r = r4     // Catch:{ IllegalStateException -> 0x019a }
            r5 = 0
            r1.a((com.google.android.exoplayer2.Format) r4, (android.media.MediaFormat) r5)     // Catch:{ IllegalStateException -> 0x019a }
            r1.an = r3     // Catch:{ IllegalStateException -> 0x019a }
            goto L_0x0115
        L_0x0114:
            r5 = 0
        L_0x0115:
            com.google.android.exoplayer2.decoder.DecoderInputBuffer r4 = r1.i     // Catch:{ IllegalStateException -> 0x019a }
            r4.h()     // Catch:{ IllegalStateException -> 0x019a }
            com.google.android.exoplayer2.mediacodec.BatchBuffer r4 = r1.j     // Catch:{ IllegalStateException -> 0x019a }
            com.google.android.exoplayer2.decoder.DecoderInputBuffer r6 = r1.i     // Catch:{ IllegalStateException -> 0x019a }
            boolean r4 = r4.a(r6)     // Catch:{ IllegalStateException -> 0x019a }
            if (r4 != 0) goto L_0x00e1
            r1.aa = r2     // Catch:{ IllegalStateException -> 0x019a }
            goto L_0x012b
        L_0x0127:
            r5 = 0
            r1.a((com.google.android.exoplayer2.FormatHolder) r0)     // Catch:{ IllegalStateException -> 0x019a }
        L_0x012b:
            com.google.android.exoplayer2.mediacodec.BatchBuffer r0 = r1.j     // Catch:{ IllegalStateException -> 0x019a }
            boolean r0 = r0.i()     // Catch:{ IllegalStateException -> 0x019a }
            if (r0 == 0) goto L_0x0138
            com.google.android.exoplayer2.mediacodec.BatchBuffer r0 = r1.j     // Catch:{ IllegalStateException -> 0x019a }
            r0.h()     // Catch:{ IllegalStateException -> 0x019a }
        L_0x0138:
            com.google.android.exoplayer2.mediacodec.BatchBuffer r0 = r1.j     // Catch:{ IllegalStateException -> 0x019a }
            boolean r0 = r0.i()     // Catch:{ IllegalStateException -> 0x019a }
            if (r0 != 0) goto L_0x00bf
            boolean r0 = r1.al     // Catch:{ IllegalStateException -> 0x019a }
            if (r0 != 0) goto L_0x00bf
            boolean r0 = r1.ab     // Catch:{ IllegalStateException -> 0x019a }
            if (r0 == 0) goto L_0x014a
            goto L_0x00bf
        L_0x014a:
            r14 = 0
        L_0x014b:
            if (r14 != 0) goto L_0x0151
            com.google.android.exoplayer2.util.TraceUtil.a()     // Catch:{ IllegalStateException -> 0x019a }
            return
        L_0x0151:
            r15 = r1
            r13 = r5
            r11 = 1
            r14 = 0
            goto L_0x0032
        L_0x0157:
            r0.<init>()     // Catch:{ IllegalStateException -> 0x019a }
            throw r0     // Catch:{ IllegalStateException -> 0x019a }
        L_0x015b:
            r0 = move-exception
            goto L_0x019f
        L_0x015d:
            r0 = move-exception
            goto L_0x019e
        L_0x015f:
            r1 = r15
            r2 = 1
            r3 = 0
            com.google.android.exoplayer2.mediacodec.MediaCodecAdapter r0 = r1.z     // Catch:{ IllegalStateException -> 0x019a }
            if (r0 == 0) goto L_0x018b
            long r4 = android.os.SystemClock.elapsedRealtime()     // Catch:{ IllegalStateException -> 0x019a }
            java.lang.String r0 = "drainAndFeed"
            com.google.android.exoplayer2.util.TraceUtil.a(r0)     // Catch:{ IllegalStateException -> 0x019a }
        L_0x016f:
            boolean r0 = r20.b(r21, r23)     // Catch:{ IllegalStateException -> 0x019a }
            if (r0 == 0) goto L_0x017b
            boolean r0 = r1.e(r4)     // Catch:{ IllegalStateException -> 0x019a }
            if (r0 != 0) goto L_0x016f
        L_0x017b:
            boolean r0 = r20.U()     // Catch:{ IllegalStateException -> 0x019a }
            if (r0 == 0) goto L_0x0187
            boolean r0 = r1.e(r4)     // Catch:{ IllegalStateException -> 0x019a }
            if (r0 != 0) goto L_0x017b
        L_0x0187:
            com.google.android.exoplayer2.util.TraceUtil.a()     // Catch:{ IllegalStateException -> 0x019a }
            return
        L_0x018b:
            com.google.android.exoplayer2.decoder.DecoderCounters r0 = r1.b     // Catch:{ IllegalStateException -> 0x019a }
            int r4 = r0.d     // Catch:{ IllegalStateException -> 0x019a }
            int r5 = r20.b(r21)     // Catch:{ IllegalStateException -> 0x019a }
            int r4 = r4 + r5
            r0.d = r4     // Catch:{ IllegalStateException -> 0x019a }
            r1.b((int) r2)     // Catch:{ IllegalStateException -> 0x019a }
            return
        L_0x019a:
            r0 = move-exception
            goto L_0x01a0
        L_0x019c:
            r0 = move-exception
            r1 = r15
        L_0x019e:
            r2 = 1
        L_0x019f:
            r3 = 0
        L_0x01a0:
            int r4 = com.google.android.exoplayer2.util.Util.a
            r5 = 21
            if (r4 < r5) goto L_0x01ac
            boolean r4 = r0 instanceof android.media.MediaCodec.CodecException
            if (r4 == 0) goto L_0x01ac
        L_0x01aa:
            r14 = 1
            goto L_0x01c3
        L_0x01ac:
            java.lang.StackTraceElement[] r4 = r0.getStackTrace()
            int r6 = r4.length
            if (r6 <= 0) goto L_0x01c2
            r4 = r4[r3]
            java.lang.String r4 = r4.getClassName()
            java.lang.String r6 = "android.media.MediaCodec"
            boolean r4 = r4.equals(r6)
            if (r4 == 0) goto L_0x01c2
            goto L_0x01aa
        L_0x01c2:
            r14 = 0
        L_0x01c3:
            if (r14 == 0) goto L_0x01f0
            r1.a((java.lang.Exception) r0)
            int r4 = com.google.android.exoplayer2.util.Util.a
            if (r4 < r5) goto L_0x01dd
            boolean r4 = r0 instanceof android.media.MediaCodec.CodecException
            if (r4 == 0) goto L_0x01d8
            r4 = r0
            android.media.MediaCodec$CodecException r4 = (android.media.MediaCodec.CodecException) r4
            boolean r14 = r4.isRecoverable()
            goto L_0x01d9
        L_0x01d8:
            r14 = 0
        L_0x01d9:
            if (r14 == 0) goto L_0x01dd
            r14 = 1
            goto L_0x01de
        L_0x01dd:
            r14 = 0
        L_0x01de:
            if (r14 == 0) goto L_0x01e3
            r20.H()
        L_0x01e3:
            com.google.android.exoplayer2.mediacodec.MediaCodecInfo r2 = r1.G
            com.google.android.exoplayer2.mediacodec.MediaCodecDecoderException r0 = r1.a((java.lang.Throwable) r0, (com.google.android.exoplayer2.mediacodec.MediaCodecInfo) r2)
            com.google.android.exoplayer2.Format r2 = r1.q
            com.google.android.exoplayer2.ExoPlaybackException r0 = r1.a((java.lang.Throwable) r0, (com.google.android.exoplayer2.Format) r2, (boolean) r14)
            throw r0
        L_0x01f0:
            throw r0
        L_0x01f1:
            r5 = r13
            r1 = r15
            r1.ap = r5
            goto L_0x01f7
        L_0x01f6:
            throw r0
        L_0x01f7:
            goto L_0x01f6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.mediacodec.MediaCodecRenderer.a(long, long):void");
    }

    public void a(long j2, boolean z2) {
        this.al = false;
        this.am = false;
        this.ao = false;
        if (this.Z) {
            this.j.a();
            this.i.a();
            this.aa = false;
        } else {
            I();
        }
        if (this.k.b() > 0) {
            this.an = true;
        }
        this.k.a();
        int i2 = this.as;
        if (i2 != 0) {
            this.ar = this.o[i2 - 1];
            this.aq = this.n[i2 - 1];
            this.as = 0;
        }
    }

    public final void a(ExoPlaybackException exoPlaybackException) {
        this.ap = exoPlaybackException;
    }

    /* access modifiers changed from: protected */
    public void a(Format format, MediaFormat mediaFormat) {
    }

    /* access modifiers changed from: protected */
    public void a(DecoderInputBuffer decoderInputBuffer) {
    }

    /* access modifiers changed from: protected */
    public void a(Exception exc) {
    }

    /* access modifiers changed from: protected */
    public void a(String str) {
    }

    /* access modifiers changed from: protected */
    public void a(String str, long j2, long j3) {
    }

    public void a(boolean z2, boolean z3) {
        this.b = new DecoderCounters();
    }

    public final void a(Format[] formatArr, long j2, long j3) {
        boolean z2 = true;
        if (this.ar == -9223372036854775807L) {
            if (this.aq != -9223372036854775807L) {
                z2 = false;
            }
            Assertions.b(z2);
            this.aq = j2;
            this.ar = j3;
            return;
        }
        int i2 = this.as;
        if (i2 == 10) {
            Log.c("MediaCodecRenderer", new StringBuilder(65).append("Too many stream changes, so dropping offset: ").append(this.o[i2 - 1]).toString());
        } else {
            this.as = i2 + 1;
        }
        long[] jArr = this.n;
        int i3 = this.as;
        jArr[i3 - 1] = j2;
        this.o[i3 - 1] = j3;
        this.p[i3 - 1] = this.aj;
    }

    /* access modifiers changed from: protected */
    public abstract boolean a(long j2, long j3, MediaCodecAdapter mediaCodecAdapter, ByteBuffer byteBuffer, int i2, int i3, int i4, long j4, boolean z2, boolean z3, Format format);

    /* access modifiers changed from: protected */
    public boolean a(MediaCodecInfo mediaCodecInfo) {
        return true;
    }

    /* access modifiers changed from: protected */
    public void b(DecoderInputBuffer decoderInputBuffer) {
    }

    /* access modifiers changed from: protected */
    public boolean b(Format format) {
        return false;
    }

    /* access modifiers changed from: protected */
    public final void c(long j2) {
        boolean z2;
        Format format = (Format) this.k.a(j2);
        if (format == null && this.C) {
            format = (Format) this.k.c();
        }
        if (format != null) {
            this.r = format;
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 || (this.C && this.r != null)) {
            a(this.r, this.B);
            this.C = false;
        }
    }

    public void d(long j2) {
        while (true) {
            int i2 = this.as;
            if (i2 != 0 && j2 >= this.p[0]) {
                long[] jArr = this.n;
                this.aq = jArr[0];
                this.ar = this.o[0];
                int i3 = i2 - 1;
                this.as = i3;
                System.arraycopy(jArr, 1, jArr, 0, i3);
                long[] jArr2 = this.o;
                System.arraycopy(jArr2, 1, jArr2, 0, this.as);
                long[] jArr3 = this.p;
                System.arraycopy(jArr3, 1, jArr3, 0, this.as);
                A();
            } else {
                return;
            }
        }
    }

    public final int o() {
        return 8;
    }

    public void p() {
    }

    public void q() {
    }

    public void r() {
        this.q = null;
        this.aq = -9223372036854775807L;
        this.ar = -9223372036854775807L;
        this.as = 0;
        O();
    }

    public void s() {
        try {
            N();
            H();
        } finally {
            a((DrmSession) null);
        }
    }

    public boolean y() {
        if (this.q == null) {
            return false;
        }
        if (w() || R()) {
            return true;
        }
        return this.T != -9223372036854775807L && SystemClock.elapsedRealtime() < this.T;
    }

    public boolean z() {
        return this.am;
    }
}
