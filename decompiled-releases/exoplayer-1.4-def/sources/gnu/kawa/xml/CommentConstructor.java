package gnu.kawa.xml;

import androidx.fragment.app.FragmentTransaction;
import gnu.lists.Consumer;
import gnu.lists.XConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.MethodProc;
import gnu.mapping.Values;
import gnu.xml.TextUtils;

public class CommentConstructor extends MethodProc {
    public static final CommentConstructor commentConstructor = new CommentConstructor();

    public int numArgs() {
        return FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
    }

    public void apply(CallContext ctx) {
        Consumer saved = ctx.consumer;
        XConsumer out = NodeConstructor.pushNodeContext(ctx);
        try {
            StringBuffer sbuf = new StringBuffer();
            Object endMarker = Location.UNBOUND;
            boolean first = true;
            int i = 0;
            while (true) {
                Object arg = ctx.getNextArg(endMarker);
                if (arg == endMarker) {
                    int i2 = sbuf.length();
                    char[] buf = new char[i2];
                    sbuf.getChars(0, i2, buf, 0);
                    out.writeComment(buf, 0, i2);
                    return;
                }
                if (arg instanceof Values) {
                    Values vals = (Values) arg;
                    int it = 0;
                    while (true) {
                        int nextPos = vals.nextPos(it);
                        it = nextPos;
                        if (nextPos == 0) {
                            break;
                        }
                        if (!first) {
                            sbuf.append(' ');
                        }
                        first = false;
                        TextUtils.stringValue(vals.getPosPrevious(it), sbuf);
                    }
                } else {
                    if (!first) {
                        sbuf.append(' ');
                    }
                    first = false;
                    TextUtils.stringValue(arg, sbuf);
                }
                i++;
            }
        } finally {
            NodeConstructor.popNodeContext(saved, ctx);
        }
    }
}
