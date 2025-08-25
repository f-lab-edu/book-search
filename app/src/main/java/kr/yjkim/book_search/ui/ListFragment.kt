package kr.yjkim.book_search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import kr.yjkim.book_search.R
import kr.yjkim.book_search.adapter.BookListAdapter
import kr.yjkim.book_search.data.BookSearchRepository
import kr.yjkim.book_search.databinding.FragmentListBinding
import okio.IOException
import retrofit2.HttpException

class ListFragment: Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val vm: ListViewModel by viewModels {
        ListViewModel.create(BookSearchRepository)
    }
    private val args: ListFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = BookListAdapter()

        vm.searchResult.observe(viewLifecycleOwner) { result ->
            result.fold(
                onSuccess = { bookList ->
                    adapter.submitList(bookList)
                    binding.layError.visibility = View.GONE
                },
                onFailure = { e ->
                    binding.errorText.text = when (e) {
                        is IOException -> getString(R.string.error_network)
                        is HttpException -> getString(R.string.error_server)
                        else -> getString(R.string.error_no_data)
                    }
                    binding.layError.visibility = View.VISIBLE
                })
        }

        val toolbarTitleText = getString(R.string.toolbar_list_title, args.keyword)
        (requireActivity() as MainActivity).setToolbar(toolbarTitleText, true)

        val recyclerView = binding.recyclerBook
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}