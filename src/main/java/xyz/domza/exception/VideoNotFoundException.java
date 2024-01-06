package xyz.domza.exception;

public class VideoNotFoundException extends RuntimeException {
    public VideoNotFoundException() {
        super();
    }

    public VideoNotFoundException(String message) {
        super(message);
    }
}
