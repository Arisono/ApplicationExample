/**
 * 
 */
package com.test.java.desgin.adapter;

/**
 * @author LiuJie
 * 适配器模式
 */
public class AdapterTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/**注释：接口  =子接口  向上转型 */
		/**注释：类的适配器模式 */
        Targetable target=new Adapter();
        target.method1();
        target.method2();
	}

}
