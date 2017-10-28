import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.google.common.util.concurrent.Service;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.CustomElementCollection;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.util.ServiceException;


public class Readshell {
	public static void main(String[] args) {
		SpreadsheetService srvice = new SpreadsheetService("Sheet1");
		String sheeturl = "https://spreadsheets.google.com/feeds/list/1c0e0O3h-pbxAtUg9ZAOZvYn3OacnAAuGqxzWZ2xnfcA/default/public/values";
		//"https://docs.google.com/spreadsheets/d/1HJCPM3UhxpfxEpq4a7yoU7bplh-TCpnjxu3GmD_oJLE/edit#gid=0";

		//https://docs.google.com/spreadsheets/d/e/2PACX-1vQaQspou6d8hif_IUvVI-vCb8FSrnr4LHx0ikERyRCSLczX9-3XvMhZz0FyzzQhl5wKsA5tl22CBBog/pubhtml
		try {
			URL url = new URL(sheeturl);
			ListFeed lf= srvice.getFeed(url, ListFeed.class);
			for(ListEntry le:lf.getEntries()){
				CustomElementCollection cec = le.getCustomElements();
				String val1 = cec.getValue("SiteID");
				
				//getdata(val1);
				System.out.println(val1);
				String val2 = cec.getValue("SiteDescription");
				//getdata(val1);
				System.out.println(val2);
				getdata(val1, val2);
				
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println("maiformed");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}
	static void getdata(String first,String second){
		String mydriver= "com.mysql.jdbc.Driver";
		String myurl = "jdbc:mysql://localhost:3306/aggregator";
		try {
			Class.forName(mydriver);
			Connection conn = DriverManager.getConnection(myurl,"root","");
			String query = "update aggregator.siteaggregator set SiteDescription ='"+second+"' where SiteID ='"+first+ "';"; 
			PreparedStatement stat = conn.prepareStatement(query);
			//stat.setInt(1, 1000);
			//stat.setString(2,);
			stat.executeUpdate();
			System.out.println("the value is update");
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
