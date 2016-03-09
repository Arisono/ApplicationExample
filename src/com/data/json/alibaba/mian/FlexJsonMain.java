package com.data.json.alibaba.mian;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.test.android.entity.LeaveEntity;



public class FlexJsonMain {

	public static void main(String[] args) {
        String json="[{\"va_startime\":\"2016-03-07 09:20:33\",\"va_mankind\":\"\",\"va_status\":\"在录入\",\"va_department\":\"采购部\",\"va_remark\":\"结婚\",\"va_endtime\":\"2016-03-07 09:20:33\",\"va_position\":\"窝边草\",\"va_vacationtype\":\"婚假\",\"va_holidaytype\":\"全日\",\"va_id\":14710,\"va_date\":\"\",\"va_code\":\"AL16030011\",\"va_alldays\":12,\"va_alltimes\":50,\"va_emcode\":\"1212\",\"va_emname\":\"兔崽子\"}]";

		List<LeaveEntity> leaveEntities =
//			FlexJsonUtil.fromJsonArray(json, LeaveEntity.class);
		JSON.parseArray(json, LeaveEntity.class);
		System.out.println(leaveEntities.toString());
		System.out.println(leaveEntities.get(0).getVa_alltimes());
	}

}
