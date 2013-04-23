package com.yarin.android.Examples_08_01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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

//直接获取数据
public class myActivity05 extends Activity
{
	private final String DEBUG_TAG = "Activity05";
	private Button		mButton;
	private EditText	mEditText;
	private WebView		mWebView;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.save_http);				
		mButton   = (Button)   findViewById(R.id.Button01);
		mWebView  = (WebView)  findViewById(R.id.WebView01);
		mEditText = (EditText) findViewById(R.id.EditText01);

	    // Get the intent that started this activity
	    Intent intent = getIntent();
	    Uri data = intent.getData();

	    /*// Figure out what to do based on the intent type
	    if (intent.getType().indexOf("image/") != -1) {
	        // Handle intents with image data ...
	    } else if (intent.getType().equals("text/plain")) {
	        // Handle intents with text ...
	    }*/
		//显示Action属性
	    String uriStr = data.toString();
		mEditText.setText(uriStr);
		if (null != uriStr)
		{
			handleURL(uriStr);
		}
		//连接按钮事件监听
		mButton.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				//取得编辑框中我们输入的内容
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
				//返回前一个页面
				mWebView.goBack();
				return true;
			}
			return super.onKeyDown(keyCode, event);
	}
	/*保存网页的处理*/
	void saveWebPage(String httpUrl){
		//获得的数据
		String resultData = "";
		String FileName   = "te1st";
		URL url = null;
		try
		{
			//构造一个URL对象
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
				//使用HttpURLConnection打开连接
				HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
				//得到读取的内容(流)
				InputStreamReader in = new InputStreamReader(urlConn.getInputStream());
				// 为输出创建BufferedReader
				BufferedReader buffer = new BufferedReader(in);
				String inputLine = null;
				//使用循环来读取获得的数据
				while (((inputLine = buffer.readLine()) != null))
				{
					//我们在每一行后面加上一个"\n"来换行
					resultData += inputLine + "\n";
				}		  
				//关闭InputStreamReader
				in.close();
				//关闭http连接
				urlConn.disconnect();
				//保存串为文件
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
	/*转化网页的处理*/
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
	    	//保存网页
	    	saveWebPage(url);
	    	//判断输入的内容是不是网址
	    	if ( URLUtil.isNetworkUrl(url) )
			{  
	    		//装载网址
	    		mWebView.loadUrl(url);
			}
	    	else
	    	{
	    		mEditText.setText("输入网址错误，请重新输入");
			}
		}
		catch (Exception e) 
		{
			Log.e(DEBUG_TAG, e.toString());
		}
	}
}



