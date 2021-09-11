package com.praveen.weatherappdemo.applicationUtil

import android.app.Dialog
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.praveen.weatherappdemo.R


class TransparentProgressDialog : DialogFragment() {
    private var rootView: View? = null
    private var tvMsg: TextView? = null
    private var progressBar: ProgressBar? = null
    private var message: String? = null
    private var isCancelable: Boolean? = false

    companion object {
        const val ARGS_MESSAGE = "message"
        const val ARGS_IS_CANCELABLE = "is_cancelable"
    }


    fun newInstance(message: String?, isCancelable: Boolean): TransparentProgressDialog? {
        val args = Bundle()
        args.putString(ARGS_MESSAGE, message)
        args.putBoolean(ARGS_IS_CANCELABLE, isCancelable)
        val fragment = TransparentProgressDialog()
        fragment.arguments = args
        return fragment
    }

    fun newInstance(message: String?): TransparentProgressDialog {
        val args = Bundle()
        args.putString(ARGS_MESSAGE, message)
        args.putBoolean(ARGS_IS_CANCELABLE, false)
        val fragment = TransparentProgressDialog()
        fragment.arguments = args
        return fragment
    }

    fun newInstance(): TransparentProgressDialog {
        val args = Bundle()
        val fragment = TransparentProgressDialog()
        fragment.arguments = args
        return fragment
    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        if (args != null) {
            message = args.getString(ARGS_MESSAGE)
            isCancelable = args.getBoolean(ARGS_IS_CANCELABLE)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(
            requireActivity(), R.style.TransparentProgressDialog
        )

        val inflater = activity?.layoutInflater
        rootView = inflater?.inflate(R.layout.transparent_progress_dialog, null)
        tvMsg = rootView!!.findViewById(R.id.tv_msg)
        progressBar = rootView!!.findViewById(R.id.progressBar)

        setMessage(message)


        builder.setView(rootView)

        if (!isCancelable!!) {
            setCancelable(false)
        }

        return builder.create()
    }

    fun setMessage(message: String?) {
        if (TextUtils.isEmpty(message)) {
            tvMsg!!.visibility = View.GONE
        } else {
            tvMsg!!.visibility = View.VISIBLE
            tvMsg!!.text = message
        }
        this.message = message
    }

    override fun dismiss() {
        if (dialog != null) {
            dialog!!.dismiss()
        }
    }

}