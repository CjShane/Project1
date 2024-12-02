import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Scanner;
public class Synonyms {
    private HashMap<String, Integer> semantic;
    public Synonyms (URL[] corpus, String consideredWord) throws IOException, MalformedURLException {
        semantic = new HashMap<>();
        parseCorpus(corpus);
    }
    public void parseCorpus(URL[] corpus) throws IOException {
        URL current = corpus[1];
        System.out.println(current);
        URLConnection con = current.openConnection();
        InputStream is = con.getInputStream();
        Scanner parser = new Scanner(is).useDelimiter(( "[^.!?]+[.!?]" ));
        int count = 1;
        String line = null;
        // read each line and write to System.out
        while ((line = parser.nextLine()) != null) {
            System.out.println(line);
        }
    }
}


