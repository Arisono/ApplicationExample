package com.test.java.base;

import java.util.HashMap;
import java.util.Map;

public class CalendarTest {

	public static void main(String[] args) {
		// Calendar calendar=Calendar.getInstance();
		// System.out.println(calendar.get(Calendar.YEAR));
		Map<Object, Boolean> isSelected = new HashMap<Object, Boolean>();
		isSelected.put("" + 0, true);
		String niString = FlexJsonUtil.toJson(isSelected);
		isSelected = FlexJsonUtil.fromHJson(niString);
	}

}
