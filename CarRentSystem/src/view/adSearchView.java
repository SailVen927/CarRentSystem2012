package view;
//finish
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.sun.org.apache.bcel.internal.generic.Select;

import dataConnect.DataBase;

public class adSearchView extends JFrame implements ActionListener{
	JButton returnButton=null;
	DataBase dataBase;

	public adSearchView(){
		this.setIconImage(new ImageIcon("src/pictures/����.png").getImage());  
		JScrollPane jsp;
		JPanel p1,p2,p3,p4,p5,p6;
		dataBase=new DataBase();		
     	returnButton=new JButton("����");
		returnButton.setBounds(700, 400, 100, 60);
		p5=new JPanel(new FlowLayout());
		p5.add(returnButton,RIGHT_ALIGNMENT);
		//����
		returnButton.addActionListener(this);
		//����
		jsp=new JScrollPane(dataBase.adCarShow());
		this.add(jsp,BorderLayout.CENTER);
		this.add(p5,BorderLayout.SOUTH);

		this.setTitle("������ѯ");          
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
