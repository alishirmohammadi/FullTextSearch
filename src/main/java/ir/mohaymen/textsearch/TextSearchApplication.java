package ir.mohaymen.textsearch;

import ir.mohaymen.textsearch.controller.CommandController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TextSearchApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TextSearchApplication.class, args);
        CommandController processor = context.getBean(CommandController.class);
        processor.start();
    }
}
