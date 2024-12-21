package com.dreamers.exoplayercore.repack;

import androidx.appcompat.widget.ActivityChooserView;

abstract class aI extends C0021au {
    final CharSequence a;
    private C0024ax b;
    private int c = 0;
    private int d;

    protected aI(aF aFVar, CharSequence charSequence) {
        this.b = aFVar.a;
        this.d = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.a = charSequence;
    }

    /* access modifiers changed from: package-private */
    public abstract int a(int i);

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object a() {
        int i;
        int i2 = this.c;
        while (true) {
            int i3 = this.c;
            if (i3 != -1) {
                int a2 = a(i3);
                if (a2 == -1) {
                    a2 = this.a.length();
                    this.c = -1;
                } else {
                    this.c = b(a2);
                }
                int i4 = this.c;
                if (i4 == i2) {
                    int i5 = i4 + 1;
                    this.c = i5;
                    if (i5 > this.a.length()) {
                        this.c = -1;
                    }
                } else {
                    while (i2 < a2 && this.b.b(this.a.charAt(i2))) {
                        i2++;
                    }
                    while (i > i2 && this.b.b(this.a.charAt(i - 1))) {
                        a2 = i - 1;
                    }
                    int i6 = this.d;
                    if (i6 == 1) {
                        i = this.a.length();
                        this.c = -1;
                        while (i > i2 && this.b.b(this.a.charAt(i - 1))) {
                            i--;
                        }
                    } else {
                        this.d = i6 - 1;
                    }
                    return this.a.subSequence(i2, i).toString();
                }
            } else {
                b();
                return null;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public abstract int b(int i);
}
