package com.example.mda;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RecentCallAdapter extends RecyclerView.Adapter<RecentCallAdapter.RecentViewHolder> {

    private List<CallLogEntity> callLogs;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(CallLogEntity callEntity);
    }

    public RecentCallAdapter(List<CallLogEntity> callLogs, OnItemClickListener listener) {
        this.callLogs = callLogs;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recent_call, parent, false);
        return new RecentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentViewHolder holder, int position) {
        CallLogEntity entity = callLogs.get(position);
        holder.name.setText(entity.name != null ? entity.name : entity.phone);
        holder.time.setText(entity.callTime);
        holder.itemView.setOnClickListener(v -> listener.onItemClick(entity));
    }

    @Override
    public int getItemCount() {
        return callLogs.size();
    }

    public static class RecentViewHolder extends RecyclerView.ViewHolder {
        TextView name, time;

        public RecentViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtName);
            time = itemView.findViewById(R.id.txtTime);
        }
    }
}
