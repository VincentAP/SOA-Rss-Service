package com.nakos.soa_uas_vincentardyanputra_2101658344.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.paris.extensions.layoutMarginTopDp
import com.airbnb.paris.extensions.style
import com.airbnb.paris.extensions.viewGroupStyle
import com.nakos.soa_uas_vincentardyanputra_2101658344.DateFormatter
import com.nakos.soa_uas_vincentardyanputra_2101658344.databinding.RecyclerItemBinding
import com.nakos.soa_uas_vincentardyanputra_2101658344.model.News24ItemList

class News24Adapter : ListAdapter<News24ItemList, News24Adapter.News24ViewHolder>(DIFF_CALLBACK) {

    private var listener: ((link: String) -> Unit)? = null

    fun setNews24Listener(listener: (String) -> Unit) = apply {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): News24ViewHolder {
        return News24ViewHolder(create(parent))
    }

    override fun onBindViewHolder(holder: News24ViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int = position

    inner class News24ViewHolder(
        private val itemBinding: RecyclerItemBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: News24ItemList, position: Int) {
            itemBinding.apply {
                constraintRecyclerItemWrapper.apply {
                    if (position == 0) {
                        style(viewGroupStyle {
                            layoutMarginTopDp(10)
                        })
                    }

                    setOnClickListener {
                        item.link?.let { lnk ->
                            listener?.invoke(lnk)
                        }
                    }
                }
                textTitle.text = item.title?.substring(item.title?.indexOf("|")?.plus(2) ?: 0)
                textDescription.text = item.description
                textPubDate.text = DateFormatter.pubDateFormat(item.pubDate ?: "")
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<News24ItemList>() {
            override fun areItemsTheSame(
                oldItem: News24ItemList,
                newItem: News24ItemList
            ): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(
                oldItem: News24ItemList,
                newItem: News24ItemList
            ): Boolean =
                oldItem == newItem
        }

        @JvmStatic
        fun create(parent: ViewGroup): RecyclerItemBinding {
            return RecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        }
    }
}