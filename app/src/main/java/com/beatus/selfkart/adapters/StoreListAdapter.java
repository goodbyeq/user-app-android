package com.beatus.selfkart.adapters;

/**
 * Created by harikrupa on 20-03-2018.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.beatus.selfkart.R;
import com.beatus.selfkart.models.StoreAddress;

import java.util.List;

public class StoreListAdapter extends RecyclerView.Adapter<StoreListAdapter.MyViewHolder> {
    private Context context;
    private List<StoreAddress> storeList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, address, storedistance;
        public ImageView favorite;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            address = view.findViewById(R.id.address);
            storedistance = view.findViewById(R.id.storedistance);
            favorite = view.findViewById(R.id.favorite);
        }
    }


    public StoreListAdapter(Context context, List<StoreAddress> storeList) {
        this.context = context;
        this.storeList = storeList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.store_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final StoreAddress item = storeList.get(position);
        holder.name.setText(item.getName());
        holder.address.setText(item.getAddress());
        holder.storedistance.setText("0.9 KMS");


    }

    @Override
    public int getItemCount() {
        return storeList.size();
    }

    public void removeItem(int position) {
        storeList.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    public void restoreItem(StoreAddress item, int position) {
        storeList.add(position, item);
        // notify item added by position
        notifyItemInserted(position);
    }
}
