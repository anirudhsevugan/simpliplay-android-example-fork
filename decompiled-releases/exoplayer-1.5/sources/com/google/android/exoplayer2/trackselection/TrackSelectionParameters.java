package com.google.android.exoplayer2.trackselection;

import android.content.Context;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.accessibility.CaptioningManager;
import com.dreamers.exoplayercore.repack.bG;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

public class TrackSelectionParameters implements Parcelable {
    public final bG C;
    public final int D;
    public final bG E;
    public final int F;
    public final boolean G;
    public final int H;

    public class Builder {
        bG a;
        int b;
        bG c;
        int d;
        boolean e;
        int f;

        public Builder() {
            this.a = bG.g();
            this.b = 0;
            this.c = bG.g();
            this.d = 0;
            this.e = false;
            this.f = 0;
        }

        public Builder(Context context) {
            this();
            a(context);
        }

        Builder(TrackSelectionParameters trackSelectionParameters) {
            this.a = trackSelectionParameters.C;
            this.b = trackSelectionParameters.D;
            this.c = trackSelectionParameters.E;
            this.d = trackSelectionParameters.F;
            this.e = trackSelectionParameters.G;
            this.f = trackSelectionParameters.H;
        }

        public Builder a(Context context) {
            CaptioningManager captioningManager;
            if (Util.a >= 19 && ((Util.a >= 23 || Looper.myLooper() != null) && (captioningManager = (CaptioningManager) context.getSystemService("captioning")) != null && captioningManager.isEnabled())) {
                this.d = 1088;
                Locale locale = captioningManager.getLocale();
                if (locale != null) {
                    this.c = bG.a((Object) Util.a(locale));
                }
            }
            return this;
        }

        public TrackSelectionParameters b() {
            return new TrackSelectionParameters(this.a, this.b, this.c, this.d, this.e, this.f);
        }
    }

    static {
        new Builder().b();
        new Parcelable.Creator() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new TrackSelectionParameters(parcel);
            }

            public /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new TrackSelectionParameters[i];
            }
        };
    }

    TrackSelectionParameters(Parcel parcel) {
        ArrayList arrayList = new ArrayList();
        parcel.readList(arrayList, (ClassLoader) null);
        this.C = bG.a((Collection) arrayList);
        this.D = parcel.readInt();
        ArrayList arrayList2 = new ArrayList();
        parcel.readList(arrayList2, (ClassLoader) null);
        this.E = bG.a((Collection) arrayList2);
        this.F = parcel.readInt();
        this.G = Util.a(parcel);
        this.H = parcel.readInt();
    }

    TrackSelectionParameters(bG bGVar, int i, bG bGVar2, int i2, boolean z, int i3) {
        this.C = bGVar;
        this.D = i;
        this.E = bGVar2;
        this.F = i2;
        this.G = z;
        this.H = i3;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            TrackSelectionParameters trackSelectionParameters = (TrackSelectionParameters) obj;
            return this.C.equals(trackSelectionParameters.C) && this.D == trackSelectionParameters.D && this.E.equals(trackSelectionParameters.E) && this.F == trackSelectionParameters.F && this.G == trackSelectionParameters.G && this.H == trackSelectionParameters.H;
        }
    }

    public int hashCode() {
        return ((((((((((this.C.hashCode() + 31) * 31) + this.D) * 31) + this.E.hashCode()) * 31) + this.F) * 31) + (this.G ? 1 : 0)) * 31) + this.H;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.C);
        parcel.writeInt(this.D);
        parcel.writeList(this.E);
        parcel.writeInt(this.F);
        Util.a(parcel, this.G);
        parcel.writeInt(this.H);
    }
}
