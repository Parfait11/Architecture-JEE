package enset.dimba.ebanking_backend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CustomerDTO {
    private Long id;
    private String name;
    private String email;
}
