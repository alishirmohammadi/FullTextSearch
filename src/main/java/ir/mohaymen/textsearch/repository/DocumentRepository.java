package ir.mohaymen.textsearch.repository;

import ir.mohaymen.textsearch.models.Document;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@org.springframework.stereotype.Repository
public class DocumentRepository implements Repository<Document> {
    private final HashMap<Integer, Document> documents = new HashMap<>();

    @Override
    public Document findById(int id) {
        return documents.get(id);
    }

    @Override
    public void save(Document document) {
        documents.put(document.getId(), document);
    }
}
