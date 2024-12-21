package com.google.android.exoplayer2.metadata.scte35;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SpliceInsertCommand extends SpliceCommand {
    private long a;
    private boolean b;
    private boolean c;
    private boolean d;
    private boolean e;
    private long f;
    private long g;
    private List h;
    private boolean i;
    private long j;
    private int k;
    private int l;
    private int m;

    public final class ComponentSplice {
        public final int a;
        public final long b;
        public final long c;

        private ComponentSplice(int i, long j, long j2) {
            this.a = i;
            this.b = j;
            this.c = j2;
        }

        /* synthetic */ ComponentSplice(int i, long j, long j2, byte b2) {
            this(i, j, j2);
        }

        public static ComponentSplice a(Parcel parcel) {
            return new ComponentSplice(parcel.readInt(), parcel.readLong(), parcel.readLong());
        }
    }

    static {
        new Parcelable.Creator() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new SpliceInsertCommand(parcel, (byte) 0);
            }

            public /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new SpliceInsertCommand[i];
            }
        };
    }

    private SpliceInsertCommand(long j2, boolean z, boolean z2, boolean z3, boolean z4, long j3, long j4, List list, boolean z5, long j5, int i2, int i3, int i4) {
        this.a = j2;
        this.b = z;
        this.c = z2;
        this.d = z3;
        this.e = z4;
        this.f = j3;
        this.g = j4;
        this.h = Collections.unmodifiableList(list);
        this.i = z5;
        this.j = j5;
        this.k = i2;
        this.l = i3;
        this.m = i4;
    }

    private SpliceInsertCommand(Parcel parcel) {
        this.a = parcel.readLong();
        boolean z = false;
        this.b = parcel.readByte() == 1;
        this.c = parcel.readByte() == 1;
        this.d = parcel.readByte() == 1;
        this.e = parcel.readByte() == 1;
        this.f = parcel.readLong();
        this.g = parcel.readLong();
        int readInt = parcel.readInt();
        ArrayList arrayList = new ArrayList(readInt);
        for (int i2 = 0; i2 < readInt; i2++) {
            arrayList.add(ComponentSplice.a(parcel));
        }
        this.h = Collections.unmodifiableList(arrayList);
        this.i = parcel.readByte() == 1 ? true : z;
        this.j = parcel.readLong();
        this.k = parcel.readInt();
        this.l = parcel.readInt();
        this.m = parcel.readInt();
    }

    /* synthetic */ SpliceInsertCommand(Parcel parcel, byte b2) {
        this(parcel);
    }

    static SpliceInsertCommand a(ParsableByteArray parsableByteArray, long j2, TimestampAdjuster timestampAdjuster) {
        boolean z;
        int i2;
        int i3;
        int i4;
        long j3;
        boolean z2;
        List list;
        long j4;
        boolean z3;
        boolean z4;
        long j5;
        boolean z5;
        TimestampAdjuster timestampAdjuster2 = timestampAdjuster;
        long h2 = parsableByteArray.h();
        boolean z6 = (parsableByteArray.c() & 128) != 0;
        List emptyList = Collections.emptyList();
        if (!z6) {
            int c2 = parsableByteArray.c();
            boolean z7 = (c2 & 128) != 0;
            boolean z8 = (c2 & 64) != 0;
            boolean z9 = (c2 & 32) != 0;
            boolean z10 = (c2 & 16) != 0;
            long a2 = (!z8 || z10) ? -9223372036854775807L : TimeSignalCommand.a(parsableByteArray, j2);
            if (!z8) {
                int c3 = parsableByteArray.c();
                ArrayList arrayList = new ArrayList(c3);
                for (int i5 = 0; i5 < c3; i5++) {
                    int c4 = parsableByteArray.c();
                    long a3 = !z10 ? TimeSignalCommand.a(parsableByteArray, j2) : -9223372036854775807L;
                    arrayList.add(new ComponentSplice(c4, a3, timestampAdjuster2.b(a3), (byte) 0));
                }
                emptyList = arrayList;
            }
            if (z9) {
                long c5 = (long) parsableByteArray.c();
                boolean z11 = (128 & c5) != 0;
                j5 = ((((c5 & 1) << 32) | parsableByteArray.h()) * 1000) / 90;
                z5 = z11;
            } else {
                z5 = false;
                j5 = -9223372036854775807L;
            }
            i4 = parsableByteArray.d();
            z = z8;
            i3 = parsableByteArray.c();
            i2 = parsableByteArray.c();
            list = emptyList;
            long j6 = a2;
            z2 = z5;
            j3 = j5;
            z3 = z10;
            z4 = z7;
            j4 = j6;
        } else {
            list = emptyList;
            z4 = false;
            z3 = false;
            j4 = -9223372036854775807L;
            z2 = false;
            j3 = -9223372036854775807L;
            i4 = 0;
            i3 = 0;
            i2 = 0;
            z = false;
        }
        return new SpliceInsertCommand(h2, z6, z4, z, z3, j4, timestampAdjuster2.b(j4), list, z2, j3, i4, i3, i2);
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeLong(this.a);
        parcel.writeByte(this.b ? (byte) 1 : 0);
        parcel.writeByte(this.c ? (byte) 1 : 0);
        parcel.writeByte(this.d ? (byte) 1 : 0);
        parcel.writeByte(this.e ? (byte) 1 : 0);
        parcel.writeLong(this.f);
        parcel.writeLong(this.g);
        int size = this.h.size();
        parcel.writeInt(size);
        for (int i3 = 0; i3 < size; i3++) {
            ComponentSplice componentSplice = (ComponentSplice) this.h.get(i3);
            parcel.writeInt(componentSplice.a);
            parcel.writeLong(componentSplice.b);
            parcel.writeLong(componentSplice.c);
        }
        parcel.writeByte(this.i ? (byte) 1 : 0);
        parcel.writeLong(this.j);
        parcel.writeInt(this.k);
        parcel.writeInt(this.l);
        parcel.writeInt(this.m);
    }
}
