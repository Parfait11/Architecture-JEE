package enset.dimba.ebanking_backend.services;

import enset.dimba.ebanking_backend.dtos.AccountHistoryDTO;
import enset.dimba.ebanking_backend.dtos.AccountOperationDTO;
import enset.dimba.ebanking_backend.dtos.BankAccountDTO;
import enset.dimba.ebanking_backend.dtos.CurrentBankAccountDTO;
import enset.dimba.ebanking_backend.dtos.CustomerDTO;
import enset.dimba.ebanking_backend.dtos.SavingBankAccountDTO;
import enset.dimba.ebanking_backend.entities.AccountOperation;
import enset.dimba.ebanking_backend.entities.BankAccount;
import enset.dimba.ebanking_backend.entities.CurrentAccount;
import enset.dimba.ebanking_backend.entities.Customer;
import enset.dimba.ebanking_backend.entities.SavingAccount;
import enset.dimba.ebanking_backend.enums.OperationType;
import enset.dimba.ebanking_backend.exceptions.BalanceNotSufficientException;
import enset.dimba.ebanking_backend.exceptions.BankAccountNotFoundException;
import enset.dimba.ebanking_backend.exceptions.CustomerNotFoundException;
import enset.dimba.ebanking_backend.mappers.BankAccountMapperImpl;
import enset.dimba.ebanking_backend.repositories.AccountOperationRepository;
import enset.dimba.ebanking_backend.repositories.BankAccountRepository;
import enset.dimba.ebanking_backend.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class BankAccountServiceImpl implements BankAccountService {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(BankAccountServiceImpl.class);
    private CustomerRepository customerRepository;
    private BankAccountRepository bankAccountRepository;
    private AccountOperationRepository accountOperationRepository;
    private BankAccountMapperImpl dtoMapper;

    // Constructeur
    public BankAccountServiceImpl(CustomerRepository customerRepository,
                                  BankAccountRepository bankAccountRepository,
                                  AccountOperationRepository accountOperationRepository,
                                  BankAccountMapperImpl dtoMapper) {
        this.customerRepository = customerRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.accountOperationRepository = accountOperationRepository;
        this.dtoMapper = dtoMapper;
    }

    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        log.info("Saving new Customer");
        Customer customer = this.dtoMapper.fromCustomerDTO(customerDTO);
        Customer savedCustomer = this.customerRepository.save(customer);  // Correction : plus de cast
        return this.dtoMapper.fromCustomer(savedCustomer);
    }

    public CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException {
        Customer customer = this.customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));  // Correction
        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setId(UUID.randomUUID().toString());
        currentAccount.setCreatedAt(new Date());
        currentAccount.setBalance(initialBalance);
        currentAccount.setOverDraft(overDraft);
        currentAccount.setCustomer(customer);
        CurrentAccount savedBankAccount = this.bankAccountRepository.save(currentAccount);
        return this.dtoMapper.fromCurrentBankAccount(savedBankAccount);
    }

    public SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException {
        Customer customer = this.customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));  // Correction
        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setId(UUID.randomUUID().toString());
        savingAccount.setCreatedAt(new Date());
        savingAccount.setBalance(initialBalance);
        savingAccount.setInterestRate(interestRate);
        savingAccount.setCustomer(customer);
        SavingAccount savedBankAccount = this.bankAccountRepository.save(savingAccount);
        return this.dtoMapper.fromSavingBankAccount(savedBankAccount);
    }

    public List<CustomerDTO> listCustomers() {
        List<Customer> customers = this.customerRepository.findAll();
        return customers.stream()
                .map(this.dtoMapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException {
        BankAccount bankAccount = this.bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new BankAccountNotFoundException("BankAccount not found"));  // Correction
        if (bankAccount instanceof SavingAccount savingAccount) {
            return this.dtoMapper.fromSavingBankAccount(savingAccount);
        } else {
            CurrentAccount currentAccount = (CurrentAccount) bankAccount;
            return this.dtoMapper.fromCurrentBankAccount(currentAccount);
        }
    }

    public void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficientException {
        BankAccount bankAccount = this.bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new BankAccountNotFoundException("BankAccount not found"));  // Correction
        if (bankAccount.getBalance() < amount) {
            throw new BalanceNotSufficientException("Balance not sufficient");
        }
        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setType(OperationType.DEBIT);
        accountOperation.setAmount(amount);
        accountOperation.setDescription(description);
        accountOperation.setOperationDate(new Date());
        accountOperation.setBankAccount(bankAccount);
        this.accountOperationRepository.save(accountOperation);
        bankAccount.setBalance(bankAccount.getBalance() - amount);
        this.bankAccountRepository.save(bankAccount);
    }

    public void credit(String accountId, double amount, String description) throws BankAccountNotFoundException {
        BankAccount bankAccount = this.bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new BankAccountNotFoundException("BankAccount not found"));  // Correction
        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setType(OperationType.CREDIT);
        accountOperation.setAmount(amount);
        accountOperation.setDescription(description);
        accountOperation.setOperationDate(new Date());
        accountOperation.setBankAccount(bankAccount);
        this.accountOperationRepository.save(accountOperation);
        bankAccount.setBalance(bankAccount.getBalance() + amount);
        this.bankAccountRepository.save(bankAccount);
    }

    public void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficientException {
        this.debit(accountIdSource, amount, "Transfer to " + accountIdDestination);
        this.credit(accountIdDestination, amount, "Transfer from " + accountIdSource);
    }

    public List<BankAccountDTO> bankAccountList() {
        List<BankAccount> bankAccounts = this.bankAccountRepository.findAll();
        return bankAccounts.stream().map(bankAccount -> {
            if (bankAccount instanceof SavingAccount savingAccount) {
                return this.dtoMapper.fromSavingBankAccount(savingAccount);
            } else {
                CurrentAccount currentAccount = (CurrentAccount) bankAccount;
                return this.dtoMapper.fromCurrentBankAccount(currentAccount);
            }
        }).collect(Collectors.toList());
    }

    public CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException {
        Customer customer = this.customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not found"));  // Correction
        return this.dtoMapper.fromCustomer(customer);
    }

    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        log.info("Updating Customer");
        Customer customer = this.dtoMapper.fromCustomerDTO(customerDTO);
        Customer savedCustomer = this.customerRepository.save(customer);
        return this.dtoMapper.fromCustomer(savedCustomer);
    }

    public void deleteCustomer(Long customerId) {
        this.customerRepository.deleteById(customerId);
    }

    public List<AccountOperationDTO> accountHistory(String accountId) {
        List<AccountOperation> accountOperations = this.accountOperationRepository.findByBankAccountId(accountId);
        return accountOperations.stream()
                .map(this.dtoMapper::fromAccountOperation)
                .collect(Collectors.toList());
    }

    public AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BankAccountNotFoundException {
        BankAccount bankAccount = this.bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new BankAccountNotFoundException("Account not Found"));  // Correction
        Page<AccountOperation> accountOperations = this.accountOperationRepository
                .findByBankAccountIdOrderByOperationDateDesc(accountId, PageRequest.of(page, size));
        AccountHistoryDTO accountHistoryDTO = new AccountHistoryDTO();
        List<AccountOperationDTO> accountOperationDTOS = accountOperations.getContent()
                .stream()
                .map(this.dtoMapper::fromAccountOperation)
                .collect(Collectors.toList());
        accountHistoryDTO.setAccountOperationDTOS(accountOperationDTOS);
        accountHistoryDTO.setAccountId(bankAccount.getId());
        accountHistoryDTO.setBalance(bankAccount.getBalance());
        accountHistoryDTO.setCurrentPage(page);
        accountHistoryDTO.setPageSize(size);
        accountHistoryDTO.setTotalPages(accountOperations.getTotalPages());
        return accountHistoryDTO;
    }

    public List<CustomerDTO> searchCustomers(String keyword) {
        List<Customer> customers = this.customerRepository.searchCustomer(keyword);
        return customers.stream()
                .map(this.dtoMapper::fromCustomer)
                .collect(Collectors.toList());
    }
}