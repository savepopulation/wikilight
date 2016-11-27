package com.raqun.wiki.query;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.raqun.wiki.R;

import java.util.List;

/**
 * Created by tyln on 16.11.2016.
 */

class QueryAdapter extends RecyclerView.Adapter<QueryAdapter.ViewHolder> {
    @NonNull
    private final List<String> mQueries;

    @Nullable
    private final OnItemClickListener mItemClickListener;

    QueryAdapter(@NonNull List<String> queries, @Nullable OnItemClickListener itemClickListener) {
        if (queries == null) {
            throw new IllegalArgumentException("List cannot be null");
        }

        this.mQueries = queries;
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
        final String query = mQueries.get(position);
        holder.mTextViewQeury.setText(query);
        if (mItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClicked(query);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mQueries.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTextViewQeury;

        ViewHolder(View itemView) {
            super(itemView);
            mTextViewQeury = (TextView) itemView.findViewById(R.id.textview_query);
        }
    }

    interface OnItemClickListener {
        void onItemClicked(@Nullable String query);
    }
}
