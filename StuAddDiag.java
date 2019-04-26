package com.oop.beihua5;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;

public class StuAddDiag extends JDialog implements ActionListener {
	
	JLabel j1,j2,j3,j4,j5,j6,j7,j8,j9,j10,j11,j12,j13,j14,j15,j16;
	JTextField jf1,jf2,jf3,jf4,jf5,jf6,
	jf7,jf8,jf9,jf10,jf11,jf12,jf13,jf14,jf15,jf16;
	JPanel jp1,jp2,jp3;
	JButton jb1,jb2;

	//owner代笔父窗口，title是窗口的名字，modal 指定是模式窗口（）或非模式窗口
	public StuAddDiag(Frame owner,String title,boolean modal){
		super(owner,title,modal);
		
		j1 = new JLabel("学号");
		j2 = new JLabel("姓名");
		j3 = new JLabel("出生年月");
		j4 = new JLabel("籍贯");
		j5 = new JLabel("入学日期");
		j6 = new JLabel("学院");
		j7 = new JLabel("专业");
		j8 = new JLabel("班级");
		j9 = new JLabel("高数");
		j10 = new JLabel("线代");
		j11 = new JLabel("英语");
		j12 = new JLabel("C#");
		j13 = new JLabel("体育");
		j14 = new JLabel("综测分");
		j15 = new JLabel("创新分");
		j16 = new JLabel("活动奖惩");
		
		jf1 = new JTextField(10);
		jf2 = new JTextField(10);
		jf3 = new JTextField(10);
		jf4 = new JTextField(10);
		jf5 = new JTextField(10);
		jf6 = new JTextField(10);
		jf7 = new JTextField(10);
		jf8 = new JTextField(10);
		jf9 = new JTextField(10);
		jf10 = new JTextField(10);
		jf11 = new JTextField(10);
		jf12 = new JTextField(10);
		jf13 = new JTextField(10);
		jf14 = new JTextField(10);
		jf15 = new JTextField(10);
		jf16 = new JTextField(10);
		
		jb1 = new JButton("添加");
		jb1.addActionListener(this);
		jb2 = new JButton("取消");
		jb2.addActionListener(this);
		
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		
		jp1.setLayout(new GridLayout(16,1));
		jp2.setLayout(new GridLayout(16, 1));
		
		jp3.add(jb1);
		jp3.add(jb2);
		
		jp1.add(j1);
		jp1.add(j2);
		jp1.add(j3);
		jp1.add(j4);
		jp1.add(j5);
		jp1.add(j6);
		jp1.add(j7);
		jp1.add(j8);
		jp1.add(j9);
		jp1.add(j10);
		jp1.add(j11);
		jp1.add(j12);
		jp1.add(j13);
		jp1.add(j14);
		jp1.add(j15);
		jp1.add(j16);
		
		jp2.add(jf1);
		jp2.add(jf2);
		jp2.add(jf3);
		jp2.add(jf4);
		jp2.add(jf5);
		jp2.add(jf6);
		jp2.add(jf7);
		jp2.add(jf8);
		jp2.add(jf9);
		jp2.add(jf10);
		jp2.add(jf11);
		jp2.add(jf12);
		jp2.add(jf13);
		jp2.add(jf14);
		jp2.add(jf15);
		jp2.add(jf16);
		
		this.add(jp1,BorderLayout.WEST);
		this.add(jp2,BorderLayout.CENTER);
		this.add(jp3,BorderLayout.SOUTH);
		
		
		
		this.setSize(800, 800);
		this.setVisible(true);
		
		
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource()==jb1){
			Connection ct = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String url = "jdbc:mysql://localhost:3306/students?useSSL=true";
				String user = "root";
				String password = "123456";
				ct = DriverManager.getConnection(url,user,password);
				
				//预编译语句对象
				String strsql = "insert into mess values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				pstmt =ct.prepareStatement(strsql);
				
				//给对象赋值
				pstmt.setString(1, jf1.getText());
				pstmt.setString(2, jf2.getText());
				pstmt.setString(3, jf3.getText());
				pstmt.setString(4, jf4.getText());
				pstmt.setString(5, jf5.getText());
				pstmt.setString(6, jf6.getText());
				pstmt.setString(7, jf7.getText());
				pstmt.setString(8, jf8.getText());
				pstmt.setString(9, jf9.getText());
				pstmt.setString(10, jf10.getText());
				pstmt.setString(11, jf11.getText());
				pstmt.setString(12, jf12.getText());
				pstmt.setString(13, jf13.getText());
				pstmt.setString(14, jf14.getText());
				pstmt.setString(15, jf15.getText());
				pstmt.setString(16, jf16.getText());
				
				pstmt.executeUpdate();
				this.dispose();//关闭学生对话框
				
				
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
					if(pstmt!= null){
						pstmt.close();
						pstmt =null;
					}
					if(ct != null){
						ct.close();
						ct = null;
					}
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
		else if(e.getSource()==jb2){
			this.dispose();
		}
			
	}

	
}
