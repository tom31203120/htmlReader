package com.sjr.htmlReader;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ContextMenuAct extends Activity {
	// Ϊÿ���˵�����һ����ʶ
	final int MENU1 = 0x111;
	final int MENU2 = 0x112;
	final int MENU3 = 0x113;
	private TextView txt;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.context_menu_main);
		txt = (TextView) findViewById(R.id.txt);
		// Ϊ�ı���ע�������Ĳ˵�
		registerForContextMenu(txt);
	}

	// ÿ�δ��������Ĳ˵�ʱ���ᴥ���÷���
	@Override
	public void onCreateContextMenu(ContextMenu menu, View source,
		ContextMenu.ContextMenuInfo menuInfo)
	{
		menu.add(0, MENU1, 0, "��ɫ");
		menu.add(0, MENU2, 0, "��ɫ");
		menu.add(0, MENU3, 0, "��ɫ");
		// �������˵�����Ϊ��ѡ�˵���
		menu.setGroupCheckable(0, true, true);
		//���������Ĳ˵��ı��⡢ͼ��
		menu.setHeaderIcon(R.drawable.tools);
		menu.setHeaderTitle("ѡ�񱳾�ɫ");
	}

	// �˵������ʱ�����÷�����
	@Override
	public boolean onContextItemSelected(MenuItem mi)
	{
		switch (mi.getItemId())
		{
			case MENU1:
				mi.setChecked(true);
				txt.setBackgroundColor(Color.RED);
				break;
			case MENU2:
				mi.setChecked(true);
				txt.setBackgroundColor(Color.GREEN);
				break;
			case MENU3:
				mi.setChecked(true);
				txt.setBackgroundColor(Color.BLUE);
				break;
		}
		return true;
	}
}
