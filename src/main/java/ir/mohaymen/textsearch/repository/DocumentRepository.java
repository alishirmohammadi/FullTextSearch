package ir.mohaymen.textsearch.repository;

import ir.mohaymen.textsearch.models.Document;

import java.util.HashMap;

public class DocumentRepository implements Repository<Document> {
    private HashMap<Integer, Document> documents;

    @Override
    public Document findById(int id) {
        return documents.get(id);
    }

    @Override
    public void save(Document document) {
        documents.put(document.getId(), document);
    }
}
