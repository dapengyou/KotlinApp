package com.example.kotlinapp.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.kotlinapp.R
import android.widget.Toast
import com.example.kotlinapp.MainActivity
import com.example.kotlinapp.api.WangAndroidApi
import com.example.kotlinapp.entity.LoginResponse
import com.example.kotlinapp.net.APIClient
import com.example.kotlinapp.net.ApiResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    val TAG = "LoginActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        user_login_bt.setOnClickListener(onCLickLister)
    }

    private  val onCLickLister = View.OnClickListener { view ->
        when(view.id){
            R.id.user_login_bt->{
                val userNameStr = user_phone_et.text.toString()
                val userPwdStr = user_password_et.text.toString()
                Log.d(TAG, "userName:$userNameStr,  userPwd:$userPwdStr")

                APIClient.instance.initRetrofit(WangAndroidApi::class.java)

                    // 全部都是RxJava知识了
                    .getLoginData(userNameStr, userPwdStr)  // 起点  往下流  ”包装Bean“
                    .subscribeOn(Schedulers.io()) // 给上面请求服务器的操作，分配异步线程
                    .observeOn(AndroidSchedulers.mainThread()) // 给下面更新UI操作，分配main线程

                    /*.subscribe(object : Consumer<LoginResponseWrapper<LoginResponse>> {
                            override fun accept(t: LoginResponseWrapper<LoginResponse>?) {
                                // 我这里是更新UI，拿到了包装Bean，实际上我不需要包装Bean
                            }

                        })*/

                    .subscribe(object: ApiResponse<LoginResponse>(this)
                    {
                        override fun success(data: LoginResponse ?) {
                            // 成功  data UI
                            Log.e(TAG, "success: $data")
//                            Toast.makeText(this@LoginActivity, "登录成功😀", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)
                        }

                        override fun failure(errorMsg: String?) {
                            // 失败 msg UI
                            Log.e(TAG, "failure: errorMsg:$errorMsg")
                            Toast.makeText(this@LoginActivity, "登录失败 ~ 呜呜呜", Toast.LENGTH_SHORT).show()
                        }

                    })
            }
        }
    }

}