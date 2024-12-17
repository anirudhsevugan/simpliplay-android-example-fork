package com.google.android.exoplayer2.text.webvtt;

import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class WebvttParserUtil {
    private static final Pattern a = Pattern.compile("^NOTE([ \t].*)?$");

    public static long a(String str) {
        String[] b = Util.b(str, "\\.");
        long j = 0;
        for (String parseLong : Util.a(b[0], ":")) {
            j = (j * 60) + Long.parseLong(parseLong);
        }
        long j2 = j * 1000;
        if (b.length == 2) {
            j2 += Long.parseLong(b[1]);
        }
        return j2 * 1000;
    }

    public static void a(ParsableByteArray parsableByteArray) {
        int i = parsableByteArray.b;
        if (!b(parsableByteArray)) {
            parsableByteArray.d(i);
            String valueOf = String.valueOf(parsableByteArray.s());
            throw new ParserException(valueOf.length() != 0 ? "Expected WEBVTT. Got ".concat(valueOf) : new String("Expected WEBVTT. Got "));
        }
    }

    public static float b(String str) {
        if (str.endsWith("%")) {
            return Float.parseFloat(str.substring(0, str.length() - 1)) / 100.0f;
        }
        throw new NumberFormatException("Percentages must end with %");
    }

    public static boolean b(ParsableByteArray parsableByteArray) {
        String s = parsableByteArray.s();
        return s != null && s.startsWith("WEBVTT");
    }

    public static Matcher c(ParsableByteArray parsableByteArray) {
        String s;
        while (true) {
            String s2 = parsableByteArray.s();
            if (s2 == null) {
                return null;
            }
            if (a.matcher(s2).matches()) {
                do {
                    s = parsableByteArray.s();
                    if (s == null) {
                        break;
                    }
                } while (s.isEmpty());
            } else {
                Matcher matcher = WebvttCueParser.a.matcher(s2);
                if (matcher.matches()) {
                    return matcher;
                }
            }
        }
    }
}
