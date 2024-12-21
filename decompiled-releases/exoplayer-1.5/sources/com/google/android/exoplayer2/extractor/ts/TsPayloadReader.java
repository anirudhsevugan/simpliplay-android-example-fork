package com.google.android.exoplayer2.extractor.ts;

import android.util.SparseArray;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import java.util.Collections;
import java.util.List;

public interface TsPayloadReader {

    public final class DvbSubtitleInfo {
        public final String a;
        public final byte[] b;

        public DvbSubtitleInfo(String str, byte[] bArr) {
            this.a = str;
            this.b = bArr;
        }
    }

    public final class EsInfo {
        public final int a;
        public final String b;
        public final List c;
        public final byte[] d;

        public EsInfo(int i, String str, List list, byte[] bArr) {
            this.a = i;
            this.b = str;
            this.c = list == null ? Collections.emptyList() : Collections.unmodifiableList(list);
            this.d = bArr;
        }
    }

    public interface Factory {
        SparseArray a();

        TsPayloadReader a(int i, EsInfo esInfo);
    }

    public final class TrackIdGenerator {
        private final String a;
        private final int b;
        private final int c;
        private int d;
        private String e;

        public TrackIdGenerator(int i, int i2) {
            this(Integer.MIN_VALUE, i, i2);
        }

        public TrackIdGenerator(int i, int i2, int i3) {
            this.a = i != Integer.MIN_VALUE ? new StringBuilder(12).append(i).append("/").toString() : "";
            this.b = i2;
            this.c = i3;
            this.d = Integer.MIN_VALUE;
            this.e = "";
        }

        private void d() {
            if (this.d == Integer.MIN_VALUE) {
                throw new IllegalStateException("generateNewId() must be called before retrieving ids.");
            }
        }

        public final void a() {
            int i = this.d;
            int i2 = i == Integer.MIN_VALUE ? this.b : i + this.c;
            this.d = i2;
            String str = this.a;
            this.e = new StringBuilder(String.valueOf(str).length() + 11).append(str).append(i2).toString();
        }

        public final int b() {
            d();
            return this.d;
        }

        public final String c() {
            d();
            return this.e;
        }
    }

    void a();

    void a(ParsableByteArray parsableByteArray, int i);

    void a(TimestampAdjuster timestampAdjuster, ExtractorOutput extractorOutput, TrackIdGenerator trackIdGenerator);
}
