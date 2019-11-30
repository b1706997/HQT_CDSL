package backend;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

	public static Connection getConnection(){
		Connection con = null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost/sqlproject?serverTimezone=UTC","son","siliconvalley");
			System.out.println("Connected");
		}
		catch(Exception ex) 
		{
			System.out.println("Cannot connected");
			ex.printStackTrace();
		}
		return con;
	}

}
