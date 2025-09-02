package kr.yjkim.book_search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import kr.yjkim.book_search.data.schema.BookItem
import kr.yjkim.book_search.databinding.ItemBookListBinding

class BookListAdapter(
    val onDetailButtonClickHandler: () -> Unit,
): ListAdapter<BookItem, BookListViewHolder>(BookDiffCallback()) {

    private var selectedPosition: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder {
        val binding = ItemBookListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookListViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.onSelected(position == selectedPosition)

        holder.binding.root.setOnClickListener {
            // required library: androidx.recyclerview:recyclerview
            val currentPosition = holder.bindingAdapterPosition
            if (currentPosition == RecyclerView.NO_POSITION) return@setOnClickListener

            if (selectedPosition == currentPosition) {
                val prevSelectedPosition = selectedPosition
                selectedPosition = -1
                notifyItemChanged(prevSelectedPosition)
            } else {
                val prevSelectedPosition = selectedPosition
                selectedPosition = currentPosition
                if (prevSelectedPosition != -1) notifyItemChanged(prevSelectedPosition)
                notifyItemChanged(currentPosition)
            }
        }

        holder.binding.btnDetail.setOnClickListener { onDetailButtonClickHandler() }
    }
}

class BookListViewHolder(val binding: ItemBookListBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: BookItem) {
        binding.tvTitle.text = item.title
        binding.tvStc.text = item.contents
        binding.img.load(item.thumbnail)
    }

    fun onSelected(isSelected: Boolean) {
        binding.laySelect.isInvisible = !isSelected
    }
}

class BookDiffCallback: DiffUtil.ItemCallback<BookItem>() {
    override fun areItemsTheSame(oldItem: BookItem, newItem: BookItem): Boolean {
        return oldItem.isbn == newItem.isbn
    }

    override fun areContentsTheSame(oldItem: BookItem, newItem: BookItem): Boolean {
        return oldItem == newItem
    }
}