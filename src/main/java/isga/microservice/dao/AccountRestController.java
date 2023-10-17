package isga.microservice.dao;

import isga.microservice.dto.BankAccountRequestDTO;
import isga.microservice.dto.BankAccountResponseDTO;
import isga.microservice.entities.BankAccount;
import isga.microservice.mappers.AccountMapper;
import isga.microservice.repositories.BankAccountRepository;
import isga.microservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class AccountRestController {

    @Autowired
    private AccountService accountService;
    private BankAccountRepository bankAccountRepo;
    private AccountMapper accountMapper ;

    public AccountRestController(BankAccountRepository bankAccountRepo) {
        this.bankAccountRepo = bankAccountRepo;
    }
    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts(){
        return bankAccountRepo.findAll();
    }
    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccounts(@PathVariable  String id){
        return bankAccountRepo.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Account %s not found" ,id)));
    }
    @PostMapping("/addBankAccount")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO requestDTO){
        return accountService.addAccount(requestDTO);
    }
    @PutMapping("/update/{id}")
    public  BankAccount updateBankAccount(@PathVariable String id,@RequestBody BankAccount bankAccount){
        BankAccount account = bankAccountRepo.findById(id).orElseThrow();
        if (bankAccount.getBalance()!=null) account.setBalance(bankAccount.getBalance());
        if (bankAccount.getCreatedAt()!=null) account.setCreatedAt(new Date());
        if (bankAccount.getType()!=null) account.setType(bankAccount.getType());
        if (bankAccount.getCurrency()!=null) account.setCurrency(bankAccount.getCurrency());
        return bankAccountRepo.save(account);
    }
    @DeleteMapping("/delete/{id}")
    public void  deleteBankAccount(@PathVariable String id){
         bankAccountRepo.deleteById(id);
    }
}
