package com.shpzdsh.musicapp.presentation.track

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil3.load
import com.shpzdsh.musicapp.MediaService
import com.shpzdsh.musicapp.R
import com.shpzdsh.musicapp.core.BaseMusicFragment
import com.shpzdsh.musicapp.core.formatDuration
import com.shpzdsh.musicapp.data.api.models.TrackDetailsResponse
import com.shpzdsh.musicapp.databinding.FragmentTrackBinding
import com.shpzdsh.musicapp.domain.models.TrackDetail
import com.shpzdsh.musicapp.presentation.viewBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class TrackFragment : BaseMusicFragment(R.layout.fragment_track) {

    override val viewModel: TrackViewModel by viewModel<TrackViewModel>()

    private val args: TrackFragmentArgs by navArgs()
    private val binding by viewBinding(FragmentTrackBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        reloadInfo()
    }

    override fun reloadInfo() {
        viewModel.loadChart(args.trackId)
    }

    private fun initView(trackData: TrackDetail) = with(binding) {
        trackCover.load(trackData.cover)
        trackName.text = trackData.title
        artistName.text = trackData.author
        totalTime.text = formatDuration(trackData.duration)

        btnPlayPause.setOnClickListener {
            val intent = Intent(requireContext(), MediaService::class.java)
            activity?.startService(intent)
        }

        btnPrevious.setOnClickListener {
            viewModel.loadPrev()
        }

        btnNext.setOnClickListener {
            viewModel.loadNext()
        }
    }

    private fun initObserver() = with(viewModel) {
        viewLifecycleOwner.lifecycleScope.launch {
            trackFlow.flowWithLifecycle(
                viewLifecycleOwner.lifecycle,
                Lifecycle.State.STARTED
            ).collect {
                it?.let { initView(it) }
            }
        }
    }
//
//        private fun updateProgressUI(currentPosition: Long, duration: Long) {
//            val currentTime = formatDuration(currentPosition.toInt() / 1000 )
//            binding.currentTime.text = currentTime
//            val progress = (currentPosition.toFloat() / duration * 100).toInt()
//            binding.progressBar.progress = progress
//        }
}