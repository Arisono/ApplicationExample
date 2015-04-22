/**
 * 
 */
package com.test.java.desgin.factory;

/** 
 * @author LiuJie
 * ������˵������ģʽ�ʺϣ����ǳ����˴����Ĳ�Ʒ��Ҫ������
 * ���Ҿ��й�ͬ�Ľӿ�ʱ������ͨ����������ģʽ���д�����
 * �����ϵ�����ģʽ�У���һ�����������ַ������󣬲�����ȷ��������
 * ����������ڵڶ��֣�����Ҫʵ���������࣬���ԣ����������£����ǻ�ѡ�õ����֡�����̬��������ģʽ��
 * 
 * 
 * ��ͨ���󹤳�    ������
 * ��ͨ����ģʽ�����ǽ���һ�������࣬��ʵ����ͬһ�ӿڵ�һЩ�����ʵ���Ĵ�����
 */
public class FactoryTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	      SendFactory factory = new SendFactory();
	      //�ӿ����
	      //��ͨ����
	      Sender sender1 = factory.produce("sms"); 
	      Sender sender2 = factory.produce("mail"); 
//	      sender1.Send();
//	      sender2.Send();
	      //�๤��ģʽ
//	      Sender sender = factory.produceMail();  
//	      sender.Send();  
	      //��̬����ģʽ   �Ƽ�
//	      Sender sender3 = SendFactory.produceMail();  
//	      sender3.Send();  
	      //���󹤳�ģʽ   �Ƽ�
	      //��ʵ���ģʽ�ĺô����ǣ����������������һ�����ܣ�
	      //����ʱ��Ϣ����ֻ����һ��ʵ���࣬ʵ��Sender�ӿڣ�ͬʱ��һ�������࣬ʵ��Provider�ӿڣ���OK�ˣ�����ȥ�Ķ��ֳɵĴ��롣����������չ�ԽϺã�
	      Provider provider = new SendMailFactory();  
	      Sender sender = provider.produce();  
	      sender.Send();  
	}

}
