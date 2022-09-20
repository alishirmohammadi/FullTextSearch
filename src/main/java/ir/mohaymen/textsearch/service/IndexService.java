package ir.mohaymen.textsearch.service;

import ir.mohaymen.textsearch.models.Document;
import ir.mohaymen.textsearch.repository.IndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableScheduling
public class IndexService {

    @Autowired
    private IndexRepository indexRepository;

    private List<String> tokenize(Document document) {
        String content = document.getContent().toLowerCase();
        return List.of(content.split("\\s|\\.|!|,|\"|\\?|\\)|\\(|-|~|<|>|\\\\|/|\\{|}|\\||@"));
    }

    public void indexDocument(Document document) {
        List<String> tokens = tokenize(document);
        for (String token :
                tokens) {
            indexRepository.indexToken(token, document.getId());
        }
    }
}
