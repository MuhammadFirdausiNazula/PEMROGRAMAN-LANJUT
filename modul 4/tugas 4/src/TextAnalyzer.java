import java.util.Scanner;

public class TextAnalyzer {
    private static String text = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            System.out.print("Pilihan Anda (1-5): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    input(scanner);
                    break;
                case 2:
                    karakter();
                    break;
                case 3:
                    Jumlah();
                    break;
                case 4:
                    Banyak(scanner);
                    break;
                case 5:
                    System.out.println("Terima kasih telah menggunakan program Ini!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak valid. pilih kembali!!!!.");
            }
        }
    }

    public static void displayMenu() {
        System.out.println("..... MENU ANALYZER ..... ");
        System.out.println("1. Masukkan Teks");
        System.out.println("2. Hitung Jumlah Karakter");
        System.out.println("3. Hitung Jumlah Kata");
        System.out.println("4. Cari Kata dalam Teks");
        System.out.println("5. Keluar");
    }

    public static void input(Scanner scanner) {
        System.out.print("Masukkan teks: ");
        text = scanner.nextLine();
        System.out.println("Teks telah dimasukkan!\n");
    }

    public static String karakter() {
        int karakter = text.length();
        System.out.println("Jumlah karakter dalam teks: " + karakter + "\n");
        return null;
    }

    public static void Jumlah() {
        int kata = countWords(text);
        System.out.println("Jumlah kata dalam teks: " + kata + "\n");
    }

    public static void Banyak(Scanner scanner) {
        System.out.print("Masukkan kata yang ingin dicari: ");
        String hasilkata = scanner.nextLine();
        int banyakkata = countWordFrequency(text, karakter());
        System.out.println("Kata '" + hasilkata + "' ditemukan sebanyak " + banyakkata + " kali dalam teks.\n");
    }

    public static int countWords(String text) {
        String[] words = text.split("\\s+");
        return words.length;
    }

    public static int countWordFrequency(String text, String searchWord) {
        String[] words = text.split("\\s+");
        int frequency = 1;
        for (String word : words) {
            if (word.equalsIgnoreCase(searchWord)) {
                frequency++;
            }
        }
        return frequency;
    }
}