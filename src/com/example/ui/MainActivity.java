package com.example.ui;

import com.example.aa.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 创建一个BaseExpandableListAdapter对象
		ExpandableListAdapter adapter = new BaseExpandableListAdapter() {

			private String[] CDs = new String[] { "风筝", "完美的一天", "是时候" };
			private String[][] songs = new String[][] {
					{ "绿光", "不是真的爱我", "爱情字典", "练习" }
			};

			// 获取指定组位置、指定子列表项处的子列表项数据
			@Override
			public Object getChild(int groupPosition, int childPosition) {
				return songs[0][childPosition];
			}

			@Override
			public long getChildId(int groupPosition, int childPosition) {
				return childPosition;
			}

			@Override
			public int getChildrenCount(int groupPosition) {
				//return songs[0].length;
				return 1;
			}

			private TextView getTextView() {
				// 用于实现条目的虚拟列表的基类. 这里的列表没有空间的定义。
				// 例如，该类的子类可以以网格的形式、走马灯的形式显示，或者作为堆栈等等
				AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
						ViewGroup.LayoutParams.FILL_PARENT, 64); // 设置宽和高
				TextView textView = new TextView(MainActivity.this);
				textView.setLayoutParams(lp);
				textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
				//textView.setPadding(36, 0, 0, 0);
				textView.setTextSize(20);
				return textView;
			}

			// 该方法决定每个子选项的外观
			@Override
			public View getChildView(int groupPosition, int childPosition,
					boolean isLastChild, View convertView, ViewGroup parent) {				
				LinearLayout ll = new LinearLayout(MainActivity.this);
				ll.setOrientation(0);
								
				TextView textView = new TextView(MainActivity.this);
				textView.setText("   日票房");
				ll.addView(textView);
				
				TextView textView2 = new TextView(MainActivity.this);
				textView2.setText("   周票房");
				ll.addView(textView2);
				
				return ll;
			}

			// 获取指定组位置处的组数据
			@Override
			public Object getGroup(int groupPosition) {
				return CDs[groupPosition];
			}

			@Override
			public int getGroupCount() {
				return CDs.length;
			}

			@Override
			public long getGroupId(int groupPosition) {
				return groupPosition;
			}

			// 该方法决定每个组选项的外观
			@Override
			public View getGroupView(int groupPosition, boolean isExpanded,
					View convertView, ViewGroup parent) {
				LinearLayout ll = new LinearLayout(MainActivity.this);
				ll.setOrientation(0);
				
//				ImageView logo = new ImageView(MainActivity.this);
//				logo.setImageResource(logos[groupPosition]);
//				ll.addView(logo);
				
				TextView textView = new TextView(MainActivity.this);
				textView.setText(getGroup(groupPosition).toString());
				ll.addView(textView);
				
				TextView textView2 = new TextView(MainActivity.this);
				textView2.setText("   456");
				ll.addView(textView2);
				
				return ll;
			}

			@Override
			public boolean isChildSelectable(int groupPosition,
					int childPosition) {
				return true;
			}

			@Override
			public boolean hasStableIds() {
				return true;
			}
		};
		
		  
		ExpandableListView expandListView = (ExpandableListView) findViewById(R.id.list);
		
		//expandListView.setGroupIndicator(null);		
		
		expandListView.setAdapter(adapter);

	}

}
