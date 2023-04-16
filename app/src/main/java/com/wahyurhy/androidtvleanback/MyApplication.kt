package com.wahyurhy.androidtvleanback

import android.app.Application
import com.wahyurhy.androidtvleanback.api.ApiService
import com.wahyurhy.androidtvleanback.api.RetrofitHelper
import com.wahyurhy.androidtvleanback.api.TmdbRepo

class MyApplication : Application() {

    lateinit var tmdbRepo: TmdbRepo

    override fun onCreate() {
        super.onCreate()

        init()
    }

    private fun init() {
        val service = RetrofitHelper.getInstance().create(ApiService::class.java)
        tmdbRepo = TmdbRepo(service)
    }
}