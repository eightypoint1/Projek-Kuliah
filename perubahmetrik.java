import java.util.*;
/*
 * Program ini mengambil input dari pengguna dalam bentuk unit metrik apapun,
 * dan mengembalikan nilai dalam bentuk unit metrik yang dipilih, pilihan unit adalah
 * kilometer, meter, centimeter, milimeter, micrometer, nanometer.
 */
public class perubahmetrik {
    private static Scanner scan = new Scanner(System.in);
    //Tabel untuk konversi unit ke meter 
    static final double[] konversikemeter = {
        1000,     // (1 km = 1000 m)
        1,        // (1 m = 1 m)
        0.01,     // (1 cm = 0.01 m)
        0.001,    // (1 mm = 0.001 m)
        1e-6,     // (1 μm = 0.000001 m)
        1e-9      // (1 nm = 0.000000001 m)
    };
    //Tabel untuk konversi dari meter ke unit target
    static final double[] konversidarimeter = {
        0.001,    // (1 m = 0.001 km)
        1,        // (1 m = 1 m)
        100,      // (1 m = 100 cm)
        1000,     // (1 m = 1000 mm)
        1000000,  // (1 m = 1,000,000 μm)
        1e9       // (1 m = 1,000,000,000 nm)
    };
    //Tabel untuk satuan unit metrik
    static final String[] satuanunit = {
        "km",    // kilometer
        "m",     // meter
        "cm",    // centimeter
        "mm",    // milimeter
        "μm",    // micrometer
        "nm",    // nanometer
    };
    
    public static void main(String[] args) {
        System.out.println("Program ini mengubah antara berbagai macam unit metrik");
        while(true){
            tunjukanpilihan("dari");
            int pilihan = scan.nextInt();
            if (pilihan == 7) {
                System.out.println("Terimakasih Sudah menggunakan Program ini");
                break;
            }
            else if (pilihan <1 || pilihan > 7) {
                System.out.println("Pilihan tidak valid, coba lagi");
                continue;
            }
            
            tunjukanpilihan("ke");
            int pilihanTujuan = scan.nextInt();
            if (pilihanTujuan < 1 || pilihanTujuan > 6) {
                System.out.println("Pilihan tidak valid, coba lagi");
                continue;
            }
            
            System.out.print("Masukkan nilai " + satuanunit[pilihan-1] + " = ");
            double nilai = scan.nextDouble();
            while(nilai < 0){
                System.out.print("Nilai tidak boleh dalam bentuk negatif, coba lagi = ");
                nilai = scan.nextDouble();
            }
            
            double hasil = buatkonversi(nilai, pilihan, pilihanTujuan);
            System.out.printf("Hasil perubahan adalah = %.10f %s%n", hasil, satuanunit[pilihanTujuan-1]);
        }
    }   
    
    // Fungsi untuk mengoutput instruksi pilihan unit
    public static void tunjukanpilihan(String arahKonversi){
        System.out.println("\nPilih unit " + arahKonversi + ":");
        System.out.println("1. Kilometer");
        System.out.println("2. Meter");
        System.out.println("3. Centimeter");
        System.out.println("4. Milimeter");
        System.out.println("5. Micrometer");
        System.out.println("6. Nanometer");
        // Output Pilihan pertama dapat memberi opsi bagi pengguna untuk keluar
        if (arahKonversi.equals("dari")) {
            System.out.println("7. keluar");
        }
        System.out.print("Buatlah Pilihan Anda = ");
    }
    
    // Fungsi untuk mengkonversi dari unit pilihan ke unit tujuan
    public static double buatkonversi(double nilai, int dariUnit, int keUnit){
        // Pertama konversi ke meter 
        double nilaiDalamMeter = nilai * konversikemeter[dariUnit-1];
        // Kemudian konversi dari meter ke unit tujuan
        return nilaiDalamMeter * konversidarimeter[keUnit-1];
    }
}