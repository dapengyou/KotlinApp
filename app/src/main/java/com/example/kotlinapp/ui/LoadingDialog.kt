package com.example.kotlinapp.ui

import android.app.Dialog
import android.content.Context
import com.example.kotlinapp.R

/**
 * @createTime: 2021/9/22
 * @author: lady_zhou
 * @Description:
 */
object LoadingDialog {
    var dialog: Dialog? = null;
    fun show(context: Context) {
        cancel()
        dialog = Dialog(context)
        dialog?.setContentView(R.layout.dialog_loading)
        dialog?.setCancelable(false)
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.show()
    }

    fun cancel() {
        dialog?.dismiss()
    }
}