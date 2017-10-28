/*

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.util.ServiceException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;
public class NewDocDemo {
    public static void main(String[] args) throws MalformedURLException, GeneralSecurityException, IOException, ServiceException {
        URL SPREADSHEET_FEED_URL;
        SPREADSHEET_FEED_URL = new URL("https://docs.google.com/spreadsheets/d/1HJCPM3UhxpfxEpq4a7yoU7bplh-TCpnjxu3GmD_oJLE/edit#gid=0");

        File p12 = new File("./key.p12");

        HttpTransport httpTransport = new NetHttpTransport();
        JacksonFactory jsonFactory = new JacksonFactory();
        String[] SCOPESArray = {"https://docs.google.com/spreadsheets/d/1HJCPM3UhxpfxEpq4a7yoU7bplh-TCpnjxu3GmD_oJLE/edit#gid=0"};
        final List SCOPES = Arrays.asList(SCOPESArray);
        GoogleCredential credential = new GoogleCredential.Builder()
                .setTransport(httpTransport)
                .setJsonFactory(jsonFactory)
                .setServiceAccountId("cliend_ID")
                .setServiceAccountScopes(SCOPES)
                .setServiceAccountPrivateKeyFromP12File(p12)
                .build();

        SpreadsheetService service = new SpreadsheetService("Test");

        service.setOAuth2Credentials(credential);
        SpreadsheetFeed feed = service.getFeed(SPREADSHEET_FEED_URL, SpreadsheetFeed.class);
        List<SpreadsheetEntry> spreadsheets = feed.getEntries();

        if (spreadsheets.size() == 0) {
            System.out.println("No spreadsheets found.");
        }

         SpreadsheetEntry spreadsheet = null;
        for (int i = 0; i < spreadsheets.size(); i++) {
            if (spreadsheets.get(i).getTitle().getPlainText().startsWith("ListOfSandboxes")) {
                spreadsheet = spreadsheets.get(i);
                System.out.println("Name of editing spreadsheet: " + spreadsheets.get(i).getTitle().getPlainText());
                System.out.println("ID of SpreadSheet: " + i);
            }
        }

    }

}
 */
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.util.ServiceException;

import java.io.IOException;
import java.net.URL;


public class NewDocDemo {

	public static final String GOOGLE_ACCOUNT_USERNAME = "dishani2k14@gmail.com"; 
	public static final String GOOGLE_ACCOUNT_PASSWORD = "dishani kori"; 

	public static final String SPREADSHEET_URL = "https://docs.google.com/spreadsheets/d/1HJCPM3UhxpfxEpq4a7yoU7bplh-TCpnjxu3GmD_oJLE/edit#gid=0";



	public static void getData(){
		String status="";

		try{
			/** Our view of Google Spreadsheets as an authenticated Google user. */
			SpreadsheetService service = new SpreadsheetService("Print Google Spreadsheet Demo");

			// Login and prompt the user to pick a sheet to use.
			service.setUserCredentials(GOOGLE_ACCOUNT_USERNAME,
					GOOGLE_ACCOUNT_PASSWORD);

			// Load sheet
			URL metafeedUrl = new URL(SPREADSHEET_URL);
			SpreadsheetEntry spreadsheet = service.getEntry(metafeedUrl,SpreadsheetEntry.class);
			URL listFeedUrl = spreadsheet.getWorksheets().get(0).getListFeedUrl();

			// Print entries
			ListFeed feed = service.getFeed(listFeedUrl, ListFeed.class);

			for (ListEntry entry : feed.getEntries()) {
				System.out.println("new row");
				for (String tag : entry.getCustomElements().getTags()) {
					System.out.println("     " + tag + ": "
							+ entry.getCustomElements().getValue(tag));
					status=entry.getCustomElements().getValue(tag);

				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		getData();
	}
}