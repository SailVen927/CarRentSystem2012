package view;
//finish
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

import dataConnect.DataBase;

public class ReturnCarView extends JFrame implements ActionListener{
	JButton confirmButton,returnButton=null;
	JTextField cidField=null;

	public ReturnCarView(){
		DataBase dataBase=new DataBase();
		confirmButton=new JButton("归还");
		returnButton=new JButton("返回");
		cidField=new JTextField(10);
		//布局
		JPanel p1,p2,p3;
		JScrollPane jsp;
		jsp=new JScrollPane(dataBase.userOrderShow());
		this.setLayout(new BorderLayout());
		p1=new JPanel();
		p1.add(new JLabel("请输入您要归还的车辆编号："));
		p1.add(cidField);
		p2=new JPanel();
		p2.add(confirmButton);
		p2.add(returnButton);
		p3=new JPanel(new GridLayout(2,1));
		p3.add(p1);
		p3.add(p2);
		this.add(jsp,BorderLayout.CENTER);
		this.add(p3,BorderLayout.SOUTH);
		//监听
		returnButton.addActionListener(this);
		confirmButton.addActionListener(this);
		//页面设置
		this.setTitle("汽车归还");          
        this.setSize(900,500); 
        this.setIconImage(new ImageIcon("src/pictures/汽车.png").getImage()); 
        this.setLocationRelativeTo(null);       
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    //设置当关闭窗口时，保证JVM也退出 
        this.setVisible(true);  
        this.setResizable(true); 
	}//RentView
	
	public void actionPerformed(ActionEvent e){
		DataBase dataBase=new DataBase();
		if(e.getActionCommand()=="归还"){
			int choice=JOptionPane.showConfirmDialog(null, "请确认您要归还的汽车信息为：\n编号："+cidField.getText().trim(),"确认信息",JOptionPane.YES_NO_OPTION);
			if(choice==JOptionPane.YES_OPTION){
				Date date = new Date();  
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
				Calendar calendar = Calendar.getInstance();  
			    date = calendar.getTime();  
			    String nowTime =df.format(date); 
				
				dataBase.noReturnFunction("UPDATE Car SET state=\'未出租\' WHERE cid=\'"+cidField.getText()+"\'");	
				dataBase.noReturnFunction("UPDATE Car SET returnday=\'"+nowTime+"\' WHERE cid=\'"+cidField.getText()+"\'");
					JOptionPane.showMessageDialog(null,"归还成功！欢迎下次光临！");
					System.out.println("归还成功");
					
				}else if(choice==JOptionPane.NO_OPTION){}				
		}else if(e.getActionCommand()=="返回"){
			dispose();
		}
}//actionPerformed
	
	
}
