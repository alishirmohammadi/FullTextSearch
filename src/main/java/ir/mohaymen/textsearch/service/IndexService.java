package ir.mohaymen.textsearch.service;

import ir.mohaymen.textsearch.models.Document;
import ir.mohaymen.textsearch.repository.DocumentRepository;
import ir.mohaymen.textsearch.repository.IndexRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.util.List;

@Service
public class IndexService {

    @Autowired
    private IndexRepository indexRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private ApplicationContext applicationContext;

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

    @SneakyThrows
    public void initialize() {
        Resource[] resources = applicationContext.getResources("file:/A:/EnglishData/*");
        for (Resource resource :
                resources) {
            String title = resource.getFile().getName();
            String content = Files.readString(resource.getFile().toPath());
            Document document = new Document(title, content);
            documentRepository.save(document);
            indexDocument(document);
            System.out.printf("Document %d indexed.%n", document.getId());
        }
        System.out.println("document saving completed!");

    }
}
