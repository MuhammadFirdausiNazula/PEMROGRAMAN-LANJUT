import java.util.Scanner;
import java.lang.Math;

public class Kalkulator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean exit = false;

        while (!exit) {
            // Menu
            System.out.println("=== Kalkulator ===");
            System.out.println("1. Akar Kuadrat");
            System.out.println("2. Pangkat");
            System.out.println("3. Logaritma");
            System.out.println("4. Faktorial");
            System.out.println("5. Keluar");
            System.out.print("Pilih operasi (1/2/3/4/5): ");

            // Pilih operasi
            int operasi = sc.nextInt();

            switch (operasi) {
                case 1:
                    // Akar kuadrat
                    System.out.print("Masukkan bilangan: ");
                    double bilangan = sc.nextDouble();
                    double akar = Math.sqrt(bilangan);
                    System.out.println("Akar kuadrat dari " + bilangan + " adalah " + akar);
                    break;
                case 2:
                    // Pangkat
                    System.out.print("Masukkan bilangan: ");
                    double bilangan1 = sc.nextDouble();
                    System.out.print("Masukkan pangkat: ");
                    double pangkat = sc.nextDouble();
                    double hasil = Math.pow(bilangan1, pangkat);
                    System.out.println(bilangan1 + " pangkat " + pangkat + " adalah " + hasil);
                    break;
                case 3:
                    // Logaritma
                    System.out.print("Masukkan bilangan: ");
                    double bilangan2 = sc.nextDouble();
                    System.out.println("Logaritma natural dari " + bilangan2 + " adalah " + Math.log(bilangan2));
                    break;
                case 4:
                    // Faktorial
                    System.out.print("Masukkan bilangan: ");
                    int bilangan3 = sc.nextInt();
                    int faktorial = 1;
                    for (int i = 1; i <= bilangan3; i++) {
                        faktorial *= i;
                    }
                    System.out.println("Faktorial dari " + bilangan3 + " adalah " + faktorial);
                    break;
                case 5:
                    // Keluar
                    System.out.println("Terima kasih telah menggunakan kalkulator ini!");
                    exit = true; // Set exit to true to break out of the loop
                    break;
                default:
                    // Opsi tidak valid
                    System.out.println("Opsi yang Anda pilih tidak valid!");
                    break;
            }
        }
    }
}