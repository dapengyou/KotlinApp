package com.example.kotlinapp.net

import android.content.Context
import com.example.kotlinapp.entity.LoginResponseWrapper
import com.example.kotlinapp.ui.LoadingDialog
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * @createTime: 2021/9/22
 * @author: lady_zhou
 * @Description:
 */
abstract class ApiResponse<T>(val context: Context) : Observer<LoginResponseWrapper<T>> {
    abstract fun success(data: T?)
    abstract fun failure(errorMsg: String?)
    private var isShow: Boolean = true

    constructor(context: Context, isShow: Boolean = false) : this(context) {
        this.isShow = isShow
    }

    override fun onSubscribe(d: Disposable?) {
        if (isShow) {
            LoadingDialog.show(context)
        }
    }

    override fun onNext(t: LoginResponseWrapper<T>?) {
        if (t?.data == null) {
            failure(t?.errorMsg)
        } else {
            success(t?.data)
        }
    }

    override fun onError(e: Throwable?) {
        LoadingDialog.cancel()
        failure(e?.message)
    }

    override fun onComplete() {
        LoadingDialog.cancel()
    }

}