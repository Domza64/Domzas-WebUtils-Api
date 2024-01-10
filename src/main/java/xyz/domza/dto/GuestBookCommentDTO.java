package xyz.domza.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.text.DateFormat;
import java.util.Date;

public record GuestBookCommentDTO(@NotEmpty @Length(max = 64) String username,
                                  @NotEmpty @Length(max = 256) String message,
                                  Date date) {
    public String getDate() {
        return DateFormat.getDateInstance().format(date);
    }
}
