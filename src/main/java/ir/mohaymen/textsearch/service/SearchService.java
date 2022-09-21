package ir.mohaymen.textsearch.service;

import ir.mohaymen.textsearch.repository.IndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Set<Integer> searchByQuery(String query) {
        List<String> or = new ArrayList<>();
        List<String> and = new ArrayList<>();
        List<String> not = new ArrayList<>();
        String[] phrase = query.split("\\s+");
        for (String s : phrase) {
            if (s.startsWith("+")) {
                and.add(s.substring(1));
            } else if (s.startsWith("-")) {
                not.add(s.substring(1));
            } else {
                or.add(s);
            }
        }
        return advancedSearch(or, and, not);
    }
}
