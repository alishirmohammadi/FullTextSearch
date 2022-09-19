package ir.mohaymen.textsearch.components;

import ir.mohaymen.textsearch.models.Document;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Preprocessor {
    public List<String> tokenize(Document document) {
        String content = document.getContent().toLowerCase();
        return List.of(content.split("\\s|\\.|!|,|\"|\\?|\\)|\\(|-|~"));
    }
}
