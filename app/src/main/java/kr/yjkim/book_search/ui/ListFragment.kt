package kr.yjkim.book_search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kr.yjkim.book_search.R
import kr.yjkim.book_search.adapter.BookListAdapter
import kr.yjkim.book_search.databinding.FragmentListBinding
import kr.yjkim.book_search.util.ResultUiState
import okio.IOException
import retrofit2.HttpException

@AndroidEntryPoint
class ListFragment: Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val vm: ListViewModel by viewModels()
    private val args: ListFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = BookListAdapter { bookItem ->
            val action = ListFragmentDirections.actionListToInfo(bookItem)
            findNavController().navigate(action)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            vm.searchResult.flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collect { uiState ->
                    when (uiState) {
                        is ResultUiState.Success -> {
                            val bookList = uiState.bookList
                            if (bookList.isEmpty()) {
                                binding.errorText.text = getString(R.string.error_no_data)
                                binding.btnTryAgain.visibility = View.INVISIBLE
                                binding.layError.visibility = View.VISIBLE
                            } else {
                                binding.layError.visibility = View.GONE
                                adapter.submitList(bookList)
                            }
                            binding.loadingText.visibility = View.GONE
                        }

                        is ResultUiState.Error -> {
                            binding.errorText.text = when (uiState.exception) {
                                is IOException -> getString(R.string.error_network)
                                is HttpException -> getString(R.string.error_server)
                                else -> getString(R.string.error_unknown)
                            }
                            binding.btnTryAgain.visibility = View.VISIBLE
                            binding.layError.visibility = View.VISIBLE
                            binding.loadingText.visibility = View.GONE
                        }

                        ResultUiState.Loading -> {
                            binding.loadingText.visibility = View.VISIBLE
                        }
                    }
                }
        }

        val toolbarTitleText = getString(R.string.toolbar_list_title, args.keyword)
        (requireActivity() as MainActivity).setToolbar(toolbarTitleText, true)

        val recyclerView = binding.recyclerBook
        val itemDivider = DividerItemDecoration(requireContext(), LinearLayout.VERTICAL)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(itemDivider)
        recyclerView.adapter = adapter

        binding.btnTryAgain.setOnClickListener {
            vm.retry(args.keyword)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}