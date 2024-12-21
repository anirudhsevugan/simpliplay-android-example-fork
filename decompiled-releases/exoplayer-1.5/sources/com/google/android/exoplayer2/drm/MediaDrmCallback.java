package com.google.android.exoplayer2.drm;

import com.google.android.exoplayer2.drm.ExoMediaDrm;
import java.util.UUID;

public interface MediaDrmCallback {
    byte[] a(ExoMediaDrm.ProvisionRequest provisionRequest);

    byte[] a(UUID uuid, ExoMediaDrm.KeyRequest keyRequest);
}
