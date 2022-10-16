package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dataConnect.DataBase;

public class MainView extends JFrame implements ActionListener{
	JButton rentButton,searchButton,fixButton,returnButton=null;
	private javax.swing.Timer timer;
	private JLabel timeLabel;
	//主界面
	public MainView(){
		searchButton=new JButton("汽车查询");
		rentButton=new JButton("汽车租赁");
		returnButton=new JButton("汽车归还");
		fixButton=new JButton("用户信息查询");
		timeLabel=new JLabel();
		//监听
		searchButton.addActionListener(this);
		rentButton.addActionListener(this);
		returnButton.addActionListener(this);
		fixButton.addActionListener(this);
		this.addWindowListener(new LoginWindowClose());
		//布局
		JPanel p1,p2,p3;
		JLabel title;
		p1=new JPanel();
		p2=new JPanel(new GridLayout(4,1));
		p3=new JPanel();
		title=new JLabel("汽车租赁系统");
		title.setFont(new Font("隶书",Font.BOLD, 60));
		this.setLayout(new BorderLayout());
		this.add(p1,BorderLayout.NORTH);
		this.add(p2,BorderLayout.CENTER);
		this.add(p3,BorderLayout.SOUTH);
		p1.add(title);
		p2.add(rentButton);
		rentButton.setBounds(0, 0, 100, 100);
		p2.add(searchButton);
		p2.add(returnButton);
		p2.add(fixButton);
		p3.add(timeLabel);
		timer=new javax.swing.Timer(500, new ActionListener(){//时间显示
			@Override
			public void actionPerformed(ActionEvent arg0) {
				timeLabel.setText(new SimpleDateFormat("yyyy年MM月dd日 EEEE hh:mm:ss").format(new Date()));
			}
		});
		timer.start();
	    //窗口设置
        this.setTitle("欢迎使用汽车租赁系统！");     
        this.setIconImage(new ImageIcon("src/pictures/汽车.png").getImage());  
        this.setSize(900,600);         
        this.setLocationRelativeTo(null);       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //设置当关闭窗口时，保证JVM也退出 
        this.setVisible(true);  
        this.setResizable(true);  
		
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand()=="汽车查询"){  
			 SearchCarView searchCarView=new SearchCarView(); 
	    }else if(e.getActionCommand()=="汽车租赁"){
	    	 RentCarView rentCarView=new RentCarView();      
	    }else if(e.getActionCommand()=="汽车归还"){
	    	 ReturnCarView returnCarView=new ReturnCarView();
	    }else if(e.getActionCommand()=="用户信息查询"){
	    	 UserIformView userIformView=new UserIformView(); 	
	    }
	}
	
	 
	
}
