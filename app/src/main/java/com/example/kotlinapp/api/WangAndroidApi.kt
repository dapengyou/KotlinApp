package com.example.kotlinapp.api

import com.example.kotlinapp.entity.LoginResponse
import com.example.kotlinapp.entity.LoginResponseWrapper
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * @createTime: 2021/9/21
 * @author: lady_zhou
 * @Description:
 */
interface WangAndroidApi {
    /**
     * 登录API
     * username=lady_zhou&password=123456
     */
    @POST("/user/login")
    @FormUrlEncoded
    fun getLoginData(
        @Field("username") username: String,
        @Field("password") password: String
    ): Observable<LoginResponseWrapper<LoginResponse>>
}