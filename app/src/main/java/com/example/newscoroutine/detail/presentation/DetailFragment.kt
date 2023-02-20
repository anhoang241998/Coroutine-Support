package com.example.newscoroutine.detail.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.newscoroutine.R
import com.example.newscoroutine.databinding.FragmentDetailsBinding
import com.example.newscoroutine.home.presentation.CatsFactItemViewsState
import com.example.newscoroutine.home.presentation.CatsListViewState
import com.google.gson.Gson

class DetailFragment : Fragment(R.layout.fragment_details) {

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = Gson().fromJson(args.catDetailsArgs, CatsFactItemViewsState::class.java)
        binding.tvTitle.text = data.title
        binding.tvDes.text = data.description

        binding.topAppBar.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}