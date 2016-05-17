package com.application.java.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import com.application.util.HttpUtil;
import com.application.util.HttpUtil.Response;

public class IOUtil {
    /**
	  * @author:Arison
	  * @desc:根据指定文件路径获取InputStream流
	  */
	public static InputStream getInputStreaForFile(){
		try {
		Response response=	HttpUtil.sendGetRequest("http://www.baidu.com/", null);
		return response.getInputStream();
		} catch (Exception e) {
			System.out.println("异常");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @author Administrator
	 * @功能:InputStream转换为String
	 */
	public static String testInputStreamToStr(InputStream in,String encode) {
		/*String str = "";
		try {
			if (encode == null || encode.equals("")) {
				encode = "utf-8";
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader(in, encode));
			StringBuffer sb = new StringBuffer();

			while ((str = reader.readLine()) != null) {
				sb.append(str).append("\n");
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;*/
		
	       ByteArrayOutputStream out = new ByteArrayOutputStream();  
	       byte[] b = new byte[1024];  
	       int len = 0;  
	       try  
	       {  
	           if (encode == null || encode.equals(""))  
	           {  
	               // 默认以utf-8形式  
	               encode = "utf-8";  
	           }  
	           while ((len = in.read(b))!=-1)  
	           {  
	               out.write(b, 0, len);  
	           }  
	           return new String(out.toByteArray(), "UTF-8");  
	       }  
	       catch (IOException e)  
	       {  
	           e.printStackTrace();  
	       }  
	       return null;  
	}

	/**
	 * @author Administrator
	 * @功能:reset()方法 演示java.io.ByteArrayOutputStream.reset()方法的用法。
	 */
	public static void testByteArrayOutPutStream() {
		ByteArrayOutputStream baos = null;
		try {
			String str = "";
			baos = new ByteArrayOutputStream();
			baos.write(75);

			str = baos.toString();
			System.out.println("Before Resetting : " + str);

			baos.reset();

			baos.write(65);

			str = baos.toString();
			System.out.println("After Resetting : " + str);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (baos != null)
				try {
					baos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

}
