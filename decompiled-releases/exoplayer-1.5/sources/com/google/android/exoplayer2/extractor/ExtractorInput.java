package com.google.android.exoplayer2.extractor;

import com.google.android.exoplayer2.upstream.DataReader;

public interface ExtractorInput extends DataReader {
    int a(int i);

    int a(byte[] bArr, int i, int i2);

    void a();

    boolean a(byte[] bArr, int i, int i2, boolean z);

    long b();

    void b(int i);

    void b(byte[] bArr, int i, int i2);

    boolean b(byte[] bArr, int i, int i2, boolean z);

    int c(byte[] bArr, int i, int i2);

    long c();

    void c(int i);

    long d();

    void d(byte[] bArr, int i, int i2);
}
