package com.example.hellofx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    private final TableView<Mahasiswa> table = new TableView<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Test TableView");
        stage.setWidth(450);
        stage.setHeight(550);

        Label label = new Label("Daftar Mahasiswa");
        label.setFont(new Font("Arial", 30));

        TableColumn<Mahasiswa, String> nameCol = new TableColumn<>("Nama");
        TableColumn<Mahasiswa, String> nimCol = new TableColumn<>("NIM");
        TableColumn<Mahasiswa, String> emailCol = new TableColumn<>("Email");

        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        nimCol.setCellValueFactory(cellData -> cellData.getValue().nimProperty());
        emailCol.setCellValueFactory(cellData -> cellData.getValue().emailProperty());

        table.getColumns().addAll(nameCol, nimCol, emailCol);

        ObservableList<Mahasiswa> data = FXCollections.observableArrayList(
                new Mahasiswa("Larynt", "202110370311189", "laryntsa@gmail.com"),
                new Mahasiswa("Ahya", "202110370311187", "ayaa@gmail.com")
        );

        table.setItems(data);

        VBox vbox = new VBox();
        vbox.setSpacing(8);
        vbox.setPadding(new Insets(20, 10, 10, 10));
        vbox.getChildren().addAll(label, table);

        TextField addName = new TextField();
        addName.setMaxWidth(nameCol.getPrefWidth());
        addName.setPromptText("Nama Mahasiswa");

        TextField addNim = new TextField();
        addNim.setMaxWidth(nimCol.getPrefWidth());
        addNim.setPromptText("NIM");

        TextField addEmail = new TextField();
        addEmail.setMaxWidth(emailCol.getPrefWidth());
        addEmail.setPromptText("Email");

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            data.add(new Mahasiswa(
                    addName.getText(),
                    addNim.getText(),
                    addEmail.getText()
            ));
            addName.clear();
            addNim.clear();
            addEmail.clear();
        });

        HBox hboxInput = new HBox();
        hboxInput.getChildren().addAll(addName, addNim, addEmail, addButton);
        hboxInput.setSpacing(10);

        vbox.getChildren().addAll(hboxInput);

        Scene scene = new Scene(new Group());
        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }
}

