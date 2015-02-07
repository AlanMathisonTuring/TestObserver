package com.nutegg.testobserver;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv = (TextView)this.findViewById(R.id.tv);
		Uri uri = Uri.parse("content://com.nutegg.doing/insert");
		getContentResolver().registerContentObserver(uri,true, new MyObserver(new Handler()));
	}

	@SuppressLint("NewApi")
	public class MyObserver extends ContentObserver{

		public MyObserver(Handler handler) {
			super(handler);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onChange(boolean selfChange, Uri uri) {
			// TODO Auto-generated method stub
			super.onChange(selfChange, uri);
			ContentResolver resolver = getContentResolver();
			Uri url = Uri.parse("content://com.nutegg.personprovider/query");
			Cursor cursor = resolver.query(url, null, null, null, null);//查询所以信息
			String pInfo = null;
			cursor.moveToFirst();
			int id = cursor.getInt(cursor.getColumnIndex("id"));
			int account = cursor.getInt(cursor.getColumnIndex("account"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String number = cursor.getString(cursor.getColumnIndex("number"));
			pInfo = "姓名:"+name+",电话号码:"+number+"\n";
			tv.setText(pInfo);
		}
		
	}

}
