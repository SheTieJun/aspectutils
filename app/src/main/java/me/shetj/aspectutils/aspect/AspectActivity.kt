package me.shetj.aspectutils.aspect

import android.Manifest
import android.os.Bundle
import android.os.Message
import kotlinx.android.synthetic.main.activity_aspect.*
import me.shetj.aspect.permission.MPermission
import me.shetj.aspectutils.R
import me.shetj.base.base.BaseActivity
import me.shetj.base.tools.app.ArmsUtils
import timber.log.Timber

class AspectActivity : BaseActivity<AspectPresenter>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aspect)
        initView()
        initData()
    }

    override fun initData() {
        mPresenter = AspectPresenter(this)
    }
    override fun initView() {

        btn_get_info.setOnClickListener {
            testAspect()
        }
        btn_get_log.setOnClickListener {
            mPresenter?.testAspect()
        }

    }

    @MPermission(value = [Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE])
    private fun testAspect() {
        ArmsUtils.makeText("testAspect ok")
    }

    override fun updateView(message: Message) {
        super.updateView(message)
        Timber.e("updateView")
    }

}
