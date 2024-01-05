package xyz.domza.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_comments")
public class GuestBookCommentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

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

    public int getId() {
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
