package com.shpzdsh.musicapp.core

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

abstract class BaseMusicFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    abstract val viewModel: BaseMusicViewModel

    abstract fun reloadInfo()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeFlow()
    }

    private fun observeFlow() = with(viewModel) {
        viewLifecycleOwner.lifecycleScope.launch {
            isLoading.flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED).collect { isLoading ->
                (activity as? ActivityStateProcessor)?.processLoading(isLoading)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            errorMessage.flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collect { errorMessage ->
                    errorMessage?.let {
                        (activity as? ActivityStateProcessor)?.processError(errorMessage) {
                            reloadInfo()
                        }
                    }
                }
        }
    }
}