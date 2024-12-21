package com.dreamers.relativeview.repack;

import java.util.Arrays;

public class b {
    private b() {
    }

    private static Throwable a(Throwable th) {
        return a(th, b.class.getName());
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

    public static void a(Object obj, String str) {
        if (obj == null) {
            throw ((NullPointerException) a(new NullPointerException(str + " must not be null")));
        }
    }

    public static void b(Object obj, String str) {
        if (obj == null) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];
            String className = stackTraceElement.getClassName();
            throw ((NullPointerException) a(new NullPointerException("Parameter specified as non-null is null: method " + className + "." + stackTraceElement.getMethodName() + ", parameter " + str)));
        }
    }
}
