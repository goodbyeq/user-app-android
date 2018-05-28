package com.beatus.selfkart.screens;

import android.content.ClipData;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beatus.selfkart.R;
import com.beatus.selfkart.adapters.CartListAdapter;
import com.beatus.selfkart.models.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoreLanding extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private List<Item> cartList;
    private CartListAdapter mAdapter;
    private CoordinatorLayout coordinatorLayout;
    private RelativeLayout scanneditemslist;

    private Button scanItems;
    private ImageView phoneScan;
    private Button CheckOut;

    private LinearLayout noItems;
    private LinearLayout noItemsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Bundle bundle = getIntent().getExtras();
        String storename = bundle.getString("storename");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_store_landing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);


        scanItems = (Button) findViewById(R.id.scanitems);

        phoneScan = (ImageView) findViewById(R.id.phonescan);
        scanItems.setOnClickListener(this);
        phoneScan.setOnClickListener(this);

        scanneditemslist = (RelativeLayout) findViewById(R.id.scanneditemslist);
        scanneditemslist.setOnClickListener(this);

        TextView txtView = (TextView) findViewById(R.id.supermarketname);
        txtView.setText(storename);

        noItems = (LinearLayout) findViewById(R.id.noItems);
        noItemsBtn = (LinearLayout) findViewById(R.id.noItemsBtn);
        noItemsBtn.setOnClickListener(this);

        prepareItemsCart();

    }

    private void prepareItemsCart() {

        cartList = new ArrayList<Item>();
        Item item = new Item(1,"Nature Valley Crunchy Granual Bars","Oats N Honey",2.5,94,new ArrayList<String>());
        cartList.add(item);
        item = new Item(2,"365 Every day value","Organic Italian Salad",16,6,
                new ArrayList<String>(Arrays.asList("Buy 1 get 1 365 every day value, Organic Italian Salad. 10 oz at 50% off. Offer valid till stock lasts")));
        cartList.add(item);
        item = new Item(2,"365 Every day value","Organic Italian Salad",16,6,
                new ArrayList<String>(Arrays.asList("Buy 1 get 1 365 every day value, Organic Italian Salad. 10 oz at 50% off. Offer valid till stock lasts",
                        "Buy 1 get 1 365 every day value, Organic Italian Salad. 10 oz at 50% off. ")));
        cartList.add(item);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.phonescan || view.getId() == R.id.scanitems) {
            scanneditemslist.setVisibility(View.VISIBLE);
            noItems.setVisibility(View.GONE);
            noItemsBtn.setVisibility(View.GONE);
            recyclerView = (RecyclerView) findViewById(R.id.itemlist);
            coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
            mAdapter = new CartListAdapter(this, cartList);

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
            recyclerView.setAdapter(mAdapter);

        }
    }

}
