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

		// ����һ��BaseExpandableListAdapter����
		ExpandableListAdapter adapter = new BaseExpandableListAdapter() {

			private String[] CDs = new String[] { "����", "������һ��", "��ʱ��" };
			private String[][] songs = new String[][] {
					{ "�̹�", "������İ���", "�����ֵ�", "��ϰ" }
			};

			// ��ȡָ����λ�á�ָ�����б�������б�������
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
				// ����ʵ����Ŀ�������б�Ļ���. ������б�û�пռ�Ķ��塣
				// ���磬���������������������ʽ������Ƶ���ʽ��ʾ��������Ϊ��ջ�ȵ�
				AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
						ViewGroup.LayoutParams.FILL_PARENT, 64); // ���ÿ�͸�
				TextView textView = new TextView(MainActivity.this);
				textView.setLayoutParams(lp);
				textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
				//textView.setPadding(36, 0, 0, 0);
				textView.setTextSize(20);
				return textView;
			}

			// �÷�������ÿ����ѡ������
			@Override
			public View getChildView(int groupPosition, int childPosition,
					boolean isLastChild, View convertView, ViewGroup parent) {				
				LinearLayout ll = new LinearLayout(MainActivity.this);
				ll.setOrientation(0);
								
				TextView textView = new TextView(MainActivity.this);
				textView.setText("   ��Ʊ��");
				ll.addView(textView);
				
				TextView textView2 = new TextView(MainActivity.this);
				textView2.setText("   ��Ʊ��");
				ll.addView(textView2);
				
				return ll;
			}

			// ��ȡָ����λ�ô���������
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

			// �÷�������ÿ����ѡ������
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
