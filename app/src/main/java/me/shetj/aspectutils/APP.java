package me.shetj.aspectutils;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import me.shetj.base.s;

/**
 * <b>@packageName：</b> com.ebu.master<br>
 * <b>@author：</b> shetj<br>
 * <b>@createTime：</b> 2018/2/26<br>
 * <b>@company：</b><br>
 * <b>@email：</b> 375105540@qq.com<br>
 * <b>@describe</b><br>
 */

public class APP extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		s.init(this,true, "");
	}


	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		MultiDex.install(this);
	}

}