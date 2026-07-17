package enset.dimba.ebanking_backend.dtos;
import lombok.Getter;
import lombok.Setter;
import enset.dimba.ebanking_backend.enums.AccountStatus;

import java.util.Date;

@Getter @Setter
public class CurrentBankAccountDTO extends BankAccountDTO {
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    private CustomerDTO customerDTO;
    private double overDraft;
}