import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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
        Synonyms synonyms = new Synonyms(corpus, "the");
    }
}
