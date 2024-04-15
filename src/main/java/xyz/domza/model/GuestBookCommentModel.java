package xyz.domza.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("user_comment")
public class GuestBookCommentModel {
    @Id
    @Indexed(unique = true)
    private String id;

    private String username;
    private String message;
    private Date date;

    public GuestBookCommentModel() {
        // Default constructor for JPA
    }

    public GuestBookCommentModel(String username, String message, Date date) {
        this.username = username;
        this.message = message;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }
}
