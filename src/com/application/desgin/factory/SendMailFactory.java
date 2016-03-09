/**
 * 
 */
package com.application.desgin.factory;

/**
 * @author LiuJie
 *
 */
public class SendMailFactory implements Provider {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.test.java.desgin.Provider#produce()
	 */
	@Override
	public Sender produce() {
		// TODO Auto-generated method stub
		return new MailSender();
	}

}
