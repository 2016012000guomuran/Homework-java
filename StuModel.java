package com.oop.beihua5;

import java.sql.*;
import java.util.*;
import javax.swing.table.*;

public class StuModel extends AbstractTableModel {
	
	Vector rowData,columnNames;
	
	Statement stat = null;
	Connection ct = null;
	ResultSet rs = null;
	
	public void init(String sql){
		if(sql.equals("")){
			sql = "SELECT * FROM mess";
		}
		columnNames = new Vector();
		columnNames.add("学号");
		columnNames.add("姓名");
		columnNames.add("出生年月");
		columnNames.add("籍贯");
		columnNames.add("入学日期");
		columnNames.add("学院");
		columnNames.add("专业");
		columnNames.add("班级");
		columnNames.add("高数");
		columnNames.add("线代");
		columnNames.add("英语");
		columnNames.add("C");
		columnNames.add("体育");
		columnNames.add("综测分");
		columnNames.add("创新分");
		columnNames.add("活动奖惩");
		
		rowData = new Vector();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/students?useSSL=true";
			String user = "root";
			String password = "123456";
			
			ct = DriverManager.getConnection(url,user,password);
			stat = ct.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				Vector hang = new Vector();
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				hang.add(rs.getString(4));
				hang.add(rs.getString(5));
				hang.add(rs.getString(6));
				hang.add(rs.getString(7));
				hang.add(rs.getString(8));
				hang.add(rs.getFloat(9));
				hang.add(rs.getFloat(10));
				hang.add(rs.getFloat(11));
				hang.add(rs.getFloat(12));
				hang.add(rs.getFloat(13));
				hang.add(rs.getFloat(14));
				hang.add(rs.getString(15));
				hang.add(rs.getString(16));
				
				rowData.add(hang);
				
			}
			
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		finally{
			try {
				if(rs!=null){
					rs.close();
					rs = null;
				}
				if(stat != null){
					stat.close();
					stat =null;
				}
				if(ct  != null){
					ct.close();
					ct= null;
				}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
	}
	
	//增加学生函数
	public void addStu(String sql){
		
	}
	
	//第二个构造函数，通过传递sql语句来获得数据模型
	public StuModel(String sql){
		this.init(sql);
	}
	
	//构造函数，用于初始化我的数据模型
	public StuModel(){
		this.init("");
	}
	
	//得到一共有多少行
	@Override
	public int getRowCount() {
		// TODO 自动生成的方法存根
		return this.rowData.size();
	}

	//得到一共有多少列
	@Override
	public int getColumnCount() {
		// TODO 自动生成的方法存根
		return this.columnNames.size();
	}

	//得到某行的数据
	@Override
	public Object getValueAt(int row, int column) {
		// TODO 自动生成的方法存根
		return ((Vector)(this.rowData.get(row))).get(column);
	}

	//得到属性名字
	public String getColumnName(int column){
		//TODO Auto-generated method stub
		return (String)this.columnNames.get(column);
	}
	
}
