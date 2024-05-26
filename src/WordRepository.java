import java.util.HashMap;
import java.util.Map;

public class WordRepository {
    private Map<String, Word> words;

    public WordRepository() {
        words = new HashMap<>();
        initializeWords();
    }

    private void initializeWords() {
        words.put("banana", new Word("banana", "buh-nan-uh", "A long curved fruit that grows in clusters and has soft pulpy flesh and yellow skin when ripe."));
        words.put("apple", new Word("apple", "ap-uhl", "A fruit with red or green skin and a crisp flesh."));
        words.put("orange", new Word("orange", "or-anj", "A round juicy citrus fruit with a tough bright reddish-yellow rind."));
        // Add more words as needed...
    }

    public Map<String, Word> getWords() {
        return words;
    }
}
