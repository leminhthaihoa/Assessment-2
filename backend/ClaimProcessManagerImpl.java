/**
 * @author <LE MINH THAI HOA - S3979194>
 *     References: https://www.geeksforgeeks.org/java-io-filereader-class/
 */


package backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ClaimProcessManagerImpl implements ClaimProcessManager{
    private List<Claim> claims;
    private List<Customer> customers;

    private List<InsuranceCard> insuranceCards;

    public ClaimProcessManagerImpl() {
        this.claims = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.insuranceCards = new ArrayList<>();
    }

    @Override
    public void add(Claim claim) {
        claims.add(claim);
    }

    @Override
    public void update(Claim claim) {
        for (int i = 0; i < claims.size(); i++){
            if (claims.get(i).getId().equals(claim.getId())){
                claims.set(i,claim);
            }
        }
    }

    @Override
    public void delete(Claim claim) {
        claims.remove(claim);
    }

    @Override
    public Claim getOne(String id) {
        for (Claim claim : claims){
            if (claim.getId().equals(id)){
                return claim;
            }
        }
        return null;
    }

    @Override
    public List<Claim> getAll() {
        return claims;
    }

    public void saveClaims(String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (Claim claim : claims) {
                String insuredPersonName = claim.getInsuredPerson();
                String cardNumber = claim.getCardNumber();
                String examDate = claim.getExamDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String documents = String.join(";", claim.getDocuments());
                String receiverBankingInfo = claim.getReceiverBankInfo().getBank() + "-"
                        + claim.getReceiverBankInfo().getName() + "-"
                        + claim.getReceiverBankInfo().getNumber();
                writer.write(claim.getId() + "," + claim.getClaimDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "," + insuredPersonName + "," + cardNumber + "," + examDate + "," + documents + "," + claim.getClaimAmount() + "," + claim.getStatus() + "," + receiverBankingInfo + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error saving claims: " + e.getMessage());
        }
    }

    public void loadCustomers(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                Customer customer = new Customer();
                customer.setID(values[0]);
                customer.setFullName(values[1]);
                customer.setPolicyHolder(Boolean.parseBoolean(values[2]));
                InsuranceCard insuranceCard = new InsuranceCard();
                insuranceCard.setCardNumber(values[3]);
                customer.setInsuranceCardNumber(insuranceCard);
                customers.add(customer);
            }
        } catch (IOException e) {
            System.err.println("Error loading customers: " + e.getMessage());
        }
    }

    public void loadInsuranceCards(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                InsuranceCard insuranceCard = new InsuranceCard();
                insuranceCard.setCardNumber(values[0]);
                insuranceCards.add(insuranceCard);
            }
        } catch (IOException e) {
            System.err.println("Error loading insurance cards: " + e.getMessage());
        }
    }


    public void loadClaims(String claimsFileName, String customersFileName, String insuranceCardsFileName) {
        loadCustomers(customersFileName);
        loadInsuranceCards(insuranceCardsFileName);

        try (BufferedReader reader = new BufferedReader(new FileReader(claimsFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                Claim claim = new Claim();
                claim.setId(values[0]);
                claim.setClaimDate(LocalDate.parse(values[1], DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                Customer customer = customers.stream()
                        .filter(c -> c.getFullName().equals(values[2]))
                        .findFirst()
                        .orElse(null);
                if (customer == null) {
                    System.out.println("Skipping claim for unregistered customer: " + values[2]);
                    continue;
                }
                InsuranceCard insuranceCard = insuranceCards.stream()
                        .filter(ic -> ic.getCardNumber().equals(values[3]))
                        .findAny()
                        .orElse(null);
                if (insuranceCard == null) {
                    System.out.println("Skipping invalid insurance card number: " + values[3]);
                    continue;
                }
                claim.setInsuredPerson(customer.getFullName());
                claim.setCardNumber(values[3]);
                claim.setExamDate(LocalDate.parse(values[4], DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                claim.setDocuments(List.of(values[5].split(";")));
                claim.setClaimAmount(Double.parseDouble(values[6]));
                claim.setStatus(values[7]);
                BankingInfo bankingInfo = new BankingInfo();
                if (values.length > 8) {
                    String[] bankingInfoValues = values[8].split("-");
                    if (bankingInfoValues.length >= 3) {
                        bankingInfo.setBank(bankingInfoValues[0]);
                        bankingInfo.setName(bankingInfoValues[1]);
                        bankingInfo.setNumber(bankingInfoValues[2]);
                        claim.setReceiverBankInfo(bankingInfo);
                    } else {
                        System.err.println("Unable to load banking info: " + String.join(",", values));
                    }
                } else {
                    System.err.println("Unable to load banking info: " + String.join(",", values));
                }
                claims.add(claim);
            }
        } catch (IOException e) {
            System.err.println("Error loading claims: " + e.getMessage());
        }
    }
}