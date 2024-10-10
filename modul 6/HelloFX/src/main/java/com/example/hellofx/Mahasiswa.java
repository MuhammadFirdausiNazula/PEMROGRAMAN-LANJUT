package com.example.hellofx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Mahasiswa {
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty nim = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();

    public Mahasiswa(String name, String nim, String email) {
        setName(name);
        setNim(nim);
        setEmail(email);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public final String getName() {
        return nameProperty().get();
    }

    public final void setName(String name) {
        nameProperty().set(name);
    }

    public StringProperty nimProperty() {
        return nim;
    }

    public final String getNim() {
        return nimProperty().get();
    }

    public final void setNim(String nim) {
        nimProperty().set(nim);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public final String getEmail() {
        return emailProperty().get();
    }

    public final void setEmail(String email) {
        emailProperty().set(email);
    }
}

