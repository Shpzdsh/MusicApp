package com.shpzdsh.chartlist.impl.presentation

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shpzdsh.api.R
import com.shpzdsh.api.databinding.FragmentChartListBinding
import com.shpzdsh.apilibrary.musicApi
import com.shpzdsh.chartlist.impl.presentation.adapter.MusicListAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ChartListFragment : Fragment(R.layout.fragment_chart_list) {

    val retrofitApi = musicApi()

    private val viewModel: ChartListViewModel by viewModels()

    val binding by viewBinding(FragmentChartListBinding::bind)

    val adapter = MusicListAdapter{

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.categoryRecyclerView.adapter = adapter
        GlobalScope.launch {
            val cont = retrofitApi.search("hello").content
            binding.categoryRecyclerView.post{
                adapter.submitList(cont)
            }
        }
    }

    companion object {
        fun newInstance() = ChartListFragment()
    }
}