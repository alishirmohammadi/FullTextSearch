package ir.mohaymen.textsearch.service;

import ir.mohaymen.textsearch.repository.IndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SearchService {
    @Autowired
    private IndexRepository indexRepository;

    public Set<Integer> advancedSearch(List<String> or, List<String> and, List<String> not) {
        if (or.size() == 0 && and.size() > 0) {
            or.add(and.get(0));
            and.remove(0);
        }

        Set<Integer> documentIds = new HashSet<>();

        or.stream().map(word -> indexRepository.getDocumentsContainingToken(word)).forEach(documentIds::addAll);
        and.stream().map(word -> indexRepository.getDocumentsContainingToken(word)).forEach(documentIds::retainAll);
        not.stream().map(word -> indexRepository.getDocumentsContainingToken(word)).forEach(documentIds::removeAll);
        return documentIds;
    }
}
