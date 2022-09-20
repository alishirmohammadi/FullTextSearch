package ir.mohaymen.textsearch;

import ir.mohaymen.textsearch.components.Preprocessor;
import ir.mohaymen.textsearch.models.Document;
import ir.mohaymen.textsearch.repository.DocumentRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Component
public class CommandProcessor {
    Scanner scanner = new Scanner(System.in);

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private Preprocessor preprocessor;

    @Autowired
    private DocumentRepository documentRepository;

    private String getCommand() {
        System.out.print("> ");
        return scanner.nextLine().trim();
    }

    @SneakyThrows
    public void start() {
        boolean exit = false;
        while (true) {
            String command = getCommand();
            if (command.matches("index")) {
                Resource[] resources = applicationContext.getResources("file:/A:/EnglishData/*");
                for (Resource resource :
                        resources) {
                    String title = resource.getFile().getName();
                    String content = Files.readString(resource.getFile().toPath());
                    Document document = new Document(title, content);
                    documentRepository.save(document);
                }
                System.out.println("document saving completed!");
            } else if (command.matches("get\\s+\\d+")) {
                Document document = documentRepository.findById(
                        Integer.parseInt(
                                command.replaceAll("[^0-9]", "")
                        )
                );
                System.out.println(document);
            }
        }
    }
}
