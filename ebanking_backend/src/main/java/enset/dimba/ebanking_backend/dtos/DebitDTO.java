package enset.dimba.ebanking_backend.dtos;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DebitDTO {
    private String accountId;
    private double amount;
    private String description;
}