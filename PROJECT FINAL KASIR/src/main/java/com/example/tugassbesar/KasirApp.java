package com.example.tugassbesar;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.print.PrinterJob;
import javafx.stage.FileChooser;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class KasirApp extends Application {

    private static final String FILE_PATH = "produk.txt";
    private Map<String, Produk> daftarProduk = new HashMap<>();
    private ListView<String> transaksiListView = new ListView<>();
    private TextField totalField = new TextField();
    private final TextField bayarField = new TextField();
    private TextField kembalianField = new TextField();
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Program Kasir ");

        // Load products from file
        readData();
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(createHeader());
        borderPane.setLeft(createProdukPanel());
        borderPane.setCenter(createTransaksiPanel());
        borderPane.setRight(createPembayaranPanel());

        Scene scene = new Scene(borderPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void createData(String nama, double harga, int stok) {
        Produk produk = new Produk(nama, harga, stok);
        daftarProduk.put(nama, produk);
        saveProdukToFile();
    }

    private void clearTransaksi() {
        transaksiListView.getItems().clear();
        updateTotal();
        bayarField.clear();
        kembalianField.clear();
        showAlert("Transaksi berhasil dihapus!");
    }


    private void readData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            daftarProduk.clear(); // Menghapus daftar produk yang ada
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String nama = parts[0];
                    double harga = Double.parseDouble(parts[1]);
                    int stok = Integer.parseInt(parts[2]);
                    tambahProduk(nama, harga, stok);
                }
            }
            createProdukPanel(); // Memanggil createProdukPanel setelah pembaruan daftar produk
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void saveProdukToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Produk produk : daftarProduk.values()) {
                writer.write(produk.nama + "," + produk.harga + "," + produk.stok);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateProdukPanel() {
        VBox produkPanel = createProdukPanel();
        BorderPane borderPane = (BorderPane) primaryStage.getScene().getRoot();
        borderPane.setLeft(produkPanel);
    }

    private HBox createHeader() {
        HBox header = new HBox();
        header.setPadding(new Insets(10, 10, 10, 10));
        header.setStyle("-fx-background-color: #9ACD32; -fx-border-color: #3498db; -fx-border-width: 0 0 2 0;");

        Label title = new Label("KASIR");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 36; -fx-font-family: 'Arial Black'");
        header.getChildren().add(title);


        return header;
    }


    private VBox createProdukPanel() {
        VBox produkPanel = new VBox(10);
        produkPanel.setPadding(new Insets(10, 10, 10, 10));
        produkPanel.setStyle("-fx-background-color: #ecf0f1; -fx-border-color: #3498db; -fx-border-width: 0 2 0 0;");

        Label label = new Label("DAFTAR PRODUK");
        label.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");
        produkPanel.getChildren().add(label);

        HBox buttonsBox = new HBox(10);

        // Button to add new product
        Button createButton = new Button("Tambah Produk");
        createButton.setStyle("-fx-font-size: 14;");
        createButton.setOnAction(e -> showCreateDialog());
        buttonsBox.getChildren().add(createButton);

        // Button to upload products
        Button uploadButton = new Button("+");
        uploadButton.setStyle("-fx-font-size: 15; -fx-min-width: 40;");
        uploadButton.setOnAction(e -> showUploadDialog());
        buttonsBox.getChildren().add(uploadButton);

        // Refresh button with refresh icon
        Button refreshButton = new Button("\uD83D\uDD04");
        refreshButton.setStyle("-fx-font-size: 14;");
        refreshButton.setOnAction(e -> updateCashierPanel());
        buttonsBox.getChildren().add(refreshButton);

        produkPanel.getChildren().add(buttonsBox);

        // Check if primaryStage.getScene() is not null before accessing getRoot()
        Scene scene = primaryStage.getScene();
        if (scene != null) {
            BorderPane borderPane = (BorderPane) scene.getRoot();

            // Dynamically update the UI with the current list of products
            daftarProduk.forEach((namaProduk, produk) -> {
                Button produkButton = new Button(namaProduk + "\n" + "Rp " + produk.getHarga());
                produkButton.setStyle("-fx-min-width: 120; -fx-min-height: 80; -fx-font-size: 14;");
                produkButton.setOnAction(e -> tambahKeTransaksi(namaProduk));

                // Create a context menu for each product button
                ContextMenu contextMenu = new ContextMenu();
                MenuItem deleteItem = new MenuItem("Hapus Produk");
                deleteItem.setOnAction(event -> deleteProduk(namaProduk));
                contextMenu.getItems().add(deleteItem);

                produkButton.setContextMenu(contextMenu);

                produkPanel.getChildren().add(produkButton);
            });

            borderPane.setLeft(produkPanel);
        }

        return produkPanel;
    }

    private void deleteProduk(String namaProduk) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi Hapus Produk");
        alert.setHeaderText(null);
        alert.setContentText("Apakah Anda yakin ingin menghapus produk " + namaProduk + "?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            daftarProduk.remove(namaProduk);
            saveProdukToFile();
            updateProdukPanel();
            showAlert("Produk " + namaProduk + " berhasil dihapus.");
        }
    }

    private void updateCashierPanel() {
        readData();
    }

    private VBox createTransaksiPanel() {
        VBox transaksiPanel = new VBox(10);
        transaksiPanel.setPadding(new Insets(10, 10, 10, 10));
        transaksiPanel.setStyle("-fx-background-color: #9ACD32; -fx-border-color: #9ACD32; -fx-border-width: 0 2 0 2;");

        Label label = new Label("TRANKSAKSI PENJUALAN");
        label.setStyle("-fx-font-size: 24; -fx-font-weight: bold; -fx-text-fill: white;");
        transaksiPanel.getChildren().add(label);

        transaksiListView.setPrefHeight(300);
        transaksiPanel.getChildren().add(transaksiListView);

        HBox totalBox = new HBox(10);
        totalBox.getChildren().addAll(new Label("Total: "), totalField);
        transaksiPanel.getChildren().add(totalBox);

        Button clearButton = new Button("Clear Transaksi");
        clearButton.setStyle("-fx-font-size: 14;");
        clearButton.setOnAction(e -> clearTransaksi());
        transaksiPanel.getChildren().add(clearButton);

        Button printButton = new Button("Cetak Nota");
        printButton.setStyle("-fx-font-size: 14;");
        printButton.setOnAction(e -> cetakNota());
        transaksiPanel.getChildren().add(printButton);

        return transaksiPanel;
    }

    private VBox createPembayaranPanel() {
        VBox pembayaranPanel = new VBox(10);
        pembayaranPanel.setPadding(new Insets(10, 10, 10, 10));
        pembayaranPanel.setStyle("-fx-background-color: #ecf0f1; -fx-border-color: #3498db; -fx-border-width: 0 0 0 2;");

        Label label = new Label("PEMBAYARAN");
        label.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");
        pembayaranPanel.getChildren().add(label);

        HBox bayarBox = new HBox(10);
        bayarBox.getChildren().addAll(new Label("Bayar: "), bayarField);
        pembayaranPanel.getChildren().add(bayarBox);

        Button hitungButton = new Button("Hitung Kembalian");
        hitungButton.setStyle("-fx-font-size: 14;");
        hitungButton.setOnAction(e -> hitungKembalian());
        pembayaranPanel.getChildren().add(hitungButton);

        HBox kembalianBox = new HBox(10);
        kembalianBox.getChildren().addAll(new Label("Kembalian: "), kembalianField);
        pembayaranPanel.getChildren().add(kembalianBox);

        // Tambahkan ImageView untuk logo
        ImageView logoImageView = new ImageView(new Image("/pngwing.com.png"));
        logoImageView.setFitWidth(250); // Sesuaikan lebar logo sesuai kebutuhan
        logoImageView.setFitHeight(250); // Sesuaikan tinggi logo sesuai kebutuhan

        pembayaranPanel.getChildren().add(logoImageView);

        return pembayaranPanel;
    }


    // Metode showUploadDialog
    private void showUploadDialog() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pilih File Produk");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TXT Files", "*.txt")
        );

        File selectedFile = fileChooser.showOpenDialog(primaryStage);

        if (selectedFile != null) {
            readAndAddProductsFromFile(selectedFile);
            updateProdukPanel();
        }
    }

    // Metode readAndAddProductsFromFile
    private void readAndAddProductsFromFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String nama = parts[0];
                    double harga = Double.parseDouble(parts[1]);
                    int stok = Integer.parseInt(parts[2]);
                    tambahProduk(nama, harga, stok);
                }
            }
            showAlert("Produk berhasil ditambahkan dari file!");
        } catch (IOException | NumberFormatException e) {
            showAlert("Gagal membaca file atau format tidak valid.");
        }
    }

    private void tambahProduk(String nama, double harga, int stok) {
        daftarProduk.put(nama, new Produk(nama, harga, stok));
        saveProdukToFile();
        createProdukPanel(); // Memanggil createProdukPanel setelah pembaruan daftar produk
    }

    private void tambahKeTransaksi(String namaProduk) {
        if (daftarProduk.containsKey(namaProduk)) {
            Produk produk = daftarProduk.get(namaProduk);
            if (produk.getStok() > 0) {
                transaksiListView.getItems().add(namaProduk + "\t\t" + "Rp " + produk.getHarga());
                produk.kurangiStok();
                updateTotal();
                saveProdukToFile();
            } else {
                showAlert("Stok produk " + namaProduk + " habis.");
            }
        } else {
            showAlert("Produk " + namaProduk + " tidak ditemukan.");
        }
    }

    private void updateTotal() {
        double total = 0;
        for (String item : transaksiListView.getItems()) {
            String[] parts = item.split("\t\t");
            total += Double.parseDouble(parts[1].substring(3));
        }
        totalField.setText("Rp " + total);
    }

    private void hitungKembalian() {
        try {
            double total = Double.parseDouble(totalField.getText().substring(3));
            double bayar = Double.parseDouble(bayarField.getText());
            if (bayar >= total) {
                double kembalian = bayar - total;
                kembalianField.setText("Rp " + kembalian);
                showAlert("Transaksi berhasil!\nKembalian: Rp " + kembalian);
            } else {
                showAlert("Jumlah pembayaran kurang!");
            }
        } catch (NumberFormatException e) {
            showAlert("Masukkan jumlah pembayaran dengan benar.");
        }
    }

    private void cetakNota() {
        String nota = generateNota();
        Text notaText = new Text(nota);

        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null && job.showPrintDialog(null)) {
            boolean success = job.printPage(notaText);
            if (success) {
                job.endJob();
                showAlert("Nota berhasil dicetak!");
            } else {
                showAlert("Gagal mencetak nota.");
            }
        }
    }

    private String generateNota() {
        StringBuilder nota = new StringBuilder();
        nota.append("======= NOTA PEMBAYARAN =======\n");
        nota.append("Tanggal: ").append(getCurrentDate()).append("\n\n");
        nota.append("Daftar Produk:\n");
        for (String item : transaksiListView.getItems()) {
            nota.append(item).append("\n");
        }
        nota.append("\nTotal: ").append(totalField.getText()).append("\n");
        nota.append("Bayar: ").append(bayarField.getText()).append("\n");
        nota.append("Kembalian: ").append(kembalianField.getText()).append("\n");
        nota.append("=============================");
        return nota.toString();
    }

    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informasi");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showCreateDialog() {
        Dialog<Pair<String, Double>> dialog = new Dialog<>();
        dialog.setTitle("Tambah Produk Baru");
        dialog.setHeaderText(null);

        ButtonType tambahButtonType = new ButtonType("Tambah", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(tambahButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField namaField = new TextField();
        TextField hargaField = new TextField();
        TextField stokField = new TextField();

        grid.add(new Label("Nama Produk:"), 0, 0);
        grid.add(namaField, 1, 0);
        grid.add(new Label("Harga:"), 0, 1);
        grid.add(hargaField, 1, 1);
        grid.add(new Label("Stok:"), 0, 2);
        grid.add(stokField, 1, 2);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == tambahButtonType) {
                while (true) {
                    try {
                        double harga = Double.parseDouble(hargaField.getText());
                        int stok = Integer.parseInt(stokField.getText());

                        if (harga > 1000000) {
                            showAlert("Harga tidak boleh melebihi 1 juta!");
                        } else if (harga < 0 || stok < 0) {
                            showAlert("Harga dan Stok tidak boleh negatif!");
                        } else {
                            return new Pair<>(namaField.getText(), harga);
                        }
                    } catch (NumberFormatException e) {
                        showAlert("Harga atau Stok harus berupa angka!");
                    }

                    Optional<Pair<String, Double>> result = dialog.showAndWait();
                    if (result.isPresent()) {
                        namaField.setText(result.get().getKey());
                        hargaField.setText(Double.toString(result.get().getValue()));
                        stokField.setText("");

                        break;
                    }
                }
            }
            return null;
        });

        Optional<Pair<String, Double>> result = dialog.showAndWait();

        result.ifPresent(data -> {
            String nama = data.getKey();
            double harga = data.getValue();
            int stok = Integer.parseInt(stokField.getText());
            createData(nama, harga, stok);
            showAlert("Produk baru berhasil ditambahkan!");
            updateProdukPanel(); // Memanggil updateProdukPanel setelah penambahan produk baru
        });
    }




    private static class Produk {
        private String nama;
        private double harga;
        private int stok;

        public Produk(String nama, double harga, int stok) {
            this.nama = nama;
            this.harga = harga;
            this.stok = stok;
        }

        public double getHarga() {
            return harga;
        }

        public int getStok() {
            return stok;
        }

        public void kurangiStok() {
            stok--;
        }

    }
}
