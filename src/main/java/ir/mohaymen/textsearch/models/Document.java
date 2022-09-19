package ir.mohaymen.textsearch.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Document {
    private String title;
    private String content;
}

