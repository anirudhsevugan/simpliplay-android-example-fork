package com.dreamers.exoplayercore.repack;

public final class cT {
    private static cU a;

    /* JADX WARNING: Removed duplicated region for block: B:17:0x005c  */
    /* JADX WARNING: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    static {
        /*
            java.lang.Integer r0 = a()     // Catch:{ all -> 0x002c }
            if (r0 == 0) goto L_0x0014
            int r1 = r0.intValue()     // Catch:{ all -> 0x002a }
            r2 = 19
            if (r1 < r2) goto L_0x0014
            com.dreamers.exoplayercore.repack.cZ r1 = new com.dreamers.exoplayercore.repack.cZ     // Catch:{ all -> 0x002a }
            r1.<init>()     // Catch:{ all -> 0x002a }
            goto L_0x0058
        L_0x0014:
            java.lang.String r1 = "com.google.devtools.build.android.desugar.runtime.twr_disable_mimic"
            boolean r1 = java.lang.Boolean.getBoolean(r1)     // Catch:{ all -> 0x002a }
            r1 = r1 ^ 1
            if (r1 == 0) goto L_0x0024
            com.dreamers.exoplayercore.repack.cX r1 = new com.dreamers.exoplayercore.repack.cX     // Catch:{ all -> 0x002a }
            r1.<init>()     // Catch:{ all -> 0x002a }
            goto L_0x0058
        L_0x0024:
            com.dreamers.exoplayercore.repack.cY r1 = new com.dreamers.exoplayercore.repack.cY     // Catch:{ all -> 0x002a }
            r1.<init>()     // Catch:{ all -> 0x002a }
            goto L_0x0058
        L_0x002a:
            r1 = move-exception
            goto L_0x002e
        L_0x002c:
            r1 = move-exception
            r0 = 0
        L_0x002e:
            java.io.PrintStream r2 = java.lang.System.err
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "An error has occurred when initializing the try-with-resources desuguring strategy. The default strategy "
            r3.<init>(r4)
            java.lang.Class<com.dreamers.exoplayercore.repack.cY> r4 = com.dreamers.exoplayercore.repack.cY.class
            java.lang.String r4 = r4.getName()
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r4 = "will be used. The error is: "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            r2.println(r3)
            java.io.PrintStream r2 = java.lang.System.err
            r1.printStackTrace(r2)
            com.dreamers.exoplayercore.repack.cY r1 = new com.dreamers.exoplayercore.repack.cY
            r1.<init>()
        L_0x0058:
            a = r1
            if (r0 == 0) goto L_0x005f
            r0.intValue()
        L_0x005f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dreamers.exoplayercore.repack.cT.<clinit>():void");
    }

    private static Integer a() {
        try {
            return (Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get((Object) null);
        } catch (Exception e) {
            System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            e.printStackTrace(System.err);
            return null;
        }
    }

    public static void a(Throwable th, Throwable th2) {
        a.a(th, th2);
    }
}
