package enset.dimba.ebanking_backend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Generated;

@Entity
public class Customer {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String name;
    private String email;
    @OneToMany(
            mappedBy = "customer"
    )
    @JsonProperty(
            access = Access.WRITE_ONLY
    )
    private List<BankAccount> bankAccounts;

    @Generated
    public Long getId() {
        return this.id;
    }

    @Generated
    public String getName() {
        return this.name;
    }

    @Generated
    public String getEmail() {
        return this.email;
    }

    @Generated
    public List<BankAccount> getBankAccounts() {
        return this.bankAccounts;
    }

    @Generated
    public void setId(final Long id) {
        this.id = id;
    }

    @Generated
    public void setName(final String name) {
        this.name = name;
    }

    @Generated
    public void setEmail(final String email) {
        this.email = email;
    }

    @Generated
    public void setBankAccounts(final List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    @Generated
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Customer)) {
            return false;
        } else {
            Customer other = (Customer)o;
            if (!other.canEqual(this)) {
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

                Object this$name = this.getName();
                Object other$name = other.getName();
                if (this$name == null) {
                    if (other$name != null) {
                        return false;
                    }
                } else if (!this$name.equals(other$name)) {
                    return false;
                }

                Object this$email = this.getEmail();
                Object other$email = other.getEmail();
                if (this$email == null) {
                    if (other$email != null) {
                        return false;
                    }
                } else if (!this$email.equals(other$email)) {
                    return false;
                }

                Object this$bankAccounts = this.getBankAccounts();
                Object other$bankAccounts = other.getBankAccounts();
                if (this$bankAccounts == null) {
                    if (other$bankAccounts != null) {
                        return false;
                    }
                } else if (!this$bankAccounts.equals(other$bankAccounts)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Generated
    protected boolean canEqual(final Object other) {
        return other instanceof Customer;
    }

    @Generated
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        Object $email = this.getEmail();
        result = result * 59 + ($email == null ? 43 : $email.hashCode());
        Object $bankAccounts = this.getBankAccounts();
        result = result * 59 + ($bankAccounts == null ? 43 : $bankAccounts.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        Long var10000 = this.getId();
        return "Customer(id=" + var10000 + ", name=" + this.getName() + ", email=" + this.getEmail() + ", bankAccounts=" + String.valueOf(this.getBankAccounts()) + ")";
    }

    @Generated
    public Customer() {
    }

    @Generated
    public Customer(final Long id, final String name, final String email, final List<BankAccount> bankAccounts) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.bankAccounts = bankAccounts;
    }
}
