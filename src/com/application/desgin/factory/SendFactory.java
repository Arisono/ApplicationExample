/**
 * 
 */
package com.application.desgin.factory;

/**
 * @author LiuJie
 */
public class SendFactory {

	/**
	 * ���󹤳�����ͨ��
	 * 
	 * @param type
	 * @return
	 */
	public Sender produce(String type) {
		if ("mail".equals(type)) {
			return new MailSender();
		} else if ("sms".equals(type)) {
			return new SmsSender();
		} else {
			System.out.println("��������ȷ������!");
			return null;
		}
	}

	/**
	 * ������������ģʽ���ṩ��������������ֱ𴴽�����
	 * ��������ģʽ��ķ�����Ϊ��̬�ģ�����Ҫ����ʵ��ֱ�ӵ��ü���
	 * 
	 * @return
	 */
	public static Sender produceMail() {
		return new MailSender();
	}

	/**
	 * ������������ģʽ���ṩ��������������ֱ𴴽�����
	 * ��������ģʽ��ķ�����Ϊ��̬�ģ�����Ҫ����ʵ��ֱ�ӵ��ü���
	 * 
	 * @return
	 */
	public static Sender produceSms() {
		return new SmsSender();
	}

}
