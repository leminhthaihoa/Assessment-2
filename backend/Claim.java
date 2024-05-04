/**
 * @author <LE MINH THAI HOA - S3979194>
 */

package backend;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Claim {
    private String id;
    private LocalDate claimDate;
    private String insuredPerson;
    private String cardNumber;
    private LocalDate examDate;
    private List<String> documents;
    private Double claimAmount;
    private String status;
    private BankingInfo receiverBankInfo;

    public Claim() {
        this.id = "";
        this.claimDate = null;
        this.insuredPerson = null;
        this.cardNumber = "";
        this.examDate = null;
        this.documents = new ArrayList<>();
        this.claimAmount = 0.0;
        this.status = "";
        this.receiverBankInfo = null;
    }

    public Claim(String insuredPerson, String cardNumber){
        this.id = "";
        this.claimDate = null;
        this.insuredPerson = insuredPerson;
        this.cardNumber = cardNumber;
        this.examDate = null;
        this.documents = new ArrayList<>();
        this.claimAmount = 0.0;
        this.status = "";
        this.receiverBankInfo = null;
    }

    public Claim(String id, LocalDate claimDate, String insuredPerson, String cardNumber, LocalDate examDate, List<String> documents, Double claimAmount, String status, BankingInfo receiverBankInfo) {
        this.id = id;
        this.claimDate = claimDate;
        this.insuredPerson = insuredPerson;
        this.cardNumber = cardNumber;
        this.examDate = examDate;
        this.documents = documents;
        this.claimAmount = claimAmount;
        this.status = status;
        this.receiverBankInfo = receiverBankInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(LocalDate claimDate) {
        this.claimDate = claimDate;
    }

    public String getInsuredPerson() {
        return insuredPerson;
    }

    public void setInsuredPerson(String insuredPerson) {
        this.insuredPerson = insuredPerson;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    public List<String> getDocuments() {
        return documents;
    }

    public void setDocuments(List<String> documents) {
        this.documents = documents;
    }

    public Double getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(Double claimAmount) {
        this.claimAmount = claimAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BankingInfo getReceiverBankInfo() {
        return receiverBankInfo;
    }

    public void setReceiverBankInfo(BankingInfo receiverBankInfo) {
        this.receiverBankInfo = receiverBankInfo;
    }

    @Override
    public String toString() {
        return "Claim{" +
                "id='" + id + '\'' +
                ", claimDate=" + claimDate +
                ", insuredPerson=" + insuredPerson +
                ", cardNumber='" + cardNumber + '\'' +
                ", examDate=" + examDate +
                ", documents=" + documents +
                ", claimAmount=" + claimAmount +
                ", status='" + status + '\'' +
                ", receiverBankInfo=" + receiverBankInfo +
                '}';
    }
}