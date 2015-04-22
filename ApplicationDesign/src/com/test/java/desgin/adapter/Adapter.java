/**
 * 
 */
package com.test.java.desgin.adapter;

/**
 * @author LiuJie
 *
 */
public class Adapter extends Source implements Targetable {
	/**注释：接口方法方法1没有实现，主要是继承了Source 中的方法1 */

	/* (non-Javadoc)
	 * @see com.test.java.desgin.adapter.Targetable#method2()
	 */
	@Override
	public void method2() {
		System.out.println("this is the targetable method!");  
	}

}
