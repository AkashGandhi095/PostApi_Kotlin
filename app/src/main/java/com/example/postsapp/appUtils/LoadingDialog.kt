package com.example.postsapp.appUtils

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.example.postsapp.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlin.math.log

object LoadingDialog {

    private const val TAG = "LoadingDialogUtils"

    private lateinit var  alertDialog :AlertDialog

    fun createLoadingDialog(context :Context)  {

        alertDialog = MaterialAlertDialogBuilder(context)
                .setView(R.layout.loading_oview).create().apply {
                    setCancelable(false)
                    setCanceledOnTouchOutside(false)
                }
    }


    fun showLoadingDialog() {
        alertDialog.show()
    }

    fun hideLoadingDialog() {
        alertDialog.hide()
    }


}