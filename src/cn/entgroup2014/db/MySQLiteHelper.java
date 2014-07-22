package cn.entgroup2014.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {

	//���ø��๹����
	public MySQLiteHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	/**
	* �����ݿ��״δ���ʱִ�и÷�����һ�㽫������ȳ�ʼ���������ڸ÷�����ִ��.
	* ��дonCreate����������execSQL����������
	* */
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		arg0.execSQL("create table if not exists hero_info("
				+ "id integer primary key,"
				+ "name varchar,"
				+ "level integer)");
	}

	/**
	* �������ݿ�ʱ����İ汾���뵱ǰ�İ汾�Ų�ͬʱ����ø÷���
	* 
	* */
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

	}

}
