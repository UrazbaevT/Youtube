package com.example.a5month_youtube.core.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding, VM : BaseViewModel> : AppCompatActivity() {

    protected lateinit var binding: VB
    protected abstract fun inflateViewBinding(): VB
    protected abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateViewBinding()
        setContentView(binding.root)

        checkConnection()
        initViewModel()
        initView()
        initListener()
    }

    open fun initViewModel() {} // инициализация вьюмодэла
    open fun checkConnection() {} // проверка на интернет
    open fun initView() {} // инициализация вьюшек
    open fun initListener() {} // обработка всех кликов
}