package view;
//finish
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import dataConnect.DataBase;

public class adAddView extends JFrame implements ActionListener{
	JButton confirmButton,returnButton=null;
	JTextField cidField,brandField,typeField,colorField,priceField=null;
	DataBase dataBase;
	
	

	public adAddView(){
		this.setIconImage(new ImageIcon("src/pictures/����.png").getImage());  
		JPanel p1,p2,p3,p4,p5,p6,p7,p8;
		dataBase=new DataBase();
		confirmButton=new JButton("����");
		returnButton=new JButton("����");
		cidField=new JTextField(10);
		brandField=new JTextField(10);
		typeField=new JTextField(10);
		colorField=new JTextField(10);
		priceField=new JTextField(10);
		p5=new JPanel(new GridLayout(6,1));
		//����
		returnButton.addActionListener(this);
		confirmButton.addActionListener(this);
		
		//����
		//jsp=new JScrollPane(dataBase.show());
		//this.add(jsp,BorderLayout.CENTER);
		this.add(p5);
		p4=new JPanel();
		p4.add(new JLabel("��ţ�"));
		p4.add(cidField);
		p1=new JPanel();
		p1.add(new JLabel("Ʒ�ƣ�"));
		p1.add(brandField);
		p2=new JPanel();
		p2.add(new JLabel("�ͺţ�"));
		p2.add(typeField);
		p3=new JPanel();
		p3.add(new JLabel("��ɫ��"));
		p3.add(colorField);
		p7=new JPanel();
		p7.add(new JLabel("���"));
		p7.add(priceField);
		p5.add(p4);
		p5.add(p1);
		p5.add(p2);
		p5.add(p3);
		p5.add(p7);
		p8=new JPanel();
		p8.add(confirmButton);
		p8.add(returnButton);
		p5.add(p8);
		

		
		this.setTitle("��������");          
        this.setSize(600,900);         
        this.setLocationRelativeTo(null);       
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    //���õ��رմ���ʱ����֤JVMҲ�˳� 
        this.setVisible(true);  
        this.setResizable(true); 
	}//RentView
	
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand()=="����"){
			//String tempString=brandField.getText()+typeField.getText()+colorField.getText();
			int choice=JOptionPane.showConfirmDialog(null, "��ȷ����Ҫ������������ϢΪ��\n��ţ�"+cidField.getText().trim()+"\nƷ�ƣ�"+brandField.getText().trim()+"\n�ͺţ�"+typeField.getText().trim()+"\n��ɫ��"+colorField.getText().trim()+"\n���"+priceField.getText().trim(),"ȷ����Ϣ",JOptionPane.YES_NO_OPTION);
			if(choice==JOptionPane.YES_OPTION){
				dataBase=new DataBase();
				dataBase.noReturnFunction("INSERT INTO Car VALUES(\'"+cidField.getText().trim()+"\',\'"+typeField.getText().trim()+"\',\'"+priceField.getText().trim()+"\',\'"+colorField.getText().trim()+"\',\'\',\'\',\'δ����\',\'"+brandField.getText().trim()+"\')");
				
					
					JOptionPane.showMessageDialog(null,"�����ɹ���");
					System.out.println("�����ɹ�");
					
				}else if(choice==JOptionPane.NO_OPTION){}				
		}else if(e.getActionCommand()=="����"){
			dispose();
		}
}//actionPerformed
	
	
}
