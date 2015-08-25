/**
 * 
 */
package com.test.java.desgin.adapter;

/**
 * @author LiuJie
 *
 */
public class Adapter extends Source implements Targetable {
	/** ע�ͣ��ӿڷ�������1û��ʵ�֣���Ҫ�Ǽ̳���Source �еķ���1 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.test.java.desgin.adapter.Targetable#method2()
	 */
	@Override
	public void method2() {
		System.out.println("this is the targetable method!");
	}

}
