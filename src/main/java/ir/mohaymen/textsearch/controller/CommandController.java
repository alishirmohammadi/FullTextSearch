package ir.mohaymen.textsearch.controller;

import ir.mohaymen.textsearch.models.Document;
import ir.mohaymen.textsearch.repository.DocumentRepository;
import ir.mohaymen.textsearch.service.IndexService;
import ir.mohaymen.textsearch.service.SearchService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@Component
public class CommandController {
    private final Scanner scanner = new Scanner(System.in);
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private IndexService indexService;
    @Autowired
    private SearchService searchService;

    @Autowired
    private DocumentRepository documentRepository;

    private String getCommand() {
        System.out.print("> ");
        return scanner.nextLine().trim();
    }

    @SneakyThrows
    public void start() {
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
            } else if (command.matches("search(\\s+-?\\+?\\w+)+")) {
                List<String> or = new ArrayList<>();
                List<String> and = new ArrayList<>();
                List<String> not = new ArrayList<>();
                String[] phrase = command.split("\\s+");
                for (int i = 1; i < phrase.length; i++) {
                    if (phrase[i].startsWith("+")) {
                        and.add(phrase[i].substring(1));
                    } else if (phrase[i].startsWith("-")) {
                        not.add(phrase[i].substring(1));
                    } else {
                        or.add(phrase[i]);
                    }
                }
                Set<Integer> documents = searchService.advancedSearch(or, and, not);
                documents.forEach(System.out::println);
            } else if (command.matches("exit")) {
                return;
            } else {
                System.out.println("Invalid command!");
            }
        }
    }
}
