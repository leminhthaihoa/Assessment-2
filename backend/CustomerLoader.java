/**
 * @author <LE MINH THAI HOA - S3979194>
 *     References: https://niithanoi.edu.vn/hoc-dung-bufferedreader-trong-java-qua-vi-du.html
 *     https://www.w3schools.com/java/java_arraylist.asp
 */


package backend;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class CustomerLoader {
    private List<Customer> customers;

    public CustomerLoader() {
        this.customers = new ArrayList<>();
    }
    public List<Customer> loadCustomers(String fileName, String insuranceCardFileName) throws IOException {
        List<Customer> customers = new ArrayList<>();

        // Load insurance cards from the insuranceCardFileName file
        List<InsuranceCard> insuranceCards = InsuranceCardLoader.loadInsuranceCards(insuranceCardFileName);

        if (insuranceCards.isEmpty()) {
            System.err.println("Error loading customers: no insurance cards found in file: " + insuranceCardFileName);
            return customers;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length < 5) {
                    System.err.println("Error loading customer: invalid line format: " + line);
                    continue;
                }
                String id = values[0];
                String fullName = values[1];
                String cardNumber = values[2];
                boolean isPolicyHolder = Boolean.parseBoolean(values[3]);
                LocalDate expirationDate = LocalDate.parse(values[4]);

                // Find the insurance card with the matching cardNumber
                InsuranceCard insuranceCard = null;
                for (InsuranceCard ic : insuranceCards) {
                    if (ic.getCardNumber().equals(cardNumber)) {
                        insuranceCard = ic;
                        break;
                    }
                }

                if (insuranceCard == null) {
                    System.err.println("Error loading customer: invalid insurance card number: " + cardNumber);
                    continue;
                }

                // Load dependents if any
                List<String> dependents = new ArrayList<>();
                if (values.length > 5) {
                    for (int i = 5; i < values.length; i += 1) {
                        String dependentId = values[i];
                        dependents.add(dependentId);
                    }
                }

                Customer customer = new Customer(id, fullName, isPolicyHolder, insuranceCard,dependents);
                customers.add(customer);
            }
        }

        return customers;
    }
    }