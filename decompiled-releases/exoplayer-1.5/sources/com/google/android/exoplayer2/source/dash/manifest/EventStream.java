package com.google.android.exoplayer2.source.dash.manifest;

import com.google.android.exoplayer2.metadata.emsg.EventMessage;

public final class EventStream {
    public final EventMessage[] a;
    public final long[] b;
    private String c;
    private String d;

    public EventStream(String str, String str2, long[] jArr, EventMessage[] eventMessageArr) {
        this.c = str;
        this.d = str2;
        this.b = jArr;
        this.a = eventMessageArr;
    }

    public final String a() {
        String str = this.c;
        String str2 = this.d;
        return new StringBuilder(String.valueOf(str).length() + 1 + String.valueOf(str2).length()).append(str).append("/").append(str2).toString();
    }
}
