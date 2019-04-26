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
		columnNames.add("ѧ��");
		columnNames.add("����");
		columnNames.add("��������");
		columnNames.add("����");
		columnNames.add("��ѧ����");
		columnNames.add("ѧԺ");
		columnNames.add("רҵ");
		columnNames.add("�༶");
		columnNames.add("����");
		columnNames.add("�ߴ�");
		columnNames.add("Ӣ��");
		columnNames.add("C");
		columnNames.add("����");
		columnNames.add("�۲��");
		columnNames.add("���·�");
		columnNames.add("�����");
		
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
	
	//����ѧ������
	public void addStu(String sql){
		
	}
	
	//�ڶ������캯����ͨ������sql������������ģ��
	public StuModel(String sql){
		this.init(sql);
	}
	
	//���캯�������ڳ�ʼ���ҵ�����ģ��
	public StuModel(){
		this.init("");
	}
	
	//�õ�һ���ж�����
	@Override
	public int getRowCount() {
		// TODO �Զ����ɵķ������
		return this.rowData.size();
	}

	//�õ�һ���ж�����
	@Override
	public int getColumnCount() {
		// TODO �Զ����ɵķ������
		return this.columnNames.size();
	}

	//�õ�ĳ�е�����
	@Override
	public Object getValueAt(int row, int column) {
		// TODO �Զ����ɵķ������
		return ((Vector)(this.rowData.get(row))).get(column);
	}

	//�õ���������
	public String getColumnName(int column){
		//TODO Auto-generated method stub
		return (String)this.columnNames.get(column);
	}
	
}
