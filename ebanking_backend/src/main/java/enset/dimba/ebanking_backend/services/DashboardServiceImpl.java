package enset.dimba.ebanking_backend.services;

import enset.dimba.ebanking_backend.dtos.CustomerBalanceDTO;
import enset.dimba.ebanking_backend.dtos.DashboardStatsDTO;
import enset.dimba.ebanking_backend.dtos.MonthlyOperationDTO;
import enset.dimba.ebanking_backend.entities.AccountOperation;
import enset.dimba.ebanking_backend.entities.BankAccount;
import enset.dimba.ebanking_backend.entities.CurrentAccount;
import enset.dimba.ebanking_backend.entities.SavingAccount;
import enset.dimba.ebanking_backend.enums.OperationType;
import enset.dimba.ebanking_backend.repositories.AccountOperationRepository;
import enset.dimba.ebanking_backend.repositories.BankAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final BankAccountRepository bankAccountRepository;
    private final AccountOperationRepository accountOperationRepository;

    @Override
    public DashboardStatsDTO getDashboardStats() {
        List<BankAccount> accounts = bankAccountRepository.findAll();
        List<AccountOperation> operations = accountOperationRepository.findAll();

        SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy-MM");
        Map<String, double[]> monthlyMap = new TreeMap<>();

        for (AccountOperation op : operations) {
            String month = monthFormat.format(op.getOperationDate());
            monthlyMap.putIfAbsent(month, new double[2]);
            if (op.getType() == OperationType.DEBIT) {
                monthlyMap.get(month)[0] += op.getAmount();
            } else if (op.getType() == OperationType.CREDIT) {
                monthlyMap.get(month)[1] += op.getAmount();
            }
        }

        List<MonthlyOperationDTO> monthlyOperations = monthlyMap.entrySet().stream()
                .map(e -> new MonthlyOperationDTO(e.getKey(), e.getValue()[0], e.getValue()[1]))
                .collect(Collectors.toList());

        Map<String, Long> accountTypeDistribution = accounts.stream()
                .collect(Collectors.groupingBy(
                        acc -> (acc instanceof SavingAccount) ? "SAVE" : "CURR",
                        Collectors.counting()
                ));

        List<Double> recentTransactionVolume = operations.stream()
                .sorted(Comparator.comparing(AccountOperation::getOperationDate).reversed())
                .limit(10)
                .map(AccountOperation::getAmount)
                .collect(Collectors.toList());
        Collections.reverse(recentTransactionVolume);

        Map<String, Double> balanceByCustomer = new LinkedHashMap<>();
        for (BankAccount acc : accounts) {
            if (acc.getCustomer() == null) continue;
            String name = acc.getCustomer().getName();
            balanceByCustomer.merge(name, acc.getBalance(), Double::sum);
        }

        List<CustomerBalanceDTO> balancePerCustomer = balanceByCustomer.entrySet().stream()
                .map(e -> new CustomerBalanceDTO(e.getKey(), e.getValue()))
                .sorted((a, b) -> Double.compare(b.totalBalance(), a.totalBalance()))
                .limit(10)
                .collect(Collectors.toList());

        return new DashboardStatsDTO(
                monthlyOperations,
                accountTypeDistribution,
                recentTransactionVolume,
                balancePerCustomer
        );
    }
}