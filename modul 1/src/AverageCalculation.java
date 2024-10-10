public class AverageCalculation {
    public static void main(String[] args){
        int[] numbers = {10,20,30,40,50};
        int sum = 0; //menambahkan angka 0 karena hitungan dimulai 0 dan seterusnya

        for (int i = 0; i < numbers.length; i++){     //memberbaiki tanda <= menjadi <.karena kalau memakai = itu sama saja membandingkan antara i dengan number
            sum += numbers [i];
        }

        double average = (double) sum / numbers.length; // memastikan bahwa hasil dari operasi pembagian ini adalah nilai pecahan
        System.out.println("Rata - rata: " + average);
    }
}
