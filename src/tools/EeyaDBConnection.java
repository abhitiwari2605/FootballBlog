package tools;
import java.util.*;
import java.sql.*;

public class EeyaDBConnection {
	public Connection con;
	private Statement stmt;
	
	public EeyaDBConnection(){
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			  
			 this.con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/trv","root","abhi2605");  
			  
			 stmt=con.createStatement();}catch(Exception e){ System.out.println(e);}
	}
	
	public Connection returnConnection(){
		return this.con;
	}
	public ResultSet queryExecuter(String query)throws SQLException{
		ResultSet rs=stmt.executeQuery(query); 
		return rs;
	}
	
}
	


