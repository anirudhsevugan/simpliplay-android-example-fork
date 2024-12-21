package com.google.android.exoplayer2.video.spherical;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.view.Display;

final class OrientationListener implements SensorEventListener {
    private final float[] a = new float[16];
    private final float[] b = new float[16];
    private final float[] c = new float[16];
    private final float[] d = new float[3];
    private final Display e;
    private final Listener[] f;
    private boolean g;

    public interface Listener {
        void a(float[] fArr, float f);
    }

    public OrientationListener(Display display, Listener... listenerArr) {
        this.e = display;
        this.f = listenerArr;
    }

    private void a(float[] fArr, float f2) {
        for (Listener a2 : this.f) {
            a2.a(fArr, f2);
        }
    }

    public final void onAccuracyChanged(Sensor sensor, int i) {
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x005d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onSensorChanged(android.hardware.SensorEvent r22) {
        /*
            r21 = this;
            r0 = r21
            float[] r1 = r0.a
            r2 = r22
            float[] r2 = r2.values
            android.hardware.SensorManager.getRotationMatrixFromVector(r1, r2)
            float[] r1 = r0.a
            android.view.Display r2 = r0.e
            int r2 = r2.getRotation()
            r3 = 16
            r4 = 130(0x82, float:1.82E-43)
            r5 = 129(0x81, float:1.81E-43)
            r6 = 2
            r7 = 1
            r8 = 0
            switch(r2) {
                case 0: goto L_0x0037;
                case 1: goto L_0x002c;
                case 2: goto L_0x0027;
                case 3: goto L_0x0025;
                default: goto L_0x001f;
            }
        L_0x001f:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            r1.<init>()
            throw r1
        L_0x0025:
            r5 = 1
            goto L_0x002d
        L_0x0027:
            r4 = 129(0x81, float:1.81E-43)
            r5 = 130(0x82, float:1.82E-43)
            goto L_0x002d
        L_0x002c:
            r4 = 2
        L_0x002d:
            float[] r2 = r0.b
            java.lang.System.arraycopy(r1, r8, r2, r8, r3)
            float[] r2 = r0.b
            android.hardware.SensorManager.remapCoordinateSystem(r2, r4, r5, r1)
        L_0x0037:
            float[] r1 = r0.a
            r2 = 131(0x83, float:1.84E-43)
            float[] r4 = r0.b
            android.hardware.SensorManager.remapCoordinateSystem(r1, r7, r2, r4)
            float[] r1 = r0.b
            float[] r2 = r0.d
            android.hardware.SensorManager.getOrientation(r1, r2)
            float[] r1 = r0.d
            r1 = r1[r6]
            float[] r9 = r0.a
            r10 = 0
            r11 = 1119092736(0x42b40000, float:90.0)
            r12 = 1065353216(0x3f800000, float:1.0)
            r13 = 0
            r14 = 0
            android.opengl.Matrix.rotateM(r9, r10, r11, r12, r13, r14)
            float[] r15 = r0.a
            boolean r2 = r0.g
            if (r2 != 0) goto L_0x0064
            float[] r2 = r0.c
            com.google.android.exoplayer2.video.spherical.FrameRotationQueue.a(r2, r15)
            r0.g = r7
        L_0x0064:
            float[] r2 = r0.b
            java.lang.System.arraycopy(r15, r8, r2, r8, r3)
            r16 = 0
            float[] r2 = r0.b
            r18 = 0
            float[] r3 = r0.c
            r20 = 0
            r17 = r2
            r19 = r3
            android.opengl.Matrix.multiplyMM(r15, r16, r17, r18, r19, r20)
            float[] r2 = r0.a
            r0.a(r2, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.video.spherical.OrientationListener.onSensorChanged(android.hardware.SensorEvent):void");
    }
}
