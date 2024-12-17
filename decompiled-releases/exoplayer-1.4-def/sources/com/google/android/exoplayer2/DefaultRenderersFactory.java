package com.google.android.exoplayer2;

import android.content.Context;
import android.os.Handler;
import com.google.android.exoplayer2.audio.AudioCapabilities;
import com.google.android.exoplayer2.audio.AudioProcessor;
import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.audio.DefaultAudioSink;
import com.google.android.exoplayer2.audio.MediaCodecAudioRenderer;
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector;
import com.google.android.exoplayer2.metadata.MetadataOutput;
import com.google.android.exoplayer2.metadata.MetadataRenderer;
import com.google.android.exoplayer2.text.TextOutput;
import com.google.android.exoplayer2.text.TextRenderer;
import com.google.android.exoplayer2.video.MediaCodecVideoRenderer;
import com.google.android.exoplayer2.video.VideoRendererEventListener;
import com.google.android.exoplayer2.video.spherical.CameraMotionRenderer;
import java.util.ArrayList;

public class DefaultRenderersFactory implements RenderersFactory {
    private final Context a;
    private long b = 5000;
    private MediaCodecSelector c = MediaCodecSelector.a;

    public DefaultRenderersFactory(Context context) {
        this.a = context;
    }

    public final Renderer[] a(Handler handler, VideoRendererEventListener videoRendererEventListener, AudioRendererEventListener audioRendererEventListener, TextOutput textOutput, MetadataOutput metadataOutput) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new MediaCodecVideoRenderer(this.a, this.c, this.b, handler, videoRendererEventListener));
        arrayList.add(new MediaCodecAudioRenderer(this.a, this.c, handler, audioRendererEventListener, new DefaultAudioSink(AudioCapabilities.a(this.a), new DefaultAudioSink.DefaultAudioProcessorChain(new AudioProcessor[0]))));
        arrayList.add(new TextRenderer(textOutput, handler.getLooper()));
        arrayList.add(new MetadataRenderer(metadataOutput, handler.getLooper()));
        arrayList.add(new CameraMotionRenderer());
        return (Renderer[]) arrayList.toArray(new Renderer[0]);
    }
}
