package kr.yjkim.book_search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil3.load
import kr.yjkim.book_search.data.BookSearchRepository
import kr.yjkim.book_search.data.network.RetrofitClient.moshi
import kr.yjkim.book_search.data.schema.BookItem
import kr.yjkim.book_search.databinding.FragmentInfoBinding

class InfoFragment: Fragment() {

    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!

    private val vm: InfoViewModel by viewModels {
        InfoViewModel.create(BookSearchRepository)
    }
    private val args: InfoFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val moshiAdapter = moshi.adapter(BookItem::class.java)
        val bookItem = moshiAdapter.fromJson(args.bookItemJson)

        vm.bookItem.observe(viewLifecycleOwner) { bookItem: BookItem ->
            val toolbarTitleText = bookItem.title
            (requireActivity() as MainActivity).setToolbar(toolbarTitleText, true)
            binding.bookThumbnail.load(bookItem.thumbnail)
            binding.bookSentence.text = bookItem.contents
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}