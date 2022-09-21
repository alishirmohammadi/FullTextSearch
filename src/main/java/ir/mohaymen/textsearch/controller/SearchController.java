package ir.mohaymen.textsearch.controller;

import ir.mohaymen.textsearch.models.Document;
import ir.mohaymen.textsearch.repository.DocumentRepository;
import ir.mohaymen.textsearch.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/search")
public class SearchController {
    @Autowired
    private SearchService searchService;
    @Autowired
    private DocumentRepository documentRepository;

    @GetMapping("/{query}")
    public List<Document> searchQuery(@PathVariable String query) {
        return searchService.searchByQuery(query).stream().map(
                documentRepository::findById
        ).collect(Collectors.toList());
    }

}
