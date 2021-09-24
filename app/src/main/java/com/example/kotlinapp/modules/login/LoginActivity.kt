package com.example.kotlinapp.modules.login

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.kotlinapp.MainActivity
import com.example.kotlinapp.R
import com.example.kotlinapp.base.BaseActivity
import com.example.kotlinapp.entity.LoginResponse
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity<LoginPersenter>(), LoginView {
    val TAG = "LoginActivity"

    private val onCLickLister = View.OnClickListener { view ->
        when (view.id) {
            R.id.user_login_bt -> {
                val userNameStr = user_phone_et.text.toString()
                val userPwdStr = user_password_et.text.toString()
                Log.d(TAG, "userName:$userNameStr,  userPwd:$userPwdStr")
                persenter.loginAction(this@LoginActivity, userNameStr, userPwdStr)
            }
        }
    }

    override fun createP(): LoginPersenter {
        return LoginPersenterImpl(this)
    }

    override fun recycle() {
    }

    override fun getLayoutID(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
        user_login_bt.setOnClickListener(onCLickLister)
    }

    override fun loginSuccess(loginBean: LoginResponse?) {
        Toast.makeText(this@LoginActivity, "登录成功😀", Toast.LENGTH_SHORT).show()
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
    }

    override fun loginFialure(erroeMsg: String?) {
        Toast.makeText(this@LoginActivity, "登录失败 ~ 呜呜呜", Toast.LENGTH_SHORT).show()
    }

}