package com.example.kotlinapp.modules.login

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.kotlinapp.entity.LoginResponse
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.kotlinapp.MainActivity
import com.example.kotlinapp.api.WangAndroidApi

import com.example.kotlinapp.net.APIClient
import com.example.kotlinapp.net.ApiResponse
import com.example.kotlinapp.utils.Flag
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @createTime: 2021/9/24
 * @author: lady_zhou
 * @Description:
 */
class LoginPersenterImpl(var loginView: LoginView?) : LoginPersenter,
    LoginPersenter.OnLoginListener {
    override fun loginAction(context: Context, userName: String, password: String) {
        APIClient.instance.initRetrofit(WangAndroidApi::class.java)

            // 全部都是RxJava知识了
            .getLoginData(userName, password)  // 起点  往下流  ”包装Bean“
            .subscribeOn(Schedulers.io()) // 给上面请求服务器的操作，分配异步线程
            .observeOn(AndroidSchedulers.mainThread()) // 给下面更新UI操作，分配main线程

            /*.subscribe(object : Consumer<LoginResponseWrapper<LoginResponse>> {
                    override fun accept(t: LoginResponseWrapper<LoginResponse>?) {
                        // 我这里是更新UI，拿到了包装Bean，实际上我不需要包装Bean
                    }

                })*/

            .subscribe(object : ApiResponse<LoginResponse>(context) {
                override fun success(data: LoginResponse?) {
                    // 成功  data UI
                    Log.e(Flag.LOGINTAG, "success: $data")
                    loginSuccess(data)
//
                }

                override fun failure(errorMsg: String?) {
                    // 失败 msg UI
                    Log.e(Flag.LOGINTAG, "failure: errorMsg:$errorMsg")
                    loginFail(errorMsg)
                }

            })
    }

    override fun unAttachView() {

    }

    override fun loginSuccess(loginResponse: LoginResponse?) {
        loginView?.loginSuccess(loginResponse)
    }

    override fun loginFail(errorMsg: String?) {
        loginView?.loginFialure(errorMsg)
    }
}