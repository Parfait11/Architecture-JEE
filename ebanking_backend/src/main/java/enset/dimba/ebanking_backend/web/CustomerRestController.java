package enset.dimba.ebanking_backend.web;

import enset.dimba.ebanking_backend.dtos.CustomerDTO;
import enset.dimba.ebanking_backend.exceptions.CustomerNotFoundException;
import enset.dimba.ebanking_backend.services.BankAccountService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public class CustomerRestController {
    private BankAccountService bankAccountService;

    public CustomerRestController() {
    }

    @GetMapping({"/customers"})
    public List<CustomerDTO> customers() {
        return this.bankAccountService.listCustomers();
    }

    @GetMapping({"/customers/search"})
    public List<CustomerDTO> searchCustomers(@RequestParam(name = "keyword",defaultValue = "") String keyword) {
        return this.bankAccountService.searchCustomers("%" + keyword + "%");
    }

    @GetMapping({"/customers/{id}"})
    public CustomerDTO getCustomer(@PathVariable(name = "id") Long customerId) throws CustomerNotFoundException {
        return this.bankAccountService.getCustomer(customerId);
    }

    @PostMapping({"/customers"})
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        return this.bankAccountService.saveCustomer(customerDTO);
    }

    @PutMapping({"/customers/{customerId}"})
    public CustomerDTO updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDTO customerDTO) {
        customerDTO.setId(customerId);
        return this.bankAccountService.updateCustomer(customerDTO);
    }

    @DeleteMapping({"/customers/{id}"})
    public void deleteCustomer(@PathVariable Long id) {
        this.bankAccountService.deleteCustomer(id);
    }
}
