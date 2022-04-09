package com.leelen.loglib

import android.app.Application
import com.leelen.leelenloglib.utils.LogHelper
import com.leelen.leelenloglib.utils.ServerHelper

/**
 *@description:
 *@copyright: Copyright (c) 2020 厦门立林科技有限公司
 *@author: liwei
 *@date: 2022/4/6
 *@version: 1.00
 *@history:
 */
open class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        //初始化日志
        LogHelper.initLog(this)

        //设置默认服务器地址，可添加备选服务器列表 List<ServerBean>
        ServerHelper.setDefaultServer(
            "http://iot.leelen.com",
            "ssl://iot.leelen.com:8883"
        )
    }
}