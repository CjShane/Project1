import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Synonyms {
    private HashMap<Integer, String> semantic;
    public Synonyms (URL[] corpus) throws IOException, MalformedURLException {
        semantic = new HashMap<>();
        parseCorpus(corpus);
    }
    public void parseCorpus(URL[] corpus) throws IOException {
        int count = 0;
        for(int i = 0; i < corpus.length; i++) {
            URL current = corpus[i];
            System.out.println(current);
            Scanner parser = new Scanner(current.openStream());
            parser.useDelimiter("[.?!]|\\Z");
            String line;
            // read each line and write to System.out
            try{
                while (parser.hasNext()) {
                    line = parser.next();
                    line = Arrays.toString(line.replaceAll("\\W+\\d+" , ""  ).toLowerCase().split("\\s+"));
                    line = line.replaceAll("[^a-zA-Z ]", "");
                    semantic.put(count++, line);
                }
            }catch(NoSuchElementException _) {}
        }
        while(count != 0) {
            //String words = semantic.get(count--);
            //words = words.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
            System.out.println(semantic.get(count--));
        }
    }
    public double calculateCosineSimilarity(String word1, String word2) {

    }
}


