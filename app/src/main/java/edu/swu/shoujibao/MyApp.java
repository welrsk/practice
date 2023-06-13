package edu.swu.shoujibao;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

public class MyApp extends Application {
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		// 1. 初始化AVOS Cloud
		AVOSCloud.initialize(this,
		"XibaWU8DjJe5y8YRQF3HVHHm-gzGzoHsz","wUQNDHKVkCekz37qCd8o73PC");
		//AVOSCloud.setDebugLogEnabled(true);//开启调试日志
	}
}
