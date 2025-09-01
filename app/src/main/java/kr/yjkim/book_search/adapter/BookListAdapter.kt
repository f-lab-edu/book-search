package kr.yjkim.book_search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil3.SingletonImageLoader
import coil3.request.ImageRequest
import coil3.request.target
import kr.yjkim.book_search.data.schema.BookItem
import kr.yjkim.book_search.databinding.ItemBookListBinding

class BookListAdapter: ListAdapter<BookItem, BookListViewHolder>(BookDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder {
        val binding = ItemBookListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class BookListViewHolder(private val binding: ItemBookListBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: BookItem) {
        binding.tvTitle.text = item.title
        binding.tvStc.text = item.contents

        val context = binding.root.context
        val imageLoader = SingletonImageLoader.get(context)

        val request = ImageRequest.Builder(context)
            .data(item.thumbnail)
            .target(binding.img)
            .build()

        imageLoader.enqueue(request)
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