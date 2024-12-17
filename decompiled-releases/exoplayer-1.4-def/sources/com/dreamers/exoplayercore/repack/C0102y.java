package com.dreamers.exoplayercore.repack;

import android.view.View;
import androidx.appcompat.widget.ActivityChooserView;
import java.util.List;

/* renamed from: com.dreamers.exoplayercore.repack.y  reason: case insensitive filesystem */
final class C0102y {
    boolean a = true;
    int b;
    int c;
    int d;
    int e;
    int f;
    int g;
    int h = 0;
    int i;
    List j = null;
    boolean k;

    C0102y() {
    }

    private View a() {
        int size = this.j.size();
        int i2 = 0;
        while (i2 < size) {
            View view = ((C0010ai) this.j.get(i2)).itemView;
            Y y = (Y) view.getLayoutParams();
            if (y.a.isRemoved() || this.d != y.a.getLayoutPosition()) {
                i2++;
            } else {
                a(view);
                return view;
            }
        }
        return null;
    }

    private View b(View view) {
        int layoutPosition;
        int size = this.j.size();
        View view2 = null;
        int i2 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        for (int i3 = 0; i3 < size; i3++) {
            View view3 = ((C0010ai) this.j.get(i3)).itemView;
            Y y = (Y) view3.getLayoutParams();
            if (view3 != view && !y.a.isRemoved() && (layoutPosition = (y.a.getLayoutPosition() - this.d) * this.e) >= 0 && layoutPosition < i2) {
                view2 = view3;
                if (layoutPosition == 0) {
                    break;
                }
                i2 = layoutPosition;
            }
        }
        return view2;
    }

    /* access modifiers changed from: package-private */
    public final View a(C0004ac acVar) {
        if (this.j != null) {
            return a();
        }
        View a2 = acVar.a(this.d);
        this.d += this.e;
        return a2;
    }

    public final void a(View view) {
        View b2 = b(view);
        this.d = b2 == null ? -1 : ((Y) b2.getLayoutParams()).a.getLayoutPosition();
    }

    /* access modifiers changed from: package-private */
    public final boolean a(C0008ag agVar) {
        int i2 = this.d;
        return i2 >= 0 && i2 < agVar.a();
    }
}
