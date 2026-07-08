package enset.dimba.ebanking_backend.dtos;

import enset.dimba.ebanking_backend.enums.AccountStatus;
import java.util.Date;

public class SavingBankAccountDTO extends BankAccountDTO {
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    private CustomerDTO customerDTO;
    private double interestRate;

    public SavingBankAccountDTO() {
    }

    public CustomerDTO getCustomerDTO() {
        return this.customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }
}
