import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CPIReader {
    public static List<CPIData> readCPIFromCSV(String filePath) {
        List<CPIData> cpiDataList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip header line
            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                try {
                    int score = Integer.parseInt(values[0].trim());
                    String country = values[1].trim();
                    int rank = Integer.parseInt(values[2].trim());

                    cpiDataList.add(new CPIData(country, score, rank));
                } catch (Exception e) {
                    System.err.println("Error parsing line: " + line);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath);
            e.printStackTrace();
        }

        return cpiDataList;
    }
}