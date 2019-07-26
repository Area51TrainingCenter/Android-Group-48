package com.area51.clasegooglemaps;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class MapsActivity extends FragmentActivity
        implements OnMapReadyCallback, EasyPermissions.PermissionCallbacks {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private final int RC_LOCATION = 123;

    @AfterPermissionGranted(RC_LOCATION)
    private void methodRequireLocationPermission() {
        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION};
        if (EasyPermissions.hasPermissions(this, perms)) {
            // Already have permission, do the thing
            // ...

            GPSTracker gpsTracker = new GPSTracker(MapsActivity.this);

            //https://anotepad.com/notes/hnjwp5
            LatLng sydney = new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude());

            Geocoder geocoder = new Geocoder(this);
            try {
                List<Address> lista =
                        geocoder.getFromLocation(
                                sydney.latitude, sydney.longitude, 1);

                if (lista != null &&
                        lista.size() > 0) {
                    String direccion = lista.get(0).getAddressLine(0);

                    mMap.addMarker(new MarkerOptions()
                            .position(sydney)
                            .icon(BitmapDescriptorFactory
                                    .fromResource(R.drawable.ic_marker_png))
                            .title(direccion)
                    .snippet("hola"));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15f));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this,
                    getString(R.string.location_rationale),
                    RC_LOCATION, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults);
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

        // Add a marker in Sydney and move the camera
        methodRequireLocationPermission();

    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }
}
