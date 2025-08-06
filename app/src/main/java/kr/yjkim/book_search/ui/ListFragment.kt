package kr.yjkim.book_search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.yjkim.book_search.R
import kr.yjkim.book_search.adapter.BookListAdapter
import kr.yjkim.book_search.databinding.FragmentListBinding

class ListFragment: Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BookListAdapter
    private val vm: MyViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)

        val toolbarTitleText = getString(R.string.toolbar_list_title, vm.keyword.toString())
        (requireActivity() as MainActivity).setToolbar(toolbarTitleText, true)

        vm.books.observe(viewLifecycleOwner) { books ->
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.recyclerBook
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val bookList = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13") // demo
        adapter = BookListAdapter(bookList)
        recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}