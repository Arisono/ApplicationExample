package com.application.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.application.constans.Constans;
import com.application.entity.EmployeesEntity;
import com.application.entity.HrorgsEntity;
import com.application.http.httpclient.ErpHttpMain;
import com.application.util.HttpUtil.Response;

public class SQLiteMain {

	public static void main(String[] args) {
		 Connection conn=null;
		 try {
			 Class.forName("org.sqlite.JDBC");
			 conn =
			 DriverManager.getConnection("jdbc:sqlite:C:/Users/Administrator/Documents/UAS");
			@SuppressWarnings("unused")
			Statement stat =  conn.createStatement();
			 conn.setAutoCommit(true);
			 @SuppressWarnings("unused")
			String date="2016-01-22 15:45:01";
			 String date1="";
			 Response respon= ErpHttpMain.getAllHrorgEmps(
					 Constans.ERP_GETALLHRORGEMPS,"USOFTSYS",
					 date1,
					 ErpHttpMain.getCookieLogin(
							 "13352991628",
							 "az00213381",
							 "USOFTSYS"));
			 JSONObject object= JSON.parseObject(respon.getResponseText());
			InserData(conn, object);
			 
//			 ResultSet rs = stat.executeQuery("select * from employees;");
//			  while (rs.next()) {
//			      System.out.println("em_code=" + rs.getString("EM_CODE"));
//			  }
			 conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void InserData(Connection conn, JSONObject object) throws SQLException {
		List<HrorgsEntity> hrorgsEntities= JSON.parseArray(object.getString("hrorgs"),HrorgsEntity.class);
		List<EmployeesEntity> employeesEntities=JSON.parseArray(object.getString("employees"),EmployeesEntity.class);
		System.out.println("组织架构："+hrorgsEntities.size());
		System.out.println("员工数量："+employeesEntities.size());
		 
		 
	    PreparedStatement prep = conn.prepareStatement(
			      "replace into employees(EM_ID,EM_CODE,EM_NAME,EM_POSITION,EM_DEFAULTORNAME,EM_DEPART,EM_MOBILE,EM_UU,COMPANY,WHICHSYS,Em_defaultorid,Em_flag) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?);");
			   for (int i = 0; i < employeesEntities.size(); i++) {
				   EmployeesEntity entity= employeesEntities.get(i);
				   prep.setString(1, String.valueOf(entity.getEM_ID()));
				   prep.setString(2, entity.getEM_CODE());
				   prep.setString(3, entity.getEM_NAME());
				   prep.setString(4,entity.getEM_POSITION());
				   prep.setString(5, entity.getEM_DEFAULTORNAME());
				   prep.setString(6, entity.getEM_DEPART());
				   prep.setString(7, entity.getEM_MOBILE());
				   prep.setString(8 ,String.valueOf(entity.getEM_UU()));
				   prep.setString(9, entity.getCOMPANY());
				   prep.setString(10, entity.getWHICHSYS());
				   prep.setInt(11, entity.getEm_defaultorid());
				   prep.setString(12, entity.getFLAG());
				   prep.addBatch();
			   }
			    conn.setAutoCommit(false);
			    prep.executeBatch();
			    conn.setAutoCommit(true);
			    
		    prep = conn.prepareStatement(
		      "insert  into hrorgs"
		      + "(or_code,whichsys,or_subof,company,or_isleaf,or_name,or_id,or_flag)"
		      + " values (?, ?, ?, ?, ?, ?, ?,?);");
		    
		    for (int i = 0; i <hrorgsEntities.size(); i++) {
			   HrorgsEntity entity=hrorgsEntities.get(i);
			   prep.setString(1, entity.getOr_code());
			   prep.setString(2, entity.getWhichsys());
			   prep.setInt(3, entity.getOr_subof());
			   prep.setString(4,entity.getCompany());
			   prep.setInt(5, entity.getOr_isleaf());
			   prep.setString(6, entity.getOr_name());
			   prep.setInt(7, entity.getOr_id());
			   prep.setString(8, entity.getFlag());
			   prep.addBatch();
			}
		    conn.setAutoCommit(false);
		    prep.executeBatch();
		    conn.setAutoCommit(true);
			    
	}

}
