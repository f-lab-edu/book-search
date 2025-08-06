package kr.yjkim.book_search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.yjkim.book_search.data.schema.BookItem
import kr.yjkim.book_search.databinding.ItemBookListBinding

class BookListAdapter: RecyclerView.Adapter<BookListViewHolder>() {

    private val bookList: MutableList<BookItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder {
        val binding = ItemBookListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookListViewHolder, position: Int) {
        val bookItem = bookList[position]
        holder.bind(bookItem)
    }

    override fun getItemCount(): Int = bookList.size

    fun submitList(list: List<BookItem>) {
        bookList.clear()
        bookList.addAll(list)
        notifyDataSetChanged()
    }
}

class BookListViewHolder(private val binding: ItemBookListBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: BookItem) {
        binding.tvTitle.text = item.title
        binding.tvStc.text = item.contents

        // thumbnail: Glide...
    }
}