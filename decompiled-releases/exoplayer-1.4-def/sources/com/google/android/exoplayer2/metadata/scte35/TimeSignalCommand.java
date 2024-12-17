package com.google.android.exoplayer2.metadata.scte35;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;

public final class TimeSignalCommand extends SpliceCommand {
    private long a;
    private long b;

    static {
        new Parcelable.Creator() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new TimeSignalCommand(parcel.readLong(), parcel.readLong(), (byte) 0);
            }

            public /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new TimeSignalCommand[i];
            }
        };
    }

    private TimeSignalCommand(long j, long j2) {
        this.a = j;
        this.b = j2;
    }

    /* synthetic */ TimeSignalCommand(long j, long j2, byte b2) {
        this(j, j2);
    }

    static long a(ParsableByteArray parsableByteArray, long j) {
        long c = (long) parsableByteArray.c();
        if ((128 & c) != 0) {
            return 8589934591L & ((((c & 1) << 32) | parsableByteArray.h()) + j);
        }
        return -9223372036854775807L;
    }

    static TimeSignalCommand a(ParsableByteArray parsableByteArray, long j, TimestampAdjuster timestampAdjuster) {
        long a2 = a(parsableByteArray, j);
        return new TimeSignalCommand(a2, timestampAdjuster.b(a2));
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.a);
        parcel.writeLong(this.b);
    }
}
