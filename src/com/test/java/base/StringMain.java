package com.test.java.base;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.test.android.entity.ExtraWork;
import com.test.android.entity.ExtraWorkItems;
import com.test.java.util.FlexJsonUtil;

public class StringMain {

	public static void main(String[] args) {
		parseObjectIsNull();
	}
	
	/**
	  * @author Administrator
	  * @功能:测试空指针
	  */
	private static void parseObjectIsNull() {
       String json="{\"name\":\"\",\"url\":\"null\",\"age\":null}";
       String jsonArray="{\"obj\":[\"黄玉林\",\"/usr/local/apache-tomcat-7.0.32/webapps/postattach/U0323/797fad2f4be6496cb0b267176b816d63.png\"],\"success\":true}";
       String jsonArrayNull=
    		   "{\"exception\":\"程序错误\"}";
       JSONObject object=JSON.parseObject(json);
       System.out.println(object.getString("age"));
       
       
       JSONObject arrayNull=JSON.parseObject(jsonArrayNull);
       System.err.println(arrayNull.get("exception"));
       JSONArray array=JSON.parseArray(arrayNull.getString("obj"));
       System.out.println("array:"+array);
       if (array==null) {
           System.out.println(array.getString(0));
           System.out.println(array.getString(1));
	  }
      
	}
	
	
	private static void parseString() {
       String url="/usr/local/apache-tomcat-7.0.32/webapps/postattach/U0323/21811559ce3a46bc97a46ec7716b4476.png";
	   System.out.println(url.split("/")[5]);
	   System.out.println(url.split("/")[7]);
	}
	
	private static void parseJsonObject(){
		String url="{\"obj\":[\"黄玉林\",\"/usr/local/apache-tomcat-7.0.32/webapps/postattach/U0323/21811559ce3a46bc97a46ec7716b4476.png\"],\"success\":true}";
	    JSONObject object=JSON.parseObject(url);
	    JSONArray array=JSON.parseArray(object.getString("obj"));
	    System.out.println(array.getString(0));
	    System.out.println(array.getString(1));
	}
	
	private static void parseJsonWorkExtra(){
		String json="{\"detailDatas\":[{\"RN\":1,\"wod_woid\":12797,\"wod_id\":17142,\"wod_detno\":1,\"wod_empcode\":\"\",\"wod_empname\":\"U0323\",\"wod_type\":\"普通加班\",\"wod_startdate\":\"2015-12-09 09:00:00\",\"wod_enddate\":\"2015-12-09 10:00:00\",\"wod_isallday\":\"\",\"wod_count\":\"\"}],\"detailColumns\":[{\"dataIndex\":\"wod_woid\",\"caption\":\"关联id\",\"width\":0,\"format\":\"0,000\",\"render\":null,\"type\":\"numberfield\"},{\"dataIndex\":\"wod_id\",\"caption\":\"id\",\"width\":0,\"format\":\"0,000\",\"render\":null,\"type\":\"numberfield\"},{\"dataIndex\":\"wod_detno\",\"caption\":\"序号\",\"width\":0,\"format\":\"0,000\",\"render\":null,\"type\":\"numberfield\"},{\"dataIndex\":\"wod_empcode\",\"caption\":\"员工工号\",\"width\":0,\"format\":null,\"render\":null,\"type\":\"textfield\"},{\"dataIndex\":\"wod_empname\",\"caption\":\"员工姓名\",\"width\":0,\"format\":null,\"render\":null,\"type\":\"textfield\"},{\"dataIndex\":\"wod_type\",\"caption\":\"加班类型\",\"width\":0,\"format\":null,\"render\":null,\"type\":\"textfield\"},{\"dataIndex\":\"wod_startdate\",\"caption\":\"起始时间\",\"width\":0,\"format\":\"Y-m-d H:m:s\",\"render\":null,\"type\":\"datetimefield\"},{\"dataIndex\":\"wod_enddate\",\"caption\":\"截止时间\",\"width\":0,\"format\":\"Y-m-d H:m:s\",\"render\":null,\"type\":\"datetimefield\"},{\"dataIndex\":\"wod_isallday\",\"caption\":\"是否全天\",\"width\":0,\"format\":null,\"render\":null,\"type\":\"textfield\"},{\"dataIndex\":\"wod_count\",\"caption\":\"当天加班时数\",\"width\":0,\"format\":\"0,000.00\",\"render\":null,\"type\":\"numberfield\"}],\"panelItems\":[{\"caption\":\"单据编号\",\"format\":null,\"field\":\"wo_code\",\"type\":\"textfield\"},{\"caption\":\"人员类型\",\"format\":null,\"field\":\"wo_mankind\",\"type\":\"combo\"},{\"caption\":\"状态\",\"format\":null,\"field\":\"wo_status\",\"type\":\"textfield\"},{\"caption\":\"录入人\",\"format\":null,\"field\":\"wo_recorder\",\"type\":\"textfield\"},{\"caption\":\"录入时间\",\"format\":null,\"field\":\"wo_recorddate\",\"type\":\"datefield\"},{\"caption\":\"备注\",\"format\":null,\"field\":\"wo_remark\",\"type\":\"textfield\"},{\"caption\":\"工作任务\",\"format\":null,\"field\":\"wo_worktask\",\"type\":\"textfield\"},{\"caption\":\"加班时长\",\"format\":null,\"field\":\"wo_hour\",\"type\":\"textfield\"},{\"caption\":\"申请人姓名\",\"format\":null,\"field\":\"wo_emname\",\"type\":\"textfield\"},{\"caption\":\"状态码\",\"format\":null,\"field\":\"wo_statuscode\",\"type\":\"textfield\"},{\"caption\":\"ID\",\"format\":null,\"field\":\"wo_id\",\"type\":\"textfield\"}],\"panelData\":{\"wo_code\":\"WO15120005\",\"wo_mankind\":\"主管及以下\",\"wo_status\":\"已提交\",\"wo_recorder\":\"\",\"wo_recorddate\":\"2015-12-09 00:00:00\",\"wo_remark\":\"\",\"wo_worktask\":\"农机具\",\"wo_hour\":5,\"wo_emname\":\"U0323\",\"wo_statuscode\":\"COMMITED\",\"wo_id\":12797}}";
		Map<String, Object> map = FlexJsonUtil.fromJson(json);
		List<ExtraWork> leaveEntities = FlexJsonUtil.fromJsonArray("["
				+ FlexJsonUtil.toJson(map.get("panelData")) + "]",
				ExtraWork.class);
		List<ExtraWorkItems> items = FlexJsonUtil.fromJsonArray(
				FlexJsonUtil.toJson(map.get("detailDatas")),
				ExtraWorkItems.class);
		
		System.out.println(leaveEntities.get(0));
		System.out.println(items.get(0));
	}

}
