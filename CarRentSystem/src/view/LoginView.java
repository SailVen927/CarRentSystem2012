package view;  
import javax.swing.*;  
import javax.swing.plaf.FontUIResource;

import dataConnect.DataBase;

import java.awt.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.awt.peer.LightweightPeer;
import java.sql.ResultSet;
import java.util.Enumeration;

public class LoginView extends JFrame implements ActionListener {  
    JTextField nameField=null;  
    JPasswordField passField=null;  
    String valuesString=null;
    JComboBox<String> pmsionBox=null;
    
   public static void main(String[] args) { 
	   LoginView loginView=new LoginView();
    } 
   //登录主界面
    public LoginView()  
    {   JButton enterButton,exitButton=null;  
    	JLabel nameLabel,passLabel,picLabel=null;
    	
    	InitGlobalFont(new Font("微软雅黑", Font.PLAIN, 20));//全局字体设置
    	nameLabel=new JLabel("用户名：");  
    	passLabel=new JLabel("密    码："); 
    	nameField=new JTextField(10);  
        passField=new JPasswordField(15);  
        enterButton=new JButton("登录");  
        exitButton=new JButton("退出");
        String[] pmsStrings={"用户","管理员"};//登录角色选择
        pmsionBox=new JComboBox<String>(pmsStrings);
        picLabel=new JLabel(new ImageIcon("src/pictures/汽车.png"));
        //监听
        enterButton.addActionListener(this);  
        exitButton.addActionListener(this); 
        //布局
        JPanel p1,p2,p3,p4,p5=null; 
        p1=new JPanel();  
        p2=new JPanel();  
        p3=new JPanel();    
        p4=new JPanel();    
        p5=new JPanel(new GridLayout(3,1));    
        this.setLayout(new BorderLayout());
        this.add(p5,BorderLayout.CENTER);
        picLabel.setBounds(0, 400, 300, 400);
        p4.add(picLabel);
        this.add(p4,BorderLayout.NORTH);
        p5.add(p1);  
        p5.add(p2);  
        p5.add(p3);  
        p1.add(nameLabel); 
        p1.add(nameField); 
        p1.add(pmsionBox);  
        p2.add(passLabel);  
        p2.add(passField);  
        p3.add(enterButton);  	
        p3.add(exitButton);
        p1.setBackground(Color.white);
        p2.setBackground(Color.white);
        p3.setBackground(Color.white);
        p4.setBackground(Color.white);
        //窗口设置
        this.setTitle("汽车租赁系统"); 
        this.setIconImage(new ImageIcon("src/pictures/汽车.png").getImage());  
        this.setSize(700,600);         
        this.setLocationRelativeTo(null);       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);  
        this.setResizable(true); 
        
    }  
    
    public void actionPerformed(ActionEvent e){  	
        if(e.getActionCommand()=="登录"){ 
        	valuesString=pmsionBox.getSelectedItem().toString();
        	if(valuesString.equals("用户")){
        		userLoginEntrance();
        	}else if(valuesString.equals("管理员")){
        		adLoginEntrance();
        	}
        }else if(e.getActionCommand()=="退出"){  
        	System.exit(0);
        }         
    }  
      
//用户登录入口
public void userLoginEntrance(){
	DataBase dataBase=new DataBase();
    	if(dataBase.searchEnter(nameField.getText(), passField.getText(), "userIform")){
    		
            dataBase.noReturnFunction("UPDATE userIform SET state=1 WHERE user_name=\'"+nameField.getText().trim()+"\'");    
            nameField.setText("");
            passField.setText(""); 
            dispose();
            MainView mainView=new MainView();
           
    	}else{
    		System.out.println(nameField.getText()+passField.getText());
    		JOptionPane.showMessageDialog(null,"  用户名或者密码错误！\n请重新输入","提示消息",JOptionPane.ERROR_MESSAGE);  
    	}
    	
}
   
//管理员登录入口
public void adLoginEntrance(){
	DataBase dataBase=new DataBase();
	if(dataBase.searchEnter(nameField.getText(), passField.getText(), "adIform")){
		nameField.setText("");  
        passField.setText(""); 
        dispose();
        adMainView admainView=new adMainView();
        
	}else{
		JOptionPane.showMessageDialog(null,"  管理员名或者密码错误！\n请重新输入","提示消息",JOptionPane.ERROR_MESSAGE);  
	}
    	
}

//字体设置
private static void InitGlobalFont(Font font){
	FontUIResource fontRes=new FontUIResource(font);
	for (Enumeration<Object> keys=UIManager.getDefaults().keys(); keys.hasMoreElements();){
		Object key=keys.nextElement();
		Object value=UIManager.get(key);
		if (value instanceof FontUIResource){
			UIManager.put(key,fontRes);
		}
	}
}
          
}//LoginView class

