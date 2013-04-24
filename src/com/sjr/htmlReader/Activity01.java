package com.sjr.htmlReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity01 extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Button button_http = (Button) findViewById(R.id.Button_HTTP);
		// ����button���¼���Ϣ 
		button_http.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)
			{
				// �½�һ��Intent���� 
				Intent intent = new Intent();
				// ָ��intentҪ�������� 
				intent.setClass(Activity01.this, Activity02.class);
				// ����һ���µ�Activity 
				startActivity(intent);
				// �رյ�ǰ��Activity 
				Activity01.this.finish();
			}
		});
	
		Button button_Get = (Button) findViewById(R.id.Button_Get);
		// ����button���¼���Ϣ 
		button_Get.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)
			{
				// �½�һ��Intent���� 
				Intent intent = new Intent();
				// ָ��intentҪ�������� 
				intent.setClass(Activity01.this, Activity03.class);
				// ����һ���µ�Activity 
				startActivity(intent);
				// �رյ�ǰ��Activity 
				Activity01.this.finish();
			}
		});
		
		Button button_Post = (Button) findViewById(R.id.Button_Post);
		// ����button���¼���Ϣ 
		button_Post.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)
			{
				// �½�һ��Intent���� 
				Intent intent = new Intent();
				// ָ��intentҪ�������� 
				intent.setClass(Activity01.this, Activity04.class);
				// ����һ���µ�Activity 
				startActivity(intent);
				// �رյ�ǰ��Activity 
				Activity01.this.finish();
			}
		});

		Button button_Brow = (Button) findViewById(R.id.Button_Browse);
		// ����button���¼���Ϣ 
		button_Brow.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)
			{
				Intent intent = new Intent();        
				intent.setAction("android.intent.action.VIEW");    
				Uri content_url = Uri.parse("http://www.cnblogs.com");   
				intent.setData(content_url);  
				startActivity(intent);
			}
		});
		Button button_Brow2 = (Button) findViewById(R.id.Button_Browse2);
		// ����button���¼���Ϣ 
		button_Brow2.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)
			{
				Intent intent = new Intent();        
				intent.setAction("android.intent.action.VIEW");    
				Uri content_url = Uri.parse("http://www.cnblogs.com");   
				intent.setData(content_url);           
				intent.setClassName("com.android.browser","com.android.browser.BrowserActivity");
				startActivity(intent);
			}
		});
		Button button_Brow3 = (Button) findViewById(R.id.Button_Browse3);
		// ����button���¼���Ϣ 
		button_Brow3.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)
			{
				Intent intent = new Intent();        
				intent.setAction("android.intent.action.VIEW");    
				Uri content_url = Uri.parse("http://www.cnblogs.com");   
				intent.setData(content_url);           
				intent.setClassName("com.UCMobile","com.UCMobile.main.UCMobile");
				startActivity(intent);
			}
		});
		Button btn_GetStartOnApp = (Button) findViewById(R.id.Button_StartOn);
		// ����ť 
		btn_GetStartOnApp.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)
			{
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_SEND);
				intent.setType("text/plain");
				intent.putExtra(Intent.EXTRA_SUBJECT, "��������");
				intent.putExtra(Intent.EXTRA_TEXT,  "����ʹ�ó�����������ȫ�棬�������˺ܶ���ճ�Ӧ�÷ǳ��ã�" +
						"���ص�ַ��www.clkeji.com"); 
				startActivity(Intent.createChooser(intent, "��������")); 
				/*
				Intent intent = new Intent();        
				intent.setAction("android.intent.action.VIEW");    
				Uri content_url = Uri.parse("http://www.cnblogs.com");   
				intent.setData(content_url);           
				intent.setClassName("com.uc.browser","com.uc.browser.ActivityUpdate");
				
				Uri uri = Uri.parse("geo:52.76, -79.0342");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);*/
			}
		});
		Button btn_RtnHome = (Button) findViewById(R.id.Button_ReturnHome);
		// ����button���¼���Ϣ 
		btn_RtnHome.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)
			{
				Intent intent = new Intent();
				//ΪIntent����Action��Category����
				intent.setAction(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_HOME);
				startActivity(intent);
			}
		});
		Button btn_CtxMenu = (Button) findViewById(R.id.Button_CtxMenu);
		// ����button���¼���Ϣ 
		btn_CtxMenu.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)
			{
				Intent intent = new Intent();
				//ΪIntent����Action��Category����
				intent.setClass(Activity01.this, ContextMenuAct.class);
				startActivity(intent);
			}
		});
	}

	static final String BOOT_START_PERMISSION = "android.permission.RECEIVE_BOOT_COMPLETED";
	
	public List<Map<String,Object>> fetch_installed_apps(Context context)
	{
	    List<ApplicationInfo> packages = context.getPackageManager().getInstalledApplications(0);
	    List<Map<String, Object>>list = new ArrayList<Map<String,Object>>(packages.size());
	    Iterator<ApplicationInfo> appInfoIterator = packages.iterator();
	
	    while(appInfoIterator.hasNext())
	    {
	        ApplicationInfo app = (ApplicationInfo) appInfoIterator.next();
	        // ���Ұ�װ��package�Ƿ��п�������Ȩ��
	        if(PackageManager.PERMISSION_GRANTED == context.getPackageManager().checkPermission(BOOT_START_PERMISSION, app.packageName))
	        {
	            String label = context.getPackageManager().getApplicationLabel(app).toString();
	            Drawable appIcon = context.getPackageManager().getApplicationIcon(app);
	            Map<String,Object> map = new HashMap<String,Object>();
	            map.put("name", label);
	            map.put("desc", app.packageName);
	            map.put("img", appIcon);
	            list.add(map);
	        }
	    }
	    return list;
	}

	
	/*PackageManager pm = this.getPackageManager();

	public void getIntent1(PackageManager packageManager,String path) {       
		try {
		    PackageInfo pkginfo = packageManager.getPackageInfo(path, PackageManager.GET_ACTIVITIES);
		    if (pkginfo != null) {
		        if (pkginfo.activities != null) {
		            Intent intent = new Intent(Intent.ACTION_MAIN);
		            intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);    
		            intent.setClassName(pkginfo.packageName, pkginfo.packageName+".LoadActivity");
		            //intent.setClassName(pkginfo.packageName, pkginfo.activities[0].name);
		            startActivity(intent);
		            finish();
		            System.exit(0);
		            System.gc();
		        }
		    }
		} catch (NameNotFoundException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	}*/


}
