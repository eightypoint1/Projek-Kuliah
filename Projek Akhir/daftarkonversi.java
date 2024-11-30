/**
 * Kelas ini digunakan untuk menyetor informasi konversi dari satuan ke meter, dan sebaliknya
 * 
 * @author Ezekiel Aaron Marmora
 * @version 1.0
 */
public class daftarkonversi {

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
}

