package ir.mohaymen.textsearch.repository;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class IndexRepository {
    private final Map<String, HashSet<Integer>> index = new HashMap<>();

    public void indexToken(String word, int documentId) {
        if (!index.containsKey(word)) {
            index.put(word, new HashSet<>());
        }
        index.get(word).add(documentId);
    }

    public Set<Integer> getDocumentsContainingToken(String token) {
        HashSet<Integer> documents = index.get(token);
        return Objects.requireNonNullElseGet(documents, HashSet::new);
    }
}
