import java.io.IOException;
import java.net.URL;
import java.util.*;


/**
 * The Synonyms class is responsible for parsing a given corpus of URLs and calculating the cosine similarity between words
 * based on how often the same words appear in the same sentence in the corpus.
 * <p>
 * The main functionalities include:
 * <ul>
 *   <li>Parsing a list of URLs to extract word pairs and their frequencies.</li>
 *   <li>Storing word pairs in a data structure (a HashMap of HashMaps).</li>
 *   <li>Calculating cosine similarity between two words based on their co-occurrence descriptors.</li>
 * </ul>
 */

public class Synonyms {
    //main hashmap
    private HashMap<String, HashMap<String, Integer>> descriptors;

    /**
     * Constructor for the Synonyms class. Initializes the descriptors and parses the provided corpus.
     *
     * @param corpus An array of URLs to parse and analyze for word co-occurrence.
     * @throws IOException If an error occurs while reading from the URLs.
     */
    public Synonyms (URL[] corpus) throws IOException {
        descriptors = new HashMap<>();
        parseCorpus(corpus);
    }

    /**
     * Parses the provided corpus of URLs, extracting words and keeps tracks of the words surrounding that words.
     * <p>
     * The method processes the content of each URL, breaking it into sentences and extracting words.
     * The other words in the sentence of each word is stored in the descriptors HashMap.
     *
     * @param corpus An array of URLs to parse and analyze.
     * @throws IOException If an error occurs while reading from the URLs.
     */
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

    /**
     * Calculates the cosine similarity between two words based on the amount of words the same words found around each descriptors.
     * <p>
     * The cosine similarity is computed by taking the dot product of the vectors representing the two words,
     * divided by the product of the magnitudes of the vectors.
     *
     * @param word1 The first word to compare.
     * @param word2 The second word to compare.
     * @return The cosine similarity between the two words, or -1.0 if either word is not found in the descriptors.
     */
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
        return -1.0; // Return -1 if one or both words are not found in the descriptors
    }
}


