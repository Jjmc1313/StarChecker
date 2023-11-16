import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
  public static void main(String[] args) throws Exception {
    final String baseURL = "http://robertsspaceindustries.com/pledge/ships/";
    
    Scanner words = new Scanner(new File("words_alpha.txt"));
    Writer output = new BufferedWriter(new FileWriter("output.txt", true));

    while (words.hasNext()) {
      HttpURLConnection con = (HttpURLConnection)(new URL(baseURL + words.next()).openConnection());
      con.setInstanceFollowRedirects(false );
      con.connect();
      int responseCode = con.getResponseCode();
      System.out.println(responseCode);
      if (responseCode == 302) {
        String location = con.getHeaderField("Location");
        output.append(location);
      }
      con.disconnect();
    }
    output.close();
  }
}
