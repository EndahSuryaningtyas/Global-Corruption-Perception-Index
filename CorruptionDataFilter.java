import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CorruptionDataFilter {
    public static void main(String[] args) {
        String csvFile = "C:\\Users\\M S I\\Documents\\CorruptionIndex\\Corruption Data.csv";
        List<String[]> data = new ArrayList<>();
        String[] headers = null;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (isHeader) {
                    headers = row;
                    isHeader = false;
                } else {
                    data.add(row);
                }
            }

            if (headers == null) {
                System.out.println("Gagal membaca header CSV.");
                return;
            }

            Scanner scanner = new Scanner(System.in);
            System.out.println("Kolom yang tersedia:");
            for (int i = 0; i < headers.length; i++) {
                System.out.println(i + ": " + headers[i]);
            }

            System.out.print("Masukkan nomor kolom untuk memfilter: ");
            int columnIndex = Integer.parseInt(scanner.nextLine());

            if (columnIndex < 0 || columnIndex >= headers.length) {
                System.out.println("Nomor kolom tidak valid.");
                return;
            }

            System.out.print("Masukkan kata kunci filter: ");
            String filterValue = scanner.nextLine().trim().toLowerCase();

            System.out.println("\n=== Hasil Filter ===");
            System.out.println(String.join(",", headers));
            for (String[] row : data) {
                if (columnIndex < row.length && row[columnIndex].toLowerCase().contains(filterValue)) {
                    System.out.println(String.join(",", row));
                }
            }

        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat membaca file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Input kolom harus berupa angka.");
        }
    }
}
