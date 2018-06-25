package android.test.com.material.recycler

//import android.test.com.material.recycler.FeedItem.Companion.VIEW_TYPE_SLICE
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.test.com.material.R
import android.test.com.material.recycler.FeedItem.Companion.VIEW_TYPE_LOCAL
import android.test.com.material.recycler.FeedItem.Companion.VIEW_TYPE_SERVER
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item_card_three.view.body
import kotlinx.android.synthetic.main.list_item_card_three.view.timestamp
import kotlinx.android.synthetic.main.list_item_simple.view.subtitle
import kotlinx.android.synthetic.main.list_item_simple.view.title

class CardAdapter internal constructor(val activity: AppCompatActivity, private val listData: List<FeedItem>, private val itemClickListener: ItemClickListener) : RecyclerView.Adapter<CardAdapter.ViewHolder>() {
    private val inflater = LayoutInflater.from(activity)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
//            VIEW_TYPE_SLICE -> ViewHolder(inflater.inflate(R.layout.list_item_slice, parent, false))
            VIEW_TYPE_SERVER -> ViewHolder(inflater.inflate(R.layout.list_item_card_one, parent, false))
            VIEW_TYPE_LOCAL -> ViewHolder(inflater.inflate(R.layout.list_item_card_three, parent, false))
            else -> ViewHolder(inflater.inflate(R.layout.list_item_simple, parent, false))
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val feedItem = listData[position]
        when (feedItem) {
            is FeedItem.Local -> {
                holder.itemView.body.text = "Something about an event happening goes in here. Assumed two lines available."
                holder.itemView.timestamp.text = "Yesterday at 6:25pm"
            }
            is FeedItem.Server -> {
                holder.itemView.title.text = "Card Title"
                holder.itemView.subtitle.text = "Some longer description text goes here. Secondary line also possible."
            }
//            is FeedItem.Slice -> {
//                feedItem.slice?.let {
//                    holder.itemView.slice_view
//                    holder.itemView.slice_view.slice = it
//                }
//            }
        }
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)

        }

        override fun onClick(view: View) {
            itemClickListener.onItemClick(adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun getItemViewType(position: Int): Int {
        return listData[position].viewType
    }

    internal fun getItem(position: Int): FeedItem {
        return listData[position]
    }

    interface ItemClickListener {
        fun onItemClick(position: Int)
    }
}

