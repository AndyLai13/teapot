package com.google.developers.teapot.paging;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.google.developers.teapot.R;
import com.google.developers.teapot.data.Tea;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Implementation of an Paging adapter that shows list of Teas.
 */
public class TeaAdapter extends PagedListAdapter<Tea, TeaViewHolder> {

    private ItemAction mItemOnClickAction;
    private LiveData<PagedList<Tea>> mTeaList;

    public TeaAdapter(LiveData<PagedList<Tea>> teaList) {
        super(DIFF_CALLBACK);
        mTeaList = teaList;
    }

    public interface ItemAction {
        void onClick(String teaName);
    }

    public void setItemOnClickAction(ItemAction itemOnClickAction) {
        mItemOnClickAction = itemOnClickAction;
    }

    @NonNull
    @Override
    public TeaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new TeaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeaViewHolder holder, int position) {
        holder.bindTo(Objects.requireNonNull(
                Objects.requireNonNull(mTeaList.getValue()).get(position)));
    }

    private static final DiffUtil.ItemCallback<Tea> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Tea>() {
                @Override
                public boolean areItemsTheSame(@NonNull Tea oldItem, @NonNull Tea newItem) {
                    return oldItem.getName().equals(newItem.getName());
                }

                @Override
                public boolean areContentsTheSame(@NonNull Tea oldItem, @NonNull Tea newItem) {
                    return oldItem == newItem;
                }
            };
}
