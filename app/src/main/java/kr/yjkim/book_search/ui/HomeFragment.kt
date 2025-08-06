package kr.yjkim.book_search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import kr.yjkim.book_search.R
import kr.yjkim.book_search.databinding.FragmentHomeBinding
import kr.yjkim.book_search.extension.hideKeyboard

class HomeFragment: Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MyViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tlSearch.editText?.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                v.clearFocus()
                v.hideKeyboard()
                // search
                viewModel.searchKeyword(v.text.toString())
                // navigate layout
                findNavController().navigate(R.id.list)
                true
            } else false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}