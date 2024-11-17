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
        fungsiutama();

    }   

    public static void fungsiutama(){
        while(true){
            System.out.println("\nPilih mode konversi:");
            System.out.println("1. Konversi tunggal");
            System.out.println("2. Konversi berantai");
            System.out.println("3. Keluar");
            System.out.print("Buatlah Pilihan Anda = ");
            
            int modePilihan = scan.nextInt();
            
            if (modePilihan == 3) {
                System.out.println("Terimakasih Sudah menggunakan Program ini");
                break;
            }
            else if (modePilihan == 1) {
                lakukanKonversiTunggal();
            }
            else if (modePilihan == 2) {
                lakukanKonversiBerantai();
            }
            else {
                System.out.println("Pilihan tidak valid, coba lagi");
            }
        }
    }
    
    // Fungsi untuk konversi tunggal (original)
    public static void lakukanKonversiTunggal() {
        tunjukanpilihan("dari");
        int pilihan = scan.nextInt();
        if (pilihan < 1 || pilihan > 6) {
            System.out.println("Pilihan tidak valid, coba lagi");
            return;
        }
        
        tunjukanpilihan("ke");
        int pilihanTujuan = scan.nextInt();
        if (pilihanTujuan < 1 || pilihanTujuan > 6) {
            System.out.println("Pilihan tidak valid, coba lagi");
            return;
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
    
    // Fungsi baru untuk konversi berantai
    public static void lakukanKonversiBerantai() {
        System.out.print("Masukkan jumlah konversi yang diinginkan: ");
        int jumlahKonversi = scan.nextInt();
        
        if (jumlahKonversi < 1) {
            System.out.println("Jumlah konversi harus minimal 1");
            return;
        }
        
        // Meminta unit awal dan nilai awal
        tunjukanpilihan("dari");
        int unitAwal = scan.nextInt();
        if (unitAwal < 1 || unitAwal > 6) {
            System.out.println("Pilihan tidak valid");
            return;
        }
        
        System.out.print("Masukkan nilai " + satuanunit[unitAwal-1] + " = ");
        double nilaiAwal = scan.nextDouble();
        while(nilaiAwal < 0){
            System.out.print("Nilai tidak boleh dalam bentuk negatif, coba lagi = ");
            nilaiAwal = scan.nextDouble();
        }
        
        // Melakukan konversi berantai secara rekursif
        konversiBerantai(nilaiAwal, unitAwal, jumlahKonversi, 1);
    }
    
    // Fungsi rekursif untuk konversi berantai
    private static void konversiBerantai(double nilai, int unitSekarang, int totalKonversi, int langkahKe) {
        if (langkahKe > totalKonversi) {
            return;
        }
        
        System.out.printf("\nLangkah %d dari %d:%n", langkahKe, totalKonversi);
        tunjukanpilihan("ke");
        int unitSelanjutnya = scan.nextInt();
        
        if (unitSelanjutnya < 1 || unitSelanjutnya > 6) {
            System.out.println("Pilihan tidak valid, konversi dihentikan");
            return;
        }
        
        double hasilKonversi = buatkonversi(nilai, unitSekarang, unitSelanjutnya);
        System.out.printf("%.10f %s = %.10f %s%n", 
            nilai, satuanunit[unitSekarang-1], 
            hasilKonversi, satuanunit[unitSelanjutnya-1]);
        
        // Rekursi untuk konversi selanjutnya
        konversiBerantai(hasilKonversi, unitSelanjutnya, totalKonversi, langkahKe + 1);
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
