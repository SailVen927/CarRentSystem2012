package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import dataConnect.DataBase;

class LoginWindowClose extends WindowAdapter{
	 public void windowClosing(WindowEvent w){
		 DataBase dataBase=new DataBase();
		 ResultSet rs=dataBase.getRS("SELECT user_name FROM userIform WHERE state=1");  
		 try {
			rs.next();
			String tempString=rs.getString(1).trim();
			dataBase.noReturnFunction("UPDATE userIform SET state=0 WHERE user_name=\'"+tempString+"\'");   
			//System.out.println("修改状态为0成功！");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		 
	 }

}

