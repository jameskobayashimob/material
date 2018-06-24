package android.test.com.material

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.bumptech.glide.Glide
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_static_maps.static_map_view
import java.net.URLEncoder


class StaticMapActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_static_maps)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), 131)
        } else {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
            fusedLocationClient.lastLocation.addOnSuccessListener(this::updateImage)
        }
    }

    private fun updateImage(location: Location?) {
        if (location == null) return

        val lat = location.latitude
        val lon = location.longitude

        Log.v("Material", "LOCATION: $lat, $lon")
        Log.v("Material", "VENUE ID: ${URLEncoder.encode("$lat,$lon", "UTF-8")}")
        Log.v("Material", "VENUE LIST USE: ${lat - 0.1f} - ${lat + 0.1f}, ${lon - 0.1f} - ${lon + 0.1f}")

        // "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=$lat,$lon&radius=1500&type=restaurant&key=AIzaSyAKxq0VB47ATC34v4m0EkvpErLfeqWRxzY"
        // "https://maps.googleapis.com/maps/api/staticmap?center=Albany,+NY&zoom=13&scale=1&size=600x300&maptype=roadmap&format=webp&visual_refresh=true&markers=size:mid%7Ccolor:0xff0000%7Clabel:X%7CAlbany,+NY"
        val url = "https://maps.google.com/maps/api/staticmap?center=$lat,$lon&size=1200x800&zoom=15&&sensor=false&format=webp&visual_refresh=true&markers=size:mid%7Ccolor:0xff0000%7Clabel:X%7C$lat,$lon"

        Glide.with(this)
                .load(url)
                .into(static_map_view)
    }
}