package com.yarin.android.Examples_08_01;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.util.Log;

public class SaveTxtSdcard {

 public static void put(String s,String name)
 {
  try 
  {
   FileOutputStream outStream = new FileOutputStream("/sdcard/"+name+".txt",true);
   OutputStreamWriter writer = new OutputStreamWriter(outStream,"gb2312");
   writer.write(s);
   writer.write("/n");
   writer.flush();
   writer.close();//�ǵùر�

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
	 String regexp = "<title>(.*?)</title>";//������ʽ���ã�title>��<title/>֮�������
	 Pattern p = Pattern.compile(regexp);
	 Matcher m = p.matcher(str);
	 if(m.find()) {
	     System.out.println(m.group(1));
	 }
 }
}