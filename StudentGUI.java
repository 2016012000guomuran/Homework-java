package com.oop.beihua5;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import org.w3c.dom.views.AbstractView;

public class StudentGUI extends JFrame  implements ActionListener{
	JFrame f;
	JPanel p1,p2,p3,p4;
	JTabbedPane tab;
	Container c;
	JTable table,table2,table3,table4;
	Connection con =null;
	Statement sql;
	ResultSet rs;
	Logon L;
	String sid;
	String[] columnNames={"学号","姓名","出生日期","籍贯","入学日期","学院","专业","班级"}; //列名
	Object[][] rowData=new Object[20][8]; //表格数据
	
	String[] columnNames2={"学号","姓名","专业","班级","高数","线代","英语","C#","体育","综测分"}; //列名
	Object[][] rowData2=new Object[20][10]; //表格数据
	 
	String[] columnNames3={"学号","姓名","专业","班级","创新分"}; //列名
	Object[][] rowData3=new Object[20][5]; //表格数据
	
	String[] columnNames4={"学号","姓名","专业","班级","活动奖惩"}; //列名
	Object[][] rowData4=new Object[20][5]; //表格数据
	
	public StudentGUI(){
		f = new JFrame("学生界面");
		c = new Container();
		c = f.getContentPane();
		
		tab = new JTabbedPane(JTabbedPane.TOP);
		
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e1) {}
		
		String uri = "jdbc:mysql://localhost:3306/students?useSSL=true";
		String user = "root";
		String password = "123456";
		
		try {
			con = DriverManager.getConnection(uri,user,password);
		} catch (SQLException e2) {}
		
		try {
			sql = con.createStatement();
			rs = sql.executeQuery("SELECT * FROM mess ");
			int count=0;
			while(rs.next()){
				
				rowData[count][0]=rs.getString(1); //初始化数组内容 
				rowData[count][1]=rs.getString(2);
				rowData[count][2]=rs.getString(3);
				rowData[count][3]=rs.getString(4);
				rowData[count][4]=rs.getString(5);
				rowData[count][5]=rs.getString(6);
				rowData[count][6]=rs.getString(7);
				rowData[count][7]=rs.getString(8);
				
				rowData2[count][0] = rs.getString(1);
				rowData2[count][1] = rs.getString(2);
				rowData2[count][2] = rs.getString(7);
				rowData2[count][3] = rs.getString(8);
				rowData2[count][4] = rs.getFloat(9);
				rowData2[count][5] = rs.getFloat(10);
				rowData2[count][6] = rs.getFloat(11);
				rowData2[count][7] = rs.getFloat(12);
				rowData2[count][8] = rs.getFloat(13);
				rowData2[count][9] = rs.getFloat(14);
				
				rowData3[count][0] = rs.getString(1);
				rowData3[count][1] = rs.getString(2);
				rowData3[count][2] = rs.getString(7);
				rowData3[count][3] =rs.getString(8);
				rowData3[count][4]= rs.getString(15);
				
				rowData4[count][0] = rs.getString(1);
				rowData4[count][1] = rs.getString(2);
				rowData4[count][2] = rs.getString(7);
				rowData4[count][3] = rs.getString(8);
				rowData4[count][4] = rs.getString(16);
				
				count++;
			}
			con.close();
		} catch (Exception e3) {
			// TODO: handle exception
			System.out.println(e3);
		}
		table=new JTable(rowData,columnNames); //实例化表格
		table.setRowHeight(50);
		table.getColumn("姓名").setMaxWidth(60);
		
		table2 = new JTable(rowData2,columnNames2);
		table2.setRowHeight(50);
		table2.getColumn("姓名").setMaxWidth(120);
		
		table3 = new JTable(rowData3,columnNames3);
		table3.setRowHeight(50);
		table3.getColumn("姓名").setMaxWidth(60);
		
		table4 = new JTable(rowData4,columnNames4);
		table4.setRowHeight(50);
		table4.getColumn("姓名").setMaxWidth(60);
		 //设置行宽
		
		 Container con1=new Container(); 
		 con1.setLayout(new BorderLayout()); 
		 con1.add(new JScrollPane(table),BorderLayout.CENTER);
		 con1.setPreferredSize(new Dimension(800, 500));
		 /*con1.add(b1,BorderLayout.SOUTH);
		 con1.add(b2,BorderLayout.SOUTH);*/
		 p1.add(con1); 

		
		   
		 Container con2=new Container(); 
		 con2.setLayout(new BorderLayout()); 
		 con2.add(new JScrollPane(table2),BorderLayout.PAGE_START); //增加组件
		 con2.setPreferredSize(new Dimension(800, 500));
		 p2.add(con2); 
		 
		 Container con3 = new Container();
		 con3.setLayout(new BorderLayout());
		 con3.add(new JScrollPane(table3),BorderLayout.CENTER);
		 con3.setPreferredSize(new Dimension(800, 500));
		 p3.add(con3);
		 
		 Container con4 = new Container();
		 con4.setLayout(new BorderLayout());
		 con4.add(new JScrollPane(table4),BorderLayout.CENTER);
		 con4.setPreferredSize(new Dimension(800, 500));
		 p4.add(con4);
		
		tab.addTab("查看学籍",null,p1,"First panel");
		tab.addTab("查看成绩",null,p2,"Second panel");
		tab.addTab("查看创新分",null,p3,"Third panel");
		tab.addTab("查看活动奖惩",null,p4,"Four panel");
		c.add(tab);
		c.setBackground(Color.gray);
	
			
		
		
		f.setBounds(300, 300, 850, 500);
		f.setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		
	}

	

	

}
