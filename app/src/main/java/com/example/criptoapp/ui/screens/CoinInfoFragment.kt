package com.example.criptoapp.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.criptoapp.databinding.FragmentCoinInfoBinding
import com.example.criptoapp.ui.viewModel.CoinViewModel

class CoinInfoFragment : Fragment() {

    private var fromSymbol: String = UNDEFINED_FROM_SYMBOL

    private val coinViewModel by lazy {
        ViewModelProvider(this)[CoinViewModel::class.java]
    }

    private var _binding: FragmentCoinInfoBinding? = null
    private val binding: FragmentCoinInfoBinding
        get() = _binding ?: throw RuntimeException("FragmentCoinInfoBinding = null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            fromSymbol = it.getString(FROM_SYMBOL_EXTRA) ?: UNDEFINED_FROM_SYMBOL
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCoinInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = coinViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        coinViewModel.getCoinInfo(fromSymbol)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val FROM_SYMBOL_EXTRA = "fsym"
        private const val UNDEFINED_FROM_SYMBOL = "undefined"

        fun newInstance(fromSymbol: String) = CoinInfoFragment().apply {
            arguments = Bundle().apply {
                putString(FROM_SYMBOL_EXTRA, fromSymbol)
            }
        }
    }
}