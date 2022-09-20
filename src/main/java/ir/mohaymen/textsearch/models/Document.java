package ir.mohaymen.textsearch.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Document implements Model {
    private String title;
    private String content;

    @Override
    public int getId() {
        return Integer.parseInt(title);
    }
}

