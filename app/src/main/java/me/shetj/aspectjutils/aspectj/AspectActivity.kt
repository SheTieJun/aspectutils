package me.shetj.aspectjutils.aspectj

import android.Manifest
import android.os.Bundle
import android.os.Message
import android.widget.Button
import kotlinx.android.synthetic.main.activity_aspect.*
import me.shetj.aspect.click.SingleClick
import me.shetj.aspect.kit.SPUtils
import me.shetj.aspect.network.CheckNetwork
import me.shetj.aspect.permission.MPermission
import me.shetj.aspect.sharepre.SPrefs
import me.shetj.aspectjutils.R
import me.shetj.base.mvp.BaseActivity
import me.shetj.base.tools.app.ArmsUtils

class AspectActivity : BaseActivity<AspectPresenter>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aspect)
        initView()
        initData()
    }

    override fun initData() {
    }

    override fun initView() {

        btn_get_info.setOnClickListener {
            testAspect()
        }
        btn_get_log.setOnClickListener {
            mPresenter.testAspect()
        }
        btn_net_work.setOnClickListener {
            testNetAspect()
        }
        btn_Single_Click.setOnClickListener {
            testSingleClick(btn_Single_Click)
        }

        btn_SPrefs.setOnClickListener {
            testSPrefs()
        }


    }

    @MPermission(value = [Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE], isRequest = true)
    private fun testAspect() {
        ArmsUtils.makeText("获取权限ok")
    }

    @CheckNetwork(isNeedNet = true)
    fun testNetAspect() {
        ArmsUtils.makeText("当前有网络，所以可以说这句话")
    }

    private var int: Int = 0

    @SingleClick(value = 2000L)
    fun testSingleClick(btn: Button?) {
        ArmsUtils.makeText("点击testSingleClick" + (int++))
    }

    @SPrefs(key = "test")
    fun testSPrefs(): String {
        val get = SPUtils.get(this, "test", "xxx")
        return "hahahah ${int++}"
    }


    override fun updateView(message: Message) {
        super.updateView(message)
    }

}
