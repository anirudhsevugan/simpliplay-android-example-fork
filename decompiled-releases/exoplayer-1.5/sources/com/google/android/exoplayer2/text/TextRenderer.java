package com.google.android.exoplayer2.text;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.core.location.LocationRequestCompat;
import com.google.android.exoplayer2.BaseRenderer;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.RendererCapabilities$$CC;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.util.Collections;
import java.util.List;

public final class TextRenderer extends BaseRenderer implements Handler.Callback {
    private final Handler a;
    private final TextOutput b;
    private final SubtitleDecoderFactory c;
    private final FormatHolder d;
    private boolean e;
    private boolean f;
    private boolean g;
    private int h;
    private Format i;
    private SubtitleDecoder j;
    private SubtitleInputBuffer k;
    private SubtitleOutputBuffer l;
    private SubtitleOutputBuffer m;
    private int n;
    private long o;

    public TextRenderer(TextOutput textOutput, Looper looper) {
        this(textOutput, looper, SubtitleDecoderFactory.a);
    }

    private TextRenderer(TextOutput textOutput, Looper looper, SubtitleDecoderFactory subtitleDecoderFactory) {
        super(3);
        this.b = (TextOutput) Assertions.b((Object) textOutput);
        this.a = looper == null ? null : Util.a(looper, (Handler.Callback) this);
        this.c = subtitleDecoderFactory;
        this.d = new FormatHolder();
        this.o = -9223372036854775807L;
    }

    private void A() {
        this.k = null;
        this.n = -1;
        SubtitleOutputBuffer subtitleOutputBuffer = this.l;
        if (subtitleOutputBuffer != null) {
            subtitleOutputBuffer.f();
            this.l = null;
        }
        SubtitleOutputBuffer subtitleOutputBuffer2 = this.m;
        if (subtitleOutputBuffer2 != null) {
            subtitleOutputBuffer2.f();
            this.m = null;
        }
    }

    private void B() {
        A();
        ((SubtitleDecoder) Assertions.b((Object) this.j)).d();
        this.j = null;
        this.h = 0;
    }

    private void C() {
        this.g = true;
        this.j = this.c.b((Format) Assertions.b((Object) this.i));
    }

    private void D() {
        B();
        C();
    }

    private long E() {
        if (this.n == -1) {
            return LocationRequestCompat.PASSIVE_INTERVAL;
        }
        Assertions.b((Object) this.l);
        return this.n >= this.l.b() ? LocationRequestCompat.PASSIVE_INTERVAL : this.l.a(this.n);
    }

    private void a(SubtitleDecoderException subtitleDecoderException) {
        String valueOf = String.valueOf(this.i);
        Log.b("TextRenderer", new StringBuilder(String.valueOf(valueOf).length() + 39).append("Subtitle decoding failed. streamFormat=").append(valueOf).toString(), subtitleDecoderException);
        a(Collections.emptyList());
        D();
    }

    private void a(List list) {
        Handler handler = this.a;
        if (handler != null) {
            handler.obtainMessage(0, list).sendToTarget();
        } else {
            b(list);
        }
    }

    private void b(List list) {
        this.b.onCues(list);
    }

    public final int a(Format format) {
        if (this.c.a(format)) {
            return RendererCapabilities$$CC.a(format.C == null ? 4 : 2);
        }
        return RendererCapabilities$$CC.a(MimeTypes.c(format.l) ? 1 : 0);
    }

    public final void a(long j2, long j3) {
        boolean z;
        if (j()) {
            long j4 = this.o;
            if (j4 != -9223372036854775807L && j2 >= j4) {
                A();
                this.f = true;
            }
        }
        if (!this.f) {
            if (this.m == null) {
                ((SubtitleDecoder) Assertions.b((Object) this.j)).a(j2);
                try {
                    this.m = (SubtitleOutputBuffer) ((SubtitleDecoder) Assertions.b((Object) this.j)).b();
                } catch (SubtitleDecoderException e2) {
                    a(e2);
                    return;
                }
            }
            if (b_() == 2) {
                if (this.l != null) {
                    long E = E();
                    z = false;
                    while (E <= j2) {
                        this.n++;
                        E = E();
                        z = true;
                    }
                } else {
                    z = false;
                }
                SubtitleOutputBuffer subtitleOutputBuffer = this.m;
                if (subtitleOutputBuffer != null) {
                    if (subtitleOutputBuffer.c()) {
                        if (!z && E() == LocationRequestCompat.PASSIVE_INTERVAL) {
                            if (this.h == 2) {
                                D();
                            } else {
                                A();
                                this.f = true;
                            }
                        }
                    } else if (subtitleOutputBuffer.b <= j2) {
                        SubtitleOutputBuffer subtitleOutputBuffer2 = this.l;
                        if (subtitleOutputBuffer2 != null) {
                            subtitleOutputBuffer2.f();
                        }
                        this.n = subtitleOutputBuffer.a(j2);
                        this.l = subtitleOutputBuffer;
                        this.m = null;
                        z = true;
                    }
                }
                if (z) {
                    Assertions.b((Object) this.l);
                    a(this.l.b(j2));
                }
                if (this.h != 2) {
                    while (!this.e) {
                        try {
                            SubtitleInputBuffer subtitleInputBuffer = this.k;
                            if (subtitleInputBuffer == null) {
                                subtitleInputBuffer = (SubtitleInputBuffer) ((SubtitleDecoder) Assertions.b((Object) this.j)).a();
                                if (subtitleInputBuffer != null) {
                                    this.k = subtitleInputBuffer;
                                } else {
                                    return;
                                }
                            }
                            if (this.h == 1) {
                                subtitleInputBuffer.a = 4;
                                ((SubtitleDecoder) Assertions.b((Object) this.j)).a(subtitleInputBuffer);
                                this.k = null;
                                this.h = 2;
                                return;
                            }
                            int a2 = a(this.d, (DecoderInputBuffer) subtitleInputBuffer, 0);
                            if (a2 == -4) {
                                if (subtitleInputBuffer.c()) {
                                    this.e = true;
                                    this.g = false;
                                } else {
                                    Format format = this.d.b;
                                    if (format != null) {
                                        subtitleInputBuffer.g = format.p;
                                        subtitleInputBuffer.h();
                                        this.g &= !subtitleInputBuffer.d();
                                    } else {
                                        return;
                                    }
                                }
                                if (!this.g) {
                                    ((SubtitleDecoder) Assertions.b((Object) this.j)).a(subtitleInputBuffer);
                                    this.k = null;
                                }
                            } else if (a2 == -3) {
                                return;
                            }
                        } catch (SubtitleDecoderException e3) {
                            a(e3);
                            return;
                        }
                    }
                }
            }
        }
    }

    public final void a(long j2, boolean z) {
        a(Collections.emptyList());
        this.e = false;
        this.f = false;
        this.o = -9223372036854775807L;
        if (this.h != 0) {
            D();
            return;
        }
        A();
        ((SubtitleDecoder) Assertions.b((Object) this.j)).c();
    }

    public final void a(Format[] formatArr, long j2, long j3) {
        this.i = formatArr[0];
        if (this.j != null) {
            this.h = 1;
        } else {
            C();
        }
    }

    public final void c(long j2) {
        Assertions.b(j());
        this.o = j2;
    }

    public final boolean handleMessage(Message message) {
        switch (message.what) {
            case 0:
                b((List) message.obj);
                return true;
            default:
                throw new IllegalStateException();
        }
    }

    public final void r() {
        this.i = null;
        this.o = -9223372036854775807L;
        a(Collections.emptyList());
        B();
    }

    public final String x() {
        return "TextRenderer";
    }

    public final boolean y() {
        return true;
    }

    public final boolean z() {
        return this.f;
    }
}
