package com.bibek.audiophile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bibek.audiophile.R;
import com.bibek.audiophile.model.FirstScreenItemModel;


import java.util.ArrayList;

public class FirstScreenItemAdapter extends RecyclerView.Adapter<FirstScreenItemAdapter.FirstScreenItemViewHolder>{


    Context context;
    ArrayList<FirstScreenItemModel> firstScreenItemModelArrayList;
    private RecyclerViewInterface recyclerViewInterface;


    public FirstScreenItemAdapter( RecyclerViewInterface recyclerViewInterface, Context context, ArrayList<FirstScreenItemModel> firstScreenItemModelArrayList) {
        this.recyclerViewInterface = recyclerViewInterface;
        this.context = context;
        this.firstScreenItemModelArrayList = firstScreenItemModelArrayList;
    }

    @NonNull
    @Override
    public FirstScreenItemAdapter.FirstScreenItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_first_screen_item,parent, false);
        return new FirstScreenItemViewHolder(view,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull FirstScreenItemAdapter.FirstScreenItemViewHolder holder, int position) {

        FirstScreenItemModel item = firstScreenItemModelArrayList.get(position);
        holder.ivFirstScreenIcon.setImageResource(item.getFirstScreenItemIconId());
        holder.ivFirstScreenItemTitle.setText(item.getFirstScreenItemTitle());
        holder.ivArrowIcon.setImageResource(item.getFirstScreenArrowIcon());

    }

    @Override
    public int getItemCount() {
        return firstScreenItemModelArrayList.size();
    }

    public class FirstScreenItemViewHolder extends RecyclerView.ViewHolder implements
    View.OnClickListener{
        private ImageView ivFirstScreenIcon;
        private TextView ivFirstScreenItemTitle;
        private ImageView ivArrowIcon;
        RecyclerViewInterface recyclerViewInterface;
        public FirstScreenItemViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            ivFirstScreenIcon = itemView.findViewById(R.id.ivFirstScreenIcon);
            ivFirstScreenItemTitle = itemView.findViewById(R.id.tvFirstScreenItemTitle);
            ivArrowIcon = itemView.findViewById(R.id.ivArrowIcon);
            this.recyclerViewInterface = recyclerViewInterface;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            recyclerViewInterface.onItemClickListener(getAdapterPosition());

        }
    }



}
