package kr.yjkim.book_search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil3.load
import dagger.hilt.android.AndroidEntryPoint
import kr.yjkim.book_search.databinding.FragmentInfoBinding

@AndroidEntryPoint
class InfoFragment: Fragment() {

    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!

    private val args: InfoFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bookItem = args.bookItem
        val toolbarTitleText = bookItem.title
        (requireActivity() as MainActivity).setToolbar(toolbarTitleText, true)
        binding.bookThumbnail.load(bookItem.thumbnail)
        binding.bookSentence.text = bookItem.contents
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}