package com.google.android.exoplayer2.upstream;

import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Map;

public abstract class BaseDataSource implements DataSource {
    private final boolean a;
    private final ArrayList b = new ArrayList(1);
    private int c;
    private DataSpec d;

    protected BaseDataSource(boolean z) {
        this.a = z;
    }

    /* access modifiers changed from: protected */
    public final void a(int i) {
        DataSpec dataSpec = (DataSpec) Util.a((Object) this.d);
        for (int i2 = 0; i2 < this.c; i2++) {
            ((TransferListener) this.b.get(i2)).a(dataSpec, this.a, i);
        }
    }

    public final void a(TransferListener transferListener) {
        Assertions.b((Object) transferListener);
        if (!this.b.contains(transferListener)) {
            this.b.add(transferListener);
            this.c++;
        }
    }

    public Map b() {
        return DataSource$$CC.a();
    }

    /* access modifiers changed from: protected */
    public final void b(DataSpec dataSpec) {
        this.d = dataSpec;
        for (int i = 0; i < this.c; i++) {
            ((TransferListener) this.b.get(i)).a(dataSpec, this.a);
        }
    }

    /* access modifiers changed from: protected */
    public final void d() {
        for (int i = 0; i < this.c; i++) {
            this.b.get(i);
        }
    }

    /* access modifiers changed from: protected */
    public final void e() {
        DataSpec dataSpec = (DataSpec) Util.a((Object) this.d);
        for (int i = 0; i < this.c; i++) {
            ((TransferListener) this.b.get(i)).b(dataSpec, this.a);
        }
        this.d = null;
    }
}
