package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import java.util.Map;

public interface DataSource extends DataReader {

    public interface Factory {
        DataSource a();
    }

    long a(DataSpec dataSpec);

    Uri a();

    void a(TransferListener transferListener);

    Map b();

    void c();
}
