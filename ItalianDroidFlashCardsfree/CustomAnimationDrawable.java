package com.SIT.AbleApps.ItalianDroidFlashCardsfree;

import android.graphics.drawable.AnimationDrawable;
import android.os.SystemClock;

/**
* This class is a hack to simply get an on finish callback for an
* AnimationDrawable.
*
*/
public class CustomAnimationDrawable extends AnimationDrawable {
// We need to keep our own frame count because mCurFrame in AnimationDrawable is private
private int mCurFrameHack = -1;

// This is the Runnable that we will call when the animation finishes
private Runnable mCallback = null;

/*
* We override the run method in order to increment the frame count the same
* way the AnimationDrawable class does. Also, when we are on the last frame we
* schedule our callback to be called after the duration of the last frame.
*/
@Override
public void run() {
super.run();

mCurFrameHack += 1;
if (mCurFrameHack == (getNumberOfFrames() - 1) && mCallback != null) {
scheduleSelf(mCallback, SystemClock.uptimeMillis() + getDuration(mCurFrameHack));
}
}

/*
* We override this method simply to reset the frame count just as is done in
* AnimationDrawable.
*/
@Override
public void unscheduleSelf(Runnable what) {
// TODO Auto-generated method stub
super.unscheduleSelf(what);
mCurFrameHack = -1;
}

public void setOnFinishCallback(Runnable callback) {
mCallback = callback;
}

public Runnable getOnFinishCallback() {
return mCallback;
}

}