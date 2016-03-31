package com.application.json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

/**
 * <h1>flexjson</h1><br>
 * 1.搴忓垪鍖杍son瀛楃涓叉椂杈冨揩锛屼紭浜巎ackson<br>
 * 2.浣嗘槸鏁版嵁閲忚緝澶ф椂锛屽簭鍒楀寲鏈夐棶棰�br> 3.鍙嶅簭鍒�?��杈冩�?
 * 
 * @author yingp
 * 
 */
public class FlexJsonUtil {

	public static <T> T fromJson(String json, Class<?> cls) {
		return new JSONDeserializer<T>().use(null, cls).deserialize(json);
	}

	public static <K, V> Map<K, V> fromJson(String json) {
		Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
		return new JSONDeserializer<Map<K, V>>().use(null, map.getClass())
				.deserialize(json);
	}

	public static <K, V> HashMap<K, V> fromHJson(String json) {
		HashMap<K, V> map = new HashMap<K, V>();
		return new JSONDeserializer<HashMap<K, V>>().use(null, map.getClass())
				.deserialize(json);
	}

	/** @注释：扩充Map */
	public static <K, V> LinkedHashMap<K, V> fromJsonLink(String json) {
		LinkedHashMap<K, V> map = new LinkedHashMap<K, V>();
		return new JSONDeserializer<LinkedHashMap<K, V>>().use(null,
				map.getClass()).deserialize(json);
	}

	public String toJson() {
		return new JSONSerializer().exclude("*.class").serialize(this);
	}

	public static String toJson(Object obj) {
		return new JSONSerializer().exclude("*.class").serialize(obj);
	}

	public static <T> String toJsonArray(Collection<?> collection) {
		return new JSONSerializer().exclude("*.class").serialize(collection);
	}

	public static <T> List<T> fromJsonArray(String json, Class<?> cls) {
		return new JSONDeserializer<List<T>>().use(null, ArrayList.class)
				.use("values", cls).deserialize(json);
	}
}
