package android.test.com.material

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_static_maps.*

class StaticMapActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_static_maps)

        val url = "https://maps.googleapis.com/maps/api/staticmap?center=Albany,+NY&zoom=13&scale=1&size=600x300&maptype=roadmap&format=webp&visual_refresh=true&markers=size:mid%7Ccolor:0xff0000%7Clabel:X%7CAlbany,+NY"

        Glide.with(this)
                .load(url)
                .into(static_map_view)
    }
}