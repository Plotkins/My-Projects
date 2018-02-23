/*package com.example.george.app2;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class MapsActivity extends FragmentActivity implements LocationListener, OnMapReadyCallback {

    private GoogleMap mMap;

    private GPSTracker gpsTracker;
    private Location mLocation;
    double latitude, longitude;
    LocationManager locationManager;
    //start of magic
    private String getMapsApiDirectionsUrl(LatLng origin, LatLng dest, String travelMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;


        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&" + travelMode;


        return url;

    }


    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onLocationChanged(Location location) {
        latitude=location.getLatitude();
        longitude=location.getLongitude();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {
        Toast.makeText(this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    private class ReadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {
            // TODO Auto-generated method stub
            String data = "";
            try {
                MapHttpConnection http = new MapHttpConnection();
                data = http.readUr(url[0]);


            } catch (Exception e) {
                // TODO: handle exception
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            new ParserTask().execute(result);
            new ScoreCalculator().execute(result);
        }

    }

    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {
            // TODO Auto-generated method stub
            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;
            try {
                jObject = new JSONObject(jsonData[0]);
                PathJSONParser parser = new PathJSONParser();
                routes = parser.parse(jObject);


            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> routes) {
            ArrayList<LatLng> points = null;
            PolylineOptions polyLineOptions = null;

            // traversing through routes
            for (int i = 0; i < routes.size(); i++) {
                points = new ArrayList<LatLng>();
                polyLineOptions = new PolylineOptions();
                List<HashMap<String, String>> path = routes.get(i);

                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                polyLineOptions.addAll(points);
                polyLineOptions.width(4);
                polyLineOptions.color(Color.BLUE);
            }

            mMap.addPolyline(polyLineOptions);

        }
    }

    private class ScoreCalculator extends AsyncTask<String, Void, Integer> {

        @Override
        protected Integer doInBackground(String... jsonData) {
            JSONObject jObject;
            int score = 0;
            try {
                jObject = new JSONObject(jsonData[0]);
                JSONArray jRoutes = null;
                JSONArray jLegs = null;
                JSONArray jSteps = null;
                jRoutes = jObject.getJSONArray("routes");
                for (int i = 0; i < jRoutes.length(); i++) {
                    jLegs = ((JSONObject) jRoutes.get(i)).getJSONArray("legs");
                    for (int j = 0; j < jLegs.length(); j++) {
                        jSteps = ((JSONObject) jLegs.get(j)).getJSONArray("steps");
                        for (int k = 0; k < jSteps.length(); k++) {
                            String travel_mode = "";
                            travel_mode = (String) ((JSONObject) jSteps.get(k)).get("travel_mode");
                            Log.d("mode: ", travel_mode + "\n");
                            int current_distance = 0;
                            current_distance = (int) ((JSONObject) ((JSONObject) jSteps.get(k)).get("distance")).getInt("value");
                            Log.d("distance: ", current_distance + "\n");
                            if (Objects.equals(travel_mode, "WALKING")) {
                                score = score + current_distance * 3;
                                Log.d("score: ", score + "\n");
                            } else if (Objects.equals(travel_mode, "TRANSIT")) {
                                score = score + current_distance * 1;
                                Log.d("score: ", score + "\n");
                            } else if (Objects.equals(travel_mode, "          ")) {
                                score = score + current_distance * 2;
                            }
                        }

                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return score;
        }

        @Override
        protected void onPostExecute(Integer result) {
            EditText pointsEdit = (EditText) findViewById(R.id.pointsEdit);
            pointsEdit.setText(result + "");
        }

    }


    //end of magic
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);

        LatLng start=new LatLng(46.7728593,23.5892915);
        LatLng end=new LatLng(46.7712432,23.6258433);
        String mode=new String();
        mode="mode=transit";
        String url = getMapsApiDirectionsUrl(start, end, mode);
        ReadTask downloadTask = new ReadTask();
        // Start downloading json data from Google Directions API
        downloadTask.execute(url);
        getLocation();
        LatLng current=new LatLng(longitude,latitude);
        Log.d("long",longitude+"/n");
        Log.d("lat",latitude+"/n");
        mMap.addMarker(new MarkerOptions().position(start).title("You start from here."));
        mMap.addMarker(new MarkerOptions().position(current).title("You are here."));
        mMap.addMarker(new MarkerOptions().position(end).title("Your destination."));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(start, 12));
    }
}
*/
package com.example.george.app2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


public class MapsActivity extends FragmentActivity implements GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener, OnMapReadyCallback {

    private GoogleMap mMap;
    LatLng destination;

    //start of magic
    private String getMapsApiDirectionsUrl(LatLng origin, LatLng dest, String travelMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;


        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&" + travelMode;


        return url;

    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {

    }

    private class ReadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {
            // TODO Auto-generated method stub
            String data = "";
            try {
                MapHttpConnection http = new MapHttpConnection();
                data = http.readUr(url[0]);


            } catch (Exception e) {
                // TODO: handle exception
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            new ParserTask().execute(result);
            new ScoreCalculator().execute(result);
        }

    }

    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {
            // TODO Auto-generated method stub
            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;
            try {
                jObject = new JSONObject(jsonData[0]);
                PathJSONParser parser = new PathJSONParser();
                routes = parser.parse(jObject);


            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> routes) {
            ArrayList<LatLng> points = null;
            PolylineOptions polyLineOptions = null;

            // traversing through routes
            for (int i = 0; i < routes.size(); i++) {
                points = new ArrayList<LatLng>();
                polyLineOptions = new PolylineOptions();
                List<HashMap<String, String>> path = routes.get(i);

                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                polyLineOptions.addAll(points);
                polyLineOptions.width(4);
                polyLineOptions.color(Color.BLUE);
            }

            mMap.addPolyline(polyLineOptions);

        }
    }

    private class ScoreCalculator extends AsyncTask<String, Void, Integer> {

        @Override
        protected Integer doInBackground(String... jsonData) {
            JSONObject jObject;
            int score = 0;
            try {
                jObject = new JSONObject(jsonData[0]);
                JSONArray jRoutes = null;
                JSONArray jLegs = null;
                JSONArray jSteps = null;
                jRoutes = jObject.getJSONArray("routes");
                for (int i = 0; i < jRoutes.length(); i++) {
                    jLegs = ((JSONObject) jRoutes.get(i)).getJSONArray("legs");
                    for (int j = 0; j < jLegs.length(); j++) {
                        jSteps = ((JSONObject) jLegs.get(j)).getJSONArray("steps");
                        for (int k = 0; k < jSteps.length(); k++) {
                            String travel_mode = "";
                            travel_mode = (String) ((JSONObject) jSteps.get(k)).get("travel_mode");
                            Log.d("mode: ", travel_mode + "\n");
                            int current_distance = 0;
                            current_distance = (int) ((JSONObject) ((JSONObject) jSteps.get(k)).get("distance")).getInt("value");
                            Log.d("distance: ", current_distance + "\n");
                            if (Objects.equals(travel_mode, "WALKING")) {
                                score = score + current_distance * 3;
                                Log.d("score: ", score + "\n");
                            } else if (Objects.equals(travel_mode, "TRANSIT")) {
                                score = score + current_distance * 1;
                                Log.d("score: ", score + "\n");
                            } else if (Objects.equals(travel_mode, "          ")) {
                                score = score + current_distance * 2;
                            }
                        }

                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return score;
        }

        @Override
        protected void onPostExecute(Integer result) {
            EditText pointsEdit = (EditText) findViewById(R.id.pointsEdit);
            pointsEdit.setText(result + "");
        }

    }


    //end of magic
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void onClick(View v){
        if(v.getId()==R.id.searchButton){
            EditText locationEdit=(EditText)findViewById(R.id.locationEdit);
            EditText modeEdit=(EditText)findViewById(R.id.modeEdit);
            String location=locationEdit.getText().toString();
            String mode=modeEdit.getText().toString();
            //if(Objects.equals(mode,"TRANSIT")|| Objects.equals(mode,"WALKING")|| Objects.equals(mode,"DRIVING"))
                mode="mode="+mode;

            List<Address> addressList=null;
            MarkerOptions mo=new MarkerOptions();
            if(!location.equals("")){
                Geocoder geocoder=new Geocoder(this);
                try {
                    addressList=geocoder.getFromLocationName(location,5);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Address myAddress=addressList.get(0);
                destination=new LatLng(myAddress.getLatitude(),myAddress.getLongitude());
                LatLng start=new LatLng(46.7730826,23.5897311);
                String url = getMapsApiDirectionsUrl(start, destination, mode);
                ReadTask downloadTask = new ReadTask();
                // Start downloading json data from Google Directions API
                downloadTask.execute(url);
                mMap.addMarker(new MarkerOptions().position(start).title("You start from here."));
                mMap.addMarker(new MarkerOptions().position(destination).title("Your destination is here."));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(start,12));
            }

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        /*if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);
*/
        /*Button searchButton=(Button)findViewById(R.id.searchButton);
        LatLng start=new LatLng(46.771699,23.592665);
        LatLng end=new LatLng(46.7712432,23.6258433);
        String mode=new String();
        mode="mode=transit";
        String url = getMapsApiDirectionsUrl(start, end, mode);
        ReadTask downloadTask = new ReadTask();
        // Start downloading json data from Google Directions API
        downloadTask.execute(url);
        mMap.addMarker(new MarkerOptions().position(start).title("You start from here."));
        mMap.addMarker(new MarkerOptions().position(end).title("Your destination is here."));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(start,12));*/

    }
}
