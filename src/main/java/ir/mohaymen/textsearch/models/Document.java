package ir.mohaymen.textsearch.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Document {
    private String title;
    private String content;

    public int getId() {
        return Integer.parseInt(title);
    }

    @Override
    public String toString() {
        return "Document{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
