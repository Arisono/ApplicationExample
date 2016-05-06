package com.application.arithmetic;

/**
  * @author:Arison 2016年5月11日 下午5:20:36
  * @desc:算法趣味题目大全
  */
public class ArithMeticUtil {
	/**
	  * @author:Arison
	  * @desc:一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
	  */
	 public static int JumpFloorII(int target) {
	        if(target<=0)return -1;
	        if(target==1)return 1;
	        if(target>1) return 2*JumpFloorII(target-1);
	        return 0;
	    }
}
