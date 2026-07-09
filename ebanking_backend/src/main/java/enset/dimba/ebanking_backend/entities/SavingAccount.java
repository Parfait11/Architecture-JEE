package enset.dimba.ebanking_backend.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Generated;

@Entity
@DiscriminatorValue("SA")
public class SavingAccount extends BankAccount {
    private double interestRate;

    @Generated
    public double getInterestRate() {
        return this.interestRate;
    }

    @Generated
    public void setInterestRate(final double interestRate) {
        this.interestRate = interestRate;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof SavingAccount)) {
            return false;
        } else {
            SavingAccount other = (SavingAccount)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                return Double.compare(this.getInterestRate(), other.getInterestRate()) == 0;
            }
        }
    }

    @Generated
    protected boolean canEqual(final Object other) {
        return other instanceof SavingAccount;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        long $interestRate = Double.doubleToLongBits(this.getInterestRate());
        result = result * 59 + (int)($interestRate >>> 32 ^ $interestRate);
        return result;
    }

    @Generated
    public String toString() {
        return "SavingAccount(interestRate=" + this.getInterestRate() + ")";
    }

    @Generated
    public SavingAccount() {
    }

    @Generated
    public SavingAccount(final double interestRate) {
        this.interestRate = interestRate;
    }
}
