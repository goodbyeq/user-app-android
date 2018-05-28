package com.beatus.selfkart.adapters;

/**
 * Created by harikrupa on 20-03-2018.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beatus.selfkart.R;
import com.beatus.selfkart.models.Item;

import java.util.List;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.MyViewHolder> {
    private Context context;
    private List<Item> cartList;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name, description, offersnotes;
        public ImageView arrow;
        public LinearLayout offerbtn;
        public Button decrement,increment;
        public TextView display;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            description = view.findViewById(R.id.description);
            offersnotes = view.findViewById(R.id.offersnotes);
            arrow = view.findViewById(R.id.arrow);
            offerbtn = view.findViewById(R.id.offerbtn);
            offerbtn.setOnClickListener(this);
            decrement= view.findViewById(R.id.decrement);
            decrement.setOnClickListener(this);
            increment= view.findViewById(R.id.increment);
            increment.setOnClickListener(this);
            display = view.findViewById(R.id.display);
        }
        @Override
        public void onClick(View view) {
            if(view.getId()==R.id.offerbtn)
            {
                Log.e("visibility", offersnotes.getVisibility()+"");
                if(offersnotes.getVisibility() == View.VISIBLE) {
                    offersnotes.setVisibility(View.GONE);
                    arrow.setImageResource(R.drawable.uparrow);
                }
                else
                {
                    offersnotes.setVisibility(View.VISIBLE);
                    arrow.setImageResource(R.drawable.downarrow);
                }
            }
            else if(view.getId() == R.id.increment) {
                int value = Integer.parseInt(display.getText().toString());
                display.setText(value+1);
            }
            else if(view.getId() == R.id.decrement) {
                int value = Integer.parseInt(display.getText().toString());
                if(value != 1){
                    display.setText(value-1);
                }
            }
        }
    }



    public CartListAdapter(Context context, List<Item> cartList) {
        this.context = context;
        this.cartList = cartList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Item item = cartList.get(position);
        holder.name.setText(item.getName());
        holder.description.setText(item.getDescription());
        holder.offerbtn.setVisibility(View.GONE);
        holder.offersnotes.setVisibility(View.GONE);
        if(item.getOffers() != null && item.getOffers().size() > 0)
        {
            holder.offerbtn.setVisibility(View.VISIBLE);
            holder.offersnotes.setVisibility(View.VISIBLE);
            String offerNotes = "";
            for(String str: item.getOffers())
            {
                offerNotes = offerNotes + str +"<br />";
            }
            Log.e("Offer notes", offerNotes);
            holder.offersnotes.setText(offerNotes);



        }


    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public void removeItem(int position) {
        cartList.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    public void restoreItem(Item item, int position) {
        cartList.add(position, item);
        // notify item added by position
        notifyItemInserted(position);
    }
}
