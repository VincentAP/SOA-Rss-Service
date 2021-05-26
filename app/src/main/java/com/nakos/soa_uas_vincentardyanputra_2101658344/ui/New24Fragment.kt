package com.nakos.soa_uas_vincentardyanputra_2101658344.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.nakos.soa_uas_vincentardyanputra_2101658344.Status
import com.nakos.soa_uas_vincentardyanputra_2101658344.adapter.News24Adapter
import com.nakos.soa_uas_vincentardyanputra_2101658344.databinding.FragmentNews24Binding
import com.nakos.soa_uas_vincentardyanputra_2101658344.viewmodel.News24ViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class New24Fragment : BaseFragment() {

    private var binding: FragmentNews24Binding? = null

    private val news24ViewModel: News24ViewModel by viewModels()

    private val recyclerAdapter: News24Adapter by lazy { News24Adapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNews24Binding.inflate(inflater, container, false)

        binding?.apply {
            recyclerNews24.adapter = recyclerAdapter.setNews24Listener { str ->
                Intent(Intent.ACTION_VIEW, Uri.parse(str)).also {
                    startActivity(it)
                }
            }
        }

        setupNews24ViewModel()
        return binding?.root
    }

    private fun setupNews24ViewModel() {
        news24ViewModel.getNews24Item()
        news24ViewModel.news24Item.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> binding?.progressBar?.visibility = View.VISIBLE
                Status.SUCCESS -> {
                    binding?.progressBar?.visibility = View.GONE
                    recyclerAdapter.submitList(it.data)
                }
                else -> {
                    binding?.progressBar?.visibility = View.GONE
                    showToast(it.message ?: "unknown error")
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}