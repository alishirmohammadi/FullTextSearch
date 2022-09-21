package ir.mohaymen.textsearch.repository;

import ir.mohaymen.textsearch.models.Document;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class DocumentRepository {
    private final HashMap<Integer, Document> documents = new HashMap<>();

    public Document findById(Object id) {
        return documents.get((int) id);
    }

    public void save(Document document) {
        documents.put(document.getId(), document);
    }
}
