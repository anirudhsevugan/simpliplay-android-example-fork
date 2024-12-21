package com.dreamers.exoplayerui.repack;

import java.util.Arrays;

public class n {
    private n() {
    }

    public static String a(String str, Object obj) {
        return str + obj;
    }

    private static Throwable a(Throwable th) {
        return a(th, n.class.getName());
    }

    private static Throwable a(Throwable th, String str) {
        StackTraceElement[] stackTrace = th.getStackTrace();
        int length = stackTrace.length;
        int i = -1;
        for (int i2 = 0; i2 < length; i2++) {
            if (str.equals(stackTrace[i2].getClassName())) {
                i = i2;
            }
        }
        th.setStackTrace((StackTraceElement[]) Arrays.copyOfRange(stackTrace, i + 1, length));
        return th;
    }

    public static void a(Object obj) {
        if (obj == null) {
            throw ((NullPointerException) a((Throwable) new NullPointerException()));
        }
    }

    public static void a(Object obj, String str) {
        if (obj == null) {
            throw ((NullPointerException) a((Throwable) new NullPointerException(str + " must not be null")));
        }
    }

    public static boolean a(Object obj, Object obj2) {
        if (obj == null) {
            return false;
        }
        return obj.equals(obj2);
    }

    public static void b(Object obj, String str) {
        if (obj == null) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];
            String className = stackTraceElement.getClassName();
            throw ((NullPointerException) a((Throwable) new NullPointerException("Parameter specified as non-null is null: method " + className + "." + stackTraceElement.getMethodName() + ", parameter " + str)));
        }
    }
}
