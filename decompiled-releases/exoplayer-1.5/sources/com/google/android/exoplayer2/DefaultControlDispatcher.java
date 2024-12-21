package com.google.android.exoplayer2;

import com.google.android.exoplayer2.Timeline;

public class DefaultControlDispatcher implements ControlDispatcher {
    public long a;
    public long b;
    private final Timeline.Window c;

    public DefaultControlDispatcher() {
        this(15000, 5000);
    }

    public DefaultControlDispatcher(long j, long j2) {
        this.b = j;
        this.a = j2;
        this.c = new Timeline.Window();
    }

    private static void a(Player player, long j) {
        long t = player.t() + j;
        long s = player.s();
        if (s != -9223372036854775807L) {
            t = Math.min(t, s);
        }
        player.a(player.r(), Math.max(t, 0));
    }

    public final boolean a() {
        return this.a > 0;
    }

    public final boolean a(Player player) {
        player.k();
        return true;
    }

    public final boolean a(Player player, int i) {
        player.b(i);
        return true;
    }

    public final boolean a(Player player, int i, long j) {
        player.a(i, j);
        return true;
    }

    public final boolean a(Player player, PlaybackParameters playbackParameters) {
        player.a(playbackParameters);
        return true;
    }

    public final boolean a(Player player, boolean z) {
        player.a(z);
        return true;
    }

    public final boolean b() {
        return this.b > 0;
    }

    public final boolean b(Player player) {
        Timeline E = player.E();
        if (!E.c() && !player.w()) {
            int r = player.r();
            E.a(r, this.c, 0);
            int c2 = player.c();
            boolean z = this.c.a() && !this.c.h;
            if (c2 != -1 && (player.t() <= 3000 || z)) {
                player.a(c2, -9223372036854775807L);
            } else if (!z) {
                player.a(r, 0);
            }
        }
        return true;
    }

    public final boolean b(Player player, boolean z) {
        player.b(z);
        return true;
    }

    public final boolean c(Player player) {
        Timeline E = player.E();
        if (!E.c() && !player.w()) {
            int r = player.r();
            E.a(r, this.c, 0);
            int b2 = player.b();
            if (b2 != -1) {
                player.a(b2, -9223372036854775807L);
            } else if (this.c.a() && this.c.i) {
                player.a(r, -9223372036854775807L);
            }
        }
        return true;
    }

    public final boolean d(Player player) {
        if (!a() || !player.d()) {
            return true;
        }
        a(player, -this.a);
        return true;
    }

    public final boolean e(Player player) {
        if (!b() || !player.d()) {
            return true;
        }
        a(player, this.b);
        return true;
    }
}
