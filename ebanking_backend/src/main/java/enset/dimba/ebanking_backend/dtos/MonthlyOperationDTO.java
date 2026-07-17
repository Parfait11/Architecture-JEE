package enset.dimba.ebanking_backend.dtos;

public record MonthlyOperationDTO(
        String month,
        Double debitSum,
        Double creditSum
) {
}
