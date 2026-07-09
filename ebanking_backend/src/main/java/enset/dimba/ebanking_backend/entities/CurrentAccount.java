package enset.dimba.ebanking_backend.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Generated;

@Entity
@DiscriminatorValue("CA")
public class CurrentAccount extends BankAccount {
    private double overDraft;

    @Generated
    public double getOverDraft() {
        return this.overDraft;
    }

    @Generated
    public void setOverDraft(final double overDraft) {
        this.overDraft = overDraft;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof CurrentAccount)) {
            return false;
        } else {
            CurrentAccount other = (CurrentAccount)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                return Double.compare(this.getOverDraft(), other.getOverDraft()) == 0;
            }
        }
    }

    @Generated
    protected boolean canEqual(final Object other) {
        return other instanceof CurrentAccount;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        long $overDraft = Double.doubleToLongBits(this.getOverDraft());
        result = result * 59 + (int)($overDraft >>> 32 ^ $overDraft);
        return result;
    }

    @Generated
    public String toString() {
        return "CurrentAccount(overDraft=" + this.getOverDraft() + ")";
    }

    @Generated
    public CurrentAccount() {
    }

    @Generated
    public CurrentAccount(final double overDraft) {
        this.overDraft = overDraft;
    }
}
