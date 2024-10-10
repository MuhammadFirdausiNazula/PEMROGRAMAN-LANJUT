import java.util.Scanner;

public class PalindromeChecker {
    public static boolean isPalindrome(String teks)
    {

        String balik = "";

        boolean cek = false;

        for (int i = teks.length() - 1; i >= 0; i--) {
            balik = balik + teks.charAt(i);
        }

        if (teks.equals(balik)) {
            cek = true;
            System.out.println("=== BENAR ===");
        }else {
            System.out.println("=== salah ===");
        }
        return cek;
    }
    public static void main(String[] args)
    {   Scanner scanner = new Scanner(System.in);
        System.out.println("Masukkan Teks :");
        String input = scanner.next();
        String teks = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        teks = teks.toLowerCase();
        boolean valid = isPalindrome(teks);
        System.out.println(valid);
    }
}
//rangkaian karakter yang membaca sama dari depan maupun dari belakang
//untuk mendeteksi pola simetri dalam teks.