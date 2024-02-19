package com.edu.filmku.core

import android.app.AlertDialog
import android.graphics.drawable.ColorDrawable
import androidx.fragment.app.FragmentActivity
import com.edu.filmku.databinding.BaseLoadingDialogBinding

/**
 *
 * Created by Lukmanul Hakim on  17/02/24
 * devs.lukman@gmail.com
 */
object DialogBuilder {

    fun loading(context: FragmentActivity?) : AlertDialog? {
        if (context == null) return null
        val binding = BaseLoadingDialogBinding.inflate(context.layoutInflater)
        val builder = android.app.AlertDialog.Builder(context)
        builder.setView(binding.root)
        val alertDialog = builder.create().apply {
            window?.setBackgroundDrawable(ColorDrawable(0))
        }
        alertDialog.setCancelable(false)
        return alertDialog
    }

}