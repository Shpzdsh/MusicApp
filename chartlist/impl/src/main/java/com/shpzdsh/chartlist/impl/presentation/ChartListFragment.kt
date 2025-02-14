package com.shpzdsh.chartlist.impl.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.shpzdsh.api.R
import com.shpzdsh.api.databinding.FragmentChartListBinding
import com.shpzdsh.apilibrary.musicApi
import com.shpzdsh.chartlist.impl.data.MusicRepositoryImpl
import com.shpzdsh.chartlist.impl.domain.LoadChartUseCase
import com.shpzdsh.chartlist.impl.domain.MusicRepository
import com.shpzdsh.chartlist.impl.presentation.adapter.MusicListAdapter
import kotlinx.coroutines.launch

class ChartListFragment : Fragment(R.layout.fragment_chart_list) {

    private lateinit var viewModel: ChartListViewModel


    val binding by viewBinding(FragmentChartListBinding::bind)

    val adapter = MusicListAdapter {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val musicRepository = MusicRepositoryImpl(musicApi())
        val loadChartUseCase: LoadChartUseCase = LoadChartUseCase(musicRepository)
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
