package com.dreamers.exoplayercore.repack;

/* renamed from: com.dreamers.exoplayercore.repack.c  reason: case insensitive filesystem */
public final class C0054c {
    int a;
    int b;
    Object c;
    int d;

    C0054c(int i, int i2, int i3, Object obj) {
        this.a = i;
        this.b = i2;
        this.d = i3;
        this.c = obj;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        C0054c cVar = (C0054c) obj;
        int i = this.a;
        if (i != cVar.a) {
            return false;
        }
        if (i == 8 && Math.abs(this.d - this.b) == 1 && this.d == cVar.b && this.b == cVar.d) {
            return true;
        }
        if (this.d != cVar.d || this.b != cVar.b) {
            return false;
        }
        Object obj2 = this.c;
        Object obj3 = cVar.c;
        if (obj2 != null) {
            if (!obj2.equals(obj3)) {
                return false;
            }
        } else if (obj3 != null) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return (((this.a * 31) + this.b) * 31) + this.d;
    }

    public final String toString() {
        String str;
        StringBuilder append = new StringBuilder().append(Integer.toHexString(System.identityHashCode(this))).append("[");
        switch (this.a) {
            case 1:
                str = "add";
                break;
            case 2:
                str = "rm";
                break;
            case 4:
                str = "up";
                break;
            case 8:
                str = "mv";
                break;
            default:
                str = "??";
                break;
        }
        return append.append(str).append(",s:").append(this.b).append("c:").append(this.d).append(",p:").append(this.c).append("]").toString();
    }
}
