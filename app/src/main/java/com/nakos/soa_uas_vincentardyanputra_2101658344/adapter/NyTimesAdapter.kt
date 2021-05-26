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
import com.nakos.soa_uas_vincentardyanputra_2101658344.model.NyTimesItemList

class NyTimesAdapter :
    ListAdapter<NyTimesItemList, NyTimesAdapter.NyTimesViewHolder>(DIFF_CALLBACK) {

    private var listener: ((link: String) -> Unit)? = null

    fun setNyTimesListener(listener: (String) -> Unit) = apply {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NyTimesViewHolder {
        return NyTimesViewHolder(create(parent))
    }

    override fun onBindViewHolder(holder: NyTimesViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int = position

    inner class NyTimesViewHolder(
        private val itemBinding: RecyclerItemBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: NyTimesItemList, position: Int) {
            itemBinding.apply {
                constraintRecyclerItemWrapper.apply {
                    if (position == 0) {
                        style(viewGroupStyle {
                            layoutMarginTopDp(10)
                        })
                    }

                    setOnClickListener {
                        item.links?.get(0)?.link?.let { lnk ->
                            listener?.invoke(lnk)
                        }
                    }
                }

                textTitle.text = item.title?.substring(item.title?.indexOf("|")?.plus(2) ?: 0)
                textDescription.text = item.description?.get(0)?.description
                textPubDate.text = DateFormatter.pubDateFormat(item.pubDate ?: "")
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NyTimesItemList>() {
            override fun areItemsTheSame(
                oldItem: NyTimesItemList,
                newItem: NyTimesItemList
            ): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(
                oldItem: NyTimesItemList,
                newItem: NyTimesItemList
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