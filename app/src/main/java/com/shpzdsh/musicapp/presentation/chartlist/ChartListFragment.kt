package com.shpzdsh.musicapp.presentation.chartlist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.shpzdsh.musicapp.R
import com.shpzdsh.musicapp.core.BaseMusicFragment
import com.shpzdsh.musicapp.databinding.FragmentChartListBinding
import com.shpzdsh.musicapp.presentation.adapter.MusicListAdapter
import com.shpzdsh.musicapp.presentation.adapter.TrackItemDecorator
import com.shpzdsh.musicapp.presentation.viewBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChartListFragment : BaseMusicFragment(R.layout.fragment_chart_list) {

    override val viewModel: ChartListViewModel by viewModel<ChartListViewModel>()

    private val binding by viewBinding(FragmentChartListBinding::bind)
    private val itemDecoration = TrackItemDecorator(R.dimen.size_normal_medium)

    private val adapter = MusicListAdapter {
        val action = ChartListFragmentDirections.actionChartListFragmentToTrackFragment(it)
        findNavController().navigate(action)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserver()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        reloadInfo()
    }

    override fun reloadInfo() {
        viewModel.loadChart()
    }

    private fun initObserver() = with(viewModel) {
        viewLifecycleOwner.lifecycleScope.launch {
            trackFlow.flowWithLifecycle(
                viewLifecycleOwner.lifecycle,
                Lifecycle.State.STARTED
            ).collect {
                adapter.submitList(it)
            }
        }
    }

    private fun initView() = with(binding) {
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(itemDecoration)

        searchField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.search(s.toString().trim())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}
