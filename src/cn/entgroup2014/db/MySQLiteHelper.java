package cn.entgroup2014.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {

	//调用父类构造器
	public MySQLiteHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	/**
	* 当数据库首次创建时执行该方法，一般将创建表等初始化操作放在该方法中执行.
	* 重写onCreate方法，调用execSQL方法创建表
	* */
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		arg0.execSQL("create table if not exists hero_info("
				+ "id integer primary key,"
				+ "name varchar,"
				+ "level integer)");
	}

	/**
	* 当打开数据库时传入的版本号与当前的版本号不同时会调用该方法
	* 
	* */
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

	}

}
