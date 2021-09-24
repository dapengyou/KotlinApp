package com.example.kotlinapp.modules.login

import android.content.Context
import com.example.kotlinapp.base.IBasePersenter
import com.example.kotlinapp.entity.LoginResponse

/**
 * @createTime: 2021/9/24
 * @author: lady_zhou
 * @Description:
 */
interface LoginPersenter : IBasePersenter {
    //登录
    fun loginAction(context: Context, userName: String, password: String)

    //回调
    interface OnLoginListener {
        fun loginSuccess(loginResponse: LoginResponse?)
        fun loginFail(errorMsg: String?)
    }
}