/**
 * 
 */
package com.test.java.desgin.factory;

/**
 * @author LiuJie
 *
 */
public class SmsSender implements Sender {

	/* (non-Javadoc)
	 * @see com.test.java.desgin.Sender#Send()
	 */
	@Override
	public void Send() {
		// TODO Auto-generated method stub
     System.out.println("this is sms sender!");  
	}

}