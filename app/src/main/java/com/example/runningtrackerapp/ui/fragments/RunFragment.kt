package com.example.runningtrackerapp.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.runningtrackerapp.R
import com.example.runningtrackerapp.adapter.RunAdapter
import com.example.runningtrackerapp.other.Constants.REQUESt_Code_LOCATION_PERMISSION
import com.example.runningtrackerapp.other.SortBy
import com.example.runningtrackerapp.other.TrackingUtility
import com.example.runningtrackerapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_run.*
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

@AndroidEntryPoint
class RunFragment : Fragment(R.layout.fragment_run), EasyPermissions.PermissionCallbacks {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var runadapter: RunAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestPermission()
        setupRecycleView()
        fab.setOnClickListener {
            fragment.findNavController().navigate(R.id.action_runFragment_to_trackingFragment)
        }

        when (viewModel.sortType) {
            SortBy.DATE -> spFilter.setSelection(0)
            SortBy.RUNNING_TIME -> spFilter.setSelection(1)
            SortBy.DISTANCE -> spFilter.setSelection(2)
            SortBy.AVG_SPEED -> spFilter.setSelection(3)
            SortBy.CALORIES_BURN -> spFilter.setSelection(3)

        }
        spFilter?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 ->
                        viewModel.sortRuns(SortBy.DATE)
                    1 ->
                        viewModel.sortRuns(SortBy.RUNNING_TIME)
                    2 ->
                        viewModel.sortRuns(SortBy.DISTANCE)
                    3 ->
                        viewModel.sortRuns(SortBy.AVG_SPEED)
                    4 ->
                        viewModel.sortRuns(SortBy.CALORIES_BURN)

                }
            }
        }
        viewModel.runs.observe(viewLifecycleOwner, Observer {
            runadapter.submitList(it)
        })


    }

    private fun setupRecycleView() = rvRuns.apply {
        runadapter = RunAdapter()
        adapter = runadapter
        layoutManager = LinearLayoutManager(requireContext())
    }

    private fun requestPermission() {
        if (TrackingUtility.hasLocationPermission(requireContext())) {
            return
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            EasyPermissions.requestPermissions(
                this,
                "you need  to accept location permissions to use the app.",
                REQUESt_Code_LOCATION_PERMISSION,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION

            )
        } else {
            EasyPermissions.requestPermissions(
                this,
                "you need  to accept location permissions to use the app.",
                REQUESt_Code_LOCATION_PERMISSION,
                android.Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
        }
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()

        } else {
            requestPermission()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {}

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(
            requestCode,
            permissions,
            grantResults,
            this
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("KEY_LAYOUT", rvRuns.getLayoutManager()?.onSaveInstanceState());


    }
    override fun onActivityCreated( savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState != null) {
            rvRuns.getLayoutManager()?.onRestoreInstanceState(
                savedInstanceState.getParcelable("KEY_LAYOUT")

            )
        }
    }
}
