package ir.mohaymen.textsearch;

import ir.mohaymen.textsearch.components.Preprocessor;
import ir.mohaymen.textsearch.models.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PreprocessorTest {
    @Autowired
    private Preprocessor preprocessor;

    @Test
    void tokenizeTest() {
        Document document = new Document("title", "salam! ali (mohammad) ~!test,hi ");
        List<String> tokens = preprocessor.tokenize(document);
        assert tokens.contains("ali");
        assert tokens.contains("salam");
        assert tokens.contains("mohammad");
        assert tokens.contains("test");
        assert tokens.contains("hi");

    }
}
