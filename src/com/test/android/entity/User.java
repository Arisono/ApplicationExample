package com.test.android.entity;

/**
 * @author LiuJie
 * @功能:测试Json解析
 */
public class User {
	private int id;
	private String name;
	private stu stu;
	private etu etu;
	//体内增加实体类是可以的
	public class stu{
		public int id ;
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
		
	}
	
	
	public class etu{
		public int id ;
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
		
	}

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

	public stu getStu() {
		return stu;
	}

	public void setStu(stu stu) {
		this.stu = stu;
	}

	public etu getEtu() {
		return etu;
	}

	public void setEtu(etu etu) {
		this.etu = etu;
	}
	
	

}
