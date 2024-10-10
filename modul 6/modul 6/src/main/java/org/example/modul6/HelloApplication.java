package org.example.modul6;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    private TableView<Mahasiswa> table = new TableView<>();
    private ObservableList<Mahasiswa> data = FXCollections.observableArrayList();

    private TextField namaField = new TextField();
    private TextField nimField = new TextField();
    private TextField emailField = new TextField();
    private TextField fakultasField = new TextField();
    private TextField jurusanField = new TextField();
    private TextField alamatField = new TextField();
    private TextField kotaField = new TextField();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Biodata Mahasiswa Table");

        TableColumn<Mahasiswa, String> namaCol = new TableColumn<>("Nama");
        namaCol.setCellValueFactory(new PropertyValueFactory<>("nama"));

        TableColumn<Mahasiswa, String> nimCol = new TableColumn<>("NIM");
        nimCol.setCellValueFactory(new PropertyValueFactory<>("nim"));

        TableColumn<Mahasiswa, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Mahasiswa, String> fakultasCol = new TableColumn<>("Fakultas");
        fakultasCol.setCellValueFactory(new PropertyValueFactory<>("fakultas"));

        TableColumn<Mahasiswa, String> jurusanCol = new TableColumn<>("Jurusan");
        jurusanCol.setCellValueFactory(new PropertyValueFactory<>("jurusan"));

        TableColumn<Mahasiswa, String> alamatCol = new TableColumn<>("Alamat");
        alamatCol.setCellValueFactory(new PropertyValueFactory<>("alamat"));

        TableColumn<Mahasiswa, String> kotaCol = new TableColumn<>("Kota");
        kotaCol.setCellValueFactory(new PropertyValueFactory<>("kota"));


        table.getColumns().addAll(namaCol, nimCol, emailCol, fakultasCol, jurusanCol, alamatCol, kotaCol);
        table.setItems(data);

        GridPane inputGrid = new GridPane();
        inputGrid.setAlignment(javafx.geometry.Pos.CENTER);
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.setPadding(new Insets(25, 25, 25, 25));

        inputGrid.add(new Label("Nama:"), 0, 0);
        inputGrid.add(namaField, 1, 0);
        inputGrid.add(new Label("NIM:"), 0, 1);
        inputGrid.add(nimField, 1, 1);
        inputGrid.add(new Label("Email:"), 0, 2);
        inputGrid.add(emailField, 1, 2);
        inputGrid.add(new Label("Fakultas:"), 0, 3);
        inputGrid.add(fakultasField, 1, 3);
        inputGrid.add(new Label("Jurusan:"), 0, 4);
        inputGrid.add(jurusanField, 1, 4);
        inputGrid.add(new Label("Alamat:"), 0, 5);
        inputGrid.add(alamatField, 1, 5);
        inputGrid.add(new Label("Kota:"), 0, 6);
        inputGrid.add(kotaField, 1, 6);

        Button createButton = new Button("Create");

        createButton.setOnAction(event -> {
            if (validateInput()) {
                Mahasiswa mahasiswa = new Mahasiswa(
                        namaField.getText(),
                        nimField.getText(),
                        emailField.getText(),
                        fakultasField.getText(),
                        jurusanField.getText(),
                        alamatField.getText(),
                        kotaField.getText()
                );

                data.add(mahasiswa);
                clearInputFields();
            }
        });

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(javafx.geometry.Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(createButton);
        inputGrid.add(hbBtn, 1, 7);


        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().addAll(inputGrid, table);

        Scene scene = new Scene(vbox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private boolean validateInput() {
        if (namaField.getText().isEmpty() || nimField.getText().isEmpty() || emailField.getText().isEmpty() ||
                fakultasField.getText().isEmpty() || jurusanField.getText().isEmpty() || alamatField.getText().isEmpty() ||
                kotaField.getText().isEmpty()) {
            showAlert("Input tidak boleh kosong!");
            return false;
        }

        try {
            Long.parseLong(nimField.getText());
        } catch (NumberFormatException e) {
            showAlert("NIM harus berupa angka!");
            return false;
        }

        if (!fakultasField.getText().matches("[a-zA-Z]+")) {
            showAlert("Fakultas hanya boleh berupa huruf!");
            return false;
        }

        if (!jurusanField.getText().matches("[a-zA-Z]+")) {
            showAlert("Jurusan hanya boleh berupa huruf!");
            return false;
        }

        if (!emailField.getText().endsWith("@webmail.umm.ac.id")) {
            showAlert("Email harus diakhiri dengan @webmail.umm.ac.id!");
            return false;
        }

        return true;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearInputFields() {
        namaField.clear();
        nimField.clear();
        emailField.clear();
        fakultasField.clear();
        jurusanField.clear();
        alamatField.clear();
        kotaField.clear();
    }

    public static class Mahasiswa {
        private String nama;
        private String nim;
        private String email;
        private String fakultas;
        private String jurusan;
        private String alamat;
        private String kota;

        public Mahasiswa(String nama, String nim, String email, String fakultas, String jurusan, String alamat, String kota) {
            this.nama = nama;
            this.nim = nim;
            this.email = email;
            this.fakultas = fakultas;
            this.jurusan = jurusan;
            this.alamat = alamat;
            this.kota = kota;
        }

        public String getNama() {
            return nama;
        }

        public String getNim() {
            return nim;
        }

        public String getEmail() {
            return email;
        }

        public String getFakultas() {
            return fakultas;
        }

        public String getJurusan() {
            return jurusan;
        }

        public String getAlamat() {
            return alamat;
        }

        public String getKota() {
            return kota;
        }










    }
}
