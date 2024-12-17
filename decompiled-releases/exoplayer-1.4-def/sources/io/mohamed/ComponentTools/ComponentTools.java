package io.mohamed.ComponentTools;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.AndroidViewComponent;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.util.YailList;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

@SimpleObject(external = true)
@DesignerComponent(category = ComponentCategory.EXTENSION, description = "This extension offers some tools that you can use with components.<br>Devoloped by: Mohammed Tamer", iconName = "https://firebasestorage.googleapis.com/v0/b/myhtmlpage-fafe8.appspot.com/o/image_16x16.png?alt=media&token=8ef0f45e-f83b-426b-a128-bdb4ed9682cc", nonVisible = true, version = 3)
public class ComponentTools extends AndroidNonvisibleComponent {
    private String BASE_PACKAGE = "com.google.appinventor.components.runtime";
    private ObjectAnimator Ganimation;
    /* access modifiers changed from: private */
    public Hashtable<String, ObjectAnimator> IDS = new Hashtable<>();
    /* access modifiers changed from: private */
    public Hashtable<AndroidViewComponent, View> TCOMPONENTS = new Hashtable<>();
    private ComponentContainer container;
    ArrayList x = new ArrayList();
    YailList x1 = new YailList();
    ArrayList y = new ArrayList();
    YailList y1 = new YailList();

    public ComponentTools(ComponentContainer container2) {
        super(container2.$form());
        this.container = container2;
    }

    @SimpleFunction(description = "Moves any component you want vertically.")
    public void MoveVertically(int px, AndroidViewComponent component, int duration, String id) {
        try {
            ObjectAnimator animation = ObjectAnimator.ofFloat(component.getView(), "translationY", new float[]{(float) px});
            animation.setDuration((long) duration);
            this.IDS.put(id, animation);
            this.Ganimation = animation;
            animation.start();
            animation.addListener(new Animator.AnimatorListener() {
                public void onAnimationEnd(Animator animation) {
                    ComponentTools componentTools = ComponentTools.this;
                    ComponentTools.this.MoveCompleted(componentTools.getKeyFromValue1(componentTools.IDS, animation));
                }

                public void onAnimationRepeat(Animator animation) {
                }

                public void onAnimationCancel(Animator animation) {
                    ComponentTools componentTools = ComponentTools.this;
                    ComponentTools.this.MoveCanceled(componentTools.getKeyFromValue1(componentTools.IDS, animation));
                }

                public void onAnimationStart(Animator animation) {
                    ComponentTools componentTools = ComponentTools.this;
                    ComponentTools.this.MoveStarted(componentTools.getKeyFromValue1(componentTools.IDS, animation));
                }
            });
        } catch (Exception e) {
            Error(e.getMessage());
        }
    }

    @SimpleFunction(description = "Moves any component you want vertically.")
    public void MoveHorizontally(int px, AndroidViewComponent component, int duration, String id) {
        try {
            ObjectAnimator animation = ObjectAnimator.ofFloat(component.getView(), "translationX", new float[]{(float) px});
            animation.setDuration((long) duration);
            this.IDS.put(id, animation);
            this.Ganimation = animation;
            animation.start();
            animation.addListener(new Animator.AnimatorListener() {
                public void onAnimationEnd(Animator animation) {
                    ComponentTools componentTools = ComponentTools.this;
                    ComponentTools.this.MoveCompleted(componentTools.getKeyFromValue1(componentTools.IDS, animation));
                }

                public void onAnimationRepeat(Animator animation) {
                }

                public void onAnimationCancel(Animator animation) {
                    ComponentTools componentTools = ComponentTools.this;
                    ComponentTools.this.MoveCanceled(componentTools.getKeyFromValue1(componentTools.IDS, animation));
                }

                public void onAnimationStart(Animator animation) {
                    ComponentTools componentTools = ComponentTools.this;
                    ComponentTools.this.MoveStarted(componentTools.getKeyFromValue1(componentTools.IDS, animation));
                }
            });
        } catch (Exception e) {
            Error(e.getMessage());
        }
    }

    @SimpleFunction(description = "Moves any component you want in custom path.")
    public void MoveInCustomPath(AndroidViewComponent component, int duration, float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean forceMoveTo, String id) {
        try {
            if (Build.VERSION.SDK_INT >= 11) {
                View view = component.getView();
                Path path = new Path();
                path.arcTo(left, top, right, bottom, startAngle, sweepAngle, forceMoveTo);
                ObjectAnimator animator = ObjectAnimator.ofFloat(view, View.X, View.Y, path);
                try {
                    animator.setDuration((long) duration);
                    try {
                        this.IDS.put(id, animator);
                        this.Ganimation = animator;
                        animator.start();
                        animator.addListener(new Animator.AnimatorListener() {
                            public void onAnimationEnd(Animator animation) {
                                ComponentTools componentTools = ComponentTools.this;
                                ComponentTools.this.MoveCompleted(componentTools.getKeyFromValue1(componentTools.IDS, animation));
                            }

                            public void onAnimationRepeat(Animator animation) {
                            }

                            public void onAnimationCancel(Animator animation) {
                                ComponentTools componentTools = ComponentTools.this;
                                ComponentTools.this.MoveCanceled(componentTools.getKeyFromValue1(componentTools.IDS, animation));
                            }

                            public void onAnimationStart(Animator animation) {
                                ComponentTools componentTools = ComponentTools.this;
                                ComponentTools.this.MoveStarted(componentTools.getKeyFromValue1(componentTools.IDS, animation));
                            }
                        });
                    } catch (Exception e) {
                        e = e;
                    }
                } catch (Exception e2) {
                    e = e2;
                    String str = id;
                    Error(e.getMessage());
                }
            } else {
                int i = duration;
                String str2 = id;
            }
        } catch (Exception e3) {
            e = e3;
            int i2 = duration;
            String str3 = id;
            Error(e.getMessage());
        }
    }

    @SimpleFunction(description = "Cancels The movement by its id.")
    public void CancelMovement(String id) {
        try {
            this.IDS.get(id).cancel();
        } catch (Exception e) {
            Error(e.getMessage());
        }
    }

    @SimpleFunction(description = "Pauses The current movement.")
    public void PauseMovement(String id) {
        try {
            this.IDS.get(id).pause();
        } catch (Exception e) {
            Error(e.getMessage());
        }
    }

    @SimpleFunction(description = "Resumes The current movement by its id.")
    public void ResumeMovement(String id) {
        try {
            this.IDS.get(id).resume();
        } catch (Exception e) {
            Error(e.getMessage());
        }
    }

    @SimpleFunction(description = "Returns true if The current movement by its id is running.")
    public boolean IsMovementRunning(String id) {
        try {
            return this.IDS.get(id).isRunning();
        } catch (Exception e) {
            Error(e.getMessage());
            return false;
        }
    }

    @SimpleFunction(description = "Returns true if The current movement by its id is paused.")
    public boolean IsMovementPaused(String id) {
        try {
            return this.IDS.get(id).isPaused();
        } catch (Exception e) {
            Error(e.getMessage());
            return false;
        }
    }

    @SimpleEvent(description = "This event is triggerred when the current movement ends.")
    public void MoveCompleted(String id) {
        EventDispatcher.dispatchEvent(this, "MoveCompleted", id);
    }

    @SimpleEvent(description = "This event is triggerred when a component is touched.The action is returned in the action parameter.")
    public void Touched(Object component, String info, YailList x2, YailList y2, String action, int positionCount, long eventTime) {
        EventDispatcher.dispatchEvent(this, "Touched", component, info, x2, y2, action, Integer.valueOf(positionCount), Long.valueOf(eventTime));
    }

    @SimpleEvent(description = "This event is triggerred when the current movement starts.")
    public void MoveStarted(String id) {
        EventDispatcher.dispatchEvent(this, "MoveStarted", id);
    }

    @SimpleEvent(description = "This event is triggerred when the current movement canceles.")
    public void MoveCanceled(String id) {
        EventDispatcher.dispatchEvent(this, "MoveCanceled", id);
    }

    @SimpleEvent(description = "This event is triggerred when an error occurrs.")
    public void Error(String error) {
        EventDispatcher.dispatchEvent(this, "Error", error);
    }

    @SimpleFunction(description = "Performs a click on any component you want.")
    public void PerformClick(AndroidViewComponent component) {
        try {
            component.getView().performClick();
            Touched(component, "UNDEFINED", YailList.makeEmptyList(), YailList.makeEmptyList(), "ACTION_UP", 1, 0);
        } catch (Exception e) {
            Error(e.getMessage());
        }
    }

    @SimpleFunction(description = "Changes the rotation of any component you want.")
    public void SetRotation(AndroidViewComponent component, float rotation) {
        try {
            component.getView().setRotation(rotation);
        } catch (Exception e) {
            Error(e.getMessage());
        }
    }

    @SimpleFunction(description = "Changes the rotation X of any component you want.")
    public void SetRotationX(AndroidViewComponent component, float rotationX) {
        try {
            component.getView().setRotationX(rotationX);
        } catch (Exception e) {
            Error(e.getMessage());
        }
    }

    @SimpleFunction(description = "Changes the rotation Y of any component you want.")
    public void SetRotationY(AndroidViewComponent component, float rotationY) {
        try {
            component.getView().setRotationY(rotationY);
        } catch (Exception e) {
            Error(e.getMessage());
        }
    }

    @SimpleFunction(description = "Changes the scale Y of any component you want.")
    public void SetScaleY(AndroidViewComponent component, float scaleY) {
        try {
            component.getView().setScaleY(scaleY);
        } catch (Exception e) {
            Error(e.getMessage());
        }
    }

    @SimpleFunction(description = "Changes the scale X of any component you want.")
    public void SetScaleX(AndroidViewComponent component, float scaleX) {
        try {
            component.getView().setScaleX(scaleX);
        } catch (Exception e) {
            Error(e.getMessage());
        }
    }

    @SimpleFunction(description = "Gets component X position.")
    public float GetComponentX(AndroidViewComponent component) {
        try {
            return component.getView().getX();
        } catch (Exception e) {
            Error(e.getMessage());
            return 0.0f;
        }
    }

    @SimpleFunction(description = "Gets component Y position.")
    public float GetComponentY(AndroidViewComponent component) {
        try {
            return component.getView().getY();
        } catch (Exception e) {
            Error(e.getMessage());
            return 0.0f;
        }
    }

    @SimpleFunction(description = "Gets the component bottom postion.")
    public float GetBottomPostion(AndroidViewComponent component) {
        try {
            return (float) component.getView().getBottom();
        } catch (Exception e) {
            Error(e.getMessage());
            return 0.0f;
        }
    }

    @SimpleFunction(description = "Gets the component top postion.")
    public float GetTopPostion(AndroidViewComponent component) {
        try {
            return (float) component.getView().getTop();
        } catch (Exception e) {
            Error(e.getMessage());
            return 0.0f;
        }
    }

    @SimpleFunction(description = "Gets the component right postion.")
    public float GetRightPostion(AndroidViewComponent component) {
        try {
            return (float) component.getView().getRight();
        } catch (Exception e) {
            Error(e.getMessage());
            return 0.0f;
        }
    }

    @SimpleFunction(description = "Gets the component left postion.")
    public float GetLeftPostion(AndroidViewComponent component) {
        try {
            return (float) component.getView().getLeft();
        } catch (Exception e) {
            Error(e.getMessage());
            return 0.0f;
        }
    }

    @SimpleFunction(description = "Gets the rotation X of the component.")
    public float GetRotationX(AndroidViewComponent component) {
        try {
            return component.getView().getRotationX();
        } catch (Exception e) {
            Error(e.getMessage());
            return 0.0f;
        }
    }

    @SimpleFunction(description = "Gets the rotation Y of the component.")
    public float GetRotationY(AndroidViewComponent component) {
        try {
            return component.getView().getRotationY();
        } catch (Exception e) {
            Error(e.getMessage());
            return 0.0f;
        }
    }

    @SimpleFunction(description = "Gets the rotation of the component.")
    public float GetRotation(AndroidViewComponent component) {
        try {
            return component.getView().getRotation();
        } catch (Exception e) {
            Error(e.getMessage());
            return 0.0f;
        }
    }

    @SimpleFunction(description = "Gets the scale X of the component.")
    public float GetScaleX(AndroidViewComponent component) {
        try {
            return component.getView().getScaleX();
        } catch (Exception e) {
            Error(e.getMessage());
            return 0.0f;
        }
    }

    @SimpleFunction(description = "Gets the scale Y of the component.")
    public float GetScaleY(AndroidViewComponent component) {
        try {
            return component.getView().getScaleY();
        } catch (Exception e) {
            Error(e.getMessage());
            return 0.0f;
        }
    }

    @SimpleFunction(description = "Performs a long click on any component you want.")
    public void PerformLongClick(AndroidViewComponent component) {
        try {
            component.getView().performLongClick();
            Touched(component, "UNDEFINED", YailList.makeEmptyList(), YailList.makeEmptyList(), "ACTION_UP", 1, 0);
        } catch (Exception e) {
            Error(e.getMessage());
        }
    }

    @SimpleFunction(description = "Set the Shadow Elevation of any component you want.")
    public void SetShadowElevation(AndroidViewComponent component, float shadowElevation) {
        try {
            component.getView().setElevation(shadowElevation);
        } catch (Exception e) {
            Error(e.getMessage());
        }
    }

    @SimpleFunction(description = "Gets the Shadow Elevation of any component you want.")
    public float GetShadowElevation(AndroidViewComponent component) {
        try {
            return component.getView().getElevation();
        } catch (Exception e) {
            Error(e.getMessage());
            return 0.0f;
        }
    }

    @SimpleFunction(description = "Sets the padding of any component you want.")
    public void SetPadding(AndroidViewComponent component, int left, int top, int right, int bottom) {
        try {
            component.getView().setPadding(left, top, right, bottom);
        } catch (Exception e) {
            Error(e.getMessage());
        }
    }

    @SimpleFunction(description = "Sets the X postion of any component you want.")
    public void SetX(AndroidViewComponent component, float x2) {
        try {
            component.getView().setX(x2);
        } catch (Exception e) {
            Error(e.getMessage());
        }
    }

    @SimpleFunction(description = "Sets the Z postion of any component you want.")
    public void SetZ(AndroidViewComponent component, float z) {
        try {
            component.getView().setZ(z);
        } catch (Exception e) {
            Error(e.getMessage());
        }
    }

    @SimpleFunction(description = "Sets the Z postion of any component you want.")
    public float GetZ(AndroidViewComponent component) {
        try {
            return component.getView().getZ();
        } catch (Exception e) {
            Error(e.getMessage());
            return 0.0f;
        }
    }

    @SimpleFunction(description = "Sets the Y postion of any component you want.")
    public void SetY(AndroidViewComponent component, float y2) {
        try {
            component.getView().setY(y2);
        } catch (Exception e) {
            Error(e.getMessage());
        }
    }

    @SimpleFunction(description = "Sets the top postion of any component you want.")
    public void SetTopPostion(AndroidViewComponent component, int top) {
        try {
            component.getView().setTop(top);
        } catch (Exception e) {
            Error(e.getMessage());
        }
    }

    @SimpleFunction(description = "Sets the right postion of any component you want.")
    public void SetRightPostion(AndroidViewComponent component, int right) {
        try {
            component.getView().setRight(right);
        } catch (Exception e) {
            Error(e.getMessage());
        }
    }

    @SimpleFunction(description = "Sets the left postion of any component you want.")
    public void SetLeftPostion(AndroidViewComponent component, int left) {
        try {
            component.getView().setLeft(left);
        } catch (Exception e) {
            Error(e.getMessage());
        }
    }

    @SimpleFunction(description = "Sets the bottom postion of any component you want.")
    public void SetBottomPostion(AndroidViewComponent component, int bottom) {
        try {
            component.getView().setBottom(bottom);
        } catch (Exception e) {
            Error(e.getMessage());
        }
    }

    @SimpleFunction(description = "Register component so the extension can detect touches on it.")
    public void RegisterComponent(AndroidViewComponent component) {
        try {
            View view = component.getView();
            this.TCOMPONENTS.put(component, view);
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    MotionEvent motionEvent = event;
                    ComponentTools componentTools = ComponentTools.this;
                    AndroidViewComponent com2 = componentTools.getKeyFromValue(componentTools.TCOMPONENTS, v);
                    String action1 = MotionEvent.actionToString(event.getActionMasked());
                    int PointerCount = event.getPointerCount();
                    long EventTime = event.getEventTime();
                    ComponentTools.this.x.clear();
                    ComponentTools.this.y.clear();
                    int touchCounter = event.getPointerCount();
                    int i = 0;
                    while (i < touchCounter) {
                        ComponentTools.this.x.add(Float.valueOf(motionEvent.getX(i)));
                        ComponentTools.this.y.add(Float.valueOf(motionEvent.getY(i)));
                        i++;
                    }
                    ComponentTools.this.x1 = YailList.makeEmptyList();
                    ComponentTools.this.y1 = YailList.makeEmptyList();
                    ComponentTools componentTools2 = ComponentTools.this;
                    componentTools2.x1 = YailList.makeList((List) componentTools2.x);
                    ComponentTools componentTools3 = ComponentTools.this;
                    componentTools3.y1 = YailList.makeList((List) componentTools3.y);
                    int i2 = touchCounter;
                    int i3 = i;
                    ComponentTools.this.Touched(com2, event.toString(), ComponentTools.this.x1, ComponentTools.this.y1, action1, PointerCount, EventTime);
                    return true;
                }
            });
        } catch (Exception e) {
            Error(e.getMessage());
        }
    }

    @SimpleFunction(description = "Gets the top padding of any component you want.")
    public int GetPaddingTop(AndroidViewComponent component) {
        try {
            return component.getView().getPaddingTop();
        } catch (Exception e) {
            Error(e.getMessage());
            return 0;
        }
    }

    @SimpleFunction(description = "Gets the right padding of any component you want.")
    public int GetPaddingRight(AndroidViewComponent component) {
        try {
            return component.getView().getPaddingRight();
        } catch (Exception e) {
            Error(e.getMessage());
            return 0;
        }
    }

    @SimpleProperty(description = "Gets the last used id.")
    public String LastUsedID() {
        return getKeyFromValue1(this.IDS, this.Ganimation);
    }

    @SimpleFunction(description = "Gets the left padding of any component you want.")
    public int GetPaddingLeft(AndroidViewComponent component) {
        try {
            return component.getView().getPaddingLeft();
        } catch (Exception e) {
            Error(e.getMessage());
            return 0;
        }
    }

    @SimpleFunction(description = "Gets the bottom padding of any component you want.")
    public int GetPaddingBottom(AndroidViewComponent component) {
        try {
            return component.getView().getPaddingBottom();
        } catch (Exception e) {
            Error(e.getMessage());
            return 0;
        }
    }

    public AndroidViewComponent getKeyFromValue(Hashtable<AndroidViewComponent, View> hm, Object value) {
        for (AndroidViewComponent o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return o;
            }
        }
        return null;
    }

    public String getKeyFromValue1(Hashtable<String, ObjectAnimator> hm, Object value) {
        for (String o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return o;
            }
        }
        return null;
    }
}
