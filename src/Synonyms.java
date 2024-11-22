import java.net.URL;
import java.util.Scanner;
public class Synonyms {
    public Synonyms (URL[] corpus){
        parseCorpus(corpus);
    }
    public void parseCorpus(URL[] corpus){
        String delimtier = "[\\.\\?\\!]|\\Z";
        Scanner parser = new Scanner(System.in);
        while(parser.hasNext()){
            String line = parser.nextLine();
            String[] parts = line.split(delimtier);
        }
    }
}
