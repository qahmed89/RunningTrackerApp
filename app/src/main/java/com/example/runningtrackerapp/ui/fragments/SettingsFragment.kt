package com.example.runningtrackerapp.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.runningtrackerapp.R
import com.example.runningtrackerapp.other.Constants.KEY_NAME
import com.example.runningtrackerapp.other.Constants.KEY_WEIGHT
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_settings.*
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : Fragment(R.layout.fragment_settings) {
    @Inject
    lateinit var sharedPreferences: SharedPreferences
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadFieldFromSharedPref()
        btnApplyChanges.setOnClickListener {
            val success = applyChangestoSharedPref()
            if (success) {
                Snackbar.make(view, "Saved make Changed", Snackbar.LENGTH_LONG).show()

            } else {
                Snackbar.make(view, "Please fill out all fields", Snackbar.LENGTH_LONG).show()

            }
        }
    }


    private fun loadFieldFromSharedPref() {
        val name = sharedPreferences.getString(KEY_NAME, "")
        val weight = sharedPreferences.getFloat(KEY_WEIGHT, 80f)
        etName.setText(name)
        etWeight.setText(weight.toString())
    }

    private fun applyChangestoSharedPref(): Boolean {
        val nameText = etName.text.toString()
        val weightText = etWeight.text.toString()
        if (nameText.isEmpty() || weightText.isEmpty()) {
            return false
        } else {
            sharedPreferences.edit()
                .putString(KEY_NAME, nameText)
                .putFloat(KEY_WEIGHT, weightText.toFloat())
                .apply()
            val toolbarText = "Lets Go $nameText"
            requireActivity().tvToolbarTitle.text = toolbarText
            return true
        }
    }

}