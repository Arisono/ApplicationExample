/**
 * 
 */
package com.test.java.desgin.factory;

/**
 * @author LiuJie 
 */
public class SendFactory {

	/** 抽象工厂【普通】
	 * @param type
	 * @return
	 */
	public Sender produce(String type) {
		if ("mail".equals(type)) {
			return new MailSender();
		} else if ("sms".equals(type)) {
			return new SmsSender();
		} else {
			System.out.println("请输入正确的类型!");
			return null;
		}
	}

	/** 而多个工厂方法模式是提供多个工厂方法，分别创建对象
	 *  工厂方法模式里的方法置为静态的，不需要创建实例，直接调用即可
	 * @return
	 */
	public static Sender produceMail() {
		return new MailSender();
	}

	/** 而多个工厂方法模式是提供多个工厂方法，分别创建对象
	 *  工厂方法模式里的方法置为静态的，不需要创建实例，直接调用即可
	 * @return
	 */
	public static Sender produceSms() {
		return new SmsSender();
	}

}
