package enset.dimba.ebanking_backend.dtos;

import lombok.Generated;

public class BankAccountDTO {
    private String type;

    @Generated
    public BankAccountDTO() {
    }

    @Generated
    public String getType() {
        return this.type;
    }

    @Generated
    public void setType(final String type) {
        this.type = type;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof BankAccountDTO)) {
            return false;
        } else {
            BankAccountDTO other = (BankAccountDTO)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$type = this.getType();
                Object other$type = other.getType();
                if (this$type == null) {
                    if (other$type != null) {
                        return false;
                    }
                } else if (!this$type.equals(other$type)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Generated
    protected boolean canEqual(final Object other) {
        return other instanceof BankAccountDTO;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $type = this.getType();
        result = result * 59 + ($type == null ? 43 : $type.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        return "BankAccountDTO(type=" + this.getType() + ")";
    }
}
