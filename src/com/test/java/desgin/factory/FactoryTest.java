/**
 * 
 */
package com.test.java.desgin.factory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * @author LiuJie
 * 总体来说，工厂模式适合：凡是出现了大量的产品需要创建，
 * 并且具有共同的接口时，可以通过工厂方法模式进行创建。
 * 在以上的三种模式中，第一种如果传入的字符串有误，不能正确创建对象，
 * 第三种相对于第二种，不需要实例化工厂类，所以，大多数情况下，我们会选用第三种——静态工厂方法模式。
 * 
 * 
 * 普通抽象工厂    测试类
 * 普通工厂模式，就是建立一个工厂类，对实现了同一接口的一些类进行实例的创建。
 */
public class FactoryTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	      SendFactory factory = new SendFactory();
	      //接口设计
	      //普通工厂
	      Sender sender1 = factory.produce("sms"); 
	      Sender sender2 = factory.produce("mail"); 
//	      sender1.Send();
//	      sender2.Send();
	      //多工厂模式
//	      Sender sender = factory.produceMail();  
//	      sender.Send();  
	      //静态工厂模式   推荐
//	      Sender sender3 = SendFactory.produceMail();  
//	      sender3.Send();  
	      //抽象工厂模式   推荐
	      //其实这个模式的好处就是，如果你现在想增加一个功能：
	      //发及时信息，则只需做一个实现类，实现Sender接口，同时做一个工厂类，实现Provider接口，就OK了，无需去改动现成的代码。这样做，拓展性较好！
	      Provider provider = new SendMailFactory();  
	      Sender sender = provider.produce();  
	      sender.Send(); 
	      //测试数据
//	      System.out.println("123.123.123.908090".split(":")[0]);
	      System.out.println(isIpAddress("123.123.123.908090".split(":")[0]));
	}
	
	
	/**
     * 检验是否是合法的IP地址
     * @param address String IP地址
     * @return boolean IP地址是否合法
     */
    public static boolean isIpAddress(String address){

        String regex = "^((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])\\.){3}(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])$";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(address);

        return m.matches();
    }

}
