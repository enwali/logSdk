package com.leelen.loglib

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.leelen.leelenloglib.bean.RELEASE_BASE_URL
import com.leelen.leelenloglib.bean.RELEASE_MQTT_HOST
import com.leelen.leelenloglib.ext.showTransparent
import com.leelen.leelenloglib.utils.LogUtils
import com.leelen.leelenloglib.utils.SpModel
import com.leelen.leelenloglib.view.LogExtSettingActivity


/**
 * @description: 日志库演示页面
 * @copyright: Copyright (c) 厦门立林科技有限公司
 * @author: liwei
 * @date: 2022/3/21 10:46
 * @version: 1.00
 * @history:
 */
class MainActivity : AppCompatActivity() {
    private var TAG: String = this.javaClass.name
    private lateinit var tvInfo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showTransparent()
        setContentView(R.layout.activity_main)
        val mEtText = findViewById<EditText>(R.id.et_text)
        tvInfo = findViewById(R.id.tv_info)

        findViewById<TextView>(R.id.btn_to_log_lib).setOnClickListener {
            val text = mEtText.text.toString().trim()
            val key = "868686"
            if (key == text) {
                LogUtils.i(TAG, "进入配置页面")
                startActivity(Intent(this, LogExtSettingActivity::class.java))
            }
        }

        findViewById<TextView>(R.id.btn_refresh_configuration).setOnClickListener {
            setInfo()
        }
    }

    override fun onResume() {
        super.onResume()
        setInfo()
    }

    @SuppressLint("SetTextI18n")
    private fun setInfo() {
        val config = SpModel.config
        val enableDebug = config?.useLogcat ?: false
        val enableSave = config?.saveToFile ?: false
        tvInfo.text =
            "当前http服务器：$RELEASE_BASE_URL\n当前mqtt服务器：$RELEASE_MQTT_HOST\n是否开启日志：$enableDebug\n是否保存日志：$enableSave"
    }
}