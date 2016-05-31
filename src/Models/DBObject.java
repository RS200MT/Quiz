package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



public class DBObject {
	
	
	static Connection con  ;
    static Statement stm ;
    
    static{
	    try{
			Class.forName("com.mysql.jdbc.Driver");
			 con = DriverManager.getConnection("jdbc:mysql://" + DBInfo.MYSQL_DATABASE_SERVER , DBInfo.MYSQL_USERNAME, DBInfo.MYSQL_PASSWORD);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			stm =  con.createStatement();
			stm.executeQuery("USE " + DBInfo.MYSQL_DATABASE_NAME );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static Statement getStatement(){
    	return stm;
    }
	
}
