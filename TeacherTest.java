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
		jb1 = new JButton("��ѯ");
		jb1.addActionListener(this);
		j11 = new JLabel("������ѧ��/�༶/����");
		
		jp1.add(j11);
		jp1.add(jtf);
		jp1.add(jb1);
		
		jb2 = new JButton("���");
		jb2.addActionListener(this);
		jb3 = new JButton("�޸�");
		jb3.addActionListener(this);
		jb4 = new JButton("ɾ��");
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
		this.setTitle("��ʦ����");
		this.setBounds(200,200,1000,500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		if(e.getSource()==jb1){
			if(jtf.getText().trim().equals(""))
				JOptionPane.showMessageDialog(this,"������ѧ�ţ�������༶!");
			else {
			String name = this.jtf.getText().trim();
			String number = this.jtf.getText().trim();
			String grade = this.jtf.getText().trim();
	
			String sql ="SELECT * FROM mess where ����  = '"+name+"'or ѧ�� ='"+number+"'or �༶ = '"+grade+"' ";
			
			sm = new StuModel(sql);
			jt.setModel(sm);
			}
		}
		else if(e.getSource()==jb2){
			StuAddDiag sa = new StuAddDiag(this, "���ѧ��", true);
			
			sm = new StuModel();
			jt.setModel(sm);
		}else if(e.getSource()==jb4){
			int rowNum = this.jt.getSelectedRow();
			if(rowNum == -1){
				JOptionPane.showMessageDialog(this,"��ѡ��һ��");
				return ;
			}
			String ѧ�� = (String)sm.getValueAt(rowNum, 0);
			String ���� = (String)sm.getValueAt(rowNum, 1);
			String �������� = (String)sm.getValueAt(rowNum, 2);
			String ���� = (String)sm.getValueAt(rowNum, 3);
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				
				String url = "jdbc:mysql://localhost:3306/students?useSSL=true";
				String user = "root";
				String password = "123456";
				ct = DriverManager.getConnection(url,user,password);
				
				
				ps = ct.prepareStatement("delete from mess where ѧ�� = ? and ���� = ? and �������� = ? and ���� = ?" );
				//ps = ct.prepareStatement("delete form mess where ѧ�� = ?");
				ps.setString(1, ѧ��);
				ps.setString(2, ����);
				ps.setString(3, ��������);
				ps.setString(4, ����);
				
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
				JOptionPane.showMessageDialog(this,"��ѡ��һ��");
				return ;
			}
			StuUpDiag su = new StuUpDiag(this, "�޸���Ϣ", true, sm, rowNum);
			sm = new StuModel();
			jt.setModel(sm);
		}
	}

	
	/*public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		TeacherTest test3 = new TeacherTest();
	}*/

}
