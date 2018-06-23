package android.test.com.material

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_static_maps.static_map_view


class StaticMapActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_static_maps)

        val latEiffelTower = "48.858235";
        val lngEiffelTower = "2.294571";
        val url = "https://maps.google.com/maps/api/staticmap?center=$latEiffelTower,$lngEiffelTower&zoom=15&size=2000x800&sensor=false"

        Glide.with(this)
                .load(url)
                .into(static_map_view)


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 131)
        } else {

        }
    }
}