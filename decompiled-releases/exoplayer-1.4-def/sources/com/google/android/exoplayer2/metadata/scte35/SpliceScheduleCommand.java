package com.google.android.exoplayer2.metadata.scte35;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SpliceScheduleCommand extends SpliceCommand {
    private List a;

    public final class ComponentSplice {
        private int a;
        private long b;

        private ComponentSplice(int i, long j) {
            this.a = i;
            this.b = j;
        }

        /* synthetic */ ComponentSplice(int i, long j, byte b2) {
            this(i, j);
        }

        static /* synthetic */ ComponentSplice a(Parcel parcel) {
            return new ComponentSplice(parcel.readInt(), parcel.readLong());
        }

        static /* synthetic */ void a(ComponentSplice componentSplice, Parcel parcel) {
            parcel.writeInt(componentSplice.a);
            parcel.writeLong(componentSplice.b);
        }
    }

    public final class Event {
        private long a;
        private boolean b;
        private boolean c;
        private boolean d;
        private long e;
        private List f;
        private boolean g;
        private long h;
        private int i;
        private int j;
        private int k;

        private Event(long j2, boolean z, boolean z2, boolean z3, List list, long j3, boolean z4, long j4, int i2, int i3, int i4) {
            this.a = j2;
            this.b = z;
            this.c = z2;
            this.d = z3;
            this.f = Collections.unmodifiableList(list);
            this.e = j3;
            this.g = z4;
            this.h = j4;
            this.i = i2;
            this.j = i3;
            this.k = i4;
        }

        private Event(Parcel parcel) {
            this.a = parcel.readLong();
            boolean z = false;
            this.b = parcel.readByte() == 1;
            this.c = parcel.readByte() == 1;
            this.d = parcel.readByte() == 1;
            int readInt = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            for (int i2 = 0; i2 < readInt; i2++) {
                arrayList.add(ComponentSplice.a(parcel));
            }
            this.f = Collections.unmodifiableList(arrayList);
            this.e = parcel.readLong();
            this.g = parcel.readByte() == 1 ? true : z;
            this.h = parcel.readLong();
            this.i = parcel.readInt();
            this.j = parcel.readInt();
            this.k = parcel.readInt();
        }

        static /* synthetic */ Event a(Parcel parcel) {
            return new Event(parcel);
        }

        static /* synthetic */ Event a(ParsableByteArray parsableByteArray) {
            long j2;
            int i2;
            int i3;
            int i4;
            boolean z;
            boolean z2;
            long j3;
            ArrayList arrayList;
            long j4;
            long h2 = parsableByteArray.h();
            boolean z3 = false;
            boolean z4 = (parsableByteArray.c() & 128) != 0;
            ArrayList arrayList2 = new ArrayList();
            if (!z4) {
                int c2 = parsableByteArray.c();
                boolean z5 = (c2 & 128) != 0;
                z = (c2 & 64) != 0;
                boolean z6 = (c2 & 32) != 0;
                long h3 = z ? parsableByteArray.h() : -9223372036854775807L;
                if (!z) {
                    int c3 = parsableByteArray.c();
                    ArrayList arrayList3 = new ArrayList(c3);
                    for (int i5 = 0; i5 < c3; i5++) {
                        arrayList3.add(new ComponentSplice(parsableByteArray.c(), parsableByteArray.h(), (byte) 0));
                    }
                    arrayList2 = arrayList3;
                }
                if (z6) {
                    long c4 = (long) parsableByteArray.c();
                    boolean z7 = (128 & c4) != 0;
                    j4 = ((((c4 & 1) << 32) | parsableByteArray.h()) * 1000) / 90;
                    z3 = z7;
                } else {
                    j4 = -9223372036854775807L;
                }
                int d2 = parsableByteArray.d();
                int c5 = parsableByteArray.c();
                j2 = j4;
                i2 = parsableByteArray.c();
                arrayList = arrayList2;
                long j5 = h3;
                i4 = d2;
                i3 = c5;
                j3 = j5;
                boolean z8 = z5;
                z2 = z3;
                z3 = z8;
            } else {
                arrayList = arrayList2;
                j3 = -9223372036854775807L;
                z2 = false;
                z = false;
                i4 = 0;
                i3 = 0;
                i2 = 0;
                j2 = -9223372036854775807L;
            }
            return new Event(h2, z4, z3, z, arrayList, j3, z2, j2, i4, i3, i2);
        }

        static /* synthetic */ void a(Event event, Parcel parcel) {
            parcel.writeLong(event.a);
            parcel.writeByte(event.b ? (byte) 1 : 0);
            parcel.writeByte(event.c ? (byte) 1 : 0);
            parcel.writeByte(event.d ? (byte) 1 : 0);
            int size = event.f.size();
            parcel.writeInt(size);
            for (int i2 = 0; i2 < size; i2++) {
                ComponentSplice.a((ComponentSplice) event.f.get(i2), parcel);
            }
            parcel.writeLong(event.e);
            parcel.writeByte(event.g ? (byte) 1 : 0);
            parcel.writeLong(event.h);
            parcel.writeInt(event.i);
            parcel.writeInt(event.j);
            parcel.writeInt(event.k);
        }
    }

    static {
        new Parcelable.Creator() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new SpliceScheduleCommand(parcel, (byte) 0);
            }

            public /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new SpliceScheduleCommand[i];
            }
        };
    }

    private SpliceScheduleCommand(Parcel parcel) {
        int readInt = parcel.readInt();
        ArrayList arrayList = new ArrayList(readInt);
        for (int i = 0; i < readInt; i++) {
            arrayList.add(Event.a(parcel));
        }
        this.a = Collections.unmodifiableList(arrayList);
    }

    /* synthetic */ SpliceScheduleCommand(Parcel parcel, byte b) {
        this(parcel);
    }

    private SpliceScheduleCommand(List list) {
        this.a = Collections.unmodifiableList(list);
    }

    static SpliceScheduleCommand a(ParsableByteArray parsableByteArray) {
        int c = parsableByteArray.c();
        ArrayList arrayList = new ArrayList(c);
        for (int i = 0; i < c; i++) {
            arrayList.add(Event.a(parsableByteArray));
        }
        return new SpliceScheduleCommand((List) arrayList);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int size = this.a.size();
        parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            Event.a((Event) this.a.get(i2), parcel);
        }
    }
}
