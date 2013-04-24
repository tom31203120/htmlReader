package com.sjr.htmlReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
//��Get��ʽ�ϴ�����
public class Activity03 extends Activity
{
	private final String DEBUG_TAG = "Activity03"; 
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.http);	
		TextView mTextView = (TextView)this.findViewById(R.id.TextView_HTTP);
		//http��ַ"?par=abcdefg"�������ϴ��Ĳ���
		String httpUrl = "http://www.google.com/search?q=everything";
		//��õ�����
		String resultData = "";
		URL url = null;
		try
		{
			//����һ��URL����
			url = new URL(httpUrl); 
		}
		catch (MalformedURLException e)
		{
			Log.e(DEBUG_TAG, "MalformedURLException");
		}
		if (url != null)
		{
			try
			{
				// ʹ��HttpURLConnection������
				HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
				//�õ���ȡ������(��)
				InputStream inputStr = urlConn.getInputStream(); 
				InputStreamReader in = new InputStreamReader(inputStr);
				// Ϊ�������BufferedReader
				BufferedReader buffer = new BufferedReader(in);
				String inputLine = null;
				//ʹ��ѭ������ȡ��õ�����
				while (((inputLine = buffer.readLine()) != null))
				{
					//������ÿһ�к������һ��"\n"������
					resultData += inputLine + "\n";
				}		  
				//�ر�InputStreamReader
				in.close();
				//�ر�http����
				urlConn.disconnect();
				mTextView.setText(resultData);
			}
			catch (IOException e)
			{
				Log.e(DEBUG_TAG, "IOException");
			}
		}
		else
		{
			Log.e(DEBUG_TAG, "Url NULL");
		}
		Button button_Back = (Button) findViewById(R.id.Button_Back);
		/* ����button���¼���Ϣ */
		button_Back.setOnClickListener(new Button.OnClickListener() 
		{
			public void onClick(View v)
			{
				/* �½�һ��Intent���� */
				Intent intent = new Intent();
				/* ָ��intentҪ�������� */
				intent.setClass(Activity03.this, Activity01.class);
				/* ����һ���µ�Activity */
				startActivity(intent);
				/* �رյ�ǰ��Activity */
				Activity03.this.finish();
			}
		});
	}
}
