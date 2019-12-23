package com.google.developers.teapot.ui.timer;

import android.os.SystemClock;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Schedule a timer count down in milliseconds.
 */
public abstract class SteepTimer {

    private final ScheduledExecutorService mExecutors;
    private long mTimeInFuture;
    private final long mFutureTime;
    private ScheduledFuture mSchedule;

    public SteepTimer(long futureTime) {
        mFutureTime = futureTime;
        mExecutors = Executors.newSingleThreadScheduledExecutor();
    }

    /**
     * Runnable to handle the count down.
     */
    private void run() {
        long timeLeft = mTimeInFuture - SystemClock.elapsedRealtime();
        if (timeLeft <= 0) {
            reset();
        } else {
            onTick(timeLeft);
        }
    }

    /**
     * Start the timer count down until the future time.
     */
    public void start() {
        mTimeInFuture = SystemClock.elapsedRealtime() + mFutureTime;
        if (mSchedule == null) {
            mSchedule = mExecutors.scheduleAtFixedRate(this::run, 0, 1000, TimeUnit.MILLISECONDS);
        }
    }

    /**
     * Rest the schedule count down time.
     */
    public void reset() {
        if (mSchedule != null) {
            mSchedule.cancel(false);
            mSchedule = null;
            onTick(mFutureTime);
        }
    }

    /**
     * Called on every 1000 milliseconds with milliseconds left until the future time.
     *
     * @param millisUntilFinished count down to future time in milliseconds.
     */
    public abstract void onTick(long millisUntilFinished);

}
