package enset.dimba.ebanking_backend.services;

import enset.dimba.ebanking_backend.entities.BankAccount;
import enset.dimba.ebanking_backend.entities.CurrentAccount;
import enset.dimba.ebanking_backend.entities.SavingAccount;
import enset.dimba.ebanking_backend.repositories.BankAccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintStream;

@Service
@Transactional
public class BankService {
    @Autowired
    private BankAccountRepository bankAccountRepository;

    public BankService() {
    }

    public void consulter() {
        // Correction : plus de cast, orElse(null) retourne BankAccount
        BankAccount bankAccount = this.bankAccountRepository
                .findById("0b36be78-8d5d-446b-9f20-37eadc9d3c3b")
                .orElse(null);
        if (bankAccount != null) {
            System.out.println("*****************************");
            System.out.println(bankAccount.getId());
            System.out.println(bankAccount.getBalance());
            System.out.println(bankAccount.getStatus());
            System.out.println(bankAccount.getCreatedAt());
            System.out.println(bankAccount.getCustomer().getName());
            System.out.println(bankAccount.getClass().getSimpleName());
            if (bankAccount instanceof CurrentAccount) {
                System.out.println("Over Draft=>" + ((CurrentAccount) bankAccount).getOverDraft());
            } else if (bankAccount instanceof SavingAccount) {
                System.out.println("Rate=>" + ((SavingAccount) bankAccount).getInterestRate());
            }
            bankAccount.getAccountOperations().forEach(op -> {
                PrintStream var10000 = System.out;
                String var10001 = String.valueOf(op.getType());
                var10000.println(var10001 + "\t" + String.valueOf(op.getOperationDate()) + "\t" + op.getAmount());
            });
        }
    }
}