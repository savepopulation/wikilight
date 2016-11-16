package com.raqun.wiki.ui.main;

import android.support.annotation.NonNull;
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

class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    @NonNull
    private final List<String> mQueries;

    public HistoryAdapter(@NonNull List<String> queries) {
        if (queries == null) {
            throw new IllegalArgumentException("List cannot be null");
        }

        this.mQueries = queries;
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
}
