import java.io.IOException;
import java.net.URL;
import java.util.Scanner;


/**
 * The Main class demonstrates the usage of the Synonyms class by calculating cosine similarity between words
 * based on a corpus of classic texts. It also reads a list of words from an input file, compares each word to
 * others in the file, and identifies the most similar word based on cosine similarity.
 * <p>
 * The class works as follows:
 * <ul>
 *   <li>It creates a Synonyms object to analyze the texts and calculate the cosine similarity between word pairs.</li>
 *   <li>It outputs cosine similarities for predefined word pairs.</li>
 *   <li>It reads words from an input file and finds the word most similar to a given word in the file.</li>
 * </ul>
 */

public class Main {
    public static void main(String[] args) throws IOException {

        URL[] corpus = {
            // Pride and Prejudice, by Jane Austen
            new URL ( "https://www.gutenberg.org/files/1342/1342-0.txt" ),
            // The Adventures of Sherlock Holmes, by A. Conan Doyle
            new URL ( "http://www.gutenberg.org/cache/epub/1661/pg1661.txt" ),
            // A Tale of Two Cities, by Charles Dickens
            new URL ( "https://www.gutenberg.org/files/98/98-0.txt" ),
            // Alice's Adventures In Wonderland, by Lewis Carroll
            new URL ( "https://www.gutenberg.org/files/11/11-0.txt" ),
            // Moby Dick; or The Whale, by Herman Melville
            new URL ( "https://www.gutenberg.org/files/2701/2701-0.txt" ),
            // War and Peace, by Leo Tolstoy
            new URL ( "https://www.gutenberg.org/files/2600/2600-0.txt" ),
            // The Importance of Being Earnest, by Oscar Wilde
            new URL ( "http://www.gutenberg.org/cache/epub/844/pg844.txt" ),
                // The Wisdom of Father Brown, by G.K. Chesterton
            new URL ( "https://www.gutenberg.org/files/223/223-0.txt" ),
        };

        Synonyms synonyms = new Synonyms(corpus);
        System.out.println("synonym: " + synonyms.calculateCosineSimilarity("vexed", "synonym"));
        System.out.println("annoyed: " + synonyms.calculateCosineSimilarity("vexed", "annoyed"));
        System.out.println("book: " + synonyms.calculateCosineSimilarity("vexed", "book"));
        System.out.println("spellbound: " + synonyms.calculateCosineSimilarity("vexed", "spellbound"));
        System.out.println();
        System.out.println("rural: " + synonyms.calculateCosineSimilarity("provincial", "rural"));
        System.out.println("cosmopolitan: " + synonyms.calculateCosineSimilarity("provincial", "cosmopolitan"));
        System.out.println("forested: " + synonyms.calculateCosineSimilarity("provincial", "forested"));
        System.out.println("horse: " + synonyms.calculateCosineSimilarity("provincial", "horse"));
        System.out.println();


        FileInOut fio = new FileInOut ( "infile.txt", "outfile.txt", true );
        Scanner in = fio.getInFile();


        String word = in.next();
        System.out.println("word: " + word);
        String closestMatch = "null";
        double closeNum = -1;

        // I have no idea why running this gives different values than running them manually
        while ( in.hasNext() )
        {
            String potentialSynonyms = in.next();
            if(synonyms.calculateCosineSimilarity(word, potentialSynonyms) > closeNum) {
                closestMatch = potentialSynonyms;
                closeNum = synonyms.calculateCosineSimilarity(word, potentialSynonyms);
            }
            System.out.println(potentialSynonyms + ": " + synonyms.calculateCosineSimilarity(word, potentialSynonyms));
        }
        System.out.println("Most Similar: " + closestMatch);

        fio.closeFiles();

    }
}
