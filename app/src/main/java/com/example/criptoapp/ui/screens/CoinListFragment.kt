package com.example.criptoapp.ui.screens

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.criptoapp.R
import com.example.criptoapp.databinding.FragmentCoinListBinding
import com.example.criptoapp.ui.CoinApp
import com.example.criptoapp.ui.viewModel.CoinViewModel
import com.example.criptoapp.ui.adapters.AdapterCoinList
import com.example.criptoapp.ui.viewModel.ViewModelFactory
import javax.inject.Inject


class CoinListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val coinViewModel by lazy {
        ViewModelProvider(this,viewModelFactory)[CoinViewModel::class.java]
    }

    private val component by lazy {
        (requireActivity().application as CoinApp).component
    }

    private val adapterCoinList by lazy {
        AdapterCoinList()
    }

    private var _binding: FragmentCoinListBinding? = null
    private val binding: FragmentCoinListBinding
        get() = _binding ?: throw RuntimeException("FragmentCoinListBinding = null")

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCoinListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
    }

    private fun observeViewModel() {
        coinViewModel.coinListFromDb.observe(viewLifecycleOwner) {
            adapterCoinList.submitList(it)
        }
    }

    private fun setupRecyclerView() {
        val orientation = requireActivity().resources.configuration.orientation
        binding.recyclerViewCoinList.adapter = adapterCoinList
        binding.recyclerViewCoinList.itemAnimator = null
        adapterCoinList.onCoinClickListener = {
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, CoinInfoFragment.newInstance(it.firstName))
                    .addToBackStack(null)
                    .commit()
            } else {
                requireActivity().supportFragmentManager.popBackStack()
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container_second, CoinInfoFragment.newInstance(it.firstName))
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}