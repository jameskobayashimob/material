package android.test.com.material

import android.os.Bundle
import android.support.design.bottomnavigation.LabelVisibilityMode
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.test.com.material.recycler.CardAdapter
import android.test.com.material.recycler.FeedItem
import kotlinx.android.synthetic.main.activity_main.fab_menu
import kotlinx.android.synthetic.main.activity_main.listview
import kotlinx.android.synthetic.main.activity_main.navigation
import kotlinx.android.synthetic.main.activity_main.toolbar

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED
        setSupportActionBar(toolbar)


//        val sliceUri = Uri.parse("content://android.test.com.material/").buildUpon().appendPath("hello").build()
//        val slice = SliceViewManager.getInstance(this).bindSlice(sliceUri)

//        SliceLiveData.fromUri(activity, feedItem.uri).observe((activity), Observer({ sliceResult ->
//            //                    holder.itemView.slice_view.slice = sliceResult
//        }))

        val feed = mutableListOf<FeedItem>()
        for (i in 0..20) {
            feed.add(FeedItem(i))
        }
//        feed.add(FeedItem.Slice(slice))

        val adapter = CardAdapter(this, feed,
                object : CardAdapter.ItemClickListener {
                    override fun onItemClick(position: Int) {

                    }
                })

        listview.layoutManager = LinearLayoutManager(this)
        listview.adapter = adapter
    }

    override fun onBackPressed() {
        if (fab_menu.isOpened()) {
            fab_menu.close(true)
        } else {
            super.onBackPressed()
        }
    }

    private val navigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_test -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}
