package com.dreamers.exoplayercore.repack;

import java.util.Map;

/* renamed from: com.dreamers.exoplayercore.repack.bi  reason: case insensitive filesystem */
abstract class C0036bi implements Map.Entry {
    C0036bi() {
    }

    public boolean equals(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            return P.a(getKey(), entry.getKey()) && P.a(getValue(), entry.getValue());
        }
    }

    public abstract Object getKey();

    public abstract Object getValue();

    public int hashCode() {
        Object key = getKey();
        Object value = getValue();
        int i = 0;
        int hashCode = key == null ? 0 : key.hashCode();
        if (value != null) {
            i = value.hashCode();
        }
        return hashCode ^ i;
    }

    public Object setValue(Object obj) {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        return getKey() + "=" + getValue();
    }
}
