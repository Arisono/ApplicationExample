/**
 * 
 */
package com.test.java.desgin.factory;

/**
 * @author LiuJie
 *
 */
public class SendSmsFactory implements Provider {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.test.java.desgin.Provider#produce()
	 */
	@Override
	public Sender produce() {
		// TODO Auto-generated method stub
		return new SmsSender();
	}

}
