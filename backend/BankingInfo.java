/**
 * @author <LE MINH THAI HOA - S3979194>
 */

package backend;

public class BankingInfo {
    private String bank;
    private String name;
    private String number;

    public BankingInfo() {
        this.bank = "";
        this.name = "";
        this.number = "";
    }

    public BankingInfo(String bank, String name, String number) {
        this.bank = bank;
        this.name = name;
        this.number = number;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "BankingInfo{" +
                "bank='" + bank + '\'' +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}