import java.util.List;
import java.util.Comparator;

public class CPIMain {
    public static void main(String[] args) {
        String csvPath = "C:\\Users\\M S I\\Documents\\CorruptionIndex\\Corruption Data.csv";

        // Membaca data dari CSV
        List<CPIData> dataList = CPIReader.readCPIFromCSV(csvPath);

        // Validasi data
        if (dataList.isEmpty()) {
            System.out.println("""
                [ERROR] Data tidak terbaca. Periksa:
                1. Path file: """ + csvPath + """
                2. Format CSV (harus: score,country,rank)
                3. Akses permission ke file""");
            return;
        }

        // Mengurutkan berdasarkan rank
        dataList.sort(Comparator.comparingInt(CPIData::getRank));

        // Menampilkan hasil
        System.out.println("====================================");
        System.out.println("CORRUPTION PERCEPTION INDEX REPORT");
        System.out.println("====================================");
        System.out.printf("%-25s | %-5s | %s%n", "COUNTRY", "SCORE", "RANK");
        System.out.println("------------------------------------");

        // Menampilkan 10 teratas + 10 terbawah
        int displayCount = Math.min(10, dataList.size());

        System.out.println("[TOP COUNTRIES]");
        for (int i = 0; i < displayCount; i++) {
            CPIData data = dataList.get(i);
            System.out.printf("%-25s | %-5d | %d%n",
                    data.getCountry(), data.getScore(), data.getRank());
        }

        System.out.println("\n[WORST COUNTRIES]");
        for (int i = Math.max(0, dataList.size() - displayCount); i < dataList.size(); i++) {
            CPIData data = dataList.get(i);
            System.out.printf("%-25s | %-5d | %d%n",
                    data.getCountry(), data.getScore(), data.getRank());
        }

        // Statistik
        System.out.println("\n[STATISTICS]");
        System.out.println("Total countries: " + dataList.size());
        System.out.printf("Average score: %.1f%n",
                dataList.stream().mapToInt(CPIData::getScore).average().orElse(0));
    }
}