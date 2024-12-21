package com.google.android.exoplayer2.source.hls.playlist;

import android.net.Uri;
import android.util.Base64;
import com.dreamers.exoplayercore.repack.C0000a;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.mp4.PsshAtomUtil;
import com.google.android.exoplayer2.source.UnrecognizedInputFormatException;
import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.UriUtil;
import com.google.android.exoplayer2.util.Util;
import com.google.appinventor.components.runtime.Component;
import gnu.kawa.functions.GetNamedPart;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class HlsPlaylistParser implements ParsingLoadable.Parser {
    private static final Pattern A = Pattern.compile("TIME-OFFSET=(-?[\\d\\.]+)\\b");
    private static final Pattern B = Pattern.compile("#EXT-X-BYTERANGE:(\\d+(?:@\\d+)?)\\b");
    private static final Pattern C = Pattern.compile("BYTERANGE=\"(\\d+(?:@\\d+)?)\\b\"");
    private static final Pattern D = Pattern.compile("BYTERANGE-START=(\\d+)\\b");
    private static final Pattern E = Pattern.compile("BYTERANGE-LENGTH=(\\d+)\\b");
    private static final Pattern F = Pattern.compile("METHOD=(NONE|AES-128|SAMPLE-AES|SAMPLE-AES-CENC|SAMPLE-AES-CTR)\\s*(?:,|$)");
    private static final Pattern G = Pattern.compile("KEYFORMAT=\"(.+?)\"");
    private static final Pattern H = Pattern.compile("KEYFORMATVERSIONS=\"(.+?)\"");
    private static final Pattern I = Pattern.compile("URI=\"(.+?)\"");
    private static final Pattern J = Pattern.compile("IV=([^,.*]+)");
    private static final Pattern K = Pattern.compile("TYPE=(AUDIO|VIDEO|SUBTITLES|CLOSED-CAPTIONS)");
    private static final Pattern L = Pattern.compile("TYPE=(PART|MAP)");
    private static final Pattern M = Pattern.compile("LANGUAGE=\"(.+?)\"");
    private static final Pattern N = Pattern.compile("NAME=\"(.+?)\"");
    private static final Pattern O = Pattern.compile("GROUP-ID=\"(.+?)\"");
    private static final Pattern P = Pattern.compile("CHARACTERISTICS=\"(.+?)\"");
    private static final Pattern Q = Pattern.compile("INSTREAM-ID=\"((?:CC|SERVICE)\\d+)\"");
    private static final Pattern R = b("AUTOSELECT");
    private static final Pattern S = b("DEFAULT");
    private static final Pattern T = b("FORCED");
    private static final Pattern U = b("INDEPENDENT");
    private static final Pattern V = b("GAP");
    private static final Pattern W = b("PRECISE");
    private static final Pattern X = Pattern.compile("VALUE=\"(.+?)\"");
    private static final Pattern Y = Pattern.compile("IMPORT=\"(.+?)\"");
    private static final Pattern Z = Pattern.compile("\\{\\$([a-zA-Z0-9\\-_]+)\\}");
    private static final Pattern a = Pattern.compile("AVERAGE-BANDWIDTH=(\\d+)\\b");
    private static final Pattern b = Pattern.compile("VIDEO=\"(.+?)\"");
    private static final Pattern c = Pattern.compile("AUDIO=\"(.+?)\"");
    private static final Pattern d = Pattern.compile("SUBTITLES=\"(.+?)\"");
    private static final Pattern e = Pattern.compile("CLOSED-CAPTIONS=\"(.+?)\"");
    private static final Pattern f = Pattern.compile("[^-]BANDWIDTH=(\\d+)\\b");
    private static final Pattern g = Pattern.compile("CHANNELS=\"(.+?)\"");
    private static final Pattern h = Pattern.compile("CODECS=\"(.+?)\"");
    private static final Pattern i = Pattern.compile("RESOLUTION=(\\d+x\\d+)");
    private static final Pattern j = Pattern.compile("FRAME-RATE=([\\d\\.]+)\\b");
    private static final Pattern k = Pattern.compile("#EXT-X-TARGETDURATION:(\\d+)\\b");
    private static final Pattern l = Pattern.compile("DURATION=([\\d\\.]+)\\b");
    private static final Pattern m = Pattern.compile("PART-TARGET=([\\d\\.]+)\\b");
    private static final Pattern n = Pattern.compile("#EXT-X-VERSION:(\\d+)\\b");
    private static final Pattern o = Pattern.compile("#EXT-X-PLAYLIST-TYPE:(.+)\\b");
    private static final Pattern p = Pattern.compile("CAN-SKIP-UNTIL=([\\d\\.]+)\\b");
    private static final Pattern q = b("CAN-SKIP-DATERANGES");
    private static final Pattern r = Pattern.compile("SKIPPED-SEGMENTS=(\\d+)\\b");
    private static final Pattern s = Pattern.compile("[:|,]HOLD-BACK=([\\d\\.]+)\\b");
    private static final Pattern t = Pattern.compile("PART-HOLD-BACK=([\\d\\.]+)\\b");
    private static final Pattern u = b("CAN-BLOCK-RELOAD");
    private static final Pattern v = Pattern.compile("#EXT-X-MEDIA-SEQUENCE:(\\d+)\\b");
    private static final Pattern w = Pattern.compile("#EXTINF:([\\d\\.]+)\\b");
    private static final Pattern x = Pattern.compile("#EXTINF:[\\d\\.]+\\b,(.+)");
    private static final Pattern y = Pattern.compile("LAST-MSN=(\\d+)\\b");
    private static final Pattern z = Pattern.compile("LAST-PART=(\\d+)\\b");
    private final HlsMasterPlaylist aa;
    private final HlsMediaPlaylist ab;

    public final class DeltaUpdateException extends IOException {
    }

    class LineIterator {
        private final BufferedReader a;
        private final Queue b;
        private String c;

        public LineIterator(Queue queue, BufferedReader bufferedReader) {
            this.b = queue;
            this.a = bufferedReader;
        }

        public final boolean a() {
            String trim;
            if (this.c != null) {
                return true;
            }
            if (!this.b.isEmpty()) {
                this.c = (String) Assertions.b((Object) (String) this.b.poll());
                return true;
            }
            do {
                String readLine = this.a.readLine();
                this.c = readLine;
                if (readLine == null) {
                    return false;
                }
                trim = readLine.trim();
                this.c = trim;
            } while (trim.isEmpty());
            return true;
        }

        public final String b() {
            if (a()) {
                String str = this.c;
                this.c = null;
                return str;
            }
            throw new NoSuchElementException();
        }
    }

    public HlsPlaylistParser() {
        this(HlsMasterPlaylist.a, (HlsMediaPlaylist) null);
    }

    public HlsPlaylistParser(HlsMasterPlaylist hlsMasterPlaylist, HlsMediaPlaylist hlsMediaPlaylist) {
        this.aa = hlsMasterPlaylist;
        this.ab = hlsMediaPlaylist;
    }

    private static int a(BufferedReader bufferedReader, boolean z2, int i2) {
        while (i2 != -1 && Character.isWhitespace(i2) && (z2 || !Util.a(i2))) {
            i2 = bufferedReader.read();
        }
        return i2;
    }

    private static int a(String str, Pattern pattern) {
        return Integer.parseInt(a(str, pattern, Collections.emptyMap()));
    }

    private static int a(String str, Pattern pattern, int i2) {
        Matcher matcher = pattern.matcher(str);
        return matcher.find() ? Integer.parseInt((String) Assertions.b((Object) matcher.group(1))) : i2;
    }

    private static long a(String str, Pattern pattern, long j2) {
        Matcher matcher = pattern.matcher(str);
        return matcher.find() ? Long.parseLong((String) Assertions.b((Object) matcher.group(1))) : j2;
    }

    private static DrmInitData.SchemeData a(String str, String str2, Map map) {
        String a2 = a(str, H, Component.TYPEFACE_SANSSERIF, map);
        if ("urn:uuid:edef8ba9-79d6-4ace-a3c8-27dcd51d21ed".equals(str2)) {
            String a3 = a(str, I, map);
            return new DrmInitData.SchemeData(C.d, "video/mp4", Base64.decode(a3.substring(a3.indexOf(44)), 0));
        } else if ("com.widevine".equals(str2)) {
            return new DrmInitData.SchemeData(C.d, "hls", Util.c(str));
        } else {
            if (!"com.microsoft.playready".equals(str2) || !Component.TYPEFACE_SANSSERIF.equals(a2)) {
                return null;
            }
            String a4 = a(str, I, map);
            return new DrmInitData.SchemeData(C.e, "video/mp4", PsshAtomUtil.a(C.e, Base64.decode(a4.substring(a4.indexOf(44)), 0)));
        }
    }

    private static DrmInitData a(String str, DrmInitData.SchemeData[] schemeDataArr) {
        DrmInitData.SchemeData[] schemeDataArr2 = new DrmInitData.SchemeData[schemeDataArr.length];
        for (int i2 = 0; i2 < schemeDataArr.length; i2++) {
            schemeDataArr2[i2] = schemeDataArr[i2].a((byte[]) null);
        }
        return new DrmInitData(str, schemeDataArr2);
    }

    private static HlsMasterPlaylist.Variant a(ArrayList arrayList, String str) {
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            HlsMasterPlaylist.Variant variant = (HlsMasterPlaylist.Variant) arrayList.get(i2);
            if (str.equals(variant.d)) {
                return variant;
            }
        }
        return null;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist a(com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistParser.LineIterator r36, java.lang.String r37) {
        /*
            r1 = r37
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.util.HashMap r11 = new java.util.HashMap
            r11.<init>()
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            r10 = 0
            r13 = 0
        L_0x0036:
            boolean r14 = r36.a()
            java.lang.String r15 = "application/x-mpegURL"
            if (r14 == 0) goto L_0x01f8
            java.lang.String r14 = r36.b()
            java.lang.String r9 = "#EXT"
            boolean r9 = r14.startsWith(r9)
            if (r9 == 0) goto L_0x004d
            r8.add(r14)
        L_0x004d:
            java.lang.String r9 = "#EXT-X-I-FRAME-STREAM-INF"
            boolean r9 = r14.startsWith(r9)
            r19 = r10
            java.lang.String r10 = "#EXT-X-DEFINE"
            boolean r10 = r14.startsWith(r10)
            if (r10 == 0) goto L_0x006d
            java.util.regex.Pattern r9 = N
            java.lang.String r9 = a((java.lang.String) r14, (java.util.regex.Pattern) r9, (java.util.Map) r11)
            java.util.regex.Pattern r10 = X
            java.lang.String r10 = a((java.lang.String) r14, (java.util.regex.Pattern) r10, (java.util.Map) r11)
            r11.put(r9, r10)
            goto L_0x00b2
        L_0x006d:
            java.lang.String r10 = "#EXT-X-INDEPENDENT-SEGMENTS"
            boolean r10 = r14.equals(r10)
            if (r10 == 0) goto L_0x0077
            r10 = 1
            goto L_0x0036
        L_0x0077:
            java.lang.String r10 = "#EXT-X-MEDIA"
            boolean r10 = r14.startsWith(r10)
            if (r10 == 0) goto L_0x0083
            r3.add(r14)
            goto L_0x00b2
        L_0x0083:
            java.lang.String r10 = "#EXT-X-SESSION-KEY"
            boolean r10 = r14.startsWith(r10)
            if (r10 == 0) goto L_0x00b5
            java.util.regex.Pattern r9 = G
            java.lang.String r10 = "identity"
            java.lang.String r9 = a((java.lang.String) r14, (java.util.regex.Pattern) r9, (java.lang.String) r10, (java.util.Map) r11)
            com.google.android.exoplayer2.drm.DrmInitData$SchemeData r9 = a((java.lang.String) r14, (java.lang.String) r9, (java.util.Map) r11)
            if (r9 == 0) goto L_0x00b2
            java.util.regex.Pattern r10 = F
            java.lang.String r10 = a((java.lang.String) r14, (java.util.regex.Pattern) r10, (java.util.Map) r11)
            java.lang.String r10 = a((java.lang.String) r10)
            com.google.android.exoplayer2.drm.DrmInitData r14 = new com.google.android.exoplayer2.drm.DrmInitData
            r15 = 1
            com.google.android.exoplayer2.drm.DrmInitData$SchemeData[] r15 = new com.google.android.exoplayer2.drm.DrmInitData.SchemeData[r15]
            r16 = 0
            r15[r16] = r9
            r14.<init>((java.lang.String) r10, (com.google.android.exoplayer2.drm.DrmInitData.SchemeData[]) r15)
            r12.add(r14)
        L_0x00b2:
            r10 = r19
            goto L_0x0036
        L_0x00b5:
            java.lang.String r10 = "#EXT-X-STREAM-INF"
            boolean r10 = r14.startsWith(r10)
            if (r10 != 0) goto L_0x00d1
            if (r9 == 0) goto L_0x00c0
            goto L_0x00d1
        L_0x00c0:
            r34 = r3
            r31 = r4
            r32 = r5
            r33 = r6
            r29 = r7
            r30 = r8
            r28 = r12
            r4 = r0
            goto L_0x01dd
        L_0x00d1:
            java.lang.String r10 = "CLOSED-CAPTIONS=NONE"
            boolean r10 = r14.contains(r10)
            r10 = r10 | r13
            if (r9 == 0) goto L_0x00df
            r13 = 16384(0x4000, float:2.2959E-41)
            r20 = r10
            goto L_0x00e2
        L_0x00df:
            r20 = r10
            r13 = 0
        L_0x00e2:
            java.util.regex.Pattern r10 = f
            int r10 = a((java.lang.String) r14, (java.util.regex.Pattern) r10)
            r28 = r12
            java.util.regex.Pattern r12 = a
            r29 = r7
            r7 = -1
            int r12 = a((java.lang.String) r14, (java.util.regex.Pattern) r12, (int) r7)
            java.util.regex.Pattern r7 = h
            r30 = r8
            r8 = 0
            java.lang.String r7 = a((java.lang.String) r14, (java.util.regex.Pattern) r7, (java.lang.String) r8, (java.util.Map) r11)
            r31 = r4
            java.util.regex.Pattern r4 = i
            java.lang.String r4 = a((java.lang.String) r14, (java.util.regex.Pattern) r4, (java.lang.String) r8, (java.util.Map) r11)
            if (r4 == 0) goto L_0x012c
            java.lang.String r8 = "x"
            java.lang.String[] r4 = com.google.android.exoplayer2.util.Util.a((java.lang.String) r4, (java.lang.String) r8)
            r8 = 0
            r21 = r4[r8]
            int r8 = java.lang.Integer.parseInt(r21)
            r18 = 1
            r4 = r4[r18]
            int r4 = java.lang.Integer.parseInt(r4)
            if (r8 <= 0) goto L_0x0123
            if (r4 > 0) goto L_0x0120
            goto L_0x0123
        L_0x0120:
            r17 = r8
            goto L_0x0126
        L_0x0123:
            r4 = -1
            r17 = -1
        L_0x0126:
            r8 = r4
            r32 = r5
            r4 = r17
            goto L_0x0130
        L_0x012c:
            r32 = r5
            r4 = -1
            r8 = -1
        L_0x0130:
            java.util.regex.Pattern r5 = j
            r33 = r6
            r6 = 0
            java.lang.String r5 = a((java.lang.String) r14, (java.util.regex.Pattern) r5, (java.lang.String) r6, (java.util.Map) r11)
            if (r5 == 0) goto L_0x0140
            float r5 = java.lang.Float.parseFloat(r5)
            goto L_0x0142
        L_0x0140:
            r5 = -1082130432(0xffffffffbf800000, float:-1.0)
        L_0x0142:
            r34 = r3
            java.util.regex.Pattern r3 = b
            java.lang.String r3 = a((java.lang.String) r14, (java.util.regex.Pattern) r3, (java.lang.String) r6, (java.util.Map) r11)
            r35 = r0
            java.util.regex.Pattern r0 = c
            java.lang.String r0 = a((java.lang.String) r14, (java.util.regex.Pattern) r0, (java.lang.String) r6, (java.util.Map) r11)
            r17 = r0
            java.util.regex.Pattern r0 = d
            java.lang.String r0 = a((java.lang.String) r14, (java.util.regex.Pattern) r0, (java.lang.String) r6, (java.util.Map) r11)
            r18 = r0
            java.util.regex.Pattern r0 = e
            java.lang.String r0 = a((java.lang.String) r14, (java.util.regex.Pattern) r0, (java.lang.String) r6, (java.util.Map) r11)
            if (r9 == 0) goto L_0x016f
            java.util.regex.Pattern r6 = I
            java.lang.String r6 = a((java.lang.String) r14, (java.util.regex.Pattern) r6, (java.util.Map) r11)
        L_0x016a:
            android.net.Uri r6 = com.google.android.exoplayer2.util.UriUtil.a(r1, r6)
            goto L_0x017e
        L_0x016f:
            boolean r6 = r36.a()
            if (r6 == 0) goto L_0x01f0
            java.lang.String r6 = r36.b()
            java.lang.String r6 = a((java.lang.String) r6, (java.util.Map) r11)
            goto L_0x016a
        L_0x017e:
            com.google.android.exoplayer2.Format$Builder r9 = new com.google.android.exoplayer2.Format$Builder
            r9.<init>()
            int r14 = r2.size()
            com.google.android.exoplayer2.Format$Builder r9 = r9.a((int) r14)
            r9.j = r15
            r9.h = r7
            r9.f = r12
            r9.g = r10
            r9.p = r4
            r9.q = r8
            r9.r = r5
            r9.e = r13
            com.google.android.exoplayer2.Format r23 = r9.a()
            com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist$Variant r4 = new com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist$Variant
            r21 = r4
            r22 = r6
            r24 = r3
            r25 = r17
            r26 = r18
            r27 = r0
            r21.<init>(r22, r23, r24, r25, r26, r27)
            r2.add(r4)
            r4 = r35
            java.lang.Object r5 = r4.get(r6)
            java.util.ArrayList r5 = (java.util.ArrayList) r5
            if (r5 != 0) goto L_0x01c5
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            r4.put(r6, r5)
        L_0x01c5:
            com.google.android.exoplayer2.source.hls.HlsTrackMetadataEntry$VariantInfo r6 = new com.google.android.exoplayer2.source.hls.HlsTrackMetadataEntry$VariantInfo
            r21 = r6
            r22 = r12
            r23 = r10
            r24 = r3
            r25 = r17
            r26 = r18
            r27 = r0
            r21.<init>(r22, r23, r24, r25, r26, r27)
            r5.add(r6)
            r13 = r20
        L_0x01dd:
            r0 = r4
            r10 = r19
            r12 = r28
            r7 = r29
            r8 = r30
            r4 = r31
            r5 = r32
            r6 = r33
            r3 = r34
            goto L_0x0036
        L_0x01f0:
            com.google.android.exoplayer2.ParserException r0 = new com.google.android.exoplayer2.ParserException
            java.lang.String r1 = "#EXT-X-STREAM-INF must be followed by another line"
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x01f8:
            r34 = r3
            r31 = r4
            r32 = r5
            r33 = r6
            r29 = r7
            r30 = r8
            r19 = r10
            r28 = r12
            r4 = r0
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            r5 = 0
        L_0x0214:
            int r6 = r2.size()
            if (r5 >= r6) goto L_0x0280
            java.lang.Object r6 = r2.get(r5)
            com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist$Variant r6 = (com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist.Variant) r6
            android.net.Uri r7 = r6.a
            boolean r7 = r0.add(r7)
            if (r7 == 0) goto L_0x027d
            com.google.android.exoplayer2.Format r7 = r6.b
            com.google.android.exoplayer2.metadata.Metadata r7 = r7.j
            if (r7 != 0) goto L_0x0230
            r7 = 1
            goto L_0x0231
        L_0x0230:
            r7 = 0
        L_0x0231:
            com.google.android.exoplayer2.util.Assertions.b((boolean) r7)
            com.google.android.exoplayer2.source.hls.HlsTrackMetadataEntry r7 = new com.google.android.exoplayer2.source.hls.HlsTrackMetadataEntry
            android.net.Uri r8 = r6.a
            java.lang.Object r8 = r4.get(r8)
            java.util.ArrayList r8 = (java.util.ArrayList) r8
            java.lang.Object r8 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r8)
            java.util.List r8 = (java.util.List) r8
            r9 = 0
            r7.<init>(r9, r9, r8)
            com.google.android.exoplayer2.metadata.Metadata r8 = new com.google.android.exoplayer2.metadata.Metadata
            r9 = 1
            com.google.android.exoplayer2.metadata.Metadata$Entry[] r10 = new com.google.android.exoplayer2.metadata.Metadata.Entry[r9]
            r9 = 0
            r10[r9] = r7
            r8.<init>((com.google.android.exoplayer2.metadata.Metadata.Entry[]) r10)
            com.google.android.exoplayer2.Format r7 = r6.b
            com.google.android.exoplayer2.Format$Builder r7 = r7.a()
            r7.i = r8
            com.google.android.exoplayer2.Format r22 = r7.a()
            com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist$Variant r7 = new com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist$Variant
            android.net.Uri r8 = r6.a
            java.lang.String r9 = r6.c
            java.lang.String r10 = r6.d
            java.lang.String r12 = r6.e
            java.lang.String r6 = r6.f
            r20 = r7
            r21 = r8
            r23 = r9
            r24 = r10
            r25 = r12
            r26 = r6
            r20.<init>(r21, r22, r23, r24, r25, r26)
            r3.add(r7)
        L_0x027d:
            int r5 = r5 + 1
            goto L_0x0214
        L_0x0280:
            r0 = 0
            r4 = 0
            r8 = 0
        L_0x0283:
            int r5 = r34.size()
            if (r4 >= r5) goto L_0x04ee
            r5 = r34
            java.lang.Object r6 = r5.get(r4)
            java.lang.String r6 = (java.lang.String) r6
            java.util.regex.Pattern r7 = O
            java.lang.String r7 = a((java.lang.String) r6, (java.util.regex.Pattern) r7, (java.util.Map) r11)
            java.util.regex.Pattern r9 = N
            java.lang.String r9 = a((java.lang.String) r6, (java.util.regex.Pattern) r9, (java.util.Map) r11)
            com.google.android.exoplayer2.Format$Builder r10 = new com.google.android.exoplayer2.Format$Builder
            r10.<init>()
            java.lang.String r12 = java.lang.String.valueOf(r7)
            int r12 = r12.length()
            r14 = 1
            int r12 = r12 + r14
            java.lang.String r14 = java.lang.String.valueOf(r9)
            int r14 = r14.length()
            int r12 = r12 + r14
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>(r12)
            java.lang.StringBuilder r12 = r14.append(r7)
            java.lang.String r14 = ":"
            java.lang.StringBuilder r12 = r12.append(r14)
            java.lang.StringBuilder r12 = r12.append(r9)
            java.lang.String r12 = r12.toString()
            r10.a = r12
            r10.b = r9
            r10.j = r15
            java.util.regex.Pattern r12 = S
            boolean r12 = d(r6, r12)
            java.util.regex.Pattern r14 = T
            boolean r14 = d(r6, r14)
            if (r14 == 0) goto L_0x02e2
            r12 = r12 | 2
        L_0x02e2:
            java.util.regex.Pattern r14 = R
            boolean r14 = d(r6, r14)
            if (r14 == 0) goto L_0x02ec
            r12 = r12 | 4
        L_0x02ec:
            r10.d = r12
            java.util.regex.Pattern r12 = P
            r14 = 0
            java.lang.String r12 = a((java.lang.String) r6, (java.util.regex.Pattern) r12, (java.lang.String) r14, (java.util.Map) r11)
            boolean r14 = android.text.TextUtils.isEmpty(r12)
            if (r14 == 0) goto L_0x02ff
            r34 = r5
            r14 = 0
            goto L_0x0334
        L_0x02ff:
            java.lang.String r14 = ","
            java.lang.String[] r12 = com.google.android.exoplayer2.util.Util.a((java.lang.String) r12, (java.lang.String) r14)
            java.lang.String r14 = "public.accessibility.describes-video"
            boolean r14 = com.google.android.exoplayer2.util.Util.a((java.lang.Object[]) r12, (java.lang.Object) r14)
            if (r14 == 0) goto L_0x0312
            r14 = 512(0x200, float:7.175E-43)
            r34 = r5
            goto L_0x0315
        L_0x0312:
            r34 = r5
            r14 = 0
        L_0x0315:
            java.lang.String r5 = "public.accessibility.transcribes-spoken-dialog"
            boolean r5 = com.google.android.exoplayer2.util.Util.a((java.lang.Object[]) r12, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x031f
            r14 = r14 | 4096(0x1000, float:5.74E-42)
        L_0x031f:
            java.lang.String r5 = "public.accessibility.describes-music-and-sound"
            boolean r5 = com.google.android.exoplayer2.util.Util.a((java.lang.Object[]) r12, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x0329
            r14 = r14 | 1024(0x400, float:1.435E-42)
        L_0x0329:
            java.lang.String r5 = "public.easy-to-read"
            boolean r5 = com.google.android.exoplayer2.util.Util.a((java.lang.Object[]) r12, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x0334
            r5 = r14 | 8192(0x2000, float:1.14794E-41)
            r14 = r5
        L_0x0334:
            r10.e = r14
            java.util.regex.Pattern r5 = M
            r12 = 0
            java.lang.String r5 = a((java.lang.String) r6, (java.util.regex.Pattern) r5, (java.lang.String) r12, (java.util.Map) r11)
            r10.c = r5
            java.util.regex.Pattern r5 = I
            java.lang.String r5 = a((java.lang.String) r6, (java.util.regex.Pattern) r5, (java.lang.String) r12, (java.util.Map) r11)
            if (r5 != 0) goto L_0x0349
            r5 = 0
            goto L_0x034d
        L_0x0349:
            android.net.Uri r5 = com.google.android.exoplayer2.util.UriUtil.a(r1, r5)
        L_0x034d:
            com.google.android.exoplayer2.metadata.Metadata r12 = new com.google.android.exoplayer2.metadata.Metadata
            r14 = 1
            com.google.android.exoplayer2.metadata.Metadata$Entry[] r1 = new com.google.android.exoplayer2.metadata.Metadata.Entry[r14]
            com.google.android.exoplayer2.source.hls.HlsTrackMetadataEntry r14 = new com.google.android.exoplayer2.source.hls.HlsTrackMetadataEntry
            r20 = r15
            java.util.List r15 = java.util.Collections.emptyList()
            r14.<init>(r7, r9, r15)
            r15 = 0
            r1[r15] = r14
            r12.<init>((com.google.android.exoplayer2.metadata.Metadata.Entry[]) r1)
            java.util.regex.Pattern r1 = K
            java.lang.String r1 = a((java.lang.String) r6, (java.util.regex.Pattern) r1, (java.util.Map) r11)
            int r14 = r1.hashCode()
            r15 = 2
            switch(r14) {
                case -959297733: goto L_0x0390;
                case -333210994: goto L_0x0386;
                case 62628790: goto L_0x037c;
                case 81665115: goto L_0x0372;
                default: goto L_0x0371;
            }
        L_0x0371:
            goto L_0x039a
        L_0x0372:
            java.lang.String r14 = "VIDEO"
            boolean r1 = r1.equals(r14)
            if (r1 == 0) goto L_0x039a
            r1 = 0
            goto L_0x039b
        L_0x037c:
            java.lang.String r14 = "AUDIO"
            boolean r1 = r1.equals(r14)
            if (r1 == 0) goto L_0x039a
            r1 = 1
            goto L_0x039b
        L_0x0386:
            java.lang.String r14 = "CLOSED-CAPTIONS"
            boolean r1 = r1.equals(r14)
            if (r1 == 0) goto L_0x039a
            r1 = 3
            goto L_0x039b
        L_0x0390:
            java.lang.String r14 = "SUBTITLES"
            boolean r1 = r1.equals(r14)
            if (r1 == 0) goto L_0x039a
            r1 = 2
            goto L_0x039b
        L_0x039a:
            r1 = -1
        L_0x039b:
            switch(r1) {
                case 0: goto L_0x049d;
                case 1: goto L_0x042d;
                case 2: goto L_0x03ec;
                case 3: goto L_0x03ab;
                default: goto L_0x039e;
            }
        L_0x039e:
            r36 = r0
            r5 = r31
            r6 = r32
            r14 = r33
        L_0x03a6:
            r1 = 1
            r16 = 0
            goto L_0x04de
        L_0x03ab:
            java.util.regex.Pattern r1 = Q
            java.lang.String r1 = a((java.lang.String) r6, (java.util.regex.Pattern) r1, (java.util.Map) r11)
            java.lang.String r5 = "CC"
            boolean r5 = r1.startsWith(r5)
            if (r5 == 0) goto L_0x03c4
            java.lang.String r1 = r1.substring(r15)
            int r1 = java.lang.Integer.parseInt(r1)
            java.lang.String r5 = "application/cea-608"
            goto L_0x03cf
        L_0x03c4:
            r5 = 7
            java.lang.String r1 = r1.substring(r5)
            int r1 = java.lang.Integer.parseInt(r1)
            java.lang.String r5 = "application/cea-708"
        L_0x03cf:
            if (r0 != 0) goto L_0x03d6
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
        L_0x03d6:
            r10.k = r5
            r10.C = r1
            com.google.android.exoplayer2.Format r1 = r10.a()
            r0.add(r1)
            r5 = r31
            r6 = r32
            r14 = r33
            r1 = 1
            r16 = 0
            goto L_0x04e0
        L_0x03ec:
            com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist$Variant r1 = c((java.util.ArrayList) r2, (java.lang.String) r7)
            if (r1 == 0) goto L_0x0402
            com.google.android.exoplayer2.Format r1 = r1.b
            java.lang.String r1 = r1.i
            r6 = 3
            java.lang.String r1 = com.google.android.exoplayer2.util.Util.b((java.lang.String) r1, (int) r6)
            r10.h = r1
            java.lang.String r1 = com.google.android.exoplayer2.util.MimeTypes.g(r1)
            goto L_0x0403
        L_0x0402:
            r1 = 0
        L_0x0403:
            if (r1 != 0) goto L_0x0407
            java.lang.String r1 = "text/vtt"
        L_0x0407:
            r10.k = r1
            r10.i = r12
            if (r5 == 0) goto L_0x041c
            com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist$Rendition r1 = new com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist$Rendition
            com.google.android.exoplayer2.Format r6 = r10.a()
            r1.<init>(r5, r6, r9)
            r14 = r33
            r14.add(r1)
            goto L_0x0425
        L_0x041c:
            r14 = r33
            java.lang.String r1 = "HlsPlaylistParser"
            java.lang.String r5 = "EXT-X-MEDIA tag with missing mandatory URI attribute: skipping"
            com.google.android.exoplayer2.util.Log.c(r1, r5)
        L_0x0425:
            r36 = r0
            r5 = r31
            r6 = r32
            goto L_0x03a6
        L_0x042d:
            r14 = r33
            com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist$Variant r1 = a((java.util.ArrayList) r2, (java.lang.String) r7)
            if (r1 == 0) goto L_0x0445
            com.google.android.exoplayer2.Format r7 = r1.b
            java.lang.String r7 = r7.i
            r15 = 1
            java.lang.String r7 = com.google.android.exoplayer2.util.Util.b((java.lang.String) r7, (int) r15)
            r10.h = r7
            java.lang.String r7 = com.google.android.exoplayer2.util.MimeTypes.g(r7)
            goto L_0x0446
        L_0x0445:
            r7 = 0
        L_0x0446:
            java.util.regex.Pattern r15 = g
            r36 = r0
            r0 = 0
            java.lang.String r6 = a((java.lang.String) r6, (java.util.regex.Pattern) r15, (java.lang.String) r0, (java.util.Map) r11)
            if (r6 == 0) goto L_0x0474
            java.lang.String r15 = "/"
            java.lang.String[] r15 = com.google.android.exoplayer2.util.Util.b((java.lang.String) r6, (java.lang.String) r15)
            r16 = 0
            r15 = r15[r16]
            int r15 = java.lang.Integer.parseInt(r15)
            r10.x = r15
            java.lang.String r15 = "audio/eac3"
            boolean r15 = r15.equals(r7)
            if (r15 == 0) goto L_0x0476
            java.lang.String r15 = "/JOC"
            boolean r6 = r6.endsWith(r15)
            if (r6 == 0) goto L_0x0476
            java.lang.String r7 = "audio/eac3-joc"
            goto L_0x0476
        L_0x0474:
            r16 = 0
        L_0x0476:
            r10.k = r7
            if (r5 == 0) goto L_0x048b
            r10.i = r12
            com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist$Rendition r1 = new com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist$Rendition
            com.google.android.exoplayer2.Format r6 = r10.a()
            r1.<init>(r5, r6, r9)
            r6 = r32
            r6.add(r1)
            goto L_0x0499
        L_0x048b:
            r6 = r32
            if (r1 == 0) goto L_0x0499
            com.google.android.exoplayer2.Format r8 = r10.a()
            r0 = r36
            r5 = r31
            r1 = 1
            goto L_0x04e0
        L_0x0499:
            r5 = r31
            r1 = 1
            goto L_0x04de
        L_0x049d:
            r36 = r0
            r6 = r32
            r14 = r33
            r0 = 0
            r1 = 1
            r16 = 0
            com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist$Variant r7 = b((java.util.ArrayList) r2, (java.lang.String) r7)
            if (r7 == 0) goto L_0x04c9
            com.google.android.exoplayer2.Format r7 = r7.b
            java.lang.String r0 = r7.i
            java.lang.String r0 = com.google.android.exoplayer2.util.Util.b((java.lang.String) r0, (int) r15)
            r10.h = r0
            java.lang.String r0 = com.google.android.exoplayer2.util.MimeTypes.g(r0)
            r10.k = r0
            int r0 = r7.width
            r10.p = r0
            int r0 = r7.height
            r10.q = r0
            float r0 = r7.q
            r10.r = r0
        L_0x04c9:
            if (r5 == 0) goto L_0x04dc
            r10.i = r12
            com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist$Rendition r0 = new com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist$Rendition
            com.google.android.exoplayer2.Format r7 = r10.a()
            r0.<init>(r5, r7, r9)
            r5 = r31
            r5.add(r0)
            goto L_0x04de
        L_0x04dc:
            r5 = r31
        L_0x04de:
            r0 = r36
        L_0x04e0:
            int r4 = r4 + 1
            r1 = r37
            r31 = r5
            r32 = r6
            r33 = r14
            r15 = r20
            goto L_0x0283
        L_0x04ee:
            r36 = r0
            r5 = r31
            r6 = r32
            r14 = r33
            if (r13 == 0) goto L_0x04fe
            java.util.List r0 = java.util.Collections.emptyList()
            r9 = r0
            goto L_0x0500
        L_0x04fe:
            r9 = r36
        L_0x0500:
            com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist r13 = new com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist
            r0 = r13
            r1 = r37
            r2 = r30
            r4 = r5
            r5 = r6
            r6 = r14
            r7 = r29
            r10 = r19
            r12 = r28
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistParser.a(com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistParser$LineIterator, java.lang.String):com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist");
    }

    private static HlsMediaPlaylist a(HlsMasterPlaylist hlsMasterPlaylist, HlsMediaPlaylist hlsMediaPlaylist, LineIterator lineIterator, String str) {
        String str2;
        String str3;
        String str4;
        ArrayList arrayList;
        String str5;
        HashMap hashMap;
        String str6;
        long j2;
        String str7;
        HlsMediaPlaylist.Part part;
        int i2;
        boolean z2;
        int i3;
        long j3;
        DrmInitData drmInitData;
        long j4;
        HashMap hashMap2;
        String str8;
        long j5;
        int i4;
        HlsMasterPlaylist hlsMasterPlaylist2 = hlsMasterPlaylist;
        HlsMediaPlaylist hlsMediaPlaylist2 = hlsMediaPlaylist;
        boolean z3 = hlsMasterPlaylist2.u;
        HashMap hashMap3 = new HashMap();
        HashMap hashMap4 = new HashMap();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        HashMap hashMap5 = new HashMap();
        ArrayList arrayList4 = new ArrayList();
        HlsMediaPlaylist.ServerControl serverControl = new HlsMediaPlaylist.ServerControl(-9223372036854775807L, false, -9223372036854775807L, -9223372036854775807L, false);
        TreeMap treeMap = new TreeMap();
        String str9 = "";
        boolean z4 = z3;
        HlsMediaPlaylist.ServerControl serverControl2 = serverControl;
        String str10 = str9;
        boolean z5 = false;
        int i5 = 0;
        HlsMediaPlaylist.Part part2 = null;
        long j6 = -9223372036854775807L;
        long j7 = 0;
        boolean z6 = false;
        int i6 = 0;
        long j8 = 0;
        int i7 = 1;
        long j9 = -9223372036854775807L;
        long j10 = -9223372036854775807L;
        boolean z7 = false;
        DrmInitData drmInitData2 = null;
        long j11 = 0;
        DrmInitData drmInitData3 = null;
        long j12 = 0;
        boolean z8 = false;
        String str11 = null;
        long j13 = -1;
        String str12 = null;
        String str13 = null;
        int i8 = 0;
        long j14 = 0;
        long j15 = 0;
        boolean z9 = false;
        HlsMediaPlaylist.Segment segment = null;
        long j16 = 0;
        long j17 = 0;
        while (lineIterator.a()) {
            String b2 = lineIterator.b();
            if (b2.startsWith("#EXT")) {
                arrayList4.add(b2);
            }
            if (b2.startsWith("#EXT-X-PLAYLIST-TYPE")) {
                String a2 = a(b2, o, (Map) hashMap3);
                if ("VOD".equals(a2)) {
                    i5 = 1;
                } else if ("EVENT".equals(a2)) {
                    i5 = 2;
                }
            } else if (b2.equals("#EXT-X-I-FRAMES-ONLY")) {
                z9 = true;
            } else {
                if (b2.startsWith("#EXT-X-START")) {
                    str2 = str9;
                    z5 = d(b2, W);
                    j6 = (long) (b(b2, A) * 1000000.0d);
                } else {
                    str2 = str9;
                    if (b2.startsWith("#EXT-X-SERVER-CONTROL")) {
                        double c2 = c(b2, p);
                        long j18 = c2 == -9.223372036854776E18d ? -9223372036854775807L : (long) (c2 * 1000000.0d);
                        boolean d2 = d(b2, q);
                        double c3 = c(b2, s);
                        long j19 = c3 == -9.223372036854776E18d ? -9223372036854775807L : (long) (c3 * 1000000.0d);
                        double c4 = c(b2, t);
                        serverControl2 = new HlsMediaPlaylist.ServerControl(j18, d2, j19, c4 == -9.223372036854776E18d ? -9223372036854775807L : (long) (c4 * 1000000.0d), d(b2, u));
                    } else if (b2.startsWith("#EXT-X-PART-INF")) {
                        j10 = (long) (b(b2, m) * 1000000.0d);
                    } else if (b2.startsWith("#EXT-X-MAP")) {
                        String a3 = a(b2, I, (Map) hashMap3);
                        String a4 = a(b2, C, (String) null, (Map) hashMap3);
                        if (a4 != null) {
                            String[] a5 = Util.a(a4, GetNamedPart.CAST_METHOD_NAME);
                            j13 = Long.parseLong(a5[0]);
                            if (a5.length > 1) {
                                j11 = Long.parseLong(a5[1]);
                            }
                        }
                        String str14 = str11;
                        if (j13 == -1) {
                            j11 = 0;
                        }
                        String str15 = str12;
                        if (str14 == null || str15 != null) {
                            segment = new HlsMediaPlaylist.Segment(a3, j11, j13, str14, str15);
                            if (j13 != -1) {
                                j11 += j13;
                            }
                            str12 = str15;
                            str11 = str14;
                            str9 = str2;
                            j13 = -1;
                        } else {
                            throw new ParserException("The encryption IV attribute must be present when an initialization segment is encrypted with METHOD=AES-128.");
                        }
                    } else {
                        String str16 = str11;
                        String str17 = str12;
                        if (b2.startsWith("#EXT-X-TARGETDURATION")) {
                            j9 = 1000000 * ((long) a(b2, k));
                        } else if (b2.startsWith("#EXT-X-MEDIA-SEQUENCE")) {
                            j15 = Long.parseLong(a(b2, v, Collections.emptyMap()));
                            str12 = str17;
                            str11 = str16;
                            j8 = j15;
                        } else if (b2.startsWith("#EXT-X-VERSION")) {
                            i7 = a(b2, n);
                        } else {
                            if (b2.startsWith("#EXT-X-DEFINE")) {
                                String a6 = a(b2, Y, (String) null, (Map) hashMap3);
                                String str18 = str16;
                                if (a6 != null) {
                                    String str19 = (String) hlsMasterPlaylist2.h.get(a6);
                                    if (str19 != null) {
                                        hashMap3.put(a6, str19);
                                    }
                                } else {
                                    hashMap3.put(a(b2, N, (Map) hashMap3), a(b2, X, (Map) hashMap3));
                                }
                                z2 = z5;
                                arrayList = arrayList4;
                                str7 = str17;
                                str3 = str18;
                                hashMap = hashMap5;
                                str6 = str13;
                                j2 = j15;
                                i2 = i5;
                            } else {
                                str3 = str16;
                                if (b2.startsWith("#EXTINF")) {
                                    String str20 = str2;
                                    str9 = str20;
                                    j16 = (long) (b(b2, w) * 1000000.0d);
                                    str12 = str17;
                                    str4 = str3;
                                    str10 = a(b2, x, str20, (Map) hashMap3);
                                } else {
                                    String str21 = str17;
                                    String str22 = str2;
                                    if (b2.startsWith("#EXT-X-SKIP")) {
                                        int a7 = a(b2, r);
                                        Assertions.b(hlsMediaPlaylist2 != null && arrayList2.isEmpty());
                                        int i9 = (int) (j8 - ((HlsMediaPlaylist) Util.a((Object) hlsMediaPlaylist)).g);
                                        int i10 = a7 + i9;
                                        if (i9 < 0 || i10 > hlsMediaPlaylist2.n.size()) {
                                            throw new DeltaUpdateException();
                                        }
                                        str2 = str22;
                                        str12 = str21;
                                        long j20 = j14;
                                        str11 = str3;
                                        while (i9 < i10) {
                                            HlsMediaPlaylist.Segment segment2 = (HlsMediaPlaylist.Segment) hlsMediaPlaylist2.n.get(i9);
                                            ArrayList arrayList5 = arrayList4;
                                            int i11 = i10;
                                            if (j8 != hlsMediaPlaylist2.g) {
                                                segment2 = segment2.a(j20, (hlsMediaPlaylist2.f - i6) + segment2.f);
                                            }
                                            arrayList2.add(segment2);
                                            long j21 = j20 + segment2.e;
                                            if (segment2.l != -1) {
                                                j5 = j21;
                                                j11 = segment2.k + segment2.l;
                                            } else {
                                                j5 = j21;
                                            }
                                            int i12 = segment2.f;
                                            HlsMediaPlaylist.Segment segment3 = segment2.d;
                                            DrmInitData drmInitData4 = segment2.h;
                                            String str23 = segment2.i;
                                            if (segment2.j != null) {
                                                i4 = i12;
                                                if (segment2.j.equals(Long.toHexString(j15))) {
                                                    j15++;
                                                    i9++;
                                                    hlsMediaPlaylist2 = hlsMediaPlaylist;
                                                    segment = segment3;
                                                    drmInitData3 = drmInitData4;
                                                    str11 = str23;
                                                    i10 = i11;
                                                    i8 = i4;
                                                    j20 = j5;
                                                    j12 = j20;
                                                    arrayList4 = arrayList5;
                                                }
                                            } else {
                                                i4 = i12;
                                            }
                                            str12 = segment2.j;
                                            j15++;
                                            i9++;
                                            hlsMediaPlaylist2 = hlsMediaPlaylist;
                                            segment = segment3;
                                            drmInitData3 = drmInitData4;
                                            str11 = str23;
                                            i10 = i11;
                                            i8 = i4;
                                            j20 = j5;
                                            j12 = j20;
                                            arrayList4 = arrayList5;
                                        }
                                        hlsMasterPlaylist2 = hlsMasterPlaylist;
                                        hlsMediaPlaylist2 = hlsMediaPlaylist;
                                        j14 = j20;
                                    } else {
                                        arrayList = arrayList4;
                                        str2 = str22;
                                        if (b2.startsWith("#EXT-X-KEY")) {
                                            String a8 = a(b2, F, (Map) hashMap3);
                                            String a9 = a(b2, G, "identity", (Map) hashMap3);
                                            if ("NONE".equals(a8)) {
                                                treeMap.clear();
                                                hlsMasterPlaylist2 = hlsMasterPlaylist;
                                                hlsMediaPlaylist2 = hlsMediaPlaylist;
                                                str9 = str2;
                                                arrayList4 = arrayList;
                                                drmInitData3 = null;
                                                str11 = null;
                                                str12 = null;
                                            } else {
                                                str12 = a(b2, J, (String) null, (Map) hashMap3);
                                                if (!"identity".equals(a9)) {
                                                    String str24 = str13;
                                                    str13 = str24 == null ? a(a8) : str24;
                                                    DrmInitData.SchemeData a10 = a(b2, a9, (Map) hashMap3);
                                                    if (a10 != null) {
                                                        treeMap.put(a9, a10);
                                                        drmInitData3 = null;
                                                    }
                                                } else if ("AES-128".equals(a8)) {
                                                    str11 = a(b2, I, (Map) hashMap3);
                                                    hlsMasterPlaylist2 = hlsMasterPlaylist;
                                                    hlsMediaPlaylist2 = hlsMediaPlaylist;
                                                    str9 = str2;
                                                    arrayList4 = arrayList;
                                                }
                                                hlsMasterPlaylist2 = hlsMasterPlaylist;
                                                hlsMediaPlaylist2 = hlsMediaPlaylist;
                                                str9 = str2;
                                                arrayList4 = arrayList;
                                                str11 = null;
                                            }
                                        } else {
                                            String str25 = str13;
                                            if (b2.startsWith("#EXT-X-BYTERANGE")) {
                                                String[] a11 = Util.a(a(b2, B, (Map) hashMap3), GetNamedPart.CAST_METHOD_NAME);
                                                j13 = Long.parseLong(a11[0]);
                                                if (a11.length > 1) {
                                                    j11 = Long.parseLong(a11[1]);
                                                }
                                            } else {
                                                if (b2.startsWith("#EXT-X-DISCONTINUITY-SEQUENCE")) {
                                                    i6 = Integer.parseInt(b2.substring(b2.indexOf(58) + 1));
                                                    hlsMediaPlaylist2 = hlsMediaPlaylist;
                                                    str5 = str25;
                                                    str12 = str21;
                                                    str9 = str2;
                                                    arrayList4 = arrayList;
                                                    z6 = true;
                                                } else if (b2.equals("#EXT-X-DISCONTINUITY")) {
                                                    i8++;
                                                } else if (b2.startsWith("#EXT-X-PROGRAM-DATE-TIME")) {
                                                    if (j7 == 0) {
                                                        j7 = C.b(Util.e(b2.substring(b2.indexOf(58) + 1))) - j14;
                                                    } else {
                                                        z2 = z5;
                                                        i2 = i5;
                                                        str7 = str21;
                                                        hashMap = hashMap5;
                                                        str6 = str25;
                                                        j2 = j15;
                                                    }
                                                } else if (b2.equals("#EXT-X-GAP")) {
                                                    hlsMediaPlaylist2 = hlsMediaPlaylist;
                                                    str5 = str25;
                                                    str12 = str21;
                                                    str9 = str2;
                                                    arrayList4 = arrayList;
                                                    z8 = true;
                                                } else if (b2.equals("#EXT-X-INDEPENDENT-SEGMENTS")) {
                                                    hlsMediaPlaylist2 = hlsMediaPlaylist;
                                                    str5 = str25;
                                                    str12 = str21;
                                                    str9 = str2;
                                                    arrayList4 = arrayList;
                                                    z4 = true;
                                                } else if (b2.equals("#EXT-X-ENDLIST")) {
                                                    hlsMediaPlaylist2 = hlsMediaPlaylist;
                                                    str5 = str25;
                                                    str12 = str21;
                                                    str9 = str2;
                                                    arrayList4 = arrayList;
                                                    z7 = true;
                                                } else {
                                                    if (b2.startsWith("#EXT-X-RENDITION-REPORT")) {
                                                        z2 = z5;
                                                        String str26 = str25;
                                                        hashMap5.put(Uri.parse(UriUtil.b(str, a(b2, I, (Map) hashMap3))), new HlsMediaPlaylist.RenditionReport(a(b2, y, (j8 + ((long) arrayList2.size())) - (arrayList3.isEmpty() ? 1 : 0)), a(b2, z, j10 != -9223372036854775807L ? (arrayList3.isEmpty() ? ((HlsMediaPlaylist.Segment) C0000a.b((Iterable) arrayList2)).a : arrayList3).size() - 1 : -1)));
                                                        str7 = str21;
                                                        j2 = j15;
                                                        part = part2;
                                                        hashMap = hashMap5;
                                                        str6 = str26;
                                                        i2 = i5;
                                                    } else {
                                                        z2 = z5;
                                                        String str27 = str25;
                                                        if (!b2.startsWith("#EXT-X-PRELOAD-HINT")) {
                                                            str7 = str21;
                                                            j2 = j15;
                                                            hashMap = hashMap5;
                                                            str6 = str27;
                                                            if (b2.startsWith("#EXT-X-PART")) {
                                                                String a12 = a(j2, str3, str7);
                                                                String a13 = a(b2, I, (Map) hashMap3);
                                                                long b3 = (long) (b(b2, l) * 1000000.0d);
                                                                i2 = i5;
                                                                boolean d3 = d(b2, U) | (z4 && arrayList3.isEmpty());
                                                                boolean d4 = d(b2, V);
                                                                part = part2;
                                                                String a14 = a(b2, C, (String) null, (Map) hashMap3);
                                                                if (a14 != null) {
                                                                    String[] a15 = Util.a(a14, GetNamedPart.CAST_METHOD_NAME);
                                                                    j4 = Long.parseLong(a15[0]);
                                                                    if (a15.length > 1) {
                                                                        j17 = Long.parseLong(a15[1]);
                                                                    }
                                                                } else {
                                                                    j4 = -1;
                                                                }
                                                                if (j4 == -1) {
                                                                    j17 = 0;
                                                                }
                                                                if (drmInitData3 == null && !treeMap.isEmpty()) {
                                                                    DrmInitData.SchemeData[] schemeDataArr = (DrmInitData.SchemeData[]) treeMap.values().toArray(new DrmInitData.SchemeData[0]);
                                                                    DrmInitData drmInitData5 = new DrmInitData(str6, schemeDataArr);
                                                                    if (drmInitData2 == null) {
                                                                        drmInitData2 = a(str6, schemeDataArr);
                                                                    }
                                                                    drmInitData3 = drmInitData5;
                                                                }
                                                                arrayList3.add(new HlsMediaPlaylist.Part(a13, segment, b3, i8, j12, drmInitData3, str3, a12, j17, j4, d4, d3, false));
                                                                j12 += b3;
                                                                if (j4 != -1) {
                                                                    j17 += j4;
                                                                }
                                                            } else {
                                                                i3 = i5;
                                                                part = part2;
                                                                if (!b2.startsWith("#")) {
                                                                    String a16 = a(j2, str3, str7);
                                                                    j2++;
                                                                    String a17 = a(b2, (Map) hashMap3);
                                                                    HlsMediaPlaylist.Segment segment4 = (HlsMediaPlaylist.Segment) hashMap4.get(a17);
                                                                    if (j13 == -1) {
                                                                        j3 = 0;
                                                                    } else {
                                                                        if (z9 && segment == null && segment4 == null) {
                                                                            segment4 = new HlsMediaPlaylist.Segment(a17, 0, j11, (String) null, (String) null);
                                                                            hashMap4.put(a17, segment4);
                                                                        }
                                                                        j3 = j11;
                                                                    }
                                                                    if (drmInitData3 != null || treeMap.isEmpty()) {
                                                                        drmInitData = drmInitData3;
                                                                    } else {
                                                                        DrmInitData.SchemeData[] schemeDataArr2 = (DrmInitData.SchemeData[]) treeMap.values().toArray(new DrmInitData.SchemeData[0]);
                                                                        drmInitData = new DrmInitData(str6, schemeDataArr2);
                                                                        if (drmInitData2 == null) {
                                                                            drmInitData2 = a(str6, schemeDataArr2);
                                                                        }
                                                                    }
                                                                    arrayList2.add(new HlsMediaPlaylist.Segment(a17, segment != null ? segment : segment4, str10, j16, i8, j14, drmInitData, str3, a16, j3, j13, z8, arrayList3));
                                                                    j12 = j14 + j16;
                                                                    arrayList3 = new ArrayList();
                                                                    if (j13 != -1) {
                                                                        j3 += j13;
                                                                    }
                                                                    j11 = j3;
                                                                    j13 = -1;
                                                                    drmInitData3 = drmInitData;
                                                                    j14 = j12;
                                                                    z5 = z2;
                                                                    i5 = i3;
                                                                    part2 = part;
                                                                    str9 = str2;
                                                                    str10 = str9;
                                                                    z8 = false;
                                                                    j16 = 0;
                                                                    hlsMediaPlaylist2 = hlsMediaPlaylist;
                                                                    str12 = str7;
                                                                    j15 = j2;
                                                                    str5 = str6;
                                                                    hashMap5 = hashMap;
                                                                    arrayList4 = arrayList;
                                                                }
                                                            }
                                                        } else if (part2 != null || !"PART".equals(a(b2, L, (Map) hashMap3))) {
                                                            str7 = str21;
                                                            j2 = j15;
                                                            hashMap = hashMap5;
                                                            str6 = str27;
                                                            i3 = i5;
                                                            part = part2;
                                                        } else {
                                                            String a18 = a(b2, I, (Map) hashMap3);
                                                            long a19 = a(b2, D, -1);
                                                            long a20 = a(b2, E, -1);
                                                            String str28 = str21;
                                                            long j22 = j15;
                                                            String a21 = a(j22, str3, str28);
                                                            if (drmInitData3 != null || treeMap.isEmpty()) {
                                                                hashMap2 = hashMap5;
                                                                str8 = str27;
                                                            } else {
                                                                hashMap2 = hashMap5;
                                                                DrmInitData.SchemeData[] schemeDataArr3 = (DrmInitData.SchemeData[]) treeMap.values().toArray(new DrmInitData.SchemeData[0]);
                                                                str8 = str27;
                                                                DrmInitData drmInitData6 = new DrmInitData(str8, schemeDataArr3);
                                                                if (drmInitData2 == null) {
                                                                    drmInitData2 = a(str8, schemeDataArr3);
                                                                }
                                                                drmInitData3 = drmInitData6;
                                                            }
                                                            if (a19 == -1 || a20 != -1) {
                                                                part2 = new HlsMediaPlaylist.Part(a18, segment, 0, i8, j12, drmInitData3, str3, a21, a19 != -1 ? a19 : 0, a20, false, false, true);
                                                            }
                                                            hlsMediaPlaylist2 = hlsMediaPlaylist;
                                                            j15 = j22;
                                                            str13 = str8;
                                                            hashMap5 = hashMap2;
                                                            z5 = z2;
                                                            str9 = str2;
                                                            str4 = str3;
                                                            str12 = str28;
                                                            arrayList4 = arrayList;
                                                        }
                                                    }
                                                    hlsMediaPlaylist2 = hlsMediaPlaylist;
                                                    z5 = z2;
                                                    i5 = i2;
                                                    part2 = part;
                                                    str9 = str2;
                                                    str12 = str7;
                                                    j15 = j2;
                                                    str5 = str6;
                                                    hashMap5 = hashMap;
                                                    arrayList4 = arrayList;
                                                }
                                                str4 = str3;
                                            }
                                            hlsMediaPlaylist2 = hlsMediaPlaylist;
                                            str5 = str25;
                                            str12 = str21;
                                            str9 = str2;
                                            arrayList4 = arrayList;
                                            str4 = str3;
                                        }
                                    }
                                }
                                hlsMasterPlaylist2 = hlsMasterPlaylist;
                            }
                            part = part2;
                            hlsMediaPlaylist2 = hlsMediaPlaylist;
                            z5 = z2;
                            i5 = i2;
                            part2 = part;
                            str9 = str2;
                            str12 = str7;
                            j15 = j2;
                            str5 = str6;
                            hashMap5 = hashMap;
                            arrayList4 = arrayList;
                            str4 = str3;
                            hlsMasterPlaylist2 = hlsMasterPlaylist;
                        }
                        str12 = str17;
                        str11 = str16;
                    }
                }
                str9 = str2;
            }
        }
        boolean z10 = z5;
        int i13 = i5;
        ArrayList arrayList6 = arrayList4;
        HlsMediaPlaylist.Part part3 = part2;
        HashMap hashMap6 = hashMap5;
        if (part3 != null) {
            arrayList3.add(part3);
        }
        return new HlsMediaPlaylist(i13, str, arrayList6, j6, z10, j7, z6, i6, j8, i7, j9, j10, z4, z7, j7 != 0, drmInitData2, arrayList2, arrayList3, serverControl2, hashMap6);
    }

    private static String a(long j2, String str, String str2) {
        if (str == null) {
            return null;
        }
        return str2 != null ? str2 : Long.toHexString(j2);
    }

    private static String a(String str) {
        return ("SAMPLE-AES-CENC".equals(str) || "SAMPLE-AES-CTR".equals(str)) ? "cenc" : "cbcs";
    }

    private static String a(String str, Map map) {
        Matcher matcher = Z.matcher(str);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            String group = matcher.group(1);
            if (map.containsKey(group)) {
                matcher.appendReplacement(stringBuffer, Matcher.quoteReplacement((String) map.get(group)));
            }
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(java.lang.String r0, java.util.regex.Pattern r1, java.lang.String r2, java.util.Map r3) {
        /*
            java.util.regex.Matcher r0 = r1.matcher(r0)
            boolean r1 = r0.find()
            if (r1 == 0) goto L_0x0016
            r1 = 1
            java.lang.String r0 = r0.group(r1)
            java.lang.Object r0 = com.google.android.exoplayer2.util.Assertions.b((java.lang.Object) r0)
            r2 = r0
            java.lang.String r2 = (java.lang.String) r2
        L_0x0016:
            boolean r0 = r3.isEmpty()
            if (r0 != 0) goto L_0x0024
            if (r2 != 0) goto L_0x001f
            goto L_0x0024
        L_0x001f:
            java.lang.String r0 = a((java.lang.String) r2, (java.util.Map) r3)
            return r0
        L_0x0024:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistParser.a(java.lang.String, java.util.regex.Pattern, java.lang.String, java.util.Map):java.lang.String");
    }

    private static String a(String str, Pattern pattern, Map map) {
        String a2 = a(str, pattern, (String) null, map);
        if (a2 != null) {
            return a2;
        }
        String pattern2 = pattern.pattern();
        throw new ParserException(new StringBuilder(String.valueOf(pattern2).length() + 19 + String.valueOf(str).length()).append("Couldn't match ").append(pattern2).append(" in ").append(str).toString());
    }

    private static boolean a(BufferedReader bufferedReader) {
        int read = bufferedReader.read();
        if (read == 239) {
            if (bufferedReader.read() != 187 || bufferedReader.read() != 191) {
                return false;
            }
            read = bufferedReader.read();
        }
        int a2 = a(bufferedReader, true, read);
        for (int i2 = 0; i2 < 7; i2++) {
            if (a2 != "#EXTM3U".charAt(i2)) {
                return false;
            }
            a2 = bufferedReader.read();
        }
        return Util.a(a(bufferedReader, false, a2));
    }

    private static double b(String str, Pattern pattern) {
        return Double.parseDouble(a(str, pattern, Collections.emptyMap()));
    }

    private static HlsMasterPlaylist.Variant b(ArrayList arrayList, String str) {
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            HlsMasterPlaylist.Variant variant = (HlsMasterPlaylist.Variant) arrayList.get(i2);
            if (str.equals(variant.c)) {
                return variant;
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public HlsPlaylist a(Uri uri, InputStream inputStream) {
        String trim;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        ArrayDeque arrayDeque = new ArrayDeque();
        try {
            if (a(bufferedReader)) {
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        trim = readLine.trim();
                        if (!trim.isEmpty()) {
                            if (!trim.startsWith("#EXT-X-STREAM-INF")) {
                                if (trim.startsWith("#EXT-X-TARGETDURATION") || trim.startsWith("#EXT-X-MEDIA-SEQUENCE") || trim.startsWith("#EXTINF") || trim.startsWith("#EXT-X-KEY") || trim.startsWith("#EXT-X-BYTERANGE") || trim.equals("#EXT-X-DISCONTINUITY") || trim.equals("#EXT-X-DISCONTINUITY-SEQUENCE")) {
                                    break;
                                } else if (trim.equals("#EXT-X-ENDLIST")) {
                                    break;
                                } else {
                                    arrayDeque.add(trim);
                                }
                            } else {
                                arrayDeque.add(trim);
                                HlsMasterPlaylist a2 = a(new LineIterator(arrayDeque, bufferedReader), uri.toString());
                                Util.a((Closeable) bufferedReader);
                                return a2;
                            }
                        }
                    } else {
                        Util.a((Closeable) bufferedReader);
                        throw new ParserException("Failed to parse the playlist, could not identify any tags.");
                    }
                }
                arrayDeque.add(trim);
                return a(this.aa, this.ab, new LineIterator(arrayDeque, bufferedReader), uri.toString());
            }
            throw new UnrecognizedInputFormatException("Input does not start with the #EXTM3U header.");
        } finally {
            Util.a((Closeable) bufferedReader);
        }
    }

    private static Pattern b(String str) {
        return Pattern.compile(new StringBuilder(String.valueOf(str).length() + 9).append(str).append("=(NO|YES)").toString());
    }

    private static double c(String str, Pattern pattern) {
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return Double.parseDouble((String) Assertions.b((Object) matcher.group(1)));
        }
        return -9.223372036854776E18d;
    }

    private static HlsMasterPlaylist.Variant c(ArrayList arrayList, String str) {
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            HlsMasterPlaylist.Variant variant = (HlsMasterPlaylist.Variant) arrayList.get(i2);
            if (str.equals(variant.e)) {
                return variant;
            }
        }
        return null;
    }

    private static boolean d(String str, Pattern pattern) {
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return "YES".equals(matcher.group(1));
        }
        return false;
    }
}
