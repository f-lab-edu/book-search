package kr.yjkim.book_search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.yjkim.book_search.databinding.ItemBookListBinding

class ListFragmentAdapter(private val bookList: List<String>): RecyclerView.Adapter<ListFragmentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListFragmentViewHolder {
        val binding = ItemBookListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListFragmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListFragmentViewHolder, position: Int) {}

    override fun getItemCount(): Int = bookList.size
}

class ListFragmentViewHolder(binding: ItemBookListBinding): RecyclerView.ViewHolder(binding.root)