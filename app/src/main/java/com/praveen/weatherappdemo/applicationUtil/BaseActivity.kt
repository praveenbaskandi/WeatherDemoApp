package com.praveen.weatherappdemo.applicationUtil

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity


open class BaseActivity: AppCompatActivity() {
    private var context: Context? = null
    private var transparentDialog: TransparentProgressDialog? = null

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
    }

    fun showProgressDialog(msg: String?) {
        if (transparentDialog == null) {
            transparentDialog = if (TextUtils.isEmpty(msg)) {
                TransparentProgressDialog().newInstance()
            } else {
                TransparentProgressDialog().newInstance(msg)
            }
        }
        transparentDialog!!.show(supportFragmentManager, "progressDialog")
    }

    fun hideProgressDialog() {
        if (transparentDialog != null) {
            transparentDialog!!.dismiss()
            transparentDialog = null
        }
    }


    fun showToast(toastMessage: String?) {
        Toast.makeText(context, toastMessage, Toast.LENGTH_LONG).show()
    }
}