package com.google.developers.teapot.paging;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.developers.teapot.data.Tea;

/**
 * A RecyclerView ViewHolder that displays a Tea.
 */
public class TeaViewHolder extends RecyclerView.ViewHolder {

    private TextView mName;
    private TextView mOrigin;
    private TextView mSteepTime;
    private Tea mTea;
    private TeaAdapter.ItemAction itemAction;

    TeaViewHolder(View itemView) {
        super(itemView);
    }

    public Tea getTea() {
        return mTea;
    }

    /**
     * Attach values to views.
     */
    void bindTo(Tea tea) {
        mName.setText(tea.getName());
        mOrigin.setText(tea.getOrigin());
        mSteepTime.setText(tea.getSteepTimeMs().toString());
    }

    public void setClickAction(TeaAdapter.ItemAction itemClickAction) {
        itemAction = itemClickAction;
    }
}
