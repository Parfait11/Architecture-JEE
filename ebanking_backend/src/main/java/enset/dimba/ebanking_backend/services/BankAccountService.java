package enset.dimba.ebanking_backend.services;



import enset.dimba.ebanking_backend.dtos.*;
import enset.dimba.ebanking_backend.exceptions.BalanceNotSufficientException;
import enset.dimba.ebanking_backend.exceptions.BankAccountNotFoundException;
import enset.dimba.ebanking_backend.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> searchCustomers(String keyword);

    CurrentBankAccountDTO saveCurrentBankAccount(
            double initialBalance,
            double overDraft,
            Long customerId
    ) throws CustomerNotFoundException;

    SavingBankAccountDTO saveSavingBankAccount(
            double initialBalance,
            double interestRate,
            Long customerId
    ) throws CustomerNotFoundException;

    List<CustomerDTO> listCustomers();

    BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;

    void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException;

    void credit(String accountId, double amount, String description) throws BankAccountNotFoundException;

    void transfer(
            String accountIdSource,
            String accountIdDestination,
            double amount
    ) throws BankAccountNotFoundException, BalanceNotSufficientException;

    List<BankAccountDTO> bankAccountList();

    List<AccountOperationDTO> accountHistory(String accountId);

    List<BankAccountDTO> getBankAccountsByCustomerId(Long customerId);

    CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long customerId);
}