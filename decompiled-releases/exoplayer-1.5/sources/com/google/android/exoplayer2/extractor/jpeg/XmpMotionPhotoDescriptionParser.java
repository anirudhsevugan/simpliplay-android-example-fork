package com.google.android.exoplayer2.extractor.jpeg;

import com.dreamers.exoplayercore.repack.bG;
import com.dreamers.exoplayercore.repack.bH;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.jpeg.MotionPhotoDescription;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.XmlPullParserUtil;
import java.io.StringReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

final class XmpMotionPhotoDescriptionParser {
    private static final String[] a = {"Camera:MotionPhoto", "GCamera:MotionPhoto", "Camera:MicroVideo", "GCamera:MicroVideo"};
    private static final String[] b = {"Camera:MotionPhotoPresentationTimestampUs", "GCamera:MotionPhotoPresentationTimestampUs", "Camera:MicroVideoPresentationTimestampUs", "GCamera:MicroVideoPresentationTimestampUs"};
    private static final String[] c = {"Camera:MicroVideoOffset", "GCamera:MicroVideoOffset"};

    private static bG a(XmlPullParser xmlPullParser, String str, String str2) {
        bH i = bG.i();
        String concat = String.valueOf(str).concat(":Item");
        String concat2 = String.valueOf(str).concat(":Directory");
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.b(xmlPullParser, concat)) {
                String concat3 = String.valueOf(str2).concat(":Mime");
                String concat4 = String.valueOf(str2).concat(":Semantic");
                String concat5 = String.valueOf(str2).concat(":Length");
                String concat6 = String.valueOf(str2).concat(":Padding");
                String d = XmlPullParserUtil.d(xmlPullParser, concat3);
                String d2 = XmlPullParserUtil.d(xmlPullParser, concat4);
                String d3 = XmlPullParserUtil.d(xmlPullParser, concat5);
                String d4 = XmlPullParserUtil.d(xmlPullParser, concat6);
                if (d == null || d2 == null) {
                    return bG.g();
                }
                i.b(new MotionPhotoDescription.ContainerItem(d, d3 != null ? Long.parseLong(d3) : 0, d4 != null ? Long.parseLong(d4) : 0));
            }
        } while (!XmlPullParserUtil.a(xmlPullParser, concat2));
        return i.a();
    }

    public static MotionPhotoDescription a(String str) {
        String str2;
        String str3;
        try {
            XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
            newPullParser.setInput(new StringReader(str));
            newPullParser.next();
            if (XmlPullParserUtil.b(newPullParser, "x:xmpmeta")) {
                bG g = bG.g();
                long j = -9223372036854775807L;
                do {
                    newPullParser.next();
                    if (!XmlPullParserUtil.b(newPullParser, "rdf:Description")) {
                        if (XmlPullParserUtil.b(newPullParser, "Container:Directory")) {
                            str2 = "Container";
                            str3 = "Item";
                        } else if (XmlPullParserUtil.b(newPullParser, "GContainer:Directory")) {
                            str2 = "GContainer";
                            str3 = "GContainerItem";
                        }
                        g = a(newPullParser, str2, str3);
                    } else if (!a(newPullParser)) {
                        return null;
                    } else {
                        j = b(newPullParser);
                        g = c(newPullParser);
                    }
                } while (!XmlPullParserUtil.a(newPullParser, "x:xmpmeta"));
                if (g.isEmpty()) {
                    return null;
                }
                return new MotionPhotoDescription(j, g);
            }
            throw new ParserException("Couldn't find xmp metadata");
        } catch (ParserException | NumberFormatException | XmlPullParserException e) {
            Log.c("MotionPhotoXmpParser", "Ignoring unexpected XMP metadata");
            return null;
        }
    }

    private static boolean a(XmlPullParser xmlPullParser) {
        String[] strArr = a;
        for (int i = 0; i < 4; i++) {
            String d = XmlPullParserUtil.d(xmlPullParser, strArr[i]);
            if (d != null) {
                return Integer.parseInt(d) == 1;
            }
        }
        return false;
    }

    private static long b(XmlPullParser xmlPullParser) {
        String[] strArr = b;
        for (int i = 0; i < 4; i++) {
            String d = XmlPullParserUtil.d(xmlPullParser, strArr[i]);
            if (d != null) {
                long parseLong = Long.parseLong(d);
                if (parseLong == -1) {
                    return -9223372036854775807L;
                }
                return parseLong;
            }
        }
        return -9223372036854775807L;
    }

    private static bG c(XmlPullParser xmlPullParser) {
        String[] strArr = c;
        for (int i = 0; i < 2; i++) {
            String d = XmlPullParserUtil.d(xmlPullParser, strArr[i]);
            if (d != null) {
                return bG.a((Object) new MotionPhotoDescription.ContainerItem("image/jpeg", 0, 0), (Object) new MotionPhotoDescription.ContainerItem("video/mp4", Long.parseLong(d), 0));
            }
        }
        return bG.g();
    }
}
