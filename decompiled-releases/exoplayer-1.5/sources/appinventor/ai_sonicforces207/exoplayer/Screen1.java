package appinventor.ai_sonicforces207.exoplayer;

import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import com.dreamers.exoplayercore.ExoplayerCore;
import com.dreamers.exoplayerui.ExoplayerUi;
import com.dreamers.relativeview.RelativeView;
import com.google.android.exoplayer2.ui.DefaultTimeBar;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.AppInventorCompatActivity;
import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.CheckBox;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.HorizontalArrangement;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.Notifier;
import com.google.appinventor.components.runtime.TextBox;
import com.google.appinventor.components.runtime.TinyDB;
import com.google.appinventor.components.runtime.VerticalArrangement;
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
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.DFloNum;
import gnu.math.IntNum;
import io.UpdateMyApp.UpdateMyApp;
import io.mohamed.ComponentTools.ComponentTools;
import kawa.lang.Promise;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.strings;
import kawa.standard.Scheme;
import kawa.standard.throw_name;

/* compiled from: Screen1.yail */
public class Screen1 extends Form implements Runnable {
    static final SimpleSymbol Lit0 = ((SimpleSymbol) new SimpleSymbol("Screen1").readResolve());
    static final SimpleSymbol Lit1 = ((SimpleSymbol) new SimpleSymbol("getMessage").readResolve());
    static final SimpleSymbol Lit10 = ((SimpleSymbol) new SimpleSymbol("ExoplayerCore1").readResolve());
    static final IntNum Lit100 = IntNum.make(-2);
    static final FString Lit101 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    static final FString Lit102 = new FString("com.google.appinventor.components.runtime.Label");
    static final SimpleSymbol Lit103 = ((SimpleSymbol) new SimpleSymbol("TitleLabel").readResolve());
    static final SimpleSymbol Lit104 = ((SimpleSymbol) new SimpleSymbol("FontSize").readResolve());
    static final IntNum Lit105 = IntNum.make(18);
    static final SimpleSymbol Lit106 = ((SimpleSymbol) new SimpleSymbol("TextColor").readResolve());
    static final IntNum Lit107;
    static final FString Lit108 = new FString("com.google.appinventor.components.runtime.Label");
    static final FString Lit109 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit11 = ((SimpleSymbol) new SimpleSymbol("SelectionFlagDefault").readResolve());
    static final IntNum Lit110 = IntNum.make(16777215);
    static final SimpleSymbol Lit111 = ((SimpleSymbol) new SimpleSymbol("Height").readResolve());
    static final FString Lit112 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final FString Lit113 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    static final FString Lit114 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    static final FString Lit115 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit116 = ((SimpleSymbol) new SimpleSymbol("HorizontalArrangement1").readResolve());
    static final FString Lit117 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final FString Lit118 = new FString("com.google.appinventor.components.runtime.Label");
    static final SimpleSymbol Lit119 = ((SimpleSymbol) new SimpleSymbol("Label1").readResolve());
    static final PairWithPosition Lit12;
    static final FString Lit120 = new FString("com.google.appinventor.components.runtime.Label");
    static final FString Lit121 = new FString("com.google.appinventor.components.runtime.TextBox");
    static final SimpleSymbol Lit122 = ((SimpleSymbol) new SimpleSymbol("videoURL").readResolve());
    static final FString Lit123 = new FString("com.google.appinventor.components.runtime.TextBox");
    static final FString Lit124 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit125 = ((SimpleSymbol) new SimpleSymbol("HorizontalArrangement2").readResolve());
    static final FString Lit126 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final FString Lit127 = new FString("com.google.appinventor.components.runtime.Label");
    static final SimpleSymbol Lit128 = ((SimpleSymbol) new SimpleSymbol("Label2").readResolve());
    static final FString Lit129 = new FString("com.google.appinventor.components.runtime.Label");
    static final PairWithPosition Lit13;
    static final FString Lit130 = new FString("com.google.appinventor.components.runtime.TextBox");
    static final FString Lit131 = new FString("com.google.appinventor.components.runtime.TextBox");
    static final FString Lit132 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit133 = ((SimpleSymbol) new SimpleSymbol("HorizontalArrangement4").readResolve());
    static final FString Lit134 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final FString Lit135 = new FString("com.google.appinventor.components.runtime.CheckBox");
    static final SimpleSymbol Lit136 = ((SimpleSymbol) new SimpleSymbol("errorShoworHide").readResolve());
    static final FString Lit137 = new FString("com.google.appinventor.components.runtime.CheckBox");
    static final FString Lit138 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit139 = ((SimpleSymbol) new SimpleSymbol("moreinfo").readResolve());
    static final PairWithPosition Lit14;
    static final SimpleSymbol Lit140 = ((SimpleSymbol) new SimpleSymbol("Shape").readResolve());
    static final IntNum Lit141 = IntNum.make(1);
    static final FString Lit142 = new FString("com.google.appinventor.components.runtime.Button");
    static final PairWithPosition Lit143;
    static final SimpleSymbol Lit144 = ((SimpleSymbol) new SimpleSymbol("moreinfo$Click").readResolve());
    static final SimpleSymbol Lit145 = ((SimpleSymbol) new SimpleSymbol("Click").readResolve());
    static final FString Lit146 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final SimpleSymbol Lit147 = ((SimpleSymbol) new SimpleSymbol("HorizontalArrangement3").readResolve());
    static final FString Lit148 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final FString Lit149 = new FString("com.google.appinventor.components.runtime.Button");
    static final PairWithPosition Lit15;
    static final FString Lit150 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit151 = ((SimpleSymbol) new SimpleSymbol("PlayWhenReady").readResolve());
    static final SimpleSymbol Lit152 = ((SimpleSymbol) new SimpleSymbol("ExoplayerUi1").readResolve());
    static final SimpleSymbol Lit153 = ((SimpleSymbol) new SimpleSymbol("ShowControls").readResolve());
    static final SimpleSymbol Lit154 = ((SimpleSymbol) new SimpleSymbol("CreatePlayer").readResolve());
    static final SimpleSymbol Lit155 = ((SimpleSymbol) new SimpleSymbol("CreateStyledPlayer").readResolve());
    static final SimpleSymbol Lit156 = ((SimpleSymbol) new SimpleSymbol("Player").readResolve());
    static final PairWithPosition Lit157;
    static final SimpleSymbol Lit158 = ((SimpleSymbol) new SimpleSymbol("KeepScreenOn").readResolve());
    static final SimpleSymbol Lit159 = ((SimpleSymbol) new SimpleSymbol("KeepContentOnPlayerReset").readResolve());
    static final PairWithPosition Lit16;
    static final PairWithPosition Lit160;
    static final SimpleSymbol Lit161 = ((SimpleSymbol) new SimpleSymbol("AddMedia").readResolve());
    static final PairWithPosition Lit162;
    static final PairWithPosition Lit163;
    static final SimpleSymbol Lit164 = ((SimpleSymbol) new SimpleSymbol("createExoPlayer$Click").readResolve());
    static final FString Lit165 = new FString("com.google.appinventor.components.runtime.Button");
    static final FString Lit166 = new FString("com.google.appinventor.components.runtime.Button");
    static final PairWithPosition Lit167;
    static final SimpleSymbol Lit168 = ((SimpleSymbol) new SimpleSymbol("createVideoViewPlayer$Click").readResolve());
    static final FString Lit169 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final PairWithPosition Lit17;
    static final IntNum Lit170 = IntNum.make(16777215);
    static final FString Lit171 = new FString("com.google.appinventor.components.runtime.HorizontalArrangement");
    static final FString Lit172 = new FString("com.dreamers.exoplayercore.ExoplayerCore");
    static final FString Lit173 = new FString("com.dreamers.exoplayercore.ExoplayerCore");
    static final SimpleSymbol Lit174 = ((SimpleSymbol) new SimpleSymbol("Play").readResolve());
    static final SimpleSymbol Lit175;
    static final SimpleSymbol Lit176 = ((SimpleSymbol) new SimpleSymbol("ExoplayerCore1$OnAppResume").readResolve());
    static final SimpleSymbol Lit177 = ((SimpleSymbol) new SimpleSymbol("OnAppResume").readResolve());
    static final SimpleSymbol Lit178 = ((SimpleSymbol) new SimpleSymbol("Checked").readResolve());
    static final PairWithPosition Lit179;
    static final PairWithPosition Lit18;
    static final SimpleSymbol Lit180 = ((SimpleSymbol) new SimpleSymbol("$error").readResolve());
    static final PairWithPosition Lit181;
    static final SimpleSymbol Lit182 = ((SimpleSymbol) new SimpleSymbol("LogError").readResolve());
    static final PairWithPosition Lit183;
    static final SimpleSymbol Lit184 = ((SimpleSymbol) new SimpleSymbol("ExoplayerCore1$OnError").readResolve());
    static final SimpleSymbol Lit185 = ((SimpleSymbol) new SimpleSymbol("OnError").readResolve());
    static final FString Lit186 = new FString("com.dreamers.exoplayerui.ExoplayerUi");
    static final SimpleSymbol Lit187 = ((SimpleSymbol) new SimpleSymbol("ActiveThumbSize").readResolve());
    static final IntNum Lit188 = IntNum.make(16);
    static final SimpleSymbol Lit189 = ((SimpleSymbol) new SimpleSymbol("AnimationEnabled").readResolve());
    static final PairWithPosition Lit19;
    static final SimpleSymbol Lit190 = ((SimpleSymbol) new SimpleSymbol("AutoShowController").readResolve());
    static final SimpleSymbol Lit191 = ((SimpleSymbol) new SimpleSymbol("BufferedColor").readResolve());
    static final IntNum Lit192;
    static final SimpleSymbol Lit193 = ((SimpleSymbol) new SimpleSymbol("ControllerTimeout").readResolve());
    static final IntNum Lit194 = IntNum.make(5000);
    static final SimpleSymbol Lit195 = ((SimpleSymbol) new SimpleSymbol("DefaultThumbnail").readResolve());
    static final SimpleSymbol Lit196 = ((SimpleSymbol) new SimpleSymbol("DisabledThumbSize").readResolve());
    static final IntNum Lit197 = IntNum.make(0);
    static final SimpleSymbol Lit198 = ((SimpleSymbol) new SimpleSymbol("FastForwardButtonVisible").readResolve());
    static final SimpleSymbol Lit199 = ((SimpleSymbol) new SimpleSymbol("FastForwardMs").readResolve());
    static final SimpleSymbol Lit2 = ((SimpleSymbol) new SimpleSymbol("*the-null-value*").readResolve());
    static final PairWithPosition Lit20;
    static final IntNum Lit200 = IntNum.make(15000);
    static final SimpleSymbol Lit201 = ((SimpleSymbol) new SimpleSymbol("FullscreenButtonVisible").readResolve());
    static final SimpleSymbol Lit202 = ((SimpleSymbol) new SimpleSymbol("HideOnTouch").readResolve());
    static final SimpleSymbol Lit203 = ((SimpleSymbol) new SimpleSymbol("IgnorePadding").readResolve());
    static final SimpleSymbol Lit204 = ((SimpleSymbol) new SimpleSymbol("NextButtonVisible").readResolve());
    static final SimpleSymbol Lit205 = ((SimpleSymbol) new SimpleSymbol("PreviousButtonVisible").readResolve());
    static final SimpleSymbol Lit206 = ((SimpleSymbol) new SimpleSymbol("ProgressColor").readResolve());
    static final IntNum Lit207;
    static final SimpleSymbol Lit208 = ((SimpleSymbol) new SimpleSymbol("RepeatMode").readResolve());
    static final SimpleSymbol Lit209 = ((SimpleSymbol) new SimpleSymbol("ResizeMode").readResolve());
    static final PairWithPosition Lit21;
    static final SimpleSymbol Lit210 = ((SimpleSymbol) new SimpleSymbol("RewindButtonVisible").readResolve());
    static final SimpleSymbol Lit211 = ((SimpleSymbol) new SimpleSymbol("RewindMs").readResolve());
    static final IntNum Lit212 = IntNum.make(5000);
    static final SimpleSymbol Lit213 = ((SimpleSymbol) new SimpleSymbol("ShowLoading").readResolve());
    static final SimpleSymbol Lit214 = ((SimpleSymbol) new SimpleSymbol("ShuffleButtonVisible").readResolve());
    static final SimpleSymbol Lit215 = ((SimpleSymbol) new SimpleSymbol("SubtitleBackgroundColor").readResolve());
    static final IntNum Lit216;
    static final SimpleSymbol Lit217 = ((SimpleSymbol) new SimpleSymbol("SubtitleBottomPadding").readResolve());
    static final IntNum Lit218 = IntNum.make(14);
    static final SimpleSymbol Lit219 = ((SimpleSymbol) new SimpleSymbol("SubtitleButtonVisible").readResolve());
    static final SimpleSymbol Lit22 = ((SimpleSymbol) new SimpleSymbol("p$decorate").readResolve());
    static final SimpleSymbol Lit220 = ((SimpleSymbol) new SimpleSymbol("SubtitleEdgeColor").readResolve());
    static final IntNum Lit221;
    static final SimpleSymbol Lit222 = ((SimpleSymbol) new SimpleSymbol("SubtitleEdgeType").readResolve());
    static final SimpleSymbol Lit223 = ((SimpleSymbol) new SimpleSymbol("SubtitleForegroundColor").readResolve());
    static final IntNum Lit224;
    static final SimpleSymbol Lit225 = ((SimpleSymbol) new SimpleSymbol("SubtitleRenderViewType").readResolve());
    static final SimpleSymbol Lit226 = ((SimpleSymbol) new SimpleSymbol("SubtitleTextSizeAbsolute").readResolve());
    static final SimpleSymbol Lit227 = ((SimpleSymbol) new SimpleSymbol("SubtitleTextSizeFraction").readResolve());
    static final DFloNum Lit228 = DFloNum.make(0.0533d);
    static final SimpleSymbol Lit229 = ((SimpleSymbol) new SimpleSymbol("SubtitleTextSizeType").readResolve());
    static final SimpleSymbol Lit23 = ((SimpleSymbol) new SimpleSymbol("ComponentTools1").readResolve());
    static final SimpleSymbol Lit230 = ((SimpleSymbol) new SimpleSymbol("SubtitleTypeface").readResolve());
    static final SimpleSymbol Lit231 = ((SimpleSymbol) new SimpleSymbol("SubtitleWindowColor").readResolve());
    static final IntNum Lit232 = IntNum.make(16777215);
    static final SimpleSymbol Lit233 = ((SimpleSymbol) new SimpleSymbol("SurfaceType").readResolve());
    static final SimpleSymbol Lit234 = ((SimpleSymbol) new SimpleSymbol("ThumbColor").readResolve());
    static final IntNum Lit235;
    static final SimpleSymbol Lit236 = ((SimpleSymbol) new SimpleSymbol("ThumbSize").readResolve());
    static final SimpleSymbol Lit237 = ((SimpleSymbol) new SimpleSymbol("TrackColor").readResolve());
    static final IntNum Lit238 = IntNum.make((int) DefaultTimeBar.DEFAULT_UNPLAYED_COLOR);
    static final SimpleSymbol Lit239 = ((SimpleSymbol) new SimpleSymbol("TrackHeight").readResolve());
    static final SimpleSymbol Lit24 = ((SimpleSymbol) new SimpleSymbol("SetPadding").readResolve());
    static final SimpleSymbol Lit240 = ((SimpleSymbol) new SimpleSymbol("UseArtwork").readResolve());
    static final SimpleSymbol Lit241 = ((SimpleSymbol) new SimpleSymbol("UseController").readResolve());
    static final SimpleSymbol Lit242 = ((SimpleSymbol) new SimpleSymbol("VideoSettingsButtonVisible").readResolve());
    static final FString Lit243 = new FString("com.dreamers.exoplayerui.ExoplayerUi");
    static final SimpleSymbol Lit244 = ((SimpleSymbol) new SimpleSymbol("$isFullScreen").readResolve());
    static final SimpleSymbol Lit245 = ((SimpleSymbol) new SimpleSymbol("HideSystemUI").readResolve());
    static final SimpleSymbol Lit246 = ((SimpleSymbol) new SimpleSymbol("ExoplayerUi1$OnSettingsWindowDismiss").readResolve());
    static final SimpleSymbol Lit247 = ((SimpleSymbol) new SimpleSymbol("OnSettingsWindowDismiss").readResolve());
    static final SimpleSymbol Lit248 = ((SimpleSymbol) new SimpleSymbol("ShowSystemUI").readResolve());
    static final SimpleSymbol Lit249 = ((SimpleSymbol) new SimpleSymbol("ExoplayerUi1$OnFullscreenChanged").readResolve());
    static final SimpleSymbol Lit25 = ((SimpleSymbol) new SimpleSymbol("TitleLayout").readResolve());
    static final SimpleSymbol Lit250 = ((SimpleSymbol) new SimpleSymbol("OnFullscreenChanged").readResolve());
    static final SimpleSymbol Lit251 = ((SimpleSymbol) new SimpleSymbol("$visible").readResolve());
    static final SimpleSymbol Lit252 = ((SimpleSymbol) new SimpleSymbol("ExoplayerUi1$OnVisibilityChanged").readResolve());
    static final SimpleSymbol Lit253 = ((SimpleSymbol) new SimpleSymbol("OnVisibilityChanged").readResolve());
    static final SimpleSymbol Lit254 = ((SimpleSymbol) new SimpleSymbol("ShowSelectionDialog").readResolve());
    static final SimpleSymbol Lit255 = ((SimpleSymbol) new SimpleSymbol("TrackTypeVideo").readResolve());
    static final PairWithPosition Lit256;
    static final SimpleSymbol Lit257 = ((SimpleSymbol) new SimpleSymbol("ExoplayerUi1$OnVideoSettingsButtonClick").readResolve());
    static final SimpleSymbol Lit258 = ((SimpleSymbol) new SimpleSymbol("OnVideoSettingsButtonClick").readResolve());
    static final FString Lit259 = new FString("com.dreamers.relativeview.RelativeView");
    static final IntNum Lit26 = IntNum.make(12);
    static final FString Lit260 = new FString("com.dreamers.relativeview.RelativeView");
    static final FString Lit261 = new FString("io.mohamed.ComponentTools.ComponentTools");
    static final FString Lit262 = new FString("io.mohamed.ComponentTools.ComponentTools");
    static final FString Lit263 = new FString("com.google.appinventor.components.runtime.Notifier");
    static final FString Lit264 = new FString("com.google.appinventor.components.runtime.Notifier");
    static final SimpleSymbol Lit265 = ((SimpleSymbol) new SimpleSymbol("$choice").readResolve());
    static final PairWithPosition Lit266;
    static final SimpleSymbol Lit267 = ((SimpleSymbol) new SimpleSymbol("Notifier1$AfterChoosing").readResolve());
    static final SimpleSymbol Lit268 = ((SimpleSymbol) new SimpleSymbol("AfterChoosing").readResolve());
    static final FString Lit269 = new FString("com.google.appinventor.components.runtime.Notifier");
    static final IntNum Lit27 = IntNum.make(24);
    static final SimpleSymbol Lit270 = ((SimpleSymbol) new SimpleSymbol("Notifier2").readResolve());
    static final FString Lit271 = new FString("com.google.appinventor.components.runtime.Notifier");
    static final FString Lit272 = new FString("com.google.appinventor.components.runtime.TinyDB");
    static final SimpleSymbol Lit273 = ((SimpleSymbol) new SimpleSymbol("Namespace").readResolve());
    static final FString Lit274 = new FString("com.google.appinventor.components.runtime.TinyDB");
    static final FString Lit275 = new FString("io.UpdateMyApp.UpdateMyApp");
    static final FString Lit276 = new FString("io.UpdateMyApp.UpdateMyApp");
    static final SimpleSymbol Lit277 = ((SimpleSymbol) new SimpleSymbol("get-simple-name").readResolve());
    static final SimpleSymbol Lit278 = ((SimpleSymbol) new SimpleSymbol("android-log-form").readResolve());
    static final SimpleSymbol Lit279 = ((SimpleSymbol) new SimpleSymbol("add-to-form-environment").readResolve());
    static final PairWithPosition Lit28;
    static final SimpleSymbol Lit280 = ((SimpleSymbol) new SimpleSymbol("lookup-in-form-environment").readResolve());
    static final SimpleSymbol Lit281 = ((SimpleSymbol) new SimpleSymbol("is-bound-in-form-environment").readResolve());
    static final SimpleSymbol Lit282 = ((SimpleSymbol) new SimpleSymbol("add-to-global-var-environment").readResolve());
    static final SimpleSymbol Lit283 = ((SimpleSymbol) new SimpleSymbol("add-to-events").readResolve());
    static final SimpleSymbol Lit284 = ((SimpleSymbol) new SimpleSymbol("add-to-components").readResolve());
    static final SimpleSymbol Lit285 = ((SimpleSymbol) new SimpleSymbol("add-to-global-vars").readResolve());
    static final SimpleSymbol Lit286 = ((SimpleSymbol) new SimpleSymbol("add-to-form-do-after-creation").readResolve());
    static final SimpleSymbol Lit287 = ((SimpleSymbol) new SimpleSymbol("send-error").readResolve());
    static final SimpleSymbol Lit288 = ((SimpleSymbol) new SimpleSymbol("dispatchEvent").readResolve());
    static final SimpleSymbol Lit289 = ((SimpleSymbol) new SimpleSymbol("dispatchGenericEvent").readResolve());
    static final SimpleSymbol Lit29 = ((SimpleSymbol) new SimpleSymbol("RelativeView1").readResolve());
    static final SimpleSymbol Lit290 = ((SimpleSymbol) new SimpleSymbol("lookup-handler").readResolve());
    static final SimpleSymbol Lit291;
    static final SimpleSymbol Lit292;
    static final SimpleSymbol Lit293;
    static final SimpleSymbol Lit294;
    static final SimpleSymbol Lit3 = ((SimpleSymbol) new SimpleSymbol("p$subtitles").readResolve());
    static final SimpleSymbol Lit30 = ((SimpleSymbol) new SimpleSymbol("Create").readResolve());
    static final SimpleSymbol Lit31 = ((SimpleSymbol) new SimpleSymbol("MainLayout").readResolve());
    static final PairWithPosition Lit32;
    static final SimpleSymbol Lit33 = ((SimpleSymbol) new SimpleSymbol("Add").readResolve());
    static final SimpleSymbol Lit34 = ((SimpleSymbol) new SimpleSymbol("PlayerView").readResolve());
    static final SimpleSymbol Lit35 = ((SimpleSymbol) new SimpleSymbol("Center").readResolve());
    static final PairWithPosition Lit36;
    static final SimpleSymbol Lit37 = ((SimpleSymbol) new SimpleSymbol("Top").readResolve());
    static final PairWithPosition Lit38;
    static final PairWithPosition Lit39;
    static final SimpleSymbol Lit4 = ((SimpleSymbol) new SimpleSymbol("subtitlesURL").readResolve());
    static final PairWithPosition Lit40;
    static final PairWithPosition Lit41;
    static final PairWithPosition Lit42;
    static final SimpleSymbol Lit43 = ((SimpleSymbol) new SimpleSymbol("AccentColor").readResolve());
    static final IntNum Lit44;
    static final SimpleSymbol Lit45;
    static final SimpleSymbol Lit46 = ((SimpleSymbol) new SimpleSymbol("ActionBar").readResolve());
    static final SimpleSymbol Lit47;
    static final SimpleSymbol Lit48 = ((SimpleSymbol) new SimpleSymbol("AlignHorizontal").readResolve());
    static final IntNum Lit49 = IntNum.make(3);
    static final SimpleSymbol Lit5 = ((SimpleSymbol) new SimpleSymbol("Text").readResolve());
    static final SimpleSymbol Lit50 = ((SimpleSymbol) new SimpleSymbol("AlignVertical").readResolve());
    static final IntNum Lit51 = IntNum.make(2);
    static final SimpleSymbol Lit52 = ((SimpleSymbol) new SimpleSymbol("AppName").readResolve());
    static final SimpleSymbol Lit53;
    static final SimpleSymbol Lit54 = ((SimpleSymbol) new SimpleSymbol("Icon").readResolve());
    static final SimpleSymbol Lit55 = ((SimpleSymbol) new SimpleSymbol("PrimaryColor").readResolve());
    static final IntNum Lit56;
    static final SimpleSymbol Lit57 = ((SimpleSymbol) new SimpleSymbol("PrimaryColorDark").readResolve());
    static final IntNum Lit58;
    static final SimpleSymbol Lit59 = ((SimpleSymbol) new SimpleSymbol("ScreenOrientation").readResolve());
    static final PairWithPosition Lit6;
    static final SimpleSymbol Lit60 = ((SimpleSymbol) new SimpleSymbol("ShowListsAsJson").readResolve());
    static final SimpleSymbol Lit61 = ((SimpleSymbol) new SimpleSymbol("Sizing").readResolve());
    static final SimpleSymbol Lit62 = ((SimpleSymbol) new SimpleSymbol("Title").readResolve());
    static final SimpleSymbol Lit63 = ((SimpleSymbol) new SimpleSymbol("TitleVisible").readResolve());
    static final SimpleSymbol Lit64 = ((SimpleSymbol) new SimpleSymbol("VersionCode").readResolve());
    static final IntNum Lit65 = IntNum.make(6);
    static final SimpleSymbol Lit66 = ((SimpleSymbol) new SimpleSymbol("VersionName").readResolve());
    static final SimpleSymbol Lit67 = ((SimpleSymbol) new SimpleSymbol("VerticalArrangement1").readResolve());
    static final SimpleSymbol Lit68 = ((SimpleSymbol) new SimpleSymbol("Visible").readResolve());
    static final SimpleSymbol Lit69 = ((SimpleSymbol) new SimpleSymbol(TinyDB.DEFAULT_NAMESPACE).readResolve());
    static final PairWithPosition Lit7;
    static final SimpleSymbol Lit70 = ((SimpleSymbol) new SimpleSymbol("GetValue").readResolve());
    static final PairWithPosition Lit71;
    static final PairWithPosition Lit72;
    static final SimpleSymbol Lit73 = ((SimpleSymbol) new SimpleSymbol("UpdateMyApp1").readResolve());
    static final SimpleSymbol Lit74 = ((SimpleSymbol) new SimpleSymbol("CheckForUpdate").readResolve());
    static final PairWithPosition Lit75;
    static final PairWithPosition Lit76;
    static final SimpleSymbol Lit77 = ((SimpleSymbol) new SimpleSymbol("StoreValue").readResolve());
    static final PairWithPosition Lit78;
    static final SimpleSymbol Lit79 = ((SimpleSymbol) new SimpleSymbol("PlatformVersion").readResolve());
    static final PairWithPosition Lit8;
    static final PairWithPosition Lit80;
    static final PairWithPosition Lit81;
    static final PairWithPosition Lit82;
    static final PairWithPosition Lit83;
    static final SimpleSymbol Lit84 = ((SimpleSymbol) new SimpleSymbol("Notifier1").readResolve());
    static final SimpleSymbol Lit85 = ((SimpleSymbol) new SimpleSymbol("ShowMessageDialog").readResolve());
    static final PairWithPosition Lit86;
    static final PairWithPosition Lit87;
    static final SimpleSymbol Lit88 = ((SimpleSymbol) new SimpleSymbol("createExoPlayer").readResolve());
    static final SimpleSymbol Lit89 = ((SimpleSymbol) new SimpleSymbol("createVideoViewPlayer").readResolve());
    static final PairWithPosition Lit9;
    static final SimpleSymbol Lit90 = ((SimpleSymbol) new SimpleSymbol("Screen1$Initialize").readResolve());
    static final SimpleSymbol Lit91 = ((SimpleSymbol) new SimpleSymbol("Initialize").readResolve());
    static final SimpleSymbol Lit92 = ((SimpleSymbol) new SimpleSymbol("ShowChooseDialog").readResolve());
    static final PairWithPosition Lit93;
    static final SimpleSymbol Lit94 = ((SimpleSymbol) new SimpleSymbol("Screen1$BackPressed").readResolve());
    static final SimpleSymbol Lit95 = ((SimpleSymbol) new SimpleSymbol("BackPressed").readResolve());
    static final FString Lit96 = new FString("com.google.appinventor.components.runtime.VerticalArrangement");
    static final SimpleSymbol Lit97 = ((SimpleSymbol) new SimpleSymbol("BackgroundColor").readResolve());
    static final IntNum Lit98 = IntNum.make(16777215);
    static final SimpleSymbol Lit99 = ((SimpleSymbol) new SimpleSymbol("Width").readResolve());
    public static Screen1 Screen1;
    static final ModuleMethod lambda$Fn1 = null;
    static final ModuleMethod lambda$Fn10 = null;
    static final ModuleMethod lambda$Fn11 = null;
    static final ModuleMethod lambda$Fn12 = null;
    static final ModuleMethod lambda$Fn13 = null;
    static final ModuleMethod lambda$Fn14 = null;
    static final ModuleMethod lambda$Fn15 = null;
    static final ModuleMethod lambda$Fn16 = null;
    static final ModuleMethod lambda$Fn17 = null;
    static final ModuleMethod lambda$Fn18 = null;
    static final ModuleMethod lambda$Fn19 = null;
    static final ModuleMethod lambda$Fn2 = null;
    static final ModuleMethod lambda$Fn20 = null;
    static final ModuleMethod lambda$Fn21 = null;
    static final ModuleMethod lambda$Fn22 = null;
    static final ModuleMethod lambda$Fn23 = null;
    static final ModuleMethod lambda$Fn24 = null;
    static final ModuleMethod lambda$Fn25 = null;
    static final ModuleMethod lambda$Fn26 = null;
    static final ModuleMethod lambda$Fn27 = null;
    static final ModuleMethod lambda$Fn28 = null;
    static final ModuleMethod lambda$Fn29 = null;
    static final ModuleMethod lambda$Fn3 = null;
    static final ModuleMethod lambda$Fn30 = null;
    static final ModuleMethod lambda$Fn31 = null;
    static final ModuleMethod lambda$Fn32 = null;
    static final ModuleMethod lambda$Fn33 = null;
    static final ModuleMethod lambda$Fn34 = null;
    static final ModuleMethod lambda$Fn35 = null;
    static final ModuleMethod lambda$Fn36 = null;
    static final ModuleMethod lambda$Fn37 = null;
    static final ModuleMethod lambda$Fn38 = null;
    static final ModuleMethod lambda$Fn39 = null;
    static final ModuleMethod lambda$Fn4 = null;
    static final ModuleMethod lambda$Fn40 = null;
    static final ModuleMethod lambda$Fn41 = null;
    static final ModuleMethod lambda$Fn42 = null;
    static final ModuleMethod lambda$Fn5 = null;
    static final ModuleMethod lambda$Fn6 = null;
    static final ModuleMethod lambda$Fn7 = null;
    static final ModuleMethod lambda$Fn8 = null;
    static final ModuleMethod lambda$Fn9 = null;
    public Boolean $Stdebug$Mnform$St;
    public final ModuleMethod $define;
    public ComponentTools ComponentTools1;
    public ExoplayerCore ExoplayerCore1;
    public final ModuleMethod ExoplayerCore1$OnAppResume;
    public final ModuleMethod ExoplayerCore1$OnError;
    public ExoplayerUi ExoplayerUi1;
    public final ModuleMethod ExoplayerUi1$OnFullscreenChanged;
    public final ModuleMethod ExoplayerUi1$OnSettingsWindowDismiss;
    public final ModuleMethod ExoplayerUi1$OnVideoSettingsButtonClick;
    public final ModuleMethod ExoplayerUi1$OnVisibilityChanged;
    public HorizontalArrangement HorizontalArrangement1;
    public HorizontalArrangement HorizontalArrangement2;
    public HorizontalArrangement HorizontalArrangement3;
    public HorizontalArrangement HorizontalArrangement4;
    public Label Label1;
    public Label Label2;
    public HorizontalArrangement MainLayout;
    public Notifier Notifier1;
    public final ModuleMethod Notifier1$AfterChoosing;
    public Notifier Notifier2;
    public HorizontalArrangement PlayerView;
    public RelativeView RelativeView1;
    public final ModuleMethod Screen1$BackPressed;
    public final ModuleMethod Screen1$Initialize;
    public TinyDB TinyDB1;
    public Label TitleLabel;
    public VerticalArrangement TitleLayout;
    public UpdateMyApp UpdateMyApp1;
    public VerticalArrangement VerticalArrangement1;
    public final ModuleMethod add$Mnto$Mncomponents;
    public final ModuleMethod add$Mnto$Mnevents;
    public final ModuleMethod add$Mnto$Mnform$Mndo$Mnafter$Mncreation;
    public final ModuleMethod add$Mnto$Mnform$Mnenvironment;
    public final ModuleMethod add$Mnto$Mnglobal$Mnvar$Mnenvironment;
    public final ModuleMethod add$Mnto$Mnglobal$Mnvars;
    public final ModuleMethod android$Mnlog$Mnform;
    public LList components$Mnto$Mncreate;
    public Button createExoPlayer;
    public final ModuleMethod createExoPlayer$Click;
    public Button createVideoViewPlayer;
    public final ModuleMethod createVideoViewPlayer$Click;
    public final ModuleMethod dispatchEvent;
    public final ModuleMethod dispatchGenericEvent;
    public CheckBox errorShoworHide;
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
    public Button moreinfo;
    public final ModuleMethod moreinfo$Click;
    public final ModuleMethod onCreate;
    public final ModuleMethod process$Mnexception;
    public final ModuleMethod send$Mnerror;
    public TextBox subtitlesURL;
    public TextBox videoURL;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol("key").readResolve();
        Lit294 = simpleSymbol;
        SimpleSymbol simpleSymbol2 = (SimpleSymbol) new SimpleSymbol("pair").readResolve();
        Lit293 = simpleSymbol2;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("component").readResolve();
        Lit292 = simpleSymbol3;
        SimpleSymbol simpleSymbol4 = (SimpleSymbol) new SimpleSymbol("list").readResolve();
        Lit291 = simpleSymbol4;
        SimpleSymbol simpleSymbol5 = (SimpleSymbol) new SimpleSymbol("any").readResolve();
        Lit175 = simpleSymbol5;
        Lit266 = PairWithPosition.make(simpleSymbol5, PairWithPosition.make(simpleSymbol5, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 1335395), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 1335390);
        SimpleSymbol simpleSymbol6 = (SimpleSymbol) new SimpleSymbol(PropertyTypeConstants.PROPERTY_TYPE_TEXT).readResolve();
        Lit53 = simpleSymbol6;
        SimpleSymbol simpleSymbol7 = (SimpleSymbol) new SimpleSymbol("number").readResolve();
        Lit45 = simpleSymbol7;
        SimpleSymbol simpleSymbol8 = (SimpleSymbol) new SimpleSymbol(PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN).readResolve();
        Lit47 = simpleSymbol8;
        Lit256 = PairWithPosition.make(simpleSymbol6, PairWithPosition.make(simpleSymbol7, PairWithPosition.make(simpleSymbol8, PairWithPosition.make(simpleSymbol8, PairWithPosition.make(simpleSymbol8, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 1265859), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 1265851), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 1265843), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 1265836), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 1265830);
        int[] iArr = new int[2];
        iArr[0] = -1;
        Lit235 = IntNum.make(iArr);
        int[] iArr2 = new int[2];
        iArr2[0] = -1;
        Lit224 = IntNum.make(iArr2);
        int[] iArr3 = new int[2];
        iArr3[0] = -1;
        Lit221 = IntNum.make(iArr3);
        int[] iArr4 = new int[2];
        iArr4[0] = -16777216;
        Lit216 = IntNum.make(iArr4);
        int[] iArr5 = new int[2];
        iArr5[0] = -1;
        Lit207 = IntNum.make(iArr5);
        int[] iArr6 = new int[2];
        iArr6[0] = -855638017;
        Lit192 = IntNum.make(iArr6);
        Lit183 = PairWithPosition.make(simpleSymbol6, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 876930);
        Lit181 = PairWithPosition.make(simpleSymbol6, PairWithPosition.make(simpleSymbol6, PairWithPosition.make(simpleSymbol6, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 876824), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 876819), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 876813);
        Lit179 = PairWithPosition.make(simpleSymbol5, PairWithPosition.make(simpleSymbol5, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 876657), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 876652);
        Lit167 = PairWithPosition.make(simpleSymbol6, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 770128);
        Lit163 = PairWithPosition.make(simpleSymbol6, PairWithPosition.make(simpleSymbol4, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 726110), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 726104);
        Lit162 = PairWithPosition.make(simpleSymbol6, PairWithPosition.make(simpleSymbol4, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 725959), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 725953);
        Lit160 = PairWithPosition.make(simpleSymbol5, PairWithPosition.make(simpleSymbol5, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 725754), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 725749);
        Lit157 = PairWithPosition.make(simpleSymbol3, PairWithPosition.make(simpleSymbol5, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 725503), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 725492);
        Lit143 = PairWithPosition.make(simpleSymbol6, PairWithPosition.make(simpleSymbol6, PairWithPosition.make(simpleSymbol6, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 643868), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 643863), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 643857);
        int[] iArr7 = new int[2];
        iArr7[0] = -1;
        Lit107 = IntNum.make(iArr7);
        Lit93 = PairWithPosition.make(simpleSymbol6, PairWithPosition.make(simpleSymbol6, PairWithPosition.make(simpleSymbol6, PairWithPosition.make(simpleSymbol6, PairWithPosition.make(simpleSymbol8, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 131379), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 131374), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 131369), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 131364), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 131358);
        Lit87 = PairWithPosition.make(simpleSymbol6, PairWithPosition.make(simpleSymbol5, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 124358), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 124352);
        Lit86 = PairWithPosition.make(simpleSymbol6, PairWithPosition.make(simpleSymbol6, PairWithPosition.make(simpleSymbol6, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 124246), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 124241), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 124235);
        Lit83 = PairWithPosition.make(simpleSymbol5, PairWithPosition.make(simpleSymbol5, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 123979), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 123974);
        Lit82 = PairWithPosition.make(simpleSymbol6, PairWithPosition.make(simpleSymbol5, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 123955), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 123949);
        Lit81 = PairWithPosition.make(simpleSymbol5, PairWithPosition.make(simpleSymbol5, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 123781), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 123776);
        Lit80 = PairWithPosition.make(simpleSymbol6, PairWithPosition.make(simpleSymbol6, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 123752), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 123746);
        Lit78 = PairWithPosition.make(simpleSymbol6, PairWithPosition.make(simpleSymbol5, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 123566), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 123560);
        Lit76 = PairWithPosition.make(simpleSymbol6, PairWithPosition.make(simpleSymbol6, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 123456), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 123450);
        Lit75 = PairWithPosition.make(simpleSymbol6, PairWithPosition.make(simpleSymbol6, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 123412), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 123406);
        Lit72 = PairWithPosition.make(simpleSymbol5, PairWithPosition.make(simpleSymbol5, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 123131), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 123126);
        Lit71 = PairWithPosition.make(simpleSymbol6, PairWithPosition.make(simpleSymbol5, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 123107), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 123101);
        int[] iArr8 = new int[2];
        iArr8[0] = -16357686;
        Lit58 = IntNum.make(iArr8);
        int[] iArr9 = new int[2];
        iArr9[0] = -16342306;
        Lit56 = IntNum.make(iArr9);
        int[] iArr10 = new int[2];
        iArr10[0] = -16726785;
        Lit44 = IntNum.make(iArr10);
        Lit42 = PairWithPosition.make(simpleSymbol3, PairWithPosition.make(simpleSymbol7, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 37440), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 37429);
        Lit41 = PairWithPosition.make(simpleSymbol3, PairWithPosition.make(simpleSymbol7, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 37292), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 37281);
        Lit40 = PairWithPosition.make(simpleSymbol3, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 37138);
        Lit39 = PairWithPosition.make(simpleSymbol3, PairWithPosition.make(simpleSymbol7, PairWithPosition.make(simpleSymbol7, PairWithPosition.make(simpleSymbol7, PairWithPosition.make(simpleSymbol7, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 37035), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 37028), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 37021), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 37014), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 37003);
        Lit38 = PairWithPosition.make(simpleSymbol3, PairWithPosition.make(simpleSymbol7, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 37440), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 37429);
        Lit36 = PairWithPosition.make(simpleSymbol3, PairWithPosition.make(simpleSymbol7, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 37292), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 37281);
        Lit32 = PairWithPosition.make(simpleSymbol3, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 37138);
        Lit28 = PairWithPosition.make(simpleSymbol3, PairWithPosition.make(simpleSymbol7, PairWithPosition.make(simpleSymbol7, PairWithPosition.make(simpleSymbol7, PairWithPosition.make(simpleSymbol7, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 37035), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 37028), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 37021), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 37014), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 37003);
        Lit21 = PairWithPosition.make(simpleSymbol5, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 33587);
        Lit20 = PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol2, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 33556), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 33551), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 33546), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 33541), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 33535);
        Lit19 = PairWithPosition.make(simpleSymbol, PairWithPosition.make(simpleSymbol5, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 33511), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 33506);
        Lit18 = PairWithPosition.make(simpleSymbol, PairWithPosition.make(simpleSymbol5, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 33349), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 33344);
        Lit17 = PairWithPosition.make(simpleSymbol, PairWithPosition.make(simpleSymbol5, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 33242), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 33237);
        Lit16 = PairWithPosition.make(simpleSymbol, PairWithPosition.make(simpleSymbol5, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 33133), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 33128);
        Lit15 = PairWithPosition.make(simpleSymbol, PairWithPosition.make(simpleSymbol5, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 33019), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 33014);
        Lit14 = PairWithPosition.make(simpleSymbol5, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 33587);
        Lit13 = PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol2, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 33556), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 33551), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 33546), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 33541), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 33535);
        Lit12 = PairWithPosition.make(simpleSymbol, PairWithPosition.make(simpleSymbol5, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 33511), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 33506);
        Lit9 = PairWithPosition.make(simpleSymbol, PairWithPosition.make(simpleSymbol5, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 33349), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 33344);
        Lit8 = PairWithPosition.make(simpleSymbol, PairWithPosition.make(simpleSymbol5, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 33242), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 33237);
        Lit7 = PairWithPosition.make(simpleSymbol, PairWithPosition.make(simpleSymbol5, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 33133), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 33128);
        Lit6 = PairWithPosition.make(simpleSymbol, PairWithPosition.make(simpleSymbol5, LList.Empty, "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 33019), "/tmp/1734748272951_6574720033052226560-0/youngandroidproject/../src/appinventor/ai_sonicforces207/exoplayer/Screen1.yail", 33014);
    }

    public Screen1() {
        ModuleInfo.register(this);
        frame frame2 = new frame();
        frame2.$main = this;
        this.get$Mnsimple$Mnname = new ModuleMethod(frame2, 1, Lit277, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.onCreate = new ModuleMethod(frame2, 2, "onCreate", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.android$Mnlog$Mnform = new ModuleMethod(frame2, 3, Lit278, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.add$Mnto$Mnform$Mnenvironment = new ModuleMethod(frame2, 4, Lit279, 8194);
        this.lookup$Mnin$Mnform$Mnenvironment = new ModuleMethod(frame2, 5, Lit280, 8193);
        this.is$Mnbound$Mnin$Mnform$Mnenvironment = new ModuleMethod(frame2, 7, Lit281, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.add$Mnto$Mnglobal$Mnvar$Mnenvironment = new ModuleMethod(frame2, 8, Lit282, 8194);
        this.add$Mnto$Mnevents = new ModuleMethod(frame2, 9, Lit283, 8194);
        this.add$Mnto$Mncomponents = new ModuleMethod(frame2, 10, Lit284, 16388);
        this.add$Mnto$Mnglobal$Mnvars = new ModuleMethod(frame2, 11, Lit285, 8194);
        this.add$Mnto$Mnform$Mndo$Mnafter$Mncreation = new ModuleMethod(frame2, 12, Lit286, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.send$Mnerror = new ModuleMethod(frame2, 13, Lit287, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.process$Mnexception = new ModuleMethod(frame2, 14, "process-exception", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.dispatchEvent = new ModuleMethod(frame2, 15, Lit288, 16388);
        this.dispatchGenericEvent = new ModuleMethod(frame2, 16, Lit289, 16388);
        this.lookup$Mnhandler = new ModuleMethod(frame2, 17, Lit290, 8194);
        ModuleMethod moduleMethod = new ModuleMethod(frame2, 18, (Object) null, 0);
        moduleMethod.setProperty("source-location", "/tmp/runtime1923688917642074873.scm:634");
        lambda$Fn1 = moduleMethod;
        this.$define = new ModuleMethod(frame2, 19, "$define", 0);
        lambda$Fn2 = new ModuleMethod(frame2, 20, (Object) null, 0);
        lambda$Fn4 = new ModuleMethod(frame2, 21, (Object) null, 0);
        lambda$Fn3 = new ModuleMethod(frame2, 22, (Object) null, 0);
        lambda$Fn5 = new ModuleMethod(frame2, 23, (Object) null, 0);
        lambda$Fn7 = new ModuleMethod(frame2, 24, (Object) null, 0);
        lambda$Fn6 = new ModuleMethod(frame2, 25, (Object) null, 0);
        lambda$Fn8 = new ModuleMethod(frame2, 26, (Object) null, 0);
        this.Screen1$Initialize = new ModuleMethod(frame2, 27, Lit90, 0);
        this.Screen1$BackPressed = new ModuleMethod(frame2, 28, Lit94, 0);
        lambda$Fn9 = new ModuleMethod(frame2, 29, (Object) null, 0);
        lambda$Fn10 = new ModuleMethod(frame2, 30, (Object) null, 0);
        lambda$Fn11 = new ModuleMethod(frame2, 31, (Object) null, 0);
        lambda$Fn12 = new ModuleMethod(frame2, 32, (Object) null, 0);
        lambda$Fn13 = new ModuleMethod(frame2, 33, (Object) null, 0);
        lambda$Fn14 = new ModuleMethod(frame2, 34, (Object) null, 0);
        lambda$Fn15 = new ModuleMethod(frame2, 35, (Object) null, 0);
        lambda$Fn16 = new ModuleMethod(frame2, 36, (Object) null, 0);
        lambda$Fn17 = new ModuleMethod(frame2, 37, (Object) null, 0);
        lambda$Fn18 = new ModuleMethod(frame2, 38, (Object) null, 0);
        lambda$Fn19 = new ModuleMethod(frame2, 39, (Object) null, 0);
        lambda$Fn20 = new ModuleMethod(frame2, 40, (Object) null, 0);
        lambda$Fn21 = new ModuleMethod(frame2, 41, (Object) null, 0);
        lambda$Fn22 = new ModuleMethod(frame2, 42, (Object) null, 0);
        lambda$Fn23 = new ModuleMethod(frame2, 43, (Object) null, 0);
        lambda$Fn24 = new ModuleMethod(frame2, 44, (Object) null, 0);
        lambda$Fn25 = new ModuleMethod(frame2, 45, (Object) null, 0);
        lambda$Fn26 = new ModuleMethod(frame2, 46, (Object) null, 0);
        lambda$Fn27 = new ModuleMethod(frame2, 47, (Object) null, 0);
        lambda$Fn28 = new ModuleMethod(frame2, 48, (Object) null, 0);
        lambda$Fn29 = new ModuleMethod(frame2, 49, (Object) null, 0);
        lambda$Fn30 = new ModuleMethod(frame2, 50, (Object) null, 0);
        this.moreinfo$Click = new ModuleMethod(frame2, 51, Lit144, 0);
        lambda$Fn31 = new ModuleMethod(frame2, 52, (Object) null, 0);
        lambda$Fn32 = new ModuleMethod(frame2, 53, (Object) null, 0);
        lambda$Fn33 = new ModuleMethod(frame2, 54, (Object) null, 0);
        lambda$Fn34 = new ModuleMethod(frame2, 55, (Object) null, 0);
        this.createExoPlayer$Click = new ModuleMethod(frame2, 56, Lit164, 0);
        lambda$Fn35 = new ModuleMethod(frame2, 57, (Object) null, 0);
        lambda$Fn36 = new ModuleMethod(frame2, 58, (Object) null, 0);
        this.createVideoViewPlayer$Click = new ModuleMethod(frame2, 59, Lit168, 0);
        lambda$Fn37 = new ModuleMethod(frame2, 60, (Object) null, 0);
        lambda$Fn38 = new ModuleMethod(frame2, 61, (Object) null, 0);
        this.ExoplayerCore1$OnAppResume = new ModuleMethod(frame2, 62, Lit176, 0);
        this.ExoplayerCore1$OnError = new ModuleMethod(frame2, 63, Lit184, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        lambda$Fn39 = new ModuleMethod(frame2, 64, (Object) null, 0);
        lambda$Fn40 = new ModuleMethod(frame2, 65, (Object) null, 0);
        this.ExoplayerUi1$OnSettingsWindowDismiss = new ModuleMethod(frame2, 66, Lit246, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.ExoplayerUi1$OnFullscreenChanged = new ModuleMethod(frame2, 67, Lit249, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.ExoplayerUi1$OnVisibilityChanged = new ModuleMethod(frame2, 68, Lit252, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.ExoplayerUi1$OnVideoSettingsButtonClick = new ModuleMethod(frame2, 69, Lit257, 0);
        this.Notifier1$AfterChoosing = new ModuleMethod(frame2, 70, Lit267, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        lambda$Fn41 = new ModuleMethod(frame2, 71, (Object) null, 0);
        lambda$Fn42 = new ModuleMethod(frame2, 72, (Object) null, 0);
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
        runtime.$instance.run();
        this.$Stdebug$Mnform$St = Boolean.FALSE;
        SimpleSymbol simpleSymbol = Lit0;
        this.form$Mnenvironment = Environment.make(misc.symbol$To$String(simpleSymbol));
        FString stringAppend = strings.stringAppend(misc.symbol$To$String(simpleSymbol), "-global-vars");
        this.global$Mnvar$Mnenvironment = Environment.make(stringAppend == null ? null : stringAppend.toString());
        Screen1 = null;
        this.form$Mnname$Mnsymbol = simpleSymbol;
        this.events$Mnto$Mnregister = LList.Empty;
        this.components$Mnto$Mncreate = LList.Empty;
        this.global$Mnvars$Mnto$Mncreate = LList.Empty;
        this.form$Mndo$Mnafter$Mncreation = LList.Empty;
        runtime.$instance.run();
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(runtime.addGlobalVarToCurrentFormEnvironment(Lit3, lambda$Fn2), $result);
        } else {
            addToGlobalVars(Lit3, lambda$Fn3);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(runtime.addGlobalVarToCurrentFormEnvironment(Lit22, lambda$Fn5), $result);
        } else {
            addToGlobalVars(Lit22, lambda$Fn6);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            SimpleSymbol simpleSymbol2 = Lit43;
            IntNum intNum = Lit44;
            SimpleSymbol simpleSymbol3 = Lit45;
            runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, intNum, simpleSymbol3);
            SimpleSymbol simpleSymbol4 = Lit46;
            Boolean bool = Boolean.TRUE;
            SimpleSymbol simpleSymbol5 = Lit47;
            runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol4, bool, simpleSymbol5);
            runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit48, Lit49, simpleSymbol3);
            runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit50, Lit51, simpleSymbol3);
            SimpleSymbol simpleSymbol6 = Lit52;
            SimpleSymbol simpleSymbol7 = Lit53;
            runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol6, "ExoPlayer Creator", simpleSymbol7);
            runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit54, "exoplayer.png", simpleSymbol7);
            runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit55, Lit56, simpleSymbol3);
            runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit57, Lit58, simpleSymbol3);
            runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit59, "user", simpleSymbol7);
            runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit60, Boolean.TRUE, simpleSymbol5);
            runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit61, "Responsive", simpleSymbol7);
            runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit62, "Exoplayer", simpleSymbol7);
            runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit63, Boolean.FALSE, simpleSymbol5);
            runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit64, Lit65, simpleSymbol3);
            Values.writeValues(runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit66, "1.5", simpleSymbol7), $result);
        } else {
            addToFormDoAfterCreation(new Promise(lambda$Fn8));
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            runtime.addToCurrentFormEnvironment(Lit90, this.Screen1$Initialize);
        } else {
            addToFormEnvironment(Lit90, this.Screen1$Initialize);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) runtime.$Stthis$Mnform$St, "Screen1", "Initialize");
        } else {
            addToEvents(simpleSymbol, Lit91);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            runtime.addToCurrentFormEnvironment(Lit94, this.Screen1$BackPressed);
        } else {
            addToFormEnvironment(Lit94, this.Screen1$BackPressed);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) runtime.$Stthis$Mnform$St, "Screen1", "BackPressed");
        } else {
            addToEvents(simpleSymbol, Lit95);
        }
        this.TitleLayout = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(runtime.addComponentWithinRepl(simpleSymbol, Lit96, Lit25, lambda$Fn9), $result);
        } else {
            addToComponents(simpleSymbol, Lit101, Lit25, lambda$Fn10);
        }
        this.TitleLabel = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(runtime.addComponentWithinRepl(Lit25, Lit102, Lit103, lambda$Fn11), $result);
        } else {
            addToComponents(Lit25, Lit108, Lit103, lambda$Fn12);
        }
        this.MainLayout = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(runtime.addComponentWithinRepl(simpleSymbol, Lit109, Lit31, lambda$Fn13), $result);
        } else {
            addToComponents(simpleSymbol, Lit112, Lit31, lambda$Fn14);
        }
        this.VerticalArrangement1 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(runtime.addComponentWithinRepl(Lit31, Lit113, Lit67, lambda$Fn15), $result);
        } else {
            addToComponents(Lit31, Lit114, Lit67, lambda$Fn16);
        }
        this.HorizontalArrangement1 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(runtime.addComponentWithinRepl(Lit67, Lit115, Lit116, lambda$Fn17), $result);
        } else {
            addToComponents(Lit67, Lit117, Lit116, lambda$Fn18);
        }
        this.Label1 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(runtime.addComponentWithinRepl(Lit116, Lit118, Lit119, lambda$Fn19), $result);
        } else {
            addToComponents(Lit116, Lit120, Lit119, lambda$Fn20);
        }
        this.videoURL = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(runtime.addComponentWithinRepl(Lit116, Lit121, Lit122, Boolean.FALSE), $result);
        } else {
            addToComponents(Lit116, Lit123, Lit122, Boolean.FALSE);
        }
        this.HorizontalArrangement2 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(runtime.addComponentWithinRepl(Lit67, Lit124, Lit125, lambda$Fn21), $result);
        } else {
            addToComponents(Lit67, Lit126, Lit125, lambda$Fn22);
        }
        this.Label2 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(runtime.addComponentWithinRepl(Lit125, Lit127, Lit128, lambda$Fn23), $result);
        } else {
            addToComponents(Lit125, Lit129, Lit128, lambda$Fn24);
        }
        this.subtitlesURL = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(runtime.addComponentWithinRepl(Lit125, Lit130, Lit4, Boolean.FALSE), $result);
        } else {
            addToComponents(Lit125, Lit131, Lit4, Boolean.FALSE);
        }
        this.HorizontalArrangement4 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(runtime.addComponentWithinRepl(Lit67, Lit132, Lit133, lambda$Fn25), $result);
        } else {
            addToComponents(Lit67, Lit134, Lit133, lambda$Fn26);
        }
        this.errorShoworHide = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(runtime.addComponentWithinRepl(Lit133, Lit135, Lit136, lambda$Fn27), $result);
        } else {
            addToComponents(Lit133, Lit137, Lit136, lambda$Fn28);
        }
        this.moreinfo = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(runtime.addComponentWithinRepl(Lit133, Lit138, Lit139, lambda$Fn29), $result);
        } else {
            addToComponents(Lit133, Lit142, Lit139, lambda$Fn30);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            runtime.addToCurrentFormEnvironment(Lit144, this.moreinfo$Click);
        } else {
            addToFormEnvironment(Lit144, this.moreinfo$Click);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) runtime.$Stthis$Mnform$St, "moreinfo", "Click");
        } else {
            addToEvents(Lit139, Lit145);
        }
        this.HorizontalArrangement3 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(runtime.addComponentWithinRepl(Lit67, Lit146, Lit147, lambda$Fn31), $result);
        } else {
            addToComponents(Lit67, Lit148, Lit147, lambda$Fn32);
        }
        this.createExoPlayer = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(runtime.addComponentWithinRepl(Lit147, Lit149, Lit88, lambda$Fn33), $result);
        } else {
            addToComponents(Lit147, Lit150, Lit88, lambda$Fn34);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            runtime.addToCurrentFormEnvironment(Lit164, this.createExoPlayer$Click);
        } else {
            addToFormEnvironment(Lit164, this.createExoPlayer$Click);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) runtime.$Stthis$Mnform$St, "createExoPlayer", "Click");
        } else {
            addToEvents(Lit88, Lit145);
        }
        this.createVideoViewPlayer = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(runtime.addComponentWithinRepl(Lit147, Lit165, Lit89, lambda$Fn35), $result);
        } else {
            addToComponents(Lit147, Lit166, Lit89, lambda$Fn36);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            runtime.addToCurrentFormEnvironment(Lit168, this.createVideoViewPlayer$Click);
        } else {
            addToFormEnvironment(Lit168, this.createVideoViewPlayer$Click);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) runtime.$Stthis$Mnform$St, "createVideoViewPlayer", "Click");
        } else {
            addToEvents(Lit89, Lit145);
        }
        this.PlayerView = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(runtime.addComponentWithinRepl(simpleSymbol, Lit169, Lit34, lambda$Fn37), $result);
        } else {
            addToComponents(simpleSymbol, Lit171, Lit34, lambda$Fn38);
        }
        this.ExoplayerCore1 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(runtime.addComponentWithinRepl(simpleSymbol, Lit172, Lit10, Boolean.FALSE), $result);
        } else {
            addToComponents(simpleSymbol, Lit173, Lit10, Boolean.FALSE);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            runtime.addToCurrentFormEnvironment(Lit176, this.ExoplayerCore1$OnAppResume);
        } else {
            addToFormEnvironment(Lit176, this.ExoplayerCore1$OnAppResume);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) runtime.$Stthis$Mnform$St, "ExoplayerCore1", "OnAppResume");
        } else {
            addToEvents(Lit10, Lit177);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            runtime.addToCurrentFormEnvironment(Lit184, this.ExoplayerCore1$OnError);
        } else {
            addToFormEnvironment(Lit184, this.ExoplayerCore1$OnError);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) runtime.$Stthis$Mnform$St, "ExoplayerCore1", "OnError");
        } else {
            addToEvents(Lit10, Lit185);
        }
        this.ExoplayerUi1 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(runtime.addComponentWithinRepl(simpleSymbol, Lit186, Lit152, lambda$Fn39), $result);
        } else {
            addToComponents(simpleSymbol, Lit243, Lit152, lambda$Fn40);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            runtime.addToCurrentFormEnvironment(Lit246, this.ExoplayerUi1$OnSettingsWindowDismiss);
        } else {
            addToFormEnvironment(Lit246, this.ExoplayerUi1$OnSettingsWindowDismiss);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) runtime.$Stthis$Mnform$St, "ExoplayerUi1", "OnSettingsWindowDismiss");
        } else {
            addToEvents(Lit152, Lit247);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            runtime.addToCurrentFormEnvironment(Lit249, this.ExoplayerUi1$OnFullscreenChanged);
        } else {
            addToFormEnvironment(Lit249, this.ExoplayerUi1$OnFullscreenChanged);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) runtime.$Stthis$Mnform$St, "ExoplayerUi1", "OnFullscreenChanged");
        } else {
            addToEvents(Lit152, Lit250);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            runtime.addToCurrentFormEnvironment(Lit252, this.ExoplayerUi1$OnVisibilityChanged);
        } else {
            addToFormEnvironment(Lit252, this.ExoplayerUi1$OnVisibilityChanged);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) runtime.$Stthis$Mnform$St, "ExoplayerUi1", "OnVisibilityChanged");
        } else {
            addToEvents(Lit152, Lit253);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            runtime.addToCurrentFormEnvironment(Lit257, this.ExoplayerUi1$OnVideoSettingsButtonClick);
        } else {
            addToFormEnvironment(Lit257, this.ExoplayerUi1$OnVideoSettingsButtonClick);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) runtime.$Stthis$Mnform$St, "ExoplayerUi1", "OnVideoSettingsButtonClick");
        } else {
            addToEvents(Lit152, Lit258);
        }
        this.RelativeView1 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(runtime.addComponentWithinRepl(simpleSymbol, Lit259, Lit29, Boolean.FALSE), $result);
        } else {
            addToComponents(simpleSymbol, Lit260, Lit29, Boolean.FALSE);
        }
        this.ComponentTools1 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(runtime.addComponentWithinRepl(simpleSymbol, Lit261, Lit23, Boolean.FALSE), $result);
        } else {
            addToComponents(simpleSymbol, Lit262, Lit23, Boolean.FALSE);
        }
        this.Notifier1 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(runtime.addComponentWithinRepl(simpleSymbol, Lit263, Lit84, Boolean.FALSE), $result);
        } else {
            addToComponents(simpleSymbol, Lit264, Lit84, Boolean.FALSE);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            runtime.addToCurrentFormEnvironment(Lit267, this.Notifier1$AfterChoosing);
        } else {
            addToFormEnvironment(Lit267, this.Notifier1$AfterChoosing);
        }
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) runtime.$Stthis$Mnform$St, "Notifier1", "AfterChoosing");
        } else {
            addToEvents(Lit84, Lit268);
        }
        this.Notifier2 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(runtime.addComponentWithinRepl(simpleSymbol, Lit269, Lit270, Boolean.FALSE), $result);
        } else {
            addToComponents(simpleSymbol, Lit271, Lit270, Boolean.FALSE);
        }
        this.TinyDB1 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(runtime.addComponentWithinRepl(simpleSymbol, Lit272, Lit69, lambda$Fn41), $result);
        } else {
            addToComponents(simpleSymbol, Lit274, Lit69, lambda$Fn42);
        }
        this.UpdateMyApp1 = null;
        if (runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(runtime.addComponentWithinRepl(simpleSymbol, Lit275, Lit73, Boolean.FALSE), $result);
        } else {
            addToComponents(simpleSymbol, Lit276, Lit73, Boolean.FALSE);
        }
        runtime.initRuntime();
    }

    /* compiled from: Screen1.yail */
    public class frame extends ModuleBody {
        Screen1 $main;

        public Object apply0(ModuleMethod moduleMethod) {
            switch (moduleMethod.selector) {
                case 18:
                    return Screen1.lambda2();
                case 19:
                    this.$main.$define();
                    return Values.empty;
                case 20:
                    return Screen1.lambda3();
                case 21:
                    return Screen1.lambda5();
                case 22:
                    return Screen1.lambda4();
                case 23:
                    return Screen1.lambda6();
                case 24:
                    return Screen1.lambda8();
                case 25:
                    return Screen1.lambda7();
                case 26:
                    return Screen1.lambda9();
                case 27:
                    return this.$main.Screen1$Initialize();
                case 28:
                    return this.$main.Screen1$BackPressed();
                case 29:
                    return Screen1.lambda10();
                case 30:
                    return Screen1.lambda11();
                case 31:
                    return Screen1.lambda12();
                case 32:
                    return Screen1.lambda13();
                case 33:
                    return Screen1.lambda14();
                case 34:
                    return Screen1.lambda15();
                case 35:
                    return Screen1.lambda16();
                case 36:
                    return Screen1.lambda17();
                case 37:
                    return Screen1.lambda18();
                case 38:
                    return Screen1.lambda19();
                case 39:
                    return Screen1.lambda20();
                case 40:
                    return Screen1.lambda21();
                case 41:
                    return Screen1.lambda22();
                case 42:
                    return Screen1.lambda23();
                case 43:
                    return Screen1.lambda24();
                case 44:
                    return Screen1.lambda25();
                case 45:
                    return Screen1.lambda26();
                case 46:
                    return Screen1.lambda27();
                case 47:
                    return Screen1.lambda28();
                case 48:
                    return Screen1.lambda29();
                case 49:
                    return Screen1.lambda30();
                case 50:
                    return Screen1.lambda31();
                case 51:
                    return this.$main.moreinfo$Click();
                case 52:
                    return Screen1.lambda32();
                case 53:
                    return Screen1.lambda33();
                case 54:
                    return Screen1.lambda34();
                case 55:
                    return Screen1.lambda35();
                case 56:
                    return this.$main.createExoPlayer$Click();
                case 57:
                    return Screen1.lambda36();
                case 58:
                    return Screen1.lambda37();
                case 59:
                    return this.$main.createVideoViewPlayer$Click();
                case 60:
                    return Screen1.lambda38();
                case 61:
                    return Screen1.lambda39();
                case 62:
                    return this.$main.ExoplayerCore1$OnAppResume();
                case 64:
                    return Screen1.lambda40();
                case 65:
                    return Screen1.lambda41();
                case 69:
                    return this.$main.ExoplayerUi1$OnVideoSettingsButtonClick();
                case 71:
                    return Screen1.lambda42();
                case 72:
                    return Screen1.lambda43();
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
                    callContext.proc = moduleMethod;
                    callContext.pc = 0;
                    return 0;
                case 22:
                case 23:
                case 24:
                    callContext.proc = moduleMethod;
                    callContext.pc = 0;
                    return 0;
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                case 60:
                case 61:
                case 62:
                case 64:
                case 65:
                case 69:
                case 71:
                case 72:
                    callContext.proc = moduleMethod;
                    callContext.pc = 0;
                    return 0;
                default:
                    return super.match0(moduleMethod, callContext);
            }
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 1:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 2:
                    if (!(obj instanceof Screen1)) {
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
                    if (!(obj instanceof Screen1)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.pc = 1;
                    return 0;
                case 63:
                case 66:
                case 67:
                case 68:
                case 70:
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
                    if (!(obj instanceof Screen1)) {
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
                    if (!(obj instanceof Screen1)) {
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
                case 63:
                    return this.$main.ExoplayerCore1$OnError(obj);
                case 66:
                    return this.$main.ExoplayerUi1$OnSettingsWindowDismiss(obj);
                case 67:
                    return this.$main.ExoplayerUi1$OnFullscreenChanged(obj);
                case 68:
                    return this.$main.ExoplayerUi1$OnVisibilityChanged(obj);
                case 70:
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
                    Screen1 screen1 = this.$main;
                    try {
                        Component component = (Component) obj;
                        try {
                            String str = (String) obj2;
                            try {
                                if (obj3 == Boolean.FALSE) {
                                    z = false;
                                }
                                try {
                                    screen1.dispatchGenericEvent(component, str, z, (Object[]) obj4);
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
    }

    static Object lambda3() {
        ModuleMethod moduleMethod = runtime.make$Mnyail$Mnlist;
        ModuleMethod moduleMethod2 = runtime.make$Mnyail$Mndictionary;
        Pair list1 = LList.list1(runtime.callYailPrimitive(runtime.make$Mndictionary$Mnpair, LList.list2("path", runtime.get$Mnproperty.apply2(Lit4, Lit5)), Lit6, "make a pair"));
        LList.chain4(list1, runtime.callYailPrimitive(runtime.make$Mndictionary$Mnpair, LList.list2("mime_type", "text/vtt"), Lit7, "make a pair"), runtime.callYailPrimitive(runtime.make$Mndictionary$Mnpair, LList.list2("label", "English"), Lit8, "make a pair"), runtime.callYailPrimitive(runtime.make$Mndictionary$Mnpair, LList.list2("language", "en"), Lit9, "make a pair"), runtime.callYailPrimitive(runtime.make$Mndictionary$Mnpair, LList.list2("selection_flags", runtime.get$Mnproperty.apply2(Lit10, Lit11)), Lit12, "make a pair"));
        return runtime.callYailPrimitive(moduleMethod, LList.list1(runtime.callYailPrimitive(moduleMethod2, list1, Lit13, "make a dictionary")), Lit14, "make a list");
    }

    static Procedure lambda4() {
        return lambda$Fn4;
    }

    static Object lambda5() {
        ModuleMethod moduleMethod = runtime.make$Mnyail$Mnlist;
        ModuleMethod moduleMethod2 = runtime.make$Mnyail$Mndictionary;
        Pair list1 = LList.list1(runtime.callYailPrimitive(runtime.make$Mndictionary$Mnpair, LList.list2("path", runtime.get$Mnproperty.apply2(Lit4, Lit5)), Lit15, "make a pair"));
        LList.chain4(list1, runtime.callYailPrimitive(runtime.make$Mndictionary$Mnpair, LList.list2("mime_type", "text/vtt"), Lit16, "make a pair"), runtime.callYailPrimitive(runtime.make$Mndictionary$Mnpair, LList.list2("label", "English"), Lit17, "make a pair"), runtime.callYailPrimitive(runtime.make$Mndictionary$Mnpair, LList.list2("language", "en"), Lit18, "make a pair"), runtime.callYailPrimitive(runtime.make$Mndictionary$Mnpair, LList.list2("selection_flags", runtime.get$Mnproperty.apply2(Lit10, Lit11)), Lit19, "make a pair"));
        return runtime.callYailPrimitive(moduleMethod, LList.list1(runtime.callYailPrimitive(moduleMethod2, list1, Lit20, "make a dictionary")), Lit21, "make a list");
    }

    static Object lambda6() {
        SimpleSymbol simpleSymbol = Lit23;
        SimpleSymbol simpleSymbol2 = Lit24;
        SimpleSymbol simpleSymbol3 = Lit25;
        Pair list1 = LList.list1(runtime.lookupInCurrentFormEnvironment(simpleSymbol3));
        IntNum intNum = Lit26;
        IntNum intNum2 = Lit27;
        LList.chain4(list1, intNum, intNum2, intNum, intNum2);
        runtime.callComponentMethod(simpleSymbol, simpleSymbol2, list1, Lit28);
        SimpleSymbol simpleSymbol4 = Lit29;
        runtime.callComponentMethod(simpleSymbol4, Lit30, LList.list1(runtime.lookupInCurrentFormEnvironment(Lit31)), Lit32);
        SimpleSymbol simpleSymbol5 = Lit33;
        runtime.callComponentMethod(simpleSymbol4, simpleSymbol5, LList.list2(runtime.lookupInCurrentFormEnvironment(Lit34), runtime.get$Mnproperty.apply2(simpleSymbol4, Lit35)), Lit36);
        return runtime.callComponentMethod(simpleSymbol4, simpleSymbol5, LList.list2(runtime.lookupInCurrentFormEnvironment(simpleSymbol3), runtime.get$Mnproperty.apply2(simpleSymbol4, Lit37)), Lit38);
    }

    static Procedure lambda7() {
        return lambda$Fn7;
    }

    static Object lambda8() {
        SimpleSymbol simpleSymbol = Lit23;
        SimpleSymbol simpleSymbol2 = Lit24;
        SimpleSymbol simpleSymbol3 = Lit25;
        Pair list1 = LList.list1(runtime.lookupInCurrentFormEnvironment(simpleSymbol3));
        IntNum intNum = Lit26;
        IntNum intNum2 = Lit27;
        LList.chain4(list1, intNum, intNum2, intNum, intNum2);
        runtime.callComponentMethod(simpleSymbol, simpleSymbol2, list1, Lit39);
        SimpleSymbol simpleSymbol4 = Lit29;
        runtime.callComponentMethod(simpleSymbol4, Lit30, LList.list1(runtime.lookupInCurrentFormEnvironment(Lit31)), Lit40);
        SimpleSymbol simpleSymbol5 = Lit33;
        runtime.callComponentMethod(simpleSymbol4, simpleSymbol5, LList.list2(runtime.lookupInCurrentFormEnvironment(Lit34), runtime.get$Mnproperty.apply2(simpleSymbol4, Lit35)), Lit41);
        return runtime.callComponentMethod(simpleSymbol4, simpleSymbol5, LList.list2(runtime.lookupInCurrentFormEnvironment(simpleSymbol3), runtime.get$Mnproperty.apply2(simpleSymbol4, Lit37)), Lit42);
    }

    static Object lambda9() {
        SimpleSymbol simpleSymbol = Lit0;
        SimpleSymbol simpleSymbol2 = Lit43;
        IntNum intNum = Lit44;
        SimpleSymbol simpleSymbol3 = Lit45;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, intNum, simpleSymbol3);
        SimpleSymbol simpleSymbol4 = Lit46;
        Boolean bool = Boolean.TRUE;
        SimpleSymbol simpleSymbol5 = Lit47;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol4, bool, simpleSymbol5);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit48, Lit49, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit50, Lit51, simpleSymbol3);
        SimpleSymbol simpleSymbol6 = Lit52;
        SimpleSymbol simpleSymbol7 = Lit53;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol6, "ExoPlayer Creator", simpleSymbol7);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit54, "exoplayer.png", simpleSymbol7);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit55, Lit56, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit57, Lit58, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit59, "user", simpleSymbol7);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit60, Boolean.TRUE, simpleSymbol5);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit61, "Responsive", simpleSymbol7);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit62, "Exoplayer", simpleSymbol7);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit63, Boolean.FALSE, simpleSymbol5);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit64, Lit65, simpleSymbol3);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit66, "1.5", simpleSymbol7);
    }

    public Object Screen1$Initialize() {
        runtime.setThisForm();
        SimpleSymbol simpleSymbol = Lit67;
        SimpleSymbol simpleSymbol2 = Lit68;
        Boolean bool = Boolean.TRUE;
        SimpleSymbol simpleSymbol3 = Lit47;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, bool, simpleSymbol3);
        ModuleMethod moduleMethod = runtime.yail$Mnequal$Qu;
        SimpleSymbol simpleSymbol4 = Lit69;
        SimpleSymbol simpleSymbol5 = Lit70;
        if (runtime.callYailPrimitive(moduleMethod, LList.list2(runtime.callComponentMethod(simpleSymbol4, simpleSymbol5, LList.list2("checkUpdate", "notThere"), Lit71), "notThere"), Lit72, "=") != Boolean.FALSE) {
            runtime.callComponentMethod(Lit73, Lit74, LList.list2(runtime.callYailPrimitive(runtime.text$Mndeobfuscate, LList.list2("g0?.`\u0006\u001f)\u000ep\u0007#5\u00165!'\u0018?\u001e8@4+5\u001a\u0005\fY.\u0001'\u0012.V#\u001cQ&\"=3LA", "zxafqoq"), Lit75, "deobfuscate text"), "Sheet1"), Lit76);
            runtime.callComponentMethod(simpleSymbol4, Lit77, LList.list2("checkUpdate", "alreadyUpdated"), Lit78);
        }
        if (runtime.callYailPrimitive(runtime.yail$Mnequal$Qu, LList.list2(runtime.callYailPrimitive(runtime.string$Mnstarts$Mnat, LList.list2(runtime.get$Mnproperty.apply2(Lit0, Lit79), "5"), Lit80, "starts at"), Boolean.TRUE), Lit81, "=") == Boolean.FALSE) {
            return Boolean.FALSE;
        }
        if (runtime.callYailPrimitive(runtime.yail$Mnequal$Qu, LList.list2(runtime.callComponentMethod(simpleSymbol4, simpleSymbol5, LList.list2("belowVersion1", "notThere"), Lit82), "notThere"), Lit83, "=") != Boolean.FALSE) {
            runtime.callComponentMethod(Lit84, Lit85, LList.list3("On this version of Android, the app DOES NOT function properly and was verified through testing. Features will be disabled.", "Severe Compatibility Issue", "OK"), Lit86);
            runtime.callComponentMethod(simpleSymbol4, Lit77, LList.list2("belowVersion1", "alreadyUpdated"), Lit87);
        }
        runtime.setAndCoerceProperty$Ex(Lit88, simpleSymbol2, Boolean.FALSE, simpleSymbol3);
        return runtime.setAndCoerceProperty$Ex(Lit89, simpleSymbol2, Boolean.FALSE, simpleSymbol3);
    }

    public Object Screen1$BackPressed() {
        runtime.setThisForm();
        SimpleSymbol simpleSymbol = Lit84;
        SimpleSymbol simpleSymbol2 = Lit92;
        Pair list1 = LList.list1("This will kill the ExoPlayer initialization you created if you press \"Yes\". This will close the app and you will be returned to your device's launcher.");
        LList.chain4(list1, "Are you sure you want to stop?", "Yes", "No", Boolean.FALSE);
        return runtime.callComponentMethod(simpleSymbol, simpleSymbol2, list1, Lit93);
    }

    static Object lambda10() {
        SimpleSymbol simpleSymbol = Lit25;
        SimpleSymbol simpleSymbol2 = Lit97;
        IntNum intNum = Lit98;
        SimpleSymbol simpleSymbol3 = Lit45;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, intNum, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit68, Boolean.FALSE, Lit47);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit99, Lit100, simpleSymbol3);
    }

    static Object lambda11() {
        SimpleSymbol simpleSymbol = Lit25;
        SimpleSymbol simpleSymbol2 = Lit97;
        IntNum intNum = Lit98;
        SimpleSymbol simpleSymbol3 = Lit45;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, intNum, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit68, Boolean.FALSE, Lit47);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit99, Lit100, simpleSymbol3);
    }

    static Object lambda12() {
        SimpleSymbol simpleSymbol = Lit103;
        SimpleSymbol simpleSymbol2 = Lit104;
        IntNum intNum = Lit105;
        SimpleSymbol simpleSymbol3 = Lit45;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, intNum, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit106, Lit107, simpleSymbol3);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit99, Lit100, simpleSymbol3);
    }

    static Object lambda13() {
        SimpleSymbol simpleSymbol = Lit103;
        SimpleSymbol simpleSymbol2 = Lit104;
        IntNum intNum = Lit105;
        SimpleSymbol simpleSymbol3 = Lit45;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, intNum, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit106, Lit107, simpleSymbol3);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit99, Lit100, simpleSymbol3);
    }

    static Object lambda14() {
        SimpleSymbol simpleSymbol = Lit31;
        SimpleSymbol simpleSymbol2 = Lit48;
        IntNum intNum = Lit49;
        SimpleSymbol simpleSymbol3 = Lit45;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, intNum, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit50, Lit51, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit97, Lit110, simpleSymbol3);
        SimpleSymbol simpleSymbol4 = Lit111;
        IntNum intNum2 = Lit100;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol4, intNum2, simpleSymbol3);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit99, intNum2, simpleSymbol3);
    }

    static Object lambda15() {
        SimpleSymbol simpleSymbol = Lit31;
        SimpleSymbol simpleSymbol2 = Lit48;
        IntNum intNum = Lit49;
        SimpleSymbol simpleSymbol3 = Lit45;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, intNum, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit50, Lit51, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit97, Lit110, simpleSymbol3);
        SimpleSymbol simpleSymbol4 = Lit111;
        IntNum intNum2 = Lit100;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol4, intNum2, simpleSymbol3);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit99, intNum2, simpleSymbol3);
    }

    static Object lambda16() {
        SimpleSymbol simpleSymbol = Lit67;
        SimpleSymbol simpleSymbol2 = Lit48;
        IntNum intNum = Lit49;
        SimpleSymbol simpleSymbol3 = Lit45;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, intNum, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit50, Lit51, simpleSymbol3);
        SimpleSymbol simpleSymbol4 = Lit111;
        IntNum intNum2 = Lit100;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol4, intNum2, simpleSymbol3);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit99, intNum2, simpleSymbol3);
    }

    static Object lambda17() {
        SimpleSymbol simpleSymbol = Lit67;
        SimpleSymbol simpleSymbol2 = Lit48;
        IntNum intNum = Lit49;
        SimpleSymbol simpleSymbol3 = Lit45;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, intNum, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit50, Lit51, simpleSymbol3);
        SimpleSymbol simpleSymbol4 = Lit111;
        IntNum intNum2 = Lit100;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol4, intNum2, simpleSymbol3);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit99, intNum2, simpleSymbol3);
    }

    static Object lambda18() {
        SimpleSymbol simpleSymbol = Lit116;
        SimpleSymbol simpleSymbol2 = Lit48;
        IntNum intNum = Lit49;
        SimpleSymbol simpleSymbol3 = Lit45;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, intNum, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit50, Lit51, simpleSymbol3);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit99, Lit100, simpleSymbol3);
    }

    static Object lambda19() {
        SimpleSymbol simpleSymbol = Lit116;
        SimpleSymbol simpleSymbol2 = Lit48;
        IntNum intNum = Lit49;
        SimpleSymbol simpleSymbol3 = Lit45;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, intNum, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit50, Lit51, simpleSymbol3);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit99, Lit100, simpleSymbol3);
    }

    static Object lambda20() {
        return runtime.setAndCoerceProperty$Ex(Lit119, Lit5, "Video URL:", Lit53);
    }

    static Object lambda21() {
        return runtime.setAndCoerceProperty$Ex(Lit119, Lit5, "Video URL:", Lit53);
    }

    static Object lambda22() {
        SimpleSymbol simpleSymbol = Lit125;
        SimpleSymbol simpleSymbol2 = Lit48;
        IntNum intNum = Lit49;
        SimpleSymbol simpleSymbol3 = Lit45;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, intNum, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit50, Lit51, simpleSymbol3);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit99, Lit100, simpleSymbol3);
    }

    static Object lambda23() {
        SimpleSymbol simpleSymbol = Lit125;
        SimpleSymbol simpleSymbol2 = Lit48;
        IntNum intNum = Lit49;
        SimpleSymbol simpleSymbol3 = Lit45;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, intNum, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit50, Lit51, simpleSymbol3);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit99, Lit100, simpleSymbol3);
    }

    static Object lambda24() {
        return runtime.setAndCoerceProperty$Ex(Lit128, Lit5, "Subtitles URL:", Lit53);
    }

    static Object lambda25() {
        return runtime.setAndCoerceProperty$Ex(Lit128, Lit5, "Subtitles URL:", Lit53);
    }

    static Object lambda26() {
        SimpleSymbol simpleSymbol = Lit133;
        SimpleSymbol simpleSymbol2 = Lit48;
        IntNum intNum = Lit49;
        SimpleSymbol simpleSymbol3 = Lit45;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, intNum, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit50, Lit51, simpleSymbol3);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit99, Lit100, simpleSymbol3);
    }

    static Object lambda27() {
        SimpleSymbol simpleSymbol = Lit133;
        SimpleSymbol simpleSymbol2 = Lit48;
        IntNum intNum = Lit49;
        SimpleSymbol simpleSymbol3 = Lit45;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, intNum, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit50, Lit51, simpleSymbol3);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit99, Lit100, simpleSymbol3);
    }

    static Object lambda28() {
        return runtime.setAndCoerceProperty$Ex(Lit136, Lit5, "Report if ExoPlayer errors occurred.", Lit53);
    }

    static Object lambda29() {
        return runtime.setAndCoerceProperty$Ex(Lit136, Lit5, "Report if ExoPlayer errors occurred.", Lit53);
    }

    static Object lambda30() {
        SimpleSymbol simpleSymbol = Lit139;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit140, Lit141, Lit45);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit5, "More info", Lit53);
    }

    static Object lambda31() {
        SimpleSymbol simpleSymbol = Lit139;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit140, Lit141, Lit45);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit5, "More info", Lit53);
    }

    public Object moreinfo$Click() {
        runtime.setThisForm();
        return runtime.callComponentMethod(Lit84, Lit85, LList.list3("About ExoPlayer Creator: ExoPlayer is an Android-only video player that includes many robust features, such as support for HLS and MPEG-DASH, a built-in quality selector, and since it is built-in on Android you don't have to worry about issues where a video won't play. As long as you put in a URL that links to a video it should work. ExoPlayer also has built-in subtitle support. The HTML5 player is simpler but requires less configuration. Just input a video URL, click \"Set Video\", and you're ready to go. The ExoPlayer Creator app requires almost no technical knowledge at all, and you can instantly start watching videos with ExoPlayer or an HTML5 player inside just one app.", "More Info", "OK"), Lit143);
    }

    static Object lambda32() {
        SimpleSymbol simpleSymbol = Lit147;
        SimpleSymbol simpleSymbol2 = Lit48;
        IntNum intNum = Lit49;
        SimpleSymbol simpleSymbol3 = Lit45;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, intNum, simpleSymbol3);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit99, Lit100, simpleSymbol3);
    }

    static Object lambda33() {
        SimpleSymbol simpleSymbol = Lit147;
        SimpleSymbol simpleSymbol2 = Lit48;
        IntNum intNum = Lit49;
        SimpleSymbol simpleSymbol3 = Lit45;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, intNum, simpleSymbol3);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit99, Lit100, simpleSymbol3);
    }

    static Object lambda34() {
        SimpleSymbol simpleSymbol = Lit88;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit140, Lit141, Lit45);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit5, "Create Exoplayer", Lit53);
    }

    static Object lambda35() {
        SimpleSymbol simpleSymbol = Lit88;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit140, Lit141, Lit45);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit5, "Create Exoplayer", Lit53);
    }

    public Object createExoPlayer$Click() {
        PairWithPosition pairWithPosition;
        Pair pair;
        SimpleSymbol simpleSymbol;
        runtime.setThisForm();
        SimpleSymbol simpleSymbol2 = Lit67;
        SimpleSymbol simpleSymbol3 = Lit68;
        Boolean bool = Boolean.FALSE;
        SimpleSymbol simpleSymbol4 = Lit47;
        runtime.setAndCoerceProperty$Ex(simpleSymbol2, simpleSymbol3, bool, simpleSymbol4);
        SimpleSymbol simpleSymbol5 = Lit10;
        runtime.setAndCoerceProperty$Ex(simpleSymbol5, Lit151, Boolean.FALSE, simpleSymbol4);
        Scheme.applyToArgs.apply1(runtime.lookupGlobalVarInCurrentFormEnvironment(Lit22, runtime.$Stthe$Mnnull$Mnvalue$St));
        SimpleSymbol simpleSymbol6 = Lit152;
        runtime.callComponentMethod(simpleSymbol6, Lit153, LList.Empty, LList.Empty);
        runtime.callComponentMethod(simpleSymbol5, Lit154, LList.Empty, LList.Empty);
        runtime.callComponentMethod(simpleSymbol6, Lit155, LList.list2(runtime.lookupInCurrentFormEnvironment(Lit34), runtime.callComponentMethod(simpleSymbol5, Lit156, LList.Empty, LList.Empty)), Lit157);
        runtime.setAndCoerceProperty$Ex(simpleSymbol6, Lit158, Boolean.TRUE, simpleSymbol4);
        runtime.setAndCoerceProperty$Ex(simpleSymbol6, Lit159, Boolean.TRUE, simpleSymbol4);
        ModuleMethod moduleMethod = runtime.yail$Mnequal$Qu;
        ModuleMethod moduleMethod2 = runtime.get$Mnproperty;
        SimpleSymbol simpleSymbol7 = Lit4;
        SimpleSymbol simpleSymbol8 = Lit5;
        if (runtime.callYailPrimitive(moduleMethod, LList.list2(moduleMethod2.apply2(simpleSymbol7, simpleSymbol8), ""), Lit160, "=") != Boolean.FALSE) {
            simpleSymbol = Lit161;
            pair = LList.list2(runtime.get$Mnproperty.apply2(Lit122, simpleSymbol8), runtime.callYailPrimitive(runtime.make$Mnyail$Mnlist, LList.Empty, LList.Empty, "make a list"));
            pairWithPosition = Lit162;
        } else {
            simpleSymbol = Lit161;
            pair = LList.list2(runtime.get$Mnproperty.apply2(Lit122, simpleSymbol8), Scheme.applyToArgs.apply1(runtime.lookupGlobalVarInCurrentFormEnvironment(Lit3, runtime.$Stthe$Mnnull$Mnvalue$St)));
            pairWithPosition = Lit163;
        }
        return runtime.callComponentMethod(simpleSymbol5, simpleSymbol, pair, pairWithPosition);
    }

    static Object lambda36() {
        SimpleSymbol simpleSymbol = Lit89;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit140, Lit141, Lit45);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit5, "Create HTML5 player", Lit53);
    }

    static Object lambda37() {
        SimpleSymbol simpleSymbol = Lit89;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit140, Lit141, Lit45);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit5, "Create HTML5 player", Lit53);
    }

    public Object createVideoViewPlayer$Click() {
        runtime.setThisForm();
        return runtime.callYailPrimitive(runtime.open$Mnanother$Mnscreen, LList.list1("VideoView"), Lit167, "open another screen");
    }

    static Object lambda38() {
        SimpleSymbol simpleSymbol = Lit34;
        SimpleSymbol simpleSymbol2 = Lit48;
        IntNum intNum = Lit49;
        SimpleSymbol simpleSymbol3 = Lit45;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, intNum, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit50, Lit51, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit97, Lit170, simpleSymbol3);
        SimpleSymbol simpleSymbol4 = Lit111;
        IntNum intNum2 = Lit100;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol4, intNum2, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit68, Boolean.FALSE, Lit47);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit99, intNum2, simpleSymbol3);
    }

    static Object lambda39() {
        SimpleSymbol simpleSymbol = Lit34;
        SimpleSymbol simpleSymbol2 = Lit48;
        IntNum intNum = Lit49;
        SimpleSymbol simpleSymbol3 = Lit45;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, intNum, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit50, Lit51, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit97, Lit170, simpleSymbol3);
        SimpleSymbol simpleSymbol4 = Lit111;
        IntNum intNum2 = Lit100;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol4, intNum2, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit68, Boolean.FALSE, Lit47);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit99, intNum2, simpleSymbol3);
    }

    public Object ExoplayerCore1$OnAppResume() {
        runtime.setThisForm();
        SimpleSymbol simpleSymbol = Lit10;
        runtime.callComponentMethod(simpleSymbol, Lit174, LList.Empty, LList.Empty);
        SimpleSymbol simpleSymbol2 = Lit152;
        SimpleSymbol simpleSymbol3 = Lit156;
        return runtime.setAndCoerceProperty$Ex(simpleSymbol2, simpleSymbol3, runtime.callComponentMethod(simpleSymbol, simpleSymbol3, LList.Empty, LList.Empty), Lit175);
    }

    public Object ExoplayerCore1$OnError(Object $error) {
        PairWithPosition pairWithPosition;
        Pair pair;
        SimpleSymbol simpleSymbol;
        SimpleSymbol simpleSymbol2;
        Object obj;
        Object obj2;
        Object $error2 = runtime.sanitizeComponentData($error);
        runtime.setThisForm();
        if (runtime.callYailPrimitive(runtime.yail$Mnequal$Qu, LList.list2(runtime.get$Mnproperty.apply2(Lit136, Lit178), Boolean.TRUE), Lit179, "=") != Boolean.FALSE) {
            simpleSymbol2 = Lit84;
            simpleSymbol = Lit85;
            if ($error2 instanceof Package) {
                obj2 = runtime.signalRuntimeError(strings.stringAppend("The variable ", runtime.getDisplayRepresentation(Lit180), " is not bound in the current context"), "Unbound Variable");
            } else {
                obj2 = $error2;
            }
            pair = LList.list3(obj2, "An error occured in ExoPlayer", "OK");
            pairWithPosition = Lit181;
        } else {
            simpleSymbol2 = Lit84;
            simpleSymbol = Lit182;
            if ($error2 instanceof Package) {
                obj = runtime.signalRuntimeError(strings.stringAppend("The variable ", runtime.getDisplayRepresentation(Lit180), " is not bound in the current context"), "Unbound Variable");
            } else {
                obj = $error2;
            }
            pair = LList.list1(obj);
            pairWithPosition = Lit183;
        }
        return runtime.callComponentMethod(simpleSymbol2, simpleSymbol, pair, pairWithPosition);
    }

    static Object lambda40() {
        SimpleSymbol simpleSymbol = Lit152;
        SimpleSymbol simpleSymbol2 = Lit187;
        IntNum intNum = Lit188;
        SimpleSymbol simpleSymbol3 = Lit45;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, intNum, simpleSymbol3);
        SimpleSymbol simpleSymbol4 = Lit189;
        Boolean bool = Boolean.TRUE;
        SimpleSymbol simpleSymbol5 = Lit47;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol4, bool, simpleSymbol5);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit190, Boolean.TRUE, simpleSymbol5);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit191, Lit192, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit193, Lit194, simpleSymbol3);
        SimpleSymbol simpleSymbol6 = Lit195;
        SimpleSymbol simpleSymbol7 = Lit53;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol6, "", simpleSymbol7);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit196, Lit197, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit198, Boolean.TRUE, simpleSymbol5);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit199, Lit200, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit201, Boolean.TRUE, simpleSymbol5);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit202, Boolean.TRUE, simpleSymbol5);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit203, Boolean.FALSE, simpleSymbol5);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit204, Boolean.TRUE, simpleSymbol5);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit205, Boolean.TRUE, simpleSymbol5);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit206, Lit207, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit208, ExoplayerUi.REPEAT_MODE_OFF, simpleSymbol7);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit209, ExoplayerUi.RESIZE_MODE_FIT, simpleSymbol7);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit210, Boolean.TRUE, simpleSymbol5);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit211, Lit212, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit213, ExoplayerUi.SHOW_BUFFERING_ALWAYS, simpleSymbol7);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit214, Boolean.FALSE, simpleSymbol5);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit215, Lit216, simpleSymbol3);
        SimpleSymbol simpleSymbol8 = Lit217;
        IntNum intNum2 = Lit218;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol8, intNum2, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit219, Boolean.TRUE, simpleSymbol5);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit220, Lit221, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit222, ExoplayerUi.EDGE_TYPE_NONE, simpleSymbol7);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit223, Lit224, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit225, ExoplayerUi.VIEW_TYPE_CANVAS, simpleSymbol7);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit226, intNum2, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit227, Lit228, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit229, ExoplayerUi.TEXT_SIE_TYPE_FRACTION, simpleSymbol7);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit230, "", simpleSymbol7);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit231, Lit232, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit233, ExoplayerUi.SURFACE_TYPE_SURFACE_VIEW, simpleSymbol7);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit234, Lit235, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit236, Lit26, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit237, Lit238, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit239, Lit51, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit240, Boolean.TRUE, simpleSymbol5);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit241, Boolean.TRUE, simpleSymbol5);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit242, Boolean.TRUE, simpleSymbol5);
    }

    static Object lambda41() {
        SimpleSymbol simpleSymbol = Lit152;
        SimpleSymbol simpleSymbol2 = Lit187;
        IntNum intNum = Lit188;
        SimpleSymbol simpleSymbol3 = Lit45;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, intNum, simpleSymbol3);
        SimpleSymbol simpleSymbol4 = Lit189;
        Boolean bool = Boolean.TRUE;
        SimpleSymbol simpleSymbol5 = Lit47;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol4, bool, simpleSymbol5);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit190, Boolean.TRUE, simpleSymbol5);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit191, Lit192, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit193, Lit194, simpleSymbol3);
        SimpleSymbol simpleSymbol6 = Lit195;
        SimpleSymbol simpleSymbol7 = Lit53;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol6, "", simpleSymbol7);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit196, Lit197, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit198, Boolean.TRUE, simpleSymbol5);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit199, Lit200, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit201, Boolean.TRUE, simpleSymbol5);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit202, Boolean.TRUE, simpleSymbol5);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit203, Boolean.FALSE, simpleSymbol5);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit204, Boolean.TRUE, simpleSymbol5);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit205, Boolean.TRUE, simpleSymbol5);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit206, Lit207, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit208, ExoplayerUi.REPEAT_MODE_OFF, simpleSymbol7);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit209, ExoplayerUi.RESIZE_MODE_FIT, simpleSymbol7);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit210, Boolean.TRUE, simpleSymbol5);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit211, Lit212, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit213, ExoplayerUi.SHOW_BUFFERING_ALWAYS, simpleSymbol7);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit214, Boolean.FALSE, simpleSymbol5);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit215, Lit216, simpleSymbol3);
        SimpleSymbol simpleSymbol8 = Lit217;
        IntNum intNum2 = Lit218;
        runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol8, intNum2, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit219, Boolean.TRUE, simpleSymbol5);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit220, Lit221, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit222, ExoplayerUi.EDGE_TYPE_NONE, simpleSymbol7);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit223, Lit224, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit225, ExoplayerUi.VIEW_TYPE_CANVAS, simpleSymbol7);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit226, intNum2, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit227, Lit228, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit229, ExoplayerUi.TEXT_SIE_TYPE_FRACTION, simpleSymbol7);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit230, "", simpleSymbol7);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit231, Lit232, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit233, ExoplayerUi.SURFACE_TYPE_SURFACE_VIEW, simpleSymbol7);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit234, Lit235, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit236, Lit26, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit237, Lit238, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit239, Lit51, simpleSymbol3);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit240, Boolean.TRUE, simpleSymbol5);
        runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit241, Boolean.TRUE, simpleSymbol5);
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, Lit242, Boolean.TRUE, simpleSymbol5);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x002e, code lost:
        if (com.google.youngandroid.runtime.signalRuntimeError(kawa.lib.strings.stringAppend("The variable ", com.google.youngandroid.runtime.getDisplayRepresentation(Lit244), " is not bound in the current context"), "Unbound Variable") != java.lang.Boolean.FALSE) goto L_0x0035;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0033, code lost:
        if (r0 != java.lang.Boolean.FALSE) goto L_0x0035;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:?, code lost:
        return gnu.mapping.Values.empty;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object ExoplayerUi1$OnSettingsWindowDismiss(java.lang.Object r6) {
        /*
            r5 = this;
            java.lang.Object r0 = com.google.youngandroid.runtime.sanitizeComponentData(r6)
            r1 = 0
            com.google.youngandroid.runtime.setThisForm()
            boolean r1 = r0 instanceof java.lang.Package
            if (r1 == 0) goto L_0x0031
            r1 = 3
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r2 = 0
            java.lang.String r3 = "The variable "
            r1[r2] = r3
            gnu.mapping.SimpleSymbol r2 = Lit244
            java.lang.Object r2 = com.google.youngandroid.runtime.getDisplayRepresentation(r2)
            r3 = 1
            r1[r3] = r2
            r2 = 2
            java.lang.String r3 = " is not bound in the current context"
            r1[r2] = r3
            gnu.lists.FString r1 = kawa.lib.strings.stringAppend(r1)
            java.lang.String r2 = "Unbound Variable"
            java.lang.Object r1 = com.google.youngandroid.runtime.signalRuntimeError(r1, r2)
            java.lang.Boolean r2 = java.lang.Boolean.FALSE
            if (r1 == r2) goto L_0x0042
            goto L_0x0035
        L_0x0031:
            java.lang.Boolean r1 = java.lang.Boolean.FALSE
            if (r0 == r1) goto L_0x0042
        L_0x0035:
            gnu.mapping.SimpleSymbol r1 = Lit152
            gnu.mapping.SimpleSymbol r2 = Lit245
            gnu.lists.LList r3 = gnu.lists.LList.Empty
            gnu.lists.LList r4 = gnu.lists.LList.Empty
            java.lang.Object r1 = com.google.youngandroid.runtime.callComponentMethod(r1, r2, r3, r4)
            goto L_0x0044
        L_0x0042:
            gnu.mapping.Values r1 = gnu.mapping.Values.empty
        L_0x0044:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: appinventor.ai_sonicforces207.exoplayer.Screen1.ExoplayerUi1$OnSettingsWindowDismiss(java.lang.Object):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x002e, code lost:
        if (com.google.youngandroid.runtime.signalRuntimeError(kawa.lib.strings.stringAppend("The variable ", com.google.youngandroid.runtime.getDisplayRepresentation(Lit244), " is not bound in the current context"), "Unbound Variable") != java.lang.Boolean.FALSE) goto L_0x0035;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0033, code lost:
        if (r0 != java.lang.Boolean.FALSE) goto L_0x0035;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x004d, code lost:
        com.google.youngandroid.runtime.callComponentMethod(Lit152, Lit248, gnu.lists.LList.Empty, gnu.lists.LList.Empty);
        r1 = Lit0;
        r2 = Lit59;
        r3 = com.google.appinventor.components.common.ScreenOrientation.Portrait;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object ExoplayerUi1$OnFullscreenChanged(java.lang.Object r6) {
        /*
            r5 = this;
            java.lang.Object r0 = com.google.youngandroid.runtime.sanitizeComponentData(r6)
            r1 = 0
            com.google.youngandroid.runtime.setThisForm()
            boolean r1 = r0 instanceof java.lang.Package
            if (r1 == 0) goto L_0x0031
            r1 = 3
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r2 = 0
            java.lang.String r3 = "The variable "
            r1[r2] = r3
            gnu.mapping.SimpleSymbol r2 = Lit244
            java.lang.Object r2 = com.google.youngandroid.runtime.getDisplayRepresentation(r2)
            r3 = 1
            r1[r3] = r2
            r2 = 2
            java.lang.String r3 = " is not bound in the current context"
            r1[r2] = r3
            gnu.lists.FString r1 = kawa.lib.strings.stringAppend(r1)
            java.lang.String r2 = "Unbound Variable"
            java.lang.Object r1 = com.google.youngandroid.runtime.signalRuntimeError(r1, r2)
            java.lang.Boolean r2 = java.lang.Boolean.FALSE
            if (r1 == r2) goto L_0x004d
            goto L_0x0035
        L_0x0031:
            java.lang.Boolean r1 = java.lang.Boolean.FALSE
            if (r0 == r1) goto L_0x004d
        L_0x0035:
            gnu.mapping.SimpleSymbol r1 = Lit152
            gnu.mapping.SimpleSymbol r2 = Lit245
            gnu.lists.LList r3 = gnu.lists.LList.Empty
            gnu.lists.LList r4 = gnu.lists.LList.Empty
            com.google.youngandroid.runtime.callComponentMethod(r1, r2, r3, r4)
            gnu.mapping.SimpleSymbol r1 = Lit0
            gnu.mapping.SimpleSymbol r2 = Lit59
            com.google.appinventor.components.common.ScreenOrientation r3 = com.google.appinventor.components.common.ScreenOrientation.Landscape
        L_0x0046:
            gnu.mapping.SimpleSymbol r4 = Lit53
            java.lang.Object r1 = com.google.youngandroid.runtime.setAndCoerceProperty$Ex(r1, r2, r3, r4)
            goto L_0x005f
        L_0x004d:
            gnu.mapping.SimpleSymbol r1 = Lit152
            gnu.mapping.SimpleSymbol r2 = Lit248
            gnu.lists.LList r3 = gnu.lists.LList.Empty
            gnu.lists.LList r4 = gnu.lists.LList.Empty
            com.google.youngandroid.runtime.callComponentMethod(r1, r2, r3, r4)
            gnu.mapping.SimpleSymbol r1 = Lit0
            gnu.mapping.SimpleSymbol r2 = Lit59
            com.google.appinventor.components.common.ScreenOrientation r3 = com.google.appinventor.components.common.ScreenOrientation.Portrait
            goto L_0x0046
        L_0x005f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: appinventor.ai_sonicforces207.exoplayer.Screen1.ExoplayerUi1$OnFullscreenChanged(java.lang.Object):java.lang.Object");
    }

    public Object ExoplayerUi1$OnVisibilityChanged(Object $visible) {
        Object obj;
        Object $visible2 = runtime.sanitizeComponentData($visible);
        runtime.setThisForm();
        SimpleSymbol simpleSymbol = Lit25;
        SimpleSymbol simpleSymbol2 = Lit68;
        if ($visible2 instanceof Package) {
            obj = runtime.signalRuntimeError(strings.stringAppend("The variable ", runtime.getDisplayRepresentation(Lit251), " is not bound in the current context"), "Unbound Variable");
        } else {
            obj = $visible2;
        }
        return runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, obj, Lit47);
    }

    public Object ExoplayerUi1$OnVideoSettingsButtonClick() {
        runtime.setThisForm();
        SimpleSymbol simpleSymbol = Lit152;
        SimpleSymbol simpleSymbol2 = Lit254;
        Pair list1 = LList.list1("Select Video Quality");
        LList.chain4(list1, runtime.get$Mnproperty.apply2(simpleSymbol, Lit255), Boolean.FALSE, Boolean.FALSE, Boolean.FALSE);
        return runtime.callComponentMethod(simpleSymbol, simpleSymbol2, list1, Lit256);
    }

    public Object Notifier1$AfterChoosing(Object $choice) {
        Object obj;
        Object $choice2 = runtime.sanitizeComponentData($choice);
        runtime.setThisForm();
        ModuleMethod moduleMethod = runtime.yail$Mnequal$Qu;
        if ($choice2 instanceof Package) {
            obj = runtime.signalRuntimeError(strings.stringAppend("The variable ", runtime.getDisplayRepresentation(Lit265), " is not bound in the current context"), "Unbound Variable");
        } else {
            obj = $choice2;
        }
        return runtime.callYailPrimitive(moduleMethod, LList.list2(obj, "Yes"), Lit266, "=") != Boolean.FALSE ? runtime.callYailPrimitive(runtime.close$Mnapplication, LList.Empty, LList.Empty, "close application") : Values.empty;
    }

    static Object lambda42() {
        return runtime.setAndCoerceProperty$Ex(Lit69, Lit273, "videoViewSettingsDB", Lit53);
    }

    static Object lambda43() {
        return runtime.setAndCoerceProperty$Ex(Lit69, Lit273, "videoViewSettingsDB", Lit53);
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
        Screen1 = this;
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
