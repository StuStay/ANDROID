package com.example.stustay.Ui.Activities
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.stustay.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var editTextSearch: EditText
    private lateinit var btnConfirmer: Button
    private lateinit var mapFragment: SupportMapFragment
    private lateinit var googleMap: GoogleMap
    private lateinit var btnSearch:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainr)

        editTextSearch = findViewById(R.id.editTextSearch)
        btnConfirmer = findViewById(R.id.btnConfirmer)
        btnSearch=findViewById(R.id.btnSearch)



        // Initialize the map fragment
        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        btnSearch.setOnClickListener {
            // Get the location data from the EditText
            val location = editTextSearch.text.toString()

            // Use Geocoder to get the LatLng from the location name
            val latLng = getLatLngFromLocationName(location)

            if (latLng != null) {
                // Show the entered location on the map
                showLocationOnMap(latLng, location)
            }
        }
        btnConfirmer.setOnClickListener {
            val location = editTextSearch.text.toString()
            val intent = Intent(this, SecondeReservation::class.java)
            intent.putExtra("location", location)
            startActivity(intent)
        }
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
    }

    private fun getLatLngFromLocationName(locationName: String): LatLng? {
        val geocoder = Geocoder(this)
        try {
            val addresses: List<Address>? = geocoder.getFromLocationName(locationName, 1)

            if (addresses != null && addresses.isNotEmpty()) {
                val latitude = addresses[0].latitude
                val longitude = addresses[0].longitude
                return LatLng(latitude, longitude)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    private fun showLocationOnMap(latLng: LatLng, location: String) {
        // Clear existing markers on the map
        googleMap.clear()

        // Add a marker for the entered location
        googleMap.addMarker(MarkerOptions().position(latLng).title(location))

        // Move the camera to the entered location
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12f))
    }
}
