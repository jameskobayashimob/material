package android.test.com.material

import android.app.PendingIntent
import android.app.PendingIntent.FLAG_NO_CREATE
import android.content.Intent
import android.net.Uri
import android.support.v4.graphics.drawable.IconCompat
import androidx.slice.Slice
import androidx.slice.SliceProvider
import androidx.slice.builders.ListBuilder
import androidx.slice.builders.SliceAction
import androidx.slice.core.SliceHints

class MaterialSliceProvider : SliceProvider() {
    override fun onBindSlice(sliceUri: Uri): Slice {
        return if (sliceUri.path == "/hello") {
            ListBuilder(context, sliceUri, ListBuilder.INFINITY)
                    .addRow {
                        it.setTitleItem(getAction("A"))
                        it.setTitle("setTitle")
                        it.setSubtitle("setSubtitle")
                    }
                    .build()
        } else {
            ListBuilder(context, sliceUri, ListBuilder.INFINITY)
                    .addRow { it.setTitle("URI not found.") }
                    .build()
        }
    }

    override fun onCreateSliceProvider(): Boolean {
        return true
    }

    fun getIntent(): PendingIntent = PendingIntent.getActivity(context, 0, Intent(context, MainActivity::class.java), FLAG_NO_CREATE)

    fun getAction(title: String): SliceAction = SliceAction(
            getIntent(),
            IconCompat.createWithResource(context, R.drawable.ic_dashboard_black_24dp), SliceHints.ICON_IMAGE,
            title)

}