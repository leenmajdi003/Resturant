package com.example.resturant.Activity.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.resturant.Activity.DetailActivity;
import com.example.resturant.Activity.Domain.Foods;
import com.example.resturant.R;

import java.util.ArrayList;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.ViewHolder> {

    private final ArrayList<Foods> items;
    private final Context context;

    public FoodListAdapter(Context context, ArrayList<Foods> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the correct layout for each food item
        View inflate = LayoutInflater.from(context).inflate(R.layout.viewholder_listfood, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Foods foodItem = items.get(position);
        holder.titleText.setText(foodItem.getTitle());
        holder.timeText.setText(foodItem.getTimeValue() + " min");
        holder.priceText.setText(" $" + foodItem.getPrice());
        holder.rateTxt.setText(String.valueOf(foodItem.getStar()));

        Glide.with(context)
                .load(foodItem.getImagePath())
                .transform(new CenterCrop(), new RoundedCorners(30))
                .into(holder.pic);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("object", items.get(position));
                context.startActivity(intent);  // Start the DetailActivity
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleText, priceText, rateTxt, timeText;
        ImageView pic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.titleText1);
            priceText = itemView.findViewById(R.id.priceText1);
            rateTxt = itemView.findViewById(R.id.rateTxt1);
            timeText = itemView.findViewById(R.id.timeText1);
            pic = itemView.findViewById(R.id.img);
        }
    }
}
