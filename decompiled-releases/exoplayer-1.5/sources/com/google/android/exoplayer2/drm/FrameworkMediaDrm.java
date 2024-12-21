package com.google.android.exoplayer2.drm;

import android.media.MediaDrm;
import android.media.UnsupportedSchemeException;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.drm.ExoMediaDrm;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import java.util.Map;
import java.util.UUID;

public final class FrameworkMediaDrm implements ExoMediaDrm {
    public static final ExoMediaDrm.Provider a = FrameworkMediaDrm$$Lambda$3.a;
    private final UUID b;
    private final MediaDrm c;
    private int d = 1;

    private FrameworkMediaDrm(UUID uuid) {
        Assertions.b((Object) uuid);
        Assertions.a(!C.b.equals(uuid), (Object) "Use C.CLEARKEY_UUID instead");
        this.b = uuid;
        MediaDrm mediaDrm = new MediaDrm(c(uuid));
        this.c = mediaDrm;
        if (C.d.equals(uuid) && "ASUS_Z00AD".equals(Util.d)) {
            mediaDrm.setPropertyString("securityLevel", "L3");
        }
    }

    static final /* synthetic */ ExoMediaDrm a(UUID uuid) {
        try {
            return b(uuid);
        } catch (UnsupportedDrmException e) {
            String valueOf = String.valueOf(uuid);
            Log.d("FrameworkMediaDrm", new StringBuilder(String.valueOf(valueOf).length() + 53).append("Failed to instantiate a FrameworkMediaDrm for uuid: ").append(valueOf).append(".").toString());
            return new DummyExoMediaDrm();
        }
    }

    private static FrameworkMediaDrm b(UUID uuid) {
        try {
            return new FrameworkMediaDrm(uuid);
        } catch (UnsupportedSchemeException e) {
            throw new UnsupportedDrmException(e);
        } catch (Exception e2) {
            throw new UnsupportedDrmException(e2);
        }
    }

    private static UUID c(UUID uuid) {
        return (Util.a >= 27 || !C.c.equals(uuid)) ? uuid : C.b;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00ab, code lost:
        r1 = r6;
     */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00ca  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.exoplayer2.drm.ExoMediaDrm.KeyRequest a(byte[] r17, java.util.List r18, int r19, java.util.HashMap r20) {
        /*
            r16 = this;
            r0 = r16
            r1 = r18
            r2 = 23
            if (r1 == 0) goto L_0x01e8
            java.util.UUID r3 = r0.b
            java.util.UUID r4 = com.google.android.exoplayer2.C.d
            boolean r3 = r4.equals(r3)
            r4 = 1
            r5 = 0
            if (r3 == 0) goto L_0x00b0
            int r3 = com.google.android.exoplayer2.util.Util.a
            r6 = 28
            if (r3 < r6) goto L_0x0085
            int r3 = r18.size()
            if (r3 <= r4) goto L_0x0085
            java.lang.Object r3 = r1.get(r5)
            com.google.android.exoplayer2.drm.DrmInitData$SchemeData r3 = (com.google.android.exoplayer2.drm.DrmInitData.SchemeData) r3
            r6 = 0
            r7 = 0
        L_0x0028:
            int r8 = r18.size()
            if (r6 >= r8) goto L_0x005d
            java.lang.Object r8 = r1.get(r6)
            com.google.android.exoplayer2.drm.DrmInitData$SchemeData r8 = (com.google.android.exoplayer2.drm.DrmInitData.SchemeData) r8
            byte[] r9 = r8.d
            java.lang.Object r9 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r9)
            byte[] r9 = (byte[]) r9
            java.lang.String r10 = r8.c
            java.lang.String r11 = r3.c
            boolean r10 = com.google.android.exoplayer2.util.Util.a((java.lang.Object) r10, (java.lang.Object) r11)
            if (r10 == 0) goto L_0x005b
            java.lang.String r8 = r8.b
            java.lang.String r10 = r3.b
            boolean r8 = com.google.android.exoplayer2.util.Util.a((java.lang.Object) r8, (java.lang.Object) r10)
            if (r8 == 0) goto L_0x005b
            boolean r8 = com.google.android.exoplayer2.extractor.mp4.PsshAtomUtil.a(r9)
            if (r8 == 0) goto L_0x005b
            int r8 = r9.length
            int r7 = r7 + r8
            int r6 = r6 + 1
            goto L_0x0028
        L_0x005b:
            r6 = 0
            goto L_0x005e
        L_0x005d:
            r6 = 1
        L_0x005e:
            if (r6 == 0) goto L_0x0085
            byte[] r6 = new byte[r7]
            r7 = 0
            r8 = 0
        L_0x0064:
            int r9 = r18.size()
            if (r7 >= r9) goto L_0x0080
            java.lang.Object r9 = r1.get(r7)
            com.google.android.exoplayer2.drm.DrmInitData$SchemeData r9 = (com.google.android.exoplayer2.drm.DrmInitData.SchemeData) r9
            byte[] r9 = r9.d
            java.lang.Object r9 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r9)
            byte[] r9 = (byte[]) r9
            int r10 = r9.length
            java.lang.System.arraycopy(r9, r5, r6, r8, r10)
            int r8 = r8 + r10
            int r7 = r7 + 1
            goto L_0x0064
        L_0x0080:
            com.google.android.exoplayer2.drm.DrmInitData$SchemeData r1 = r3.a((byte[]) r6)
            goto L_0x00b6
        L_0x0085:
            r3 = 0
        L_0x0086:
            int r6 = r18.size()
            if (r3 >= r6) goto L_0x00b0
            java.lang.Object r6 = r1.get(r3)
            com.google.android.exoplayer2.drm.DrmInitData$SchemeData r6 = (com.google.android.exoplayer2.drm.DrmInitData.SchemeData) r6
            byte[] r7 = r6.d
            java.lang.Object r7 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r7)
            byte[] r7 = (byte[]) r7
            int r7 = com.google.android.exoplayer2.extractor.mp4.PsshAtomUtil.c(r7)
            int r8 = com.google.android.exoplayer2.util.Util.a
            if (r8 >= r2) goto L_0x00a5
            if (r7 != 0) goto L_0x00a5
            goto L_0x00ab
        L_0x00a5:
            int r8 = com.google.android.exoplayer2.util.Util.a
            if (r8 < r2) goto L_0x00ad
            if (r7 != r4) goto L_0x00ad
        L_0x00ab:
            r1 = r6
            goto L_0x00b6
        L_0x00ad:
            int r3 = r3 + 1
            goto L_0x0086
        L_0x00b0:
            java.lang.Object r1 = r1.get(r5)
            com.google.android.exoplayer2.drm.DrmInitData$SchemeData r1 = (com.google.android.exoplayer2.drm.DrmInitData.SchemeData) r1
        L_0x00b6:
            java.util.UUID r3 = r0.b
            byte[] r6 = r1.d
            java.lang.Object r6 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r6)
            byte[] r6 = (byte[]) r6
            java.util.UUID r7 = com.google.android.exoplayer2.C.e
            boolean r7 = r7.equals(r3)
            r8 = 26
            if (r7 == 0) goto L_0x0176
            byte[] r7 = com.google.android.exoplayer2.extractor.mp4.PsshAtomUtil.a((byte[]) r6, (java.util.UUID) r3)
            if (r7 != 0) goto L_0x00d1
            goto L_0x00d2
        L_0x00d1:
            r6 = r7
        L_0x00d2:
            java.util.UUID r7 = com.google.android.exoplayer2.C.e
            com.google.android.exoplayer2.util.ParsableByteArray r9 = new com.google.android.exoplayer2.util.ParsableByteArray
            r9.<init>((byte[]) r6)
            int r10 = r9.k()
            short r11 = r9.f()
            short r12 = r9.f()
            java.lang.String r13 = "FrameworkMediaDrm"
            if (r11 != r4) goto L_0x016d
            if (r12 == r4) goto L_0x00ed
            goto L_0x016d
        L_0x00ed:
            short r14 = r9.f()
            java.nio.charset.Charset r15 = com.dreamers.exoplayercore.repack.aC.d
            java.lang.String r9 = r9.a((int) r14, (java.nio.charset.Charset) r15)
            java.lang.String r14 = "<LA_URL>"
            boolean r14 = r9.contains(r14)
            if (r14 == 0) goto L_0x0100
            goto L_0x0172
        L_0x0100:
            java.lang.String r6 = "</DATA>"
            int r6 = r9.indexOf(r6)
            r14 = -1
            if (r6 != r14) goto L_0x010e
            java.lang.String r14 = "Could not find the </DATA> tag. Skipping LA_URL workaround."
            com.google.android.exoplayer2.util.Log.c(r13, r14)
        L_0x010e:
            java.lang.String r5 = r9.substring(r5, r6)
            java.lang.String r6 = r9.substring(r6)
            java.lang.String r9 = java.lang.String.valueOf(r5)
            int r9 = r9.length()
            int r9 = r9 + r8
            java.lang.String r13 = java.lang.String.valueOf(r6)
            int r13 = r13.length()
            int r9 = r9 + r13
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>(r9)
            java.lang.StringBuilder r5 = r13.append(r5)
            java.lang.String r9 = "<LA_URL>https://x</LA_URL>"
            java.lang.StringBuilder r5 = r5.append(r9)
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            int r10 = r10 + 52
            java.nio.ByteBuffer r6 = java.nio.ByteBuffer.allocate(r10)
            java.nio.ByteOrder r9 = java.nio.ByteOrder.LITTLE_ENDIAN
            r6.order(r9)
            r6.putInt(r10)
            short r9 = (short) r11
            r6.putShort(r9)
            short r9 = (short) r12
            r6.putShort(r9)
            int r9 = r5.length()
            int r4 = r9 << 1
            short r4 = (short) r4
            r6.putShort(r4)
            java.nio.charset.Charset r4 = com.dreamers.exoplayercore.repack.aC.d
            byte[] r4 = r5.getBytes(r4)
            r6.put(r4)
            byte[] r6 = r6.array()
            goto L_0x0172
        L_0x016d:
            java.lang.String r4 = "Unexpected record count or type. Skipping LA_URL workaround."
            com.google.android.exoplayer2.util.Log.b(r13, r4)
        L_0x0172:
            byte[] r6 = com.google.android.exoplayer2.extractor.mp4.PsshAtomUtil.a((java.util.UUID) r7, (byte[]) r6)
        L_0x0176:
            int r4 = com.google.android.exoplayer2.util.Util.a
            if (r4 >= r2) goto L_0x0182
            java.util.UUID r4 = com.google.android.exoplayer2.C.d
            boolean r4 = r4.equals(r3)
            if (r4 != 0) goto L_0x01bc
        L_0x0182:
            java.util.UUID r4 = com.google.android.exoplayer2.C.e
            boolean r4 = r4.equals(r3)
            if (r4 == 0) goto L_0x01c3
            java.lang.String r4 = "Amazon"
            java.lang.String r5 = com.google.android.exoplayer2.util.Util.c
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x01c3
            java.lang.String r4 = "AFTB"
            java.lang.String r5 = com.google.android.exoplayer2.util.Util.d
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x01bc
            java.lang.String r4 = "AFTS"
            java.lang.String r5 = com.google.android.exoplayer2.util.Util.d
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x01bc
            java.lang.String r4 = "AFTM"
            java.lang.String r5 = com.google.android.exoplayer2.util.Util.d
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x01bc
            java.lang.String r4 = "AFTT"
            java.lang.String r5 = com.google.android.exoplayer2.util.Util.d
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x01c3
        L_0x01bc:
            byte[] r3 = com.google.android.exoplayer2.extractor.mp4.PsshAtomUtil.a((byte[]) r6, (java.util.UUID) r3)
            if (r3 == 0) goto L_0x01c3
            r6 = r3
        L_0x01c3:
            java.util.UUID r3 = r0.b
            java.lang.String r4 = r1.c
            int r5 = com.google.android.exoplayer2.util.Util.a
            if (r5 >= r8) goto L_0x01e5
            java.util.UUID r5 = com.google.android.exoplayer2.C.c
            boolean r3 = r5.equals(r3)
            if (r3 == 0) goto L_0x01e5
            java.lang.String r3 = "video/mp4"
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x01e3
            java.lang.String r3 = "audio/mp4"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x01e5
        L_0x01e3:
            java.lang.String r4 = "cenc"
        L_0x01e5:
            r5 = r6
            r6 = r4
            goto L_0x01eb
        L_0x01e8:
            r1 = 0
            r5 = r1
            r6 = r5
        L_0x01eb:
            android.media.MediaDrm r3 = r0.c
            r4 = r17
            r7 = r19
            r8 = r20
            android.media.MediaDrm$KeyRequest r3 = r3.getKeyRequest(r4, r5, r6, r7, r8)
            java.util.UUID r4 = r0.b
            byte[] r5 = r3.getData()
            java.util.UUID r6 = com.google.android.exoplayer2.C.c
            boolean r4 = r6.equals(r4)
            if (r4 == 0) goto L_0x0209
            byte[] r5 = com.google.android.exoplayer2.drm.ClearKeyUtil.a((byte[]) r5)
        L_0x0209:
            java.lang.String r4 = r3.getDefaultUrl()
            java.lang.String r6 = "https://x"
            boolean r6 = r6.equals(r4)
            if (r6 == 0) goto L_0x0217
            java.lang.String r4 = ""
        L_0x0217:
            boolean r6 = android.text.TextUtils.isEmpty(r4)
            if (r6 == 0) goto L_0x0229
            if (r1 == 0) goto L_0x0229
            java.lang.String r6 = r1.b
            boolean r6 = android.text.TextUtils.isEmpty(r6)
            if (r6 != 0) goto L_0x0229
            java.lang.String r4 = r1.b
        L_0x0229:
            int r1 = com.google.android.exoplayer2.util.Util.a
            if (r1 < r2) goto L_0x0230
            r3.getRequestType()
        L_0x0230:
            com.google.android.exoplayer2.drm.ExoMediaDrm$KeyRequest r1 = new com.google.android.exoplayer2.drm.ExoMediaDrm$KeyRequest
            r1.<init>(r5, r4)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.drm.FrameworkMediaDrm.a(byte[], java.util.List, int, java.util.HashMap):com.google.android.exoplayer2.drm.ExoMediaDrm$KeyRequest");
    }

    public final void a(ExoMediaDrm.OnEventListener onEventListener) {
        this.c.setOnEventListener(new FrameworkMediaDrm$$Lambda$0(onEventListener));
    }

    public final void a(byte[] bArr) {
        this.c.closeSession(bArr);
    }

    public final byte[] a() {
        return this.c.openSession();
    }

    public final byte[] a(byte[] bArr, byte[] bArr2) {
        if (C.c.equals(this.b)) {
            bArr2 = ClearKeyUtil.b(bArr2);
        }
        return this.c.provideKeyResponse(bArr, bArr2);
    }

    public final ExoMediaDrm.ProvisionRequest b() {
        MediaDrm.ProvisionRequest provisionRequest = this.c.getProvisionRequest();
        return new ExoMediaDrm.ProvisionRequest(provisionRequest.getData(), provisionRequest.getDefaultUrl());
    }

    public final void b(byte[] bArr) {
        this.c.provideProvisionResponse(bArr);
    }

    public final void b(byte[] bArr, byte[] bArr2) {
        this.c.restoreKeys(bArr, bArr2);
    }

    public final Map c(byte[] bArr) {
        return this.c.queryKeyStatus(bArr);
    }

    public final synchronized void c() {
        int i = this.d - 1;
        this.d = i;
        if (i == 0) {
            this.c.release();
        }
    }

    public final /* synthetic */ ExoMediaCrypto d(byte[] bArr) {
        return new FrameworkMediaCrypto(c(this.b), bArr, Util.a < 21 && C.d.equals(this.b) && "L3".equals(this.c.getPropertyString("securityLevel")));
    }

    public final Class d() {
        return FrameworkMediaCrypto.class;
    }
}
