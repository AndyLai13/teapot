package com.google.developers.teapot.ui.timer;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.google.developers.teapot.R;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * View that displays minutes in the center of a circular progress bar.
 */
public class SteepProgress extends ProgressBar {

    private TextPaint mTextPaint;
    private String mTimeText = "-:--";
    private Long mMaxSteepTime = 0L;
    private int mMaxProgress = 300;
    private int mFontHeight;
    private int mPositionX;
    private int mPositionY;

    public SteepProgress(Context context, AttributeSet attrs) {
        super(context, attrs, R.attr.progressBarStyle, R.style.SteepRing);

        final Resources res = getResources();
        float GESTURE_THRESHOLD_DP = 76;

        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.density = res.getDisplayMetrics().density;
        mTextPaint.setTextSize((int) GESTURE_THRESHOLD_DP * res.getDisplayMetrics().scaledDensity);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        mFontHeight = (int) (fontMetrics.descent + fontMetrics.ascent);
        setMax(mMaxProgress);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mPositionX = getMeasuredWidth() / 2;
        mPositionY = getMeasuredHeight() / 2 - mFontHeight / 2;
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        canvas.drawText(mTimeText, mPositionX, mPositionY, mTextPaint);
        super.onDraw(canvas);
    }

    /**
     * Set max time steep required to calculate the progress.
     *
     * @param time the max time in milliseconds
     */
    public void setMaxTime(long time) {
        mMaxSteepTime = TimeUnit.MILLISECONDS.toSeconds(time);
        setTime(mMaxSteepTime);
    }

    /**
     * Update time text and progress bar position.
     *
     * @param milliseconds unit time in Milliseconds
     */
    public synchronized void setTime(long milliseconds) {
        long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds) % 60;
        long totalSeconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds);
        long seconds = totalSeconds % 60;

        if (minutes == 0) {
            mTimeText = String.format(Locale.ROOT, "%02d", seconds);
        } else {
            mTimeText = String.format(Locale.ROOT, "%1d:%02d", minutes, seconds);
        }

        long progress = mMaxProgress - ((totalSeconds * mMaxProgress) / mMaxSteepTime);
        setProgress((int) progress);
    }

    /**
     * Get current view time text.
     */
    public String getTime() {
        return mTimeText;
    }

}
