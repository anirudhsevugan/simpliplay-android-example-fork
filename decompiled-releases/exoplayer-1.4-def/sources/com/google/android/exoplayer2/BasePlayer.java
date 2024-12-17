package com.google.android.exoplayer2;

import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Timeline;

public abstract class BasePlayer implements Player {
    protected final Timeline.Window a = new Timeline.Window();

    private int p() {
        int m = m();
        if (m == 1) {
            return 0;
        }
        return m;
    }

    /* access modifiers changed from: protected */
    public final Player.Commands a(Player.Commands commands) {
        boolean z = false;
        Player.Commands.Builder a2 = new Player.Commands.Builder().a(commands).a(3, !w()).a(4, d() && !w()).a(5, (b() != -1) && !w());
        if ((c() != -1) && !w()) {
            z = true;
        }
        return a2.a(6, z).a(7, !w()).a();
    }

    public final boolean a() {
        return h() == 3 && l() && i() == 0;
    }

    public final boolean a(int i) {
        return g().a.a(i);
    }

    public final int b() {
        Timeline E = E();
        if (E.c()) {
            return -1;
        }
        return E.a(r(), p(), n());
    }

    public final int c() {
        Timeline E = E();
        if (E.c()) {
            return -1;
        }
        return E.b(r(), p(), n());
    }

    public final boolean d() {
        Timeline E = E();
        return !E.c() && E.a(r(), this.a, 0).h;
    }

    public final long e() {
        Timeline E = E();
        if (E.c()) {
            return -9223372036854775807L;
        }
        return C.a(E.a(r(), this.a, 0).m);
    }
}
