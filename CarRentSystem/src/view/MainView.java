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
	//������
	public MainView(){
		searchButton=new JButton("������ѯ");
		rentButton=new JButton("��������");
		returnButton=new JButton("�����黹");
		fixButton=new JButton("�û���Ϣ��ѯ");
		timeLabel=new JLabel();
		//����
		searchButton.addActionListener(this);
		rentButton.addActionListener(this);
		returnButton.addActionListener(this);
		fixButton.addActionListener(this);
		this.addWindowListener(new LoginWindowClose());
		//����
		JPanel p1,p2,p3;
		JLabel title;
		p1=new JPanel();
		p2=new JPanel(new GridLayout(4,1));
		p3=new JPanel();
		title=new JLabel("��������ϵͳ");
		title.setFont(new Font("����",Font.BOLD, 60));
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
		timer=new javax.swing.Timer(500, new ActionListener(){//ʱ����ʾ
			@Override
			public void actionPerformed(ActionEvent arg0) {
				timeLabel.setText(new SimpleDateFormat("yyyy��MM��dd�� EEEE hh:mm:ss").format(new Date()));
			}
		});
		timer.start();
	    //��������
        this.setTitle("��ӭʹ����������ϵͳ��");     
        this.setIconImage(new ImageIcon("src/pictures/����.png").getImage());  
        this.setSize(900,600);         
        this.setLocationRelativeTo(null);       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //���õ��رմ���ʱ����֤JVMҲ�˳� 
        this.setVisible(true);  
        this.setResizable(true);  
		
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand()=="������ѯ"){  
			 SearchCarView searchCarView=new SearchCarView(); 
	    }else if(e.getActionCommand()=="��������"){
	    	 RentCarView rentCarView=new RentCarView();      
	    }else if(e.getActionCommand()=="�����黹"){
	    	 ReturnCarView returnCarView=new ReturnCarView();
	    }else if(e.getActionCommand()=="�û���Ϣ��ѯ"){
	    	 UserIformView userIformView=new UserIformView(); 	
	    }
	}
	
	 
	
}
