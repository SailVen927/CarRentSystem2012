package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.sun.org.apache.bcel.internal.generic.NEW;

import dataConnect.DataBase;

public class RentCarView extends JFrame implements ActionListener{
	JButton rentButton,returnButton=null;
	JTextField brandField,typeField,colorField,daysField=null;
	//租车界面
	public RentCarView(){
		rentButton=new JButton("租赁");
		rentButton.setSize(100, 50);
		returnButton=new JButton("返回");
		returnButton.setSize(100, 50);
		brandField=new JTextField(10);
		typeField=new JTextField(10);
		colorField=new JTextField(10);
		daysField=new JTextField(10);
		//监听
		returnButton.addActionListener(this);
		rentButton.addActionListener(this);
		//布局
		JPanel p1,p2,p3,p4,p5,p7;
		JScrollPane jsp=null;
		DataBase dataBase=new DataBase();
		jsp=new JScrollPane(dataBase.show());
		this.add(jsp,BorderLayout.CENTER);
		p1=new JPanel();
		p1.add(new JLabel("品牌："));
		p1.add(brandField);
		p2=new JPanel();
		p2.add(new JLabel("型号："));
		p2.add(typeField);
		p3=new JPanel();
		p3.add(new JLabel("颜色："));
		p3.add(colorField);
		p4=new JPanel();
		p4.add(new JLabel("租期："));
		p4.add(daysField);
		p7=new JPanel();
		p5=new JPanel(new GridLayout(2,4));
		p5.add(p1);
		p5.add(p2);
		p5.add(p3);
		p5.add(p4);
		p5.add(p7);
		p5.add(rentButton);
		p5.add(returnButton);
		this.add(p5,BorderLayout.SOUTH);
		//界面设置
		this.setTitle("汽车租赁");   
		this.setIconImage(new ImageIcon("src/pictures/汽车.png").getImage());  
        this.setSize(1200,600);         
        this.setLocationRelativeTo(null);       
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    //设置当关闭窗口时，保证JVM也退出 
        this.setVisible(true);  
        this.setResizable(true); 
	}//RentView
	
	public void actionPerformed(ActionEvent e){
		DataBase dataBase=new DataBase();
		if(e.getActionCommand()=="租赁"){
			int choice=JOptionPane.showConfirmDialog(null, "请确认您要租赁的汽车信息为：\n品牌："+brandField.getText()+"\n型号："+typeField.getText()+"\n颜色："+colorField.getText()+"\n租期："+daysField.getText(),"确认信息",JOptionPane.YES_NO_OPTION);
			if(choice==JOptionPane.YES_OPTION){
				String str=dataBase.searchRentCarInform(brandField.getText(), typeField.getText(), colorField.getText());
				String[] str1=str.split("@");
				if(str1[0].equals("0")){
					JOptionPane.showMessageDialog(null, "车辆已租出或不存在！");
				}else{
					//更新车辆信息
					int temp=0;
					Date date = new Date();  
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
					Calendar calendar = Calendar.getInstance();  
				    date = calendar.getTime();  
				    String nowTime =df.format(date); 
				    dataBase.noReturnFunction("UPDATE Car SET rentday=\'"+nowTime+"\' WHERE cid=\'"+str1[0]+"\'");
					dataBase.updateInform("已出租","Car","state","cid=\'"+str1[0]+"\'");
					//新增订单
					try {
						ResultSet rs1=dataBase.getRS("SELECT MAX(onum) FROM orders");
						rs1.next();
						temp=Integer.parseInt(rs1.getString(1).trim());
						temp++;
						ResultSet rs2=dataBase.getRS("SELECT user_name FROM userIform WHERE state=1");
						rs2.next();
						dataBase.noReturnFunction("INSERT INTO orders VALUES(\'"+temp+"\',\'"+str1[0]+"\',\'"+rs2.getString(1).trim()+"\')");
						
					} catch (NumberFormatException e3){
						JOptionPane.showMessageDialog(null, "无数据！");
					} catch (SQLException e3){
						JOptionPane.showMessageDialog(null, "查询语句出错！");
					}
					try{
						JOptionPane.showMessageDialog(null,"租车成功！租金为"+Cal(Integer.parseInt(str1[1]),Integer.parseInt(daysField.getText().trim())));
					}catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "租期输入结构错误！");
						dataBase.noReturnFunction("DELETE FROM orders WHERE onum=\'"+temp+"\'");
					}
				}
				
			}else if(choice==JOptionPane.NO_OPTION){
			}
		}else if(e.getActionCommand()=="返回"){
			dispose();
		}
}//actionPerformed
//折扣	
public int Cal(int price,int days){
	if(days>150){
		return (int)(days*price*0.7);
	}else if(days>30){
		return (int)(days*price*0.8);
	}else if(days>7){
		return (int)(days*price*0.9);
	}else{
		return days*price;
	}
	}	
}

