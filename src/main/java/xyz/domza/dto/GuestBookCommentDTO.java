package xyz.domza.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.text.DateFormat;
import java.util.Date;

public record GuestBookCommentDTO(@NotEmpty @NotNull String username,
                                  @NotEmpty @NotNull String message,
                                  Date date) {
    public String getDate() {
        return DateFormat.getDateInstance().format(date);
    }
}
