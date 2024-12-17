package com.dreamers.exoplayercore.repack;

import androidx.core.util.Pools;
import gnu.expr.Declaration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: com.dreamers.exoplayercore.repack.a  reason: case insensitive filesystem */
public final class C0000a implements C {
    final ArrayList a;
    int b;
    private Pools.Pool c;
    private ArrayList d;
    private C0027b e;
    private B f;

    C0000a(C0027b bVar) {
        this(bVar, (byte) 0);
    }

    private C0000a(C0027b bVar, byte b2) {
        this.c = new Pools.SimplePool(30);
        this.a = new ArrayList();
        this.d = new ArrayList();
        this.b = 0;
        this.e = bVar;
        this.f = new B(this);
    }

    public static int a(int i, String str) {
        if (i >= 0) {
            return i;
        }
        throw new IllegalArgumentException(str + " cannot be negative but was: " + i);
    }

    public static int a(boolean z, boolean z2) {
        if (z == z2) {
            return 0;
        }
        return z ? 1 : -1;
    }

    public static Object a(Iterable iterable, Object obj) {
        Iterator it = iterable.iterator();
        return it.hasNext() ? it.next() : obj;
    }

    public static Object a(Object obj) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException();
    }

    public static Object a(Object obj, int i) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException("at index ".concat(String.valueOf(i)));
    }

    public static Object a(Object obj, Object obj2) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException(String.valueOf(obj2));
    }

    public static Object a(List list) {
        return list.get(list.size() - 1);
    }

    private static String a(int i, int i2, String str) {
        if (i < 0) {
            return a("%s (%s) must not be negative", str, Integer.valueOf(i));
        } else if (i2 >= 0) {
            return a("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i), Integer.valueOf(i2));
        } else {
            throw new IllegalArgumentException("negative size: ".concat(String.valueOf(i2)));
        }
    }

    public static String a(Iterable iterable) {
        StringBuilder sb = new StringBuilder("[");
        boolean z = true;
        for (Object append : iterable) {
            if (!z) {
                sb.append(", ");
            }
            sb.append(append);
            z = false;
        }
        return sb.append(']').toString();
    }

    public static String a(String str) {
        int length = str.length();
        int i = 0;
        while (i < length) {
            if (b(str.charAt(i))) {
                char[] charArray = str.toCharArray();
                while (i < length) {
                    char c2 = charArray[i];
                    if (b(c2)) {
                        charArray[i] = (char) (c2 ^ ' ');
                    }
                    i++;
                }
                return String.valueOf(charArray);
            }
            i++;
        }
        return str;
    }

    private static String a(String str, Object... objArr) {
        int indexOf;
        String valueOf = String.valueOf(str);
        int i = 0;
        for (int i2 = 0; i2 < objArr.length; i2++) {
            objArr[i2] = c(objArr[i2]);
        }
        StringBuilder sb = new StringBuilder(valueOf.length() + (objArr.length * 16));
        int i3 = 0;
        while (i < objArr.length && (indexOf = valueOf.indexOf("%s", i3)) != -1) {
            sb.append(valueOf, i3, indexOf);
            sb.append(objArr[i]);
            int i4 = i + 1;
            i3 = indexOf + 2;
            i = i4;
        }
        sb.append(valueOf, i3, valueOf.length());
        if (i < objArr.length) {
            sb.append(" [");
            sb.append(objArr[i]);
            for (int i5 = i + 1; i5 < objArr.length; i5++) {
                sb.append(", ");
                sb.append(objArr[i5]);
            }
            sb.append(']');
        }
        return sb.toString();
    }

    public static void a(int i, int i2, int i3) {
        String str;
        if (i < 0 || i2 < i || i2 > i3) {
            if (i < 0 || i > i3) {
                str = a(i, i3, "start index");
            } else if (i2 < 0 || i2 > i3) {
                str = a(i2, i3, "end index");
            } else {
                str = a("end index (%s) must not be less than start index (%s)", Integer.valueOf(i2), Integer.valueOf(i));
            }
            throw new IndexOutOfBoundsException(str);
        }
    }

    private void a(C0054c cVar, int i) {
        switch (cVar.a) {
            case 2:
                this.e.a(i, cVar.d);
                return;
            case 4:
                this.e.a(i, cVar.d, cVar.c);
                return;
            default:
                throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
        }
    }

    public static void a(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    public static void a(boolean z, Object obj) {
        if (!z) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
    }

    public static void a(boolean z, String str, long j) {
        if (!z) {
            throw new IllegalArgumentException(a(str, Long.valueOf(j)));
        }
    }

    private static boolean a(char c2) {
        return c2 >= 'a' && c2 <= 'z';
    }

    public static boolean a(CharSequence charSequence, CharSequence charSequence2) {
        int c2;
        int length = charSequence.length();
        if (charSequence == charSequence2) {
            return true;
        }
        if (length != charSequence2.length()) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            char charAt = charSequence.charAt(i);
            char charAt2 = charSequence2.charAt(i);
            if (charAt != charAt2 && ((c2 = c(charAt)) >= 26 || c2 != c(charAt2))) {
                return false;
            }
        }
        return true;
    }

    public static boolean a(Collection collection, Object obj) {
        a((Object) collection);
        try {
            return collection.contains(obj);
        } catch (ClassCastException | NullPointerException e2) {
            return false;
        }
    }

    public static Object[] a(Object... objArr) {
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            a(objArr[i], i);
        }
        return objArr;
    }

    public static int b(int i, int i2) {
        String str;
        if (i >= 0 && i < i2) {
            return i;
        }
        if (i < 0) {
            str = a("%s (%s) must not be negative", "index", Integer.valueOf(i));
        } else if (i2 < 0) {
            throw new IllegalArgumentException("negative size: ".concat(String.valueOf(i2)));
        } else {
            str = a("%s (%s) must be less than size (%s)", "index", Integer.valueOf(i), Integer.valueOf(i2));
        }
        throw new IndexOutOfBoundsException(str);
    }

    public static int b(Object obj) {
        return d(obj == null ? 0 : obj.hashCode());
    }

    public static Object b(Iterable iterable) {
        if (!(iterable instanceof List)) {
            return bV.a(iterable.iterator());
        }
        List list = (List) iterable;
        if (!list.isEmpty()) {
            return a(list);
        }
        throw new NoSuchElementException();
    }

    public static String b(String str) {
        int length = str.length();
        int i = 0;
        while (i < length) {
            if (a(str.charAt(i))) {
                char[] charArray = str.toCharArray();
                while (i < length) {
                    char c2 = charArray[i];
                    if (a(c2)) {
                        charArray[i] = (char) (c2 ^ ' ');
                    }
                    i++;
                }
                return String.valueOf(charArray);
            }
            i++;
        }
        return str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0044, code lost:
        r8 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0048, code lost:
        if (r7 == (r0 + 1)) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004a, code lost:
        r8 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004c, code lost:
        if (r7 == r0) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004f, code lost:
        if (r8 == false) goto L_0x0054;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0051, code lost:
        r6 = r6 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0054, code lost:
        r0 = a(r11.a, r0, r6, r11.c);
        a(r0, r2);
        a(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0065, code lost:
        if (r11.a != 4) goto L_0x0068;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0067, code lost:
        r2 = r2 + r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0068, code lost:
        r0 = r7;
        r6 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x006a, code lost:
        r5 = r5 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b(com.dreamers.exoplayercore.repack.C0054c r11) {
        /*
            r10 = this;
            int r0 = r11.a
            r1 = 1
            if (r0 == r1) goto L_0x0081
            int r0 = r11.a
            r2 = 8
            if (r0 == r2) goto L_0x0081
            int r0 = r11.b
            int r2 = r11.a
            int r0 = r10.d(r0, r2)
            int r2 = r11.b
            int r3 = r11.a
            r4 = 0
            switch(r3) {
                case 2: goto L_0x002d;
                case 3: goto L_0x001b;
                case 4: goto L_0x002b;
                default: goto L_0x001b;
            }
        L_0x001b:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "op should be remove or update."
            java.lang.String r11 = java.lang.String.valueOf(r11)
            java.lang.String r11 = r1.concat(r11)
            r0.<init>(r11)
            throw r0
        L_0x002b:
            r3 = 1
            goto L_0x002e
        L_0x002d:
            r3 = 0
        L_0x002e:
            r5 = 1
            r6 = 1
        L_0x0030:
            int r7 = r11.d
            if (r5 >= r7) goto L_0x006d
            int r7 = r11.b
            int r8 = r3 * r5
            int r7 = r7 + r8
            int r8 = r11.a
            int r7 = r10.d(r7, r8)
            int r8 = r11.a
            switch(r8) {
                case 2: goto L_0x004c;
                case 3: goto L_0x0044;
                case 4: goto L_0x0046;
                default: goto L_0x0044;
            }
        L_0x0044:
            r8 = 0
            goto L_0x004f
        L_0x0046:
            int r8 = r0 + 1
            if (r7 != r8) goto L_0x0044
        L_0x004a:
            r8 = 1
            goto L_0x004f
        L_0x004c:
            if (r7 != r0) goto L_0x0044
            goto L_0x004a
        L_0x004f:
            if (r8 == 0) goto L_0x0054
            int r6 = r6 + 1
            goto L_0x006a
        L_0x0054:
            int r8 = r11.a
            java.lang.Object r9 = r11.c
            com.dreamers.exoplayercore.repack.c r0 = r10.a(r8, r0, r6, r9)
            r10.a((com.dreamers.exoplayercore.repack.C0054c) r0, (int) r2)
            r10.a((com.dreamers.exoplayercore.repack.C0054c) r0)
            int r0 = r11.a
            r8 = 4
            if (r0 != r8) goto L_0x0068
            int r2 = r2 + r6
        L_0x0068:
            r0 = r7
            r6 = 1
        L_0x006a:
            int r5 = r5 + 1
            goto L_0x0030
        L_0x006d:
            java.lang.Object r1 = r11.c
            r10.a((com.dreamers.exoplayercore.repack.C0054c) r11)
            if (r6 <= 0) goto L_0x0080
            int r11 = r11.a
            com.dreamers.exoplayercore.repack.c r11 = r10.a(r11, r0, r6, r1)
            r10.a((com.dreamers.exoplayercore.repack.C0054c) r11, (int) r2)
            r10.a((com.dreamers.exoplayercore.repack.C0054c) r11)
        L_0x0080:
            return
        L_0x0081:
            java.lang.IllegalArgumentException r11 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "should not dispatch add or move for pre layout"
            r11.<init>(r0)
            goto L_0x008a
        L_0x0089:
            throw r11
        L_0x008a:
            goto L_0x0089
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dreamers.exoplayercore.repack.C0000a.b(com.dreamers.exoplayercore.repack.c):void");
    }

    public static void b(Object obj, Object obj2) {
        if (obj == null) {
            throw new NullPointerException("null key in entry: null=".concat(String.valueOf(obj2)));
        } else if (obj2 == null) {
            throw new NullPointerException("null value in entry: " + obj + "=null");
        }
    }

    private void b(List list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            a((C0054c) list.get(i));
        }
        list.clear();
    }

    public static void b(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }

    private static boolean b(char c2) {
        return c2 >= 'A' && c2 <= 'Z';
    }

    private static int c(char c2) {
        return (char) ((c2 | ' ') - 'a');
    }

    public static int c(int i, int i2) {
        if (i >= 0 && i <= i2) {
            return i;
        }
        throw new IndexOutOfBoundsException(a(i, i2, "index"));
    }

    private static String c(Object obj) {
        try {
            return String.valueOf(obj);
        } catch (Exception e2) {
            String str = obj.getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(obj));
            Logger.getLogger("com.google.common.base.Strings").log(Level.WARNING, "Exception during lenientFormat for ".concat(String.valueOf(str)), e2);
            return "<" + str + " threw " + e2.getClass().getName() + ">";
        }
    }

    private void c(C0054c cVar) {
        this.d.add(cVar);
        switch (cVar.a) {
            case 1:
                this.e.c(cVar.b, cVar.d);
                return;
            case 2:
                this.e.b(cVar.b, cVar.d);
                return;
            case 4:
                this.e.a(cVar.b, cVar.d, cVar.c);
                return;
            case 8:
                this.e.d(cVar.b, cVar.d);
                return;
            default:
                throw new IllegalArgumentException("Unknown update op type for ".concat(String.valueOf(cVar)));
        }
    }

    public static void c(boolean z) {
        if (!z) {
            throw new IllegalStateException(String.valueOf("no calls to next() since the last call to remove()"));
        }
    }

    public static int d(int i) {
        return (int) (((long) Integer.rotateLeft((int) (((long) i) * -862048943), 15)) * 461845907);
    }

    private int d(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        for (int size = this.d.size() - 1; size >= 0; size--) {
            C0054c cVar = (C0054c) this.d.get(size);
            if (cVar.a == 8) {
                if (cVar.b < cVar.d) {
                    i5 = cVar.b;
                    i4 = cVar.d;
                } else {
                    i5 = cVar.d;
                    i4 = cVar.b;
                }
                if (i < i5 || i > i4) {
                    if (i < cVar.b) {
                        if (i2 == 1) {
                            cVar.b++;
                            i6 = cVar.d + 1;
                        } else if (i2 == 2) {
                            cVar.b--;
                            i6 = cVar.d - 1;
                        }
                        cVar.d = i6;
                    }
                } else if (i5 == cVar.b) {
                    if (i2 == 1) {
                        i8 = cVar.d + 1;
                    } else {
                        if (i2 == 2) {
                            i8 = cVar.d - 1;
                        }
                        i++;
                    }
                    cVar.d = i8;
                    i++;
                } else {
                    if (i2 == 1) {
                        i7 = cVar.b + 1;
                    } else {
                        if (i2 == 2) {
                            i7 = cVar.b - 1;
                        }
                        i--;
                    }
                    cVar.b = i7;
                    i--;
                }
            } else if (cVar.b > i) {
                if (i2 == 1) {
                    i3 = cVar.b + 1;
                } else if (i2 == 2) {
                    i3 = cVar.b - 1;
                }
                cVar.b = i3;
            } else if (cVar.a == 1) {
                i -= cVar.d;
            } else if (cVar.a == 2) {
                i += cVar.d;
            }
        }
        for (int size2 = this.d.size() - 1; size2 >= 0; size2--) {
            C0054c cVar2 = (C0054c) this.d.get(size2);
            if (cVar2.a == 8) {
                if (cVar2.d != cVar2.b && cVar2.d >= 0) {
                }
            } else if (cVar2.d > 0) {
            }
            this.d.remove(size2);
            a(cVar2);
        }
        return i;
    }

    public static int e(int i) {
        int max = Math.max(i, 2);
        int highestOneBit = Integer.highestOneBit(max);
        double d2 = (double) highestOneBit;
        Double.isNaN(d2);
        if (max <= ((int) (d2 * 1.0d))) {
            return highestOneBit;
        }
        int i2 = highestOneBit << 1;
        return i2 > 0 ? i2 : Declaration.MODULE_REFERENCE;
    }

    private boolean f(int i) {
        int size = this.d.size();
        for (int i2 = 0; i2 < size; i2++) {
            C0054c cVar = (C0054c) this.d.get(i2);
            if (cVar.a == 8) {
                if (a(cVar.d, i2 + 1) == i) {
                    return true;
                }
            } else if (cVar.a == 1) {
                int i3 = cVar.b + cVar.d;
                for (int i4 = cVar.b; i4 < i3; i4++) {
                    if (a(i4, i2 + 1) == i) {
                        return true;
                    }
                }
                continue;
            } else {
                continue;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public final int a(int i, int i2) {
        int size = this.d.size();
        while (i2 < size) {
            C0054c cVar = (C0054c) this.d.get(i2);
            if (cVar.a == 8) {
                if (cVar.b == i) {
                    i = cVar.d;
                } else {
                    if (cVar.b < i) {
                        i--;
                    }
                    if (cVar.d <= i) {
                        i++;
                    }
                }
            } else if (cVar.b > i) {
                continue;
            } else if (cVar.a == 2) {
                if (i < cVar.b + cVar.d) {
                    return -1;
                }
                i -= cVar.d;
            } else if (cVar.a == 1) {
                i += cVar.d;
            }
            i2++;
        }
        return i;
    }

    public final C0054c a(int i, int i2, int i3, Object obj) {
        C0054c cVar = (C0054c) this.c.acquire();
        if (cVar == null) {
            return new C0054c(i, i2, i3, obj);
        }
        cVar.a = i;
        cVar.b = i2;
        cVar.d = i3;
        cVar.c = obj;
        return cVar;
    }

    /* access modifiers changed from: package-private */
    public final void a() {
        b((List) this.a);
        b((List) this.d);
        this.b = 0;
    }

    public final void a(C0054c cVar) {
        cVar.c = null;
        this.c.release(cVar);
    }

    /* access modifiers changed from: package-private */
    public final boolean a(int i) {
        return (i & this.b) != 0;
    }

    /* access modifiers changed from: package-private */
    public final int b(int i) {
        return a(i, 0);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x0254, code lost:
        if (r13 == 0) goto L_0x02a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x02a4, code lost:
        if (r13 == 0) goto L_0x02a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x02a6, code lost:
        b(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x02aa, code lost:
        c(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x02ad, code lost:
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x016c, code lost:
        if (r10.d > r11.b) goto L_0x019e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x019c, code lost:
        if (r10.d >= r11.b) goto L_0x019e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x01b7, code lost:
        r1.add(r2, r8);
     */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x0004 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void b() {
        /*
            r15 = this;
            com.dreamers.exoplayercore.repack.B r0 = r15.f
            java.util.ArrayList r1 = r15.a
        L_0x0004:
            int r2 = r1.size()
            r3 = 1
            int r2 = r2 - r3
            r4 = 0
            r5 = 0
        L_0x000c:
            r6 = -1
            if (r2 < 0) goto L_0x0022
            java.lang.Object r7 = r1.get(r2)
            com.dreamers.exoplayercore.repack.c r7 = (com.dreamers.exoplayercore.repack.C0054c) r7
            int r7 = r7.a
            r8 = 8
            if (r7 != r8) goto L_0x001e
            if (r5 == 0) goto L_0x001f
            goto L_0x0023
        L_0x001e:
            r5 = 1
        L_0x001f:
            int r2 = r2 + -1
            goto L_0x000c
        L_0x0022:
            r2 = -1
        L_0x0023:
            r5 = 2
            r7 = 4
            r8 = 0
            if (r2 == r6) goto L_0x01f2
            int r9 = r2 + 1
            java.lang.Object r10 = r1.get(r2)
            com.dreamers.exoplayercore.repack.c r10 = (com.dreamers.exoplayercore.repack.C0054c) r10
            java.lang.Object r11 = r1.get(r9)
            com.dreamers.exoplayercore.repack.c r11 = (com.dreamers.exoplayercore.repack.C0054c) r11
            int r12 = r11.a
            switch(r12) {
                case 1: goto L_0x01bc;
                case 2: goto L_0x00ab;
                case 3: goto L_0x003b;
                case 4: goto L_0x003c;
                default: goto L_0x003b;
            }
        L_0x003b:
            goto L_0x0004
        L_0x003c:
            int r4 = r10.d
            int r5 = r11.b
            if (r4 >= r5) goto L_0x0048
            int r4 = r11.b
            int r4 = r4 - r3
            r11.b = r4
            goto L_0x0061
        L_0x0048:
            int r4 = r10.d
            int r5 = r11.b
            int r6 = r11.d
            int r5 = r5 + r6
            if (r4 >= r5) goto L_0x0061
            int r4 = r11.d
            int r4 = r4 - r3
            r11.d = r4
            com.dreamers.exoplayercore.repack.C r4 = r0.a
            int r5 = r10.b
            java.lang.Object r6 = r11.c
            com.dreamers.exoplayercore.repack.c r4 = r4.a(r7, r5, r3, r6)
            goto L_0x0062
        L_0x0061:
            r4 = r8
        L_0x0062:
            int r5 = r10.b
            int r6 = r11.b
            if (r5 > r6) goto L_0x006e
            int r5 = r11.b
            int r5 = r5 + r3
            r11.b = r5
            goto L_0x008f
        L_0x006e:
            int r5 = r10.b
            int r6 = r11.b
            int r12 = r11.d
            int r6 = r6 + r12
            if (r5 >= r6) goto L_0x008f
            int r5 = r11.b
            int r6 = r11.d
            int r5 = r5 + r6
            int r6 = r10.b
            int r5 = r5 - r6
            com.dreamers.exoplayercore.repack.C r6 = r0.a
            int r8 = r10.b
            int r8 = r8 + r3
            java.lang.Object r3 = r11.c
            com.dreamers.exoplayercore.repack.c r8 = r6.a(r7, r8, r5, r3)
            int r3 = r11.d
            int r3 = r3 - r5
            r11.d = r3
        L_0x008f:
            r1.set(r9, r10)
            int r3 = r11.d
            if (r3 <= 0) goto L_0x009a
            r1.set(r2, r11)
            goto L_0x00a2
        L_0x009a:
            r1.remove(r2)
            com.dreamers.exoplayercore.repack.C r3 = r0.a
            r3.a(r11)
        L_0x00a2:
            if (r4 == 0) goto L_0x00a7
            r1.add(r2, r4)
        L_0x00a7:
            if (r8 == 0) goto L_0x0004
            goto L_0x01b7
        L_0x00ab:
            int r6 = r10.b
            int r7 = r10.d
            if (r6 >= r7) goto L_0x00c3
            int r6 = r11.b
            int r7 = r10.b
            if (r6 != r7) goto L_0x00c1
            int r6 = r11.d
            int r7 = r10.d
            int r12 = r10.b
            int r7 = r7 - r12
            if (r6 != r7) goto L_0x00c1
            r4 = 1
        L_0x00c1:
            r6 = 0
            goto L_0x00d5
        L_0x00c3:
            int r6 = r11.b
            int r7 = r10.d
            int r7 = r7 + r3
            if (r6 != r7) goto L_0x00d4
            int r6 = r11.d
            int r7 = r10.b
            int r12 = r10.d
            int r7 = r7 - r12
            if (r6 != r7) goto L_0x00d4
            r4 = 1
        L_0x00d4:
            r6 = 1
        L_0x00d5:
            int r7 = r10.d
            int r12 = r11.b
            if (r7 >= r12) goto L_0x00e1
            int r7 = r11.b
            int r7 = r7 - r3
            r11.b = r7
            goto L_0x0101
        L_0x00e1:
            int r7 = r10.d
            int r12 = r11.b
            int r13 = r11.d
            int r12 = r12 + r13
            if (r7 >= r12) goto L_0x0101
            int r2 = r11.d
            int r2 = r2 - r3
            r11.d = r2
            r10.a = r5
            r10.d = r3
            int r2 = r11.d
            if (r2 != 0) goto L_0x0004
            r1.remove(r9)
            com.dreamers.exoplayercore.repack.C r2 = r0.a
            r2.a(r11)
            goto L_0x0004
        L_0x0101:
            int r7 = r10.b
            int r12 = r11.b
            if (r7 > r12) goto L_0x010d
            int r5 = r11.b
            int r5 = r5 + r3
            r11.b = r5
            goto L_0x012e
        L_0x010d:
            int r7 = r10.b
            int r12 = r11.b
            int r13 = r11.d
            int r12 = r12 + r13
            if (r7 >= r12) goto L_0x012e
            int r7 = r11.b
            int r12 = r11.d
            int r7 = r7 + r12
            int r12 = r10.b
            int r7 = r7 - r12
            com.dreamers.exoplayercore.repack.C r12 = r0.a
            int r13 = r10.b
            int r13 = r13 + r3
            com.dreamers.exoplayercore.repack.c r8 = r12.a(r5, r13, r7, r8)
            int r3 = r10.b
            int r5 = r11.b
            int r3 = r3 - r5
            r11.d = r3
        L_0x012e:
            if (r4 == 0) goto L_0x013d
            r1.set(r2, r11)
            r1.remove(r9)
            com.dreamers.exoplayercore.repack.C r2 = r0.a
            r2.a(r10)
            goto L_0x0004
        L_0x013d:
            if (r6 == 0) goto L_0x016f
            if (r8 == 0) goto L_0x015b
            int r3 = r10.b
            int r4 = r8.b
            if (r3 <= r4) goto L_0x014e
            int r3 = r10.b
            int r4 = r8.d
            int r3 = r3 - r4
            r10.b = r3
        L_0x014e:
            int r3 = r10.d
            int r4 = r8.b
            if (r3 <= r4) goto L_0x015b
            int r3 = r10.d
            int r4 = r8.d
            int r3 = r3 - r4
            r10.d = r3
        L_0x015b:
            int r3 = r10.b
            int r4 = r11.b
            if (r3 <= r4) goto L_0x0168
            int r3 = r10.b
            int r4 = r11.d
            int r3 = r3 - r4
            r10.b = r3
        L_0x0168:
            int r3 = r10.d
            int r4 = r11.b
            if (r3 <= r4) goto L_0x01a5
            goto L_0x019e
        L_0x016f:
            if (r8 == 0) goto L_0x018b
            int r3 = r10.b
            int r4 = r8.b
            if (r3 < r4) goto L_0x017e
            int r3 = r10.b
            int r4 = r8.d
            int r3 = r3 - r4
            r10.b = r3
        L_0x017e:
            int r3 = r10.d
            int r4 = r8.b
            if (r3 < r4) goto L_0x018b
            int r3 = r10.d
            int r4 = r8.d
            int r3 = r3 - r4
            r10.d = r3
        L_0x018b:
            int r3 = r10.b
            int r4 = r11.b
            if (r3 < r4) goto L_0x0198
            int r3 = r10.b
            int r4 = r11.d
            int r3 = r3 - r4
            r10.b = r3
        L_0x0198:
            int r3 = r10.d
            int r4 = r11.b
            if (r3 < r4) goto L_0x01a5
        L_0x019e:
            int r3 = r10.d
            int r4 = r11.d
            int r3 = r3 - r4
            r10.d = r3
        L_0x01a5:
            r1.set(r2, r11)
            int r3 = r10.b
            int r4 = r10.d
            if (r3 == r4) goto L_0x01b2
            r1.set(r9, r10)
            goto L_0x01b5
        L_0x01b2:
            r1.remove(r9)
        L_0x01b5:
            if (r8 == 0) goto L_0x0004
        L_0x01b7:
            r1.add(r2, r8)
            goto L_0x0004
        L_0x01bc:
            int r3 = r10.d
            int r5 = r11.b
            if (r3 >= r5) goto L_0x01c3
            r4 = -1
        L_0x01c3:
            int r3 = r10.b
            int r5 = r11.b
            if (r3 >= r5) goto L_0x01cb
            int r4 = r4 + 1
        L_0x01cb:
            int r3 = r11.b
            int r5 = r10.b
            if (r3 > r5) goto L_0x01d8
            int r3 = r10.b
            int r5 = r11.d
            int r3 = r3 + r5
            r10.b = r3
        L_0x01d8:
            int r3 = r11.b
            int r5 = r10.d
            if (r3 > r5) goto L_0x01e5
            int r3 = r10.d
            int r5 = r11.d
            int r3 = r3 + r5
            r10.d = r3
        L_0x01e5:
            int r3 = r11.b
            int r3 = r3 + r4
            r11.b = r3
            r1.set(r2, r11)
            r1.set(r9, r10)
            goto L_0x0004
        L_0x01f2:
            java.util.ArrayList r0 = r15.a
            int r0 = r0.size()
            r1 = 0
        L_0x01f9:
            if (r1 >= r0) goto L_0x02b1
            java.util.ArrayList r2 = r15.a
            java.lang.Object r2 = r2.get(r1)
            com.dreamers.exoplayercore.repack.c r2 = (com.dreamers.exoplayercore.repack.C0054c) r2
            int r9 = r2.a
            switch(r9) {
                case 1: goto L_0x02aa;
                case 2: goto L_0x0257;
                case 4: goto L_0x020a;
                case 8: goto L_0x02aa;
                default: goto L_0x0208;
            }
        L_0x0208:
            goto L_0x02ad
        L_0x020a:
            int r9 = r2.b
            int r10 = r2.b
            int r11 = r2.d
            int r10 = r10 + r11
            int r11 = r2.b
            r12 = 0
            r13 = -1
        L_0x0215:
            if (r11 >= r10) goto L_0x0247
            com.dreamers.exoplayercore.repack.b r14 = r15.e
            com.dreamers.exoplayercore.repack.ai r14 = r14.a(r11)
            if (r14 != 0) goto L_0x0235
            boolean r14 = r15.f(r11)
            if (r14 == 0) goto L_0x0226
            goto L_0x0235
        L_0x0226:
            if (r13 != r3) goto L_0x0233
            java.lang.Object r13 = r2.c
            com.dreamers.exoplayercore.repack.c r9 = r15.a(r7, r9, r12, r13)
            r15.c((com.dreamers.exoplayercore.repack.C0054c) r9)
            r9 = r11
            r12 = 0
        L_0x0233:
            r13 = 0
            goto L_0x0243
        L_0x0235:
            if (r13 != 0) goto L_0x0242
            java.lang.Object r13 = r2.c
            com.dreamers.exoplayercore.repack.c r9 = r15.a(r7, r9, r12, r13)
            r15.b((com.dreamers.exoplayercore.repack.C0054c) r9)
            r9 = r11
            r12 = 0
        L_0x0242:
            r13 = 1
        L_0x0243:
            int r12 = r12 + r3
            int r11 = r11 + 1
            goto L_0x0215
        L_0x0247:
            int r10 = r2.d
            if (r12 == r10) goto L_0x0254
            java.lang.Object r10 = r2.c
            r15.a((com.dreamers.exoplayercore.repack.C0054c) r2)
            com.dreamers.exoplayercore.repack.c r2 = r15.a(r7, r9, r12, r10)
        L_0x0254:
            if (r13 != 0) goto L_0x02aa
            goto L_0x02a6
        L_0x0257:
            int r9 = r2.b
            int r10 = r2.b
            int r11 = r2.d
            int r10 = r10 + r11
            int r11 = r2.b
            r12 = 0
            r13 = -1
        L_0x0262:
            if (r11 >= r10) goto L_0x0299
            com.dreamers.exoplayercore.repack.b r14 = r15.e
            com.dreamers.exoplayercore.repack.ai r14 = r14.a(r11)
            if (r14 != 0) goto L_0x0281
            boolean r14 = r15.f(r11)
            if (r14 == 0) goto L_0x0273
            goto L_0x0281
        L_0x0273:
            if (r13 != r3) goto L_0x027e
            com.dreamers.exoplayercore.repack.c r13 = r15.a(r5, r9, r12, r8)
            r15.c((com.dreamers.exoplayercore.repack.C0054c) r13)
            r13 = 1
            goto L_0x027f
        L_0x027e:
            r13 = 0
        L_0x027f:
            r14 = 0
            goto L_0x028e
        L_0x0281:
            if (r13 != 0) goto L_0x028c
            com.dreamers.exoplayercore.repack.c r13 = r15.a(r5, r9, r12, r8)
            r15.b((com.dreamers.exoplayercore.repack.C0054c) r13)
            r13 = 1
            goto L_0x028d
        L_0x028c:
            r13 = 0
        L_0x028d:
            r14 = 1
        L_0x028e:
            if (r13 == 0) goto L_0x0294
            int r11 = r11 - r12
            int r10 = r10 - r12
            r12 = 1
            goto L_0x0296
        L_0x0294:
            int r12 = r12 + 1
        L_0x0296:
            int r11 = r11 + r3
            r13 = r14
            goto L_0x0262
        L_0x0299:
            int r10 = r2.d
            if (r12 == r10) goto L_0x02a4
            r15.a((com.dreamers.exoplayercore.repack.C0054c) r2)
            com.dreamers.exoplayercore.repack.c r2 = r15.a(r5, r9, r12, r8)
        L_0x02a4:
            if (r13 != 0) goto L_0x02aa
        L_0x02a6:
            r15.b((com.dreamers.exoplayercore.repack.C0054c) r2)
            goto L_0x02ad
        L_0x02aa:
            r15.c((com.dreamers.exoplayercore.repack.C0054c) r2)
        L_0x02ad:
            int r1 = r1 + 1
            goto L_0x01f9
        L_0x02b1:
            java.util.ArrayList r0 = r15.a
            r0.clear()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dreamers.exoplayercore.repack.C0000a.b():void");
    }

    public final int c(int i) {
        int size = this.a.size();
        for (int i2 = 0; i2 < size; i2++) {
            C0054c cVar = (C0054c) this.a.get(i2);
            switch (cVar.a) {
                case 1:
                    if (cVar.b > i) {
                        break;
                    } else {
                        i += cVar.d;
                        break;
                    }
                case 2:
                    if (cVar.b <= i) {
                        if (cVar.b + cVar.d <= i) {
                            i -= cVar.d;
                            break;
                        } else {
                            return -1;
                        }
                    } else {
                        continue;
                    }
                case 8:
                    if (cVar.b != i) {
                        if (cVar.b < i) {
                            i--;
                        }
                        if (cVar.d > i) {
                            break;
                        } else {
                            i++;
                            break;
                        }
                    } else {
                        i = cVar.d;
                        break;
                    }
            }
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public final void c() {
        int size = this.d.size();
        for (int i = 0; i < size; i++) {
            this.d.get(i);
        }
        b((List) this.d);
        this.b = 0;
    }

    /* access modifiers changed from: package-private */
    public final boolean d() {
        return this.a.size() > 0;
    }

    /* access modifiers changed from: package-private */
    public final void e() {
        c();
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            C0054c cVar = (C0054c) this.a.get(i);
            switch (cVar.a) {
                case 1:
                    this.e.c(cVar.b, cVar.d);
                    break;
                case 2:
                    this.e.a(cVar.b, cVar.d);
                    break;
                case 4:
                    this.e.a(cVar.b, cVar.d, cVar.c);
                    break;
                case 8:
                    this.e.d(cVar.b, cVar.d);
                    break;
            }
        }
        b((List) this.a);
        this.b = 0;
    }

    /* access modifiers changed from: package-private */
    public final boolean f() {
        return !this.d.isEmpty() && !this.a.isEmpty();
    }
}
