package com.sjr.htmlReader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.Environment;
import android.util.Log;

public class SaveTxtSdcard {

	public static void put(String s,String name)
	{
		try 
		{
			String dirpath = Environment.getExternalStorageDirectory().getPath() + "/htmlReader/";
			File wallpaperDirectory = new File(dirpath);
			// have the object build the directory structure, if needed.
			wallpaperDirectory.mkdirs();
			FileOutputStream outStream = new FileOutputStream(dirpath +name+".txt", true);
			OutputStreamWriter writer = new OutputStreamWriter(outStream,"gb2312");
			
			writer.write(s);
			writer.write("/n");
			writer.flush();
			writer.close();//记得关闭
			
			outStream.close();
		} 
		catch (Exception e)
		{
			Log.e("m", "file write error");
		} 
	}
	public static void put(String s)
	{
		String str = "<html><head><title>This a test!</title></head>";
		String regexp = "<title>(.*?)</title>";//正则表达式配置＜title>与<title/>之间的内容
		Pattern p = Pattern.compile(regexp);
		Matcher m = p.matcher(str);
		if(m.find()) {
		    System.out.println(m.group(1));
		}
	}
}