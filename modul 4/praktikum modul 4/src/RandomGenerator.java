import java.util.Random;
import java.util.Scanner;

public class RandomGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int choice;
        do {
            displayMenu();
            System.out.print("Pilih opsi (1/2/3): ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    generateRandomNumber(scanner, random);
                    break;
                case 2:
                    generateRandomString(scanner, random);
                    break;
                case 3:
                    System.out.println("Terima kasih, program berakhir.");
                    break;
                default:
                    System.out.println("Opsi tidak valid. Silakan pilih lagi.");
            }

        } while (choice != 3);

    }

    private static void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Random Angka");
        System.out.println("2. Random String");
        System.out.println("3. Keluar");
    }

    private static void generateRandomNumber(Scanner scanner, Random random) {
        System.out.print("Masukkan batas bawah angka acak: ");
        int lowerBound = scanner.nextInt();

        System.out.print("Masukkan batas atas angka acak: ");
        int upperBound = scanner.nextInt();

        int randomNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
        System.out.println("Angka acak: " + randomNumber);
    }

    private static void generateRandomString(Scanner scanner, Random random) {
        System.out.print("Masukkan panjang string acak: ");
        int length = scanner.nextInt();

        StringBuilder randomString = new StringBuilder();

        for (int i = 0; i < length; i++) {
            char randomChar = generateRandomChar(random);
            randomString.append(randomChar);
        }

        System.out.println("String acak: " + randomString.toString());
    }

    private static char generateRandomChar(Random random) {
        String validChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int index = random.nextInt(validChars.length());
        return validChars.charAt(index);
    }
}


