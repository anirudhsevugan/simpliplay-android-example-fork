package com.google.android.exoplayer2.metadata.id3;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.Metadata$Entry$$CC;

public abstract class Id3Frame implements Metadata.Entry {
    public final String c;

    public Id3Frame(String str) {
        this.c = str;
    }

    public final Format a() {
        return Metadata$Entry$$CC.a();
    }

    public void a(MediaMetadata.Builder builder) {
        Metadata$Entry$$CC.c();
    }

    public final byte[] b() {
        return Metadata$Entry$$CC.b();
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return this.c;
    }
}
