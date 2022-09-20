package ir.mohaymen.textsearch.controller;

import ir.mohaymen.textsearch.models.Document;
import ir.mohaymen.textsearch.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/document")
public class DocumentController {
    @Autowired
    private DocumentRepository documentRepository;

    @GetMapping("/{id}")
    public Document getDocument(@PathVariable int id) {
        System.out.println("salam");
        Document document = documentRepository.findById(id);
        System.out.println(document);
        return document;
    }
}
