package com.nit.plantsproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nit.plantsproject.ClassGetSet.DataClass2;
import com.nit.plantsproject.Main.DetailScheduleActivity;
import com.nit.plantsproject.R;

import java.util.ArrayList;
import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleViewHolder> {

    private Context context;
    private List<DataClass2> dataList;

    public ScheduleAdapter(Context context, List<DataClass2> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item2, parent, false);
        return new ScheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        Glide.with(context).load(dataList.get(position).getDataImage()).into(holder.recImg);
        holder.recSchedule.setText(dataList.get(position).getDataSchedule());
        holder.recDay.setText(dataList.get(position).getDataDay());
        holder.recTime.setText(dataList.get(position).getDataTime());
        holder.recGuest.setText(dataList.get(position).getDataTime());

        holder.recCard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailScheduleActivity.class);
                intent.putExtra("Image", dataList.get(holder.getAdapterPosition()).getDataImage());
                intent.putExtra("Schedule", dataList.get(holder.getAdapterPosition()).getDataSchedule());
                intent.putExtra("Day", dataList.get(holder.getAdapterPosition()).getDataDay());
                intent.putExtra("Guest", dataList.get(holder.getAdapterPosition()).getDataGuest());
                intent.putExtra("Time", dataList.get(holder.getAdapterPosition()).getDataTime());
                intent.putExtra("Key", dataList.get(holder.getAdapterPosition()).getKey());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void searchDataList(ArrayList<DataClass2> searchList){
        dataList = searchList;
        notifyDataSetChanged();
    }
}

class ScheduleViewHolder extends RecyclerView.ViewHolder {

    ImageView recImg;
    TextView recSchedule, recDay, recTime, recGuest;
    CardView recCard1;

    public ScheduleViewHolder(@NonNull View itemView) {
        super(itemView);

        recImg = itemView.findViewById(R.id.recImg);
        recCard1 = itemView.findViewById(R.id.recCard1);
        recSchedule = itemView.findViewById(R.id.recSchedule);
        recDay = itemView.findViewById(R.id.recDay);
        recTime = itemView.findViewById(R.id.recTime);
        recGuest = itemView.findViewById(R.id.recGuest);


    }
}

