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
		this.setIconImage(new ImageIcon("src/pictures/����.png").getImage());  
		searchCarButton=new JButton("������ѯ");
		searchOrderButton=new JButton("������ѯ");
		addButton=new JButton("��������");
		updateButton=new JButton("������Ϣ�޸�");
		
		searchCarButton.addActionListener(this);
		searchOrderButton.addActionListener(this);
		updateButton.addActionListener(this);
		addButton.addActionListener(this);
		
		JPanel panel,p1,p2,p3;
		JLabel title;
		p1=new JPanel();
		p2=new JPanel(new GridLayout(4,1));
		p3=new JPanel();
		title=new JLabel("��������ϵͳ������Ա���棩");
		title.setFont(new Font("����",Font.BOLD, 60));
		this.setLayout(new BorderLayout());
		this.add(p1,BorderLayout.NORTH);
		this.add(p2,BorderLayout.CENTER);
		this.add(p3,BorderLayout.SOUTH);
		p1.add(title);
		p2.add(searchCarButton);
		p2.add(searchOrderButton);
		p2.add(updateButton);
		p2.add(addButton);
		
        this.setTitle("����Ա����");          
        this.setSize(900,600);         
        this.setLocationRelativeTo(null);       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //���õ��رմ���ʱ����֤JVMҲ�˳� 
        this.setVisible(true);  
        this.setResizable(true);  
		
	}
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand()=="������ѯ"){  
			 adSearchView adsearchCarView=new adSearchView(); 
	    }else if(e.getActionCommand()=="������Ϣ�޸�"){
	    	adUpdateView adUpdateView=new adUpdateView();
	    }else if(e.getActionCommand()=="��������"){
	    	adAddView adAddView=new adAddView();  
	    }else if(e.getActionCommand()=="������ѯ"){
	    	adSearchOrderView adSearchOrderView=new adSearchOrderView();  
	    }
	}
	
}

