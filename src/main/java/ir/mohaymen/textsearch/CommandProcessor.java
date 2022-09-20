package ir.mohaymen.textsearch;

import ir.mohaymen.textsearch.models.Document;
import ir.mohaymen.textsearch.repository.DocumentRepository;
import ir.mohaymen.textsearch.service.IndexService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.util.Scanner;

@Component
public class CommandProcessor {
    private final Scanner scanner = new Scanner(System.in);
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private IndexService indexService;

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
                    indexService.indexDocument(document);
                    System.out.printf("Document %d indexed.%n", document.getId());
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
