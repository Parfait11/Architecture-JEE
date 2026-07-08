package enset.dimba.ebanking_backend.dtos;

import lombok.Generated;

public class DebitDTO {
    private String accountId;
    private double amount;
    private String description;

    @Generated
    public DebitDTO() {
    }

    @Generated
    public String getAccountId() {
        return this.accountId;
    }

    @Generated
    public double getAmount() {
        return this.amount;
    }

    @Generated
    public String getDescription() {
        return this.description;
    }

    @Generated
    public void setAccountId(final String accountId) {
        this.accountId = accountId;
    }

    @Generated
    public void setAmount(final double amount) {
        this.amount = amount;
    }

    @Generated
    public void setDescription(final String description) {
        this.description = description;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof DebitDTO)) {
            return false;
        } else {
            DebitDTO other = (DebitDTO)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (Double.compare(this.getAmount(), other.getAmount()) != 0) {
                return false;
            } else {
                Object this$accountId = this.getAccountId();
                Object other$accountId = other.getAccountId();
                if (this$accountId == null) {
                    if (other$accountId != null) {
                        return false;
                    }
                } else if (!this$accountId.equals(other$accountId)) {
                    return false;
                }

                Object this$description = this.getDescription();
                Object other$description = other.getDescription();
                if (this$description == null) {
                    if (other$description != null) {
                        return false;
                    }
                } else if (!this$description.equals(other$description)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Generated
    protected boolean canEqual(final Object other) {
        return other instanceof DebitDTO;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        long $amount = Double.doubleToLongBits(this.getAmount());
        result = result * 59 + (int)($amount >>> 32 ^ $amount);
        Object $accountId = this.getAccountId();
        result = result * 59 + ($accountId == null ? 43 : $accountId.hashCode());
        Object $description = this.getDescription();
        result = result * 59 + ($description == null ? 43 : $description.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        String var10000 = this.getAccountId();
        return "DebitDTO(accountId=" + var10000 + ", amount=" + this.getAmount() + ", description=" + this.getDescription() + ")";
    }
}
