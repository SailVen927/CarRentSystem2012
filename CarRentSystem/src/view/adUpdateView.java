package view;
//function finish
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.sun.org.apache.bcel.internal.generic.NEW;

import dataConnect.DataBase;

public class adUpdateView extends JFrame implements ActionListener{
	JButton updateButton,returnButton,deleteButton=null;
	JTextField cidField,changeField=null;
	DataBase dataBase;
	JComboBox<String> pmsionBox=null;

	public adUpdateView(){
		this.setIconImage(new ImageIcon("src/pictures/����.png").getImage());  
		JScrollPane jsp;
		JPanel p1,p2,p3;
		dataBase=new DataBase();
		updateButton=new JButton("�޸�");
		deleteButton=new JButton("ɾ��");
		returnButton=new JButton("����");
		
		String[] pmsStrings={"���","�ͺ�","Ʒ��","��ɫ","���","״̬"};
        pmsionBox=new JComboBox<String>(pmsStrings);
		cidField=new JTextField(10);
		changeField=new JTextField(10);

		//����
		returnButton.addActionListener(this);
		deleteButton.addActionListener(this);
		updateButton.addActionListener(this);
		
		//����
		jsp=new JScrollPane(dataBase.adCarShow());
		this.add(jsp,BorderLayout.CENTER);
		
		p1=new JPanel(new GridLayout(2,1));
		p2=new JPanel(new FlowLayout(FlowLayout.CENTER));
		p3=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p3.add(updateButton);
		p3.add(deleteButton);
		p3.add(returnButton);
		p2.add(new JLabel("Ҫ�޸ĵĳ�����ţ�"));
		p2.add(cidField);
		p2.add(new JLabel("Ҫ�޸ģ�"));
		p2.add(pmsionBox);
		p2.add(new JLabel("�޸�Ϊ��"));
		p2.add(changeField);
		p1.add(p2);
		p1.add(p3);
		this.add(p1,BorderLayout.SOUTH);
		
		this.setTitle("��������");          
        this.setSize(1200,700);         
        this.setLocationRelativeTo(null);       
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    //���õ��رմ���ʱ����֤JVMҲ�˳� 
        this.setVisible(true);  
        this.setResizable(true); 
	}//RentView
	
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand()=="�޸�"){
			String valuesString=pmsionBox.getSelectedItem().toString();
			String columnName=changeToColumn(valuesString);
			int choice=JOptionPane.showConfirmDialog(null, "��ȷ����Ҫ�޸ı��Ϊ "+cidField.getText()+" �������� "+valuesString+" Ϊ "+changeField.getText(),"ȷ����Ϣ",JOptionPane.YES_NO_OPTION);
			if(choice==JOptionPane.YES_OPTION){
				dataBase=new DataBase();
				dataBase.noReturnFunction("UPDATE Car SET "+columnName+"=\'"+changeField.getText()+"\' WHERE cid=\'"+cidField.getText()+"\'");
				JOptionPane.showMessageDialog(null,"���³ɹ���");
			}else if(choice==JOptionPane.NO_OPTION){
			}
		}else if(e.getActionCommand()=="ɾ��"){
			dataBase.noReturnFunction("DELETE FROM orders WHERE fk_cid=\'"+cidField.getText()+"\'");
			JOptionPane.showMessageDialog(null,"ɾ���ɹ���");
			dataBase.noReturnFunction("DELETE FROM Car WHERE cid=\'"+cidField.getText()+"\'");	
		}else if(e.getActionCommand()=="����"){
			dispose();
		}
}//actionPerformed
	
public String changeToColumn(String choice){//"���","�ͺ�","Ʒ��","��ɫ","���","״̬"
	String temp=null;
	if(choice.equals("���")){
		temp="cid";
	}else if(choice.equals("�ͺ�")){
		temp="ctype";
	}else if(choice.equals("Ʒ��")){
		temp="cbrand";
	}else if(choice.equals("��ɫ")){
		temp="color";
	}else if(choice.equals("���")){
		temp="cprice";
	}else if(choice.equals("״̬")){
		temp="state";
	}
	return temp;
}
	
}

