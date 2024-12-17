package com.google.android.exoplayer2.source.hls;

import android.net.Uri;
import com.google.android.exoplayer2.util.Assertions;
import java.util.LinkedHashMap;
import java.util.Map;

final class FullSegmentEncryptionKeyCache {
    private final LinkedHashMap a = new LinkedHashMap() {
        private /* synthetic */ int a = 4;

        /* access modifiers changed from: protected */
        public boolean removeEldestEntry(Map.Entry entry) {
            return size() > this.a;
        }
    };

    public final byte[] a(Uri uri) {
        if (uri == null) {
            return null;
        }
        return (byte[]) this.a.get(uri);
    }

    public final byte[] a(Uri uri, byte[] bArr) {
        return (byte[]) this.a.put((Uri) Assertions.b((Object) uri), (byte[]) Assertions.b((Object) bArr));
    }

    public final byte[] b(Uri uri) {
        return (byte[]) this.a.remove(Assertions.b((Object) uri));
    }
}
