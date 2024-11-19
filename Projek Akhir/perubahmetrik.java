import java.util.*;

/**
 * Program ini mengonversi nilai antara berbagai unit metrik: kilometer, meter, 
 * centimeter, milimeter, mikrometer, dan nanometer. Program menawarkan tiga 
 * mode utama: konversi tunggal, konversi berantai, dan melihat riwayat konversi.
 * 
 * <p>Fitur utama:</p>
 * <ul>
 *   <li>Konversi tunggal: Konversi langsung dari satu unit ke unit lainnya.</li>
 *   <li>Konversi berantai: Melakukan serangkaian konversi bertahap.</li>
 *   <li>Riwayat konversi: Menampilkan hingga 100 riwayat konversi terakhir.</li>
 * </ul>
 * 
 * @author Ezekiel Aaron Marmora
 * @version 1.0
 */

public class perubahmetrik {
    private static Scanner scan = new Scanner(System.in);

    /**
     * Array untuk menyimpan hingga 100 riwayat konversi terakhir.
     * Masing-masing array menyimpan nilai awal, nilai hasil, unit awal, dan unit tujuan.
     */

    private static double[] nilaiAwalRiwayat = new double[100];  // menyimpan 100 konversi terakhir
    private static double[] nilaiAkhirRiwayat = new double[100];
    private static String[] unitAwalRiwayat = new String[100];
    private static String[] unitAkhirRiwayat = new String[100];
    private static int jumlahRiwayat = 0;  // menghitung jumlah riwayat yang tersimpan
    
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
        1,        // (1 m = 1 m)s
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
    
    /**
     * Fungsi utama program. Menyediakan menu untuk memilih mode operasi:
     * <ul>
     *   <li>1: Konversi tunggal</li>
     *   <li>2: Konversi berantai</li>
     *   <li>3: Melihat riwayat</li>
     *   <li>4: Keluar</li>
     * </ul>
     */

    private static void fungsiUtama(){
        System.out.println("Program ini mengubah antara berbagai macam unit metrik");
        while(true){
            System.out.println("\nPilih mode:");
            System.out.println("1. Konversi tunggal");
            System.out.println("2. Konversi berantai");
            System.out.println("3. Lihat riwayat konversi");
            System.out.println("4. Keluar");
            System.out.print("Buatlah Pilihan Anda = ");
            
            int modePilihan = scan.nextInt();
            
            switch(modePilihan) {
                case 1:
                    lakukanKonversiTunggal();
                    break;
                case 2:
                    lakukanKonversiBerantai();
                    break;
                case 3:
                    tampilkanRiwayat();
                    break;
                case 4:
                    System.out.println("Terimakasih Sudah menggunakan Program ini");
                    return;
                default:
                    System.out.println("Pilihan tidak valid, coba lagi");
            }
        }
    }

    /**
     * Menambahkan entri ke riwayat konversi. Jika riwayat penuh, elemen
     * paling lama akan dihapus untuk memberi ruang.
     * 
     * @param nilaiAwal Nilai awal sebelum konversi
     * @param nilaiHasil Nilai setelah konversi
     * @param unitAwal Unit awal sebelum konversi
     * @param unitAkhir Unit hasil konversi
     */

    private static void tambahRiwayat(double nilaiAwal, double nilaiHasil, String unitAwal, String unitAkhir) {
        // Jika array penuh, geser semua elemen ke kiri
        if (jumlahRiwayat == 100) {
            for (int i = 0; i < 99; i++) {
                nilaiAwalRiwayat[i] = nilaiAwalRiwayat[i + 1];
                nilaiAkhirRiwayat[i] = nilaiAkhirRiwayat[i + 1];
                unitAwalRiwayat[i] = unitAwalRiwayat[i + 1];
                unitAkhirRiwayat[i] = unitAkhirRiwayat[i + 1];
            }
            //Hapus yang Lama
            jumlahRiwayat--;
        }
        
        // Tambah riwayat baru
        nilaiAwalRiwayat[jumlahRiwayat] = nilaiAwal;
        nilaiAkhirRiwayat[jumlahRiwayat] = nilaiHasil;
        unitAwalRiwayat[jumlahRiwayat] = unitAwal;
        unitAkhirRiwayat[jumlahRiwayat] = unitAkhir;
        jumlahRiwayat++;
    }
    
    /**
     * Menampilkan riwayat konversi. Menampilkan hingga 100 entri terakhir
     * dalam urutan dari yang terbaru ke yang terlama.
     */

    private static void tampilkanRiwayat() {
        if (jumlahRiwayat == 0) {
            System.out.println("\nBelum ada riwayat konversi.");
            return;
        }
        
        System.out.println("\nRIWAYAT KONVERSI:");
        System.out.println("==================");
        
        // Tampilkan riwayat dari yang terbaru ke yang terlama
        for (int i = jumlahRiwayat-1; i>= 0; i--) {
            System.out.printf("%d. %.2f %s → %.2f %s%n", 
                jumlahRiwayat - i,
                nilaiAwalRiwayat[i], 
                unitAwalRiwayat[i], 
                nilaiAkhirRiwayat[i], 
                unitAkhirRiwayat[i]);
        }

    }
    
    /**
     * Melakukan konversi tunggal dari satu unit ke unit lainnya.
     * Menanyakan pengguna untuk memasukkan nilai, unit awal, dan unit tujuan.
     */
    
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
        System.out.printf("Hasil perubahan adalah = %.10f %s%n", 
            hasil, satuanunit[pilihanTujuan-1]);
            
        // Tambahkan ke riwayat
        tambahRiwayat(nilai, hasil, 
            satuanunit[pilihan-1], satuanunit[pilihanTujuan-1]);
    }
    
    /**
     * Melakukan serangkaian konversi metrik secara bertahap (berantai).
     * Pengguna dapat menentukan jumlah langkah konversi yang diinginkan
     * dan memilih unit tujuan untuk setiap langkah.
     */

    public static void lakukanKonversiBerantai() {
        System.out.print("Masukkan jumlah konversi yang diinginkan: ");
        int jumlahKonversi = scan.nextInt();
        
        if (jumlahKonversi < 1) {
            System.out.println("Jumlah konversi harus minimal 1");
            return;
        }
        
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
        
        konversiBerantaiRekursif(nilaiAwal, unitAwal, jumlahKonversi, 1);
    }
    
    /**
     * Fungsi rekursif untuk melakukan konversi berantai. Setiap langkah konversi
     * akan meminta input unit tujuan dari pengguna dan menampilkan hasilnya.
     * 
     * @param nilai Nilai yang akan dikonversi pada langkah ini
     * @param unitSekarang Unit asal untuk konversi saat ini (1-6)
     * @param totalKonversi Total jumlah langkah konversi yang diinginkan
     * @param indexLangkah Indeks langkah konversi saat ini
     */

    private static void konversiBerantaiRekursif(double nilai, int unitSekarang, 
                                                int totalKonversi, int indexLangkah) {
        if (indexLangkah > totalKonversi) {
            return;
        }
        
        System.out.printf("\nLangkah %d dari %d:%n", indexLangkah, totalKonversi);
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
            
        // Tambahkan ke riwayat
        tambahRiwayat(nilai, hasilKonversi, 
            satuanunit[unitSekarang-1], satuanunit[unitSelanjutnya-1]);
        
        konversiBerantaiRekursif(hasilKonversi, unitSelanjutnya, totalKonversi, indexLangkah + 1);
    }
    
    /**
     * Menampilkan daftar pilihan unit kepada pengguna.
     * 
     * @param arahKonversi "dari" atau "ke" untuk menunjukkan arah konversi
     */

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
    
    /**
     * Melakukan konversi nilai dari satu unit ke unit lainnya.
     * 
     * @param nilai Nilai yang akan dikonversi
     * @param dariUnit Unit asal (1-6)
     * @param keUnit Unit tujuan (1-6)
     * @return Nilai hasil konversi
     */

    public static double buatkonversi(double nilai, int dariUnit, int keUnit){
        // Pertama konversi ke meter 
        double nilaiDalamMeter = nilai * konversikemeter[dariUnit-1];
        // Kemudian konversi dari meter ke unit tujuan
        return nilaiDalamMeter * konversidarimeter[keUnit-1];
    }

    public static void main(String[] args) {
        fungsiUtama();
    }  
}
