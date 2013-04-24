package com.sjr.htmlReader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.ClipData;


//ֱ�ӻ�ȡ����
public class myActivity05 extends Activity
{
	private final String DEBUG_TAG = "Activity05";
	private Button		mButton;
	private EditText	mEditText;
	private WebView		mWebView;
	private TextView    mTextView;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.save_http);				
		mButton   = (Button)   findViewById(R.id.Button01);
		mWebView  = (WebView)  findViewById(R.id.WebView01);
		mTextView = (TextView) findViewById(R.id.TextView01);
		mEditText = (EditText) findViewById(R.id.EditText01);

	    // Get the intent that started this activity
	    Intent intent = getIntent();
	    ClipData cp = intent.getClipData();
	    String strCp = cp.getItemAt(0).getText().toString();
	    if (null != strCp)
	    {
	    	mEditText.setText(strCp);
	    	mTextView.setText(strCp);
	    	//mWebView.set
	    	//mTextView.set
	    }
		//��ʾAction����
	    /*// Figure out what to do based on the intent type
	    if (intent.getType().indexOf("image/") != -1) {
	        // Handle intents with image data ...
	    } else if (intent.getType().equals("text/plain")) {
	        // Handle intents with text ...
	    }*/
	    Uri data = intent.getData();
	    if (null != data)
	    {
	    	String uriStr = data.toString();
	    	mEditText.setText(uriStr);		
	    	if (null != uriStr)
			{
				handleURL(uriStr);
			}
	    }

		//���Ӱ�ť�¼�����
		mButton.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				//ȡ�ñ༭�����������������
				Intent intent = new Intent();        
				intent.setAction("android.intent.action.VIEW");    
				Uri content_url = Uri.parse("http://www.cnblogs.com");   
				intent.setData(content_url);           
				intent.setClassName("com.UCMobile","com.UCMobile.main.UCMobile");
				startActivity(intent);
		    	String url = mEditText.getText().toString();
		    	handleURL(url);
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && 
				 mWebView.canGoBack())
			{
				//����ǰһ��ҳ��
				mWebView.goBack();
				return true;
			}
			return super.onKeyDown(keyCode, event);
	}
	/*������ҳ�Ĵ���*/
	void saveWebPage(String httpUrl){
		//��õ�����
		String resultData = "";
		String FileName   = "te1st";
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
				//ʹ��HttpURLConnection������
				HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
				//�õ���ȡ������(��)
				InputStreamReader in = new InputStreamReader(urlConn.getInputStream());
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
				//���洮Ϊ�ļ�
				//mTextView.setText(resultData);
				SaveTxtSdcard.put(resultData, FileName);
				//parseWebPage(resultData);
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
	}
	/*ת����ҳ�Ĵ���*/
	void parseWebPage(String str){
        try {    
        	File file=new File("/sdcard/1/tttt.txt");
			FileWriter fw = new FileWriter(file);
            BufferedWriter bfw = new BufferedWriter(fw);
            bfw.write(str, 0, str.length());
            bfw.flush();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
	}
	void handleURL(String url)
	{
		try 
		{
	    	//������ҳ
	    	saveWebPage(url);
	    	//�ж�����������ǲ�����ַ
	    	if ( URLUtil.isNetworkUrl(url) )
			{  
	    		//װ����ַ
	    		mWebView.loadUrl(url);
			}
	    	else
	    	{
	    		mEditText.setText("������ַ��������������");
			}
		}
		catch (Exception e) 
		{
			Log.e(DEBUG_TAG, e.toString());
		}
	}
}



