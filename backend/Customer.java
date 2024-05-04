/**
 * @author <LE MINH THAI HOA - S3979194>
 *     Reference: https://www.w3schools.com/java/java_arraylist.asp
 *
 */

package backend;

import java.util.ArrayList;
import java.util.List;

public class Customer{
    private String ID;
    private String fullName;
    private Boolean isPolicyHolder;
    private InsuranceCard insuranceCardNumber;
    private List<Claim> claims;

    private List<String> dependents;

    public Customer(String ID, String fullName, boolean isPolicyHolder, InsuranceCard insuranceCardNumber, List<Claim> claims, List<String> dependents) {
        this.ID = ID;
        this.fullName = fullName;
        this.insuranceCardNumber = insuranceCardNumber;
        this.claims = claims;
        this.isPolicyHolder = isPolicyHolder;
        this.dependents = dependents;
    }

    public Customer(String ID, String fullName, Boolean isPolicyHolder, InsuranceCard insuranceCardNumber, List<String> dependents) {
        this.ID = ID;
        this.fullName = fullName;
        this.isPolicyHolder = isPolicyHolder;
        this.insuranceCardNumber = insuranceCardNumber;
        this.dependents = dependents;
    }

    public Customer() {
        this.ID = "";
        this.fullName = "";
        this.insuranceCardNumber = null;
        this.claims = new ArrayList<>();
        this.isPolicyHolder = null;
        this.dependents = new ArrayList<>();
    }

    public String getID() {
        return ID;
    }

    public String getFullName() {
        return fullName;
    }

    public Boolean getPolicyHolder() {
        return isPolicyHolder;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPolicyHolder(Boolean policyHolder) {
        isPolicyHolder = policyHolder;
    }

    public List<String> getDependents() {
        return dependents;
    }

    public void setDependents(List<String> dependents) {
        this.dependents = dependents;
    }

    public InsuranceCard getInsuranceCardNumber() {
        return insuranceCardNumber;
    }
    public List<Claim> getClaims() {
        return claims;
    }

    public void setClaims(List<Claim> claims) {
        this.claims = claims;
    }

    public void setInsuranceCardNumber(InsuranceCard insuranceCardNumber) {
        this.insuranceCardNumber = insuranceCardNumber;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "ID='" + ID + '\'' +
                ", fullName='" + fullName + '\'' +
                ", isPolicyHolder=" + isPolicyHolder +
                ", insuranceCardNumber=" + insuranceCardNumber +
                ", dependents=" + dependents +
                '}';
    }
}

