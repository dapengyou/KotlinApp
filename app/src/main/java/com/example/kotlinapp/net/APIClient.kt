package com.example.kotlinapp.net

import android.util.Log
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URLDecoder
import java.util.concurrent.TimeUnit

/**
 * @createTime: 2021/9/22
 * @author: lady_zhou
 * @Description:
 */
class APIClient {
    val BASE_URL = "https://www.wanandroid.com"
    //单例
    private object Holder {
        val getInstance = APIClient()
    }

    companion object {
        val instance = Holder.getInstance
    }

    fun <T> initRetrofit(apiInterface: Class<T>): T {
        var interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            var text = URLDecoder.decode(it, "utf-8")
            Log.e("okhttp----", text)
        })
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)// 四个级别：NONE,BASIC,HEADER,BODY; BASEIC:请求/响应行;  HEADER:请求/响应行 + 头;  BODY:请求/响应航 + 头 + 体;
        // OKHttpClient请求服务器
        val mOkHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .addNetworkInterceptor(StethoInterceptor())//调试神器
            // 添加读取超时时间
            .readTimeout(10000, TimeUnit.SECONDS)

            // 添加连接超时时间
            .connectTimeout(10000, TimeUnit.SECONDS)

            // 添加写出超时时间
            .writeTimeout(10000, TimeUnit.SECONDS)
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)

            // 请求方  ←
            .client(mOkHttpClient)

            // 响应方  →
            // Response的事情  回来
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // RxJava来处理
            .addConverterFactory(GsonConverterFactory.create()) // Gson 来解析 --- JavaBean
            .build()

        return retrofit.create(apiInterface)
    }
}