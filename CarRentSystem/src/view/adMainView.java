package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.xml.internal.messaging.saaj.soap.ver1_1.Message1_1Impl;

public class adMainView extends JFrame implements ActionListener{
	JFrame mainJFrame=null;
	JButton updateButton,searchCarButton,searchOrderButton,addButton=null;
	JPanel panel;
	
	public adMainView(){
		this.setIconImage(new ImageIcon("src/pictures/汽车.png").getImage());  
		searchCarButton=new JButton("汽车查询");
		searchOrderButton=new JButton("订单查询");
		addButton=new JButton("新增汽车");
		updateButton=new JButton("汽车信息修改");
		
		searchCarButton.addActionListener(this);
		searchOrderButton.addActionListener(this);
		updateButton.addActionListener(this);
		addButton.addActionListener(this);
		
		JPanel panel,p1,p2,p3;
		JLabel title;
		p1=new JPanel();
		p2=new JPanel(new GridLayout(4,1));
		p3=new JPanel();
		title=new JLabel("汽车租赁系统（管理员界面）");
		title.setFont(new Font("隶书",Font.BOLD, 60));
		this.setLayout(new BorderLayout());
		this.add(p1,BorderLayout.NORTH);
		this.add(p2,BorderLayout.CENTER);
		this.add(p3,BorderLayout.SOUTH);
		p1.add(title);
		p2.add(searchCarButton);
		p2.add(searchOrderButton);
		p2.add(updateButton);
		p2.add(addButton);
		
        this.setTitle("管理员界面");          
        this.setSize(900,600);         
        this.setLocationRelativeTo(null);       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //设置当关闭窗口时，保证JVM也退出 
        this.setVisible(true);  
        this.setResizable(true);  
		
	}
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand()=="汽车查询"){  
			 adSearchView adsearchCarView=new adSearchView(); 
	    }else if(e.getActionCommand()=="汽车信息修改"){
	    	adUpdateView adUpdateView=new adUpdateView();
	    }else if(e.getActionCommand()=="新增汽车"){
	    	adAddView adAddView=new adAddView();  
	    }else if(e.getActionCommand()=="订单查询"){
	    	adSearchOrderView adSearchOrderView=new adSearchOrderView();  
	    }
	}
	
}

