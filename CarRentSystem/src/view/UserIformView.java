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
		JButton upNameButton=new JButton("�����û���");
		JButton upPwdButton=new JButton("��������");
		JButton returnButton=new JButton("����");

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
		        p2.add(name=new JLabel("�û�����"+rs.getString(1)));
		        name.setFont(new Font("����",Font.BOLD, 30));
		        p3.add(pwd=new JLabel(" ���룺"+rs.getString(2)));
		        pwd.setFont(new Font("����",Font.BOLD, 30));
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
		
		 this.setTitle("������Ϣ"); 
		 this.setIconImage(new ImageIcon("src/pictures/����.png").getImage());          
	     this.setSize(600,600);         
	     this.setLocationRelativeTo(null);       
	     this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    //���õ��رմ���ʱ����֤JVMҲ�˳� 
	     this.setVisible(true);  
	     this.setResizable(true);  
	}//RentView
	
	public void pwdChange(){
		pwdIformJWindow=new JFrame();
		pwdIformJWindow.setIconImage(new ImageIcon("src/pictures/����.png").getImage());  
		JButton updateButton,returnButton=null;
		
		passField1=new JPasswordField(20);
		passField2=new JPasswordField(20);
		JPanel p1,p2,p3,p4;	
		dataBase=new DataBase();
		updateButton=new JButton("�޸�");
		//returnButton=new JButton("����");
		
		p1=new JPanel();
		p2=new JPanel(new FlowLayout(FlowLayout.CENTER));
		p3=new JPanel(new FlowLayout(FlowLayout.CENTER));
		p4=new JPanel();

		//����
		//ActionListener a1=new IformChange();
		//returnButton.addActionListener(this);
		updateButton.addActionListener(this);
		
		//����
		p1=new JPanel(new GridLayout(3,1));
		p2.add(new JLabel("�����룺"));
		p2.add(passField1);
		p3.add(new JLabel("ȷ�������룺"));
		p3.add(passField2);
		p4.add(updateButton);
		//p4.add(returnButton);
		p1.add(p2);
		p1.add(p3);
		p1.add(p4);
		pwdIformJWindow.add(p1);
		
		pwdIformJWindow.setTitle("�û������޸�");          
		pwdIformJWindow.setSize(650,300);         
		pwdIformJWindow.setLocationRelativeTo(null);       
		pwdIformJWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    //���õ��رմ���ʱ����֤JVMҲ�˳� 
		pwdIformJWindow.setVisible(true);  
		pwdIformJWindow.setResizable(true); 
	}
	public void nameChange(){
		nameIformJWindow=new JFrame();
		JButton updateButton,returnButton=null;
		nameField1=new JTextField(10);
		JPanel p1,p2,p3,p4;	
		dataBase=new DataBase();
		updateButton=new JButton("�޸�");
		
		p1=new JPanel();
		p2=new JPanel();
		p3=new JPanel();
		p4=new JPanel();

		//����
		updateButton.addActionListener(this);
		
		//����
		p1=new JPanel(new GridLayout(2,1));
		
		p2.add(new JLabel("���û�����"));
		p2.add(nameField1);
		p4.add(updateButton);
		p1.add(p2);
		p1.add(p4);
		nameIformJWindow.add(p1);
		nameIformJWindow.setTitle("�û����޸�");          
		nameIformJWindow.setSize(500,300);         
		nameIformJWindow.setLocationRelativeTo(null);       
		nameIformJWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		nameIformJWindow.setVisible(true);  
		nameIformJWindow.setResizable(true); 
	}
	
	//@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand()=="�����û���"){  
			   flag=1;
        	   nameChange();
        	}else if(e.getActionCommand()==("��������")){
        	   flag=0;
        	   pwdChange();
        	}else if(e.getActionCommand()=="�޸�" && flag==1){
						dataBase=new DataBase();
						dataBase.noReturnFunction("ALTER TABLE orders\n DROP CONSTRAINT FK_orders_userIform");
						dataBase.noReturnFunction("UPDATE orders SET fk_user_name=\'"+nameField1.getText()+"\' ");
						dataBase.noReturnFunction("UPDATE userIform SET user_name=\'"+nameField1.getText()+"\' WHERE state=1");
						dataBase.noReturnFunction("ALTER TABLE orders\n ADD CONSTRAINT FK_orders_userIform FOREIGN KEY(fk_user_name) REFERENCES userIform(user_name)  ");
						JOptionPane.showMessageDialog(null,"�����û����ɹ���");		
						nameIformJWindow.dispose();
        	}else if(e.getActionCommand()=="�޸�" && flag==0){
        		if(passField1.getText().equals(passField2.getText())){//����һ��
					int choice=JOptionPane.showConfirmDialog(null, "��ȷ��Ҫ�޸�������"," ",JOptionPane.YES_NO_OPTION);
					if(choice==JOptionPane.YES_OPTION){
						dataBase=new DataBase();
						dataBase.noReturnFunction("UPDATE userIform SET user_pwd=\'"+passField1.getText()+"\' WHERE state=1");
						JOptionPane.showMessageDialog(null,"��������ɹ���");
						System.out.println("���³ɹ���");
						pwdIformJWindow.dispose();
					}else if(choice==JOptionPane.NO_OPTION){
						
					}
				}else{//���벻һ��
					JOptionPane.showMessageDialog(null,"�����������벻ͬ��������");
				}
        	}else if(e.getActionCommand()=="����"){  
        		dispose();
        }
	}
	
}


