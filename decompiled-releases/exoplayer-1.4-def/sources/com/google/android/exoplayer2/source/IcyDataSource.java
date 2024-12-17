package com.google.android.exoplayer2.source;

import android.net.Uri;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.util.Map;

final class IcyDataSource implements DataSource {
    private final DataSource a;
    private final int b;
    private final Listener c;
    private final byte[] d;
    private int e;

    public interface Listener {
        void a(ParsableByteArray parsableByteArray);
    }

    public IcyDataSource(DataSource dataSource, int i, Listener listener) {
        Assertions.a(i > 0);
        this.a = dataSource;
        this.b = i;
        this.c = listener;
        this.d = new byte[1];
        this.e = i;
    }

    public final int a(byte[] bArr, int i, int i2) {
        if (this.e == 0) {
            boolean z = false;
            if (this.a.a(this.d, 0, 1) != -1) {
                int i3 = (this.d[0] & Ev3Constants.Opcode.TST) << 4;
                if (i3 != 0) {
                    byte[] bArr2 = new byte[i3];
                    int i4 = i3;
                    int i5 = 0;
                    while (true) {
                        if (i4 <= 0) {
                            while (i3 > 0 && bArr2[i3 - 1] == 0) {
                                i3--;
                            }
                            if (i3 > 0) {
                                this.c.a(new ParsableByteArray(bArr2, i3));
                            }
                        } else {
                            int a2 = this.a.a(bArr2, i5, i4);
                            if (a2 == -1) {
                                break;
                            }
                            i5 += a2;
                            i4 -= a2;
                        }
                    }
                }
                z = true;
            }
            if (!z) {
                return -1;
            }
            this.e = this.b;
        }
        int a3 = this.a.a(bArr, i, Math.min(this.e, i2));
        if (a3 != -1) {
            this.e -= a3;
        }
        return a3;
    }

    public final long a(DataSpec dataSpec) {
        throw new UnsupportedOperationException();
    }

    public final Uri a() {
        return this.a.a();
    }

    public final void a(TransferListener transferListener) {
        Assertions.b((Object) transferListener);
        this.a.a(transferListener);
    }

    public final Map b() {
        return this.a.b();
    }

    public final void c() {
        throw new UnsupportedOperationException();
    }
}
