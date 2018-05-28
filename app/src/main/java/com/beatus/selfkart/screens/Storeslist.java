package com.beatus.selfkart.screens;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.beatus.selfkart.R;
import com.beatus.selfkart.adapters.StoreListAdapter;
import com.beatus.selfkart.listeners.RecyclerItemClickListener;
import com.beatus.selfkart.models.StoreAddress;
import com.beatus.selfkart.models.StoreAddressList;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Storeslist extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView recyclerView;
    private List<StoreAddress> storesList;
    private StoreListAdapter mAdapter;
    private CoordinatorLayout coordinatorLayout;
    private LinearLayout mapviewbtn;

    private static String sampleJson = "{   \"results\": [      {         \"address\": \"Near Y Junction, Habeeb Nagar, Moosapet,Kukatpally, Hyderabad\",         \"latitude\": 17.47629,         \"name\": \"DMart\",         \"rating\": \"4\",         \"longitude\": 78.4217      },      {         \"address\": \"5-4/6, Balaji Towers, Mumbai Highway, ChandaNagar, Hyderabad\",         \"latitude\": 17.494537,         \"name\": \"Vijetha Super Market\",         \"rating\": \"4\",         \"longitude\": 78.32163      },      {         \"address\": \"Madhapur, Survey No. 64, Plot No. 20, Sector - 1,HUDA Techno Enclave, HITEC City, Hyderabad\",         \"latitude\": 17.446138,         \"name\": \"Ratnadeep Super Market\",         \"rating\": \"4\",         \"longitude\": 78.38477      },      {         \"address\": \"Survey No. 133 - 140, Sangeet Nagar, MoosapetVillage, Balanagar Mandal, Kukatpally, Hyderabad\",         \"latitude\": 17.480669,         \"name\": \"Metro Cash and Carry\",         \"rating\": \"4\",         \"longitude\": 78.41982      },      {         \"address\": \"Ektha Pearl, Opposite Botanical Gardens, WhiteFields, Kondapur, Hyderabad\",         \"latitude\": 17.4561,         \"name\": \"Ratnadeep\",         \"rating\": \"4\",         \"longitude\": 78.36491      },      {         \"address\": \"KPHB 6th Phase Road, Vidhura Vihar, K P H B Phase6, Kukatpally, Hyderabad\",         \"latitude\": 17.488186,         \"name\": \"Vijetha\",         \"rating\": \"4\",         \"longitude\": 78.38951      },      {         \"address\": \"Plot No 128, Vinayaka RR Dist, Indira Nagar,Gachibowli, Hyderabad\",         \"latitude\": 17.4413,         \"name\": \"Spencer's Neighborhood Store\",         \"rating\": \"3\",         \"longitude\": 78.35905      },      {         \"address\": \"Gangaram, Chanda Nagar, Hyderabad\",         \"latitude\": 17.494667,         \"name\": \"More\",         \"rating\": \"4\",         \"longitude\": 78.32228      },      {         \"address\": \"Plot 401, Vivekananda Nagar Colony Main Road,Chaitanya Nagar, Vivekananda Nagar, Kukatpally, Hyderabad\",         \"latitude\": 17.494942,         \"name\": \"Reliance Fresh\",         \"rating\": \"3\",         \"longitude\": 78.41169      },      {         \"address\": \"Plot No. 14, 15, 19 & 20, Adjacent To BharatPetrol Bunk, Madinaguda, Hyderabad\",         \"latitude\": 17.49476,         \"name\": \"Vijetha Super Market\",         \"rating\": \"3\",         \"longitude\": 78.34204      },      {         \"address\": \"2-91/4, Hitec City Road,, Beside Jayabheri SiliconCounty, Kothaguda, Hyderabad\",         \"latitude\": 17.458231,         \"name\": \"Ghanshyam Super Market\",         \"rating\": \"4\",         \"longitude\": 78.37119      },      {         \"address\": \"Plot No.1, Survey Number. 294, Ground Floor, MainRoad, Nizampet Village, Hyderabad\",         \"latitude\": 17.51716,         \"name\": \"More Super Market\",         \"rating\": \"4\",         \"longitude\": 78.38138      },      {         \"address\": \"Cyber Residency,Indra Nagar,Gachi bowli Village,Serilingampally, Hyderabad\",         \"latitude\": 17.441725,         \"name\": \"Reliance Fresh\",         \"rating\": \"3\",         \"longitude\": 78.35958      },      {         \"address\": \"Plot No. 10 & 15, Dharma Reddy Colony, Near JntuCircle, Kphb Colony, Hyderabad\",         \"latitude\": 17.495802,         \"name\": \"Vijetha Super Market\",         \"rating\": \"3\",         \"longitude\": 78.395454      },      {         \"address\": \"Plot No. HIG 451 & 452, Ground Floor, BhavanaEnclave, JNTU - Hitech City Road, KPHB COLONY, K P H B Phase 6,Kukatpally, Hyderabad\",         \"latitude\": 17.488407,         \"name\": \"More Supermarket\",         \"rating\": \"3\",         \"longitude\": 78.39144      },      {         \"address\": \"Near Huda Trade Center, Old Mumbai Highway,Serilingampally, Hyderabad\",         \"latitude\": 17.488153,         \"name\": \"Vijetha Super Market\",         \"rating\": \"4\",         \"longitude\": 78.31387      },      {         \"address\": \"Satyam Valley, Rajiv Gandhi Nagar Colony, WhisperValley, Hyderabad\",         \"latitude\": 17.527143,         \"name\": \"VIJETA Super Market\",         \"rating\": \"3\",         \"longitude\": 78.3689      },      {         \"address\": \"Road Number 1, Balaji Nagar Colony, VenkatrayaNagar, Nizampet, Hyderabad\",         \"latitude\": 17.515816,         \"name\": \"Food Bazaar\",         \"rating\": \"3\",         \"longitude\": 78.38208      },      {         \"address\": \"DK Enclave, Alluri Seetha Ramaraju Nagar, Miyapur,Hyderabad\",         \"latitude\": 17.505224,         \"name\": \"More Super Market\",         \"rating\": \"3\",         \"longitude\": 78.35656      },      {         \"address\": \"Plot#126&127, Sai Baba Temple Road, PrashanthNagar Colony Kondapur, Prashanth Nagar Colony, Kondapur, Hyderabad\",         \"latitude\": 17.462582,         \"name\": \"KIRANAWALA\",         \"rating\": \"4\",         \"longitude\": 78.36639      }   ]}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storeslist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        recyclerView = (RecyclerView) findViewById(R.id.storeslist);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        storesList = new ArrayList<>();
        final StoreAddressList storeAddresses =  new Gson().fromJson(sampleJson, StoreAddressList.class);
        mAdapter = new StoreListAdapter(this, storeAddresses.getResults());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d("clicked on", position + "");
                Intent intent = new Intent(Storeslist.this, StoreLanding.class);
                intent.putExtra("storename", storeAddresses.getResults().get(position).getName());
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, final int position) {
                Log.d("long clicked on", position + "");

            }
        }));
        recyclerView.setAdapter(mAdapter);

        mapviewbtn = (LinearLayout) findViewById(R.id.mapviewbtn);
        mapviewbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        if(view.getId() == R.id.mapviewbtn)
        {
            Intent intent = new Intent(Storeslist.this, StoreSearch.class);
            startActivity(intent);
        }
    }

}
