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
public class ReadDocSheet{
    public static void main(String[] args) throws MalformedURLException, GeneralSecurityException, IOException, ServiceException {
        URL SPREADSHEET_FEED_URL;
        SPREADSHEET_FEED_URL = new URL("https://docs.google.com/spreadsheets/d/1HJCPM3UhxpfxEpq4a7yoU7bplh-TCpnjxu3GmD_oJLE/edit#gid=0");

        File p12 = new File("./key.p12");

        HttpTransport httpTransport = new NetHttpTransport();
        JacksonFactory jsonFactory = new JacksonFactory();
        String[] SCOPESArray = {"https://docs.google.com/spreadsheets/d/1HJCPM3UhxpfxEpq4a7yoU7bplh-TCpnjxu3GmD_oJLE/edit#gid=0", "https://spreadsheets.google.com/feeds/spreadsheets/private/full", "https://docs.google.com/feeds"};
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