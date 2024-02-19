package com.edu.filmku.core

import android.app.AlertDialog
import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 *
 * Created by Lukmanul Hakim on  15/02/24
 * devs.lukman@gmail.com
 */
abstract class BaseActivity<VB : ViewBinding>(
    private val inflate: (LayoutInflater) -> VB
) : AppCompatActivity() {

    private var loadingDialog: AlertDialog? = null

    protected val binding by lazy {
        inflate.invoke(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        loadingDialog = DialogBuilder.loading(this)
        onViewReady()
    }

    abstract fun onViewReady()

    fun setLoading(show : Boolean) {
        if (show) {
            loadingDialog?.apply {
                if (!isShowing) show()
            }
        } else loadingDialog?.dismiss()
    }

    fun showMessage(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}