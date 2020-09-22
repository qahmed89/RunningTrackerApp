package com.example.runningtrackerapp.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.runningtrackerapp.R
import com.example.runningtrackerapp.other.Constants
import com.example.runningtrackerapp.other.Constants.ACTION_SHOW_TRACKING_FRAGMENT
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_settings.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
@Inject
lateinit var sharedPreferences:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navgateTiTrackingFragment(intent)
        setSupportActionBar(toolbar)
        loadFieldFromSharedPref()
        bottomNavigationView.setupWithNavController(fragment.findNavController())
        bottomNavigationView.setOnNavigationItemReselectedListener { /* No*/ }
        fragment.findNavController().addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.runFragment, R.id.statisticsFragment, R.id.settingsFragment ->
                    bottomNavigationView.visibility = View.VISIBLE
                else ->
                    bottomNavigationView.visibility = View.GONE
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navgateTiTrackingFragment(intent)
    }

    private fun navgateTiTrackingFragment(intent: Intent?) {
        if (intent?.action == ACTION_SHOW_TRACKING_FRAGMENT) {
            fragment.findNavController().navigate(R.id.action_global_trackingFragment)
        }
    }
    private fun loadFieldFromSharedPref() {
        val name = sharedPreferences.getString(Constants.KEY_NAME, "")
        val toolbarText = "Lets Go $name"
        tvToolbarTitle.text = toolbarText
    }
}