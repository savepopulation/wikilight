package com.raqun.wiki.query;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.raqun.wiki.Constants;
import com.raqun.wiki.R;
import com.raqun.wiki.data.HistoryItem;
import com.raqun.wiki.utils.DateUtil;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by tyln on 16.11.2016.
 */

class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    @NonNull
    private final List<HistoryItem> mHistoryItems;

    @Nullable
    private final OnItemClickListener mItemClickListener;

    HistoryAdapter(@NonNull List<HistoryItem> historyItems, @Nullable OnItemClickListener itemClickListener) {
        if (historyItems == null) {
            throw new IllegalArgumentException("List cannot be null");
        }

        this.mHistoryItems = historyItems;
        this.mItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final HistoryItem historyItem = mHistoryItems.get(position);
        holder.mTextViewQuery.setText(historyItem.getQuery());
        holder.mTextViewDate.setText(DateUtil.convertTime(historyItem.getCreateDate()));
        if (mItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClicked(historyItem.getQuery());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mHistoryItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTextViewQuery;
        private final TextView mTextViewDate;

        ViewHolder(View itemView) {
            super(itemView);
            mTextViewQuery = (TextView) itemView.findViewById(R.id.textview_query);
            mTextViewDate = (TextView) itemView.findViewById(R.id.textview_date);
        }
    }

    interface OnItemClickListener {
        void onItemClicked(@Nullable String query);
    }
}
