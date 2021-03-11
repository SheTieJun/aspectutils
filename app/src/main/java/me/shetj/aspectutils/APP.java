package me.shetj.aspectutils;

import android.app.Application;
import android.content.Context;
import androidx.multidex.MultiDex;

import me.shetj.base.S;

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
		S.init(this,true);
	}


	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		MultiDex.install(this);
	}

}