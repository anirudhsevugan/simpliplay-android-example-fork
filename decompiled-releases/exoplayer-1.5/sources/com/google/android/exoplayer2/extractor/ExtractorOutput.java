package com.google.android.exoplayer2.extractor;

public interface ExtractorOutput {
    public static final ExtractorOutput a = new ExtractorOutput() {
        public final TrackOutput a(int i, int i2) {
            throw new UnsupportedOperationException();
        }

        public final void a(SeekMap seekMap) {
            throw new UnsupportedOperationException();
        }

        public final void c_() {
            throw new UnsupportedOperationException();
        }
    };

    TrackOutput a(int i, int i2);

    void a(SeekMap seekMap);

    void c_();
}
