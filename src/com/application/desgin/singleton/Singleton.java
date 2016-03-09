/**
 * 
 */
package com.application.desgin.singleton;

/**
 * @author LiuJie �������Singleton����һ�ֳ��õ����ģʽ��
 *         ��JavaӦ���У���������ܱ�֤��һ��JVM�У��ö���ֻ��һ��ʵ����ڡ������ģʽ�м����ô���
 *         1��ĳЩ�ഴ���Ƚ�Ƶ��������һЩ���͵Ķ�������һ�ʺܴ��ϵͳ����
 *         2��ʡȥ��new����������ϵͳ�ڴ��ʹ��Ƶ�ʣ�����GCѹ����
 *         3����Щ���罻����ĺ��Ľ������棬�����Ž������̣���������Դ�������Ļ���ϵͳ��ȫ���ˡ�
 *         ������һ����ӳ����˶��˾��Աͬʱָ�ӣ��϶����ҳ�һ�ţ�������ֻ��ʹ�õ���ģʽ�����ܱ�֤���
 *         Ľ��׷�������������������̡�
 */
public class Singleton {
	/* ����˽�о�̬ʵ���ֹ�����ã��˴���ֵΪnull��Ŀ����ʵ���ӳټ��� */
	private static Singleton instance = null;

	/* ˽�й��췽������ֹ��ʵ�� */
	private Singleton() {
	}

	/* ��̬���̷���������ʵ�� */
	public static Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}

	/* ���ö����������л������Ա�֤���������л�ǰ�󱣳�һ�� */
	public Object readResolve() {
		return instance;
	}

}
