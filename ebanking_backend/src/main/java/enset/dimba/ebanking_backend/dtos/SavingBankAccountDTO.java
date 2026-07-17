package enset.dimba.ebanking_backend.dtos;

import enset.dimba.ebanking_backend.enums.AccountStatus;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;

@Getter
@Setter
public class SavingBankAccountDTO extends BankAccountDTO {
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    private CustomerDTO customerDTO;
    private double interestRate;
}