package dataConnect;

import view.*;
import java.sql.*;
import java.util.Vector;

import javax.swing.JTable;

public class DataBase {
	   Statement sql=null;
	   static Connection con=null;
	  ResultSet rs=null;
	  PreparedStatement ps=null;
   public DataBase() {
	   String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";//数据库引擎
	   String uri="jdbc:sqlserver://localhost:1433;DatabaseName=CarRentSys";
	   String Name="sa";
	   String passWord="159753456ffsail";
	   try {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con=DriverManager.getConnection(uri, Name, passWord);
		//System.out.println("数据库连接成功");
		//LoginView loginView=new LoginView();
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		System.out.println("数据库连接失败了！");
	}
	   
   }//main
   
 //返回SQL语句结果(finish)
   public ResultSet getRS(String selectSQL){
	   try{
		   ps=con.prepareStatement(selectSQL);
	       rs=ps.executeQuery();
	   }catch(Exception e){
	    e.printStackTrace();
	   }
	   return rs;
	  }//getRS
   public void close(){
	   if(con!=null){
	    try {
	    	System.out.println("数据库已关闭");
	     con.close();
	     
	    } catch (SQLException e) {
	     e.printStackTrace();
	     System.out.println("数据库关闭失败！");
	    }
	    con=null;
	   }
	  }//close
   //返回用户查询界面
   public JTable show(){
	   Vector rowData,columnNames;
	   columnNames=new Vector();
       columnNames.add("编号");
       columnNames.add("品牌");
       columnNames.add("型号");   
       columnNames.add("颜色");
       columnNames.add("租金");
       
       //数据库操作
       DataBase dataBase=new DataBase();
       rowData=new Vector<>();
       ResultSet rs=dataBase.getRS("Select cid,cbrand,ctype,color,cprice FROM Car WHERE state=\'未出租\'");
       try {
			while(rs.next()){
				Vector tempVector=new Vector();
				tempVector.add(rs.getString(1));
				tempVector.add(rs.getString(2));
				tempVector.add(rs.getString(3));
				tempVector.add(rs.getString(4));
				tempVector.add(rs.getInt(5));
				rowData.add(tempVector);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
       JTable table=new JTable(rowData,columnNames);
       table.setRowHeight(35);
       return table;
   }
   //返回管理员查询界面
   public JTable adCarShow(){
	   Vector rowData,columnNames;
	   columnNames=new Vector();
       columnNames.add("编号");
       columnNames.add("品牌");
       columnNames.add("型号");   
       columnNames.add("颜色");
       columnNames.add("租金");
       columnNames.add("出租时间");
       columnNames.add("归还时间");
       columnNames.add("状态");
       
       //数据库操作
       DataBase dataBase=new DataBase();
       rowData=new Vector<>();
       rs=dataBase.getRS("Select cid,cbrand,ctype,color,cprice,rentday,returnday,state FROM Car");//rs存放查询结果
       try {
			while(rs.next()){
				Vector tempVector=new Vector();
				tempVector.add(rs.getString(1));//cid
				tempVector.add(rs.getString(2));//cBrand
				tempVector.add(rs.getString(3));//ctype
				tempVector.add(rs.getString(4));//color
				tempVector.add(rs.getInt(5));//cprice
				tempVector.add(rs.getString(6));//rentday
				tempVector.add(rs.getString(7));//returnday
				tempVector.add(rs.getString(8));//state
				rowData.add(tempVector);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
       JTable table=new JTable(rowData,columnNames);
       table.setRowHeight(35);
       return table;
   }
   
   public JTable adOrderShow(){
	   Vector rowData,columnNames;
	   columnNames=new Vector();
       columnNames.add("订单编号");
       columnNames.add("租车用户");
       columnNames.add("车辆编号");
       columnNames.add("品牌");
       columnNames.add("型号");   
       columnNames.add("颜色");
       columnNames.add("出租时间"); 
       columnNames.add("归还时间"); 
       columnNames.add("状态"); 
       
       //数据库操作
       DataBase dataBase=new DataBase();
       rowData=new Vector<>();
       rs=dataBase.getRS("Select onum,fk_user_name,cid,cbrand,ctype,color,rentday,returnday,Car.state FROM Car,orders WHERE Car.cid=orders.fk_cid ORDER BY onum ASC");//rs存放查询结果
       try {
			while(rs.next()){
				Vector tempVector=new Vector();
				tempVector.add(rs.getString(1));//onum
				tempVector.add(rs.getString(2));//fk_user_name
				tempVector.add(rs.getString(3));//cid
				tempVector.add(rs.getString(4));//cBrand
				tempVector.add(rs.getString(5));//ctype
				tempVector.add(rs.getString(6));//color
				tempVector.add(rs.getString(7));//rentday
				tempVector.add(rs.getString(8));//returnday
				tempVector.add(rs.getString(9));//state
				rowData.add(tempVector);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
       JTable table=new JTable(rowData,columnNames);
       table.setRowHeight(35);
       return table;
   }   
  
   //返回用户所租车辆信息
   public JTable userOrderShow(){
	   Vector rowData,columnNames;
	   columnNames=new Vector();
	   columnNames.add("订单编号");
       columnNames.add("汽车编号");
       columnNames.add("品牌");
       columnNames.add("型号");   
       columnNames.add("颜色");
       columnNames.add("出租时间");
       
       //数据库操作
       DataBase dataBase=new DataBase();
       rowData=new Vector<String>();
       ResultSet rs=dataBase.getRS("Select onum,cid,cbrand,ctype,color,rentday FROM Car,orders,userIform WHERE Car.cid=orders.fk_cid AND userIform.user_name=orders.fk_user_name AND userIform.state=1 AND Car.state=\'已出租\'");//rs存放查询结果
       try {
			while(rs.next()){
				Vector<String> tempVector=new Vector<String>();
				tempVector.add(rs.getString(1));
				tempVector.add(rs.getString(2));
				tempVector.add(rs.getString(3));
				tempVector.add(rs.getString(4));
				tempVector.add(rs.getString(5));
				tempVector.add(rs.getString(6));
				rowData.add(tempVector);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
       JTable table=new JTable(rowData,columnNames);
       table.setRowHeight(35);
       return table;
   }
   //无参操作
   public void noReturnFunction(String insertSQL){
	   try {
		   ps=con.prepareStatement(insertSQL);
		   ps.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	}
   }//insert
   
 //验证登录信息
   public boolean searchEnter(String name,String pwd,String tableName){
	   try {
		ps=con.prepareStatement("SELECT * FROM "+tableName);
		rs=ps.executeQuery();
		while(rs.next()){
			if(name.equals(rs.getString(1).trim()) && pwd.equals(rs.getString(2).trim())){
				return true;
			}
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return false;	   
   }//searchEnter
   //查找用户租赁时请求的车辆信息
   public String searchRentCarInform(String cbrand,String ctype,String color){
	   try {
		ps=con.prepareStatement("SELECT cbrand,ctype,color,state,cid,cprice FROM Car");
		rs=ps.executeQuery();
		
		while(rs.next()){
			System.out.println(rs.getString(5).trim()+"@"+rs.getString(6));
			if(cbrand.equals(rs.getString(1).trim()) && ctype.equals(rs.getString(2).trim()) && color.equals(rs.getString(3).trim()) && "未出租".equals(rs.getString(4).trim())){
				System.out.println(rs.getString(5).trim()+"@"+rs.getString(6));
				return rs.getString(5).trim()+"@"+rs.getString(6);
			}
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return "0";	   
   }//searchRentCarInform
   //更新数据
   public void updateInform(String newInfom,String table,String colname,String condition){
	   try {
		  ps=con.prepareStatement("UPDATE "+table+" SET "+colname+"=\'"+newInfom+"\' WHERE "+condition);
		  ps.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	   
   }//updateInform
}
