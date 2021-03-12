package me.shetj.aspectjutils.aspectj

import android.widget.Button
import me.shetj.aspectj.click.SingleClick
import me.shetj.aspectj.debug.DebugTrace
import me.shetj.aspectj.kit.SPUtils
import me.shetj.aspectj.network.CheckNetwork
import me.shetj.aspectj.sharepre.SPrefs
import me.shetj.base.mvp.BasePresenter
import me.shetj.base.mvp.IView
import me.shetj.base.tools.app.ArmsUtils

class AspectPresenter(view : IView) : BasePresenter<AspectModel>(view) {



    @DebugTrace
    fun testAspect() {
        ArmsUtils.makeText("测试测试")
    }

    @CheckNetwork(isNeedNet = true)
    fun testNetAspect() {
        ArmsUtils.makeText("当前有网络，所以可以说这句话")
    }

    private var int:Int =  0

    @SingleClick(value = 2000L)
    fun testSingleClick(btn: Button?) {
        ArmsUtils.makeText("点击testSingleClick"+(int++))
    }

    @SPrefs(key = "test")
    fun testSPrefs(): String {
        val get = SPUtils.get(view.rxContext, "test", "xxx")
        return "hahahah ${int++}"
    }


}
