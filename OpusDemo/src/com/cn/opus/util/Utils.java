package com.cn.opus.util;

import android.os.Environment;

public class Utils {

	public static boolean isSdPrepare() {
		String status = Environment.getExternalStorageState();
		if (status.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}
}
