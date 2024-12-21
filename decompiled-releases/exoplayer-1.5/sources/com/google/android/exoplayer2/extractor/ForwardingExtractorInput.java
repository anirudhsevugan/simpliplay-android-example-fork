package com.google.android.exoplayer2.extractor;

public class ForwardingExtractorInput implements ExtractorInput {
    private final ExtractorInput a;

    public ForwardingExtractorInput(ExtractorInput extractorInput) {
        this.a = extractorInput;
    }

    public final int a(int i) {
        return this.a.a(i);
    }

    public final int a(byte[] bArr, int i, int i2) {
        return this.a.a(bArr, i, i2);
    }

    public final void a() {
        this.a.a();
    }

    public final boolean a(byte[] bArr, int i, int i2, boolean z) {
        return this.a.a(bArr, i, i2, z);
    }

    public long b() {
        return this.a.b();
    }

    public final void b(int i) {
        this.a.b(i);
    }

    public final void b(byte[] bArr, int i, int i2) {
        this.a.b(bArr, i, i2);
    }

    public final boolean b(byte[] bArr, int i, int i2, boolean z) {
        return this.a.b(bArr, i, i2, z);
    }

    public final int c(byte[] bArr, int i, int i2) {
        return this.a.c(bArr, i, i2);
    }

    public long c() {
        return this.a.c();
    }

    public final void c(int i) {
        this.a.c(i);
    }

    public long d() {
        return this.a.d();
    }

    public final void d(byte[] bArr, int i, int i2) {
        this.a.d(bArr, i, i2);
    }
}
