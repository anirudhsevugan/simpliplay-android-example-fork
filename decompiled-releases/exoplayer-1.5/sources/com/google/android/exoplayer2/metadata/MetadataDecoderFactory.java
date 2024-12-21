package com.google.android.exoplayer2.metadata;

import com.google.android.exoplayer2.Format;

public interface MetadataDecoderFactory {
    public static final MetadataDecoderFactory a = new MetadataDecoderFactory() {
        public final boolean a(Format format) {
            String str = format.l;
            return "application/id3".equals(str) || "application/x-emsg".equals(str) || "application/x-scte35".equals(str) || "application/x-icy".equals(str) || "application/vnd.dvb.ait".equals(str);
        }

        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.google.android.exoplayer2.metadata.MetadataDecoder b(com.google.android.exoplayer2.Format r4) {
            /*
                r3 = this;
                java.lang.String r4 = r4.l
                if (r4 == 0) goto L_0x0061
                int r0 = r4.hashCode()
                switch(r0) {
                    case -1354451219: goto L_0x0034;
                    case -1348231605: goto L_0x002a;
                    case -1248341703: goto L_0x0020;
                    case 1154383568: goto L_0x0016;
                    case 1652648887: goto L_0x000c;
                    default: goto L_0x000b;
                }
            L_0x000b:
                goto L_0x003e
            L_0x000c:
                java.lang.String r0 = "application/x-scte35"
                boolean r0 = r4.equals(r0)
                if (r0 == 0) goto L_0x003e
                r0 = 2
                goto L_0x003f
            L_0x0016:
                java.lang.String r0 = "application/x-emsg"
                boolean r0 = r4.equals(r0)
                if (r0 == 0) goto L_0x003e
                r0 = 1
                goto L_0x003f
            L_0x0020:
                java.lang.String r0 = "application/id3"
                boolean r0 = r4.equals(r0)
                if (r0 == 0) goto L_0x003e
                r0 = 0
                goto L_0x003f
            L_0x002a:
                java.lang.String r0 = "application/x-icy"
                boolean r0 = r4.equals(r0)
                if (r0 == 0) goto L_0x003e
                r0 = 3
                goto L_0x003f
            L_0x0034:
                java.lang.String r0 = "application/vnd.dvb.ait"
                boolean r0 = r4.equals(r0)
                if (r0 == 0) goto L_0x003e
                r0 = 4
                goto L_0x003f
            L_0x003e:
                r0 = -1
            L_0x003f:
                switch(r0) {
                    case 0: goto L_0x005b;
                    case 1: goto L_0x0055;
                    case 2: goto L_0x004f;
                    case 3: goto L_0x0049;
                    case 4: goto L_0x0043;
                    default: goto L_0x0042;
                }
            L_0x0042:
                goto L_0x0061
            L_0x0043:
                com.google.android.exoplayer2.metadata.dvbsi.AppInfoTableDecoder r4 = new com.google.android.exoplayer2.metadata.dvbsi.AppInfoTableDecoder
                r4.<init>()
                return r4
            L_0x0049:
                com.google.android.exoplayer2.metadata.icy.IcyDecoder r4 = new com.google.android.exoplayer2.metadata.icy.IcyDecoder
                r4.<init>()
                return r4
            L_0x004f:
                com.google.android.exoplayer2.metadata.scte35.SpliceInfoDecoder r4 = new com.google.android.exoplayer2.metadata.scte35.SpliceInfoDecoder
                r4.<init>()
                return r4
            L_0x0055:
                com.google.android.exoplayer2.metadata.emsg.EventMessageDecoder r4 = new com.google.android.exoplayer2.metadata.emsg.EventMessageDecoder
                r4.<init>()
                return r4
            L_0x005b:
                com.google.android.exoplayer2.metadata.id3.Id3Decoder r4 = new com.google.android.exoplayer2.metadata.id3.Id3Decoder
                r4.<init>()
                return r4
            L_0x0061:
                java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
                java.lang.String r4 = java.lang.String.valueOf(r4)
                int r1 = r4.length()
                java.lang.String r2 = "Attempted to create decoder for unsupported MIME type: "
                if (r1 == 0) goto L_0x0074
                java.lang.String r4 = r2.concat(r4)
                goto L_0x0079
            L_0x0074:
                java.lang.String r4 = new java.lang.String
                r4.<init>(r2)
            L_0x0079:
                r0.<init>(r4)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.metadata.MetadataDecoderFactory.AnonymousClass1.b(com.google.android.exoplayer2.Format):com.google.android.exoplayer2.metadata.MetadataDecoder");
        }
    };

    boolean a(Format format);

    MetadataDecoder b(Format format);
}
