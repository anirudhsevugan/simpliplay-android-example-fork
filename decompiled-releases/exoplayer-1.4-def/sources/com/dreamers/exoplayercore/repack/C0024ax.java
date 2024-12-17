package com.dreamers.exoplayercore.repack;

/* renamed from: com.dreamers.exoplayercore.repack.ax  reason: case insensitive filesystem */
public abstract class C0024ax implements aE {
    protected C0024ax() {
    }

    public static C0024ax a() {
        return aB.a;
    }

    public static C0024ax a(char c) {
        return new C0026az(c);
    }

    static /* synthetic */ String c(char c) {
        char[] cArr = {'\\', 'u', 0, 0, 0, 0};
        for (int i = 0; i < 4; i++) {
            cArr[5 - i] = "0123456789ABCDEF".charAt(c & 15);
            c = (char) (c >> 4);
        }
        return String.copyValueOf(cArr);
    }

    public int a(CharSequence charSequence, int i) {
        int length = charSequence.length();
        C0000a.c(i, length);
        while (i < length) {
            if (b(charSequence.charAt(i))) {
                return i;
            }
            i++;
        }
        return -1;
    }

    /* renamed from: a */
    public final boolean apply(Character ch) {
        return b(ch.charValue());
    }

    public abstract boolean b(char c);

    public String toString() {
        return super.toString();
    }
}
