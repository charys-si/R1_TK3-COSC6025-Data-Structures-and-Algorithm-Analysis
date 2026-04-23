/**
 * Tugas Kelompok ke-3 (Week 8)
 * Topik: Algorithmic Analysis, Mathematical Induction, and Recursive Functions
 *
 * Nama Kelompok: [Group - 7 ]
 * Anggota:
 * 1. [JIMMY SEPRIANSYAH] - [2902706364]
 * 2. [ANITA] - [2902734600]
 * 3. [CHARYS SONNIA INDRIYANA] - [2902728251]
 * 4. [SHAFA ALIFIA SALSABILA NURUL ICHSAN] - [2902697233]
 * 5. [MUHAMMAD RIZKI KURNIA PUTRA] - [2401968362]
 * Kelas: [Computer Science]
 */

import java.util.Scanner;

class LaguRekursif {
    String judul, artis;
    double durasi;

    LaguRekursif(String judul, String artis, double durasi) {
        this.judul = judul;
        this.artis = artis;
        this.durasi = durasi;
    }
}

public class PlaylistRekursif {
    static LaguRekursif[] playlist = new LaguRekursif[50];
    static int jumlahLagu = 0;
    static Scanner sc = new Scanner(System.in);

    /**
     * totalDurasi() - REKURSIF
     * Tujuan: Menghitung total durasi seluruh lagu
     * Base case: n == 0 → return 0
     * Recursive case: durasi[n-1] + totalDurasi(n-1)
     * Kompleksitas waktu: O(n)
     */
    static double totalDurasi(int n) {
        if (n == 0) return 0;
        return playlist[n-1].durasi + totalDurasi(n-1);
    }

    /**
     * tampilkanMundur() - REKURSIF
     * Tujuan: Menampilkan lagu dari indeks terakhir ke pertama
     * Base case: index < 0 → stop
     * Recursive case: tampilkan index, lalu panggil index-1
     * Kompleksitas waktu: O(n)
     */
    static void tampilkanMundur(int index) {
        if (index < 0) return;
        System.out.println((index+1) + ". " + playlist[index].judul +
                " - " + playlist[index].artis +
                " (" + playlist[index].durasi + " menit)");
        tampilkanMundur(index - 1);
    }

    /**
     * cariDurasiTerpanjang() - REKURSIF
     * Tujuan: Mencari durasi terpanjang dalam playlist
     * Base case: n == 1 → return durasi[0]
     * Recursive case: max(durasi[n-1], cariDurasiTerpanjang(n-1))
     * Kompleksitas waktu: O(n)
     */
    static double cariDurasiTerpanjang(int n) {
        if (n == 1) return playlist[0].durasi;
        double maxSebelumnya = cariDurasiTerpanjang(n - 1);
        return Math.max(playlist[n-1].durasi, maxSebelumnya);
    }

    /**
     * Mencari judul lagu dengan durasi terpanjang
     */
    static String cariJudulTerpanjang(int n, double durasiTerpanjang) {
        if (n == 0) return "";
        if (playlist[n-1].durasi == durasiTerpanjang) {
            return playlist[n-1].judul;
        }
        return cariJudulTerpanjang(n-1, durasiTerpanjang);
    }

    public static void main(String[] args) {
        // Data playlist untuk 10 lagu
        playlist[jumlahLagu++] = new LaguRekursif("Bohemian Rhapsody", "Queen", 5.55);
        playlist[jumlahLagu++] = new LaguRekursif("We Are the Champions", "Queen", 2.59);
        playlist[jumlahLagu++] = new LaguRekursif("The Show Must Go On", "Queen", 4.24);
        playlist[jumlahLagu++] = new LaguRekursif("I Want to Break Free", "Queen", 3.43);
        playlist[jumlahLagu++] = new LaguRekursif("Radio Ga Ga", "Queen", 5.48);
        playlist[jumlahLagu++] = new LaguRekursif("Under Pressure", "Queen", 4.08);
        playlist[jumlahLagu++] = new LaguRekursif("Another One Bites the Dust", "Queen", 3.32);
        playlist[jumlahLagu++] = new LaguRekursif("Somebody to Love", "Queen", 4.55);
        playlist[jumlahLagu++] = new LaguRekursif("Don't Stop Me Now", "Queen", 3.29);
        playlist[jumlahLagu++] = new LaguRekursif("We Will Rock You", "Queen", 2.01);

        System.out.println("=== ANALISIS REKURSIF PLAYLIST ===\n");
        System.out.println("Jumlah lagu: " + jumlahLagu);

        // 1. Total Durasi
        long start = System.nanoTime();
        double total = totalDurasi(jumlahLagu);
        long end = System.nanoTime();
        System.out.printf("Total durasi: %.2f menit\n", total);
        System.out.println("Execution Time (totalDurasi): " + (end - start) / 1_000_000 + " ms\n");

        // 2. Lagu Terpanjang
        start = System.nanoTime();
        double durasiTerpanjang = cariDurasiTerpanjang(jumlahLagu);
        String judulTerpanjang = cariJudulTerpanjang(jumlahLagu, durasiTerpanjang);
        end = System.nanoTime();
        System.out.printf("Lagu terpanjang: \"%s\" (%.2f menit)\n", judulTerpanjang, durasiTerpanjang);
        System.out.println("Execution Time (cariDurasiTerpanjang): " + (end - start) / 1_000_000 + " ms\n");

        // 3. Tampilkan Mundur
        start = System.nanoTime();
        System.out.println("Daftar lagu (ditampilkan terbalik):");
        tampilkanMundur(jumlahLagu - 1);
        end = System.nanoTime();
        System.out.println("Execution Time (tampilkanMundur): " + (end - start) / 1_000_000 + " ms\n");
    }
}