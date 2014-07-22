package com.example.ui;

import baidu.yun.com.Sample;

import com.baidu.inf.iis.bcs.BaiduBCS;
import com.baidu.inf.iis.bcs.auth.BCSCredentials;
import com.baidu.inf.iis.bcs.model.BCSClientException;
import com.baidu.inf.iis.bcs.model.BCSServiceException;
import com.example.aa.R;

import cn.entgroup2014.db.MySQLiteHelper;
import cn.test.common.HttpUtils;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

public class SqliteTestAc extends Activity {

	MySQLiteHelper myHelper;
	TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aa);

		/*
		 * tv = (TextView) findViewById(R.id.txt_test); //
		 * 创建MySQLiteOpenHelper辅助类对象 myHelper = new MySQLiteHelper(this,
		 * "my.db", null, 1);
		 * 
		 * // 向数据库中插入和更新数据 insertAndUpdateData(myHelper);
		 * 
		 * // 查询数据 String result = queryData(myHelper); tv.setText("名字\t等级\n" +
		 * result);
		 */

		String url="http://www.hao123.com/";
		//HttpUtils.GetCnt(url, "gb2312");
		
		String cnt = "123";
		WebView mWebView = (WebView) this.findViewById(R.id.mywebview1);
		mWebView.getSettings().setJavaScriptEnabled(true); 
		
		//mWebView.loadDataWithBaseURL(url, "", "text/html", "utf-8", null);				
		mWebView.loadUrl(url); 			
		
	}

	/**
	 * 向数据库中插入和更新数据
	 * 
	 * */
	public void insertAndUpdateData(MySQLiteHelper myHelper) {
		// 获取数据库对象
		SQLiteDatabase db = myHelper.getWritableDatabase();
		// 使用execSQL方法向表中插入数据
		db.execSQL("insert into hero_info(name,level) values('华东',10)");

		// 使用insert方法向表中插入数据
		ContentValues values = new ContentValues();
		values.put("name", "灰灰");
		values.put("level", 5);
		// 调用方法插入数据
		db.insert("hero_info", "id", values);

		// 使用update方法更新表中的数据
		// 清空ContentValues对象
		values.clear();
		values.put("name", "灰灰");
		values.put("level", 10);
		// 更新xh的level 为10
		db.update("hero_info", values, "level = 5", null);

		// 关闭SQLiteDatabase对象
		db.close();
	}

	/**
	 * 从数据库中查询数据
	 * 
	 * @param myHelper
	 * @return
	 */
	public String queryData(MySQLiteHelper myHelper) {
		String result = "";
		// 获得数据库对象
		SQLiteDatabase db = myHelper.getReadableDatabase();

		// 查询表中的数据
		Cursor cursor = db.query("hero_info", null, null, null, null, null,
				"id asc");
		// 获取name列的索引
		int nameIndex = cursor.getColumnIndex("name");
		// 获取level列的索引
		int levelIndex = cursor.getColumnIndex("level");

		for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()) {
			result = result + cursor.getString(nameIndex) + "\t\t";
			result = result + cursor.getInt(levelIndex) + "\n";
		}
		cursor.close();// 关闭结果集
		db.close();// 关闭数据库对象
		return result;
	}

	// @Override
	// protected void onDestroy() {
	// SQLiteDatabase db = myHelper.getWritableDatabase();// 获取数据库对象
	// // 删除hero_info表中所有的数据 传入1 表示删除所有行------>点击back按钮
	// db.delete("hero_info", "1", null);
	// super.onDestroy();
	// }

}
