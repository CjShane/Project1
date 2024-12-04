import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Synonyms {
    private HashMap<String, HashMap<String, Integer>> descriptors;
    public Synonyms (URL[] corpus) throws IOException {
        descriptors = new HashMap<>();
        parseCorpus(corpus);
    }
    public void parseCorpus(URL[] corpus) throws IOException {
        for(int i = 0; i < corpus.length; i++) {
            URL current = corpus[i];
            System.out.println(current);
            Scanner parser = new Scanner(current.openStream());
            parser.useDelimiter("[.?!]|\\Z");
            String line;

            try{
                while (parser.hasNext()) {
                    // get the first sentence from the website and convert into a more usable format
                    line = parser.next();
                    line = line.replaceAll("[^a-zA-Z ]", "");
                    line = Arrays.toString(line.replaceAll("\\W+\\d+" , ""  ).toLowerCase().split("\\s+"));
                    line = line.replaceAll("[^a-zA-Z ]", "");
                    line = line.trim();

                    String word1 = "";
                    String word2 = "";
                    int wordEnd = 0;
                    for(int j = 0; j < line.length() - 1; j = wordEnd) {
                        word1 = "";
                        int wordStart = j;
                        while(line.charAt(wordEnd) != ' ' && wordEnd < line.length() - 1) {
                            word1 += line.charAt(wordEnd) + "";
                            wordEnd++;
                        }

                        for(int k =0; k < line.length() - 1; k++) {
                            word2 = "";
                            if(k == wordStart && wordEnd != line.length() - 1)
                                k = ++wordEnd;
                            while (line.charAt(k) != ' ' && k < line.length() - 1) {
                                word2 += line.charAt(k) + "";
                                k++;
                            }

                            HashMap<String, Integer> temp;
                            temp = descriptors.get(word1);
                            if(temp == null){
                                temp = new HashMap<String, Integer>();
                                descriptors.put(word1, temp);
                            }
                            if(temp.get(word2) == null){
                                temp.put(word2, 1);
                            }else{
                                temp.put(word2, temp.get(word2) + 1);
                            }

                        }
                    }
                }
            }catch(NoSuchElementException _) {}
        }
    }
    public double calculateCosineSimilarity(String word1, String word2) {
        if(descriptors.containsKey(word1) && descriptors.containsKey(word2)) {

            double something = 0;
            HashMap<String, Integer> map = descriptors.get(word1);
            for (Map.Entry<String, Integer> temp : map.entrySet()) {
                int value = temp.getValue();
                something += Math.pow(value, 2);
            }

            double something2 = 0;
            HashMap<String, Integer> map2 = descriptors.get(word2);
            for (Map.Entry<String, Integer> temp2 : map2.entrySet()) {
                int value = temp2.getValue();
                something2 += Math.pow(value, 2);
            }

            double denom = Math.sqrt(something);
            denom *= Math.sqrt(something2);

            int dotProduct = 0;
            int value;
            int value2 = 0;


            Set<String> name = map.keySet();
            Set<String> name2 = map2.keySet();
            name.retainAll(name2);

            for(String s : name) {
                dotProduct += map.get(s) * map2.get(s);
            }

            return dotProduct / denom;
        }
        return -1.0;
    }
}


