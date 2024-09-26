package com.example.resturant.Activity.Adapter;

import android.content.Context;
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
import com.example.resturant.Activity.Domain.Category;
import com.example.resturant.Activity.Domain.Foods;
import com.example.resturant.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewholder> {

    ArrayList<Category> items;
    Context context;

    public CategoryAdapter(ArrayList<Category> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CategoryAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.veiwholder_categoty,parent,false);
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.viewholder holder, int position) {
holder.titleTxt.setText(items.get(position).getName());

switch (position) {
    case 0:
    {
        holder.pic.setBackgroundResource(R.drawable.cart_0_background);
        break;
    }
    case 1:
    {
        holder.pic.setBackgroundResource(R.drawable.cart_1_background);
        break;
    }
    case 2:
    {
        holder.pic.setBackgroundResource(R.drawable.cart_2_background);
        break;
    }
    case 3:
    {
        holder.pic.setBackgroundResource(R.drawable.cart_3_background);
        break;
    }
    case 4:
    {
        holder.pic.setBackgroundResource(R.drawable.cart_4_background);
        break;
    }
    case 5:
    {
        holder.pic.setBackgroundResource(R.drawable.cart_5_background);
        break;
    }
    case 6:
    {
        holder.pic.setBackgroundResource(R.drawable.cart_6_background);
        break;
    }
    case 7:
    {
        holder.pic.setBackgroundResource(R.drawable.cart_7_background);
        break;
    }
}
        int drawableResourceId = context.getResources().getIdentifier(
                items.get(position).getImagePath(),
                "drawable",
                holder.itemView.getContext().getPackageName()
        );

        Glide.with(context)
                .load(drawableResourceId)
                .into(holder.pic);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView titleTxt,PriceTxt,starTxt,TimeTxt;
        ImageView pic;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            titleTxt=itemView.findViewById(R.id.catNameTxt);

            pic=itemView.findViewById(R.id.imgCart);
        }
    }
}
