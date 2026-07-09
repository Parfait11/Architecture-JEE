package enset.dimba.ebanking_backend.dtos;

import enset.dimba.ebanking_backend.enums.OperationType;
import java.util.Date;
import lombok.Generated;

public class AccountOperationDTO {
    private Long id;
    private Date operationDate;
    private double amount;
    private OperationType type;
    private String description;

    @Generated
    public AccountOperationDTO() {
    }

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public Date getOperationDate() {
        return this.operationDate;
    }

    @Generated
    public double getAmount() {
        return this.amount;
    }

    @Generated
    public OperationType getType() {
        return this.type;
    }

    @Generated
    public String getDescription() {
        return this.description;
    }

    @Generated
    public void setId(final Long id) {
        this.id = id;
    }

    @Generated
    public void setOperationDate(final Date operationDate) {
        this.operationDate = operationDate;
    }

    @Generated
    public void setAmount(final double amount) {
        this.amount = amount;
    }

    @Generated
    public void setType(final OperationType type) {
        this.type = type;
    }

    @Generated
    public void setDescription(final String description) {
        this.description = description;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof AccountOperationDTO)) {
            return false;
        } else {
            AccountOperationDTO other = (AccountOperationDTO)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (Double.compare(this.getAmount(), other.getAmount()) != 0) {
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

                Object this$operationDate = this.getOperationDate();
                Object other$operationDate = other.getOperationDate();
                if (this$operationDate == null) {
                    if (other$operationDate != null) {
                        return false;
                    }
                } else if (!this$operationDate.equals(other$operationDate)) {
                    return false;
                }

                Object this$type = this.getType();
                Object other$type = other.getType();
                if (this$type == null) {
                    if (other$type != null) {
                        return false;
                    }
                } else if (!this$type.equals(other$type)) {
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
        return other instanceof AccountOperationDTO;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        long $amount = Double.doubleToLongBits(this.getAmount());
        result = result * 59 + (int)($amount >>> 32 ^ $amount);
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $operationDate = this.getOperationDate();
        result = result * 59 + ($operationDate == null ? 43 : $operationDate.hashCode());
        Object $type = this.getType();
        result = result * 59 + ($type == null ? 43 : $type.hashCode());
        Object $description = this.getDescription();
        result = result * 59 + ($description == null ? 43 : $description.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        Long var10000 = this.getId();
        return "AccountOperationDTO(id=" + var10000 + ", operationDate=" + String.valueOf(this.getOperationDate()) + ", amount=" + this.getAmount() + ", type=" + String.valueOf(this.getType()) + ", description=" + this.getDescription() + ")";
    }
}