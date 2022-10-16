package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JWindow;

import com.sun.org.apache.bcel.internal.generic.NEW;

import dataConnect.DataBase;

public class UserIformView extends JFrame implements ActionListener{
	DataBase dataBase;
	JPasswordField passField1,passField2=null; 
	JTextField nameField1=null;
	JLabel name,pwd;
	JFrame pwdIformJWindow,nameIformJWindow;
	int flag=0;
	
	public UserIformView(){
		
		dataBase=new DataBase();
		JButton upNameButton=new JButton("更新用户名");
		JButton upPwdButton=new JButton("更新密码");
		JButton returnButton=new JButton("返回");

		upNameButton.addActionListener(this);
		upPwdButton.addActionListener(this);
		returnButton.addActionListener(this);
		
		JPanel p1=new JPanel(new GridLayout(3,1));
		JPanel p2=new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel p3=new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel p4=new JPanel();
		ResultSet rs=dataBase.getRS("select user_name,user_pwd from userIform where state=1");
		try {
			    rs.next();
		        p2.add(name=new JLabel("用户名："+rs.getString(1)));
		        name.setFont(new Font("黑体",Font.BOLD, 30));
		        p3.add(pwd=new JLabel(" 密码："+rs.getString(2)));
		        pwd.setFont(new Font("黑体",Font.BOLD, 30));
		        p4.add(upNameButton);
		        p4.add(upPwdButton);
		        p4.add(returnButton);
		        p1.add(p2);
		        p1.add(p3);
		        p1.add(p4);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.add(p1);
		
		 this.setTitle("个人信息"); 
		 this.setIconImage(new ImageIcon("src/pictures/汽车.png").getImage());          
	     this.setSize(600,600);         
	     this.setLocationRelativeTo(null);       
	     this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    //设置当关闭窗口时，保证JVM也退出 
	     this.setVisible(true);  
	     this.setResizable(true);  
	}//RentView
	
	public void pwdChange(){
		pwdIformJWindow=new JFrame();
		pwdIformJWindow.setIconImage(new ImageIcon("src/pictures/汽车.png").getImage());  
		JButton updateButton,returnButton=null;
		
		passField1=new JPasswordField(20);
		passField2=new JPasswordField(20);
		JPanel p1,p2,p3,p4;	
		dataBase=new DataBase();
		updateButton=new JButton("修改");
		//returnButton=new JButton("返回");
		
		p1=new JPanel();
		p2=new JPanel(new FlowLayout(FlowLayout.CENTER));
		p3=new JPanel(new FlowLayout(FlowLayout.CENTER));
		p4=new JPanel();

		//监听
		//ActionListener a1=new IformChange();
		//returnButton.addActionListener(this);
		updateButton.addActionListener(this);
		
		//布局
		p1=new JPanel(new GridLayout(3,1));
		p2.add(new JLabel("新密码："));
		p2.add(passField1);
		p3.add(new JLabel("确认新密码："));
		p3.add(passField2);
		p4.add(updateButton);
		//p4.add(returnButton);
		p1.add(p2);
		p1.add(p3);
		p1.add(p4);
		pwdIformJWindow.add(p1);
		
		pwdIformJWindow.setTitle("用户密码修改");          
		pwdIformJWindow.setSize(650,300);         
		pwdIformJWindow.setLocationRelativeTo(null);       
		pwdIformJWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    //设置当关闭窗口时，保证JVM也退出 
		pwdIformJWindow.setVisible(true);  
		pwdIformJWindow.setResizable(true); 
	}
	public void nameChange(){
		nameIformJWindow=new JFrame();
		JButton updateButton,returnButton=null;
		nameField1=new JTextField(10);
		JPanel p1,p2,p3,p4;	
		dataBase=new DataBase();
		updateButton=new JButton("修改");
		
		p1=new JPanel();
		p2=new JPanel();
		p3=new JPanel();
		p4=new JPanel();

		//监听
		updateButton.addActionListener(this);
		
		//布局
		p1=new JPanel(new GridLayout(2,1));
		
		p2.add(new JLabel("新用户名："));
		p2.add(nameField1);
		p4.add(updateButton);
		p1.add(p2);
		p1.add(p4);
		nameIformJWindow.add(p1);
		nameIformJWindow.setTitle("用户名修改");          
		nameIformJWindow.setSize(500,300);         
		nameIformJWindow.setLocationRelativeTo(null);       
		nameIformJWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		nameIformJWindow.setVisible(true);  
		nameIformJWindow.setResizable(true); 
	}
	
	//@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand()=="更新用户名"){  
			   flag=1;
        	   nameChange();
        	}else if(e.getActionCommand()==("更新密码")){
        	   flag=0;
        	   pwdChange();
        	}else if(e.getActionCommand()=="修改" && flag==1){
						dataBase=new DataBase();
						dataBase.noReturnFunction("ALTER TABLE orders\n DROP CONSTRAINT FK_orders_userIform");
						dataBase.noReturnFunction("UPDATE orders SET fk_user_name=\'"+nameField1.getText()+"\' ");
						dataBase.noReturnFunction("UPDATE userIform SET user_name=\'"+nameField1.getText()+"\' WHERE state=1");
						dataBase.noReturnFunction("ALTER TABLE orders\n ADD CONSTRAINT FK_orders_userIform FOREIGN KEY(fk_user_name) REFERENCES userIform(user_name)  ");
						JOptionPane.showMessageDialog(null,"更新用户名成功！");		
						nameIformJWindow.dispose();
        	}else if(e.getActionCommand()=="修改" && flag==0){
        		if(passField1.getText().equals(passField2.getText())){//密码一致
					int choice=JOptionPane.showConfirmDialog(null, "您确认要修改密码吗？"," ",JOptionPane.YES_NO_OPTION);
					if(choice==JOptionPane.YES_OPTION){
						dataBase=new DataBase();
						dataBase.noReturnFunction("UPDATE userIform SET user_pwd=\'"+passField1.getText()+"\' WHERE state=1");
						JOptionPane.showMessageDialog(null,"更新密码成功！");
						System.out.println("更新成功！");
						pwdIformJWindow.dispose();
					}else if(choice==JOptionPane.NO_OPTION){
						
					}
				}else{//密码不一致
					JOptionPane.showMessageDialog(null,"两次输入密码不同！请重试");
				}
        	}else if(e.getActionCommand()=="返回"){  
        		dispose();
        }
	}
	
}


