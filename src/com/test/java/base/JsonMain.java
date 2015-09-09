package com.test.java.base;

import com.test.java.util.StaticUtil;

public class JsonMain {
	public static void main(String[] args) {
		//1,json少字段，实体类多字段，解析成单个实体
		//StaticUtil.TestEntity();
		//2.解析成数组
		//StaticUtil.TestEntityList();
		//3,解析实体内部类?????????
		StaticUtil.TestEntityItem();
	}
}
