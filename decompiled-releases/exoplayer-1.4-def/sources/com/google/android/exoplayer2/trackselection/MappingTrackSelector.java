package com.google.android.exoplayer2.trackselection;

import android.util.Pair;
import com.google.android.exoplayer2.RendererCapabilities;
import com.google.android.exoplayer2.RendererCapabilities$$CC;
import com.google.android.exoplayer2.RendererConfiguration;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;

public abstract class MappingTrackSelector extends TrackSelector {
    private MappedTrackInfo currentMappedTrackInfo;

    public final class MappedTrackInfo {
        public static final int RENDERER_SUPPORT_EXCEEDS_CAPABILITIES_TRACKS = 2;
        public static final int RENDERER_SUPPORT_NO_TRACKS = 0;
        public static final int RENDERER_SUPPORT_PLAYABLE_TRACKS = 3;
        public static final int RENDERER_SUPPORT_UNSUPPORTED_TRACKS = 1;
        private final int rendererCount;
        private final int[][][] rendererFormatSupports;
        private final int[] rendererMixedMimeTypeAdaptiveSupports;
        private final String[] rendererNames;
        private final TrackGroupArray[] rendererTrackGroups;
        private final int[] rendererTrackTypes;
        private final TrackGroupArray unmappedTrackGroups;

        MappedTrackInfo(String[] strArr, int[] iArr, TrackGroupArray[] trackGroupArrayArr, int[] iArr2, int[][][] iArr3, TrackGroupArray trackGroupArray) {
            this.rendererNames = strArr;
            this.rendererTrackTypes = iArr;
            this.rendererTrackGroups = trackGroupArrayArr;
            this.rendererFormatSupports = iArr3;
            this.rendererMixedMimeTypeAdaptiveSupports = iArr2;
            this.unmappedTrackGroups = trackGroupArray;
            this.rendererCount = iArr.length;
        }

        public final int getAdaptiveSupport(int i, int i2, boolean z) {
            int i3 = this.rendererTrackGroups[i].b[i2].a;
            int[] iArr = new int[i3];
            int i4 = 0;
            for (int i5 = 0; i5 < i3; i5++) {
                int trackSupport = getTrackSupport(i, i2, i5);
                if (trackSupport == 4 || (z && trackSupport == 3)) {
                    iArr[i4] = i5;
                    i4++;
                }
            }
            return getAdaptiveSupport(i, i2, Arrays.copyOf(iArr, i4));
        }

        public final int getAdaptiveSupport(int i, int i2, int[] iArr) {
            int i3 = 0;
            String str = null;
            boolean z = false;
            int i4 = 0;
            int i5 = 16;
            while (i3 < iArr.length) {
                String str2 = this.rendererTrackGroups[i].b[i2].b[iArr[i3]].l;
                int i6 = i4 + 1;
                if (i4 == 0) {
                    str = str2;
                } else {
                    z |= !Util.a((Object) str, (Object) str2);
                }
                i5 = Math.min(i5, RendererCapabilities$$CC.c(this.rendererFormatSupports[i][i2][i3]));
                i3++;
                i4 = i6;
            }
            return z ? Math.min(i5, this.rendererMixedMimeTypeAdaptiveSupports[i]) : i5;
        }

        public final int getRendererCount() {
            return this.rendererCount;
        }

        public final String getRendererName(int i) {
            return this.rendererNames[i];
        }

        public final int getRendererSupport(int i) {
            int i2;
            int i3 = 0;
            for (int[] iArr : this.rendererFormatSupports[i]) {
                for (int b : r9[r2]) {
                    switch (RendererCapabilities$$CC.b(b)) {
                        case 0:
                        case 1:
                        case 2:
                            i2 = 1;
                            break;
                        case 3:
                            i2 = 2;
                            break;
                        case 4:
                            return 3;
                        default:
                            throw new IllegalStateException();
                    }
                    i3 = Math.max(i3, i2);
                }
            }
            return i3;
        }

        public final int getRendererType(int i) {
            return this.rendererTrackTypes[i];
        }

        public final TrackGroupArray getTrackGroups(int i) {
            return this.rendererTrackGroups[i];
        }

        public final int getTrackSupport(int i, int i2, int i3) {
            return RendererCapabilities$$CC.b(this.rendererFormatSupports[i][i2][i3]);
        }

        public final int getTypeSupport(int i) {
            int i2 = 0;
            for (int i3 = 0; i3 < this.rendererCount; i3++) {
                if (this.rendererTrackTypes[i3] == i) {
                    i2 = Math.max(i2, getRendererSupport(i3));
                }
            }
            return i2;
        }

        public final TrackGroupArray getUnmappedTrackGroups() {
            return this.unmappedTrackGroups;
        }
    }

    private static int findRenderer(RendererCapabilities[] rendererCapabilitiesArr, TrackGroup trackGroup, int[] iArr, boolean z) {
        int length = rendererCapabilitiesArr.length;
        int i = 0;
        boolean z2 = true;
        for (int i2 = 0; i2 < rendererCapabilitiesArr.length; i2++) {
            RendererCapabilities rendererCapabilities = rendererCapabilitiesArr[i2];
            int i3 = 0;
            for (int i4 = 0; i4 < trackGroup.a; i4++) {
                i3 = Math.max(i3, RendererCapabilities$$CC.b(rendererCapabilities.a(trackGroup.b[i4])));
            }
            boolean z3 = iArr[i2] == 0;
            if (i3 > i || (i3 == i && z && !z2 && z3)) {
                length = i2;
                z2 = z3;
                i = i3;
            }
        }
        return length;
    }

    private static int[] getFormatSupport(RendererCapabilities rendererCapabilities, TrackGroup trackGroup) {
        int[] iArr = new int[trackGroup.a];
        for (int i = 0; i < trackGroup.a; i++) {
            iArr[i] = rendererCapabilities.a(trackGroup.b[i]);
        }
        return iArr;
    }

    private static int[] getMixedMimeTypeAdaptationSupports(RendererCapabilities[] rendererCapabilitiesArr) {
        int length = rendererCapabilitiesArr.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = rendererCapabilitiesArr[i].o();
        }
        return iArr;
    }

    public final MappedTrackInfo getCurrentMappedTrackInfo() {
        return this.currentMappedTrackInfo;
    }

    public final void onSelectionActivated(Object obj) {
        this.currentMappedTrackInfo = (MappedTrackInfo) obj;
    }

    /* access modifiers changed from: protected */
    public abstract Pair selectTracks(MappedTrackInfo mappedTrackInfo, int[][][] iArr, int[] iArr2, MediaSource.MediaPeriodId mediaPeriodId, Timeline timeline);

    public final TrackSelectorResult selectTracks(RendererCapabilities[] rendererCapabilitiesArr, TrackGroupArray trackGroupArray, MediaSource.MediaPeriodId mediaPeriodId, Timeline timeline) {
        RendererCapabilities[] rendererCapabilitiesArr2 = rendererCapabilitiesArr;
        TrackGroupArray trackGroupArray2 = trackGroupArray;
        int[] iArr = new int[(rendererCapabilitiesArr2.length + 1)];
        int length = rendererCapabilitiesArr2.length + 1;
        TrackGroup[][] trackGroupArr = new TrackGroup[length][];
        int[][][] iArr2 = new int[(rendererCapabilitiesArr2.length + 1)][][];
        for (int i = 0; i < length; i++) {
            trackGroupArr[i] = new TrackGroup[trackGroupArray2.length];
            iArr2[i] = new int[trackGroupArray2.length][];
        }
        int[] mixedMimeTypeAdaptationSupports = getMixedMimeTypeAdaptationSupports(rendererCapabilitiesArr);
        for (int i2 = 0; i2 < trackGroupArray2.length; i2++) {
            TrackGroup trackGroup = trackGroupArray2.b[i2];
            int findRenderer = findRenderer(rendererCapabilitiesArr, trackGroup, iArr, MimeTypes.h(trackGroup.b[0].l) == 5);
            int[] formatSupport = findRenderer == rendererCapabilitiesArr2.length ? new int[trackGroup.a] : getFormatSupport(rendererCapabilitiesArr2[findRenderer], trackGroup);
            int i3 = iArr[findRenderer];
            trackGroupArr[findRenderer][i3] = trackGroup;
            iArr2[findRenderer][i3] = formatSupport;
            iArr[findRenderer] = i3 + 1;
        }
        TrackGroupArray[] trackGroupArrayArr = new TrackGroupArray[rendererCapabilitiesArr2.length];
        String[] strArr = new String[rendererCapabilitiesArr2.length];
        int[] iArr3 = new int[rendererCapabilitiesArr2.length];
        for (int i4 = 0; i4 < rendererCapabilitiesArr2.length; i4++) {
            int i5 = iArr[i4];
            trackGroupArrayArr[i4] = new TrackGroupArray((TrackGroup[]) Util.a((Object[]) trackGroupArr[i4], i5));
            iArr2[i4] = (int[][]) Util.a((Object[]) iArr2[i4], i5);
            strArr[i4] = rendererCapabilitiesArr2[i4].x();
            iArr3[i4] = rendererCapabilitiesArr2[i4].a();
        }
        MappedTrackInfo mappedTrackInfo = new MappedTrackInfo(strArr, iArr3, trackGroupArrayArr, mixedMimeTypeAdaptationSupports, iArr2, new TrackGroupArray((TrackGroup[]) Util.a((Object[]) trackGroupArr[rendererCapabilitiesArr2.length], iArr[rendererCapabilitiesArr2.length])));
        Pair selectTracks = selectTracks(mappedTrackInfo, iArr2, mixedMimeTypeAdaptationSupports, mediaPeriodId, timeline);
        return new TrackSelectorResult((RendererConfiguration[]) selectTracks.first, (ExoTrackSelection[]) selectTracks.second, mappedTrackInfo);
    }
}
