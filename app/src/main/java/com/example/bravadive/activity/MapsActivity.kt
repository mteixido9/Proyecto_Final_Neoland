package com.example.bravadive.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.bravadive.App
import com.example.bravadive.R
import com.example.bravadive.Spot
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {


    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, MapsActivity::class.java)
        }
    }

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        (supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment)?.getMapAsync(this)

        iVLogo.setImageResource(R.mipmap.brava_dive_oval)
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
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO){
                val markerList = mutableListOf<Marker>()
                val spotList = App.getDatabase(this@MapsActivity.application).spotDao().getAll()// Leemos spots y añadimos a una lista dsd db.
                Log.w("Carlos", "El tamano de la lista es ${spotList.size}")

                withContext(Dispatchers.Main){
                    //Leemos todos los spots de db, hacemos un for y creamos un marker para cada spot con su LatLng y titulo. Luego lo añadimos a una lista.
                    spotList.forEach {
                        markerList.add(
                            mMap.addMarker(
                                MarkerOptions().position(LatLng(it.latitude.toDouble(), it.longitude.toDouble()))
                                    .title(it.name)
                            )
                        )
                    }

                    //Setting map init preferencies
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(markerList[0].position))
                    mMap.setMinZoomPreference(10.5f)
                    mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE

                    //Setting marker Navegation
                    mMap.setOnMarkerClickListener { marker ->
                        marker.title?.let { markerTitle ->
                            spotList.forEach {
                                if (markerTitle.contentEquals(it.name))
                                    startActivity(DetailActivity.getIntent(this@MapsActivity, it))
                            }
                        }
                        true
                    }

                }
            }
        }
    }
}