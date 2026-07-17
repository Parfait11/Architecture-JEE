package enset.dimba.ebanking_backend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferRequestDTO {
    private String accountSource;
    private String accountDestination;
    private double amount;
}