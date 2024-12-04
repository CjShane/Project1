import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Synonyms {
    private HashMap<String, HashMap<String, Integer>> descriptors;
    public Synonyms (URL[] corpus, String base) throws IOException {
        descriptors = new HashMap<>();
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
                    line = line.trim();
                    for(int j = 0; j < line.length(); j++){
                        String word = line.
                        HashMap<String, Integer> map = new HashMap<>();
                        if(!map.containsKey(word)){
                            map.put(word, map.put(word, 1));
                        }else{
                            map.put(word, map.get(word) + 1);
                        }

                        descriptors.put(word, map);
                    }
                    //descriptors.put(count++, line );
                }
            }catch(NoSuchElementException _) {}
        }
        while(count != 0) {
            //String words = semantic.get(count--);
            //words = words.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
            System.out.println(descriptors.get(count--));
            System.out.println();
        }
    }
//    public double calculateCosineSimilarity(String word1, String word2) {
//
//    }
}


