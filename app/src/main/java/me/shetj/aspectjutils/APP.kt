package me.shetj.aspectjutils

import android.app.Application
import me.shetj.base.S.init

class APP : Application() {
    override fun onCreate() {
        super.onCreate()
        init(this, true)
    }
}