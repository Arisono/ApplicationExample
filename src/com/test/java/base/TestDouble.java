package com.test.java.base;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDouble {

	public static void main(String[] args) {
        String str=null;
        System.out.println(""+get3BrStr( str));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}
	
	
	public static String get3BrStr(String str){
		    if (str==null) {
				return str;
			}
		    int length = str.length(); 
		    if (length<=6) {
				return str;
			}
	        int n=(length+6)/6;
		    int from = 0;  
		    int to = 0;  
		    StringBuilder builder = new StringBuilder();  
		    for (int i = 0; i < n; i++) { 
		        from = to;  
		        to = from + 6;  
		        to = to > length ? length : to;
		        if (i==n-1) {
					builder.append(str.subSequence(from, to));
				}else{
			      builder.append(str.subSequence(from, to)).append("\n");
				}
		    }  
		    String result = builder.toString();
		    return result;
	}

}
