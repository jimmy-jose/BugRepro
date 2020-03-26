package com.example.bugrepro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.bugrepro.dummy.DummyContent
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import javax.inject.Named
import kotlin.properties.Delegates

class MainActivity : DaggerAppCompatActivity(),
    PagerItemFragment.OnListFragmentInteractionListener {

    @JvmField // expose a field
    @field:[Inject Named("isAppNull")] // leave your annotatios unchanged
    var isDemo: Boolean = false // set a default value

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivityTAG", "is $isDemo")
    }

    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {

    }
}
