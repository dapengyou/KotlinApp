package com.example.kotlinapp.entity

/**
 * @createTime: 2021/9/21
 * @author: lady_zhou
 * @Description:
 */
data class LoginResponseWrapper<T>(val data: T, val errorCode: Int, val errorMsg: String)