package com.google.android.exoplayer2.extractor.mp4;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.GaplessInfoHolder;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.id3.ApicFrame;
import com.google.android.exoplayer2.metadata.id3.CommentFrame;
import com.google.android.exoplayer2.metadata.id3.Id3Frame;
import com.google.android.exoplayer2.metadata.id3.InternalFrame;
import com.google.android.exoplayer2.metadata.id3.TextInformationFrame;
import com.google.android.exoplayer2.metadata.mp4.MdtaMetadataEntry;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.appinventor.components.runtime.util.FullScreenVideoUtil;

final class MetadataUtil {
    private static String[] a;

    static {
        String[] strArr = new String[FullScreenVideoUtil.FULLSCREEN_VIDEO_ACTION_PAUSE];
        strArr[0] = "Blues";
        strArr[1] = "Classic Rock";
        strArr[2] = "Country";
        strArr[3] = "Dance";
        strArr[4] = "Disco";
        strArr[5] = "Funk";
        strArr[6] = "Grunge";
        strArr[7] = "Hip-Hop";
        strArr[8] = "Jazz";
        strArr[9] = "Metal";
        strArr[10] = "New Age";
        strArr[11] = "Oldies";
        strArr[12] = "Other";
        strArr[13] = "Pop";
        strArr[14] = "R&B";
        strArr[15] = "Rap";
        strArr[16] = "Reggae";
        strArr[17] = "Rock";
        strArr[18] = "Techno";
        strArr[19] = "Industrial";
        strArr[20] = "Alternative";
        strArr[21] = "Ska";
        strArr[22] = "Death Metal";
        strArr[23] = "Pranks";
        strArr[24] = "Soundtrack";
        strArr[25] = "Euro-Techno";
        strArr[26] = "Ambient";
        strArr[27] = "Trip-Hop";
        strArr[28] = "Vocal";
        strArr[29] = "Jazz+Funk";
        strArr[30] = "Fusion";
        strArr[31] = "Trance";
        strArr[32] = "Classical";
        strArr[33] = "Instrumental";
        strArr[34] = "Acid";
        strArr[35] = "House";
        strArr[36] = "Game";
        strArr[37] = "Sound Clip";
        strArr[38] = "Gospel";
        strArr[39] = "Noise";
        strArr[40] = "AlternRock";
        strArr[41] = "Bass";
        strArr[42] = "Soul";
        strArr[43] = "Punk";
        strArr[44] = "Space";
        strArr[45] = "Meditative";
        strArr[46] = "Instrumental Pop";
        strArr[47] = "Instrumental Rock";
        strArr[48] = "Ethnic";
        strArr[49] = "Gothic";
        strArr[50] = "Darkwave";
        strArr[51] = "Techno-Industrial";
        strArr[52] = "Electronic";
        strArr[53] = "Pop-Folk";
        strArr[54] = "Eurodance";
        strArr[55] = "Dream";
        strArr[56] = "Southern Rock";
        strArr[57] = "Comedy";
        strArr[58] = "Cult";
        strArr[59] = "Gangsta";
        strArr[60] = "Top 40";
        strArr[61] = "Christian Rap";
        strArr[62] = "Pop/Funk";
        strArr[63] = "Jungle";
        strArr[64] = "Native American";
        strArr[65] = "Cabaret";
        strArr[66] = "New Wave";
        strArr[67] = "Psychadelic";
        strArr[68] = "Rave";
        strArr[69] = "Showtunes";
        strArr[70] = "Trailer";
        strArr[71] = "Lo-Fi";
        strArr[72] = "Tribal";
        strArr[73] = "Acid Punk";
        strArr[74] = "Acid Jazz";
        strArr[75] = "Polka";
        strArr[76] = "Retro";
        strArr[77] = "Musical";
        strArr[78] = "Rock & Roll";
        strArr[79] = "Hard Rock";
        strArr[80] = "Folk";
        strArr[81] = "Folk-Rock";
        strArr[82] = "National Folk";
        strArr[83] = "Swing";
        strArr[84] = "Fast Fusion";
        strArr[85] = "Bebob";
        strArr[86] = "Latin";
        strArr[87] = "Revival";
        strArr[88] = "Celtic";
        strArr[89] = "Bluegrass";
        strArr[90] = "Avantgarde";
        strArr[91] = "Gothic Rock";
        strArr[92] = "Progressive Rock";
        strArr[93] = "Psychedelic Rock";
        strArr[94] = "Symphonic Rock";
        strArr[95] = "Slow Rock";
        strArr[96] = "Big Band";
        strArr[97] = "Chorus";
        strArr[98] = "Easy Listening";
        strArr[99] = "Acoustic";
        strArr[100] = "Humour";
        strArr[101] = "Speech";
        strArr[102] = "Chanson";
        strArr[103] = "Opera";
        strArr[104] = "Chamber Music";
        strArr[105] = "Sonata";
        strArr[106] = "Symphony";
        strArr[107] = "Booty Bass";
        strArr[108] = "Primus";
        strArr[109] = "Porn Groove";
        strArr[110] = "Satire";
        strArr[111] = "Slow Jam";
        strArr[112] = "Club";
        strArr[113] = "Tango";
        strArr[114] = "Samba";
        strArr[115] = "Folklore";
        strArr[116] = "Ballad";
        strArr[117] = "Power Ballad";
        strArr[118] = "Rhythmic Soul";
        strArr[119] = "Freestyle";
        strArr[120] = "Duet";
        strArr[121] = "Punk Rock";
        strArr[122] = "Drum Solo";
        strArr[123] = "A capella";
        strArr[124] = "Euro-House";
        strArr[125] = "Dance Hall";
        strArr[126] = "Goa";
        strArr[127] = "Drum & Bass";
        strArr[128] = "Club-House";
        strArr[129] = "Hardcore";
        strArr[130] = "Terror";
        strArr[131] = "Indie";
        strArr[132] = "BritPop";
        strArr[133] = "Afro-Punk";
        strArr[134] = "Polsk Punk";
        strArr[135] = "Beat";
        strArr[136] = "Christian Gangsta Rap";
        strArr[137] = "Heavy Metal";
        strArr[138] = "Black Metal";
        strArr[139] = "Crossover";
        strArr[140] = "Contemporary Christian";
        strArr[141] = "Christian Rock";
        strArr[142] = "Merengue";
        strArr[143] = "Salsa";
        strArr[144] = "Thrash Metal";
        strArr[145] = "Anime";
        strArr[146] = "Jpop";
        strArr[147] = "Synthpop";
        strArr[148] = "Abstract";
        strArr[149] = "Art Rock";
        strArr[150] = "Baroque";
        strArr[151] = "Bhangra";
        strArr[152] = "Big beat";
        strArr[153] = "Breakbeat";
        strArr[154] = "Chillout";
        strArr[155] = "Downtempo";
        strArr[156] = "Dub";
        strArr[157] = "EBM";
        strArr[158] = "Eclectic";
        strArr[159] = "Electro";
        strArr[160] = "Electroclash";
        strArr[161] = "Emo";
        strArr[162] = "Experimental";
        strArr[163] = "Garage";
        strArr[164] = "Global";
        strArr[165] = "IDM";
        strArr[166] = "Illbient";
        strArr[167] = "Industro-Goth";
        strArr[168] = "Jam Band";
        strArr[169] = "Krautrock";
        strArr[170] = "Leftfield";
        strArr[171] = "Lounge";
        strArr[172] = "Math Rock";
        strArr[173] = "New Romantic";
        strArr[174] = "Nu-Breakz";
        strArr[175] = "Post-Punk";
        strArr[176] = "Post-Rock";
        strArr[177] = "Psytrance";
        strArr[178] = "Shoegaze";
        strArr[179] = "Space Rock";
        strArr[180] = "Trop Rock";
        strArr[181] = "World Music";
        strArr[182] = "Neoclassical";
        strArr[183] = "Audiobook";
        strArr[184] = "Audio theatre";
        strArr[185] = "Neue Deutsche Welle";
        strArr[186] = "Podcast";
        strArr[187] = "Indie-Rock";
        strArr[188] = "G-Funk";
        strArr[189] = "Dubstep";
        strArr[190] = "Garage Rock";
        strArr[191] = "Psybient";
        a = strArr;
    }

    public static Metadata.Entry a(ParsableByteArray parsableByteArray) {
        String str;
        int j = parsableByteArray.b + parsableByteArray.j();
        int j2 = parsableByteArray.j();
        int i = j2 >>> 24;
        Id3Frame id3Frame = null;
        if (i == 169 || i == 253) {
            int i2 = 16777215 & j2;
            if (i2 == 6516084) {
                int j3 = parsableByteArray.j();
                if (parsableByteArray.j() == 1684108385) {
                    parsableByteArray.e(8);
                    String g = parsableByteArray.g(j3 - 16);
                    id3Frame = new CommentFrame("und", g, g);
                } else {
                    String valueOf = String.valueOf(Atom.c(j2));
                    Log.c("MetadataUtil", valueOf.length() != 0 ? "Failed to parse comment attribute: ".concat(valueOf) : new String("Failed to parse comment attribute: "));
                }
                parsableByteArray.d(j);
                return id3Frame;
            } else if (i2 == 7233901 || i2 == 7631467) {
                TextInformationFrame a2 = a(j2, "TIT2", parsableByteArray);
                parsableByteArray.d(j);
                return a2;
            } else if (i2 == 6516589 || i2 == 7828084) {
                TextInformationFrame a3 = a(j2, "TCOM", parsableByteArray);
                parsableByteArray.d(j);
                return a3;
            } else if (i2 == 6578553) {
                TextInformationFrame a4 = a(j2, "TDRC", parsableByteArray);
                parsableByteArray.d(j);
                return a4;
            } else if (i2 == 4280916) {
                TextInformationFrame a5 = a(j2, "TPE1", parsableByteArray);
                parsableByteArray.d(j);
                return a5;
            } else if (i2 == 7630703) {
                TextInformationFrame a6 = a(j2, "TSSE", parsableByteArray);
                parsableByteArray.d(j);
                return a6;
            } else if (i2 == 6384738) {
                TextInformationFrame a7 = a(j2, "TALB", parsableByteArray);
                parsableByteArray.d(j);
                return a7;
            } else if (i2 == 7108978) {
                TextInformationFrame a8 = a(j2, "USLT", parsableByteArray);
                parsableByteArray.d(j);
                return a8;
            } else if (i2 == 6776174) {
                TextInformationFrame a9 = a(j2, "TCON", parsableByteArray);
                parsableByteArray.d(j);
                return a9;
            } else if (i2 == 6779504) {
                TextInformationFrame a10 = a(j2, "TIT1", parsableByteArray);
                parsableByteArray.d(j);
                return a10;
            }
        } else if (j2 == 1735291493) {
            try {
                int b = b(parsableByteArray);
                String str2 = (b <= 0 || b > 192) ? null : a[b - 1];
                if (str2 != null) {
                    id3Frame = new TextInformationFrame("TCON", (String) null, str2);
                } else {
                    Log.c("MetadataUtil", "Failed to parse standard genre code");
                }
                return id3Frame;
            } finally {
                parsableByteArray.d(j);
            }
        } else if (j2 == 1684632427) {
            TextInformationFrame b2 = b(j2, "TPOS", parsableByteArray);
            parsableByteArray.d(j);
            return b2;
        } else if (j2 == 1953655662) {
            TextInformationFrame b3 = b(j2, "TRCK", parsableByteArray);
            parsableByteArray.d(j);
            return b3;
        } else if (j2 == 1953329263) {
            Id3Frame a11 = a(j2, "TBPM", parsableByteArray, true, false);
            parsableByteArray.d(j);
            return a11;
        } else if (j2 == 1668311404) {
            Id3Frame a12 = a(j2, "TCMP", parsableByteArray, true, true);
            parsableByteArray.d(j);
            return a12;
        } else if (j2 == 1668249202) {
            int j4 = parsableByteArray.j();
            if (parsableByteArray.j() == 1684108385) {
                int b4 = Atom.b(parsableByteArray.j());
                String str3 = b4 == 13 ? "image/jpeg" : b4 == 14 ? "image/png" : null;
                if (str3 == null) {
                    str = new StringBuilder(41).append("Unrecognized cover art flags: ").append(b4).toString();
                } else {
                    parsableByteArray.e(4);
                    int i3 = j4 - 16;
                    byte[] bArr = new byte[i3];
                    parsableByteArray.a(bArr, 0, i3);
                    id3Frame = new ApicFrame(str3, (String) null, 3, bArr);
                    parsableByteArray.d(j);
                    return id3Frame;
                }
            } else {
                str = "Failed to parse cover art attribute";
            }
            Log.c("MetadataUtil", str);
            parsableByteArray.d(j);
            return id3Frame;
        } else if (j2 == 1631670868) {
            TextInformationFrame a13 = a(j2, "TPE2", parsableByteArray);
            parsableByteArray.d(j);
            return a13;
        } else if (j2 == 1936682605) {
            TextInformationFrame a14 = a(j2, "TSOT", parsableByteArray);
            parsableByteArray.d(j);
            return a14;
        } else if (j2 == 1936679276) {
            TextInformationFrame a15 = a(j2, "TSO2", parsableByteArray);
            parsableByteArray.d(j);
            return a15;
        } else if (j2 == 1936679282) {
            TextInformationFrame a16 = a(j2, "TSOA", parsableByteArray);
            parsableByteArray.d(j);
            return a16;
        } else if (j2 == 1936679265) {
            TextInformationFrame a17 = a(j2, "TSOP", parsableByteArray);
            parsableByteArray.d(j);
            return a17;
        } else if (j2 == 1936679791) {
            TextInformationFrame a18 = a(j2, "TSOC", parsableByteArray);
            parsableByteArray.d(j);
            return a18;
        } else if (j2 == 1920233063) {
            Id3Frame a19 = a(j2, "ITUNESADVISORY", parsableByteArray, false, false);
            parsableByteArray.d(j);
            return a19;
        } else if (j2 == 1885823344) {
            Id3Frame a20 = a(j2, "ITUNESGAPLESS", parsableByteArray, false, true);
            parsableByteArray.d(j);
            return a20;
        } else if (j2 == 1936683886) {
            TextInformationFrame a21 = a(j2, "TVSHOWSORT", parsableByteArray);
            parsableByteArray.d(j);
            return a21;
        } else if (j2 == 1953919848) {
            TextInformationFrame a22 = a(j2, "TVSHOW", parsableByteArray);
            parsableByteArray.d(j);
            return a22;
        } else if (j2 == 757935405) {
            Id3Frame a23 = a(parsableByteArray, j);
            parsableByteArray.d(j);
            return a23;
        }
        String valueOf2 = String.valueOf(Atom.c(j2));
        Log.a("MetadataUtil", valueOf2.length() != 0 ? "Skipped unknown metadata entry: ".concat(valueOf2) : new String("Skipped unknown metadata entry: "));
        parsableByteArray.d(j);
        return null;
    }

    private static Id3Frame a(int i, String str, ParsableByteArray parsableByteArray, boolean z, boolean z2) {
        int b = b(parsableByteArray);
        if (z2) {
            b = Math.min(1, b);
        }
        if (b >= 0) {
            return z ? new TextInformationFrame(str, (String) null, Integer.toString(b)) : new CommentFrame("und", str, Integer.toString(b));
        }
        String valueOf = String.valueOf(Atom.c(i));
        Log.c("MetadataUtil", valueOf.length() != 0 ? "Failed to parse uint8 attribute: ".concat(valueOf) : new String("Failed to parse uint8 attribute: "));
        return null;
    }

    private static Id3Frame a(ParsableByteArray parsableByteArray, int i) {
        String str = null;
        String str2 = null;
        int i2 = -1;
        int i3 = -1;
        while (parsableByteArray.b < i) {
            int i4 = parsableByteArray.b;
            int j = parsableByteArray.j();
            int j2 = parsableByteArray.j();
            parsableByteArray.e(4);
            if (j2 == 1835360622) {
                str = parsableByteArray.g(j - 12);
            } else if (j2 == 1851878757) {
                str2 = parsableByteArray.g(j - 12);
            } else {
                if (j2 == 1684108385) {
                    i2 = i4;
                    i3 = j;
                }
                parsableByteArray.e(j - 12);
            }
        }
        if (str == null || str2 == null || i2 == -1) {
            return null;
        }
        parsableByteArray.d(i2);
        parsableByteArray.e(16);
        return new InternalFrame(str, str2, parsableByteArray.g(i3 - 16));
    }

    private static TextInformationFrame a(int i, String str, ParsableByteArray parsableByteArray) {
        int j = parsableByteArray.j();
        if (parsableByteArray.j() == 1684108385) {
            parsableByteArray.e(8);
            return new TextInformationFrame(str, (String) null, parsableByteArray.g(j - 16));
        }
        String valueOf = String.valueOf(Atom.c(i));
        Log.c("MetadataUtil", valueOf.length() != 0 ? "Failed to parse text attribute: ".concat(valueOf) : new String("Failed to parse text attribute: "));
        return null;
    }

    public static MdtaMetadataEntry a(ParsableByteArray parsableByteArray, int i, String str) {
        while (true) {
            int i2 = parsableByteArray.b;
            if (i2 >= i) {
                return null;
            }
            int j = parsableByteArray.j();
            if (parsableByteArray.j() == 1684108385) {
                int j2 = parsableByteArray.j();
                int j3 = parsableByteArray.j();
                int i3 = j - 16;
                byte[] bArr = new byte[i3];
                parsableByteArray.a(bArr, 0, i3);
                return new MdtaMetadataEntry(str, bArr, j3, j2);
            }
            parsableByteArray.d(i2 + j);
        }
    }

    public static void a(int i, GaplessInfoHolder gaplessInfoHolder, Format.Builder builder) {
        if (i == 1 && gaplessInfoHolder.a()) {
            builder.A = gaplessInfoHolder.a;
            builder.B = gaplessInfoHolder.b;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000c, code lost:
        if (r7 != null) goto L_0x003c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x003e A[LOOP:1: B:15:0x003c->B:16:0x003e, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(int r6, com.google.android.exoplayer2.metadata.Metadata r7, com.google.android.exoplayer2.metadata.Metadata r8, com.google.android.exoplayer2.Format.Builder r9, com.google.android.exoplayer2.metadata.Metadata... r10) {
        /*
            com.google.android.exoplayer2.metadata.Metadata r0 = new com.google.android.exoplayer2.metadata.Metadata
            r1 = 0
            com.google.android.exoplayer2.metadata.Metadata$Entry[] r2 = new com.google.android.exoplayer2.metadata.Metadata.Entry[r1]
            r0.<init>((com.google.android.exoplayer2.metadata.Metadata.Entry[]) r2)
            r2 = 2
            r3 = 1
            if (r6 != r3) goto L_0x000f
            if (r7 == 0) goto L_0x003b
            goto L_0x003c
        L_0x000f:
            if (r6 != r2) goto L_0x003b
            if (r8 == 0) goto L_0x003b
            r6 = 0
        L_0x0014:
            com.google.android.exoplayer2.metadata.Metadata$Entry[] r7 = r8.a
            int r7 = r7.length
            if (r6 >= r7) goto L_0x003b
            com.google.android.exoplayer2.metadata.Metadata$Entry[] r7 = r8.a
            r7 = r7[r6]
            boolean r4 = r7 instanceof com.google.android.exoplayer2.metadata.mp4.MdtaMetadataEntry
            if (r4 == 0) goto L_0x0038
            com.google.android.exoplayer2.metadata.mp4.MdtaMetadataEntry r7 = (com.google.android.exoplayer2.metadata.mp4.MdtaMetadataEntry) r7
            java.lang.String r4 = "com.android.capture.fps"
            java.lang.String r5 = r7.a
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x0038
            com.google.android.exoplayer2.metadata.Metadata r6 = new com.google.android.exoplayer2.metadata.Metadata
            com.google.android.exoplayer2.metadata.Metadata$Entry[] r8 = new com.google.android.exoplayer2.metadata.Metadata.Entry[r3]
            r8[r1] = r7
            r6.<init>((com.google.android.exoplayer2.metadata.Metadata.Entry[]) r8)
            r7 = r6
            goto L_0x003c
        L_0x0038:
            int r6 = r6 + 1
            goto L_0x0014
        L_0x003b:
            r7 = r0
        L_0x003c:
            if (r1 >= r2) goto L_0x0047
            r6 = r10[r1]
            com.google.android.exoplayer2.metadata.Metadata r7 = r7.a((com.google.android.exoplayer2.metadata.Metadata) r6)
            int r1 = r1 + 1
            goto L_0x003c
        L_0x0047:
            com.google.android.exoplayer2.metadata.Metadata$Entry[] r6 = r7.a
            int r6 = r6.length
            if (r6 <= 0) goto L_0x004e
            r9.i = r7
        L_0x004e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mp4.MetadataUtil.a(int, com.google.android.exoplayer2.metadata.Metadata, com.google.android.exoplayer2.metadata.Metadata, com.google.android.exoplayer2.Format$Builder, com.google.android.exoplayer2.metadata.Metadata[]):void");
    }

    private static int b(ParsableByteArray parsableByteArray) {
        parsableByteArray.e(4);
        if (parsableByteArray.j() == 1684108385) {
            parsableByteArray.e(8);
            return parsableByteArray.c();
        }
        Log.c("MetadataUtil", "Failed to parse uint8 attribute value");
        return -1;
    }

    private static TextInformationFrame b(int i, String str, ParsableByteArray parsableByteArray) {
        int j = parsableByteArray.j();
        if (parsableByteArray.j() == 1684108385 && j >= 22) {
            parsableByteArray.e(10);
            int d = parsableByteArray.d();
            if (d > 0) {
                String sb = new StringBuilder(11).append(d).toString();
                int d2 = parsableByteArray.d();
                if (d2 > 0) {
                    String valueOf = String.valueOf(sb);
                    sb = new StringBuilder(String.valueOf(valueOf).length() + 12).append(valueOf).append("/").append(d2).toString();
                }
                return new TextInformationFrame(str, (String) null, sb);
            }
        }
        String valueOf2 = String.valueOf(Atom.c(i));
        Log.c("MetadataUtil", valueOf2.length() != 0 ? "Failed to parse index/count attribute: ".concat(valueOf2) : new String("Failed to parse index/count attribute: "));
        return null;
    }
}
