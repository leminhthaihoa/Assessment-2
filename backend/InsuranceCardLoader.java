/**
 * @author <LE MINH THAI HOA - S3979194>
 * Reference: https://niithanoi.edu.vn/hoc-dung-bufferedreader-trong-java-qua-vi-du.html
 */

package backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class InsuranceCardLoader {
    public static List<InsuranceCard> loadInsuranceCards(String fileName) {
        List<InsuranceCard> insuranceCards = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length < 4) {
                    System.err.println("Error loading insurance card: invalid line format: " + line);
                    continue;
                }
                InsuranceCard insuranceCard = new InsuranceCard();
                insuranceCard.setCardNumber(values[0]);
                insuranceCard.setCardHolder(values[1]);
                insuranceCard.setPolicyOwner(values[2]);
                insuranceCard.setExpirationDate(LocalDate.parse(values[3], formatter));
                insuranceCards.add(insuranceCard);
            }
        } catch (IOException e) {
            System.err.println("Error loading insurance cards: " + e.getMessage());
        }

        return insuranceCards;
    }
}