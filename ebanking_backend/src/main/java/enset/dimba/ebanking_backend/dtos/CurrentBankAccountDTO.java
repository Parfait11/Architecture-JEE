package enset.dimba.ebanking_backend.dtos;

import enset.dimba.ebanking_backend.enums.AccountStatus;
import java.util.Date;
import lombok.Generated;

public class CurrentBankAccountDTO extends BankAccountDTO {
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    private CustomerDTO customerDTO;
    private double overDraft;

    @Generated
    public CurrentBankAccountDTO() {
    }

    @Generated
    public String getId() {
        return this.id;
    }

    @Generated
    public double getBalance() {
        return this.balance;
    }

    @Generated
    public Date getCreatedAt() {
        return this.createdAt;
    }

    @Generated
    public AccountStatus getStatus() {
        return this.status;
    }

    @Generated
    public CustomerDTO getCustomerDTO() {
        return this.customerDTO;
    }

    @Generated
    public double getOverDraft() {
        return this.overDraft;
    }

    @Generated
    public void setId(final String id) {
        this.id = id;
    }

    @Generated
    public void setBalance(final double balance) {
        this.balance = balance;
    }

    @Generated
    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    @Generated
    public void setStatus(final AccountStatus status) {
        this.status = status;
    }

    @Generated
    public void setCustomerDTO(final CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    @Generated
    public void setOverDraft(final double overDraft) {
        this.overDraft = overDraft;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof CurrentBankAccountDTO)) {
            return false;
        } else {
            CurrentBankAccountDTO other = (CurrentBankAccountDTO)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (Double.compare(this.getBalance(), other.getBalance()) != 0) {
                return false;
            } else if (Double.compare(this.getOverDraft(), other.getOverDraft()) != 0) {
                return false;
            } else {
                Object this$id = this.getId();
                Object other$id = other.getId();
                if (this$id == null) {
                    if (other$id != null) {
                        return false;
                    }
                } else if (!this$id.equals(other$id)) {
                    return false;
                }

                Object this$createdAt = this.getCreatedAt();
                Object other$createdAt = other.getCreatedAt();
                if (this$createdAt == null) {
                    if (other$createdAt != null) {
                        return false;
                    }
                } else if (!this$createdAt.equals(other$createdAt)) {
                    return false;
                }

                Object this$status = this.getStatus();
                Object other$status = other.getStatus();
                if (this$status == null) {
                    if (other$status != null) {
                        return false;
                    }
                } else if (!this$status.equals(other$status)) {
                    return false;
                }

                Object this$customerDTO = this.getCustomerDTO();
                Object other$customerDTO = other.getCustomerDTO();
                if (this$customerDTO == null) {
                    if (other$customerDTO != null) {
                        return false;
                    }
                } else if (!this$customerDTO.equals(other$customerDTO)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Generated
    protected boolean canEqual(final Object other) {
        return other instanceof CurrentBankAccountDTO;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        long $balance = Double.doubleToLongBits(this.getBalance());
        result = result * 59 + (int)($balance >>> 32 ^ $balance);
        long $overDraft = Double.doubleToLongBits(this.getOverDraft());
        result = result * 59 + (int)($overDraft >>> 32 ^ $overDraft);
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $createdAt = this.getCreatedAt();
        result = result * 59 + ($createdAt == null ? 43 : $createdAt.hashCode());
        Object $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        Object $customerDTO = this.getCustomerDTO();
        result = result * 59 + ($customerDTO == null ? 43 : $customerDTO.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        String var10000 = this.getId();
        return "CurrentBankAccountDTO(id=" + var10000 + ", balance=" + this.getBalance() + ", createdAt=" + String.valueOf(this.getCreatedAt()) + ", status=" + String.valueOf(this.getStatus()) + ", customerDTO=" + String.valueOf(this.getCustomerDTO()) + ", overDraft=" + this.getOverDraft() + ")";
    }
}
