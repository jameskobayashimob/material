package android.test.com.material.recycler

import androidx.slice.Slice

sealed class FeedItem(val viewType: Int) {
    companion object {
        const val VIEW_TYPE_LOCAL = 0
        const val VIEW_TYPE_SERVER = 1
        const val VIEW_TYPE_SLICE = 2
    }

    data class Local(val id: String) : FeedItem(VIEW_TYPE_LOCAL)
    data class Server(val id: String) : FeedItem(VIEW_TYPE_SERVER)
    data class Slice(val slice: androidx.slice.Slice?) : FeedItem(VIEW_TYPE_SLICE)
}