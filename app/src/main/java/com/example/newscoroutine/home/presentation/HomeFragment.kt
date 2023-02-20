package com.example.newscoroutine.home.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.newscoroutine.common.MainViewModelFactory
import com.example.newscoroutine.R
import com.example.newscoroutine.common.MainRepository
import com.example.newscoroutine.common.MainViewModel
import com.example.newscoroutine.databinding.FragmentHomeBinding
import com.example.newscoroutine.home.presentation.adapter.CatFactsAdapter
import com.google.gson.Gson

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    private val viewModel: MainViewModel by lazy {
        val repository = MainRepository()
        val factory = MainViewModelFactory(repository = repository)
        ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }

    private val adapter by lazy {
        CatFactsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        viewModel.catFacts.observe(viewLifecycleOwner) { state ->
            when (state) {
                is CatsListViewState.Content -> {
                    binding.pbLoading.visibility = View.GONE
                    binding.rvCatFacts.visibility = View.VISIBLE
                    state.productList?.let { list ->
                        adapter.setCatsList(list)
                        adapter.notifyDataSetChanged()
                    }
                }
                is CatsListViewState.Error -> {
                    binding.pbLoading.visibility = View.GONE
                    binding.rvCatFacts.visibility = View.GONE
                    Log.e("DEBUG", "Loi")
                }
                else -> {
                    binding.rvCatFacts.visibility = View.GONE
                    binding.pbLoading.visibility = View.VISIBLE
                }
            }
        }
        viewModel.fetchCatFacts()

        adapter.onItemTapped {
            val data = Gson().toJson(it)
            moveToDetails(data)
        }
    }

    private fun setupRecyclerView() {
        binding.rvCatFacts.setHasFixedSize(true)
        binding.rvCatFacts.setItemViewCacheSize(20)
        binding.rvCatFacts.adapter = adapter
    }

    private fun moveToDetails(data: String) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(
            data
        )
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}