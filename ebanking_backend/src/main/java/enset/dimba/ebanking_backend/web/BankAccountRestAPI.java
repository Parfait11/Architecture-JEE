package enset.dimba.ebanking_backend.web;

import enset.dimba.ebanking_backend.dtos.AccountHistoryDTO;
import enset.dimba.ebanking_backend.dtos.AccountOperationDTO;
import enset.dimba.ebanking_backend.dtos.BankAccountDTO;
import enset.dimba.ebanking_backend.dtos.CreditDTO;
import enset.dimba.ebanking_backend.dtos.DebitDTO;
import enset.dimba.ebanking_backend.dtos.TransferRequestDTO;
import enset.dimba.ebanking_backend.exceptions.BalanceNotSufficientException;
import enset.dimba.ebanking_backend.exceptions.BankAccountNotFoundException;
import enset.dimba.ebanking_backend.services.BankAccountService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public class BankAccountRestAPI {
    private BankAccountService bankAccountService;

    public BankAccountRestAPI(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping({"/accounts/{accountId}"})
    public BankAccountDTO getBankAccount(@PathVariable String accountId) throws BankAccountNotFoundException {
        return this.bankAccountService.getBankAccount(accountId);
    }

    @GetMapping({"/accounts"})
    public List<BankAccountDTO> listAccounts() {
        return this.bankAccountService.bankAccountList();
    }

    @GetMapping({"/accounts/{accountId}/operations"})
    public List<AccountOperationDTO> getHistory(@PathVariable String accountId) {
        return this.bankAccountService.accountHistory(accountId);
    }

    @GetMapping({"/accounts/{accountId}/pageOperations"})
    public AccountHistoryDTO getAccountHistory(@PathVariable String accountId, @RequestParam(name = "page",defaultValue = "0") int page, @RequestParam(name = "size",defaultValue = "5") int size) throws BankAccountNotFoundException {
        return this.bankAccountService.getAccountHistory(accountId, page, size);
    }

    @PostMapping({"/accounts/debit"})
    public DebitDTO debit(@RequestBody DebitDTO debitDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
        this.bankAccountService.debit(debitDTO.getAccountId(), debitDTO.getAmount(), debitDTO.getDescription());
        return debitDTO;
    }

    @PostMapping({"/accounts/credit"})
    public CreditDTO credit(@RequestBody CreditDTO creditDTO) throws BankAccountNotFoundException {
        this.bankAccountService.credit(creditDTO.getAccountId(), creditDTO.getAmount(), creditDTO.getDescription());
        return creditDTO;
    }

    @PostMapping({"/accounts/transfer"})
    public void transfer(@RequestBody TransferRequestDTO transferRequestDTO) throws BankAccountNotFoundException, BalanceNotSufficientException {
        this.bankAccountService.transfer(transferRequestDTO.getAccountSource(), transferRequestDTO.getAccountDestination(), transferRequestDTO.getAmount());
    }
}
