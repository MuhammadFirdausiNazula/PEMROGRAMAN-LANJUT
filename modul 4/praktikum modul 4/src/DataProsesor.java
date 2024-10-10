import java.util.Arrays;
import java.util.Scanner;

public class DataProsesor {
    public static void main(String[] args) {
        int[] data = {38, -15, 72, 0, -42, 19, -63, 50, 27, -84, 61, 5};

        Arrays.sort(data);

        // Menampilkan data yang sudah diurutkan
        System.out.println("Data yang sudah diurutkan: " + Arrays.toString(data));

        // Menerima input dari user untuk mencari indeks
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nilai yang ingin dicari: ");
        int targetValue = scanner.nextInt();

        int index = Arrays.binarySearch(data, targetValue);

        if (index >= 0) {
            System.out.println("Nilai " + targetValue + " ditemukan pada indeks " + index);
        } else {
            System.out.println("Nilai " + targetValue + " tidak ditemukan dalam data.");
        }

        int countPositive = 0;
        int countNegative = 0;
        int countZero = 0;

        for (int element : data) {
            if (element > 0) {
                countPositive++;
            } else if (element < 0) {
                countNegative++;
            } else {
                countZero++;
            }
        }

        // Menampilkan nilai rasio
        System.out.println("\nRasio elemen:");
        System.out.println("Positif: " + countPositive);
        System.out.println("Negatif: " + countNegative);
        System.out.println("Nol: " + countZero);
    }
}
