package enset.dimba.ebanking_backend.entities;

import enset.dimba.ebanking_backend.enums.AccountStatus;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.Date;
import java.util.List;
import lombok.Generated;

@Entity
@Inheritance(
        strategy = InheritanceType.SINGLE_TABLE
)
@DiscriminatorColumn(
        name = "TYPE",
        length = 4
)
public abstract class BankAccount {
    @Id
    private String id;
    private double balance;
    private Date createdAt;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    @ManyToOne
    private Customer customer;
    @OneToMany(
            mappedBy = "bankAccount",
            fetch = FetchType.LAZY
    )
    private List<AccountOperation> accountOperations;

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
    public Customer getCustomer() {
        return this.customer;
    }

    @Generated
    public List<AccountOperation> getAccountOperations() {
        return this.accountOperations;
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
    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }

    @Generated
    public void setAccountOperations(final List<AccountOperation> accountOperations) {
        this.accountOperations = accountOperations;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof BankAccount)) {
            return false;
        } else {
            BankAccount other = (BankAccount)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (Double.compare(this.getBalance(), other.getBalance()) != 0) {
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

                Object this$customer = this.getCustomer();
                Object other$customer = other.getCustomer();
                if (this$customer == null) {
                    if (other$customer != null) {
                        return false;
                    }
                } else if (!this$customer.equals(other$customer)) {
                    return false;
                }

                Object this$accountOperations = this.getAccountOperations();
                Object other$accountOperations = other.getAccountOperations();
                if (this$accountOperations == null) {
                    if (other$accountOperations != null) {
                        return false;
                    }
                } else if (!this$accountOperations.equals(other$accountOperations)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Generated
    protected boolean canEqual(final Object other) {
        return other instanceof BankAccount;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        long $balance = Double.doubleToLongBits(this.getBalance());
        result = result * 59 + (int)($balance >>> 32 ^ $balance);
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $createdAt = this.getCreatedAt();
        result = result * 59 + ($createdAt == null ? 43 : $createdAt.hashCode());
        Object $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        Object $customer = this.getCustomer();
        result = result * 59 + ($customer == null ? 43 : $customer.hashCode());
        Object $accountOperations = this.getAccountOperations();
        result = result * 59 + ($accountOperations == null ? 43 : $accountOperations.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        String var10000 = this.getId();
        return "BankAccount(id=" + var10000 + ", balance=" + this.getBalance() + ", createdAt=" + String.valueOf(this.getCreatedAt()) + ", status=" + String.valueOf(this.getStatus()) + ", customer=" + String.valueOf(this.getCustomer()) + ", accountOperations=" + String.valueOf(this.getAccountOperations()) + ")";
    }

    @Generated
    public BankAccount() {
    }

    @Generated
    public BankAccount(final String id, final double balance, final Date createdAt, final AccountStatus status, final Customer customer, final List<AccountOperation> accountOperations) {
        this.id = id;
        this.balance = balance;
        this.createdAt = createdAt;
        this.status = status;
        this.customer = customer;
        this.accountOperations = accountOperations;
    }
}
