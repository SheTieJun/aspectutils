package me.shetj.aspectutils.aspect

import android.widget.Button
import me.shetj.aspect.click.SingleClick
import me.shetj.aspect.debug.DebugTrace
import me.shetj.aspect.network.CheckNetwork
import me.shetj.aspect.sharepre.SPrefs
import me.shetj.base.base.BasePresenter
import me.shetj.base.base.IView
import me.shetj.base.kt.toMessage
import me.shetj.base.tools.app.ArmsUtils
import me.shetj.base.tools.file.SPUtils
import timber.log.Timber

class AspectPresenter(view :IView) :BasePresenter<AspectModel>(view) {


    init {
        model = AspectModel()
    }

    @DebugTrace
    fun testAspect() {
        view?.updateView("测试".toMessage())
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
        Timber.i("getInfo = $get")
        return "hahahah ${int++}"
    }


}
