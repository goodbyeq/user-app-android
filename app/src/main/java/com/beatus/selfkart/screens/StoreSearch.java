package com.beatus.selfkart.screens;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.beatus.selfkart.R;
import com.beatus.selfkart.locationservices.LocationProvider;
import com.beatus.selfkart.mapsanimations.MapRadar;
import com.beatus.selfkart.mapsanimations.MapRipple;
import com.beatus.selfkart.models.StoreAddress;
import com.beatus.selfkart.models.StoreAddressList;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

public class StoreSearch extends FragmentActivity implements OnMapReadyCallback,LocationProvider.LocationCallback,View.OnClickListener {

    private GoogleMap mMap;
    MapView mMapView;
    private Context mContext;
    private MapRipple mapRipple;
    private MapRadar mapRadar;
    private LocationProvider mLocationProvider;
    private String[] permissionsToAsk={Manifest.permission.ACCESS_NETWORK_STATE,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.READ_PHONE_STATE};
    private int permissionRequestCode=200;
    private Bundle savedState;
    private LinearLayout openstores;

    private static String sampleJson = "{   \"results\": [      {         \"address\": \"Near Y Junction, Habeeb Nagar, Moosapet,Kukatpally, Hyderabad\",         \"latitude\": 17.47629,         \"name\": \"DMart\",         \"rating\": \"4\",         \"longitude\": 78.4217      },      {         \"address\": \"5-4/6, Balaji Towers, Mumbai Highway, ChandaNagar, Hyderabad\",         \"latitude\": 17.494537,         \"name\": \"Vijetha Super Market\",         \"rating\": \"4\",         \"longitude\": 78.32163      },      {         \"address\": \"Madhapur, Survey No. 64, Plot No. 20, Sector - 1,HUDA Techno Enclave, HITEC City, Hyderabad\",         \"latitude\": 17.446138,         \"name\": \"Ratnadeep Super Market\",         \"rating\": \"4\",         \"longitude\": 78.38477      },      {         \"address\": \"Survey No. 133 - 140, Sangeet Nagar, MoosapetVillage, Balanagar Mandal, Kukatpally, Hyderabad\",         \"latitude\": 17.480669,         \"name\": \"Metro Cash and Carry\",         \"rating\": \"4\",         \"longitude\": 78.41982      },      {         \"address\": \"Ektha Pearl, Opposite Botanical Gardens, WhiteFields, Kondapur, Hyderabad\",         \"latitude\": 17.4561,         \"name\": \"Ratnadeep\",         \"rating\": \"4\",         \"longitude\": 78.36491      },      {         \"address\": \"KPHB 6th Phase Road, Vidhura Vihar, K P H B Phase6, Kukatpally, Hyderabad\",         \"latitude\": 17.488186,         \"name\": \"Vijetha\",         \"rating\": \"4\",         \"longitude\": 78.38951      },      {         \"address\": \"Plot No 128, Vinayaka RR Dist, Indira Nagar,Gachibowli, Hyderabad\",         \"latitude\": 17.4413,         \"name\": \"Spencer's Neighborhood Store\",         \"rating\": \"3\",         \"longitude\": 78.35905      },      {         \"address\": \"Gangaram, Chanda Nagar, Hyderabad\",         \"latitude\": 17.494667,         \"name\": \"More\",         \"rating\": \"4\",         \"longitude\": 78.32228      },      {         \"address\": \"Plot 401, Vivekananda Nagar Colony Main Road,Chaitanya Nagar, Vivekananda Nagar, Kukatpally, Hyderabad\",         \"latitude\": 17.494942,         \"name\": \"Reliance Fresh\",         \"rating\": \"3\",         \"longitude\": 78.41169      },      {         \"address\": \"Plot No. 14, 15, 19 & 20, Adjacent To BharatPetrol Bunk, Madinaguda, Hyderabad\",         \"latitude\": 17.49476,         \"name\": \"Vijetha Super Market\",         \"rating\": \"3\",         \"longitude\": 78.34204      },      {         \"address\": \"2-91/4, Hitec City Road,, Beside Jayabheri SiliconCounty, Kothaguda, Hyderabad\",         \"latitude\": 17.458231,         \"name\": \"Ghanshyam Super Market\",         \"rating\": \"4\",         \"longitude\": 78.37119      },      {         \"address\": \"Plot No.1, Survey Number. 294, Ground Floor, MainRoad, Nizampet Village, Hyderabad\",         \"latitude\": 17.51716,         \"name\": \"More Super Market\",         \"rating\": \"4\",         \"longitude\": 78.38138      },      {         \"address\": \"Cyber Residency,Indra Nagar,Gachi bowli Village,Serilingampally, Hyderabad\",         \"latitude\": 17.441725,         \"name\": \"Reliance Fresh\",         \"rating\": \"3\",         \"longitude\": 78.35958      },      {         \"address\": \"Plot No. 10 & 15, Dharma Reddy Colony, Near JntuCircle, Kphb Colony, Hyderabad\",         \"latitude\": 17.495802,         \"name\": \"Vijetha Super Market\",         \"rating\": \"3\",         \"longitude\": 78.395454      },      {         \"address\": \"Plot No. HIG 451 & 452, Ground Floor, BhavanaEnclave, JNTU - Hitech City Road, KPHB COLONY, K P H B Phase 6,Kukatpally, Hyderabad\",         \"latitude\": 17.488407,         \"name\": \"More Supermarket\",         \"rating\": \"3\",         \"longitude\": 78.39144      },      {         \"address\": \"Near Huda Trade Center, Old Mumbai Highway,Serilingampally, Hyderabad\",         \"latitude\": 17.488153,         \"name\": \"Vijetha Super Market\",         \"rating\": \"4\",         \"longitude\": 78.31387      },      {         \"address\": \"Satyam Valley, Rajiv Gandhi Nagar Colony, WhisperValley, Hyderabad\",         \"latitude\": 17.527143,         \"name\": \"VIJETA Super Market\",         \"rating\": \"3\",         \"longitude\": 78.3689      },      {         \"address\": \"Road Number 1, Balaji Nagar Colony, VenkatrayaNagar, Nizampet, Hyderabad\",         \"latitude\": 17.515816,         \"name\": \"Food Bazaar\",         \"rating\": \"3\",         \"longitude\": 78.38208      },      {         \"address\": \"DK Enclave, Alluri Seetha Ramaraju Nagar, Miyapur,Hyderabad\",         \"latitude\": 17.505224,         \"name\": \"More Super Market\",         \"rating\": \"3\",         \"longitude\": 78.35656      },      {         \"address\": \"Plot#126&127, Sai Baba Temple Road, PrashanthNagar Colony Kondapur, Prashanth Nagar Colony, Kondapur, Hyderabad\",         \"latitude\": 17.462582,         \"name\": \"KIRANAWALA\",         \"rating\": \"4\",         \"longitude\": 78.36639      }   ]}";

    @Override
    public void onClick(View view)
    {
        if(view.getId()== R.id.openstores) {
            Intent intent = new Intent(StoreSearch.this,Storeslist.class);
            startActivity(intent);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_search);
        savedState = savedInstanceState;
        openstores = (LinearLayout) findViewById(R.id.openstores);
        openstores.setOnClickListener(this);
        int MyVersion = Build.VERSION.SDK_INT;
        if (MyVersion > Build.VERSION_CODES.LOLLIPOP_MR1) {
            if (!checkIfAlreadyhavePermission()) {
                requestForSpecificPermission();
            }
            else {
                mContext = this;
                // Obtain the SupportMapFragment and get notified when the map is ready to be used.
                mMapView = (MapView) findViewById(R.id.map);
                mMapView.onCreate(savedInstanceState);

                mMapView.onResume();// needed to get the map to display immediately

                try {
                    MapsInitializer.initialize(getApplicationContext());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                mMapView.getMapAsync(this);
            }
        }

    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mLocationProvider = new LocationProvider(getApplicationContext(), this);
        mLocationProvider.connect();
    }

    public void handleNewLocation(Location location) {

        handleMarkerMoment(location);


    }

    private void handleMarkerMoment(Location location)
    {
        double currentLatitude = location.getLatitude();
        double currentLongitude = location.getLongitude();
        LatLng latLng = new LatLng(currentLatitude, currentLongitude);
        Bitmap bmp = BitmapFactory.decodeResource(
                getResources(), R.drawable.image);
        //mMap.addMarker(new MarkerOptions().position(new LatLng(currentLatitude, currentLongitude)).title("Current Location"));
        MarkerOptions options = new MarkerOptions()
                .position(latLng)
                .title("My location");
        Marker marker = mMap.addMarker(options);
        mMap.getUiSettings().setScrollGesturesEnabled(true);
        mMap.getUiSettings().setAllGesturesEnabled(true);
        marker.setIcon(BitmapDescriptorFactory.fromBitmap(bmp));
        // mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.mapstyle_retro));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 14));
        mapRipple = new MapRipple(mMap, latLng, mContext);

        mapRipple.withNumberOfRipples(3);
        mapRipple.withFillColor(getResources().getColor(R.color.colorAccent));
        mapRipple.withStrokeColor(getResources().getColor(R.color.colorAccent));
        mapRipple.withStrokewidth(4);      // 10dp
        mapRipple.withDistance(2000);      // 2000 metres radius
        mapRipple.withRippleDuration(9000);    //12000ms
        mapRipple.withTransparency(0.7f);
        mapRipple.startRippleMapAnimation();

        StoreAddressList storeAddresses =  new Gson().fromJson(sampleJson, StoreAddressList.class);
        Log.e("check",storeAddresses.toString());
        bmp = BitmapFactory.decodeResource(
                getResources(), R.drawable.cart);

        for(StoreAddress store : storeAddresses.getResults())
        {
            latLng = new LatLng(store.getLatitude(), store.getLongitude());

            //mMap.addMarker(new MarkerOptions().position(new LatLng(currentLatitude, currentLongitude)).title("Current Location"));
            options = new MarkerOptions()
                    .position(latLng)
                    .title(store.getName());
            marker = mMap.addMarker(options);
            marker.setIcon(BitmapDescriptorFactory.fromBitmap(bmp));
        }



    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults){

        switch(permsRequestCode){

            case 200:

                boolean accepted = grantResults[0]== PackageManager.PERMISSION_GRANTED;
                if(accepted)
                {
                    goIntoApp(mContext);
                }


                break;

        }

    }

    private void goIntoApp(Context context) {
        mContext = context;
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mMapView = (MapView) findViewById(R.id.map);
        mMapView.onCreate(savedState);

        mMapView.onResume();// needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(this);
    }

    private void requestForSpecificPermission() {
        ActivityCompat.requestPermissions(this, permissionsToAsk, permissionRequestCode);
    }

    private boolean checkIfAlreadyhavePermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }
}