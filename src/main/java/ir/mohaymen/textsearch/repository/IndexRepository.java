package ir.mohaymen.textsearch.repository;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;

@Component
public class IndexRepository {
    private final HashMap<String, HashSet<Integer>> index = new HashMap<>();

    public void indexToken(String word, int documentId) {
        if (!index.containsKey(word)) {
            index.put(word, new HashSet<>());
        }
        index.get(word).add(documentId);
    }
}
