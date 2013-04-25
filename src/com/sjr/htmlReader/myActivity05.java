package com.sjr.htmlReader;

import java.io.File;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.ClipData;


//直接获取数据
public class myActivity05 extends Activity
{
	private final String DEBUG_TAG = "Activity05";
	private final String PATH_NAME = Environment.getExternalStorageDirectory().getPath() + "/htmlReader/"; 
	private Button		mButton;
	private EditText	mEditText;
	private TextView    mTextView;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.save_http);				
		mButton   = (Button)   findViewById(R.id.Button01);
		mTextView = (TextView) findViewById(R.id.TextView01);
		mEditText = (EditText) findViewById(R.id.EditText01);

	    // Get the intent that started this activity
	    Intent intent = getIntent();
	    String strAction = intent.getAction();
	    if ("android.intent.action.VIEW".equals(strAction))
	    {
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
	    }
	    else if ("android.intent.action.SEND".equals(strAction))
	    {
	    	/*处理ActionSend的情况*/
		    ClipData cp = intent.getClipData();
		    if (null != cp)
		    {
		    	String title = sanitizeFilename(intent.getStringExtra(Intent.EXTRA_SUBJECT));
			    String strCp = cp.getItemAt(0).getText().toString();
			    if (null != strCp)
			    {
			    	mEditText.setText(title);
			    	mTextView.setText(strCp);
			    	SaveTxtSdcard.put(strCp, title);
			    	//启动静读天下
			    	startMoonReaderp(title);
			    }
		    }
	    }

		//连接按钮事件监听
		mButton.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				Intent intent = new Intent();
				intent.setAction(android.content.Intent.ACTION_VIEW);
				String title = sanitizeFilename(mEditText.getText().toString());
				String filename = PATH_NAME + title + ".txt";
				Log.d("dddd", filename);
				File file = new File(filename);
				intent.setDataAndType(Uri.fromFile(file), "text/*");
				startActivity(intent);
				
				/*//取得编辑框中我们输入的内容
				Intent intent = new Intent();        
				intent.setAction("android.intent.action.VIEW");    
				Uri content_url = Uri.parse("http://www.cnblogs.com");   
				intent.setData(content_url);           
				intent.setClassName("com.UCMobile","com.UCMobile.main.UCMobile");
				startActivity(intent);
		    	String url = mEditText.getText().toString();
		    	handleURL(url);*/
			}
		});
	}
	
	/*异步下载网页*/
	private class Html2txt extends AsyncTask<String, Void, String>
	{
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			return new HtmlExtractor().getContent(params[0]);
		}
	}
	
	/*启动静读天下来朗读文本*/
	void startMoonReaderp(String title)
	{
		Intent intent = new Intent();
		intent.setAction(android.content.Intent.ACTION_VIEW);
		String pathname = PATH_NAME + title + ".txt";
		Log.d("dddd", pathname);
		File file = new File(pathname);
		intent.setClassName("com.flyersoft.moonreaderp","com.flyersoft.moonreaderp.ActivityMain");
		intent.setDataAndType(Uri.fromFile(file), "text/*");
		startActivity(intent);
	}
	
	void handleURL(String url)
	{
		try 
		{
	    	//判断输入的内容是不是网址
	    	if ( URLUtil.isNetworkUrl(url) )
			{  
	    		//装载网址
	    		String res = null;
	    		Html2txt ht = new Html2txt();
	    		ht.execute(url);
	    		try {
	    			res = ht.get();
	    		} catch (InterruptedException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		} catch (ExecutionException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	    		Log.i("Act2", res);
	    		String title = getFileNameFromRes(res);
	    		
	    		mEditText.setText(title);
	    		mTextView.setText(res);
	    		//保存文件
		    	SaveTxtSdcard.put(res, title);
		    	//启动静读天下
		    	startMoonReaderp(title);
			}
	    	else
	    	{
	    		mEditText.setText("网址错误,或者网址不可达");
			}
		}
		catch (Exception e) 
		{
			Log.e(DEBUG_TAG, e.toString());
		}
	}
	
	private String getFileNameFromRes(String res) {
		// TODO Auto-generated method stub
		String title = null;
		int index = 0, offset = 0;   
        if ((index = res.indexOf("\n", index + 1)) != -1) {  
        	title = sanitizeFilename(res.substring(offset, index));  
        }  
		return title;
	}
	
	private String sanitizeFilename(String unsanitized) {
		return unsanitized
				.replaceAll("[\\?\\\\/:|<>\\*]", " ") // filter out ? \ / : | < > *
				.replaceAll("\\s", "_");              // white space as underscores
	}
	
}



