package com.nbehary.fpsmonitor;

import android.util.Log;
import android.view.Choreographer;

/**
 * Created by nate on 9/7/15.
 */
public class FPSMonitor {

    static final String TAG = "FPSMonitor";
    static final long FIRST_FRAME = -1;

    Choreographer mChoreographer;
    Choreographer.FrameCallback mCallback;
    long mLastFrameTime;

    public FPSMonitor(){
        mLastFrameTime = FIRST_FRAME;
        mCallback = new Choreographer.FrameCallback() {
            @Override
            public void doFrame(long frameTimeNanos) {
                long frameTimeMS =frameTimeNanos * 1000;
                if (mLastFrameTime == FIRST_FRAME) {
                    mLastFrameTime = frameTimeMS;
                    mChoreographer.postFrameCallback(mCallback);
                    return;
                }
                long msDelay = frameTimeMS = mLastFrameTime;
                mLastFrameTime = frameTimeMS;
                collectDelay(msDelay);
            }
        };
    }


    private void collectDelay(long delay){
        Log.d(TAG, String.format("Delay: %d", delay));
    }

}
