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
		this.setIconImage(new ImageIcon("src/pictures/汽车.png").getImage());  
		JPanel p1,p2,p3,p4,p5,p6,p7,p8;
		dataBase=new DataBase();
		confirmButton=new JButton("新增");
		returnButton=new JButton("返回");
		cidField=new JTextField(10);
		brandField=new JTextField(10);
		typeField=new JTextField(10);
		colorField=new JTextField(10);
		priceField=new JTextField(10);
		p5=new JPanel(new GridLayout(6,1));
		//监听
		returnButton.addActionListener(this);
		confirmButton.addActionListener(this);
		
		//布局
		//jsp=new JScrollPane(dataBase.show());
		//this.add(jsp,BorderLayout.CENTER);
		this.add(p5);
		p4=new JPanel();
		p4.add(new JLabel("编号："));
		p4.add(cidField);
		p1=new JPanel();
		p1.add(new JLabel("品牌："));
		p1.add(brandField);
		p2=new JPanel();
		p2.add(new JLabel("型号："));
		p2.add(typeField);
		p3=new JPanel();
		p3.add(new JLabel("颜色："));
		p3.add(colorField);
		p7=new JPanel();
		p7.add(new JLabel("租金："));
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
		

		
		this.setTitle("汽车新增");          
        this.setSize(600,900);         
        this.setLocationRelativeTo(null);       
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    //设置当关闭窗口时，保证JVM也退出 
        this.setVisible(true);  
        this.setResizable(true); 
	}//RentView
	
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand()=="新增"){
			//String tempString=brandField.getText()+typeField.getText()+colorField.getText();
			int choice=JOptionPane.showConfirmDialog(null, "请确认您要新增的汽车信息为：\n编号："+cidField.getText().trim()+"\n品牌："+brandField.getText().trim()+"\n型号："+typeField.getText().trim()+"\n颜色："+colorField.getText().trim()+"\n租金："+priceField.getText().trim(),"确认信息",JOptionPane.YES_NO_OPTION);
			if(choice==JOptionPane.YES_OPTION){
				dataBase=new DataBase();
				dataBase.noReturnFunction("INSERT INTO Car VALUES(\'"+cidField.getText().trim()+"\',\'"+typeField.getText().trim()+"\',\'"+priceField.getText().trim()+"\',\'"+colorField.getText().trim()+"\',\'\',\'\',\'未出租\',\'"+brandField.getText().trim()+"\')");
				
					
					JOptionPane.showMessageDialog(null,"新增成功！");
					System.out.println("新增成功");
					
				}else if(choice==JOptionPane.NO_OPTION){}				
		}else if(e.getActionCommand()=="返回"){
			dispose();
		}
}//actionPerformed
	
	
}
