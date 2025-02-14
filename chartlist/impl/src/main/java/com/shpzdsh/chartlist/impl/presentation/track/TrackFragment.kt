package com.shpzdsh.chartlist.impl.presentation.track

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.shpzdsh.api.R
import com.shpzdsh.api.databinding.FragmentChartListBinding
import com.shpzdsh.api.databinding.FragmentTrackBinding
import com.shpzdsh.apilibrary.models.TrackDetailsResponse
import com.shpzdsh.chartlist.impl.MusicApp
import com.shpzdsh.chartlist.impl.presentation.viewBinding
import kotlinx.coroutines.launch

class TrackFragment : Fragment(R.layout.fragment_track) {

    // Инициализация ViewModel
    private lateinit var viewModel: TrackViewModel

    val binding by viewBinding(FragmentTrackBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val loadTrackUseCase = (activity?.application as MusicApp).loadTrackUseCase
        val factory = TrackViewModelFactory(loadTrackUseCase)
        viewModel = ViewModelProvider(
            this,
            factory
        )[TrackViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.trackFlow.flowWithLifecycle(
                viewLifecycleOwner.lifecycle,
                Lifecycle.State.STARTED
            ).collect {
                it?.let { updateUI(it) }
            }
        }
        viewModel.loadChart(arguments?.getLong(ID_KEY, -1L) ?: -1L)
    }

    private fun updateUI(trackData: TrackDetailsResponse) = with(binding) {
        // Заполняем данные в UI
        trackTitleTextView.text = trackData.title
        artistNameTextView.text = trackData.artist.name
        trackDurationTextView.text = trackData.duration.toString()

        // Обработка нажатия на кнопку воспроизведения
        playButton.setOnClickListener {
            // Воспроизведение превью трека (используйте MediaPlayer или ExoPlayer)
        }
    }

    companion object {
        private const val ID_KEY = "id_key"
        fun newInstance(trackId: Long) = TrackFragment().apply {
            arguments = Bundle().apply { putLong(ID_KEY, trackId) }
        }
    }

}