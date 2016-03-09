package com.application.mail;

import javax.mail.MessagingException;

import com.application.util.EmailUtil;

public class EmailMain {

	public static void main(String[] args) {
           try {
			EmailUtil.sendEmail("542793253@qq.com","jie.13237658359",null, "迷惑", "邮件主题内容");
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
