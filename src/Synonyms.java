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
        for(int i = 0; i < corpus.length; i++) {
            URL current = corpus[i];
            System.out.println(current);
            Scanner parser = new Scanner(current.openStream());
            parser.useDelimiter("[.?!]|\\Z");
            String line;
            // read each line and write to System.out
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

//                        if(!descriptors.getValue(word1,  )){
//                            temp.put(word2, 1);
//                        }else{
//                            temp.put(word1, temp.get(word1) + 1);
//                        }
//                            if (!word1.equals(word2)) {
//                                descriptors.put(word1, temp);
//                            }
                        }
                    }
                    //descriptors.put(wordEnd++, line );
                }
            }catch(NoSuchElementException _) {}
        }
//        while(wordEnd != 0) {
//            System.out.println(descriptors.get(wordEnd--));
//            System.out.println();
//        }
    }
//    public double calculateCosineSimilarity(String word1, String word2) {
//
//    }
}


