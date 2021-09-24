package com.example.kotlinapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * @createTime: 2021/9/24
 * @author: lady_zhou
 * @Description:
 */
abstract class BaseActivity<P> : AppCompatActivity() where P : IBasePersenter {
    lateinit var persenter: P
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutID())
        persenter = createP()
        initView()
    }

    abstract fun initView()

    abstract fun createP(): P

    abstract fun recycle()
    abstract fun getLayoutID(): Int

    override fun onDestroy() {
        super.onDestroy()
        recycle()
    }

}