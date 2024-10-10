import java.util.Scanner;
public class ExceptionHandlingExample {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("masukkan sebuah angka:");
        try {
            int number = scanner.nextInt();
            System.out.println("anda memasukkan angka: " + number);

        } catch (Exception e) {
            System.out.println(" Input bukan angka!");
        }
    
    }
}
