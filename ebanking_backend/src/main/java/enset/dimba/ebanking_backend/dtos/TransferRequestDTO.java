package enset.dimba.ebanking_backend.dtos;

import lombok.Generated;

public class TransferRequestDTO {
    private String accountSource;
    private String accountDestination;
    private double amount;
    private String description;

    @Generated
    public TransferRequestDTO() {
    }

    @Generated
    public String getAccountSource() {
        return this.accountSource;
    }

    @Generated
    public String getAccountDestination() {
        return this.accountDestination;
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
    public void setAccountSource(final String accountSource) {
        this.accountSource = accountSource;
    }

    @Generated
    public void setAccountDestination(final String accountDestination) {
        this.accountDestination = accountDestination;
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
        } else if (!(o instanceof TransferRequestDTO)) {
            return false;
        } else {
            TransferRequestDTO other = (TransferRequestDTO)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (Double.compare(this.getAmount(), other.getAmount()) != 0) {
                return false;
            } else {
                Object this$accountSource = this.getAccountSource();
                Object other$accountSource = other.getAccountSource();
                if (this$accountSource == null) {
                    if (other$accountSource != null) {
                        return false;
                    }
                } else if (!this$accountSource.equals(other$accountSource)) {
                    return false;
                }

                Object this$accountDestination = this.getAccountDestination();
                Object other$accountDestination = other.getAccountDestination();
                if (this$accountDestination == null) {
                    if (other$accountDestination != null) {
                        return false;
                    }
                } else if (!this$accountDestination.equals(other$accountDestination)) {
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
        return other instanceof TransferRequestDTO;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        long $amount = Double.doubleToLongBits(this.getAmount());
        result = result * 59 + (int)($amount >>> 32 ^ $amount);
        Object $accountSource = this.getAccountSource();
        result = result * 59 + ($accountSource == null ? 43 : $accountSource.hashCode());
        Object $accountDestination = this.getAccountDestination();
        result = result * 59 + ($accountDestination == null ? 43 : $accountDestination.hashCode());
        Object $description = this.getDescription();
        result = result * 59 + ($description == null ? 43 : $description.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        String var10000 = this.getAccountSource();
        return "TransferRequestDTO(accountSource=" + var10000 + ", accountDestination=" + this.getAccountDestination() + ", amount=" + this.getAmount() + ", description=" + this.getDescription() + ")";
    }
}
