package com.google.developers.teapot.ui.timer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * ViewModel for steep timer activity.
 */
public class TimerViewModel extends ViewModel {

    private final MutableLiveData<Long> mLapseTime = new MutableLiveData<>();
    private SteepTimer mTimer;
    public final long STEEP_TIME_MILLIS = 240000;

    public TimerViewModel() {
    }

    /**
     * Set how long the steep timer should run.
     */
    public void setSteepTimer() {
        if (mTimer == null) {
            mLapseTime.postValue(STEEP_TIME_MILLIS);
            mTimer = new SteepTimer(STEEP_TIME_MILLIS) {
                @Override
                public void onTick(long millisUntilFinished) {
                    mLapseTime.postValue(millisUntilFinished);
                }
            };
        }
    }

    /**
     * Trigger timer to start.
     */
    public void start() {
        if (mTimer != null) {
            mTimer.start();
        }
    }

    /**
     * Reset timer to the beginning.
     */
    public void reset() {
        if (mTimer != null) {
            mTimer.reset();
        }
    }

    public LiveData<Long> getTimer() {
        return mLapseTime;
    }

}
