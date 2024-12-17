package com.google.android.exoplayer2.extractor.mp4;

import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract class Atom {
    public final int a;

    final class ContainerAtom extends Atom {
        public final long b;
        public final List c = new ArrayList();
        public final List d = new ArrayList();

        public ContainerAtom(int i, long j) {
            super(i);
            this.b = j;
        }

        public final void a(ContainerAtom containerAtom) {
            this.d.add(containerAtom);
        }

        public final void a(LeafAtom leafAtom) {
            this.c.add(leafAtom);
        }

        public final LeafAtom d(int i) {
            int size = this.c.size();
            for (int i2 = 0; i2 < size; i2++) {
                LeafAtom leafAtom = (LeafAtom) this.c.get(i2);
                if (leafAtom.a == i) {
                    return leafAtom;
                }
            }
            return null;
        }

        public final ContainerAtom e(int i) {
            int size = this.d.size();
            for (int i2 = 0; i2 < size; i2++) {
                ContainerAtom containerAtom = (ContainerAtom) this.d.get(i2);
                if (containerAtom.a == i) {
                    return containerAtom;
                }
            }
            return null;
        }

        public final String toString() {
            String c2 = c(this.a);
            String arrays = Arrays.toString(this.c.toArray());
            String arrays2 = Arrays.toString(this.d.toArray());
            return new StringBuilder(String.valueOf(c2).length() + 22 + String.valueOf(arrays).length() + String.valueOf(arrays2).length()).append(c2).append(" leaves: ").append(arrays).append(" containers: ").append(arrays2).toString();
        }
    }

    final class LeafAtom extends Atom {
        public final ParsableByteArray b;

        public LeafAtom(int i, ParsableByteArray parsableByteArray) {
            super(i);
            this.b = parsableByteArray;
        }
    }

    public Atom(int i) {
        this.a = i;
    }

    public static int a(int i) {
        return (i >> 24) & 255;
    }

    public static int b(int i) {
        return i & 16777215;
    }

    public static String c(int i) {
        return new StringBuilder(4).append((char) (i >>> 24)).append((char) ((i >> 16) & 255)).append((char) ((i >> 8) & 255)).append((char) (i & 255)).toString();
    }

    public String toString() {
        return c(this.a);
    }
}
