package com.google.android.exoplayer2.metadata.icy;

import com.dreamers.exoplayercore.repack.aC;
import com.google.android.exoplayer2.metadata.SimpleMetadataDecoder;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.util.regex.Pattern;

public final class IcyDecoder extends SimpleMetadataDecoder {
    private static final Pattern a = Pattern.compile("(.+?)='(.*?)';", 32);
    private final CharsetDecoder b = aC.c.newDecoder();
    private final CharsetDecoder c = aC.b.newDecoder();

    private String a(ByteBuffer byteBuffer) {
        String charBuffer;
        CharsetDecoder charsetDecoder;
        try {
            charBuffer = this.b.decode(byteBuffer).toString();
            charsetDecoder = this.b;
        } catch (CharacterCodingException e) {
            this.b.reset();
            byteBuffer.rewind();
            try {
                charBuffer = this.c.decode(byteBuffer).toString();
                charsetDecoder = this.c;
            } catch (CharacterCodingException e2) {
                this.c.reset();
                byteBuffer.rewind();
                return null;
            } catch (Throwable th) {
                this.c.reset();
                byteBuffer.rewind();
                throw th;
            }
        } catch (Throwable th2) {
            this.b.reset();
            byteBuffer.rewind();
            throw th2;
        }
        charsetDecoder.reset();
        byteBuffer.rewind();
        return charBuffer;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.exoplayer2.metadata.Metadata a(com.google.android.exoplayer2.metadata.MetadataInputBuffer r8, java.nio.ByteBuffer r9) {
        /*
            r7 = this;
            java.lang.String r8 = r7.a(r9)
            int r0 = r9.limit()
            byte[] r0 = new byte[r0]
            r9.get(r0)
            r9 = 1
            r1 = 0
            r2 = 0
            if (r8 != 0) goto L_0x0021
            com.google.android.exoplayer2.metadata.Metadata r8 = new com.google.android.exoplayer2.metadata.Metadata
            com.google.android.exoplayer2.metadata.Metadata$Entry[] r9 = new com.google.android.exoplayer2.metadata.Metadata.Entry[r9]
            com.google.android.exoplayer2.metadata.icy.IcyInfo r3 = new com.google.android.exoplayer2.metadata.icy.IcyInfo
            r3.<init>(r0, r2, r2)
            r9[r1] = r3
            r8.<init>((com.google.android.exoplayer2.metadata.Metadata.Entry[]) r9)
            return r8
        L_0x0021:
            java.util.regex.Pattern r3 = a
            java.util.regex.Matcher r8 = r3.matcher(r8)
            r3 = r2
            r4 = 0
        L_0x0029:
            boolean r4 = r8.find(r4)
            if (r4 == 0) goto L_0x0067
            java.lang.String r4 = r8.group(r9)
            r5 = 2
            java.lang.String r5 = r8.group(r5)
            if (r4 == 0) goto L_0x0062
            java.lang.String r4 = com.dreamers.exoplayercore.repack.C0000a.a((java.lang.String) r4)
            int r6 = r4.hashCode()
            switch(r6) {
                case -315603473: goto L_0x0050;
                case 1646559960: goto L_0x0046;
                default: goto L_0x0045;
            }
        L_0x0045:
            goto L_0x005a
        L_0x0046:
            java.lang.String r6 = "streamtitle"
            boolean r4 = r4.equals(r6)
            if (r4 == 0) goto L_0x005a
            r4 = 0
            goto L_0x005b
        L_0x0050:
            java.lang.String r6 = "streamurl"
            boolean r4 = r4.equals(r6)
            if (r4 == 0) goto L_0x005a
            r4 = 1
            goto L_0x005b
        L_0x005a:
            r4 = -1
        L_0x005b:
            switch(r4) {
                case 0: goto L_0x0061;
                case 1: goto L_0x005f;
                default: goto L_0x005e;
            }
        L_0x005e:
            goto L_0x0062
        L_0x005f:
            r3 = r5
            goto L_0x0062
        L_0x0061:
            r2 = r5
        L_0x0062:
            int r4 = r8.end()
            goto L_0x0029
        L_0x0067:
            com.google.android.exoplayer2.metadata.Metadata r8 = new com.google.android.exoplayer2.metadata.Metadata
            com.google.android.exoplayer2.metadata.Metadata$Entry[] r9 = new com.google.android.exoplayer2.metadata.Metadata.Entry[r9]
            com.google.android.exoplayer2.metadata.icy.IcyInfo r4 = new com.google.android.exoplayer2.metadata.icy.IcyInfo
            r4.<init>(r0, r2, r3)
            r9[r1] = r4
            r8.<init>((com.google.android.exoplayer2.metadata.Metadata.Entry[]) r9)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.metadata.icy.IcyDecoder.a(com.google.android.exoplayer2.metadata.MetadataInputBuffer, java.nio.ByteBuffer):com.google.android.exoplayer2.metadata.Metadata");
    }
}
