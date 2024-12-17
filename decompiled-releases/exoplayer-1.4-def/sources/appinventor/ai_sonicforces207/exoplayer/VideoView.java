package appinventor.ai_sonicforces207.exoplayer;

import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.AppInventorCompatActivity;
import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.Notifier;
import com.google.appinventor.components.runtime.TinyDB;
import com.google.appinventor.components.runtime.WebViewer;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.errors.StopBlocksExecution;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import com.google.appinventor.components.runtime.util.RetValManager;
import com.google.appinventor.components.runtime.util.RuntimeErrorAlert;
import com.google.youngandroid.runtime;
import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.Apply;
import gnu.kawa.functions.Format;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.functions.IsEqual;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.reflect.SlotGet;
import gnu.kawa.reflect.SlotSet;
import gnu.lists.Consumer;
import gnu.lists.FString;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.lists.VoidConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import kawa.lang.Promise;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.strings;
import kawa.standard.Scheme;
import kawa.standard.require;
import kawa.standard.throw_name;

/* compiled from: VideoView.yail */
public class VideoView extends Form implements Runnable {
    static final SimpleSymbol Lit0 = ((SimpleSymbol) new SimpleSymbol("VideoView").readResolve());
    static final SimpleSymbol Lit1 = ((SimpleSymbol) new SimpleSymbol("getMessage").readResolve());
    static final SimpleSymbol Lit10 = ((SimpleSymbol) new SimpleSymbol("AlignVertical").readResolve());
    static final IntNum Lit11 = IntNum.make(2);
    static final SimpleSymbol Lit12 = ((SimpleSymbol) new SimpleSymbol("AppName").readResolve());
    static final SimpleSymbol Lit13;
    static final SimpleSymbol Lit14 = ((SimpleSymbol) new SimpleSymbol("PrimaryColor").readResolve());
    static final IntNum Lit15;
    static final SimpleSymbol Lit16 = ((SimpleSymbol) new SimpleSymbol("PrimaryColorDark").readResolve());
    static final IntNum Lit17;
    static final SimpleSymbol Lit18 = ((SimpleSymbol) new SimpleSymbol("ScreenOrientation").readResolve());
    static final SimpleSymbol Lit19 = ((SimpleSymbol) new SimpleSymbol("ShowListsAsJson").readResolve());
    static final SimpleSymbol Lit2 = ((SimpleSymbol) new SimpleSymbol("*the-null-value*").readResolve());
    static final SimpleSymbol Lit20 = ((SimpleSymbol) new SimpleSymbol("Sizing").readResolve());
    static final SimpleSymbol Lit21 = ((SimpleSymbol) new SimpleSymbol("Title").readResolve());
    static final SimpleSymbol Lit22 = ((SimpleSymbol) new SimpleSymbol("TitleVisible").readResolve());
    static final SimpleSymbol Lit23 = ((SimpleSymbol) new SimpleSymbol("Notifier1").readResolve());
    static final SimpleSymbol Lit24 = ((SimpleSymbol) new SimpleSymbol("ShowChooseDialog").readResolve());
    static final PairWithPosition Lit25;
    static final SimpleSymbol Lit26 = ((SimpleSymbol) new SimpleSymbol("VideoView$BackPressed").readResolve());
    static final SimpleSymbol Lit27 = ((SimpleSymbol) new SimpleSymbol("BackPressed").readResolve());
    static final FString Lit28 = new FString("com.google.appinventor.components.runtime.WebViewer");
    static final SimpleSymbol Lit29 = ((SimpleSymbol) new SimpleSymbol("WebViewer1").readResolve());
    static final SimpleSymbol Lit3 = ((SimpleSymbol) new SimpleSymbol("AccentColor").readResolve());
    static final SimpleSymbol Lit30 = ((SimpleSymbol) new SimpleSymbol("HomeUrl").readResolve());
    static final FString Lit31 = new FString("com.google.appinventor.components.runtime.WebViewer");
    static final FString Lit32 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit33 = ((SimpleSymbol) new SimpleSymbol("HorizontalArrangement1").readResolve());
    static final SimpleSymbol Lit34 = ((SimpleSymbol) new SimpleSymbol("Width").readResolve());
    static final IntNum Lit35 = IntNum.make(-2);
    static final FString Lit36 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final FString Lit37 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit38 = ((SimpleSymbol) new SimpleSymbol("Button1").readResolve());
    static final SimpleSymbol Lit39 = ((SimpleSymbol) new SimpleSymbol("Text").readResolve());
    static final IntNum Lit4;
    static final FString Lit40 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit41 = ((SimpleSymbol) new SimpleSymbol("Reload").readResolve());
    static final SimpleSymbol Lit42 = ((SimpleSymbol) new SimpleSymbol("Button1$Click").readResolve());
    static final SimpleSymbol Lit43 = ((SimpleSymbol) new SimpleSymbol("Click").readResolve());
    static final FString Lit44 = new FString("com.google.appinventor.components.runtime.Notifier");
    static final FString Lit45 = new FString("com.google.appinventor.components.runtime.Notifier");
    static final SimpleSymbol Lit46 = ((SimpleSymbol) new SimpleSymbol("$choice").readResolve());
    static final PairWithPosition Lit47;
    static final SimpleSymbol Lit48 = ((SimpleSymbol) new SimpleSymbol("Notifier1$AfterChoosing").readResolve());
    static final SimpleSymbol Lit49 = ((SimpleSymbol) new SimpleSymbol("AfterChoosing").readResolve());
    static final SimpleSymbol Lit5 = ((SimpleSymbol) new SimpleSymbol("number").readResolve());
    static final FString Lit50 = new FString("com.google.appinventor.components.runtime.TinyDB");
    static final SimpleSymbol Lit51 = ((SimpleSymbol) new SimpleSymbol(TinyDB.DEFAULT_NAMESPACE).readResolve());
    static final SimpleSymbol Lit52 = ((SimpleSymbol) new SimpleSymbol("Namespace").readResolve());
    static final FString Lit53 = new FString("com.google.appinventor.components.runtime.TinyDB");
    static final SimpleSymbol Lit54 = ((SimpleSymbol) new SimpleSymbol("get-simple-name").readResolve());
    static final SimpleSymbol Lit55 = ((SimpleSymbol) new SimpleSymbol("android-log-form").readResolve());
    static final SimpleSymbol Lit56 = ((SimpleSymbol) new SimpleSymbol("add-to-form-environment").readResolve());
    static final SimpleSymbol Lit57 = ((SimpleSymbol) new SimpleSymbol("lookup-in-form-environment").readResolve());
    static final SimpleSymbol Lit58 = ((SimpleSymbol) new SimpleSymbol("is-bound-in-form-environment").readResolve());
    static final SimpleSymbol Lit59 = ((SimpleSymbol) new SimpleSymbol("add-to-global-var-environment").readResolve());
    static final SimpleSymbol Lit6 = ((SimpleSymbol) new SimpleSymbol("ActionBar").readResolve());
    static final SimpleSymbol Lit60 = ((SimpleSymbol) new SimpleSymbol("add-to-events").readResolve());
    static final SimpleSymbol Lit61 = ((SimpleSymbol) new SimpleSymbol("add-to-components").readResolve());
    static final SimpleSymbol Lit62 = ((SimpleSymbol) new SimpleSymbol("add-to-global-vars").readResolve());
    static final SimpleSymbol Lit63 = ((SimpleSymbol) new SimpleSymbol("add-to-form-do-after-creation").readResolve());
    static final SimpleSymbol Lit64 = ((SimpleSymbol) new SimpleSymbol("send-error").readResolve());
    static final SimpleSymbol Lit65 = ((SimpleSymbol) new SimpleSymbol("dispatchEvent").readResolve());
    static final SimpleSymbol Lit66 = ((SimpleSymbol) new SimpleSymbol("dispatchGenericEvent").readResolve());
    static final SimpleSymbol Lit67 = ((SimpleSymbol) new SimpleSymbol("lookup-handler").readResolve());
    static final SimpleSymbol Lit68;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8 = ((SimpleSymbol) new SimpleSymbol("AlignHorizontal").readResolve());
    static final IntNum Lit9 = IntNum.make(3);
    public static VideoView VideoView;
    static final ModuleMethod lambda$Fn1 = null;
    static final ModuleMethod lambda$Fn10 = null;
    static final ModuleMethod lambda$Fn2 = null;
    static final ModuleMethod lambda$Fn3 = null;
    static final ModuleMethod lambda$Fn4 = null;
    static final ModuleMethod lambda$Fn5 = null;
    static final ModuleMethod lambda$Fn6 = null;
    static final ModuleMethod lambda$Fn7 = null;
    static final ModuleMethod lambda$Fn8 = null;
    static final ModuleMethod lambda$Fn9 = null;
    public Boolean $Stdebug$Mnform$St;
    public final ModuleMethod $define;
    public Button Button1;
    public final ModuleMethod Button1$Click;
    public HorizontalArrangement HorizontalArrangement1;
    public Notifier Notifier1;
    public final ModuleMethod Notifier1$AfterChoosing;
    public TinyDB TinyDB1;
    public final ModuleMethod VideoView$BackPressed;
    public WebViewer WebViewer1;
    public final ModuleMethod add$Mnto$Mncomponents;
    public final ModuleMethod add$Mnto$Mnevents;
    public final ModuleMethod add$Mnto$Mnform$Mndo$Mnafter$Mncreation;
    public final ModuleMethod add$Mnto$Mnform$Mnenvironment;
    public final ModuleMethod add$Mnto$Mnglobal$Mnvar$Mnenvironment;
    public final ModuleMethod add$Mnto$Mnglobal$Mnvars;
    public final ModuleMethod android$Mnlog$Mnform;
    public LList components$Mnto$Mncreate;
    public final ModuleMethod dispatchEvent;
    public final ModuleMethod dispatchGenericEvent;
    public LList events$Mnto$Mnregister;
    public LList form$Mndo$Mnafter$Mncreation;
    public Environment form$Mnenvironment;
    public Symbol form$Mnname$Mnsymbol;
    public final ModuleMethod get$Mnsimple$Mnname;
    public Environment global$Mnvar$Mnenvironment;
    public LList global$Mnvars$Mnto$Mncreate;
    public final ModuleMethod is$Mnbound$Mnin$Mnform$Mnenvironment;
    public final ModuleMethod lookup$Mnhandler;
    public final ModuleMethod lookup$Mnin$Mnform$Mnenvironment;
    public final ModuleMethod onCreate;
    public final ModuleMethod process$Mnexception;
    public final ModuleMethod send$Mnerror;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("any").readResolve();
        Lit68 = simpleSymbol;
        Lit47 = PairWithPosition.make(simpleSymbol, PairWithPosition.make(simpleSymbol, LList.Empty, "/tmp/1734468769417_7147412059587560448-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/VideoView.yail", 233571), "/tmp/1734468769417_7147412059587560448-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/VideoView.yail", 233566);
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol(PropertyTypeConstants.PROPERTY_TYPE_TEXT).readResolve();
        Lit13 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol(PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN).readResolve();
        Lit7 = simpleSymbol3;
        Lit25 = PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol3, LList.Empty, "/tmp/1734468769417_7147412059587560448-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/VideoView.yail", 102644), "/tmp/1734468769417_7147412059587560448-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/VideoView.yail", 102639), "/tmp/1734468769417_7147412059587560448-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/VideoView.yail", 102634), "/tmp/1734468769417_7147412059587560448-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/VideoView.yail", 102629), "/tmp/1734468769417_7147412059587560448-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/VideoView.yail", 102623);
        int[] iArr = new int[2];
        iArr[0] = -16357686;
        Lit17 = IntNum.make(iArr);
        int[] iArr2 = new int[2];
        iArr2[0] = -16342306;
        Lit15 = IntNum.make(iArr2);
        int[] iArr3 = new int[2];
        iArr3[0] = -16726785;
        Lit4 = IntNum.make(iArr3);
    }

    public VideoView() {
        ModuleInfo.register(this);
        frame frame2 = new frame();
        frame2.$main = this;
        this.get$Mnsimple$Mnname = new ModuleMethod(frame2, 1, Lit54, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.onCreate = new ModuleMethod(frame2, 2, "onCreate", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.android$Mnlog$Mnform = new ModuleMethod(frame2, 3, Lit55, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.add$Mnto$Mnform$Mnenvironment = new ModuleMethod(frame2, 4, Lit56, 8194);
        this.lookup$Mnin$Mnform$Mnenvironment = new ModuleMethod(frame2, 5, Lit57, 8193);
        this.is$Mnbound$Mnin$Mnform$Mnenvironment = new ModuleMethod(frame2, 7, Lit58, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.add$Mnto$Mnglobal$Mnvar$Mnenvironment = new ModuleMethod(frame2, 8, Lit59, 8194);
        this.add$Mnto$Mnevents = new ModuleMethod(frame2, 9, Lit60, 8194);
        this.add$Mnto$Mncomponents = new ModuleMethod(frame2, 10, Lit61, 16388);
        this.add$Mnto$Mnglobal$Mnvars = new ModuleMethod(frame2, 11, Lit62, 8194);
        this.add$Mnto$Mnform$Mndo$Mnafter$Mncreation = new ModuleMethod(frame2, 12, Lit63, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.send$Mnerror = new ModuleMethod(frame2, 13, Lit64, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.process$Mnexception = new ModuleMethod(frame2, 14, "process-exception", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.dispatchEvent = new ModuleMethod(frame2, 15, Lit65, 16388);
        this.dispatchGenericEvent = new ModuleMethod(frame2, 16, Lit66, 16388);
        this.lookup$Mnhandler = new ModuleMethod(frame2, 17, Lit67, 8194);
        ModuleMethod moduleMethod = new ModuleMethod(frame2, 18, (Object) null, 0);
        moduleMethod.setProperty("source-location", "/tmp/runtime7811953657198212853.scm:634");
        lambda$Fn1 = moduleMethod;
        this.$define = new ModuleMethod(frame2, 19, "$define", 0);
        lambda$Fn2 = new ModuleMethod(frame2, 20, (Object) null, 0);
        this.VideoView$BackPressed = new ModuleMethod(frame2, 21, Lit26, 0);
        lambda$Fn3 = new ModuleMethod(frame2, 22, (Object) null, 0);
        lambda$Fn4 = new ModuleMethod(frame2, 23, (Object) null, 0);
        lambda$Fn5 = new ModuleMethod(frame2, 24, (Object) null, 0);
        lambda$Fn6 = new ModuleMethod(frame2, 25, (Object) null, 0);
        lambda$Fn7 = new ModuleMethod(frame2, 26, (Object) null, 0);
        lambda$Fn8 = new ModuleMethod(frame2, 27, (Object) null, 0);
        this.Button1$Click = new ModuleMethod(frame2, 28, Lit42, 0);
        this.Notifier1$AfterChoosing = new ModuleMethod(frame2, 29, Lit48, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        lambda$Fn9 = new ModuleMethod(frame2, 30, (Object) null, 0);
        lambda$Fn10 = new ModuleMethod(frame2, 31, (Object) null, 0);
    }

    public Object lookupInFormEnvironment(Symbol symbol) {
        return lookupInFormEnvironment(symbol, Boolean.FALSE);
    }

    public void run() {
        CallContext instance = CallContext.getInstance();
        Consumer consumer = instance.consumer;
        instance.consumer = VoidConsumer.instance;
        try {
            run(instance);
            th = null;
        } catch (Throwable th) {
            th = th;
        }
        ModuleBody.runCleanup(instance, th, consumer);
    }

    public final void run(CallContext $ctx) {
        Consumer $result = $ctx.consumer;
        Object find = require.find("com.google.youngandroid.runtime");
        try {
            ((Runnable) find).run();
            this.$Stdebug$Mnform$St = Boolean.FALSE;
            SimpleSymbol simpleSymbol = Lit0;
            this.form$Mnenvironment = Environment.make(misc.symbol$To$String(simpleSymbol));
            FString stringAppend = strings.stringAppend(misc.symbol$To$String(simpleSymbol), "-global-vars");
            this.global$Mnvar$Mnenvironment = Environment.make(stringAppend == null ? null : stringAppend.toString());
            VideoView = null;
            this.form$Mnname$Mnsymbol = simpleSymbol;
            this.events$Mnto$Mnregister = LList.Empty;
            this.components$Mnto$Mncreate = LList.Empty;
            this.global$Mnvars$Mnto$Mncreate = LList.Empty;
            this.form$Mndo$Mnafter$Mncreation = LList.Empty;
            Object find2 = require.find("com.google.youngandroid.runtime");
            try {
                ((Runnable) find2).run();
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    SimpleSymbol simpleSymbol2 = Lit3;
                    IntNum intNum = Lit4;
                    SimpleSymbol simpleSymbol3 = Lit5;
                    runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, intNum, simpleSymbol3);
                    SimpleSymbol simpleSymbol4 = Lit6;
                    Boolean bool = Boolean.TRUE;
                    SimpleSymbol simpleSymbol5 = Lit7;
                    runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol4, bool, simpleSymbol5);
                    runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit8, Lit9, simpleSymbol3);
                    runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit10, Lit11, simpleSymbol3);
                    SimpleSymbol simpleSymbol6 = Lit12;
                    SimpleSymbol simpleSymbol7 = Lit13;
                    runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol6, "exoplayer", simpleSymbol7);
                    runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit14, Lit15, simpleSymbol3);
                    runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit16, Lit17, simpleSymbol3);
                    runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit18, "unspecified", simpleSymbol7);
                    runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit19, Boolean.TRUE, simpleSymbol5);
                    runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit20, "Responsive", simpleSymbol7);
                    runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit21, "VideoView", simpleSymbol7);
                    Values.writeValues(runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit22, Boolean.FALSE, simpleSymbol5), $result);
                } else {
                    addToFormDoAfterCreation(new Promise(lambda$Fn2));
                }
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    runtime.addToCurrentFormEnvironment(Lit26, this.VideoView$BackPressed);
                } else {
                    addToFormEnvironment(Lit26, this.VideoView$BackPressed);
                }
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) runtime.$Stthis$Mnform$St, "VideoView", "BackPressed");
                } else {
                    addToEvents(simpleSymbol, Lit27);
                }
                this.WebViewer1 = null;
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(runtime.addComponentWithinRepl(simpleSymbol, Lit28, Lit29, lambda$Fn3), $result);
                } else {
                    addToComponents(simpleSymbol, Lit31, Lit29, lambda$Fn4);
                }
                this.HorizontalArrangement1 = null;
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(runtime.addComponentWithinRepl(simpleSymbol, Lit32, Lit33, lambda$Fn5), $result);
                } else {
                    addToComponents(simpleSymbol, Lit36, Lit33, lambda$Fn6);
                }
                this.Button1 = null;
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(runtime.addComponentWithinRepl(Lit33, Lit37, Lit38, lambda$Fn7), $result);
                } else {
                    addToComponents(Lit33, Lit40, Lit38, lambda$Fn8);
                }
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    runtime.addToCurrentFormEnvironment(Lit42, this.Button1$Click);
                } else {
                    addToFormEnvironment(Lit42, this.Button1$Click);
                }
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) runtime.$Stthis$Mnform$St, "Button1", "Click");
                } else {
                    addToEvents(Lit38, Lit43);
                }
                this.Notifier1 = null;
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(runtime.addComponentWithinRepl(simpleSymbol, Lit44, Lit23, Boolean.FALSE), $result);
                } else {
                    addToComponents(simpleSymbol, Lit45, Lit23, Boolean.FALSE);
                }
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    runtime.addToCurrentFormEnvironment(Lit48, this.Notifier1$AfterChoosing);
                } else {
                    addToFormEnvironment(Lit48, this.Notifier1$AfterChoosing);
                }
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    EventDispatcher.registerEventForDelegation((HandlesEventDispatching) runtime.$Stthis$Mnform$St, "Notifier1", "AfterChoosing");
                } else {
                    addToEvents(Lit23, Lit49);
                }
                this.TinyDB1 = null;
                if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
                    Values.writeValues(runtime.addComponentWithinRepl(simpleSymbol, Lit50, Lit51, lambda$Fn9), $result);
                } else {
                    addToComponents(simpleSymbol, Lit53, Lit51, lambda$Fn10);
                }
                runtime.initRuntime();
            } catch (ClassCastException e) {
                throw new WrongType(e, "java.lang.Runnable.run()", 1, find2);
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "java.lang.Runnable.run()", 1, find);
        }
    }

    static Object lambda3() {
        SimpleSymbol simpleSymbol = Lit0;
        SimpleSymbol simpleSymbol2 = Lit3;
        IntNum intNum = Lit4;
        SimpleSymbol simpleSymbol3 = Lit5;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, intNum, simpleSymbol3);
        SimpleSymbol simpleSymbol4 = Lit6;
        Boolean bool = Boolean.TRUE;
        SimpleSymbol simpleSymbol5 = Lit7;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol4, bool, simpleSymbol5);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit8, Lit9, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit10, Lit11, simpleSymbol3);
        SimpleSymbol simpleSymbol6 = Lit12;
        SimpleSymbol simpleSymbol7 = Lit13;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol6, "exoplayer", simpleSymbol7);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit14, Lit15, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit16, Lit17, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit18, "unspecified", simpleSymbol7);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit19, Boolean.TRUE, simpleSymbol5);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit20, "Responsive", simpleSymbol7);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit21, "VideoView", simpleSymbol7);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit22, Boolean.FALSE, simpleSymbol5);
    }

    public Object VideoView$BackPressed() {
        runtime.setThisForm();
        SimpleSymbol simpleSymbol = Lit23;
        SimpleSymbol simpleSymbol2 = Lit24;
        Pair list1 = LList.list1("This will kill your existing HTML5 initialization if you press \"Yes\".");
        LList.chain4(list1, "Are you sure you want to exit HTML5 video player?", "Yes", "No", Boolean.FALSE);
        return runtime.callComponentMethod(simpleSymbol, simpleSymbol2, list1, Lit25);
    }

    static Object lambda4() {
        return runtime.setAndCoerceProperty$Ex(Lit29, Lit30, "file:///android_asset/videoPlayer.html", Lit13);
    }

    static Object lambda5() {
        return runtime.setAndCoerceProperty$Ex(Lit29, Lit30, "file:///android_asset/videoPlayer.html", Lit13);
    }

    static Object lambda6() {
        SimpleSymbol simpleSymbol = Lit33;
        SimpleSymbol simpleSymbol2 = Lit8;
        IntNum intNum = Lit9;
        SimpleSymbol simpleSymbol3 = Lit5;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, intNum, simpleSymbol3);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit34, Lit35, simpleSymbol3);
    }

    static Object lambda7() {
        SimpleSymbol simpleSymbol = Lit33;
        SimpleSymbol simpleSymbol2 = Lit8;
        IntNum intNum = Lit9;
        SimpleSymbol simpleSymbol3 = Lit5;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, intNum, simpleSymbol3);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit34, Lit35, simpleSymbol3);
    }

    static Object lambda8() {
        return runtime.setAndCoerceProperty$Ex(Lit38, Lit39, "Refresh page", Lit13);
    }

    static Object lambda9() {
        return runtime.setAndCoerceProperty$Ex(Lit38, Lit39, "Refresh page", Lit13);
    }

    public Object Button1$Click() {
        runtime.setThisForm();
        return runtime.callComponentMethod(Lit29, Lit41, LList.Empty, LList.Empty);
    }

    public Object Notifier1$AfterChoosing(Object $choice) {
        Object obj;
        Object $choice2 = runtime.sanitizeComponentData($choice);
        runtime.setThisForm();
        ModuleMethod moduleMethod = runtime.yail$Mnequal$Qu;
        if ($choice2 instanceof Package) {
            obj = runtime.signalRuntimeError(strings.stringAppend("The variable ", runtime.getDisplayRepresentation(Lit46), " is not bound in the current context"), "Unbound Variable");
        } else {
            obj = $choice2;
        }
        return runtime.callYailPrimitive(moduleMethod, LList.list2(obj, "Yes"), Lit47, "=") != Boolean.FALSE ? runtime.callYailPrimitive(runtime.close$Mnscreen, LList.Empty, LList.Empty, "close screen") : Values.empty;
    }

    static Object lambda10() {
        return runtime.setAndCoerceProperty$Ex(Lit51, Lit52, "videoViewSettingsDB", Lit13);
    }

    static Object lambda11() {
        return runtime.setAndCoerceProperty$Ex(Lit51, Lit52, "videoViewSettingsDB", Lit13);
    }

    /* compiled from: VideoView.yail */
    public class frame extends ModuleBody {
        VideoView $main;

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 1:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 2:
                    if (!(obj instanceof VideoView)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 3:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 5:
                    if (!(obj instanceof Symbol)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 7:
                    if (!(obj instanceof Symbol)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 12:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 13:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 14:
                    if (!(obj instanceof VideoView)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 29:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                default:
                    return super.match1(moduleMethod, obj, callContext);
            }
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 4:
                    if (!(obj instanceof Symbol)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                case 5:
                    if (!(obj instanceof Symbol)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                case 8:
                    if (!(obj instanceof Symbol)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                case 9:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                case 11:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                case 17:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.pc = 2;
                    return 0;
                default:
                    return super.match2(moduleMethod, obj, obj2, callContext);
            }
        }

        public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 10:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.value3 = obj3;
                    callContext.value4 = obj4;
                    callContext.proc = moduleMethod;
                    callContext.pc = 4;
                    return 0;
                case 15:
                    if (!(obj instanceof VideoView)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    if (!(obj2 instanceof Component)) {
                        return -786430;
                    }
                    callContext.value2 = obj2;
                    if (!(obj3 instanceof String)) {
                        return -786429;
                    }
                    callContext.value3 = obj3;
                    if (!(obj4 instanceof String)) {
                        return -786428;
                    }
                    callContext.value4 = obj4;
                    callContext.proc = moduleMethod;
                    callContext.pc = 4;
                    return 0;
                case 16:
                    if (!(obj instanceof VideoView)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    if (!(obj2 instanceof Component)) {
                        return -786430;
                    }
                    callContext.value2 = obj2;
                    if (!(obj3 instanceof String)) {
                        return -786429;
                    }
                    callContext.value3 = obj3;
                    callContext.value4 = obj4;
                    callContext.proc = moduleMethod;
                    callContext.pc = 4;
                    return 0;
                default:
                    return super.match4(moduleMethod, obj, obj2, obj3, obj4, callContext);
            }
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            switch (moduleMethod.selector) {
                case 1:
                    return this.$main.getSimpleName(obj);
                case 2:
                    try {
                        this.$main.onCreate((Bundle) obj);
                        return Values.empty;
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "onCreate", 1, obj);
                    }
                case 3:
                    this.$main.androidLogForm(obj);
                    return Values.empty;
                case 5:
                    try {
                        return this.$main.lookupInFormEnvironment((Symbol) obj);
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "lookup-in-form-environment", 1, obj);
                    }
                case 7:
                    try {
                        return this.$main.isBoundInFormEnvironment((Symbol) obj) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e3) {
                        throw new WrongType(e3, "is-bound-in-form-environment", 1, obj);
                    }
                case 12:
                    this.$main.addToFormDoAfterCreation(obj);
                    return Values.empty;
                case 13:
                    this.$main.sendError(obj);
                    return Values.empty;
                case 14:
                    this.$main.processException(obj);
                    return Values.empty;
                case 29:
                    return this.$main.Notifier1$AfterChoosing(obj);
                default:
                    return super.apply1(moduleMethod, obj);
            }
        }

        public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
            boolean z = true;
            switch (moduleMethod.selector) {
                case 10:
                    this.$main.addToComponents(obj, obj2, obj3, obj4);
                    return Values.empty;
                case 15:
                    try {
                        try {
                            try {
                                try {
                                    return this.$main.dispatchEvent((Component) obj, (String) obj2, (String) obj3, (Object[]) obj4) ? Boolean.TRUE : Boolean.FALSE;
                                } catch (ClassCastException e) {
                                    throw new WrongType(e, "dispatchEvent", 4, obj4);
                                }
                            } catch (ClassCastException e2) {
                                throw new WrongType(e2, "dispatchEvent", 3, obj3);
                            }
                        } catch (ClassCastException e3) {
                            throw new WrongType(e3, "dispatchEvent", 2, obj2);
                        }
                    } catch (ClassCastException e4) {
                        throw new WrongType(e4, "dispatchEvent", 1, obj);
                    }
                case 16:
                    VideoView videoView = this.$main;
                    try {
                        Component component = (Component) obj;
                        try {
                            String str = (String) obj2;
                            try {
                                if (obj3 == Boolean.FALSE) {
                                    z = false;
                                }
                                try {
                                    videoView.dispatchGenericEvent(component, str, z, (Object[]) obj4);
                                    return Values.empty;
                                } catch (ClassCastException e5) {
                                    throw new WrongType(e5, "dispatchGenericEvent", 4, obj4);
                                }
                            } catch (ClassCastException e6) {
                                throw new WrongType(e6, "dispatchGenericEvent", 3, obj3);
                            }
                        } catch (ClassCastException e7) {
                            throw new WrongType(e7, "dispatchGenericEvent", 2, obj2);
                        }
                    } catch (ClassCastException e8) {
                        throw new WrongType(e8, "dispatchGenericEvent", 1, obj);
                    }
                default:
                    return super.apply4(moduleMethod, obj, obj2, obj3, obj4);
            }
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            switch (moduleMethod.selector) {
                case 4:
                    try {
                        this.$main.addToFormEnvironment((Symbol) obj, obj2);
                        return Values.empty;
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "add-to-form-environment", 1, obj);
                    }
                case 5:
                    try {
                        return this.$main.lookupInFormEnvironment((Symbol) obj, obj2);
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "lookup-in-form-environment", 1, obj);
                    }
                case 8:
                    try {
                        this.$main.addToGlobalVarEnvironment((Symbol) obj, obj2);
                        return Values.empty;
                    } catch (ClassCastException e3) {
                        throw new WrongType(e3, "add-to-global-var-environment", 1, obj);
                    }
                case 9:
                    this.$main.addToEvents(obj, obj2);
                    return Values.empty;
                case 11:
                    this.$main.addToGlobalVars(obj, obj2);
                    return Values.empty;
                case 17:
                    return this.$main.lookupHandler(obj, obj2);
                default:
                    return super.apply2(moduleMethod, obj, obj2);
            }
        }

        public Object apply0(ModuleMethod moduleMethod) {
            switch (moduleMethod.selector) {
                case 18:
                    return VideoView.lambda2();
                case 19:
                    this.$main.$define();
                    return Values.empty;
                case 20:
                    return VideoView.lambda3();
                case 21:
                    return this.$main.VideoView$BackPressed();
                case 22:
                    return VideoView.lambda4();
                case 23:
                    return VideoView.lambda5();
                case 24:
                    return VideoView.lambda6();
                case 25:
                    return VideoView.lambda7();
                case 26:
                    return VideoView.lambda8();
                case 27:
                    return VideoView.lambda9();
                case 28:
                    return this.$main.Button1$Click();
                case 30:
                    return VideoView.lambda10();
                case 31:
                    return VideoView.lambda11();
                default:
                    return super.apply0(moduleMethod);
            }
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 18:
                    callContext.proc = moduleMethod;
                    callContext.pc = 0;
                    return 0;
                case 19:
                    callContext.proc = moduleMethod;
                    callContext.pc = 0;
                    return 0;
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 30:
                case 31:
                    callContext.proc = moduleMethod;
                    callContext.pc = 0;
                    return 0;
                default:
                    return super.match0(moduleMethod, callContext);
            }
        }
    }

    public String getSimpleName(Object object) {
        return object.getClass().getSimpleName();
    }

    public void onCreate(Bundle icicle) {
        AppInventorCompatActivity.setClassicModeFromYail(true);
        super.onCreate(icicle);
    }

    public void androidLogForm(Object message) {
    }

    public void addToFormEnvironment(Symbol name, Object object) {
        androidLogForm(Format.formatToString(0, "Adding ~A to env ~A with value ~A", name, this.form$Mnenvironment, object));
        this.form$Mnenvironment.put(name, object);
    }

    public Object lookupInFormEnvironment(Symbol name, Object default$Mnvalue) {
        Environment environment = this.form$Mnenvironment;
        boolean x = true & ((environment == null ? 1 : 0) + 1);
        if (!x ? !x : !environment.isBound(name)) {
            return default$Mnvalue;
        }
        return this.form$Mnenvironment.get(name);
    }

    public boolean isBoundInFormEnvironment(Symbol name) {
        return this.form$Mnenvironment.isBound(name);
    }

    public void addToGlobalVarEnvironment(Symbol name, Object object) {
        androidLogForm(Format.formatToString(0, "Adding ~A to env ~A with value ~A", name, this.global$Mnvar$Mnenvironment, object));
        this.global$Mnvar$Mnenvironment.put(name, object);
    }

    public void addToEvents(Object component$Mnname, Object event$Mnname) {
        this.events$Mnto$Mnregister = lists.cons(lists.cons(component$Mnname, event$Mnname), this.events$Mnto$Mnregister);
    }

    public void addToComponents(Object container$Mnname, Object component$Mntype, Object component$Mnname, Object init$Mnthunk) {
        this.components$Mnto$Mncreate = lists.cons(LList.list4(container$Mnname, component$Mntype, component$Mnname, init$Mnthunk), this.components$Mnto$Mncreate);
    }

    public void addToGlobalVars(Object var, Object val$Mnthunk) {
        this.global$Mnvars$Mnto$Mncreate = lists.cons(LList.list2(var, val$Mnthunk), this.global$Mnvars$Mnto$Mncreate);
    }

    public void addToFormDoAfterCreation(Object thunk) {
        this.form$Mndo$Mnafter$Mncreation = lists.cons(thunk, this.form$Mndo$Mnafter$Mncreation);
    }

    public void sendError(Object error) {
        RetValManager.sendError(error == null ? null : error.toString());
    }

    public void processException(Object ex) {
        Object apply1 = Scheme.applyToArgs.apply1(GetNamedPart.getNamedPart.apply2(ex, Lit1));
        RuntimeErrorAlert.alert(this, apply1 == null ? null : apply1.toString(), ex instanceof YailRuntimeError ? ((YailRuntimeError) ex).getErrorType() : "Runtime Error", "End Application");
    }

    public boolean dispatchEvent(Component componentObject, String registeredComponentName, String eventName, Object[] args) {
        SimpleSymbol registeredObject = misc.string$To$Symbol(registeredComponentName);
        if (!isBoundInFormEnvironment(registeredObject)) {
            EventDispatcher.unregisterEventForDelegation(this, registeredComponentName, eventName);
            return false;
        } else if (lookupInFormEnvironment(registeredObject) != componentObject) {
            return false;
        } else {
            boolean x = true;
            try {
                Scheme.apply.apply2(lookupHandler(registeredComponentName, eventName), LList.makeList(args, 0));
                return true;
            } catch (StopBlocksExecution e) {
                if (throw_name.throwName.apply1(e) != Boolean.FALSE) {
                    return true;
                }
                return false;
            } catch (PermissionException e2) {
                PermissionException exception = e2;
                exception.printStackTrace();
                if (this != componentObject) {
                    x = false;
                }
                if (!x ? !x : !IsEqual.apply(eventName, "PermissionNeeded")) {
                    PermissionDenied(componentObject, eventName, exception.getPermissionNeeded());
                    return false;
                }
                processException(exception);
                return false;
            } catch (Throwable th) {
                Throwable exception2 = th;
                androidLogForm(exception2.getMessage());
                exception2.printStackTrace();
                processException(exception2);
                return false;
            }
        }
    }

    public void dispatchGenericEvent(Component componentObject, String eventName, boolean notAlreadyHandled, Object[] args) {
        boolean x = false;
        Object handler = lookupInFormEnvironment(misc.string$To$Symbol(strings.stringAppend("any$", getSimpleName(componentObject), "$", eventName)));
        if (handler != Boolean.FALSE) {
            try {
                Scheme.apply.apply2(handler, lists.cons(componentObject, lists.cons(notAlreadyHandled ? Boolean.TRUE : Boolean.FALSE, LList.makeList(args, 0))));
            } catch (StopBlocksExecution e) {
                StopBlocksExecution exception = e;
            } catch (PermissionException e2) {
                PermissionException exception2 = e2;
                exception2.printStackTrace();
                if (this == componentObject) {
                    x = true;
                }
                if (!x ? !x : !IsEqual.apply(eventName, "PermissionNeeded")) {
                    PermissionDenied(componentObject, eventName, exception2.getPermissionNeeded());
                } else {
                    processException(exception2);
                }
            } catch (Throwable th) {
                Throwable exception3 = th;
                androidLogForm(exception3.getMessage());
                exception3.printStackTrace();
                processException(exception3);
            }
        }
    }

    public Object lookupHandler(Object componentName, Object eventName) {
        String str = null;
        String obj = componentName == null ? null : componentName.toString();
        if (eventName != null) {
            str = eventName.toString();
        }
        return lookupInFormEnvironment(misc.string$To$Symbol(EventDispatcher.makeFullEventName(obj, str)));
    }

    public void $define() {
        int i;
        Object arg0;
        Object arg02;
        Object arg03;
        WrongType wrongType;
        Object var;
        Object apply1;
        Object component$Mnname;
        Language.setDefaults(Scheme.getInstance());
        try {
            run();
        } catch (Exception exception) {
            Exception exception2 = exception;
            androidLogForm(exception2.getMessage());
            processException(exception2);
        }
        VideoView = this;
        addToFormEnvironment(Lit0, this);
        Object obj = this.events$Mnto$Mnregister;
        Object components = obj;
        Object arg04 = obj;
        while (true) {
            i = -2;
            if (arg04 == LList.Empty) {
                try {
                    break;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "arg0", -2, arg0);
                } catch (ClassCastException e2) {
                    throw new WrongType(e2, "arg0", -2, arg02);
                } catch (ClassCastException e3) {
                    throw new WrongType(e3, "lookup-in-form-environment", 0, apply1);
                } catch (ClassCastException e4) {
                    throw new WrongType(e4, "add-to-form-environment", 0, component$Mnname);
                } catch (ClassCastException e5) {
                    throw new WrongType(e5, "arg0", i, arg03);
                } catch (ClassCastException e6) {
                    throw new WrongType(e6, "add-to-global-var-environment", 0, var);
                } catch (YailRuntimeError exception3) {
                    Object obj2 = components;
                    processException(exception3);
                    return;
                }
            } else {
                try {
                    Pair arg05 = (Pair) arg04;
                    Object event$Mninfo = arg05.getCar();
                    Object apply12 = lists.car.apply1(event$Mninfo);
                    String str = null;
                    String obj3 = apply12 == null ? null : apply12.toString();
                    Object apply13 = lists.cdr.apply1(event$Mninfo);
                    if (apply13 != null) {
                        str = apply13.toString();
                    }
                    EventDispatcher.registerEventForDelegation(this, obj3, str);
                    arg04 = arg05.getCdr();
                } catch (ClassCastException e7) {
                    throw new WrongType(e7, "arg0", -2, arg04);
                }
            }
        }
        components = lists.reverse(this.components$Mnto$Mncreate);
        addToGlobalVars(Lit2, lambda$Fn1);
        arg0 = lists.reverse(this.form$Mndo$Mnafter$Mncreation);
        while (arg0 != LList.Empty) {
            Pair arg06 = (Pair) arg0;
            misc.force(arg06.getCar());
            arg0 = arg06.getCdr();
            i = -2;
        }
        Object var$Mnval = null;
        arg02 = components;
        while (arg02 != LList.Empty) {
            Pair arg07 = (Pair) arg02;
            Object component$Mninfo = arg07.getCar();
            Object apply14 = lists.caddr.apply1(component$Mninfo);
            lists.cadddr.apply1(component$Mninfo);
            Object component$Mntype = lists.cadr.apply1(component$Mninfo);
            apply1 = lists.car.apply1(component$Mninfo);
            component$Mnname = apply14;
            Object obj4 = apply1;
            Object component$Mnobject = Invoke.make.apply2(component$Mntype, lookupInFormEnvironment((Symbol) apply1));
            SlotSet.set$Mnfield$Ex.apply3(this, component$Mnname, component$Mnobject);
            addToFormEnvironment((Symbol) component$Mnname, component$Mnobject);
            arg02 = arg07.getCdr();
            Pair pair = arg07;
            var$Mnval = component$Mninfo;
            i = -2;
        }
        arg03 = lists.reverse(this.global$Mnvars$Mnto$Mncreate);
        while (arg03 != LList.Empty) {
            Pair arg08 = (Pair) arg03;
            var$Mnval = arg08.getCar();
            var = lists.car.apply1(var$Mnval);
            addToGlobalVarEnvironment((Symbol) var, Scheme.applyToArgs.apply1(lists.cadr.apply1(var$Mnval)));
            arg03 = arg08.getCdr();
            Pair pair2 = arg08;
        }
        Object component$Mndescriptors = components;
        Object arg09 = component$Mndescriptors;
        while (arg09 != LList.Empty) {
            try {
                Pair arg010 = (Pair) arg09;
                Object component$Mninfo2 = arg010.getCar();
                lists.caddr.apply1(component$Mninfo2);
                Object obj5 = var$Mnval;
                var$Mnval = lists.cadddr.apply1(component$Mninfo2);
                if (var$Mnval != Boolean.FALSE) {
                    Scheme.applyToArgs.apply1(var$Mnval);
                }
                arg09 = arg010.getCdr();
            } catch (ClassCastException e8) {
                wrongType = new WrongType(e8, "arg0", i, arg09);
                throw wrongType;
            }
        }
        Object arg011 = component$Mndescriptors;
        while (arg011 != LList.Empty) {
            try {
                Pair arg012 = (Pair) arg011;
                Object component$Mninfo3 = arg012.getCar();
                Object apply15 = lists.caddr.apply1(component$Mninfo3);
                lists.cadddr.apply1(component$Mninfo3);
                Object obj6 = var$Mnval;
                var$Mnval = apply15;
                callInitialize(SlotGet.field.apply2(this, var$Mnval));
                arg011 = arg012.getCdr();
            } catch (ClassCastException e9) {
                wrongType = new WrongType(e9, "arg0", i, arg011);
                throw wrongType;
            }
        }
        Object obj7 = components;
    }

    public static SimpleSymbol lambda1symbolAppend$V(Object[] argsArray) {
        Object symbols = LList.makeList(argsArray, 0);
        Apply apply = Scheme.apply;
        ModuleMethod moduleMethod = strings.string$Mnappend;
        Object result = LList.Empty;
        Object arg0 = symbols;
        while (arg0 != LList.Empty) {
            try {
                Pair arg02 = (Pair) arg0;
                Object cdr = arg02.getCdr();
                Object car = arg02.getCar();
                try {
                    result = Pair.make(misc.symbol$To$String((Symbol) car), result);
                    arg0 = cdr;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "symbol->string", 1, car);
                }
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "arg0", -2, arg0);
            }
        }
        Object apply2 = apply.apply2(moduleMethod, LList.reverseInPlace(result));
        try {
            return misc.string$To$Symbol((CharSequence) apply2);
        } catch (ClassCastException e3) {
            throw new WrongType(e3, "string->symbol", 1, apply2);
        }
    }

    static Object lambda2() {
        return null;
    }
}
