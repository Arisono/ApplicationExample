package com.application.push.baidu;

import net.sf.json.JSONObject;

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
		// 1. get apiKey and secretKey from developer console
		String apiKey = "iF9q4ruTpFwQ9x8pY6Edqm9G";
		String secretKey = "6UPRFpWsTXwiPzbvf3jw0iD4aTZEXjsk";
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
			notification.put("title", "待处理流程单据");
			notification.put("description","需要紧急处理");
			notification.put("notification_builder_id", 0);
			notification.put("notification_basic_style",0x02+0x01+0x04);
			notification.put("open_type", 0);
			notification.put("pkg_content", "com.sk.weichat.ui.erp.activity.WebViewLoadActivity");
			//notification.put("url", "http://push.baidu.com");
			JSONObject jsonCustormCont = new JSONObject();
//			jsonCustormCont.put("title", "待处理流程001"); //鑷畾涔夊唴瀹癸紝key-value
//			jsonCustormCont.put("url", "jsps/mobile/process.jsp?nodeId=15290089"); //鑷畾涔夊唴瀹癸紝key-value
//			jsonCustormCont.put("master", "UAS");
//			jsonCustormCont.put("uu", "10041166");
//			jsonCustormCont.put("masterId", "2929");
//			
			jsonCustormCont.put("title", "待处理流程001"); //鑷畾涔夊唴瀹癸紝key-value
			jsonCustormCont.put("url", "jsps/mobile/process.jsp?nodeId=15340079"); //鑷畾涔夊唴瀹癸紝key-value
			jsonCustormCont.put("master", "UAS");
			jsonCustormCont.put("uu", "10041166");
			jsonCustormCont.put("masterId", "2929");
			notification.put("custom_content", jsonCustormCont);
			
			PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest()
					.addChannelId("3537149100550334139")//  3955136970545093253
					.addMsgExpires(new Integer(1)). // message鏈夋晥鏃堕棿
					addMessageType(1).// 1锛氶�氱煡,0:閫忎紶娑堟伅. 榛樿涓�0 娉細IOS鍙湁閫氱煡.
					addMessage(notification.toString()).
					addDeviceType(3);// deviceType => 3:android, 4:ios
			// 5. http request
			PushMsgToSingleDeviceResponse response = pushClient
					.pushMsgToSingleDevice(request);
			// Http璇锋眰缁撴灉瑙ｆ瀽鎵撳嵃
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
