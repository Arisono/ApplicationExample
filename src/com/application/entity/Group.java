package com.application.entity;

import java.util.ArrayList;
import java.util.List;

public class Group {
	private int id;

	private String name;
    //变量名影响了json数据解析
	private List<User> users_json = new ArrayList<User>();
    //JSON解析不支持
	/*public class User {
		public int id;
		public String name;
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}*/

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers_json() {
		return users_json;
	}

	public void setUsers_json(List<User> users_json) {
		this.users_json = users_json;
	}



}
