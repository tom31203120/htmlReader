package com.yarin.android.Examples_08_01;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//��post��ʽ�ϴ�����
public class Activity04  extends Activity
{
	private final String DEBUG_TAG = "Activity04"; 
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.http);
		
		TextView mTextView = (TextView)this.findViewById(R.id.TextView_HTTP);
		//http��ַ"?par=abcdefg"�������ϴ��Ĳ���
		String httpUrl = "http://192.168.1.103/E%3A/code/android_application_sample_code/8/Examples_08_01/httpget.jsp";
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
				//��Ϊ�����post����,������Ҫ����Ϊtrue
				urlConn.setDoOutput(true);
				urlConn.setDoInput(true);
		        // ������POST��ʽ
				urlConn.setRequestMethod("POST");
		        // Post ������ʹ�û���
				urlConn.setUseCaches(false);
				urlConn.setInstanceFollowRedirects(true);
		        // ���ñ������ӵ�Content-type������Ϊapplication/x-www-form-urlencoded��
				urlConn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		        // ���ӣ���postUrl.openConnection()���˵����ñ���Ҫ��connect֮ǰ��ɣ�
		        // Ҫע�����connection.getOutputStream�������Ľ���connect��
				urlConn.connect();
				//DataOutputStream��
		        DataOutputStream out = new DataOutputStream(urlConn.getOutputStream());
		        //Ҫ�ϴ��Ĳ���
		        String content = "par=" + URLEncoder.encode("ABCDEFG", "gb2312");
		        //��Ҫ�ϴ�������д������
		        out.writeBytes(content); 
		        //ˢ�¡��ر�
		        out.flush();
		        out.close(); 
		        //��ȡ����
		        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
				String inputLine = null;
				//ʹ��ѭ������ȡ��õ�����
				while (((inputLine = reader.readLine()) != null))
				{
					//������ÿһ�к������һ��"\n"������
					resultData += inputLine + "\n";
				}		  
				reader.close();
				//�ر�http����
				urlConn.disconnect();
				//������ʾȡ�õ�����
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
				intent.setClass(Activity04.this, Activity01.class);
				/* ����һ���µ�Activity */
				startActivity(intent);
				/* �رյ�ǰ��Activity */
				Activity04.this.finish();
			}
		});
	}
}
