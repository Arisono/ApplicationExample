package com.questions.one;

public class QuestionsMain {

	public static void main(String[] args) {
      System.out.println(replaceSpace("liujie ", "liujie ".length()));
	}
	
	/**
	  * @author Administrator
	  * @功能:字符串互异算法
	  */
	public static boolean checkDifferent(String iniString) {
		  if(iniString.length() > 256){  
	            return false;  
	        }  
	        boolean []char_set = new boolean[256];  
	        for(int i = 0; i < iniString.length(); i++){  
	            char ch = iniString.charAt(i);  
	            if(char_set[ch])  
	                return false;  
	             char_set[ch] = true;  
	        }  
	        return true;  
	}
	
	
	   public static String replaceSpace(String iniString, int length) {  
	        // write code here  
	        int cnt = 0;  
	        int newLen;  
	        for(int i = 0; i < iniString.length(); i++){  
	            if(iniString.charAt(i) == ' ')  
	                cnt++;  
	        }  
	        
	        newLen = cnt*2 + length;  
	          
	        char []res = new char[newLen];  
	          
	        for(int i = length-1 ; i>=0; i--){  
	            if(iniString.charAt(i) == ' ')  
	            {  
	                res[newLen-1] = '0';  
	                res[newLen-2] = '2';  
	                res[newLen-3] = '%';  
	                newLen = newLen - 3;  
	            }else{  
	                res[newLen-1] = iniString.charAt(i);  
	                newLen = newLen - 1;  
	            }  
	        }  
	          
	          
	        String s = new String(res);  
	          
	        return s;  
	    }  

}
