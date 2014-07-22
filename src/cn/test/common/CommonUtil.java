package cn.test.common;

import android.content.Context;

public class CommonUtil {

	/**
	 * pxתΪdip
	 * @param context
	 * @param pxValue
	 * @return
	 */
	public static  int px2dip(Context context, float pxValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (pxValue / scale + 0.5f)-15;  
    }  
	
}
