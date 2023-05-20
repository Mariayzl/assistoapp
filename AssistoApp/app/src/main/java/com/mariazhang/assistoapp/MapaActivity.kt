package com.mariazhang.assistoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapaActivity : AppCompatActivity(), OnMapReadyCallback {


    private lateinit var googleMap : GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapa)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)


    }

    override fun onMapReady(map: GoogleMap?) {

        googleMap = map!!

        val location = LatLng(37.7749, -122.4194) // Coordenadas de San Francisco, CA
        googleMap.addMarker(MarkerOptions().position(location).title("San Francisco"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12f))
    }


}