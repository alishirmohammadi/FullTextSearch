package ir.mohaymen.textsearch.controller;

import ir.mohaymen.textsearch.models.Document;
import ir.mohaymen.textsearch.repository.DocumentRepository;
import ir.mohaymen.textsearch.service.IndexService;
import ir.mohaymen.textsearch.service.SearchService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CommandController {
    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    private IndexService indexService;
    @Autowired
    private SearchService searchService;

    @Autowired
    private DocumentRepository documentRepository;

    private String getCommand() {
        System.out.print("> ");
        return scanner.nextLine().trim().toLowerCase();
    }

    @SneakyThrows
    public void start() {
        while (true) {
            String command = getCommand();
            if (command.matches("index")) {
                indexService.initialize();
            } else if (command.matches("get\\s+\\d+")) {
                Document document = documentRepository.findById(
                        Integer.parseInt(
                                command.replaceAll("[^0-9]", "")
                        )
                );
                System.out.println(document);
            } else if (command.matches("^search\\s+(?<query>.+)$")) {
                Pattern pattern = Pattern.compile("^search\\s+(?<query>.+)$");
                Matcher matcher = pattern.matcher(command);
                if (matcher.find()) {
                    Set<Integer> documents = searchService.searchByQuery(matcher.group("query"));
                    documents.forEach(System.out::println);
                }
            } else if (command.matches("exit")) {
                return;
            } else {
                System.out.println("Invalid command!");
            }
        }
    }
}
