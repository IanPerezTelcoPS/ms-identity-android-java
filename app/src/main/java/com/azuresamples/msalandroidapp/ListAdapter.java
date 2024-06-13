package com.azuresamples.msalandroidapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import model.FilesItem;
import model.FilesResponseModel;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private FilesResponseModel itemList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(FilesItem item);
    }

    public ListAdapter(FilesResponseModel itemList, OnItemClickListener listener) {
        this.itemList = itemList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);
        return new ListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        FilesItem item = itemList.values.get(position);
        holder.bind(item, listener);
    }

    @Override
    public int getItemCount() {
        return itemList.values.size();
    }

    public void updateFileList(FilesResponseModel newFilesList) {
        itemList = newFilesList;
        notifyDataSetChanged();
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        private ImageView icon;

        public ListViewHolder(@NonNull View view) {
            super(view);
            textView = view.findViewById(R.id.textView);
            icon = view.findViewById(R.id.ivType);
        }

        public void bind(final FilesItem item, final OnItemClickListener listener) {
            textView.setText(item.getName());
            if (item.folder != null) {
                icon.setImageResource(R.drawable.folder_24); // Icon for folder
            } else {
                icon.setImageResource(R.drawable.file_24); // Icon for file
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}

