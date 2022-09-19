package ir.mohaymen.textsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TextSearchApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TextSearchApplication.class, args);
        CommandProcessor processor = context.getBean(CommandProcessor.class);
        processor.start();
    }
}
