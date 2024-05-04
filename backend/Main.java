/**
 * @author <LE MINH THAI HOA - S3979194>
 *     Reference: https://www.geeksforgeeks.org/stream-in-java/
 *     https://www.geeksforgeeks.org/generating-random-numbers-in-java/
 *     https://www.w3schools.com/java/java_date.asp
 */

package backend;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
public class Main {
    public static void main(String[] args) throws IOException {
        int Cont = 0;
        do {
            ClaimProcessManagerImpl claimProcessManager = new ClaimProcessManagerImpl();
            Scanner scanner = new Scanner(System.in);

            claimProcessManager.loadCustomers("backend/Customer.txt");
            claimProcessManager.loadClaims("backend/Claim.txt", "backend/Customer.txt", "backend/InsuranceCard.txt");
            CustomerLoader customerLoader = new CustomerLoader();

            List<Claim> claims = claimProcessManager.getAll();

            List<Customer> customers = customerLoader.loadCustomers("backend/Customer.txt", "backend/InsuranceCard.txt");

            System.out.println("--INSURANCE CLAIM MANAGEMENT SYSTEM--");
            System.out.println("-------------------------------------");
            System.out.println("Hello Admin");
            System.out.println("What do you want to do today: ");
            System.out.println("--------------------------------------");
            System.out.println("1: Show the claims");
            System.out.println("2: Manage the claims");
            System.out.print("What's your choice: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    System.out.println("SHOW CLAIMS: ");
                    System.out.println("-------------------------------------");
                    System.out.println("1:Show one ");
                    System.out.println("2: Show all ");
                    System.out.print("Your choice: ");
                    String in2 = scanner.nextLine();
                    if (in2.equals("1")) {
                        System.out.println("Choose the claim ID you want to see: ");
                        for (int i = 0; i < claims.size(); i++) {
                            System.out.println((i + 1) + ")" + claims.get(i).getId());
                        }
                        System.out.print("Your choice: ");
                        int check = 0;


                        do {
                            int in3 = scanner.nextInt();
                            if ((in3 - 1) >= 0 && (in3 - 1) < claims.size()) {
                                System.out.println("ID: " + claims.get(in3 - 1).getId());
                                System.out.println("Insured person: " + claims.get(in3 - 1).getInsuredPerson());
                                System.out.println("Card ID: " + claims.get(in3 - 1).getCardNumber());
                                System.out.println("Claim date: " + claims.get(in3 - 1).getClaimDate());
                                System.out.println("Exam date: " + claims.get(in3 - 1).getExamDate());
                                System.out.println("Documents: " + claims.get(in3 - 1).getDocuments());
                                System.out.println("Claim amount: " + claims.get(in3 - 1).getClaimAmount());
                                System.out.println("Status: " + claims.get(in3 - 1).getStatus());
                                System.out.println("Banking info: ");
                                System.out.println("Bank: " + claims.get(in3 - 1).getReceiverBankInfo().getBank());
                                System.out.println("Name: " + claims.get(in3 - 1).getReceiverBankInfo().getName());
                                System.out.println("Number: " + claims.get(in3 - 1).getReceiverBankInfo().getNumber());
                                check = 1;
                            } else {
                                System.out.println("THE CHOICE IS NOT ON THE LIST !");
                                System.out.print("Your choice again: ");
                            }
                        } while (check == 0);
                    }
                    if (in2.equals("2")) {

                        for (Claim claim : claims) {
                            System.out.println(claim.getId() + " | " + claim.getInsuredPerson() + " | " + claim.getCardNumber() + " | " + claim.getClaimDate() + " | " + claim.getExamDate() + " | " + claim.getReceiverBankInfo() + " | " + claim.getDocuments());
                        }
                    }
                    break;

                case "2":
                    System.out.println("MANAGE CLAIMS: ");
                    System.out.println("-------------------------------------");
                    System.out.println("1: Make new claims");
                    System.out.println("2: Update claims");
                    System.out.println("3: Delete claims");
                    System.out.print("Your choice: ");

                    int in4 = scanner.nextInt();

                    if (in4 == 1) {
                        System.out.println("MAKING CLAIMS: ");
                        System.out.println("-------------------------------------");
                        Claim newClaim = new Claim();
                        Random random = new Random();
                        int ID;
                        String claimID;
                        int[] idFlag = {0};
                        do {
                            ID = 1000000 + random.nextInt(90000000);
                            claimID = "f-" + String.format("%08d", ID);
                            idFlag[0] = 0;
                            for (Claim claim : claims) {
                                if (claim.getId().equals(claimID)) {
                                    idFlag[0] = 1;
                                    break;
                                }
                            }
                        } while (idFlag[0] == 1);
                        newClaim.setId(claimID);

                        System.out.println("Choose insured person: ");
                        int a = 0;
                        for (int i = 0; i < customers.size(); i++) {
                                System.out.println((a + 1) + ") " + customers.get(i).getFullName());
                                a += 1;
                        }
                        System.out.print("Enter the number of the insured person you want: ");
                        String selectedCard = "";
                        String selectedCustomer = "";
                        int check = 0;
                        do {

                            int choice = scanner.nextInt();
                            if (choice >= 1 && choice <= a) {
                                selectedCustomer = customers.get(choice - 1).getFullName();
                                selectedCard = customers.get(choice - 1).getInsuranceCardNumber().getCardNumber();
                                check = 1;
                            } else {
                                System.out.println("Invalid selection.");
                                System.out.print("Enter again: ");
                            }

                        } while (check == 0);
                        newClaim.setInsuredPerson(selectedCustomer);
                        newClaim.setCardNumber(selectedCard);

                        scanner.nextLine();
                        System.out.print("Enter the document name: ");
                        String documentName = scanner.nextLine();

                        String document = claimID + "_" + selectedCard + "_" + documentName + ".pdf";
                        List<String> documents = new ArrayList<>();
                        documents.add(document);
                        newClaim.setDocuments(documents);

                        System.out.print("Choose the claim amount: ");

                        int checkAmount = 0;
                        do {
                            double amount = scanner.nextInt();

                            if (amount >= 0.0) {
                                newClaim.setClaimAmount(amount);
                                checkAmount = 1;
                            } else {
                                System.out.println("The amount can't be negative");
                                System.out.print("Please enter again: ");
                            }
                        } while (checkAmount == 0);

                        LocalDate claimDate = LocalDate.now();

                        newClaim.setClaimDate(claimDate);

                        newClaim.setExamDate(claimDate.plusDays(5));

                        newClaim.setStatus("New");

                        BankingInfo bankingInfo = new BankingInfo();

                        System.out.println("Create bank info: ");

                        scanner.nextLine();
                        System.out.print("Enter your bank name: ");
                        String bank = scanner.nextLine();
                        bankingInfo.setBank(bank);

                        System.out.print("Enter your bank number: ");
                        String number = scanner.nextLine();
                        bankingInfo.setNumber(number);

                        bankingInfo.setName(selectedCustomer);

                        newClaim.setReceiverBankInfo(bankingInfo);

                        claimProcessManager.add(newClaim);

                        claimProcessManager.saveClaims("backend/Claim.txt");

                        System.out.println("Add claim successfully");
                    }

                    if (in4 == 2) {
                        Claim updatedClaim = null;
                        System.out.println("This is the list of claim ID:  ");
                        for (int i = 0; i < claims.size(); i++) {
                            System.out.println((i + 1) + ") " + claims.get(i).getId());
                        }
                        System.out.print("Choose the number of the claim ID you want: ");
                        int chosenID = scanner.nextInt();

                        if ((chosenID - 1) >= 0 && (chosenID - 1) < claims.size()) {
                            updatedClaim = claims.get(chosenID - 1);
                        } else {
                            System.out.println("Invalid selection");
                        }

                        if (updatedClaim != null) {
                            System.out.println("What do you want to update: ");
                            System.out.println("1: Status");
                            System.out.println("2: Document");
                            System.out.print("Your choice: ");
                            int updateChoice = scanner.nextInt();

                            if (updateChoice == 1) {
                                System.out.println("Choose the status: ");
                                System.out.println("1: New");
                                System.out.println("2: Processing");
                                System.out.println("3: Done");
                                System.out.print("Your choice: ");

                                int checkStatus = 0;

                                do {
                                    int statusChoice = scanner.nextInt();

                                    if (statusChoice == 1) {
                                        String status1 = "New";
                                        if (updatedClaim.getStatus().equals(status1)) {
                                            System.out.println("This claim is already have New status");
                                            System.out.print("Please choose again: ");
                                        } else {
                                            updatedClaim.setStatus(status1);
                                            System.out.println("Update status completed");
                                            checkStatus = 1;
                                        }
                                    }
                                    if (statusChoice == 2) {
                                        String status2 = "Processing";
                                        if (updatedClaim.getStatus().equals(status2)) {
                                            System.out.println("This claim is already have Processing status");
                                            System.out.print("Please choose again: ");
                                        } else {
                                            updatedClaim.setStatus(status2);
                                            System.out.println("Update status completed");
                                            checkStatus = 1;
                                        }
                                    }

                                    if (statusChoice == 3) {
                                        String status3 = "Done";
                                        if (updatedClaim.getStatus().equals(status3)) {
                                            System.out.println("This claim is already have Done status");
                                            System.out.print("Please choose again: ");

                                        } else {
                                            updatedClaim.setStatus(status3);
                                            System.out.println("Update status completed");
                                            checkStatus = 1;
                                        }
                                    }

                                } while (checkStatus == 0);


                            }

                            if (updateChoice == 2) {
                                System.out.println("What do you want to do: ");
                                System.out.println("1: Add documents");
                                System.out.println("2: Remove documents");
                                System.out.print("Your choice: ");
                                int documentChoice = scanner.nextInt();

                                if (documentChoice == 1) {
                                    String newDocument;
                                    scanner.nextLine();
                                    System.out.print("Enter document name: ");
                                    String newDocName = scanner.nextLine();

                                    newDocument = updatedClaim.getId() + "_" + updatedClaim.getCardNumber() + "_" + newDocName + ".pdf";
                                    List<String> documents = new ArrayList<>(updatedClaim.getDocuments());
                                    documents.add(newDocument);
                                    updatedClaim.setDocuments(documents);
                                    System.out.println("Add Completed");
                                }
                                if (documentChoice == 2) {
                                    System.out.println("This is the available documents:");
                                    List<String> documents = new ArrayList<>(updatedClaim.getDocuments());
                                    for (int i = 0; i < documents.size(); i++) {
                                        System.out.println((i + 1) + ") " + documents.get(i));
                                    }
                                    System.out.print("What document do you want to remove: ");
                                    int delDocChoice = scanner.nextInt();

                                    if ((delDocChoice - 1) >= 0 && (delDocChoice - 1) < documents.size()) {
                                        String delDoc = documents.get(delDocChoice - 1);
                                        documents.remove(delDocChoice - 1);
                                        updatedClaim.setDocuments(documents);
                                        System.out.println("Delete complete");
                                    }
                                }

                            }
                            claimProcessManager.update(updatedClaim);
                            claimProcessManager.saveClaims("backend/Claim.txt");
                        }
                    }

                    if (in4 == 3) {
                        Claim delClaim = null;
                        System.out.println("This is the list of claim IDs: ");
                        for (int i = 0; i < claims.size(); i++) {
                            System.out.println((i + 1) + ") " + claims.get(i).getId());
                        }
                        System.out.print("Choose the claim ID correspond to claim you want to delete: ");
                        int delChoice = scanner.nextInt();

                        if ((delChoice - 1) >= 0 && (delChoice - 1) < claims.size()) {
                            delClaim = claims.get(delChoice - 1);
                        } else {
                            System.out.println("Invalid Selection");
                        }

                        if (delClaim != null) {
                            claimProcessManager.delete(delClaim);
                            claimProcessManager.saveClaims("backend/Claim.txt");
                            System.out.println("DELETE SUCCESSFULLY");
                        }

                    }
                    break;
            }

            System.out.print("DO YOU WANT TO CONTINUE MODIFYING CLAIMS ? (1: YES ; 0: NO) : ");
            int cont = scanner.nextInt();

            if (cont == 0){
                Cont = 1;
            }

        } while (Cont == 0);
    }
}