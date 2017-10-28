import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MySql {
	public static void main(String[] args) {
		String mydriver= "com.mysql.jdbc.Driver";
		String myurl = "jdbc:mysql://localhost:3306/aggregator";
		try {
			Class.forName(mydriver);
			System.out.println("driver manager");
			Connection conn = DriverManager.getConnection(myurl,"root","");
			System.out.println("connection ho rha h..............");
			String query = "update aggregator.siteaggregator set SiteShortName ='Canadia' where DefaultCustomerName = 'Canadian Public Tender';"; 
			//Statement stmt = conn.createStatement();
			//ResultSet reset = stmt.executeQuery(query);
			PreparedStatement stat = conn.prepareStatement(query);
			//stat.setInt(1, 1000);
			//stat.setString(2,);
			stat.executeUpdate();
			System.out.println("its update...............");
			/*
			while(reset.next()){
				System.out.println(reset.getString(1)+" "+reset.getString(2)+" "+reset.getString(3));
			}
			*/
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
	}

}
