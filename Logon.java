package com.oop.beihua5;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Logon extends JFrame implements ActionListener {
	
	JFrame f;
	Label username,userpassword,usertaype;
	JRadioButton student,manger;
	JButton b1,b2;
	JTextField t1;
	JPasswordField t2;
	
	public Logon(){
		
		f = new JFrame("欢迎登录学生管理系统");
		username = new Label("账号:");
		userpassword = new Label("密码:");
		usertaype = new Label("用户类型:");
		ButtonGroup BR = new ButtonGroup();
		student = new JRadioButton("学生");
		manger = new JRadioButton("管理员");
		t1 = new JTextField(15);
		t2 = new JPasswordField(15);
		b1 = new JButton("登录");
		b2 = new JButton("取消");
		BR.add(student);
		BR.add(manger);
		student.setSelected(true);
		f.add(username);
		f.add(t1);
		f.add(userpassword);
		f.add(t2);
		f.add(usertaype);
		f.add(student);
		f.add(manger);
		f.add(b1);
		f.add(b2);

		f.setBounds(200, 200, 400, 400);
		
		GridBagLayout layout = new GridBagLayout();
		f.setLayout(layout);
		GridBagConstraints s = new GridBagConstraints();
		s.fill = GridBagConstraints.NONE;
		
		s.insets = new Insets(0,0,15,15);
		s.gridwidth = 1;
		s.weightx = 0;
		s.weighty = 0;
		layout.setConstraints(username,s);
		
		s.gridwidth = 0;
		s.weightx = 0;
		s.weighty = 0;
		layout.setConstraints(t1,s);
		
		s.gridwidth = 1;
		s.weightx = 0;
		s.weighty = 0;
		layout.setConstraints(userpassword,s);
		
		s.gridwidth = 0;
		s.weightx = 0;
		s.weighty = 0;
		layout.setConstraints(t2,s);
		
		s.gridwidth = 1;
		s.weightx = 0;
		s.weighty = 0;
		layout.setConstraints(usertaype,s);
		
		s.gridwidth = 1;
		s.weightx = 0;
		s.weighty = 0;
		layout.setConstraints(student,s);
		
		s.gridwidth = 0;
		s.weightx = 0;
		s.weighty = 0;
		layout.setConstraints(manger,s);
		
		GridBagConstraints s1 = new GridBagConstraints();
		s1.insets = new Insets(0, 60, 0, 0);
		s1.gridwidth = 1;
		s1.weightx = 0;
		s1.weighty = 0;
		layout.setConstraints(b1,s1);
		
		GridBagConstraints s2 = new GridBagConstraints();
		s2.insets = new Insets(0, 40, 0, 0);
		s2.gridwidth = 0;
		s2.weightx = 0;
		s2.weighty = 0;
		layout.setConstraints(b2,s2);
		
		//student.addActionListener(this);
		//manger.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		
		/*ImageIcon background = new ImageIcon("C:\\Users\\qiyuh\\Pictures\\Saved Pictures\\2.jpg");
		JLabel label = new JLabel(background);
		label.setBounds(0, 0, this.getWidth(), this.getHeight());
		JPanel imagePanel = (JPanel)this.getContentPane();
		imagePanel.setOpaque(false);
		this.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));*/
		
		
		f.setVisible(true);
	}
	

	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		String url = "jdbc:mysql://localhost:3306/user?useSSL=true";
		String user = "root";
		String password = "123456";
		// TODO 自动生成的方法存根
		Statement stat = null;
		Connection ct = null;
		ResultSet rs = null;
		
		String ID = t1.getText();

		String psw=new String(t2.getPassword());
		
		if(e.getSource()==b1){
			if(student.isSelected()){
			//System.out.println("111");
			try {
				
				ct = DriverManager.getConnection(url,user,password);
				stat = ct.createStatement();
				rs = stat.executeQuery("SELECT * FROM student where id = '"+ID+"'");
				if(rs.next()){
					//System.out.print(psw.toString());
					if(rs.getString("PassWord").equals(psw)){
						JOptionPane.showMessageDialog(f, "欢迎你!");
						f.setVisible(false);
						new StudentGUI();
						
					}
					else{
						JOptionPane.showMessageDialog(f, "对不起,密码错误,请重新输入!");
						t2.setText("");
					}
					
				}
				else if(ID.equals("")){
					JOptionPane.showMessageDialog(f, "请输入用户名");
					
					
				}
				else{
					JOptionPane.showMessageDialog(f, "用户不存在");
					t1.setText("");
					t2.setText("");
				}
			} catch (Exception e2) {
				// TODO: handle exception
				
			}
		}
			else if(manger.isSelected()){
				try {
					
					ct = DriverManager.getConnection(url,user,password);
					stat = ct.createStatement();
					rs = stat.executeQuery("SELECT * FROM teacher where id = '"+ID+"'");
					if(rs.next()){
						//System.out.print(psw.toString());
						if(rs.getString("PassWord").equals(psw)){
							JOptionPane.showMessageDialog(f, "欢迎你!");
							f.setVisible(false);
							new TeacherTest();
							
						}
						else{
							JOptionPane.showMessageDialog(f, "对不起,密码错误,请重新输入!");
							t2.setText("");
						}
						
					}
					else if(ID.equals("")){
						JOptionPane.showMessageDialog(f, "请输入用户名");
						
						
					}
					else{
						JOptionPane.showMessageDialog(f, "用户不存在");
					}
				} catch (Exception e2) {
					// TODO: handle exception
					
				}
			}
		}
		if(e.getSource()==b2){
			 int exi = JOptionPane.showConfirmDialog (f, "要退出该程序吗？", "友情提示", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
             if (exi == JOptionPane.YES_OPTION)
             {
                 System.exit (0);
             }
             else
             {
                 return;
             }
		}
	}

	
}
