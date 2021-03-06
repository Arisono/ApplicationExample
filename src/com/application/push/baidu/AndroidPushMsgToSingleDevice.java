package com.application.push.baidu;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.alibaba.fastjson.JSONObject;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.PushMsgToSingleDeviceRequest;
import com.baidu.yun.push.model.PushMsgToSingleDeviceResponse;

public class AndroidPushMsgToSingleDevice {
	public static void main(String[] args) throws PushClientException,
			PushServerException {
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(7);
		for (int i = 0; i <1; i++) {
			fixedThreadPool.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
			
					try {
						pushStart();
					} catch (PushClientException e) {
						e.printStackTrace();
					} catch (PushServerException e) {
						e.printStackTrace();
					}
				}
			});
			
		}
	
	}

	private static void pushStart() throws PushClientException,
			PushServerException {
		// 1. get apiKey and secretKey from developer console
		String apiKey = "EmEVqG9NiKchcSbkoGkiyG2F2rp8YNmf";
		String secretKey = "vys9xmWtx2Oerv83usNtU64OEWOpz0Gq";
		PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

		// 2. build a BaidupushClient object to access released interfaces
		BaiduPushClient pushClient = new BaiduPushClient(pair,
				BaiduPushConstants.CHANNEL_REST_URL);

		// 3. register a YunLogHandler to get detail interacting information
		// in this request.
		pushClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				System.out.println(event.getMessage());
			}
		});

		try {
			// 4. specify request arguments
			JSONObject notification = new JSONObject();
			notification.put("title", "大师兄来访");
			notification.put("description","收到没？");
			notification.put("notification_builder_id", 0);
			notification.put("notification_basic_style",0x02+0x01+0x04);
			notification.put("open_type", 2);
			//#Intent;component=com.xzjmyk.pm.activity/.ui.message.uas.B2bMsgActivity;end
			notification.put("pkg_content", "#Intent;component=com.xzjmyk.pm.activity/.ui.message.uas.B2bMsgActivity;end");
			//notification.put("url", "http://push.baidu.com");
			JSONObject jsonCustormCont = new JSONObject();
			jsonCustormCont.put("title", "待处理流程001"); //鑷畾涔夊唴瀹癸紝key-value
			jsonCustormCont.put("url", "jsps/mobile/task.jsp?caller=ResourceAssignment!Bill%26id=11472"); //鑷畾涔夊唴瀹癸紝key-value
			jsonCustormCont.put("master", "USOFTSYS");
			jsonCustormCont.put("uu", "10041166");
			jsonCustormCont.put("pageTitle", "商务消息");
			jsonCustormCont.put("masterId", "2929");
			jsonCustormCont.put("content", "中国人民解放军");
			//jsonCustormCont.put("platform", "ERP");
//			jsonCustormCont.put("title", "待处理流程001"); //鑷畾涔夊唴瀹癸紝key-value
//			jsonCustormCont.put("url", "jsps/mobile/process.jsp?nodeId=15340079"); //鑷畾涔夊唴瀹癸紝key-value
//			jsonCustormCont.put("master", "UAS");
//			jsonCustormCont.put("uu", "10041166");
//			jsonCustormCont.put("masterId", "2929");
			notification.put("custom_content", jsonCustormCont);
			
			PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest()
					.addChannelId("3955136970545093253")//  3955136970545093253
					.addMsgExpires(new Integer(1)). 
					addMessageType(1).// 
					addMessage(notification.toString()).
					addDeviceType(3);// deviceType => 3:android, 4:ios
			// 5. http request
			PushMsgToSingleDeviceResponse response = pushClient
					.pushMsgToSingleDevice(request);

			System.out.println("msgId: " + response.getMsgId() + ",sendTime: "
					+ response.getSendTime());
		} catch (PushClientException e) {
			/*
			 * ERROROPTTYPE 鐢ㄤ簬璁剧疆寮傚父鐨勫鐞嗘柟寮� -- 鎶涘嚭寮傚父鍜屾崟鑾峰紓甯�,'true' 琛ㄧず鎶涘嚭, 'false' 琛ㄧず鎹曡幏銆�
			 */
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				e.printStackTrace();
			}
		} catch (PushServerException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				System.out.println(String.format(
						"requestId: %d, errorCode: %d, errorMessage: %s",
						e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
			}
		}
	}
}
