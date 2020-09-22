package com.example.runningtrackerapp.ui

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.runningtrackerapp.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class CancelTrackingDialog :DialogFragment () {
private  var yesListner:(()-> Unit)?=null
    fun setYesListener(listener :()->Unit){
        yesListner=listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

       return MaterialAlertDialogBuilder(
            requireContext(),
            R.style.AlertDialogTheme
        ).setTitle("Cancel the Run ?")
            .setMessage("Are you sure you want cancel the current run and delete all its data ?")
            .setIcon(R.drawable.ic_delete)
            .setPositiveButton("yes") { _, _ ->
yesListner?.let { yes->
    yes()
}


            }
            .setNegativeButton("No") { dialogInterface, i ->
                dialogInterface.cancel()

            }
            .create()
    }
}