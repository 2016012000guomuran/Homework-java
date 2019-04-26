package com.oop.beihua5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class TeacherTest extends JFrame implements ActionListener {
	JPanel jp1,jp2;
	JLabel j11,j12;
	JButton jb1,jb2,jb3,jb4;
	JTable jt;
	JScrollPane jsp;
	JTextField jtf;
	StuModel sm;
	
	Statement stat = null;
	PreparedStatement ps;
	Connection ct =null;
	ResultSet rs = null;
	
	public TeacherTest(){
		jp1 = new JPanel();
		jtf = new JTextField(10);
		jb1 = new JButton("查询");
		jb1.addActionListener(this);
		j11 = new JLabel("请输入学号/班级/姓名");
		
		jp1.add(j11);
		jp1.add(jtf);
		jp1.add(jb1);
		
		jb2 = new JButton("添加");
		jb2.addActionListener(this);
		jb3 = new JButton("修改");
		jb3.addActionListener(this);
		jb4 = new JButton("删除");
		jb4.addActionListener(this);
		
		jp2 = new JPanel();
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		
		sm = new StuModel();
		
		jt = new JTable(sm);
		
		jsp = new JScrollPane(jt);
		
		this.add(jsp);
		this.add(jp1,"North");
		this.add(jp2,"South");
		this.setTitle("教师界面");
		this.setBounds(200,200,1000,500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource()==jb1){
			if(jtf.getText().trim().equals(""))
				JOptionPane.showMessageDialog(this,"请输入学号，姓名或班级!");
			else {
			String name = this.jtf.getText().trim();
			String number = this.jtf.getText().trim();
			String grade = this.jtf.getText().trim();
	
			String sql ="SELECT * FROM mess where 姓名  = '"+name+"'or 学号 ='"+number+"'or 班级 = '"+grade+"' ";
			
			sm = new StuModel(sql);
			jt.setModel(sm);
			}
		}
		else if(e.getSource()==jb2){
			StuAddDiag sa = new StuAddDiag(this, "添加学生", true);
			
			sm = new StuModel();
			jt.setModel(sm);
		}else if(e.getSource()==jb4){
			int rowNum = this.jt.getSelectedRow();
			if(rowNum == -1){
				JOptionPane.showMessageDialog(this,"请选中一行");
				return ;
			}
			String 学号 = (String)sm.getValueAt(rowNum, 0);
			String 姓名 = (String)sm.getValueAt(rowNum, 1);
			String 出生年月 = (String)sm.getValueAt(rowNum, 2);
			String 籍贯 = (String)sm.getValueAt(rowNum, 3);
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				
				String url = "jdbc:mysql://localhost:3306/students?useSSL=true";
				String user = "root";
				String password = "123456";
				ct = DriverManager.getConnection(url,user,password);
				
				
				ps = ct.prepareStatement("delete from mess where 学号 = ? and 姓名 = ? and 出生年月 = ? and 籍贯 = ?" );
				//ps = ct.prepareStatement("delete form mess where 学号 = ?");
				ps.setString(1, 学号);
				ps.setString(2, 姓名);
				ps.setString(3, 出生年月);
				ps.setString(4, 籍贯);
				
				ps.executeUpdate();
			} catch (Exception e1) {
				// TODO: handle exception
				e1.printStackTrace();
			}finally{
				try {
					if(rs != null){
						rs.close();
						rs = null;
					}
					if(ps!=null){
						ps.close();
						ps = null;
					}
					if(ct != null){
						ct.close();
						ct =null;
					}
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
			sm =new StuModel();
			
			jt.setModel(sm);
			
		}else if(e.getSource()==jb3){
			int rowNum = this.jt.getSelectedRow();
			if(rowNum == -1){
				JOptionPane.showMessageDialog(this,"请选择一行");
				return ;
			}
			StuUpDiag su = new StuUpDiag(this, "修改信息", true, sm, rowNum);
			sm = new StuModel();
			jt.setModel(sm);
		}
	}

	
	/*public static void main(String[] args) {
		// TODO 自动生成的方法存根
		TeacherTest test3 = new TeacherTest();
	}*/

}
