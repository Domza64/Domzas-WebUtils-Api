package xyz.domza.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "diary_articles")
public class DiaryArticleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String title;
    private String text;

    public DiaryArticleModel() {
        // Default constructor for JPA
    }

    public DiaryArticleModel(String title, String text) {
       this.title = title;
       this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
