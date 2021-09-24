package com.example.kotlinapp.modules.login

import com.example.kotlinapp.entity.LoginResponse

/**
 * @createTime: 2021/9/24
 * @author: lady_zhou
 * @Description:
 */
interface LoginView {
    // 把结果显示到  Activity / Fragment

    fun loginSuccess(loginBean: LoginResponse ?)

    fun loginFialure(erroeMsg: String  ?)
}