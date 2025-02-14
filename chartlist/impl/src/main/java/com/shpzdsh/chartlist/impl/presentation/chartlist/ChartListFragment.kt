package com.shpzdsh.chartlist.impl.presentation.chartlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.shpzdsh.api.R
import com.shpzdsh.api.databinding.FragmentChartListBinding
import com.shpzdsh.chartlist.impl.MusicApp
import com.shpzdsh.chartlist.impl.presentation.Navigation
import com.shpzdsh.chartlist.impl.presentation.adapter.MusicListAdapter
import com.shpzdsh.chartlist.impl.presentation.track.TrackFragment
import com.shpzdsh.chartlist.impl.presentation.viewBinding
import kotlinx.coroutines.launch

class ChartListFragment : Fragment(R.layout.fragment_chart_list) {

    private lateinit var viewModel: ChartListViewModel


    val binding by viewBinding(FragmentChartListBinding::bind)

    val adapter = MusicListAdapter {
        val fragment = TrackFragment.newInstance(it)
        (activity as Navigation).navigate(fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val loadChartUseCase = (activity?.application as MusicApp).loadChartUseCase
        val factory = ChartListViewModelFactory(loadChartUseCase)
        viewModel = ViewModelProvider(
            this,
            factory
        )[ChartListViewModel::class.java]

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.categoryRecyclerView.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.trackFlow.flowWithLifecycle(
                viewLifecycleOwner.lifecycle,
                Lifecycle.State.STARTED
            ).collect {
                adapter.submitList(it)
            }
        }
        viewModel.loadChart()
    }

    companion object {
        fun newInstance() = ChartListFragment()
    }
}
