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
		 * ����MySQLiteOpenHelper��������� myHelper = new MySQLiteHelper(this,
		 * "my.db", null, 1);
		 * 
		 * // �����ݿ��в���͸������� insertAndUpdateData(myHelper);
		 * 
		 * // ��ѯ���� String result = queryData(myHelper); tv.setText("����\t�ȼ�\n" +
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
	 * �����ݿ��в���͸�������
	 * 
	 * */
	public void insertAndUpdateData(MySQLiteHelper myHelper) {
		// ��ȡ���ݿ����
		SQLiteDatabase db = myHelper.getWritableDatabase();
		// ʹ��execSQL��������в�������
		db.execSQL("insert into hero_info(name,level) values('����',10)");

		// ʹ��insert��������в�������
		ContentValues values = new ContentValues();
		values.put("name", "�һ�");
		values.put("level", 5);
		// ���÷�����������
		db.insert("hero_info", "id", values);

		// ʹ��update�������±��е�����
		// ���ContentValues����
		values.clear();
		values.put("name", "�һ�");
		values.put("level", 10);
		// ����xh��level Ϊ10
		db.update("hero_info", values, "level = 5", null);

		// �ر�SQLiteDatabase����
		db.close();
	}

	/**
	 * �����ݿ��в�ѯ����
	 * 
	 * @param myHelper
	 * @return
	 */
	public String queryData(MySQLiteHelper myHelper) {
		String result = "";
		// ������ݿ����
		SQLiteDatabase db = myHelper.getReadableDatabase();

		// ��ѯ���е�����
		Cursor cursor = db.query("hero_info", null, null, null, null, null,
				"id asc");
		// ��ȡname�е�����
		int nameIndex = cursor.getColumnIndex("name");
		// ��ȡlevel�е�����
		int levelIndex = cursor.getColumnIndex("level");

		for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()) {
			result = result + cursor.getString(nameIndex) + "\t\t";
			result = result + cursor.getInt(levelIndex) + "\n";
		}
		cursor.close();// �رս����
		db.close();// �ر����ݿ����
		return result;
	}

	// @Override
	// protected void onDestroy() {
	// SQLiteDatabase db = myHelper.getWritableDatabase();// ��ȡ���ݿ����
	// // ɾ��hero_info�������е����� ����1 ��ʾɾ��������------>���back��ť
	// db.delete("hero_info", "1", null);
	// super.onDestroy();
	// }

}
