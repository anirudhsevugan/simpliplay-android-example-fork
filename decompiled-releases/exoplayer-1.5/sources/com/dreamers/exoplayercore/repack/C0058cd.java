package com.dreamers.exoplayercore.repack;

import androidx.appcompat.widget.ActivityChooserView;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* renamed from: com.dreamers.exoplayercore.repack.cd  reason: case insensitive filesystem */
abstract class C0058cd extends cI {
    C0058cd() {
    }

    /* access modifiers changed from: package-private */
    public abstract Map a();

    public void clear() {
        a().clear();
    }

    public boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object a = C0055ca.a(a(), key);
            return P.a(a, entry.getValue()) && (a != null || a().containsKey(key));
        }
    }

    public boolean isEmpty() {
        return a().isEmpty();
    }

    public boolean remove(Object obj) {
        if (contains(obj)) {
            return a().keySet().remove(((Map.Entry) obj).getKey());
        }
        return false;
    }

    public boolean removeAll(Collection collection) {
        try {
            return super.removeAll((Collection) C0000a.a((Object) collection));
        } catch (UnsupportedOperationException e) {
            return cF.a((Set) this, collection.iterator());
        }
    }

    public boolean retainAll(Collection collection) {
        int i;
        try {
            return super.retainAll((Collection) C0000a.a((Object) collection));
        } catch (UnsupportedOperationException e) {
            int size = collection.size();
            if (size < 3) {
                C0000a.a(size, "expectedSize");
                i = size + 1;
            } else {
                i = size < 1073741824 ? (int) ((((float) size) / 0.75f) + 1.0f) : ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            }
            HashSet hashSet = new HashSet(i);
            for (Object next : collection) {
                if (contains(next)) {
                    hashSet.add(((Map.Entry) next).getKey());
                }
            }
            return a().keySet().retainAll(hashSet);
        }
    }

    public int size() {
        return a().size();
    }
}
