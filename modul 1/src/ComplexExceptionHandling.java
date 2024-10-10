import java.util.Scanner;

public class ComplexExceptionHandling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Masukkan angka dengan spasi:");
        String input = scanner.nextLine();

        try {
            String[] angkaString = input.split(" ");
            double total = 0;
            int jumlahAngka = 0;

            for (String angka : angkaString) {
                double nilai = Double.parseDouble(angka);
                total += nilai;
                jumlahAngka++;
            }

            if (jumlahAngka == 0) {
                throw new ArithmeticException("Tidak ada angka yang dimasukkan.");
            }

            double rataRata = total / jumlahAngka;
            System.out.println("Rata-rata: " + rataRata);
        } catch (NumberFormatException e) {
            System.out.println("Kesalahan: Masukkan harus berupa angka yang valid.");
        } catch (ArithmeticException e) {
            System.out.println("Kesalahan: Tidak dapat menghitung rata-rata karena tidak ada angka yang dimasukkan.");
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
//pengucualian , dari codingan ditas kita dapat menyimpulkan jadi saat kita menggunakan code selain angka kita tidak bisa menemukan hasil yang kita mau, karena ditas perinyahnya untuk menghitug rata rata angka