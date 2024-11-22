import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;
public class Synonyms {
    private HashMap<String, Integer> semantic;
    public Synonyms (URL[] corpus, String consideredWord) {
        semantic = new HashMap<>();
        parseCorpus(corpus);
    }
    public void parseCorpus(URL[] corpus){
        String delimtier = "[\\.\\?\\!]|\\Z";
        Scanner parser = new Scanner(System.in);
        //while(parser.hasNext()){
            String line = parser.nextLine();
            String[] parts = line.split(delimtier);
        //}
    }
}
