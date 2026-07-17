package enset.dimba.ebanking_backend.dtos;
import lombok.Getter;
import lombok.Setter;
import enset.dimba.ebanking_backend.enums.OperationType;

import java.util.Date;

@Getter @Setter
public class AccountOperationDTO {
    private Long id;
    private Date operationDate;
    private double amount;
    private OperationType type;
    private String description;
}