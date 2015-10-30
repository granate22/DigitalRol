package digitalrol.android.com.digitalrol.activities;

/**
 * Created by diego.mazzitelli on 30/07/2015.
 */
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import digitalrol.android.com.digitalrol.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener  {

    private GoogleMap myMap;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;

    @Override
    public void onStart()
    {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop()
    {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onMapReady(GoogleMap map) {

        myMap = map;
        myMap.setMyLocationEnabled(true);
        myMap.addMarker(addWaypoint(-37.983167,-57.533333,"Banco 5 Chalets"));
        myMap.addMarker(addWaypoint(-38.240000,-57.458333,"Banco Afuera"));
        myMap.addMarker(addWaypoint(-37.853483,-57.464333,"Banco Bacota"));
        myMap.addMarker(addWaypoint(-37.917933,-57.516517,"Banco Camet"));
        myMap.addMarker(addWaypoint(-37.883600,-57.481500,"Banco Cuarteles"));
        myMap.addMarker(addWaypoint(-38.104167,-57.292500,"Banco del Medio"));
        myMap.addMarker(addWaypoint(-38.006200,-57.522117,"Banco El Caballo"));
        myMap.addMarker(addWaypoint(-37.867817,-57.474983,"Banco Elena"));
        myMap.addMarker(addWaypoint(-38.116117,-57.378500,"Banco Pampita"));
        myMap.addMarker(addWaypoint(-38.129333,-57.254167,"Banco Patria"));
        myMap.addMarker(addWaypoint(-38.130800,-57.495583,"Banco Pescadores"));
        myMap.addMarker(addWaypoint(-38.091950,-57.535283,"Banco Pierino"));
        myMap.addMarker(addWaypoint(-38.114667,-57.535567,"Banco Rescal"));
        myMap.addMarker(addWaypoint(-38.127700,-57.540900,"Banco Restano"));
        myMap.addMarker(addWaypoint(-38.112600,-57.534200,"Banco Restinga Norte"));
        myMap.addMarker(addWaypoint(-38.114150,-57.540200,"Banco Restinga Sur"));
        myMap.addMarker(addWaypoint(-37.866433,-57.468450,"Banco Santa Clara"));
        myMap.addMarker(addWaypoint(-38.127700,-57.530600,"Banco Tierra"));
        myMap.addMarker(addWaypoint(-38.085133,-57.363733,"Banco Tio"));
        myMap.addMarker(addWaypoint(-37.968750,-57.499000,"Banco Tres Cupulas"));
        myMap.addMarker(addWaypoint(-38.037200,-57.522567,"Escollera Norte"));
        myMap.addMarker(addWaypoint(-38.038833,-57.517933,"Escollera Sur"));


    }

    private MarkerOptions addWaypoint(double latitude, double longitude, String title)
    {
        LatLng latLng = new LatLng(latitude,longitude);
        return new MarkerOptions().position(latLng).title(title)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
    }

    private MarkerOptions addRedWaypoint(double latitude, double longitude, String title)
    {
        LatLng latLng = new LatLng(latitude,longitude);
        return new MarkerOptions().position(latLng).title(title)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null && myMap != null) {

            myMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude()), 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude()))      // Sets the center of the map to location user
                    .zoom(10)                   // Sets the zoom
                    .bearing(0)                // Sets the orientation of the camera to east
                    .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            myMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            myMap.addMarker(addRedWaypoint(mLastLocation.getLatitude(), mLastLocation.getLongitude(), "Ubicación actual"));
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
