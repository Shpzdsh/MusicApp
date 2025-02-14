package com.shpzdsh.musicapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.shpzdsh.chartlist.impl.presentation.chartlist.ChartListFragment
import com.shpzdsh.chartlist.impl.presentation.Navigation

class MainActivity : AppCompatActivity(), Navigation {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = ChartListFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.container, fragment).commit()

    }

    override fun navigate(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }
}