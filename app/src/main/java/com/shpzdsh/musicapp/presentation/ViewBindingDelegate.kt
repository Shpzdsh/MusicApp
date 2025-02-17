package com.shpzdsh.musicapp.presentation

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ViewBindingDelegate<T : ViewBinding>(
    val fragment: Fragment,
    val viewBindingFactory: (View) -> T
) : ReadOnlyProperty<Fragment, T> {

    private var binding: T? = null

    init {
        fragment.lifecycle.addObserver(LifecycleOwner())
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        val binding = binding
        if (binding != null) {
            return binding
        }

        val lifecycle = fragment.viewLifecycleOwner.lifecycle
        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            throw IllegalStateException("View фрагмента было уничтожено, нельзя получать доступ к view")
        }

        return viewBindingFactory(thisRef.requireView()).also { this.binding = it }
    }

    inner class LifecycleOwner : DefaultLifecycleObserver {

        private val viewLifecycleOwnerLiveDataObserver = Observer<androidx.lifecycle.LifecycleOwner?> {
            val viewLifecycleOwner = it ?: return@Observer

            viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {

                override fun onDestroy(owner: androidx.lifecycle.LifecycleOwner) {
                    binding = null
                }
            })
        }

        override fun onCreate(owner: androidx.lifecycle.LifecycleOwner) {
            fragment.viewLifecycleOwnerLiveData.observeForever(viewLifecycleOwnerLiveDataObserver)
        }

        override fun onDestroy(owner: androidx.lifecycle.LifecycleOwner) {
            fragment.viewLifecycleOwnerLiveData.removeObserver(viewLifecycleOwnerLiveDataObserver)
        }
    }
}

fun <T : ViewBinding> Fragment.viewBinding(viewBindingFactory: (View) -> T) =
    ViewBindingDelegate(this, viewBindingFactory)