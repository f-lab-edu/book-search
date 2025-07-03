package kr.yjkim.book_search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.yjkim.book_search.databinding.ItemBookListBinding

class BookListAdapter(private val bookList: List<String>): RecyclerView.Adapter<BookListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder {
        val binding = ItemBookListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookListViewHolder, position: Int) {}

    override fun getItemCount(): Int = bookList.size
}

class BookListViewHolder(binding: ItemBookListBinding): RecyclerView.ViewHolder(binding.root)