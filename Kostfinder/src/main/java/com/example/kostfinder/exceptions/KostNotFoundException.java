package com.kostfinder.kostfinder.exceptions;

public class KostNotFoundException extends RuntimeException {

    // Konstruktor tanpa argumen
    public KostNotFoundException() {
        super();
    }

    // Konstruktor dengan pesan
    public KostNotFoundException(String message) {
        super(message);
    }

    // Konstruktor dengan pesan dan penyebab
    public KostNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    // Konstruktor dengan penyebab
    public KostNotFoundException(Throwable cause) {
        super(cause);
    }
}
