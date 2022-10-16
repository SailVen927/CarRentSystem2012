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
		this.setIconImage(new ImageIcon("src/pictures/汽车.png").getImage());  
		JScrollPane jsp;
		JPanel p1,p2,p3;
		dataBase=new DataBase();
		updateButton=new JButton("修改");
		deleteButton=new JButton("删除");
		returnButton=new JButton("返回");
		
		String[] pmsStrings={"编号","型号","品牌","颜色","租金","状态"};
        pmsionBox=new JComboBox<String>(pmsStrings);
		cidField=new JTextField(10);
		changeField=new JTextField(10);

		//监听
		returnButton.addActionListener(this);
		deleteButton.addActionListener(this);
		updateButton.addActionListener(this);
		
		//布局
		jsp=new JScrollPane(dataBase.adCarShow());
		this.add(jsp,BorderLayout.CENTER);
		
		p1=new JPanel(new GridLayout(2,1));
		p2=new JPanel(new FlowLayout(FlowLayout.CENTER));
		p3=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p3.add(updateButton);
		p3.add(deleteButton);
		p3.add(returnButton);
		p2.add(new JLabel("要修改的车辆编号："));
		p2.add(cidField);
		p2.add(new JLabel("要修改："));
		p2.add(pmsionBox);
		p2.add(new JLabel("修改为："));
		p2.add(changeField);
		p1.add(p2);
		p1.add(p3);
		this.add(p1,BorderLayout.SOUTH);
		
		this.setTitle("汽车更新");          
        this.setSize(1200,700);         
        this.setLocationRelativeTo(null);       
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);    //设置当关闭窗口时，保证JVM也退出 
        this.setVisible(true);  
        this.setResizable(true); 
	}//RentView
	
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand()=="修改"){
			String valuesString=pmsionBox.getSelectedItem().toString();
			String columnName=changeToColumn(valuesString);
			int choice=JOptionPane.showConfirmDialog(null, "请确认您要修改编号为 "+cidField.getText()+" 的汽车的 "+valuesString+" 为 "+changeField.getText(),"确认信息",JOptionPane.YES_NO_OPTION);
			if(choice==JOptionPane.YES_OPTION){
				dataBase=new DataBase();
				dataBase.noReturnFunction("UPDATE Car SET "+columnName+"=\'"+changeField.getText()+"\' WHERE cid=\'"+cidField.getText()+"\'");
				JOptionPane.showMessageDialog(null,"更新成功！");
			}else if(choice==JOptionPane.NO_OPTION){
			}
		}else if(e.getActionCommand()=="删除"){
			dataBase.noReturnFunction("DELETE FROM orders WHERE fk_cid=\'"+cidField.getText()+"\'");
			JOptionPane.showMessageDialog(null,"删除成功！");
			dataBase.noReturnFunction("DELETE FROM Car WHERE cid=\'"+cidField.getText()+"\'");	
		}else if(e.getActionCommand()=="返回"){
			dispose();
		}
}//actionPerformed
	
public String changeToColumn(String choice){//"编号","型号","品牌","颜色","租金","状态"
	String temp=null;
	if(choice.equals("编号")){
		temp="cid";
	}else if(choice.equals("型号")){
		temp="ctype";
	}else if(choice.equals("品牌")){
		temp="cbrand";
	}else if(choice.equals("颜色")){
		temp="color";
	}else if(choice.equals("租金")){
		temp="cprice";
	}else if(choice.equals("状态")){
		temp="state";
	}
	return temp;
}
	
}

