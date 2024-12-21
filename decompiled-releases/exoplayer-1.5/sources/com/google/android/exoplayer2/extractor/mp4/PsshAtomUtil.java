package com.google.android.exoplayer2.extractor.mp4;

import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.nio.ByteBuffer;
import java.util.UUID;

public final class PsshAtomUtil {

    class PsshAtom {
        /* access modifiers changed from: private */
        public final UUID a;
        /* access modifiers changed from: private */
        public final int b;
        /* access modifiers changed from: private */
        public final byte[] c;

        public PsshAtom(UUID uuid, int i, byte[] bArr) {
            this.a = uuid;
            this.b = i;
            this.c = bArr;
        }
    }

    public static boolean a(byte[] bArr) {
        return d(bArr) != null;
    }

    public static byte[] a(UUID uuid, byte[] bArr) {
        return a(uuid, (UUID[]) null, bArr);
    }

    public static byte[] a(UUID uuid, UUID[] uuidArr, byte[] bArr) {
        int length = (bArr != null ? bArr.length : 0) + 32;
        if (uuidArr != null) {
            length += (uuidArr.length << 4) + 4;
        }
        ByteBuffer allocate = ByteBuffer.allocate(length);
        allocate.putInt(length);
        allocate.putInt(1886614376);
        allocate.putInt(uuidArr != null ? 16777216 : 0);
        allocate.putLong(uuid.getMostSignificantBits());
        allocate.putLong(uuid.getLeastSignificantBits());
        if (uuidArr != null) {
            allocate.putInt(uuidArr.length);
            for (UUID uuid2 : uuidArr) {
                allocate.putLong(uuid2.getMostSignificantBits());
                allocate.putLong(uuid2.getLeastSignificantBits());
            }
        }
        if (!(bArr == null || bArr.length == 0)) {
            allocate.putInt(bArr.length);
            allocate.put(bArr);
        }
        return allocate.array();
    }

    public static byte[] a(byte[] bArr, UUID uuid) {
        PsshAtom d = d(bArr);
        if (d == null) {
            return null;
        }
        if (uuid.equals(d.a)) {
            return d.c;
        }
        String valueOf = String.valueOf(uuid);
        String valueOf2 = String.valueOf(d.a);
        Log.c("PsshAtomUtil", new StringBuilder(String.valueOf(valueOf).length() + 33 + String.valueOf(valueOf2).length()).append("UUID mismatch. Expected: ").append(valueOf).append(", got: ").append(valueOf2).append(".").toString());
        return null;
    }

    public static UUID b(byte[] bArr) {
        PsshAtom d = d(bArr);
        if (d == null) {
            return null;
        }
        return d.a;
    }

    public static int c(byte[] bArr) {
        PsshAtom d = d(bArr);
        if (d == null) {
            return -1;
        }
        return d.b;
    }

    private static PsshAtom d(byte[] bArr) {
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr);
        if (parsableByteArray.c < 32) {
            return null;
        }
        parsableByteArray.d(0);
        if (parsableByteArray.j() != parsableByteArray.a() + 4 || parsableByteArray.j() != 1886614376) {
            return null;
        }
        int a = Atom.a(parsableByteArray.j());
        if (a > 1) {
            Log.c("PsshAtomUtil", new StringBuilder(37).append("Unsupported pssh version: ").append(a).toString());
            return null;
        }
        UUID uuid = new UUID(parsableByteArray.l(), parsableByteArray.l());
        if (a == 1) {
            parsableByteArray.e(parsableByteArray.o() << 4);
        }
        int o = parsableByteArray.o();
        if (o != parsableByteArray.a()) {
            return null;
        }
        byte[] bArr2 = new byte[o];
        parsableByteArray.a(bArr2, 0, o);
        return new PsshAtom(uuid, a, bArr2);
    }
}
