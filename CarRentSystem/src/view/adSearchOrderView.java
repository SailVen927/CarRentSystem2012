package view;
//function finish
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dataConnect.DataBase;

public class adSearchOrderView extends JFrame implements ActionListener{
	JButton returnButton=null;
	public adSearchOrderView(){
		DataBase dataBase=new DataBase();		
     	returnButton=new JButton("����");
		returnButton.setBounds(700, 400, 100, 60);
		//����
		returnButton.addActionListener(this);
		//����
		JScrollPane jsp;
		JPanel p5;
		p5=new JPanel(new FlowLayout());
		p5.add(returnButton,RIGHT_ALIGNMENT);
		jsp=new JScrollPane(dataBase.adOrderShow());
		this.add(jsp,BorderLayout.CENTER);
		this.add(p5,BorderLayout.SOUTH);

		this.setTitle("������ѯ"); 
		this.setIconImage(new ImageIcon("src/pictures/����.png").getImage());  
        this.setSize(1200,900);         
        this.setLocationRelativeTo(null);       
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    //���õ��رմ���ʱ����֤JVMҲ�˳� 
        this.setVisible(true);  
        this.setResizable(true); 
	}//SearchView
	
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand()=="����"){
			dispose();
		}
	}  
}
