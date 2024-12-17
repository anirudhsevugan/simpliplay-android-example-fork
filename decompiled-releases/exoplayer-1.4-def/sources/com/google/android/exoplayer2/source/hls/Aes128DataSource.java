package com.google.android.exoplayer2.source.hls;

import android.net.Uri;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSourceInputStream;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

class Aes128DataSource implements DataSource {
    private final DataSource a;
    private final byte[] b;
    private final byte[] c;
    private CipherInputStream d;

    public Aes128DataSource(DataSource dataSource, byte[] bArr, byte[] bArr2) {
        this.a = dataSource;
        this.b = bArr;
        this.c = bArr2;
    }

    public final int a(byte[] bArr, int i, int i2) {
        Assertions.b((Object) this.d);
        int read = this.d.read(bArr, i, i2);
        if (read < 0) {
            return -1;
        }
        return read;
    }

    public final long a(DataSpec dataSpec) {
        try {
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS7Padding");
            try {
                instance.init(2, new SecretKeySpec(this.b, "AES"), new IvParameterSpec(this.c));
                DataSourceInputStream dataSourceInputStream = new DataSourceInputStream(this.a, dataSpec);
                this.d = new CipherInputStream(dataSourceInputStream, instance);
                dataSourceInputStream.a();
                return -1;
            } catch (InvalidAlgorithmParameterException | InvalidKeyException e) {
                throw new RuntimeException(e);
            }
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e2) {
            throw new RuntimeException(e2);
        }
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
        if (this.d != null) {
            this.d = null;
            this.a.c();
        }
    }
}
