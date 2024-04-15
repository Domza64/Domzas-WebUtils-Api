package xyz.domza.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("diary_article")
public class DiaryArticleModel {
    @Id
    @Indexed(unique = true)
    private String id;

    private String title;
    private String text;

    public DiaryArticleModel() {
        // Default constructor for JPA
    }

    public DiaryArticleModel(String title, String text) {
       this.title = title;
       this.text = text;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
