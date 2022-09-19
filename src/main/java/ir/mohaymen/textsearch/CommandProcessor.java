package ir.mohaymen.textsearch;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.util.Arrays;
import java.util.Scanner;

@Component
public class CommandProcessor {
    Scanner scanner = new Scanner(System.in);

    @Autowired
    private ApplicationContext applicationContext;

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
                    String documentName = resource.getFile().getName();
                    String content = Files.readString(resource.getFile().toPath());

                }
            }
        }
    }
}
