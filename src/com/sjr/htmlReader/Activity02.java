package com.sjr.htmlReader;

import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//ֱ�ӻ�ȡ����
public class Activity02 extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.http);		
		TextView mTextView = (TextView)this.findViewById(R.id.TextView_HTTP);
		String strUrl = "http://mp.weixin.qq.com/mp/appmsg/show?__biz=MjM5MjAyNDUyMA==&appmsgid=10000210&itemidx=1#wechat_redirect";
		String res = null;
		Html2txt ht = new Html2txt();
		ht.execute(strUrl);
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
		mTextView.setText(res);
		//���ð����¼�����
		Button button_Back = (Button) findViewById(R.id.Button_Back);
		/* ����button���¼���Ϣ */
		button_Back.setOnClickListener(new Button.OnClickListener() 
		{
			public void onClick(View v)
			{
				/* �½�һ��Intent���� */
				Intent intent = new Intent();
				/* ָ��intentҪ�������� */
				intent.setClass(Activity02.this, Activity01.class);
				/* ����һ���µ�Activity */
				startActivity(intent);
				/* �رյ�ǰ��Activity */
				Activity02.this.finish();
			}
		});
	}
	private class Html2txt extends AsyncTask<String, Void, String>
	{
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			return new HtmlExtractor().getContent(params[0]);
		}
	}
}

